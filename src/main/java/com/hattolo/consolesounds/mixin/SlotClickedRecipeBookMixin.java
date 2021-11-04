package com.hattolo.consolesounds.mixin;

import com.hattolo.consolesounds.ConsoleSoundsClient;
import com.hattolo.consolesounds.ConsoleSoundsConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.CraftingScreen;
import net.minecraft.client.gui.screen.recipebook.RecipeBookGhostSlots;
import net.minecraft.client.gui.screen.recipebook.RecipeBookWidget;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.screen.AbstractRecipeScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundEvents;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeBookWidget.class)
public class SlotClickedRecipeBookMixin {
    @Shadow protected AbstractRecipeScreenHandler<?> craftingScreenHandler;
    @Shadow @Final protected RecipeBookGhostSlots ghostSlots;

    @Inject(at = @At("HEAD"), method = "slotClicked")
    private void slotClicked(@Nullable Slot slot, CallbackInfo ci) {
        if (slot != null && slot.id < craftingScreenHandler.getCraftingSlotCount()) {
            if (ghostSlots.getSlotCount() > 0) {
                if (AutoConfig.getConfigHolder(ConsoleSoundsConfig.class).getConfig().enableCraftingSounds) MinecraftClient.getInstance().getSoundManager().play(PositionedSoundInstance.master(ConsoleSoundsClient.UI_FAIL_EVENT, 1.0F));
            }
        }
    }
}
