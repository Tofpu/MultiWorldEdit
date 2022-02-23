package io.tofpu.multiworldedit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public final class MultiWorldEditAPI {
    private static MultiWorldEdit multiWorldEdit;
    private static MultiWorldEditType multiWorldEditType;

    public static MultiWorldEdit load(final Plugin plugin) {
        final String version = plugin.getServer().getBukkitVersion().split("-")[0];
        final Plugin worldEditPlugin = Bukkit.getPluginManager()
                .getPlugin("WorldEdit");
        
        if (worldEditPlugin == null) {
            return null;
        }

        final String worldEditVersion = worldEditPlugin.getDescription().getVersion();

        plugin.getLogger().info("Searching for a compatible version of WorldEdit...");
        switch (version) {
            case "1.8":
            case "1.8.8":
                if (!worldEditVersion.startsWith("6")) {
                    incompatiblePlugin(plugin, worldEditVersion);
                    break;
                }
                MultiWorldEditAPI.multiWorldEdit = new MultiWorldEditV6();
                multiWorldEditType = MultiWorldEditType.V6;
                break;
            case "1.12.2":
            case "1.16.5":
            case "1.17.1":
            case "1.18.1":
                if (!worldEditVersion.startsWith("7")) {
                    incompatiblePlugin(plugin, worldEditVersion);
                    break;
                }
                MultiWorldEditAPI.multiWorldEdit = new MultiWorldEditV7();
                multiWorldEditType = MultiWorldEditType.V7;
                break;
            default:
                plugin.getLogger().info("Found no compatible version for server " +
                                        "version " + version + " and WorldEdit version " +
                                        "v" + worldEditVersion + "! disabling the " +
                                        "plugin now!");
                Bukkit.getPluginManager().disablePlugin(plugin);
                break;
        }

        if (MultiWorldEditAPI.multiWorldEdit != null) {
            plugin.getLogger().info("Found compatible version of WorldEdit for v" + version);
        }
        return MultiWorldEditAPI.multiWorldEdit;
    }

    private static void incompatiblePlugin(final Plugin plugin, final String version) {
        plugin.getLogger().info("Found incompatible version of WorldEdit " +
                                "(" + version + "). Please ensure" +
                                " that you're using WorldEdit version v7!");
        Bukkit.getPluginManager().disablePlugin(plugin);
    }

    public static MultiWorldEditType getWorldEditType() {
        return multiWorldEditType;
    }

    public static MultiWorldEdit getWorldEdit() {
        return multiWorldEdit;
    }
}
