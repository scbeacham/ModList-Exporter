package com.yourname.modlistexporter.utils;

import net.fabricmc.loader.api.ModContainer;
import net.fabricmc.loader.api.metadata.ModMetadata;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Utility class for formatting mod lists into different output formats.
 */
public class ExportFormatter {

    /**
     * Formats a list of mods into plain text format.
     * Each mod is formatted as "Name – Version (Author)" on a separate line.
     *
     * @param mods Collection of mod containers to format
     * @return Formatted string representation of the mod list
     */
    public static String formatAsPlainText(Collection<ModContainer> mods) {
        return mods.stream()
                .map(ExportFormatter::formatModAsPlainText)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Formats a list of mods into Markdown table format.
     * Creates a table with columns: Name | Version | Author
     *
     * @param mods Collection of mod containers to format
     * @return Formatted Markdown table representation of the mod list
     */
    public static String formatAsMarkdown(Collection<ModContainer> mods) {
        StringBuilder markdown = new StringBuilder();

        // Add table header
        markdown.append("| Name | Version | Author |\n");
        markdown.append("|------|---------|--------|\n");

        // Add table rows
        String rows = mods.stream()
                .map(ExportFormatter::formatModAsMarkdownRow)
                .collect(Collectors.joining("\n"));

        markdown.append(rows);

        return markdown.toString();
    }

    /**
     * Formats a single mod into plain text format.
     *
     * @param mod The mod container to format
     * @return Formatted string representation of the mod
     */
    private static String formatModAsPlainText(ModContainer mod) {
        ModMetadata metadata = mod.getMetadata();
        String name = metadata.getName();
        String version = metadata.getVersion().getFriendlyString();

        // Get author if available
        String author = "Unknown";
        if (!metadata.getAuthors().isEmpty()) {
            author = metadata.getAuthors().iterator().next().getName();
        }

        return String.format("%s – %s (%s)", name, version, author);
    }

    /**
     * Formats a single mod into a Markdown table row.
     *
     * @param mod The mod container to format
     * @return Formatted Markdown table row representation of the mod
     */
    private static String formatModAsMarkdownRow(ModContainer mod) {
        ModMetadata metadata = mod.getMetadata();
        String name = metadata.getName();
        String version = metadata.getVersion().getFriendlyString();

        // Get author if available
        String author = "Unknown";
        if (!metadata.getAuthors().isEmpty()) {
            author = metadata.getAuthors().iterator().next().getName();
        }

        return String.format("| %s | %s | %s |", name, version, author);
    }
}
