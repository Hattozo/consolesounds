package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsClient;
import com.hattolo.consolesounds.ConsoleSoundsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
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
        //System.out.println("Lel");
        if (cir.getReturnValue()) {
            if (keyCode == GLFW.GLFW_KEY_BACKSPACE || keyCode == GLFW.GLFW_KEY_DELETE || keyCode == GLFW.GLFW_KEY_HOME || keyCode == GLFW.GLFW_KEY_END || keyCode == GLFW.GLFW_KEY_LEFT || keyCode == GLFW.GLFW_KEY_RIGHT) return;
            if (modifiers == GLFW.GLFW_MOD_CONTROL) return;

            //System.out.println("Escaped!");
            if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().playSoundOnMenuExit) MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ConsoleSoundsClient.UI_BACK_EVENT, 1.0F));
        }
        /*
        System.out.println("Lel");
        if (keyCode == GLFW.GLFW_KEY_ESCAPE && Screen.shouldCloseOnEsc()) {
            System.out.println("Escaped!");
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ConsoleModClient.UI_BACK_EVENT, 1.0F));
        }
         */
    }
}
