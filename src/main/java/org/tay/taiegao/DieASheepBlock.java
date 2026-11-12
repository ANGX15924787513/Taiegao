package org.tay.taiegao;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FallingBlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DieASheepBlock extends Block {
    public DieASheepBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        if (world.isClient()) return;

        int radius = 5;
        Vec3d target = Vec3d.ofCenter(pos);

        var sheep = new DieASheepEntity(EntityType.SHEEP, world);
        sheep.setPos(target.getX(), target.getY() + 1, target.getZ());
        sheep.damage(world.getDamageSources().magic(),0.5f);
        world.spawnEntity(sheep);

        if (!world.getBlockState(pos.down()).isOf(Blocks.SAND)) return;

        for (BlockPos sandPos : BlockPos.iterate(pos.add(-radius, -radius, -radius), pos.add(radius, radius, radius))) {
            BlockState sandState = world.getBlockState(sandPos);
            if (!sandState.isOf(Blocks.SAND)) continue;

            FallingBlockEntity falling = FallingBlockEntity.spawnFromBlock(world, sandPos, sandState);
            falling.setFallingBlockPos(sandPos);
            falling.setVelocity(0,5,0);
        }
    }
}
