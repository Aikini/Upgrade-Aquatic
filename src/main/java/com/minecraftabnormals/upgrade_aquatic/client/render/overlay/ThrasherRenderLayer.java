
package com.minecraftabnormals.upgrade_aquatic.client.render.overlay;

import com.minecraftabnormals.upgrade_aquatic.client.UARenderTypes;
import com.minecraftabnormals.upgrade_aquatic.client.model.ThrasherModel;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.EntityGreatThrasher;
import com.minecraftabnormals.upgrade_aquatic.common.entities.thrasher.EntityThrasher;
import com.minecraftabnormals.upgrade_aquatic.core.UpgradeAquatic;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import com.teamabnormals.abnormals_core.client.ClientInfo;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThrasherRenderLayer<T extends EntityThrasher, M extends ThrasherModel<T>> extends LayerRenderer<T, M> {
	private static final ResourceLocation THRASHER_FROST = new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/thrasher/thrasher_emissive.png");
	private static final ResourceLocation GREAT_THRASHER_FROST = new ResourceLocation(UpgradeAquatic.MODID, "textures/entity/thrasher/great_thrasher_emissive.png");
	
	public ThrasherRenderLayer(IEntityRenderer<T, M> renderer) {
		super(renderer);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T thrasher, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		ClientInfo.MINECRAFT.getTextureManager().bindTexture(this.getThrasherFrostLayer(thrasher));

		int stunnedAnimation = (int) (thrasher.STUNNED_ANIMATION.getAnimationProgress() * 240);
		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(UARenderTypes.getEmissiveEntity(this.getThrasherFrostLayer(thrasher)));
		
		this.getEntityModel().setRotationAngles(thrasher, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getEntityModel().render(matrixStackIn, ivertexbuilder, stunnedAnimation, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
	
	public ResourceLocation getThrasherFrostLayer(EntityThrasher thrasher) {
		return thrasher instanceof EntityGreatThrasher ? GREAT_THRASHER_FROST: THRASHER_FROST;
	}
}