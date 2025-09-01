package com.yourname.modlistexporter;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yourname.modlistexporter.commands.ExportCommand;
import com.yourname.modlistexporter.config.ModConfig;
import com.yourname.modlistexporter.keybind.ExportKeybind;
import net.minecraft.client.MinecraftClient;

import java.nio.file.Path;

public class ModListExporter implements ModInitializer {
    public static final String MOD_ID = "modlistexporter";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static ModConfig config;

    @Override
    public void onInitialize() {
        LOGGER.info("Hello World from ModList Exporter!");
        
        // Register the command functionality
        ExportCommand.register();
        
        // Register the keybinding
        ExportKeybind.register();
    }
    
    /**
     * Initializes the configuration when the client is ready.
     * Called from the client-side initialization.
     */
    public static void initializeConfig() {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client != null) {
            Path gameDir = client.runDirectory.toPath();
            Path configDir = gameDir.resolve("config").resolve("modlistexporter");
            config = ModConfig.load(configDir);
            LOGGER.info("Configuration initialized - Auto-export: {}, Clipboard format: {}", 
                config.isAutoExportOnStartup(), config.getClipboardFormat());
        }
    }
    
    /**
     * Gets the current configuration.
     *
     * @return The mod configuration, or null if not initialized
     */
    public static ModConfig getConfig() {
        return config;
    }
}
