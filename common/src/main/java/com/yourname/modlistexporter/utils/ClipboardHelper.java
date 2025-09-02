package com.yourname.modlistexporter.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;

/**
 * Utility class for clipboard operations.
 * Handles copying text to and from the system clipboard using AWT Toolkit.
 * For Minecraft-specific fallbacks, use ClipboardHelperFallback interface.
 */
public class ClipboardHelper {

    private static ClipboardHelperFallback fallback;

    /**
     * Sets the fallback implementation for clipboard operations.
     * This should be called by the Minecraft-specific module.
     *
     * @param fallbackImpl The fallback implementation
     */
    public static void setFallback(ClipboardHelperFallback fallbackImpl) {
        fallback = fallbackImpl;
    }

    /**
     * Copies the given text to the system clipboard.
     * First tries AWT Toolkit, then falls back to the configured fallback if AWT fails.
     *
     * @param text The text to copy to the clipboard
     * @return true if successful, false if both methods fail
     */
    public static boolean copyToClipboard(String text) {
        // Try AWT Toolkit first (standard approach)
        try {
            System.out.println("=== CLIPBOARD DEBUG START ===");
            System.out.println("Text to copy length: " + text.length());
            System.out.println("Text to copy (first 200 chars): " + text.substring(0, Math.min(200, text.length())));
            System.out.println("Text to copy (last 200 chars): " + text.substring(Math.max(0, text.length() - 200)));

            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            System.out.println("Got AWT clipboard: " + clipboard);

            StringSelection selection = new StringSelection(text);
            System.out.println("Created StringSelection");

            clipboard.setContents(selection, selection);
            System.out.println("Set AWT clipboard contents successfully");

            // Verify the copy worked
            String copiedText = (String) clipboard.getData(DataFlavor.stringFlavor);
            System.out.println("Verified AWT clipboard content length: " + copiedText.length());
            System.out.println("Verified AWT clipboard content (first 100 chars): " + copiedText.substring(0, Math.min(100, copiedText.length())));
            System.out.println("=== CLIPBOARD DEBUG END ===");

            return true;

        } catch (Exception e) {
            System.err.println("=== AWT CLIPBOARD FAILED, TRYING FALLBACK ===");
            System.err.println("Exception type: " + e.getClass().getName());
            System.err.println("Exception message: " + e.getMessage());
            
            // Fallback to configured fallback implementation
            if (fallback != null) {
                try {
                    boolean success = fallback.copyToClipboard(text);
                    if (success) {
                        System.out.println("=== FALLBACK SUCCESS ===");
                        return true;
                    } else {
                        System.err.println("Fallback clipboard copy failed");
                        return false;
                    }
                } catch (Exception fallbackException) {
                    System.err.println("=== FALLBACK ALSO FAILED ===");
                    System.err.println("Fallback Exception type: " + fallbackException.getClass().getName());
                    System.err.println("Fallback Exception message: " + fallbackException.getMessage());
                    System.err.println("Fallback Exception stack trace:");
                    fallbackException.printStackTrace();
                    System.err.println("=== BOTH CLIPBOARD METHODS FAILED ===");
                    return false;
                }
            } else {
                System.err.println("No fallback implementation configured");
                return false;
            }
        }
    }

    /**
     * Reads text from the system clipboard.
     * First tries AWT Toolkit, then falls back to the configured fallback if AWT fails.
     *
     * @return The text from the clipboard, or empty string if clipboard is empty or unavailable
     */
    public static String readFromClipboard() {
        // Try AWT Toolkit first
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            if (clipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                return (String) clipboard.getData(DataFlavor.stringFlavor);
            }
        } catch (Exception e) {
            System.err.println("AWT clipboard read failed, trying fallback: " + e.getMessage());
            
            // Fallback to configured fallback implementation
            if (fallback != null) {
                try {
                    return fallback.readFromClipboard();
                } catch (Exception fallbackException) {
                    System.err.println("Fallback clipboard read also failed: " + fallbackException.getMessage());
                }
            }
        }
        return "";
    }

    /**
     * Clears the system clipboard.
     * First tries AWT Toolkit, then falls back to the configured fallback if AWT fails.
     */
    public static void clearClipboard() {
        // Try AWT Toolkit first
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new StringSelection(""), null);
        } catch (Exception e) {
            System.err.println("AWT clipboard clear failed, trying fallback: " + e.getMessage());
            
            // Fallback to configured fallback implementation
            if (fallback != null) {
                try {
                    fallback.clearClipboard();
                } catch (Exception fallbackException) {
                    System.err.println("Fallback clipboard clear also failed: " + fallbackException.getMessage());
                }
            }
        }
    }
}
