# Installation Guide

## Prerequisites

- Minecraft Server (1.20+)
- Server Type: Paper, Spigot, or compatible fork
- Java 17 or higher

## Step-by-Step Installation

### Option 1: Using Pre-built JAR (Easiest)

1. Download the latest JAR file from [Releases](https://github.com/C1loudYT/Bow-SMP-Plugin/releases)
2. Navigate to your server's `plugins` folder
3. Place the JAR file in the plugins folder
4. Restart your server
5. Done! Players can now use `/randombow`

### Option 2: Building from Source

1. Clone the repository:
   ```bash
   git clone https://github.com/C1loudYT/Bow-SMP-Plugin.git
   cd Bow-SMP-Plugin
   ```

2. Build with Maven:
   ```bash
   mvn clean package
   ```

3. Copy the JAR from `target/BowSMPPlugin-1.0.0.jar` to your server's `plugins` folder

4. Restart your server

## Uploading to Modrinth

1. Go to [Modrinth](https://modrinth.com)
2. Click "Create Project"
3. Fill in the following information:
   - **Project Type:** Plugin
   - **Project Name:** Bow SMP Plugin
   - **Summary:** A fun SMP plugin that gives players random bows with unique abilities!
   - **Description:** Copy from README.md
   - **Categories:** Gameplay
   - **License:** MIT (or your choice)

4. Upload the JAR file from the releases or build

5. Set the following:
   - **Game Versions:** 1.20.1+
   - **Loaders:** Paper, Spigot

6. Publish your project!

## Verification

After installation, verify the plugin is loaded:

1. Start your server
2. Check the console output for:
   ```
   [PluginName] BowSMPPlugin has been enabled!
   ```

3. In-game, run:
   ```
   /randombow
   ```

4. You should receive a bow with a random ability!

## Troubleshooting

### Plugin not loading?
- Ensure the JAR is in the correct `plugins` folder
- Check that your server is using Paper or Spigot
- Verify Java version is 17+
- Check server logs for errors

### Command not working?
- Make sure the plugin loaded (check console on startup)
- Verify you're using `/randombow` or `/rbow`
- Ensure you have proper permissions

### Bows not giving items?
- Check that you have inventory space
- Verify the console for any error messages

## Support

For issues or questions:
1. Check the [GitHub Issues](https://github.com/C1loudYT/Bow-SMP-Plugin/issues)
2. Create a new issue with details about your problem

Enjoy your Bow SMP!