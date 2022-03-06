
package fr.artcas2.minecraft.moneymod.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;

import fr.artcas2.minecraft.moneymod.procedures.AjouterSuperArtcasCoinProcedure;

public class SuperArtcasCoinItem extends Item {
	public SuperArtcasCoinItem() {
		super(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(64).fireResistant().rarity(Rarity.RARE));
		setRegistryName("super_artcas_coin");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}

	@Override
	public boolean isFoil(ItemStack itemstack) {
		return true;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player entity, InteractionHand hand) {
		InteractionResultHolder<ItemStack> ar = super.use(world, entity, hand);
		ItemStack itemstack = ar.getObject();
		double x = entity.getX();
		double y = entity.getY();
		double z = entity.getZ();

		AjouterSuperArtcasCoinProcedure.execute(entity, itemstack);
		return ar;
	}
}
