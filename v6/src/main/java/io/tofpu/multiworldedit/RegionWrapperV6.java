package io.tofpu.multiworldedit;

import com.sk89q.worldedit.regions.Region;

public final class RegionWrapperV6 implements RegionWrapper {
    private final Region region;

    public RegionWrapperV6(final Region region) {
        this.region = region;
    }

    @Override
    public VectorWrapper getMinimumPoint() {
        return new VectorWrapperV6(region.getMinimumPoint());
    }

    @Override
    public VectorWrapper getMaximumPoint() {
        return new VectorWrapperV6(region.getMaximumPoint());
    }

    @Override
    public Region to() {
        return this.region;
    }
}
