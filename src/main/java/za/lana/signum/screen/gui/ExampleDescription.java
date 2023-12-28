package za.lana.signum.screen.gui;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.networking.NetworkSide;
import io.github.cottonmc.cotton.gui.networking.ScreenNetworking;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import io.github.cottonmc.cotton.gui.widget.WToggleButton;
import io.github.cottonmc.cotton.gui.widget.data.Texture;
import io.github.cottonmc.cotton.gui.widget.icon.TextureIcon;
import za.lana.signum.block.entity.ExampleBlockEntity;

public class ExampleDescription extends SyncedGuiDescription {
	private static final Identifier TEST_MESSAGE = new Identifier("libgui", "test");
	private static final Identifier UNREGISTERED_ON_SERVER = new Identifier("libgui", "unregistered_on_server");

	public ExampleDescription(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
		super(type, syncId, playerInventory, getBlockInventory(context, ExampleBlockEntity.INVENTORY_SIZE), null);

		WGridPanel root = (WGridPanel)this.getRootPanel();

		WItemSlot slot = WItemSlot.of(blockInventory, 0, 4, 1);
		root.add(slot, 0, 1);

		WButton buttonA = new WButton(Text.literal("Send Message"));

		buttonA.setOnClick(() -> {
			ScreenNetworking.of(this, NetworkSide.CLIENT).send(TEST_MESSAGE, buf -> {});
			ScreenNetworking.of(this, NetworkSide.CLIENT).send(UNREGISTERED_ON_SERVER, buf -> {});
		});

		root.add(buttonA, 0, 3, 4, 1);

		WButton buttonB = new WButton(Text.literal("Show Warnings"));
		buttonB.setOnClick(() -> slot.setIcon(new TextureIcon(new Identifier("libgui-test", "saddle.png"))));

		root.add(buttonB, 5, 3, 4, 1);
		TextureIcon testIcon = new TextureIcon(new Texture(new Identifier("libgui-test", "icon.png")));
		root.add(new WButton(testIcon, Text.literal("Button C")), 0, 5, 4, 1);
		root.add(new WButton(Text.literal("Button D")), 5, 5, 4, 1);
		root.add(new WTextField(Text.literal("Type something...")).setMaxLength(64), 0, 7, 5, 1);

		root.add(new WLabel(Text.literal("Large Glass-only output:")), 0, 9);
		WItemSlot glassOutputSlot = WItemSlot.outputOf(blockInventory, 0).setOutputFilter(stack -> stack.isOf(Items.GLASS));
		glassOutputSlot.setIcon(new TextureIcon(new Identifier("minecraft:textures/block/glass.png")));
		root.add(glassOutputSlot, 4, 9);
		WToggleButton glassIconToggle = new WToggleButton(Text.literal("Show glass icon only when empty?"));
		glassIconToggle.setOnToggle(glassOutputSlot::setIconOnlyPaintedForEmptySlots);
		root.add(glassIconToggle, 0, 10);

		root.add(WItemSlot.of(blockInventory, 7).setIcon(new TextureIcon(new Identifier("libgui-test", "saddle.png"))).setInputFilter(stack -> stack.isOf(Items.SADDLE)), 7, 10);

		root.add(createPlayerInventoryPanel(), 0, 11);
		System.out.println(root.toString());

		this.getRootPanel().validate(this);

		ScreenNetworking.of(this, NetworkSide.SERVER).receive(TEST_MESSAGE, buf -> {
			System.out.println("Received on the server!");
		});

		try {
			slot.onHidden();
			slot.onShown();
		} catch (Throwable t) {
			throw new AssertionError("ValidatedSlot.setVisible crashed", t);
		}
	}
}
