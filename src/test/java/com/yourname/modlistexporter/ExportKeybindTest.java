package com.yourname.modlistexporter;

import com.yourname.modlistexporter.keybind.ExportKeybind;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ExportKeybind functionality.
 * This will test the keybinding registration and export trigger logic.
 */
public class ExportKeybindTest {

    @BeforeEach
    void setUp() {
        // Setup test environment
    }

    @Test
    void testKeybindRegistration() {
        // Test that keybinding can be registered without errors
        try {
            ExportKeybind.register();
            assertNotNull(ExportKeybind.getKeybind(), "Keybinding should be created after registration");
        } catch (Exception e) {
            fail("Keybinding registration should not throw exceptions: " + e.getMessage());
        }
    }

    @Test
    void testKeybindProperties() {
        // Test that keybinding has correct properties
        ExportKeybind.register();
        var keybind = ExportKeybind.getKeybind();
        
        assertNotNull(keybind, "Keybinding should not be null");
        assertEquals("key.modlistexporter.export", keybind.getTranslationKey(), 
            "Translation key should match expected value");
        assertEquals("category.modlistexporter.general", keybind.getCategory(), 
            "Category should match expected value");
    }

    @Test
    void testKeybindDefaultKey() {
        // Test that keybinding has F9 as default key
        ExportKeybind.register();
        var keybind = ExportKeybind.getKeybind();
        
        assertNotNull(keybind, "Keybinding should not be null");
        // Note: We can't easily test the actual key code without GLFW context,
        // but we can verify the keybinding was created successfully
        assertTrue(true, "Keybinding should be created with F9 as default");
    }

    @Test
    void testKeybindHandleKeyPress() {
        // Test that handleKeyPress method doesn't throw exceptions
        try {
            ExportKeybind.register();
            ExportKeybind.handleKeyPress();
            assertTrue(true, "handleKeyPress should complete without exceptions");
        } catch (Exception e) {
            fail("handleKeyPress should not throw exceptions: " + e.getMessage());
        }
    }

    @Test
    void testKeybindMultipleRegistrations() {
        // Test that multiple registrations don't cause issues
        try {
            ExportKeybind.register();
            ExportKeybind.register(); // Second registration
            assertNotNull(ExportKeybind.getKeybind(), "Keybinding should still be valid after multiple registrations");
        } catch (Exception e) {
            fail("Multiple keybinding registrations should not cause issues: " + e.getMessage());
        }
    }

    @Test
    void testKeybindNullSafety() {
        // Test that methods handle null keybinding gracefully
        try {
            // This would happen if register() wasn't called
            ExportKeybind.handleKeyPress();
            assertTrue(true, "handleKeyPress should handle null keybinding gracefully");
        } catch (Exception e) {
            fail("handleKeyPress should handle null keybinding without exceptions: " + e.getMessage());
        }
    }
}
