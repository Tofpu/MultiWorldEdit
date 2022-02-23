package io.tofpu.multiworldedit;

public interface VectorWrapper {
    double getX();
    double getY();
    double getZ();
    float toYaw();
    float toPitch();

    VectorWrapper add(double x, double y, double z);
    VectorWrapper add(final VectorWrapper vectorWrapper);

    VectorWrapper subtract(double x, double y, double z);
    VectorWrapper subtract(final VectorWrapper vectorWrapper);
}
