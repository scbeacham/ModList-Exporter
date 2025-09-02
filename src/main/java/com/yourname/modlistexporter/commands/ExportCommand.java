package com.yourname.modlistexporter.commands;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import com.yourname.modlistexporter.utils.ExportFormatter;
import com.yourname.modlistexporter.utils.ClipboardHelper;
import com.yourname.modlistexporter.ModListExporter;
import com.yourname.modlistexporter.config.ModConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

/**
 * Client-side command handler for the /modlist command.
 * Exports the current mod list to both plain text and Markdown files, and copies content to clipboard based on config.
 */
public class ExportCommand {

    /**
     * Registers the command functionality.
     */
    public static void register() {
        ModListExporter.LOGGER.info("ExportCommand registered - use '/modlist' in chat to export mod list");
    }

    /**
     * Handles the /modlist command.
     * Called from the ChatScreenMixin when the command is detected.
     */
    public static void handleModlistCommand() {
        handleModlistCommand(false);
    }

    /**
     * Handles the /modlist command with auto-export support.
     *
     * @param isAutoExport true if this is an auto-export, false if manual command
     */
    public static void handleModlistCommand(boolean isAutoExport) {
        MinecraftClient client = MinecraftClient.getInstance();

        try {
            // Initialize config if not already done
            if (ModListExporter.getConfig() == null) {
                ModListExporter.initializeConfig();
            }

            // Get all loaded mods
            Collection<ModContainer> mods = FabricLoader.getInstance().getAllMods();

            // Format the mod list in both plain text and Markdown
            String plainTextList = ExportFormatter.formatAsPlainText(mods);
            String markdownList = ExportFormatter.formatAsMarkdown(mods);

            // Get the game directory and create the output directory
            Path gameDir = client.runDirectory.toPath();
            Path configDir = gameDir.resolve("config");
            Path modlistDir = configDir.resolve("modlistexporter");
            Path plainTextFile = modlistDir.resolve("modlist.txt");
            Path markdownFile = modlistDir.resolve("modlist.md");

            // Create the directory structure if it doesn't exist
            Files.createDirectories(modlistDir);

            // Write both formatted lists to their respective files (overwrite if exists)
            Files.writeString(plainTextFile, plainTextList);
            Files.writeString(markdownFile, markdownList);

            // Get configuration for clipboard format
            ModConfig config = ModListExporter.getConfig();
            String clipboardContent = config != null && config.isClipboardFormatMarkdown() ? markdownList : plainTextList;
            String clipboardFormatName = config != null && config.isClipboardFormatMarkdown() ? "Markdown" : "Plain text";

            // Copy to clipboard
            boolean clipboardSuccess = ClipboardHelper.copyToClipboard(clipboardContent);

            // Send success message to the player
            if (client.player != null) {
                String messageKey;
                if (isAutoExport) {
                    messageKey = "modlistexporter.chat.export_auto";
                } else {
                    messageKey = "modlistexporter.chat.export_success";
                }
                
                // Try to get translated text, fallback to hardcoded if not found
                Text message = Text.translatable(messageKey, clipboardFormatName);
                String translatedText = message.getString();
                
                // Debug logging to see what's happening
                ModListExporter.LOGGER.info("Translation key: {}", messageKey);
                ModListExporter.LOGGER.info("Translated text: {}", translatedText);
                
                // If translation failed (key == translated text), use fallback
                if (translatedText.equals(messageKey)) {
                    ModListExporter.LOGGER.warn("Translation failed for key: {}, using fallback", messageKey);
                    String fallbackMessage;
                    if (isAutoExport) {
                        fallbackMessage = "✅ Auto-exported mod list on startup (" + clipboardFormatName + " copied to clipboard)";
                    } else {
                        fallbackMessage = "✅ Exported mod list to config/modlistexporter/modlist.txt and modlist.md (" + clipboardFormatName + " copied to clipboard)";
                    }
                    message = Text.literal(fallbackMessage);
                }
                
                client.player.sendMessage(message, false);
            }

            String logMessage = isAutoExport ? 
                "Successfully auto-exported mod list to modlist.txt and modlist.md, " + clipboardFormatName + " copied to clipboard" :
                "Successfully exported mod list to modlist.txt and modlist.md, " + clipboardFormatName + " copied to clipboard";
            ModListExporter.LOGGER.info(logMessage);

        } catch (IOException e) {
            // Send error message to the player
            if (client.player != null) {
                String errorMessageKey = isAutoExport ? 
                    "modlistexporter.chat.export_auto_fail" :
                    "modlistexporter.chat.export_fail";
                
                // Try to get translated text, fallback to hardcoded if not found
                Text errorMessage = Text.translatable(errorMessageKey, e.getMessage());
                String translatedErrorText = errorMessage.getString();
                
                // If translation failed (key == translated text), use fallback
                if (translatedErrorText.equals(errorMessageKey)) {
                    ModListExporter.LOGGER.warn("Translation failed for error key: {}, using fallback", errorMessageKey);
                    String fallbackErrorMessage;
                    if (isAutoExport) {
                        fallbackErrorMessage = "❌ Failed to auto-export mod list: " + e.getMessage();
                    } else {
                        fallbackErrorMessage = "❌ Failed to export mod list: " + e.getMessage();
                    }
                    errorMessage = Text.literal(fallbackErrorMessage);
                }
                
                client.player.sendMessage(errorMessage, false);
            }

            ModListExporter.LOGGER.error("Failed to export mod list", e);
        }
    }
}
