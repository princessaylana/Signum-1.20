/**
 * SIGNUM
 * MIT License
 * Lana
 * */
package za.lana.signum.screen.gui;
// this would be equivalent to a normal screen handler

import io.github.cottonmc.cotton.gui.SyncedGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WItemSlot;
import io.github.cottonmc.cotton.gui.widget.WSprite;
import io.github.cottonmc.cotton.gui.widget.data.Insets;
import io.github.cottonmc.cotton.gui.widget.icon.ItemIcon;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import za.lana.signum.item.ModItems;
import za.lana.signum.screen.ModScreenHandlers;


public class ExampleDescription extends SyncedGuiDescription {
    private static final int INVENTORY_SIZE = 1;

    public ExampleDescription(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(GuiScreens.EXAMPLE_GUI, syncId, playerInventory, getBlockInventory(context, INVENTORY_SIZE), getBlockPropertyDelegate(context));
        this.world = playerInventory.player.getWorld();

        WGridPanel root = new WGridPanel();
        setRootPanel(root);
        root.setSize(260, 200);
        root.setInsets(Insets.ROOT_PANEL);

        WSprite icon = new WSprite(new Identifier("signum:textures/item/element_zero_crystal.png"));
        root.add(icon, 0, 2, 1, 1);

        WButton button = new WButton(Text.translatable("example_gui.signum.examplebutton"));
        button.setOnClick(() -> {
            // This code runs on the client when you click the button.
            System.out.println("Button clicked!");
            // Paints button's icon:
            button.setIcon(new ItemIcon(new ItemStack(ModItems.ELEMENT_ZERO_DUST)));
        });
        // Widget, X, Y, Width, Height
        root.add(button, 9, 3, 6, 2);

        WItemSlot itemSlot = WItemSlot.of(blockInventory, 0);
        root.add(itemSlot, 4, 1);

        root.add(this.createPlayerInventoryPanel(), 0, 3);
        root.validate(this);
    }
}
