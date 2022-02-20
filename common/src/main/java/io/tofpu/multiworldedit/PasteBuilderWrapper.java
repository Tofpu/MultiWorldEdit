package io.tofpu.multiworldedit;

import com.sk89q.worldedit.function.operation.Operation;

/**
 * A wrapper of WorldEdit's PasteBuilder class.
 */
public interface PasteBuilderWrapper {
    /**
     * Set the target location.
     *
     * @param x x location
     * @param y y location
     * @param z z location
     * @return this builder instance
     */
    public PasteBuilderWrapper to(final int x, final int y, final int z);

    /**
     * Set whether air blocks in the source are skipped over when pasting.
     *
     * @return this builder instance
     */
    public PasteBuilderWrapper ignoreAirBlocks(boolean ignoreAirBlocks);

    /**
     * Build the operation.
     *
     * @return the operation
     */
    public Operation build();
}
