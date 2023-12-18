/**
 * SIGNUM
 * MIT License
 * Lana
 * 2023
 * */
package za.lana.signum.entity.projectile;

import net.minecraft.block.AbstractBlock;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.passive.FrogEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import za.lana.signum.effect.ModEffects;
import za.lana.signum.entity.ModEntities;
import za.lana.signum.entity.hostile.WizardEntity;
import za.lana.signum.particle.ModParticles;
import za.lana.signum.sound.ModSounds;

public class SpellBoltEntity
        extends ProjectileEntity {
    protected final int age1 = 200;
    protected final float dam = 1.5f * 2;

    private final SpellBoltEntity entity = this;


    private static final TrackedData<Boolean> HIT = DataTracker.registerData(SpellBoltEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public SpellBoltEntity(EntityType<? extends SpellBoltEntity> entityType, World world) {
        super(entityType, world);
    }
    //
    public SpellBoltEntity(World world, WizardEntity owner) {
        this(ModEntities.SPELLBOLT_ENTITY, world);
        this.setOwner(owner);

        BlockPos blockpos = owner.getBlockPos();
        double d0 = (double)blockpos.getX() + 0.5D;
        double d1 = (double)blockpos.getY() + 1.75D;
        double d2 = (double)blockpos.getZ() + 0.5D;
        this.refreshPositionAndAngles(d0, d1, d2, this.getYaw(), this.getPitch());
        if (this.age >= age1) {
            this.discard();
        }

        this.setPosition(
                owner.getX() - (double)(owner.getWidth() + 1.0f) * 0.5 * (double)MathHelper.sin(owner.bodyYaw * ((float)Math.PI / 180)),
                owner.getEyeY() - (double)0.1f,
                owner.getZ() + (double)(owner.getWidth() + 1.0f) * 0.5 * (double)MathHelper.cos(owner.bodyYaw * ((float)Math.PI / 180)));
    }

    @Override
    protected void initDataTracker() {
    }
    //
    @Override
    public void tick() {
        super.tick();
        Vec3d vec3d = this.getVelocity();
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        this.onCollision(hitResult);
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        this.updateRotation();
        float g = 0.99f;
        float h = 0.06f;

        if (this.getWorld().getStatesInBox(this.getBoundingBox()).noneMatch(AbstractBlock.AbstractBlockState::isAir)) {
            this.discard();
            return;
        }
        if (this.isInsideWaterOrBubbleColumn()) {
            this.discard();
            return;
        }
        this.setVelocity(vec3d.multiply(0.99f));
        if (!this.hasNoGravity()) {
            // y default is 0.06
            this.setVelocity(this.getVelocity().add(0.0, -0.016f, 0.0));
            //this.setVelocity(this.getVelocity().add(0.0, -0.03f, 0.0));
        }
        if (this.getWorld().isClient) {
            for (int i = 0; i < 2; ++i) {
                this.getWorld().addParticle(ModParticles.ORANGE_SHROOM_PARTICLE, this.getParticleX(0.5), this.getRandomBodyY() - 0.50, this.getParticleZ(0.5), (this.random.nextDouble() - 0.5) * 2.0, -this.random.nextDouble(), (this.random.nextDouble() - 0.5) * 2.0);
            }
        }
        this.setPosition(d, e, f);
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult){
        super.onEntityHit(entityHitResult);
        Entity target = entityHitResult.getEntity();
        World level = getEntityWorld();

        int i = target instanceof EndermanEntity ? 6 : 0;
        target.damage(this.getDamageSources().thrown(this, this.getOwner()), i);

        if (target instanceof ClientPlayerEntity) {
            //
            ((LivingEntity) target).addStatusEffect((new StatusEffectInstance(ModEffects.TRANSMUTE_EFFECT, 60 * 2 , 1 / 4)));
            target.damage(getWorld().getDamageSources().magic(), dam);
            this.entity.playSpawnEffects();
            this.discard();
        }
        if (target instanceof EnderDragonEntity || target instanceof WitherEntity || target instanceof GhastEntity || target instanceof FrogEntity ){
            ((LivingEntity) target).addStatusEffect((new StatusEffectInstance(ModEffects.TRANSMUTE_EFFECT, 60 * 2 , 1 / 4)));
            target.damage(getWorld().getDamageSources().magic(), dam);
            this.entity.playSpawnEffects();
            this.discard();
        }else{
            ((LivingEntity) target).addStatusEffect((new StatusEffectInstance(ModEffects.TRANSMUTE_EFFECT, 60 * 2 , 1 / 4)));

            if (!this.getWorld().isClient) {
                // spawn a frog #1
                EntityType.FROG.spawn(((ServerWorld) target.getWorld()), target.getBlockPos(), SpawnReason.TRIGGERED);
                // spawn a frog #2
                EntityType.FROG.spawn(((ServerWorld) target.getWorld()), target.getBlockPos(), SpawnReason.TRIGGERED);
                // spawn a frog #3
                EntityType.FROG.spawn(((ServerWorld) target.getWorld()), target.getBlockPos(), SpawnReason.TRIGGERED);
                //
                target.damage(getWorld().getDamageSources().magic(), dam);
                this.entity.playSpawnEffects();
                target.discard();
                this.discard();
            }
        }
        if (this.getWorld().isClient){
            level.addParticle(ModParticles.TRANSMUTE_PARTICLE, getX(), getY(), getZ(), 0.0, 2.0, 0.0);
            this.playSound(ModSounds.TIBERIUM_HIT, 2F, 2F);
        }
    }

    // TODO NEED TO CHANGE THIS ?
    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        if (!this.getWorld().isClient) {
            this.discard();

        }
        for(int x = 0; x < 18; ++x) {
            for(int y = 0; y < 18; ++y) {
                this.getWorld().addParticle(ModParticles.TRANSMUTE_PARTICLE, this.getX(), this.getY() + 0.5, this.getZ(),
                        Math.cos(x*20) * 0.15d, Math.cos(y*20) * 0.15d, Math.sin(x*20) * 0.15d * 0.5f);
            }
        }
    }

    @Override
    public boolean hasNoGravity() {
        return true;
    }

    @Override
    public void onSpawnPacket(EntitySpawnS2CPacket packet) {
        super.onSpawnPacket(packet);
        double d = packet.getVelocityX();
        double e = packet.getVelocityY();
        double f = packet.getVelocityZ();
        for (int i = 0; i < 7; ++i) {
            double g = 0.4 + 0.1 * (double)i;
            this.getWorld().addParticle(ModParticles.WHITE_SHROOM_PARTICLE, this.getX(), this.getY(), this.getZ(), d * g, e, f * g);
        }
        this.setVelocity(d, e, f);
    }
    public void playSpawnEffects() {
        if (this.getWorld().isClient) {
            for(int i = 0; i < 20; ++i) {
                double d = this.random.nextGaussian() * 0.02;
                double e = this.random.nextGaussian() * 0.02;
                double f = this.random.nextGaussian() * 0.02;
                double g = 10.0;
                this.getWorld().addParticle(ParticleTypes.POOF, this.offsetX(1.0) - d * 10.0, this.getRandomBodyY() - e * 10.0, this.getParticleZ(1.0) - f * 10.0, d, e, f);
            }
        } else {
            this.getWorld().sendEntityStatus(this, (byte)20);
        }

    }
}

