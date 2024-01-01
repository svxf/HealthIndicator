package com.svxf.healthindicator.command;

import com.svxf.healthindicator.Main;
import com.svxf.healthindicator.config.HealthIndicatorConfig;
import com.svxf.healthindicator.utils.Utils;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.command.Command;

public class Toggle extends Command {
    public Toggle() {
        super("togglehealthindicator", "togglehi");
    }

    HealthIndicatorConfig config = Main.config;

    private void set(boolean value) {
        config.enabled = value;
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
