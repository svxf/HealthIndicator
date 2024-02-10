package com.svxf.healthindicator.mixins;

import com.svxf.healthindicator.HealthIndicator;
import com.svxf.healthindicator.utils.HeartType;
import com.svxf.healthindicator.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RendererLivingEntity.class)
public abstract class RendererLivingEntityMixin<T extends EntityLivingBase> extends Render<T> {
    public Minecraft mc = Minecraft.getMinecraft();

    protected RendererLivingEntityMixin(RenderManager renderManager) {
        super(renderManager);
    }

    @Inject(method = "renderName(Lnet/minecraft/entity/EntityLivingBase;DDD)V", at = @At("HEAD"), cancellable = true)
    private void onRenderLabel(EntityLivingBase entity, double x, double y, double z, CallbackInfo ci) {
        if (HealthIndicator.config.indicatorEnabled)
        {
            if (entity instanceof EntityPlayer en) {
                if (!Utils.shouldRenderHeartsForEntity(en)) return;

                double distanceSq = entity.getDistanceSqToEntity(mc.getRenderViewEntity());
                double maxDistanceSq = HealthIndicator.config.maxViewDistance * HealthIndicator.config.maxViewDistance;
                if (distanceSq > maxDistanceSq) return;

                GlStateManager.pushMatrix();

                GlStateManager.translate((float)x + 0.0f, (float)y + en.height + 0.5F - (en.isSneaking() ? 0.3F : 0.0F) + HealthIndicator.config.offset, (float)z);

                GL11.glNormal3f(0.0F, 1.0F, 0.0F);

                GlStateManager.rotate(-mc.getRenderManager().playerViewY, 0.0F, 1.0F, 0.0F);
                GlStateManager.rotate(mc.getRenderManager().playerViewX, 1.0F, 0.0F, 0.0F);

                GlStateManager.scale(-0.02666667F, -0.02666667F, 0.02666667F);
                GlStateManager.translate(0.0F, 9.374999F - 11f, 0.0F);

                GlStateManager.depthMask(true);
                GlStateManager.enableDepth();

                GlStateManager.disableLighting();
                GlStateManager.enableBlend();

                float alpha = en.isSneaking() ? HealthIndicator.config.alpha : 1.0F;
                GlStateManager.color(1.0F, 1.0F, 1.0F, alpha);

                int healthRed = (int) Math.ceil(en.getHealth());
                int maxHealth = (int) Math.ceil(en.getMaxHealth());
                int healthYellow = (int) Math.ceil(en.getAbsorptionAmount());

                int heartsRed = (int) Math.ceil(healthRed / 2.0f);
                boolean lastRedHalf = (healthRed & 1) == 1;
                int heartsNormal = (int) Math.ceil(maxHealth / 2.0f);
                int heartsYellow = (int) Math.ceil(healthYellow / 2.0f);
                boolean lastYellowHalf = (healthYellow & 1) == 1;
                int heartsTotal = heartsNormal + heartsYellow;

                int heartsPerRow = 10;
                int rowsTotal = (heartsTotal + heartsPerRow - 1) / heartsPerRow;
                int rowOffset = Math.max(10 - (rowsTotal - 2), 3);

                int pixelsTotal = Math.min(heartsTotal, heartsPerRow) * 8 + 1;
                float maxX = pixelsTotal / 2.0f;

                int heartsTotalHeight = rowsTotal * rowOffset;

                for (int heart = 0; heart < heartsTotal; heart++) {
                    int row = heart / heartsPerRow;
                    int col = heart % heartsPerRow;

                    float x1 = maxX + col * 8 - 80;
                    float y1 = row * rowOffset - heartsTotalHeight;

                    mc.getTextureManager().bindTexture(Gui.icons);
                    mc.ingameGUI.drawTexturedModalRect(x1, y1, 16, 0, 9, 9);

                    HeartType type;
                    if (heart < heartsRed) {
                        type = HeartType.RED_FULL;
                        if (heart == heartsRed - 1 && lastRedHalf) {
                            type = HeartType.RED_HALF;
                        }
                    } else if (heart < heartsNormal) {
                        type = HeartType.EMPTY;
                    } else {
                        type = HeartType.YELLOW_FULL;
                        if (heart == heartsTotal - 1 && lastYellowHalf) {
                            type = HeartType.YELLOW_HALF;
                        }
                    }
                    if (type != HeartType.EMPTY) {
                        mc.getTextureManager().bindTexture(Gui.icons);
                        mc.ingameGUI.drawTexturedModalRect(x1, y1, type.u, type.v, 9, 9);
                    }
                }

                GlStateManager.enableLighting();
                GlStateManager.disableBlend();

                GlStateManager.depthMask(true);

                GlStateManager.popMatrix();
            }
        }
    }
}
