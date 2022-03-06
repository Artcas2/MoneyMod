package fr.artcas2.minecraft.moneymod.procedures;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.chat.TextComponent;

import fr.artcas2.minecraft.moneymod.network.MoneymodModVariables;

public class ObtenirMonnaieProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _player && !_player.level.isClientSide())
			_player.displayClientMessage(new TextComponent((new TranslatableComponent("money").getString() + ""
					+ (" : " + (entity.getCapability(MoneymodModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new MoneymodModVariables.PlayerVariables())).money))),
					(false));
	}
}
