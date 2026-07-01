package com.bowsmp.commands;

import com.bowsmp.managers.BowManager;
import com.bowsmp.models.PlayerBowData;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WithdrawCommand implements CommandExecutor {
    private final BowManager bowManager;

    public WithdrawCommand(BowManager bowManager) {
        this.bowManager = bowManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command!");
            return true;
        }

        PlayerBowData bowData = bowManager.getBowData(player);
        if (bowData == null) {
            player.sendMessage(ChatColor.RED + "You don't have a bow! Use /randombow to get one.");
            return true;
        }

        // Check if player has any strength to withdraw
        double strength = bowData.getStrength();
        if (strength <= 0) {
            player.sendMessage(ChatColor.RED + "You have no strength to withdraw! Current strength: " + ChatColor.YELLOW + String.format("%.1f", strength));
            return true;
        }

        if (args.length == 0) {
            // No argument - show current strength and usage
            player.sendMessage(ChatColor.AQUA + "=== Bow Strength Info ===");
            player.sendMessage(ChatColor.YELLOW + "Current Strength: " + ChatColor.WHITE + String.format("%.1f", strength));
            player.sendMessage(ChatColor.YELLOW + "Current Damage: " + ChatColor.WHITE + String.format("%.1f", bowData.getDamage()) + " hearts");
            player.sendMessage(ChatColor.GRAY + " ");
            player.sendMessage(ChatColor.AQUA + "Usage: /withdraw <amount>");
            player.sendMessage(ChatColor.GRAY + "Example: /withdraw 5.0");
            return true;
        }

        try {
            double amount = Double.parseDouble(args[0]);

            if (amount <= 0) {
                player.sendMessage(ChatColor.RED + "You must withdraw a positive amount!");
                return true;
            }

            if (amount > strength) {
                player.sendMessage(ChatColor.RED + "You cannot withdraw " + ChatColor.YELLOW + String.format("%.1f", amount) +
                        ChatColor.RED + " strength! You only have " + ChatColor.YELLOW + String.format("%.1f", strength) + ChatColor.RED + ".");
                return true;
            }

            // Withdraw the strength
            double newStrength = bowData.withdraw(amount);
            double newDamage = bowData.getDamage();

            player.sendMessage(ChatColor.GREEN + "Successfully withdrew " + ChatColor.YELLOW + String.format("%.1f", amount) +
                    ChatColor.GREEN + " strength!");
            player.sendMessage(ChatColor.AQUA + "Remaining Strength: " + ChatColor.WHITE + String.format("%.1f", newStrength));
            player.sendMessage(ChatColor.AQUA + "New Damage: " + ChatColor.WHITE + String.format("%.1f", newDamage) + " hearts");

        } catch (NumberFormatException e) {
            player.sendMessage(ChatColor.RED + "Invalid number! Usage: /withdraw <amount>");
        }

        return true;
    }
}