package com.wzssoft.treasurehuntlib.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;

/**
 * added since 17.0.1
 *
 * @reference SilkTouchEnchantment
 * @specialNote this enchantment enables find treasure from digging shovelable blocks
 * <p>
 * level 1 /-> enable find treasure
 */
public class TreasureEnchantment extends Enchantment {

    protected TreasureEnchantment(Enchantment.Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.DIGGER, slotTypes);
    }

    @Override
    public int getMinPower(int level) {
        return 15;
    }

    @Override
    public int getMaxPower(int level) {
        return super.getMinPower(level) + 50;
    }

    //设置书的数量，比如锋利有5级，那么就写5
    @Override
    public int getMaxLevel() {
        return 1;
    }

    //无法从附魔台中附魔
    public boolean isAvailableForRandomSelection() {
        return false;
    }

    //不能和精准采集同时附魔
    @Override
    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != Enchantments.SILK_TOUCH;
    }
}
