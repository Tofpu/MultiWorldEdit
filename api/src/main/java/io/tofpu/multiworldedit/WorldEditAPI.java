package io.tofpu.multiworldedit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public final class WorldEditAPI {
    private static WorldEdit worldEdit;
    private static WorldEditType worldEditType;

    public static WorldEdit load(final Plugin plugin) {
        final String version = plugin.getServer().getBukkitVersion().split("-")[0];
        final Plugin worldEditPlugin = Bukkit.getPluginManager()
                .getPlugin("WorldEdit");
        final String worldEditVersion = worldEditPlugin.getDescription().getVersion();

        plugin.getLogger().info("Searching for a compatible version of WorldEdit...");
        switch (version) {
            case "1.8":
            case "1.8.8":
                if (!worldEditVersion.startsWith("6")) {
                    incompatiblePlugin(plugin, worldEditVersion);
                    break;
                }
                WorldEditAPI.worldEdit = new WorldEditV6();
                worldEditType = WorldEditType.V6;
                break;
            case "1.12.2":
            case "1.16.5":
            case "1.17.1":
            case "1.18.1":
                if (!worldEditVersion.startsWith("7")) {
                    incompatiblePlugin(plugin, worldEditVersion);
                    break;
                }
                WorldEditAPI.worldEdit = new WorldEditV7();
                worldEditType = WorldEditType.V7;
                break;
            default:
                plugin.getLogger().info("Found no compatible version for server " +
                                        "version " + version + " and WorldEdit version " +
                                        "v" + worldEditVersion + "! disabling the " +
                                        "plugin now!");
                Bukkit.getPluginManager().disablePlugin(plugin);
                break;
        }

        if (WorldEditAPI.worldEdit != null) {
            plugin.getLogger().info("Found compatible version of WorldEdit for v" + version);
        }
        return WorldEditAPI.worldEdit;
    }

    private static void incompatiblePlugin(final Plugin plugin, final String version) {
        plugin.getLogger().info("Found incompatible version of WorldEdit " +
                                "(" + version + "). Please ensure" +
                                " that you're using WorldEdit version v7!");
        Bukkit.getPluginManager().disablePlugin(plugin);
    }

    public static WorldEditType getWorldEditType() {
        return worldEditType;
    }

    public static WorldEdit getWorldEdit() {
        return worldEdit;
    }
}
