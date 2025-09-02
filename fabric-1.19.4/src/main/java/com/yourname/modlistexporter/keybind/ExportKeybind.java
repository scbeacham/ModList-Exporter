package com.yourname.modlistexporter.keybind;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import com.yourname.modlistexporter.commands.ExportCommand;
import com.yourname.modlistexporter.ModListExporter;

/**
 * Handles the keybinding for triggering modlist export.
 * Uses Minecraft's vanilla keybinding system with default F9 key.
 */
public class ExportKeybind {
    private static KeyBinding exportKeybind;
    
    /**
     * Registers the keybinding with Minecraft's keybinding system.
     */
    public static void register() {
        // Create the keybinding with default F9 key
        exportKeybind = new KeyBinding(
            "modlistexporter.key.export", // Translation key
            InputUtil.Type.KEYSYM, // Keyboard key
            GLFW.GLFW_KEY_F9, // Default F9 key
            "modlistexporter.key.category" // Category translation key
        );
        
        ModListExporter.LOGGER.info("Registered export keybinding with default F9 key");
    }
    
    /**
     * Gets the keybinding instance.
     *
     * @return The keybinding for export functionality
     */
    public static KeyBinding getKeybind() {
        return exportKeybind;
    }
    
    /**
     * Checks if the keybinding was pressed and handles the export.
     * This should be called every tick in the client.
     */
    public static void handleKeyPress() {
        if (exportKeybind != null && exportKeybind.wasPressed()) {
            ModListExporter.LOGGER.info("Export keybinding pressed - triggering modlist export");
            
            // Run the export logic (same as /modlist command)
            ExportCommand.handleModlistCommand(false); // false = not auto-export
        }
    }
}
