package com.svxf.healthindicator;

public class HealthIndicator {
    public static HealthIndicator instance;

    public HealthIndicator() {
        instance = this;
    }

    public static HealthIndicator getInstance() {
        if (instance == null)
        {
            instance = new HealthIndicator();
        }
        return instance;
    }
}
