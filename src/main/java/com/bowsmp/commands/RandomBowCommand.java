package com.bowsmp.commands;

import com.bowsmp.managers.BowManager;
import com.bowsmp.models.PlayerBowData;
import com.bowsmp.enums.BowType;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class RandomBowCommand implements CommandExecutor {
    private final BowManager bowManager;

    public RandomBowCommand(BowManager bowManager) {
        this.bowManager = bowManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        PlayerBowData bowData = bowManager.getOrCreateBow(player);
        BowType bowType = bowData.getBowType();

        // Create bow item
        ItemStack bow = new ItemStack(Material.BOW);
        ItemMeta meta = bow.getItemMeta();

        if (meta != null) {
            meta.setDisplayName(ChatColor.GOLD + bowType.getName() + " Bow");
            meta.setLore(Arrays.asList(
                    ChatColor.GRAY + bowType.getDescription(),
                    ChatColor.YELLOW + "Damage: " + ChatColor.WHITE + String.format("%.1f", bowData.getDamage()) + " hearts",
                    ChatColor.YELLOW + "Cooldown: " + ChatColor.WHITE + bowType.getCooldown() + "s",
                    ChatColor.YELLOW + "Kills: " + ChatColor.WHITE + bowData.getKillCount(),
                    ChatColor.YELLOW + "Strength: " + ChatColor.WHITE + String.format("%.1f", bowData.getStrength())
            ));
            bow.setItemMeta(meta);
        }

        player.getInventory().addItem(bow);
        player.sendMessage(ChatColor.GREEN + "You received a " + ChatColor.GOLD + bowType.getName() + ChatColor.GREEN + " Bow!");
        player.sendMessage(ChatColor.GRAY + bowType.getDescription());

        return true;
    }
}