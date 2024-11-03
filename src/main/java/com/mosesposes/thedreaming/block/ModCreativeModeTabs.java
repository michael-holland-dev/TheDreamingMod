//package com.mosesposes.thedreaming.item;
//
//import com.mosesposes.thedreaming.TheDreaming;
//import net.minecraft.core.registries.Registries;
//import net.minecraft.network.chat.Component;
//import net.minecraft.world.item.CreativeModeTab;
//import net.minecraft.world.item.ItemStack;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.RegistryObject;
//
//public class ModCreativeModeTabs {
//    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
//            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheDreaming.MOD_ID);
//
//    public static final RegistryObject<CreativeModeTab> ALEXANDRITE_ITEMS_TAB = CREATIVE_MODE_TABS.register('alexandrite_items_tab',
//            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItem.ALEXANDRITE.get()))
//                    .title(Component.translatable())
//                    ).
//
//    public static void register(IEventBus eventBus) {
//        CREATIVE_MODE_TABS.register(eventBus);
//    }
//}
