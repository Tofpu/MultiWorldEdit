package io.tofpu.multiworldedit;

import com.sk89q.worldedit.Vector;

public final class VectorWrapperV6 implements VectorWrapper {
    private final Vector vector;

    public VectorWrapperV6(final Vector vector) {
        this.vector = vector;
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
    public VectorWrapper add(final double x, final double y, final double z) {
        return new VectorWrapperV6(vector.add(x, y, z));
    }

    @Override
    public VectorWrapper add(final VectorWrapper vectorWrapper) {
        return add(vectorWrapper.getX(), vectorWrapper.getY(), vectorWrapper.getZ());
    }

    @Override
    public VectorWrapper subtract(final double x, final double y, final double z) {
        return new VectorWrapperV6(vector.subtract(x, y, z));
    }

    @Override
    public VectorWrapper subtract(final VectorWrapper vectorWrapper) {
        return subtract(vectorWrapper.getX(), vectorWrapper.getY(), vectorWrapper.getZ());
    }
}
