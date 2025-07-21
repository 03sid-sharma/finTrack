package com.sid.fintrack.config;

import org.springframework.stereotype.Component;
import io.github.cdimascio.dotenv.Dotenv;

@Component
public class EnvConfig {
    private final Dotenv dotenv = Dotenv.load();

    public String getDbName() {
        return dotenv.get("DB_NAME");
    }

    public String getJWTSecret() {
        return dotenv.get("JWT_SECRET");
    }

    public String getJWTExpirationMs() {
        return dotenv.get("JWT_EXPIRATION");
    }


    // ... other getters
}