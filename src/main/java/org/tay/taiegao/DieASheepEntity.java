package org.tay.taiegao;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class DieASheepEntity extends SheepEntity {
    public DieASheepEntity(EntityType<? extends SheepEntity> entityType, World world) {
        super(entityType, world);
        setColor(DyeColor.GREEN);
        addVelocity(0,2,0);
    }

    @Override
    public void onDeath(DamageSource damageSource){
        super.onDeath(damageSource);
        getWorld().createExplosion(
                this,// 爆炸来源实体
                getX(),
                getY(),
                getZ(),
                10.0F,                       // 爆炸威力，原版 TNT 是 4.0
                World.ExplosionSourceType.TNT  // 破坏方块，类似原版 TNT
        );

        var world = getWorld();
        for (int i = 0; i < 20; i++) {
            var tnt = new TntEntity(world,getX(), getY(), getZ(),this);
            tnt.setFuse(Random.create().nextInt(40));
            world.spawnEntity(tnt);
        }
    }
}
