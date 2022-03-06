
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.artcas2.minecraft.moneymod.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.BlockItem;

import java.util.List;
import java.util.ArrayList;

import fr.artcas2.minecraft.moneymod.item.SuperArtcasCoinItem;
import fr.artcas2.minecraft.moneymod.item.BankCardItem;
import fr.artcas2.minecraft.moneymod.item.ArtcasCoinItem;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoneymodModItems {
	private static final List<Item> REGISTRY = new ArrayList<>();
	public static final Item ARTCAS_COIN = register(new ArtcasCoinItem());
	public static final Item SUPER_ARTCAS_COIN = register(new SuperArtcasCoinItem());
	public static final Item ARTCAS_COIN_BLOCK = register(MoneymodModBlocks.ARTCAS_COIN_BLOCK, CreativeModeTab.TAB_MISC);
	public static final Item CASH_DISPENSER = register(MoneymodModBlocks.CASH_DISPENSER, CreativeModeTab.TAB_DECORATIONS);
	public static final Item BANK_CARD = register(new BankCardItem());

	private static Item register(Item item) {
		REGISTRY.add(item);
		return item;
	}

	private static Item register(Block block, CreativeModeTab tab) {
		return register(new BlockItem(block, new Item.Properties().tab(tab)).setRegistryName(block.getRegistryName()));
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new Item[0]));
	}
}
