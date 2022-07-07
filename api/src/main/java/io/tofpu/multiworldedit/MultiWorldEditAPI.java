package io.tofpu.multiworldedit;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public final class MultiWorldEditAPI {
    private static MultiWorldEdit multiWorldEdit;
    private static MultiWorldEditType multiWorldEditType;

    public static MultiWorldEdit load(final Plugin plugin) {
        final String version = plugin.getServer()
                .getBukkitVersion()
                .split("-")[0];
        final String[] versionArgs =
                version.split("\\.");
        final String minor = versionArgs[1];

        final Plugin worldEditPlugin = Bukkit.getPluginManager()
                .getPlugin("WorldEdit");
        
        if (worldEditPlugin == null) {
            return null;
        }
        final String worldEditVersion = worldEditPlugin.getDescription().getVersion();
        plugin.getLogger().info("Searching for a compatible version of WorldEdit...");
        
        if (Integer.parseInt(minor) <= 12) {
            if (!worldEditVersion.startsWith("6")) {
                incompatiblePlugin(plugin, worldEditVersion);
                return null;
            }
            MultiWorldEditAPI.multiWorldEdit = new MultiWorldEditV6();
            multiWorldEditType = MultiWorldEditType.V6;
        } else {
            if (worldEditVersion.startsWith("6")) {
                incompatiblePlugin(plugin, worldEditVersion);
                return null;
            }
            MultiWorldEditAPI.multiWorldEdit = new MultiWorldEditV7();
            multiWorldEditType = MultiWorldEditType.V7;
        }

        plugin.getLogger().info("Found compatible version of WorldEdit for v" + version);
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

    public static MultiWorldEdit getMultiWorldEdit() {
        return multiWorldEdit;
    }
}
