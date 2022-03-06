
package fr.artcas2.minecraft.moneymod.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;

public class BankCardItem extends Item {
	public BankCardItem() {
		super(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).fireResistant().rarity(Rarity.COMMON));
		setRegistryName("bank_card");
	}

	@Override
	public int getUseDuration(ItemStack itemstack) {
		return 0;
	}
}
