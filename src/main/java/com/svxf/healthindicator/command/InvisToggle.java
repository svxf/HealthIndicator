package com.svxf.healthindicator.command;

import com.svxf.healthindicator.Main;
import com.svxf.healthindicator.config.HealthIndicatorConfig;
import com.svxf.healthindicator.utils.Utils;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.command.Command;

public class InvisToggle extends Command {
    public InvisToggle() {
        super("toggleinvis", "invis");
    }

    HealthIndicatorConfig config = Main.config;

    private void set(boolean value) {
        config.showInvis = value;
        Utils.print("ShowInvis has set to "+EnumChatFormatting.RED + value + EnumChatFormatting.RESET + ".");
    }


    @Override
    public void handle(String[] args) {
        if(args.length == 0) {
            set(!config.showInvis);
            return;
        }

        set(Boolean.parseBoolean(args[0]));
    }
}
