package io.tofpu.multiworldedit;

import com.sk89q.worldedit.regions.Region;

public final class RegionWrapperV7 implements RegionWrapper {
    private final Region region;

    public RegionWrapperV7(final Region region) {
        this.region = region;
    }

    @Override
    public VectorWrapper getMinimumPoint() {
        return new VectorWrapperV7(region.getMinimumPoint());
    }

    @Override
    public VectorWrapper getMaximumPoint() {
        return new VectorWrapperV7(region.getMaximumPoint());
    }

    @Override
    public Region to() {
        return this.region;
    }
}
