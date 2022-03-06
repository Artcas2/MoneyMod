
package fr.artcas2.minecraft.moneymod.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.Minecraft;

import fr.artcas2.minecraft.moneymod.world.inventory.DistributeurMenu;
import fr.artcas2.minecraft.moneymod.network.DistributeurButtonMessage;
import fr.artcas2.minecraft.moneymod.MoneymodMod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class DistributeurScreen extends AbstractContainerScreen<DistributeurMenu> {
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox monnaie;

	public DistributeurScreen(DistributeurMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 170;
		this.imageHeight = 120;
	}

	private static final ResourceLocation texture = new ResourceLocation("moneymod:textures/distributeur.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
		monnaie.render(ms, mouseX, mouseY, partialTicks);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (monnaie.isFocused())
			return monnaie.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		monnaie.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, "Distribueur", 50, 9, -12829636);
		this.font.draw(poseStack, "Argent \u00E0 retirer : ", 33, 34, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardHandler.setSendRepeatsToGui(false);
	}

	@Override
	public void init() {
		super.init();
		this.minecraft.keyboardHandler.setSendRepeatsToGui(true);
		monnaie = new EditBox(this.font, this.leftPos + 23, this.topPos + 49, 120, 20, new TextComponent(""));
		DistributeurMenu.guistate.put("text:monnaie", monnaie);
		monnaie.setMaxLength(32767);
		this.addWidget(this.monnaie);
		this.addRenderableWidget(new Button(this.leftPos + 50, this.topPos + 86, 61, 20, new TextComponent("Retirer"), e -> {
			if (true) {
				MoneymodMod.PACKET_HANDLER.sendToServer(new DistributeurButtonMessage(0, x, y, z));
				DistributeurButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
