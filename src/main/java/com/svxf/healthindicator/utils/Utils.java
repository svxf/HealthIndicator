package com.svxf.healthindicator.utils;

import com.svxf.healthindicator.Main;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class Utils {
    public static void print(String message) {
        String prefix = EnumChatFormatting.RED + "Health Indicator: " + EnumChatFormatting.RESET;
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(prefix+message));
    }

    public static boolean shouldRenderHeartsForEntity(EntityPlayer entity) {
        return (entity != Minecraft.getMinecraft().thePlayer || Main.config.renderAboveSelf)
                && (!entity.isInvisible() || (entity.isInvisible() && Main.config.showInvis))
                && (entity.deathTime == 0);
    }
}
