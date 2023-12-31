package com.svxf.healthindicator.command;

import com.svxf.healthindicator.Config;
import com.svxf.healthindicator.HealthIndicator;
import com.svxf.healthindicator.utils.Utils;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.command.Command;

public class Offset extends Command {
    public Offset() {
        super("heartoffset", "offset");
    }

    HealthIndicator hi = HealthIndicator.getInstance();
    Config config = hi.getConfig();

    private void set(float value) {
        config.offset = value;
        hi.saveConfig();
        Utils.print("Offset has set to "+EnumChatFormatting.RED + value + EnumChatFormatting.RESET + ".");
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
