package org.tay.taiegao;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static org.tay.taiegao.Taiegao.NAME_SPACE;

public class TayEntities {
    private TayEntities() {}

    public static final EntityType<DieASheepEntity> DIE_A_SHEEP_ENTITY_ENTITY_TYPE = new EntityType<DieASheepEntity>(EntityType.Builder.create(DieASheepEntity, SpawnGroup.CREATURE));

    public static void initialize(){

    }
}
