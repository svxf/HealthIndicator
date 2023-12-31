package com.svxf.healthindicator.command;

import com.svxf.healthindicator.Config;
import com.svxf.healthindicator.HealthIndicator;
import com.svxf.healthindicator.utils.Utils;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.command.Command;

public class Toggle extends Command {
    public Toggle() {
        super("togglehealthindicator", "togglehi");
    }

    HealthIndicator hi = HealthIndicator.getInstance();
    Config config = hi.getConfig();

    private void set(boolean value) {
        config.enabled = value;
        hi.saveConfig();
        Utils.print("Visiblity has set to "+EnumChatFormatting.RED + value + EnumChatFormatting.RESET + ".");
    }

    @Override
    public void handle(String[] args) {
        if(args.length == 0) {
            set(!config.enabled);
            return;
        }

        set(Boolean.parseBoolean(args[0]));
    }
}
