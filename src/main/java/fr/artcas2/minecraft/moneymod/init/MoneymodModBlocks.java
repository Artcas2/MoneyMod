
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package fr.artcas2.minecraft.moneymod.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegistryEvent;

import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.ArrayList;

import fr.artcas2.minecraft.moneymod.block.CashDispenserBlock;
import fr.artcas2.minecraft.moneymod.block.ArtcasCoinBlockBlock;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class MoneymodModBlocks {
	private static final List<Block> REGISTRY = new ArrayList<>();
	public static final Block ARTCAS_COIN_BLOCK = register(new ArtcasCoinBlockBlock());
	public static final Block CASH_DISPENSER = register(new CashDispenserBlock());

	private static Block register(Block block) {
		REGISTRY.add(block);
		return block;
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(REGISTRY.toArray(new Block[0]));
	}
}
