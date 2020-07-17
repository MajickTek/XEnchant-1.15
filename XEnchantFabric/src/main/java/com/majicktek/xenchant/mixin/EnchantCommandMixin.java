package com.majicktek.xenchant.mixin;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.Dynamic2CommandExceptionType;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.server.command.EnchantCommand;

import java.util.Collection;
import java.util.Iterator;

@Mixin(EnchantCommand.class)
public class EnchantCommandMixin {

    @Shadow @Final private static DynamicCommandExceptionType FAILED_ENTITY_EXCEPTION;
    @Shadow @Final private static DynamicCommandExceptionType FAILED_ITEMLESS_EXCEPTION;
    @Shadow @Final private static DynamicCommandExceptionType FAILED_INCOMPATIBLE_EXCEPTION;
    @Shadow @Final private static Dynamic2CommandExceptionType FAILED_LEVEL_EXCEPTION;
    @Shadow @Final private static SimpleCommandExceptionType FAILED_EXCEPTION;

    /**
     * @author MajickTek
     * @reason The functionality of the command needs to be changed in a native way.
     */
    @Overwrite
    private static int execute(ServerCommandSource source, Collection<? extends Entity> targets, Enchantment enchantment, int level) throws CommandSyntaxException {
        Iterator iterator = targets.iterator();
        Entity entity = (Entity)iterator.next();
        if (entity instanceof LivingEntity) {
            LivingEntity livingEntity = (LivingEntity)entity;
            ItemStack itemStack = livingEntity.getMainHandStack();
            if (!itemStack.isEmpty()) {
                    itemStack.addEnchantment(enchantment, level);
            } else if(targets.size() == 1){
                throw FAILED_ITEMLESS_EXCEPTION.create(livingEntity.getName().getString());
            }
        }

        if (targets.size() == 1) {
            source.sendFeedback(new TranslatableText("commands.enchant.success.single", new Object[]{enchantment.getName(level), ((Entity)targets.iterator().next()).getDisplayName()}), true);
        } else {
            source.sendFeedback(new TranslatableText("commands.enchant.success.multiple", new Object[]{enchantment.getName(level), targets.size()}), true);
        }
        return 0;
    }


}
