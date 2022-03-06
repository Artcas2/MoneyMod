package fr.artcas2.minecraft.moneymod.procedures;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.CommandSource;
import net.minecraft.client.gui.components.EditBox;

import java.util.HashMap;

import fr.artcas2.minecraft.moneymod.network.MoneymodModVariables;

public class RetirerArgentProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		if (entity instanceof Player _player)
			_player.closeContainer();
		if (new Object() {
			double convert(String s) {
				try {
					return Double.parseDouble(s.trim());
				} catch (Exception e) {
				}
				return 0;
			}
		}.convert(guistate.containsKey("text:monnaie") ? ((EditBox) guistate.get("text:monnaie")).getValue() : "") > (entity
				.getCapability(MoneymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new MoneymodModVariables.PlayerVariables())).money) {
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent("Vous avez entr\u00E9 un nombre trop grand par raport a votre argent."), (false));
		} else {
			{
				double _setval = (entity.getCapability(MoneymodModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new MoneymodModVariables.PlayerVariables())).money - new Object() {
							double convert(String s) {
								try {
									return Double.parseDouble(s.trim());
								} catch (Exception e) {
								}
								return 0;
							}
						}.convert(guistate.containsKey("text:monnaie") ? ((EditBox) guistate.get("text:monnaie")).getValue() : "");
				entity.getCapability(MoneymodModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.money = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if (entity instanceof Player _player && !_player.level.isClientSide())
				_player.displayClientMessage(new TextComponent(("Vous avez bien retir\u00E9 "
						+ ((guistate.containsKey("text:monnaie") ? ((EditBox) guistate.get("text:monnaie")).getValue() : "") + " ArtcasCoin."))),
						(false));
			if (world instanceof ServerLevel _level)
				_level.getServer().getCommands().performCommand(
						new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", new TextComponent(""),
								_level.getServer(), null).withSuppressedOutput(),
						("give @p moneymod:artcas_coin "
								+ (guistate.containsKey("text:monnaie") ? ((EditBox) guistate.get("text:monnaie")).getValue() : "")));
		}
	}
}
