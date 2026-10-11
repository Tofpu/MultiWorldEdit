package io.tofpu.multiworldedit;

import org.bukkit.plugin.Plugin;

public interface MultiWorldEditAPILoader {
    static MultiWorldEditAPILoader detect() {
        return new DetectMultiWorldEditAPILoader();
    }

    MultiWorldEdit load(final Plugin plugin);
}
