package com.mosesposes.thedreaming.item;

import com.mosesposes.thedreaming.TheDreaming;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import java.util.function.Supplier;

public class ModBlock {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TheDreaming.MOD_ID);

    public static RegistryObject<Block> ALEXANDRITE_BLOCK = registerBlock("alexandrite_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

//    public static RegistryObject<Block> ALEXANDRITE_ORE =
//            registerBlock("alexandrite_ore", () -> new Block(BlockBehaviour.Properties.of()
//                    .strength(4f).requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));

    public static void register(IEventBus event) {
        BLOCKS.register(event);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItem.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
