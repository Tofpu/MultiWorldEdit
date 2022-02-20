package io.tofpu.multiworldedit;

import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.extent.Extent;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.transform.BlockTransformExtent;
import com.sk89q.worldedit.function.mask.ExistingBlockMask;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.math.transform.Transform;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.registry.WorldData;

import static com.google.common.base.Preconditions.checkNotNull;

public final class PasteBuilderWrapperV6 implements PasteBuilderWrapper {
    private final Clipboard clipboard;
    private final WorldData worldData;
    private final Transform transform;
    private final Extent targetExtent;
    private final WorldData targetWorldData;

    private Vector to = new Vector();
    private boolean ignoreAirBlocks;

    PasteBuilderWrapperV6(ClipboardHolder holder, Extent targetExtent, WorldData targetWorldData) {
        checkNotNull(holder);
        checkNotNull(targetExtent);
        checkNotNull(targetWorldData);
        this.clipboard = holder.getClipboard();
        this.worldData = holder.getWorldData();
        this.transform = holder.getTransform();
        this.targetExtent = targetExtent;
        this.targetWorldData = targetWorldData;
    }

    @Override
    public PasteBuilderWrapper to(final int x, final int y, final int z) {
        this.to = new Vector(x, y, z);
        return this;
    }

    @Override
    public PasteBuilderWrapper ignoreAirBlocks(final boolean ignoreAirBlocks) {
        this.ignoreAirBlocks = ignoreAirBlocks;
        return this;
    }

    @Override
    public Operation build() {
        BlockTransformExtent extent = new BlockTransformExtent(clipboard, transform, targetWorldData.getBlockRegistry());
        ForwardExtentCopy copy = new ForwardExtentCopy(extent, clipboard.getRegion(), clipboard.getOrigin(), targetExtent, to);
        copy.setTransform(transform);
        if (ignoreAirBlocks) {
            copy.setSourceMask(new ExistingBlockMask(clipboard));
        }
        return copy;
    }
}
