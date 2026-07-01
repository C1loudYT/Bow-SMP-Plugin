package com.bowsmp.managers;

import com.bowsmp.enums.BowType;
import com.bowsmp.models.PlayerBowData;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class BowManager {
    private final Map<String, PlayerBowData> playerBows = new HashMap<>();
    private final Random random = new Random();

    public PlayerBowData getOrCreateBow(Player player) {
        return playerBows.computeIfAbsent(player.getUniqueId().toString(),
                k -> new PlayerBowData(player, getRandomBowType()));
    }

    public PlayerBowData getBowData(Player player) {
        return playerBows.get(player.getUniqueId().toString());
    }

    public void giveRandomBow(Player player) {
        PlayerBowData bowData = getOrCreateBow(player);
        BowType newType = getRandomBowType();
        bowData.setBowType(newType);
        bowData.setLastShotTime(0); // Reset cooldown
    }

    public BowType getRandomBowType() {
        BowType[] types = BowType.values();
        return types[random.nextInt(types.length)];
    }

    public void removeBowData(Player player) {
        playerBows.remove(player.getUniqueId().toString());
    }

    public void addKill(Player player) {
        PlayerBowData data = getBowData(player);
        if (data != null) {
            data.addKill();
        }
    }
}