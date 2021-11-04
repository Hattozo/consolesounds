package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsClient;
import com.hattolo.consolesounds.ConsoleSoundsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.fabric.mixin.networking.accessor.MinecraftClientAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Screen.class)
public class ExitScreenMixin {
    @Inject(at = @At("RETURN"), method = "keyPressed")
    private void keyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {
        var screen = (Screen) (Object) this;

        //System.out.println("Key pressed");
        if (keyCode == GLFW.GLFW_KEY_ESCAPE && screen.shouldCloseOnEsc()) {
            //System.out.println("Play sound");
            if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().playSoundOnMenuExit) MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ConsoleSoundsClient.UI_BACK_EVENT, 1.0F));
        }
    }
}
