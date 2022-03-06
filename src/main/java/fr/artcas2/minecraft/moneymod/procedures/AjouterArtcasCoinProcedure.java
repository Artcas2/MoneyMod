package fr.artcas2.minecraft.moneymod.procedures;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.TextComponent;

import fr.artcas2.minecraft.moneymod.network.MoneymodModVariables;

public class AjouterArtcasCoinProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		{
			double _setval = (entity.getCapability(MoneymodModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new MoneymodModVariables.PlayerVariables())).money + 1;
			entity.getCapability(MoneymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.money = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent(("+1 " + new TranslatableComponent("money").getString())), (false));
		(itemstack).shrink(1);
	}
}
