package io.tofpu.multiworldedit;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.regions.Region;

public final class ClipboardWrapperV7 implements ClipboardWrapper {
    private final Clipboard clipboard;

    public ClipboardWrapperV7(final Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    @Override
    public Clipboard to() {
        return this.clipboard;
    }

    @Override
    public VectorWrapper getOrigin() {
        return new VectorWrapperV7(this.clipboard.getOrigin());
    }

    @Override
    public VectorWrapper getMinimumPoint() {
        return new VectorWrapperV7(this.clipboard.getMinimumPoint());
    }

    @Override
    public VectorWrapper getMaximumPoint() {
        return new VectorWrapperV7(this.clipboard.getMaximumPoint());
    }
}
