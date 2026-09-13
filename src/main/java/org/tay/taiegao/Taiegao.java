package org.tay.taiegao;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class Taiegao implements ModInitializer {
    public static final String NAME_SPACE = "taiegao";

    @Override
    public void onInitialize() {
        TayItems.initialize();
        TayBlocks.initialize();
        eventInit();
    }

    private void eventInit(){
        ServerLivingEntityEvents.AFTER_DEATH.register((entity, damageSource) -> {
            // 只处理带标记的羊
            if (!(entity instanceof SheepEntity sheep)) return;
            if (!sheep.getCommandTags().contains("die_a_sheep")) return;

            World world = sheep.getWorld();
            if (world.isClient()) return;

            // 在羊的死亡位置生成 TNT 级别的爆炸
            world.createExplosion(
                    sheep,                      // 爆炸来源实体
                    sheep.getX(),
                    sheep.getY(),
                    sheep.getZ(),
                    10.0F,                       // 爆炸威力，原版 TNT 是 4.0
                    World.ExplosionSourceType.TNT  // 破坏方块，类似原版 TNT
            );

            for (int i = 0; i < 20; i++) {
                var tnt = new TntEntity(world,sheep.getX(), sheep.getY(), sheep.getZ(),sheep);
                tnt.setFuse(Random.create().nextInt(40));
                world.spawnEntity(tnt);
            }
        });

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerWorld world : server.getWorlds()) {
                for (var entity : world.iterateEntities()) {
                    if (entity instanceof SheepEntity sheep
                            && sheep.getCommandTags().contains("die_a_sheep")) {
                        float newYaw = sheep.getYaw() + 25.0F;
                        sheep.setYaw(newYaw);
                        sheep.setHeadYaw(newYaw);
                        sheep.setBodyYaw(newYaw);
                    }
                }
            }
        });
    }
}
