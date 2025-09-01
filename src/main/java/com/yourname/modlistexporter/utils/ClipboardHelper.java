package com.yourname.modlistexporter.utils;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

/**
 * Utility class for clipboard operations.
 * Handles copying text to and from the system clipboard using a hybrid approach:
 * 1. Try AWT Toolkit first (standard approach)
 * 2. Fallback to LWJGL GLFW if AWT fails (for headless environments)
 */
public class ClipboardHelper {

    /**
     * Copies the given text to the system clipboard using a hybrid approach.
     * First tries AWT Toolkit, then falls back to LWJGL GLFW if AWT fails.
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
            System.err.println("=== AWT CLIPBOARD FAILED, TRYING LWJGL FALLBACK ===");
            System.err.println("Exception type: " + e.getClass().getName());
            System.err.println("Exception message: " + e.getMessage());
            
            // Fallback to LWJGL GLFW clipboard
            try {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client != null && client.getWindow() != null) {
                    long windowHandle = client.getWindow().getHandle();
                    System.out.println("Using LWJGL GLFW clipboard with window handle: " + windowHandle);
                    
                    GLFW.glfwSetClipboardString(windowHandle, text);
                    System.out.println("Successfully set LWJGL GLFW clipboard contents");
                    
                    // Verify the copy worked
                    String copiedText = GLFW.glfwGetClipboardString(windowHandle);
                    if (copiedText != null && copiedText.equals(text)) {
                        System.out.println("Verified LWJGL GLFW clipboard content matches");
                        System.out.println("=== LWJGL FALLBACK SUCCESS ===");
                        return true;
                    } else {
                        System.err.println("LWJGL GLFW clipboard verification failed");
                        return false;
                    }
                } else {
                    System.err.println("MinecraftClient or Window not available for LWJGL fallback");
                    return false;
                }
            } catch (Exception lwjglException) {
                System.err.println("=== LWJGL FALLBACK ALSO FAILED ===");
                System.err.println("LWJGL Exception type: " + lwjglException.getClass().getName());
                System.err.println("LWJGL Exception message: " + lwjglException.getMessage());
                System.err.println("LWJGL Exception stack trace:");
                lwjglException.printStackTrace();
                System.err.println("=== BOTH CLIPBOARD METHODS FAILED ===");
                return false;
            }
        }
    }

    /**
     * Reads text from the system clipboard.
     * First tries AWT Toolkit, then falls back to LWJGL GLFW if AWT fails.
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
            System.err.println("AWT clipboard read failed, trying LWJGL fallback: " + e.getMessage());
            
            // Fallback to LWJGL GLFW clipboard
            try {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client != null && client.getWindow() != null) {
                    long windowHandle = client.getWindow().getHandle();
                    String text = GLFW.glfwGetClipboardString(windowHandle);
                    return text != null ? text : "";
                }
            } catch (Exception lwjglException) {
                System.err.println("LWJGL clipboard read also failed: " + lwjglException.getMessage());
            }
        }
        return "";
    }

    /**
     * Clears the system clipboard.
     * First tries AWT Toolkit, then falls back to LWJGL GLFW if AWT fails.
     */
    public static void clearClipboard() {
        // Try AWT Toolkit first
        try {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(new StringSelection(""), null);
        } catch (Exception e) {
            System.err.println("AWT clipboard clear failed, trying LWJGL fallback: " + e.getMessage());
            
            // Fallback to LWJGL GLFW clipboard
            try {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client != null && client.getWindow() != null) {
                    long windowHandle = client.getWindow().getHandle();
                    GLFW.glfwSetClipboardString(windowHandle, "");
                }
            } catch (Exception lwjglException) {
                System.err.println("LWJGL clipboard clear also failed: " + lwjglException.getMessage());
            }
        }
    }
}
