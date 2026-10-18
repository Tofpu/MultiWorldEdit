package io.tofpu.multiworldedit;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import io.tofpu.multiworldedit.meta.LocationPoint;
import io.tofpu.multiworldedit.meta.OriginalClass;

public interface ClipboardWrapper extends OriginalClass<Clipboard>, LocationPoint {
    /**
     * @deprecated
     *
     * Use {@link #getRegion} instead for a standardized getter experience. No differences implementation-wise.
     */
    @Deprecated
    default RegionWrapper region()  {
        return getRegion();
    }

    RegionWrapper getRegion();
}
