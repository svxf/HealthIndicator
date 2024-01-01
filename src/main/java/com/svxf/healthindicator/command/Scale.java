package com.svxf.healthindicator.command;

import com.svxf.healthindicator.Main;
import com.svxf.healthindicator.config.HealthIndicatorConfig;
import com.svxf.healthindicator.utils.Utils;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.command.Command;

public class Scale extends Command {
    public Scale() {
        super("heartscale", "scale");
    }

    HealthIndicatorConfig config = Main.config;

    private void set(float value) {
        config.scale = value;
        Utils.print("Scale has set to "+EnumChatFormatting.RED + value + EnumChatFormatting.RESET + ".");
    }

    @Override
    public void handle(String[] args) {
        if(args.length == 0) {
            Utils.print("Incorrect args given, enter in a number");
            return;
        }

        set(Float.parseFloat(args[0]));
    }
}
