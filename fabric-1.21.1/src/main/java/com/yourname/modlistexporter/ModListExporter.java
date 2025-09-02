package com.yourname.modlistexporter;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yourname.modlistexporter.commands.ExportCommand;
import com.yourname.modlistexporter.config.ModConfig;
import com.yourname.modlistexporter.config.ConfigLogger;
import com.yourname.modlistexporter.keybind.ExportKeybind;
import com.yourname.modlistexporter.utils.ClipboardHelper;
import com.yourname.modlistexporter.utils.LWJGLClipboardFallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.nio.file.Path;

public class ModListExporter implements ModInitializer {
    public static final String MOD_ID = "modlistexporter";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    
    private static ModConfig config;
    private static ConfigLogger configLogger;

    @Override
    public void onInitialize() {
        LOGGER.info("Hello World from ModList Exporter!");
        
        // Set up the config logger
        configLogger = new ConfigLogger() {
            @Override
            public void info(String message, Object... args) {
                LOGGER.info(message, args);
            }
            
            @Override
            public void error(String message, Throwable throwable) {
                LOGGER.error(message, throwable);
            }
        };
        
        // Set up the clipboard fallback
        ClipboardHelper.setFallback(new LWJGLClipboardFallback());
        
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
            config = ModConfig.load(configDir, configLogger);
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
