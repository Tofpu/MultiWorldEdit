package io.tofpu.multiworldedit;

import io.tofpu.multiworldedit.exception.IncompatibleWorldEditException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

final class DetectMultiWorldEditAPILoader implements MultiWorldEditAPILoader {
    @Override
    public MultiWorldEdit load(Plugin plugin) {
        // example: 1.16.2
        final String serverVersion = plugin.getServer()
                .getBukkitVersion()
                .split("-")[0];
        // example: [1,16,2]
        final String[] versionArgs =
                serverVersion.split("\\.");

        // example: 1
        final int major = Integer.parseInt(versionArgs[0]);
        // example: 16
        final int minor = Integer.parseInt(versionArgs[1]);

        final Plugin worldEditPlugin = Bukkit.getPluginManager()
                .getPlugin("WorldEdit");

        if (worldEditPlugin == null) {
            return null;
        }
        final String worldEditVersion = worldEditPlugin.getDescription().getVersion();
        plugin.getLogger().info("Searching for a compatible version of WorldEdit...");

        if (major == 1 && minor <= 12) {
            if (!worldEditVersion.startsWith("6")) {
                throw IncompatibleWorldEditException.fromVersion(worldEditVersion, serverVersion);
            }

            plugin.getLogger().info("Found compatible version of WorldEdit for v" + serverVersion);
            return new MultiWorldEditV6();
        }

        if (worldEditVersion.startsWith("6")) {
            throw IncompatibleWorldEditException.fromVersion(worldEditVersion, serverVersion);
        }

        plugin.getLogger().info("Found compatible version of WorldEdit for v" + serverVersion);
        return new MultiWorldEditV7();
    }
}
