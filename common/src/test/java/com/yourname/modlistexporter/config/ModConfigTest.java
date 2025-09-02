package com.yourname.modlistexporter.config;

import com.yourname.modlistexporter.config.ModConfig;
import com.yourname.modlistexporter.config.ConfigLogger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Test class for ModConfig functionality.
 * This will test configuration loading, saving, and default values.
 */
public class ModConfigTest {

    @TempDir
    Path tempDir;
    
    private ConfigLogger mockLogger;

    @BeforeEach
    void setUp() {
        mockLogger = new ConfigLogger() {
            @Override
            public void info(String message, Object... args) {
                // Mock implementation - just ignore for tests
            }
            
            @Override
            public void error(String message, Throwable throwable) {
                // Mock implementation - just ignore for tests
            }
        };
    }

    @Test
    void testDefaultConfiguration() {
        ModConfig config = new ModConfig();
        
        // Test default values
        assertFalse(config.isAutoExportOnStartup(), "Auto-export should be false by default");
        assertEquals("markdown", config.getClipboardFormat(), "Clipboard format should be 'markdown' by default");
        assertTrue(config.isClipboardFormatMarkdown(), "Should be markdown format by default");
    }

    @Test
    void testConfigurationSetters() {
        ModConfig config = new ModConfig();
        
        // Test setting auto-export
        config.setAutoExportOnStartup(true);
        assertTrue(config.isAutoExportOnStartup(), "Auto-export should be true after setting");
        
        config.setAutoExportOnStartup(false);
        assertFalse(config.isAutoExportOnStartup(), "Auto-export should be false after setting");
        
        // Test setting clipboard format
        config.setClipboardFormat("plaintext");
        assertEquals("plaintext", config.getClipboardFormat(), "Clipboard format should be 'plaintext'");
        assertFalse(config.isClipboardFormatMarkdown(), "Should not be markdown format");
        
        config.setClipboardFormat("markdown");
        assertEquals("markdown", config.getClipboardFormat(), "Clipboard format should be 'markdown'");
        assertTrue(config.isClipboardFormatMarkdown(), "Should be markdown format");
    }

    @Test
    void testConfigurationSaveAndLoad() throws Exception {
        ModConfig originalConfig = new ModConfig();
        originalConfig.setAutoExportOnStartup(true);
        originalConfig.setClipboardFormat("plaintext");
        
        // Save configuration
        originalConfig.save(tempDir, mockLogger);
        
        // Verify config file was created
        Path configFile = tempDir.resolve("config.json");
        assertTrue(Files.exists(configFile), "Config file should be created");
        
        // Load configuration
        ModConfig loadedConfig = ModConfig.load(tempDir, mockLogger);
        
        // Verify loaded values match original
        assertEquals(originalConfig.isAutoExportOnStartup(), loadedConfig.isAutoExportOnStartup(), 
            "Auto-export should match after save/load");
        assertEquals(originalConfig.getClipboardFormat(), loadedConfig.getClipboardFormat(), 
            "Clipboard format should match after save/load");
    }

    @Test
    void testDefaultConfigCreation() throws Exception {
        // Load from empty directory (should create default config)
        ModConfig config = ModConfig.load(tempDir, mockLogger);
        
        // Verify default values
        assertFalse(config.isAutoExportOnStartup(), "Should have default auto-export value");
        assertEquals("markdown", config.getClipboardFormat(), "Should have default clipboard format");
        
        // Verify config file was created
        Path configFile = tempDir.resolve("config.json");
        assertTrue(Files.exists(configFile), "Default config file should be created");
    }

    @Test
    void testClipboardFormatValidation() {
        ModConfig config = new ModConfig();
        
        // Test null clipboard format (should default to markdown)
        config.setClipboardFormat(null);
        assertEquals("markdown", config.getClipboardFormat(), "Null should default to markdown");
        assertTrue(config.isClipboardFormatMarkdown(), "Null should be treated as markdown");
        
        // Test empty clipboard format (should default to markdown)
        config.setClipboardFormat("");
        assertEquals("markdown", config.getClipboardFormat(), "Empty should default to markdown");
        assertTrue(config.isClipboardFormatMarkdown(), "Empty should be treated as markdown");
        
        // Test case insensitive comparison
        config.setClipboardFormat("MARKDOWN");
        assertTrue(config.isClipboardFormatMarkdown(), "Uppercase should be recognized as markdown");
        
        config.setClipboardFormat("Markdown");
        assertTrue(config.isClipboardFormatMarkdown(), "Title case should be recognized as markdown");
        
        config.setClipboardFormat("PLAINTEXT");
        assertFalse(config.isClipboardFormatMarkdown(), "Uppercase plaintext should not be markdown");
    }

    @Test
    void testConfigurationPersistence() throws Exception {
        // Create and save initial config
        ModConfig config1 = new ModConfig();
        config1.setAutoExportOnStartup(true);
        config1.setClipboardFormat("plaintext");
        config1.save(tempDir, mockLogger);
        
        // Load and modify config
        ModConfig config2 = ModConfig.load(tempDir, mockLogger);
        config2.setAutoExportOnStartup(false);
        config2.setClipboardFormat("markdown");
        config2.save(tempDir, mockLogger);
        
        // Load again and verify changes persisted
        ModConfig config3 = ModConfig.load(tempDir, mockLogger);
        assertFalse(config3.isAutoExportOnStartup(), "Auto-export change should persist");
        assertEquals("markdown", config3.getClipboardFormat(), "Clipboard format change should persist");
        assertTrue(config3.isClipboardFormatMarkdown(), "Markdown format should be recognized");
    }
}
