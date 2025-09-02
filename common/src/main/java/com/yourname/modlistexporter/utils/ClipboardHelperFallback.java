package com.yourname.modlistexporter.utils;

/**
 * Fallback interface for clipboard operations that require Minecraft-specific APIs.
 * This allows the common module to delegate clipboard operations to Minecraft-specific implementations.
 */
public interface ClipboardHelperFallback {
    
    /**
     * Copies the given text to the system clipboard using Minecraft-specific APIs.
     *
     * @param text The text to copy to the clipboard
     * @return true if successful, false if the operation fails
     */
    boolean copyToClipboard(String text);
    
    /**
     * Reads text from the system clipboard using Minecraft-specific APIs.
     *
     * @return The text from the clipboard, or empty string if clipboard is empty or unavailable
     */
    String readFromClipboard();
    
    /**
     * Clears the system clipboard using Minecraft-specific APIs.
     */
    void clearClipboard();
}
