package com.svxf.healthindicator;

import com.svxf.healthindicator.command.*;
import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.command.CommandBus;

public class Main implements ModInitializer {
    @Override
    public void preInit() {
        System.out.println("Initializing HealthIndicator!");
        HealthIndicator hi = HealthIndicator.getInstance();
        hi.Init(System.getProperty("user.home") + "/.weave/mods");

        CommandBus.register(new Help());
        CommandBus.register(new Distance());
        CommandBus.register(new InvisToggle());
        CommandBus.register(new Offset());
        CommandBus.register(new Scale());
        CommandBus.register(new Toggle());
    }
}