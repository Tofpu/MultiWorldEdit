package io.tofpu.multiworldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.world.World;

import java.io.IOException;

public final class EditSessionWrapperV7 implements EditSessionWrapper {
    private final EditSession editSession;

    public EditSessionWrapperV7(final World world) {
        this.editSession = WorldEdit.getInstance()
                .newEditSession(world);
    }

    @Override
    public EditSession to() {
        return this.editSession;
    }

    @Override
    public void close() throws IOException {
        editSession.flushSession();
        editSession.close();
    }
}
