/**
 * SIGNUM
 * MIT License
 * Lana
 * 2023
 * */
package za.lana.signum.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import za.lana.signum.entity.projectile.IceBoltEntity;
import za.lana.signum.entity.projectile.ShockBoltEntity;
import za.lana.signum.sound.ModSounds;

import java.util.List;

public class LightningStaff
        extends Item {
    private final ToolMaterial material;
    private float attackDamage = 2.0f;
    private static final int STAFFCOOLDOWN = 40;


    public LightningStaff(ToolMaterial material, Settings settings) {
        super(settings.maxDamageIfAbsent(material.getDurability()));
        this.material = material;
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemstack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.TIBERIUM_HIT, SoundCategory.NEUTRAL,1.5F, 1F);
        user.getItemCooldownManager().set(this, STAFFCOOLDOWN);
        if (!world.isClient()) {
            ShockBoltEntity shockBoltEntity = new ShockBoltEntity(world, user);
            shockBoltEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0, 3, 1);
            world.spawnEntity(shockBoltEntity);
        }
        user.incrementStat(Stats.USED.getOrCreateStat(this));
        // BREAK TOOL
        if (!user.getAbilities().creativeMode) {
            itemstack.damage(1, user, p -> p.sendToolBreakStatus(hand));
        }
        return TypedActionResult.success(itemstack, world.isClient());
    }
    // REPAIR ITEM WITH MOD MATERIAL
    public ToolMaterial getMaterial() {
        return this.material;
    }
    @Override
    public int getEnchantability() {
        return this.material.getEnchantability();
    }
    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return this.material.getRepairIngredient().test(ingredient) || super.canRepair(stack, ingredient);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("item.signum.lightning_staff.info"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}

