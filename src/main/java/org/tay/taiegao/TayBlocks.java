package org.tay.taiegao;

import net.minecraft.block.AbstractBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static org.tay.taiegao.Taiegao.NAME_SPACE;

public class TayBlocks {
    private TayBlocks(){}

    public static final DieASheepBlock DIE_A_SHEEP_BLOCK = new DieASheepBlock(AbstractBlock.Settings.create());
    public static final BlockItem DIE_A_SHEEP_BLOCK_ITEM = new BlockItem(DIE_A_SHEEP_BLOCK,new Item.Settings());

    public static void initialize(){
        Registry.register(Registries.BLOCK,Identifier.of(NAME_SPACE,"die_a_sheep"),DIE_A_SHEEP_BLOCK);

        Registry.register(Registries.ITEM, Identifier.of(NAME_SPACE,"die_a_sheep"),DIE_A_SHEEP_BLOCK_ITEM);

        TayItems.registerItemToGroup(DIE_A_SHEEP_BLOCK_ITEM);
    }
}
