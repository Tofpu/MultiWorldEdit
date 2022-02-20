package io.tofpu.multiworldedit;

import com.sk89q.worldedit.extent.clipboard.Clipboard;

public final class ClipboardWrapperV6 implements ClipboardWrapper {
    private final Clipboard clipboard;

    public ClipboardWrapperV6(final Clipboard clipboard) {
        this.clipboard = clipboard;
    }

    @Override
    public Clipboard to() {
        return this.clipboard;
    }

    @Override
    public VectorWrapper getOrigin() {
        return new VectorWrapperV6(this.clipboard.getOrigin());
    }

    @Override
    public VectorWrapper getMinimumPoint() {
        return new VectorWrapperV6(this.clipboard.getMinimumPoint());
    }

    @Override
    public VectorWrapper getMaximumPoint() {
        return new VectorWrapperV6(this.clipboard.getMaximumPoint());
    }
}
