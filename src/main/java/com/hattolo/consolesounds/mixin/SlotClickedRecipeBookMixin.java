package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.CraftingScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// unused mixin

@Mixin(RecipeBookWidget.class)
public class SlotClickedRecipeBookMixin {
    @Inject(at = @At("HEAD"), method = "slotClicked")
    private void init(@Nullable Slot slot, CallbackInfo ci) {
        if (slot != null && slot.id < 4) {
            MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ConsoleSoundsClient.UI_FAIL_EVENT, 1.0F));
        }
    }
}
