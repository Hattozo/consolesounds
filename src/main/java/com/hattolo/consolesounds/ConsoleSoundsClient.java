package com.hattolo.consolesounds;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
//import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
//import net.fabricmc.fabric.api.resource.ResourcePackActivationType;
//import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConsoleSoundsClient implements ClientModInitializer {
	public static final Logger log = LogManager.getLogger("Console Sounds");

	public static final Identifier UI_SELECT_ID = new Identifier("consolemod:ui_select");
	public static SoundEvent UI_SELECT_EVENT = new SoundEvent(UI_SELECT_ID);

	public static final Identifier UI_BACK_ID = new Identifier("consolemod:ui_back");
	public static SoundEvent UI_BACK_EVENT = new SoundEvent(UI_BACK_ID);

	public static final Identifier UI_SCROLL_ID = new Identifier("consolemod:ui_scroll");
	public static SoundEvent UI_SCROLL_EVENT = new SoundEvent(UI_SCROLL_ID);

	public static final Identifier UI_FAIL_ID = new Identifier("consolemod:ui_fail");
	public static SoundEvent UI_FAIL_EVENT = new SoundEvent(UI_FAIL_ID);

	@Override
	public void onInitializeClient() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// register sounds
		Registry.register(Registry.SOUND_EVENT, UI_SELECT_ID, UI_SELECT_EVENT);
		Registry.register(Registry.SOUND_EVENT, UI_BACK_ID, UI_BACK_EVENT);
		Registry.register(Registry.SOUND_EVENT, UI_SCROLL_ID, UI_SCROLL_EVENT);
		Registry.register(Registry.SOUND_EVENT, UI_FAIL_ID, UI_FAIL_EVENT);
		log.info("Registered all sounds!");

		// register config
		AutoConfig.register(ConsoleSoundsConfig.class, GsonConfigSerializer::new);
		log.info("Registered config!");

		// register and automatically load resource pack
		//ResourceManagerHelper.registerBuiltinResourcePack(new Identifier("consolemod", "console_pack"), FabricLoader.getInstance().getModContainer("consolemod").get(), ResourcePackActivationType.ALWAYS_ENABLED);

		log.info("Initialized the mod!");
	}
}
