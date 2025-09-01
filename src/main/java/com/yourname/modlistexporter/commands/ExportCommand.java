package com.yourname.modlistexporter.commands;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import com.yourname.modlistexporter.utils.ExportFormatter;
import com.yourname.modlistexporter.utils.ClipboardHelper;
import com.yourname.modlistexporter.ModListExporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

/**
 * Client-side command handler for the /modlist command.
 * Exports the current mod list to both plain text and Markdown files, and copies Markdown to clipboard.
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
        MinecraftClient client = MinecraftClient.getInstance();

        try {
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

            // Copy Markdown to clipboard
            boolean clipboardSuccess = ClipboardHelper.copyToClipboard(markdownList);

            // Send success message to the player
            if (client.player != null) {
                if (clipboardSuccess) {
                    client.player.sendMessage(Text.literal("✅ Exported mod list to modlist.txt and modlist.md (Markdown copied to clipboard)"), false);
                } else {
                    client.player.sendMessage(Text.literal("✅ Exported mod list to modlist.txt and modlist.md (clipboard unavailable)"), false);
                }
            }

            ModListExporter.LOGGER.info("Successfully exported mod list to modlist.txt and modlist.md, Markdown copied to clipboard");

        } catch (IOException e) {
            // Send error message to the player
            if (client.player != null) {
                client.player.sendMessage(Text.literal("❌ Failed to export mod list: " + e.getMessage()), false);
            }

            ModListExporter.LOGGER.error("Failed to export mod list", e);
        }
    }
}
