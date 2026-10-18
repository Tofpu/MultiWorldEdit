package io.tofpu.multiworldedit;

import com.sk89q.worldedit.regions.Region;
import io.tofpu.multiworldedit.meta.LocationPoint;
import io.tofpu.multiworldedit.meta.OriginalClass;

public interface RegionWrapper extends OriginalClass<Region>, LocationPoint {
    @Override
    default VectorWrapper getOrigin() {
        throw new UnsupportedOperationException("RegionWrapper does not implement " +
                                                "getOrigin");
    }
}
