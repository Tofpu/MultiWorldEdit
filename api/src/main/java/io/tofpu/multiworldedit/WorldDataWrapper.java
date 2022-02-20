package io.tofpu.multiworldedit;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.world.registry.WorldData;

public final class WorldDataWrapper {
    private final WorldData blockData;

    public WorldDataWrapper(final BukkitWorld bukkitWorld) {
        this.blockData = bukkitWorld.getWorldData();
    }

    public WorldData getBlockData() {
        return blockData;
    }
}
