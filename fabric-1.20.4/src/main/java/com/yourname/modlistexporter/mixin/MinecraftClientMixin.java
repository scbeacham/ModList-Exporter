package com.yourname.modlistexporter.mixin;

import com.yourname.modlistexporter.keybind.ExportKeybind;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Mixin to handle keybinding presses for the export functionality.
 * Injects into MinecraftClient to check for keybinding presses every tick.
 */
@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Inject(method = "tick", at = @At("TAIL"))
    private void onTick(CallbackInfo ci) {
        try {
            // Check for keybinding presses every tick
            ExportKeybind.handleKeyPress();
        } catch (Exception e) {
            // Log error but don't crash the game
            System.err.println("Error in ExportKeybind.handleKeyPress(): " + e.getMessage());
        }
    }
}
