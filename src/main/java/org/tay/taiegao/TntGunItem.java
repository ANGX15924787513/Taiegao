package org.tay.taiegao;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class TntGunItem extends Item {
    private static final int COOLDOWN_TICKS = 2;
    HungerManager hungerManager;
    public TntGunItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.setCurrentHand(hand);
        hungerManager = player.getHungerManager();
        return new TypedActionResult<>(ActionResult.CONSUME, player.getStackInHand(hand));
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks){
        if(world.isClient || !(user instanceof PlayerEntity player) || remainingUseTicks % COOLDOWN_TICKS != 0) return;
        var tnt = new TntEntity(world,player.getX(),player.getY(),player.getZ(),player);
        tnt.setGlowing(true);
        tnt.addVelocity(player.getRotationVec(1.0F).multiply(2F));
        tnt.setFuse(20);
        if(hungerManager.getFoodLevel() != 0) hungerManager.setFoodLevel(hungerManager.getFoodLevel() - 1);
        world.spawnEntity(tnt);
    }
}
