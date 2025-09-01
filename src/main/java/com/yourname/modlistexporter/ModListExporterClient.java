package com.yourname.modlistexporter;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import com.yourname.modlistexporter.commands.ExportCommand;
import com.yourname.modlistexporter.config.ModConfig;

/**
 * Client-side initialization for ModList Exporter.
 * Handles client-specific setup and auto-export functionality.
 */
public class ModListExporterClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Initialize configuration when client is ready
        ModListExporter.initializeConfig();
        
        // Check if auto-export is enabled and schedule it
        ModConfig config = ModListExporter.getConfig();
        if (config != null && config.isAutoExportOnStartup()) {
            // Schedule auto-export for after the world is loaded
            // We'll use a simple delay approach since we don't have Fabric API
            new Thread(() -> {
                try {
                    // Wait for the client to be fully initialized
                    Thread.sleep(5000); // 5 second delay
                    
                    // Check if we're in a world and player exists
                    MinecraftClient client = MinecraftClient.getInstance();
                    if (client != null && client.player != null) {
                        // Execute auto-export on the main thread
                        client.execute(() -> {
                            ModListExporter.LOGGER.info("Executing auto-export on startup");
                            ExportCommand.handleModlistCommand(true);
                        });
                    }
                } catch (InterruptedException e) {
                    ModListExporter.LOGGER.warn("Auto-export thread interrupted", e);
                }
            }).start();
        }
    }
}
