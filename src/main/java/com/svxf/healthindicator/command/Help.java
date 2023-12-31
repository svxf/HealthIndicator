package com.svxf.healthindicator.command;

import com.svxf.healthindicator.utils.Utils;
import net.weavemc.loader.api.command.Command;

public class Help extends Command {
    public Help() {
        super("heart", "hearthelp");
    }

    @Override
    public void handle(String[] args) {
        Utils.print("Commands:\n" +
                "/togglehealthindicator (/togglehi)\n" +
                "/heartdistance [number] (/hd)\n" +
                "/toggleinvis (/invis)\n" +
                "/heartscale [number] (/scale)\n" +
                "/heartoffset [number] (/offset)");
    }
}
