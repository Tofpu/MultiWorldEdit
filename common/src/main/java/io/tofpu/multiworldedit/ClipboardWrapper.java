package io.tofpu.multiworldedit;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import io.tofpu.multiworldedit.meta.LocationPoint;
import io.tofpu.multiworldedit.meta.OriginalClass;

public interface ClipboardWrapper extends OriginalClass<Clipboard>, LocationPoint {
    RegionWrapper region();
}
