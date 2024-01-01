package com.svxf.healthindicator.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Number;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;

public class HealthIndicatorConfig extends Config {

    @Switch(
            name = "Main Toggle",
            description = "Global toggle.",
            subcategory = "Health Indicators"
    )
    public boolean enabled = true;

    @Switch(
            name = "Show Invis",
            description = "Show indicators on invisible players.",
            subcategory = "Health Indicators"
    )
    public boolean showInvis = true;

    @Number(
            name = "Offset",
            min = 0.0F,
            max = 5.0F,
            description = "Offset above the player head.",
            subcategory = "Health Indicators"
    )
    public float offset = 0.0f;

    @Number(
            name = "Distance",
            min = 0.0F,
            max = 100.0F,
            description = "Maximum distance to show indicators.",
            subcategory = "Health Indicators"
    )
    public float distance = 20.0f;

    @Number(
            name = "Scale",
            min = 0.1F,
            max = 3.0F,
            description = "Scale of the indicators.",
            subcategory = "Health Indicators"
    )
    public float scale = 0.3f;

    public HealthIndicatorConfig() {
        super(new Mod("Health Indicators", ModType.HUD), "health-indicators.json");
        this.initialize();
    }
}
