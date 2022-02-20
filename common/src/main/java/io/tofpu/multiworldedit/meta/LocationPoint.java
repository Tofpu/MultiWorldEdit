package io.tofpu.multiworldedit.meta;

import io.tofpu.multiworldedit.VectorWrapper;

public interface LocationPoint {
    VectorWrapper getOrigin();
    VectorWrapper getMinimumPoint();
    VectorWrapper getMaximumPoint();
}
