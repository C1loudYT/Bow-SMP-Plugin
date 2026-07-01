package com.bowsmp;

import org.bukkit.plugin.java.JavaPlugin;
import com.bowsmp.listeners.BowListener;
import com.bowsmp.commands.RandomBowCommand;
import com.bowsmp.commands.WithdrawCommand;
import com.bowsmp.managers.BowManager;

public class BowSMPPlugin extends JavaPlugin {

    private static BowSMPPlugin instance;
    private BowManager bowManager;

    @Override
    public void onEnable() {
        instance = this;
        bowManager = new BowManager();

        // Register event listeners
        getServer().getPluginManager().registerEvents(new BowListener(bowManager), this);

        // Register commands
        getCommand("randombow").setExecutor(new RandomBowCommand(bowManager));
        getCommand("withdraw").setExecutor(new WithdrawCommand(bowManager));

        getLogger().info("BowSMPPlugin has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("BowSMPPlugin has been disabled!");
    }

    public static BowSMPPlugin getInstance() {
        return instance;
    }

    public BowManager getBowManager() {
        return bowManager;
    }
}