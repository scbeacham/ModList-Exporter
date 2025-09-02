package com.yourname.modlistexporter.utils;

import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

/**
 * LWJGL fallback implementation for clipboard operations.
 * This provides Minecraft-specific clipboard functionality using LWJGL GLFW.
 */
public class LWJGLClipboardFallback implements ClipboardHelperFallback {
    
    @Override
    public boolean copyToClipboard(String text) {
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
            System.err.println("=== LWJGL FALLBACK FAILED ===");
            System.err.println("LWJGL Exception type: " + lwjglException.getClass().getName());
            System.err.println("LWJGL Exception message: " + lwjglException.getMessage());
            System.err.println("LWJGL Exception stack trace:");
            lwjglException.printStackTrace();
            return false;
        }
    }
    
    @Override
    public String readFromClipboard() {
        try {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null && client.getWindow() != null) {
                long windowHandle = client.getWindow().getHandle();
                String text = GLFW.glfwGetClipboardString(windowHandle);
                return text != null ? text : "";
            }
        } catch (Exception lwjglException) {
            System.err.println("LWJGL clipboard read failed: " + lwjglException.getMessage());
        }
        return "";
    }
    
    @Override
    public void clearClipboard() {
        try {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client != null && client.getWindow() != null) {
                long windowHandle = client.getWindow().getHandle();
                GLFW.glfwSetClipboardString(windowHandle, "");
            }
        } catch (Exception lwjglException) {
            System.err.println("LWJGL clipboard clear failed: " + lwjglException.getMessage());
        }
    }
}
