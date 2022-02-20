package io.tofpu.multiworldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.transform.BlockTransformExtent;
import com.sk89q.worldedit.function.mask.ExistingBlockMask;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.Transform;
import com.sk89q.worldedit.session.ClipboardHolder;

import static com.google.common.base.Preconditions.checkNotNull;

public final class PasteBuilderWrapperV7 implements PasteBuilderWrapper {
    private final Clipboard clipboard;
    private final Transform transform;
    private final Extent targetExtent;

    private BlockVector3 to = BlockVector3.ZERO;
    private boolean ignoreAirBlocks;

    PasteBuilderWrapperV7(ClipboardHolder holder, Extent targetExtent) {
        checkNotNull(holder);
        checkNotNull(targetExtent);
        this.clipboard = holder.getClipboard();
        this.transform = holder.getTransform();
        this.targetExtent = targetExtent;
    }

    @Override
    public PasteBuilderWrapper to(final int x, final int y, final int z) {
        this.to = BlockVector3.at(x, y, z);
        return this;
    }

    @Override
    public PasteBuilderWrapper ignoreAirBlocks(final boolean ignoreAirBlocks) {
        this.ignoreAirBlocks = ignoreAirBlocks;
        return this;
    }

    @Override
    public Operation build() {
        BlockTransformExtent extent = new BlockTransformExtent(clipboard, transform);
        ForwardExtentCopy copy = new ForwardExtentCopy(extent, clipboard.getRegion(), clipboard.getOrigin(), targetExtent, to);
        copy.setTransform(transform);
        if (ignoreAirBlocks) {
            copy.setSourceMask(new ExistingBlockMask(clipboard));
        }

        if (targetExtent instanceof EditSession) {
            ((EditSession) targetExtent).flushSession();
        }

        return copy;
    }
}
