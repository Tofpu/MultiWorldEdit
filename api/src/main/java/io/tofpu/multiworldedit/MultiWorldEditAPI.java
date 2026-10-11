package io.tofpu.multiworldedit;

import java.util.concurrent.atomic.AtomicReference;

public final class MultiWorldEditAPI {
    private MultiWorldEditAPI() {
        /* This utility class should not be instantiated */
    }

    private static final AtomicReference<MultiWorldEdit> multiWorldEditInstance = new AtomicReference<>();

    public static void init(MultiWorldEdit instance) {
        if (instance == null) {
            throw new IllegalArgumentException("MultiWorldEdit instance cannot be null");
        }

        // compareAndSet atomically updates the value if it is currently null
        if (!multiWorldEditInstance.compareAndSet(null, instance)) {
            throw new IllegalStateException("MultiWorldEditAPI has already been initialized.");
        }
    }

    public static MultiWorldEdit getMultiWorldEdit() {
        return multiWorldEditInstance.get();
    }
}
