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
    public boolean indicatorEnabled = true;

    @Switch(
            name = "Show Invis",
            description = "Show indicators on invisible players.",
            subcategory = "Health Indicators"
    )
    public boolean showInvis = true;

    @Switch(
            name = "Render Above Self",
            description = "Whether or not the hearts should render above your head.",
            subcategory = "Health Indicators"
    )
    public boolean renderAboveSelf = false;

    @Switch(
            name = "Hide in GUI",
            description = "Whether or not the hearts should render if you hae a GUI opened.",
            subcategory = "Health Indicators"
    )
    public boolean hideInGUI = false;

    @Number(
            name = "Offset",
            min = -1.0F,
            max = 1.0F,
            description = "Offset above the player head.",
            subcategory = "Health Indicators"
    )
    public float offset = 0.0f;

    @Number(
            name = "Alpha",
            min = 0.0F,
            max = 1.0F,
            description = "Transparency of the hearts when sneaking.",
            subcategory = "Health Indicators"
    )
    public float alpha = 0.3f;

    public HealthIndicatorConfig() {
        super(new Mod("Health Indicators", ModType.HUD), "health-indicators.json");
        this.initialize();
    }
}
