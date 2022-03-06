
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.artcas2.minecraft.moneymod.init;

import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.gui.screens.MenuScreens;

import fr.artcas2.minecraft.moneymod.client.gui.DistributeurScreen;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MoneymodModScreens {
	@SubscribeEvent
	public static void clientLoad(FMLClientSetupEvent event) {
		event.enqueueWork(() -> {
			MenuScreens.register(MoneymodModMenus.DISTRIBUTEUR, DistributeurScreen::new);
		});
	}
}
