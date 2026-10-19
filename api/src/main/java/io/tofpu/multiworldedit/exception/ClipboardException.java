package io.tofpu.multiworldedit.exception;

import java.io.File;

public class ClipboardException extends MultiWorldEditException {
    public ClipboardException(String message, Throwable cause) {
        super(message, cause);
    }

    public static ClipboardException fromFile(File file, Throwable cause) {
        return new ClipboardException(
                "Failed to read clipboard from file " + file.getPath(),
                cause
        );
    }
}
