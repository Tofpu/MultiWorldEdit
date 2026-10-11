package io.tofpu.multiworldedit.exception;

@SuppressWarnings("unused")
public class MultiWorldEditException extends RuntimeException {
    public MultiWorldEditException() {
    }

    public MultiWorldEditException(String message) {
        super(message);
    }

    public MultiWorldEditException(String message, Throwable cause) {
        super(message, cause);
    }

    public MultiWorldEditException(Throwable cause) {
        super(cause);
    }

    public MultiWorldEditException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
