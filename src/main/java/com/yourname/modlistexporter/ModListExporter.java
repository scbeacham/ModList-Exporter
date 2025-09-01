package com.yourname.modlistexporter;

import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.yourname.modlistexporter.commands.ExportCommand;

public class ModListExporter implements ModInitializer {
    public static final String MOD_ID = "modlistexporter";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        LOGGER.info("Hello World from ModList Exporter!");
        
        // Register the command functionality
        ExportCommand.register();
    }
}
