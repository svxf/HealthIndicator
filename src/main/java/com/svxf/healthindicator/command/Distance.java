package com.svxf.healthindicator.command;

import com.svxf.healthindicator.Config;
import com.svxf.healthindicator.HealthIndicator;
import com.svxf.healthindicator.utils.Utils;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.command.Command;

public class Distance extends Command {
    public Distance() {
        super("heartdistance", "hd");
    }

    HealthIndicator hi = HealthIndicator.getInstance();
    Config config = hi.getConfig();

    private void set(float value) {
        config.distance = value;
        hi.saveConfig();
        Utils.print("Distance has set to "+EnumChatFormatting.RED + value + EnumChatFormatting.RESET + ".");
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
