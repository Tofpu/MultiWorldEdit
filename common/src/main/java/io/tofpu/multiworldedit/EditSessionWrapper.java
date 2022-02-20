package io.tofpu.multiworldedit;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.world.World;
import io.tofpu.multiworldedit.meta.OriginalClass;

import java.io.Closeable;

public interface EditSessionWrapper extends OriginalClass<EditSession>, Closeable {}
