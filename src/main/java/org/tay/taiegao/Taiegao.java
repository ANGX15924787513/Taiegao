package org.tay.taiegao;

import net.fabricmc.api.ModInitializer;

public class Taiegao implements ModInitializer {
    public static final String NAME_SPACE = "taiegao";

    @Override
    public void onInitialize() {
        TayItems.initialize();
        TayBlocks.initialize();
    }
}
