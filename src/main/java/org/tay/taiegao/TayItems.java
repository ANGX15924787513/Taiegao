package org.tay.taiegao;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static org.tay.taiegao.Taiegao.NAME_SPACE;

public class TayItems {
    private TayItems() {}

    //ADD ITEMS HERE -> REGISTER
    public static final TntGunItem TNT_GUN = new TntGunItem(new Item.Settings()
            .maxCount(1)
    );

    public static final SpawnEggItem DIE_A_SHEEP_SPAWN_EGG_ITEM = new SpawnEggItem(E);

    public static final ItemGroup TAY_ITEM_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(TNT_GUN))
            .displayName(Text.translatable("itemGroup.taiegao.tay_item_group"))
            .build();

    public static void initialize(){
        Registry.register(Registries.ITEM, Identifier.of(NAME_SPACE,"tnt_gun"),TNT_GUN);
        Registry.register(Registries.ITEM_GROUP, Identifier.of(NAME_SPACE,"tay_item_group"),TAY_ITEM_GROUP);

        registerItemToGroup(TNT_GUN);
    }

    public static void registerItemToGroup(Item item){
        ItemGroupEvents.modifyEntriesEvent(Registries.ITEM_GROUP.getKey(TAY_ITEM_GROUP).orElseThrow())
                .register(entries -> entries.add(item));
    }
}
