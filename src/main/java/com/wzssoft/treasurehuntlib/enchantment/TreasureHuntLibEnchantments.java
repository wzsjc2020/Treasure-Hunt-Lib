package com.wzssoft.treasurehuntlib.enchantment;

import com.wzssoft.treasurehuntlib.TreasureHuntLib;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TreasureHuntLibEnchantments {

    public static Enchantment TREASURE = registerEnchantment("treasure",
            new TreasureEnchantment(Enchantment.Rarity.VERY_RARE, EquipmentSlot.MAINHAND));

    //因为方块和需要同时注册一个item，以便能在物品栏中找到该方块
    public static Enchantment registerEnchantment(String name, Enchantment enchantment) {
        return Registry.register(Registry.ENCHANTMENT, new Identifier(TreasureHuntLib.MODID, name), enchantment);
    }

    public static void registerModEnchantment() {
        //当执行这一行命令时，会自动执行一遍空参构造
    }
}
