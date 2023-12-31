package com.svxf.healthindicator;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class HealthIndicator {
    public static HealthIndicator instance;

    private File configFile;
    private Config config = new Config();
    private final Gson gson = new Gson();

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

    public boolean Init(String dir) {
        configFile = new File(dir, "HealthIndicator.json");

        if (configFile.exists()) {
            try {
                String jsonConfig = new String(Files.readAllBytes(configFile.toPath()), StandardCharsets.UTF_8);
                config = gson.fromJson(jsonConfig, Config.class);
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        } else {
            saveConfig();
        }
        return true;
    }

    public void saveConfig() {
        try {
            if (!configFile.exists()) {
                if (!configFile.createNewFile()) {
                    System.err.println("Failed to create config file");
                    return;
                }
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(configFile));
            bufferedWriter.write(gson.toJson(config));
            bufferedWriter.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public Config getConfig()
    {
        return config;
    }
}
