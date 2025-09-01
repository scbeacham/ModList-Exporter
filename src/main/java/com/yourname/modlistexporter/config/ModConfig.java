package com.yourname.modlistexporter.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yourname.modlistexporter.ModListExporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Configuration manager for ModList Exporter.
 * Handles loading, saving, and managing the mod's configuration file.
 */
public class ModConfig {
    private static final String CONFIG_FILE = "config.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    
    private boolean autoExportOnStartup;
    private String clipboardFormat;
    
    /**
     * Default configuration values.
     */
    public ModConfig() {
        this.autoExportOnStartup = false;
        this.clipboardFormat = "markdown";
    }
    
    /**
     * Loads the configuration from file, creating default config if it doesn't exist.
     *
     * @param configDir The directory where the config file should be located
     * @return The loaded configuration
     */
    public static ModConfig load(Path configDir) {
        Path configFile = configDir.resolve(CONFIG_FILE);
        
        try {
            if (Files.exists(configFile)) {
                // Load existing config
                String jsonContent = Files.readString(configFile);
                ModConfig config = GSON.fromJson(jsonContent, ModConfig.class);
                ModListExporter.LOGGER.info("Loaded configuration from {}", configFile);
                return config;
            } else {
                // Create default config
                ModConfig defaultConfig = new ModConfig();
                defaultConfig.save(configDir);
                ModListExporter.LOGGER.info("Created default configuration at {}", configFile);
                return defaultConfig;
            }
        } catch (IOException e) {
            ModListExporter.LOGGER.error("Failed to load configuration, using defaults", e);
            return new ModConfig();
        }
    }
    
    /**
     * Saves the configuration to file.
     *
     * @param configDir The directory where the config file should be saved
     */
    public void save(Path configDir) {
        try {
            Path configFile = configDir.resolve(CONFIG_FILE);
            Files.createDirectories(configDir);
            String jsonContent = GSON.toJson(this);
            Files.writeString(configFile, jsonContent);
            ModListExporter.LOGGER.info("Saved configuration to {}", configFile);
        } catch (IOException e) {
            ModListExporter.LOGGER.error("Failed to save configuration", e);
        }
    }
    
    /**
     * Gets whether auto-export on startup is enabled.
     *
     * @return true if auto-export is enabled, false otherwise
     */
    public boolean isAutoExportOnStartup() {
        return autoExportOnStartup;
    }
    
    /**
     * Sets whether auto-export on startup is enabled.
     *
     * @param autoExportOnStartup true to enable auto-export, false to disable
     */
    public void setAutoExportOnStartup(boolean autoExportOnStartup) {
        this.autoExportOnStartup = autoExportOnStartup;
    }
    
    /**
     * Gets the clipboard format preference.
     *
     * @return "markdown" or "plaintext"
     */
    public String getClipboardFormat() {
        return (clipboardFormat != null && !clipboardFormat.trim().isEmpty()) ? clipboardFormat : "markdown";
    }
    
    /**
     * Sets the clipboard format preference.
     *
     * @param clipboardFormat "markdown" or "plaintext"
     */
    public void setClipboardFormat(String clipboardFormat) {
        this.clipboardFormat = clipboardFormat;
    }
    
    /**
     * Checks if the clipboard format is set to markdown.
     *
     * @return true if markdown, false if plaintext
     */
    public boolean isClipboardFormatMarkdown() {
        return "markdown".equalsIgnoreCase(getClipboardFormat());
    }
}
