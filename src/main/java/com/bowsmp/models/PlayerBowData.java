package com.bowsmp.models;

import com.bowsmp.enums.BowType;
import org.bukkit.entity.Player;

public class PlayerBowData {
    private final Player player;
    private BowType bowType;
    private double damage;
    private double strength;
    private long lastShotTime;
    private int killCount;

    public PlayerBowData(Player player, BowType bowType) {
        this.player = player;
        this.bowType = bowType;
        this.damage = getDamageForBowType(bowType);
        this.strength = 0.0;
        this.lastShotTime = 0;
        this.killCount = 0;
    }

    public Player getPlayer() {
        return player;
    }

    public BowType getBowType() {
        return bowType;
    }

    public void setBowType(BowType bowType) {
        this.bowType = bowType;
        this.damage = getDamageForBowType(bowType);
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public double getStrength() {
        return strength;
    }

    public void setStrength(double strength) {
        this.strength = strength;
    }

    public void addKill() {
        this.killCount++;
        this.damage += 0.5; // Increase damage by 0.5 hearts per kill
        this.strength += 0.5; // Also add to strength pool
    }

    public double withdraw(double amount) {
        this.strength -= amount;
        this.damage -= amount;
        return this.strength;
    }

    public int getKillCount() {
        return killCount;
    }

    public long getLastShotTime() {
        return lastShotTime;
    }

    public void setLastShotTime(long time) {
        this.lastShotTime = time;
    }

    public boolean isOnCooldown() {
        long currentTime = System.currentTimeMillis();
        long cooldownMs = bowType.getCooldown() * 1000L;
        return (currentTime - lastShotTime) < cooldownMs;
    }

    public long getCooldownRemaining() {
        long currentTime = System.currentTimeMillis();
        long cooldownMs = bowType.getCooldown() * 1000L;
        long remaining = cooldownMs - (currentTime - lastShotTime);
        return Math.max(0, remaining);
    }

    private double getDamageForBowType(BowType type) {
        return switch (type) {
            case EXPLOSION -> 2.5;
            case LIGHTNING -> 3.0;
            case AIMBOT -> 5.0;
            case COBWEB -> 2.0;
            case TELEPORT -> 0.0; // No damage
            case FLIGHT -> 0.0; // No damage
        };
    }
}