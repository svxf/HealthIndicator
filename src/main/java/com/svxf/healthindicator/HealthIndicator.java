package com.svxf.healthindicator;

import com.svxf.healthindicator.config.HealthIndicatorConfig;
import com.svxf.healthindicator.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.EnumChatFormatting;
import net.weavemc.loader.api.ModInitializer;
import net.weavemc.loader.api.event.*;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.gitlab.candicey.zenithloader.ZenithLoader;
import com.gitlab.candicey.zenithloader.dependency.Dependencies;

public class HealthIndicator implements ModInitializer {

    public KeyBinding key = new KeyBinding("Health Indicators", 38, "Health Indicator Mod");

    public static HealthIndicatorConfig config;
    @Override
    public void preInit() {
        System.out.println("Initializing HealthIndicator!");

        EventBus.subscribe(this);
        EventBus.subscribe(StartGameEvent.Post.class, e -> {
            List<KeyBinding> keyBindings = new ArrayList<>(
                    Arrays.asList(Minecraft.getMinecraft().gameSettings.keyBindings));
            keyBindings.add(key);
            Minecraft.getMinecraft().gameSettings.keyBindings = keyBindings.toArray(new KeyBinding[0]);
        });

        ZenithLoader.INSTANCE.loadDependencies(
                Dependencies.INSTANCE.getConcentra().invoke(
                        "healthindicator"
                )
        );

        EventBus.subscribe(StartGameEvent.Pre.class, (event) -> config = new HealthIndicatorConfig());
    }

    @SubscribeEvent
    public void onKeyPress(KeyboardEvent e) {
        if (Keyboard.getEventKey() == key.getKeyCode() && e.getKeyState()) {
            Utils.print("Visiblity has set to "+ EnumChatFormatting.RED + !HealthIndicator.config.indicatorEnabled + EnumChatFormatting.RESET + ".");
            HealthIndicator.config.indicatorEnabled = !HealthIndicator.config.indicatorEnabled;
        }
    }

    @SubscribeEvent
    public void onMousePress(MouseEvent e) {
        if (Mouse.getEventButton() == key.getKeyCode() + 100 && e.getButtonState()) {
            Utils.print("Visiblity has set to "+ EnumChatFormatting.RED + !HealthIndicator.config.indicatorEnabled + EnumChatFormatting.RESET + ".");
            HealthIndicator.config.indicatorEnabled = !HealthIndicator.config.indicatorEnabled;
        }
    }
}