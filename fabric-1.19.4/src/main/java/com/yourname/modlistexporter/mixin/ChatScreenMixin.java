package com.yourname.modlistexporter.mixin;

import com.yourname.modlistexporter.commands.ExportCommand;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to intercept chat messages and handle the /modlist command.
 */
@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    
    @Shadow
    private TextFieldWidget chatField;
    
    @Inject(method = "keyPressed(III)Z", at = @At("HEAD"), cancellable = true)
    private void onKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        // Check if Enter was pressed (keyCode 257 is Enter)
        if (keyCode == 257) {
            String message = chatField.getText().trim();
            
            // Check if the message is exactly "/modlist"
            if ("/modlist".equals(message)) {
                // Cancel the original key press
                cir.setReturnValue(true);
                
                // Clear the chat field
                chatField.setText("");
                
                // Handle the modlist command
                ExportCommand.handleModlistCommand();
            }
        }
    }
}
