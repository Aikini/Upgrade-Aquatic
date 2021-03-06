package com.minecraftabnormals.upgrade_aquatic.common.items;

import java.util.List;
import java.util.function.Supplier;

import com.minecraftabnormals.upgrade_aquatic.common.entities.PikeEntity;
import com.teamabnormals.abnormals_core.common.entity.BucketableWaterMobEntity;
import com.teamabnormals.abnormals_core.common.items.MobBucketItem;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class PikeBucketItem extends MobBucketItem {

	public PikeBucketItem(Supplier<EntityType<? extends BucketableWaterMobEntity>> entityType, Supplier<? extends Fluid> supplier, Properties builder) {
		super(entityType, supplier, builder);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		CompoundNBT compoundnbt = stack.getTag();
		if (compoundnbt != null && compoundnbt.contains("BucketVariantTag", 3)) {
			int i = compoundnbt.getInt("BucketVariantTag");
			TextFormatting[] atextformatting = new TextFormatting[] {TextFormatting.ITALIC, TextFormatting.GRAY};
			
			tooltip.add((new TranslationTextComponent("tooltip.upgrade_aquatic." + PikeEntity.getNameById(i)).func_240701_a_(atextformatting)));
		}
	}
	
}
