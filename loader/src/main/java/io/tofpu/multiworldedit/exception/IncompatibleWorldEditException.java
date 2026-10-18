package io.tofpu.multiworldedit.exception;

public class IncompatibleWorldEditException extends MultiWorldEditException {
    public IncompatibleWorldEditException(String message) {
        super(message);
    }

    public static IncompatibleWorldEditException fromVersion(
            String worldEditVersion,
            String serverVersion
    ) {
        return new IncompatibleWorldEditException(
                "WorldEdit version %worldEditVersion% is incompatible with server version %serverVersion%."
                        .replace("%worldEditVersion%", worldEditVersion)
                        .replace("%serverVersion%", serverVersion)
        );
    }
}
