package io.tofpu.multiworldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class MultiWorldEditV7 implements MultiWorldEdit {
    @Override
    public Clipboard read(final File file) {
        final ClipboardFormat format = ClipboardFormats.findByFile(file);

        if (format == null) {
            return null;
        }
        try {
            return format.getReader(new FileInputStream(file)).read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PasteBuilderWrapper create(final Clipboard clipboard, final EditSession session, final World world) {
        return new PasteBuilderWrapperV7(new ClipboardHolder(clipboard), session);
    }

    @Override
    public EditSessionWrapper create(final World world, final int maxBlocks) {
        return new EditSessionWrapperV7(world);
    }

    @Override
    public VectorWrapper create(final double x, final double y, final double z) {
        return new VectorWrapperV7(Vector3.at(x, y, z));
    }

    @Override
    public ClipboardWrapper create(final Clipboard clipboard) {
        return new ClipboardWrapperV7(clipboard);
    }

    @Override
    public RegionWrapper create(final Region region) {
        return new RegionWrapperV7(region);
    }
}
