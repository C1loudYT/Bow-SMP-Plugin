# Bow SMP Plugin

A Spigot/Paper plugin that gives players random bows with unique abilities and special effects!

## Features

- **6 Unique Bow Types** with different abilities and cooldowns
- **Infinite Arrows** - Never run out of ammunition
- **Bow Upgrades** - Damage increases by 0.5 hearts per kill
- **Strength System** - Track and withdraw bow strength
- **Cooldown Announcements** - Chat notifications when cooldowns expire (only visible to you)
- **Custom Lore** - Each bow displays its stats and abilities

## Bow Types

### 1. **Explosion Bow**
- Shoots an explosion that deals **2.5 hearts** of damage
- **3 minute cooldown**
- Creates an explosion effect at impact

### 2. **Lightning Bow**
- Summons lightning at impact dealing **3 hearts** of damage
- **5 minute cooldown**
- Visually stunning lightning strike

### 3. **Aimbot Bow**
- Automatically targets nearest enemy
- Deals **5 hearts** of damage
- **10 minute cooldown**
- Long range targeting (30 blocks)

### 4. **Cobweb Bow**
- Creates cobweb at impact location dealing **2 hearts** of damage
- **1 minute cooldown**
- Cobweb disappears after 5 seconds

### 5. **Teleport Bow**
- Teleports you to where the arrow lands
- **10 second cooldown** between shots
- No damage dealt
- Perfect for mobility

### 6. **Flight Bow**
- Grants **10 seconds of flight**
- **5 minute cooldown**
- No damage dealt
- Levitation effect for aerial mobility

## Commands

### `/randombow` or `/rbow`
Gives you a random bow with infinite arrows and its special ability!

```
/randombow
```

### `/withdraw <amount>` or `/wd <amount>`
Withdraw strength from your bow to reduce its damage. Perfect for sharing power or reducing overpowered bows!

**Usage:**
```
/withdraw 5.0
/wd 2.5
```

**Examples:**
- `/withdraw 5.0` - Removes 5.0 hearts of damage from your bow
- `/withdraw` - Shows your current strength and damage

## Strength System

- Every kill grants **0.5 hearts** of damage to your bow
- This damage also gets added to your **strength pool**
- Use `/withdraw` to remove strength from your bow, reducing its damage
- Useful for:
  - Balancing overpowered bows
  - Sharing power with teammates
  - Handicapping yourself for fair fights

## Installation

1. **Download** the compiled JAR file
2. **Place** it in your server's `plugins` folder
3. **Restart** your server
4. **Run** `/randombow` to get started!

## Building from Source

```bash
git clone https://github.com/C1loudYT/Bow-SMP-Plugin.git
cd Bow-SMP-Plugin
mvn clean package
```

The compiled JAR will be in the `target` folder.

## Configuration

No configuration file needed! The plugin works out of the box.

## Usage

1. Type `/randombow` or `/rbow` to receive a random bow
2. Equip the bow and draw it (right-click)
3. Fire an arrow at enemies or objects
4. Watch the ability trigger!
5. Wait for cooldown before using again
6. Get kills to increase your bow damage by 0.5 hearts
7. Use `/withdraw` to reduce bow strength if needed

## Permissions

Currently no special permissions required. Players can use all commands by default.

## Requirements

- **Java 17+**
- **Minecraft 1.20+**
- **Paper or Spigot Server**

## Support

For issues, feature requests, or bugs, please create an issue on the repository.

## License

This plugin is open-source and free to use.

## Credits

Created for SMP gameplay enhancement!