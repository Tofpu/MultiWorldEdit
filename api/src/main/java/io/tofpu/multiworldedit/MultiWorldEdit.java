package io.tofpu.multiworldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public interface MultiWorldEdit {

    class Holder {
        private static final AtomicReference<MultiWorldEdit> instance = new AtomicReference<>();
    }

    static MultiWorldEdit getInstance() {
        MultiWorldEdit multiWorldEdit = Holder.instance.get();
        if (multiWorldEdit == null) {
            throw new IllegalStateException("MultiWorldEdit has not been loaded yet! Did you call the loader in onEnable?");
        }
        return multiWorldEdit;
    }

    static void setInstance(MultiWorldEdit instance) {
        if (!Holder.instance.compareAndSet(null, instance)) {
            throw new IllegalStateException("MultiWorldEdit is already initialized!");
        }
    }

    ClipboardWrapper read(final File file);

    PasteBuilderWrapper create(final Clipboard clipboard, final EditSession session,
            final World world);
    EditSessionWrapper create(final World world, final int maxBlocks);
    VectorWrapper create(final double x, final double y, final double z);
    ClipboardWrapper create(final Clipboard clipboard);
    RegionWrapper create(final Region region);

    MultiWorldEditVersion version();
}
