package com.bowsmp.listeners;

import com.bowsmp.managers.BowManager;
import com.bowsmp.models.PlayerBowData;
import com.bowsmp.enums.BowType;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class BowListener implements Listener {
    private final BowManager bowManager;

    public BowListener(BowManager bowManager) {
        this.bowManager = bowManager;
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Arrow arrow)) return;
        if (!(arrow.getShooter() instanceof Player player)) return;

        PlayerBowData bowData = bowManager.getBowData(player);
        if (bowData == null) return;

        BowType bowType = bowData.getBowType();

        // Check cooldown
        if (bowData.isOnCooldown()) {
            return; // Still on cooldown
        }

        Location hitLocation = arrow.getLocation();

        switch (bowType) {
            case EXPLOSION:
                handleExplosion(player, hitLocation, bowData);
                break;
            case LIGHTNING:
                handleLightning(player, hitLocation, bowData);
                break;
            case AIMBOT:
                handleAimbot(player, arrow, bowData);
                break;
            case COBWEB:
                handleCobweb(player, hitLocation, bowData);
                break;
            case TELEPORT:
                handleTeleport(player, hitLocation, bowData);
                break;
            case FLIGHT:
                handleFlight(player, bowData);
                break;
        }

        bowData.setLastShotTime(System.currentTimeMillis());
        arrow.remove();
    }

    private void handleExplosion(Player player, Location hitLocation, PlayerBowData bowData) {
        hitLocation.getWorld().createExplosion(hitLocation, 3.0f, false, false);

        // Deal damage to nearby entities
        for (Entity entity : hitLocation.getWorld().getNearbyEntities(hitLocation, 5, 5, 5)) {
            if (entity instanceof LivingEntity livingEntity && !entity.equals(player)) {
                livingEntity.damage(bowData.getDamage(), player);
            }
        }

        scheduleCooldownMessage(player, bowData);
    }

    private void handleLightning(Player player, Location hitLocation, PlayerBowData bowData) {
        hitLocation.getWorld().strikeLightning(hitLocation);

        // Deal damage to nearby entities
        for (Entity entity : hitLocation.getWorld().getNearbyEntities(hitLocation, 5, 5, 5)) {
            if (entity instanceof LivingEntity livingEntity && !entity.equals(player)) {
                livingEntity.damage(bowData.getDamage(), player);
            }
        }

        scheduleCooldownMessage(player, bowData);
    }

    private void handleAimbot(Player player, Arrow arrow, PlayerBowData bowData) {
        // Aimbot: Find nearest entity and adjust arrow trajectory
        Location playerLocation = player.getLocation();
        LivingEntity target = null;
        double closestDistance = 30.0; // 30 block range

        for (Entity entity : player.getNearbyEntities(30, 30, 30)) {
            if (entity instanceof LivingEntity livingEntity && !entity.equals(player) && !(entity instanceof ArmorStand)) {
                double distance = playerLocation.distance(entity.getLocation());
                if (distance < closestDistance) {
                    closestDistance = distance;
                    target = livingEntity;
                }
            }
        }

        if (target != null) {
            target.damage(bowData.getDamage(), player);
        }

        scheduleCooldownMessage(player, bowData);
    }

    private void handleCobweb(Player player, Location hitLocation, PlayerBowData bowData) {
        // Place cobweb at hit location
        Block block = hitLocation.getBlock();
        block.setType(Material.COBWEB);

        // Deal damage to entities in the area
        for (Entity entity : hitLocation.getWorld().getNearbyEntities(hitLocation, 2, 2, 2)) {
            if (entity instanceof LivingEntity livingEntity && !entity.equals(player)) {
                livingEntity.damage(bowData.getDamage(), player);
            }
        }

        // Remove cobweb after 5 seconds
        Bukkit.getScheduler().scheduleSyncDelayedTask(
                com.bowsmp.BowSMPPlugin.getInstance(),
                () -> block.setType(Material.AIR),
                100 // 5 seconds = 100 ticks
        );

        scheduleCooldownMessage(player, bowData);
    }

    private void handleTeleport(Player player, Location hitLocation, PlayerBowData bowData) {
        player.teleport(hitLocation);
        player.sendMessage(ChatColor.AQUA + "Teleported to arrow impact!");

        scheduleCooldownMessage(player, bowData);
    }

    private void handleFlight(Player player, PlayerBowData bowData) {
        player.setAllowFlight(true);
        player.setFlying(true);
        player.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, 200, 2, false, false));

        player.sendMessage(ChatColor.LIGHT_PURPLE + "You can now fly for 10 seconds!");

        Bukkit.getScheduler().scheduleSyncDelayedTask(
                com.bowsmp.BowSMPPlugin.getInstance(),
                () -> {
                    player.setFlying(false);
                    player.setAllowFlight(false);
                    player.sendMessage(ChatColor.RED + "Your flight has ended!");
                },
                200 // 10 seconds = 200 ticks
        );

        scheduleCooldownMessage(player, bowData);
    }

    @EventHandler
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player player)) return;

        PlayerBowData bowData = bowManager.getBowData(player);
        if (bowData == null) return;

        LivingEntity victim = event.getEntity() instanceof LivingEntity ? (LivingEntity) event.getEntity() : null;
        if (victim == null) return;

        // Check if player killed someone
        if (victim.getHealth() - event.getDamage() <= 0) {
            bowManager.addKill(player);
            player.sendMessage(ChatColor.GOLD + "Kill recorded! Bow damage increased by 0.5 hearts. " +
                    "Total: " + ChatColor.YELLOW + String.format("%.1f", bowData.getDamage()) + " hearts");
        }
    }

    private void scheduleCooldownMessage(Player player, PlayerBowData bowData) {
        BowType bowType = bowData.getBowType();
        int cooldownSeconds = bowType.getCooldown();

        Bukkit.getScheduler().scheduleSyncDelayedTask(
                com.bowsmp.BowSMPPlugin.getInstance(),
                () -> {
                    // Only send message to the player with the bow
                    if (player.isOnline()) {
                        player.sendMessage(ChatColor.GREEN + "Your " + ChatColor.GOLD + bowType.getName() +
                                ChatColor.GREEN + " bow cooldown is over! You can shoot again!");
                    }
                },
                (long) cooldownSeconds * 20 // Convert seconds to ticks
        );
    }
}