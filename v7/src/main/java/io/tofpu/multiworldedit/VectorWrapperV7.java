package io.tofpu.multiworldedit;

import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.Vector3;

public final class VectorWrapperV7 implements VectorWrapper {
    private final Vector3 vector;

    public VectorWrapperV7(final Vector3 vector) {
        this.vector = vector;
    }

    public VectorWrapperV7(final BlockVector3 vector) {
        this.vector = Vector3.at(vector.getX(), vector.getY(), vector.getZ());
    }

    @Override
    public double getX() {
        return vector.getX();
    }

    @Override
    public double getY() {
        return vector.getY();
    }

    @Override
    public double getZ() {
        return vector.getZ();
    }

    @Override
    public float toYaw() {
        return (float) vector.toYaw();
    }

    @Override
    public float toPitch() {
        return (float) Math.toDegrees(vector.toPitch());
    }

    @Override
    public VectorWrapper add(final double x, final double y, final double z) {
        return new VectorWrapperV7(vector.add(x, y, z));
    }

    @Override
    public VectorWrapper add(final VectorWrapper vectorWrapper) {
        return add(vectorWrapper.getX(), vectorWrapper.getY(), vectorWrapper.getZ());
    }

    @Override
    public VectorWrapper subtract(final double x, final double y, final double z) {
        return new VectorWrapperV7(vector.subtract(x, y, z));
    }

    @Override
    public VectorWrapper subtract(final VectorWrapper vectorWrapper) {
        return subtract(vectorWrapper.getX(), vectorWrapper.getY(), vectorWrapper.getZ());
    }
}
