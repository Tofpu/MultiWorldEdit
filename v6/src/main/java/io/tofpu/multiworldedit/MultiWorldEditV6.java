package io.tofpu.multiworldedit;

import com.sk89q.jnbt.NBTInputStream;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.Vector;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.SchematicReader;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.world.World;
import com.sk89q.worldedit.world.registry.WorldData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public final class MultiWorldEditV6 implements MultiWorldEdit {
    @Override
    public ClipboardWrapper read(final File file) {
        try {
            final FileInputStream inputStream = new FileInputStream(file);
            return create(new SchematicReader(new NBTInputStream(new GZIPInputStream(inputStream))).read(null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PasteBuilderWrapper create(final Clipboard clipboard, final EditSession session,
            final World world) {
        final WorldData worldData = world.getWorldData();
        return new PasteBuilderWrapperV6(new ClipboardHolder(clipboard, worldData),
                session, worldData);
    }

    @Override
    public EditSessionWrapper create(final World world, final int maxBlocks) {
        return new EditSessionWrapperV6(world, maxBlocks);
    }

    @Override
    public VectorWrapper create(final double x, final double y, final double z) {
        return new VectorWrapperV6(new Vector(x, y, z));
    }

    @Override
    public ClipboardWrapper create(final Clipboard clipboard) {
        return new ClipboardWrapperV6(clipboard);
    }

    @Override
    public RegionWrapper create(final Region region) {
        return new RegionWrapperV6(region);
    }
}
