package com.svxf.healthindicator;

import com.svxf.healthindicator.command.*;
import com.svxf.healthindicator.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.command.CommandBus;
import net.weavemc.loader.api.event.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main implements ModInitializer {

    public KeyBinding key = new KeyBinding("Health Indicators", 38, "Health Indicator Mod");

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

        EventBus.subscribe(this);
        EventBus.subscribe(StartGameEvent.Post.class, e -> {
            List<KeyBinding> keyBindings = new ArrayList<>(
                    Arrays.asList(Minecraft.getMinecraft().gameSettings.keyBindings));
            keyBindings.add(key);
            Minecraft.getMinecraft().gameSettings.keyBindings = keyBindings.toArray(new KeyBinding[0]);
        });
    }

    @SubscribeEvent
    public void onKeyPress(KeyboardEvent e) {
        if (Keyboard.getEventKey() == key.getKeyCode() && e.getKeyState()) {
            Utils.print("Visiblity has set to "+ EnumChatFormatting.RED + !HealthIndicator.getInstance().getConfig().enabled + EnumChatFormatting.RESET + ".");
            HealthIndicator.getInstance().getConfig().enabled = !HealthIndicator.getInstance().getConfig().enabled;
        }
    }

    @SubscribeEvent
    public void onMousePress(MouseEvent e) {
        if (Mouse.getEventButton() == key.getKeyCode() + 100 && e.getButtonState()) {
            Utils.print("Visiblity has set to "+ EnumChatFormatting.RED + !HealthIndicator.getInstance().getConfig().enabled + EnumChatFormatting.RESET + ".");
            HealthIndicator.getInstance().getConfig().enabled = !HealthIndicator.getInstance().getConfig().enabled;
        }
    }
}