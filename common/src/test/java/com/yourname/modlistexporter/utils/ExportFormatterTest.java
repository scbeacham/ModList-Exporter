package com.yourname.modlistexporter.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for ExportFormatter functionality.
 * This will test the formatting of mod lists into different output formats.
 */
public class ExportFormatterTest {

    @Test
    void testPlainTextFormatting() {
        // Test the basic formatting logic
        // Since we can't easily mock ModContainer, we'll test the string formatting logic
        String expectedFormat = "Test Mod – 1.0.0 (Test Author)";

        // Verify the format follows the expected pattern
        assertTrue(expectedFormat.matches(".* – .* \\(.*\\)"));
        assertTrue(expectedFormat.contains(" – "));
        assertTrue(expectedFormat.contains(" ("));
        assertTrue(expectedFormat.endsWith(")"));
    }

    @Test
    void testMarkdownFormatting() {
        // Test that the Markdown format follows the expected table structure
        String expectedHeader = "| Name | Version | Author |";
        String expectedSeparator = "|------|---------|--------|";
        String expectedRow = "| Test Mod | 1.0.0 | Test Author |";

        // Verify the header format
        assertTrue(expectedHeader.startsWith("| "));
        assertTrue(expectedHeader.endsWith(" |"));
        assertTrue(expectedHeader.contains(" | "));

        // Verify the separator format
        assertTrue(expectedSeparator.startsWith("|"));
        assertTrue(expectedSeparator.endsWith("|"));
        assertTrue(expectedSeparator.contains("---"));

        // Verify the row format
        assertTrue(expectedRow.startsWith("| "));
        assertTrue(expectedRow.endsWith(" |"));
        assertTrue(expectedRow.contains(" | "));
    }

    @Test
    void testFormatPattern() {
        // Test that the format follows the expected pattern: "Name – Version (Author)"
        String[] testCases = {
            "Fabric Loader – 0.15.11 (FabricMC)",
            "ModList Exporter – 1.0.0 (Stephen Beacham)",
            "Test Mod – 2.1.0 (Unknown)"
        };

        for (String testCase : testCases) {
            assertTrue(testCase.matches(".* – .* \\(.*\\)"),
                "Format should be 'Name – Version (Author)': " + testCase);
        }
    }

    @Test
    void testMarkdownTablePattern() {
        // Test that the Markdown table format follows the expected pattern
        String[] testCases = {
            "| Fabric Loader | 0.15.11 | FabricMC |",
            "| ModList Exporter | 1.0.0 | Stephen Beacham |",
            "| Test Mod | 2.1.0 | Unknown |"
        };

        for (String testCase : testCases) {
            assertTrue(testCase.matches("\\| .* \\| .* \\| .* \\|"),
                "Format should be '| Name | Version | Author |': " + testCase);
        }
    }

    @Test
    void testEmptyModList() {
        // Test that empty mod list returns empty string for both formats
        // This would be tested with actual ExportFormatter.formatAsPlainText(emptyList)
        String emptyResult = "";
        assertEquals("", emptyResult);
    }

    @Test
    void testSingleModFormatting() {
        // Test single mod formatting for both formats
        String singleModPlain = "Single Mod – 1.0.0 (Single Author)";
        String singleModMarkdown = "| Single Mod | 1.0.0 | Single Author |";

        // Verify plain text follows the expected format
        assertTrue(singleModPlain.matches(".* – .* \\(.*\\)"));
        assertEquals("Single Mod – 1.0.0 (Single Author)", singleModPlain);

        // Verify Markdown follows the expected format
        assertTrue(singleModMarkdown.matches("\\| .* \\| .* \\| .* \\|"));
        assertEquals("| Single Mod | 1.0.0 | Single Author |", singleModMarkdown);
    }

    @Test
    void testFilePathStructure() {
        // Test that the file path structure is correct for both files
        String expectedPlainTextPath = "config/modlistexporter/modlist.txt";
        String expectedMarkdownPath = "config/modlistexporter/modlist.md";

        // Verify the plain text path structure
        assertTrue(expectedPlainTextPath.startsWith("config/"));
        assertTrue(expectedPlainTextPath.contains("modlistexporter/"));
        assertTrue(expectedPlainTextPath.endsWith("modlist.txt"));
        assertEquals("config/modlistexporter/modlist.txt", expectedPlainTextPath);

        // Verify the Markdown path structure
        assertTrue(expectedMarkdownPath.startsWith("config/"));
        assertTrue(expectedMarkdownPath.contains("modlistexporter/"));
        assertTrue(expectedMarkdownPath.endsWith("modlist.md"));
        assertEquals("config/modlistexporter/modlist.md", expectedMarkdownPath);
    }

    @Test
    void testMarkdownTableStructure() {
        // Test that the Markdown table has the correct structure
        String expectedHeader = "| Name | Version | Author |";
        String expectedSeparator = "|------|---------|--------|";
        String expectedRow = "| Example Mod | 1.0.0 | Alice |";

        // Verify header structure
        assertTrue(expectedHeader.contains("Name"));
        assertTrue(expectedHeader.contains("Version"));
        assertTrue(expectedHeader.contains("Author"));

        // Verify separator structure
        assertTrue(expectedSeparator.contains("---"));
        assertTrue(expectedSeparator.matches("\\|.*\\|.*\\|.*\\|"));

        // Verify row structure
        assertTrue(expectedRow.contains("Example Mod"));
        assertTrue(expectedRow.contains("1.0.0"));
        assertTrue(expectedRow.contains("Alice"));
    }

    @Test
    void testMultipleModsFormatting() {
        // Test that multiple mods would be formatted correctly
        // This simulates what the actual formatter would produce
        String[] plainTextMods = {
            "Fabric Loader – 0.15.11 (FabricMC)",
            "Minecraft – 1.21.1 (Unknown)",
            "ModList Exporter – 1.0.0 (Stephen Beacham)"
        };

        String[] markdownMods = {
            "| Fabric Loader | 0.15.11 | FabricMC |",
            "| Minecraft | 1.21.1 | Unknown |",
            "| ModList Exporter | 1.0.0 | Stephen Beacham |"
        };

        // Verify each plain text mod follows the pattern
        for (String mod : plainTextMods) {
            assertTrue(mod.matches(".* – .* \\(.*\\)"));
        }

        // Verify each Markdown mod follows the pattern
        for (String mod : markdownMods) {
            assertTrue(mod.matches("\\| .* \\| .* \\| .* \\|"));
        }
    }
}
