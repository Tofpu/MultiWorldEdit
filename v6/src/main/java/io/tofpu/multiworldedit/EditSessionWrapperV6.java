package io.tofpu.multiworldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.world.World;

import java.io.IOException;

public final class EditSessionWrapperV6 implements EditSessionWrapper {
    private final EditSession editSession;

    public EditSessionWrapperV6(final World world, final int maxBlocks) {
        this.editSession = WorldEdit.getInstance()
                .getEditSessionFactory()
                .getEditSession(world, maxBlocks);
    }

    @Override
    public void close() throws IOException {
        // EditSession on WorldEdit v6 doesn't need to be closed
    }

    @Override
    public EditSession to() {
        return this.editSession;
    }
}
