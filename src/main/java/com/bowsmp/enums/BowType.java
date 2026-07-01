package com.bowsmp.enums;

public enum BowType {
    EXPLOSION("Explosion", "Shoots an explosion dealing 2.5 hearts", 180),
    LIGHTNING("Lightning", "Summons lightning dealing 3 hearts", 300),
    AIMBOT("Aimbot", "Shoots with aimbot dealing 5 hearts", 600),
    COBWEB("Cobweb", "Summons cobweb where shot dealing 2 hearts", 60),
    TELEPORT("Teleport", "Teleports to where arrow lands", 10),
    FLIGHT("Flight", "Grants 10 seconds of flight", 300);

    private final String name;
    private final String description;
    private final int cooldown; // in seconds

    BowType(String name, String description, int cooldown) {
        this.name = name;
        this.description = description;
        this.cooldown = cooldown;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCooldown() {
        return cooldown;
    }
}