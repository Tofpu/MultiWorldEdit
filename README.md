# MultiWorldEdit

**MultiWorldEdit** is a Java abstraction library designed to bridge the breaking API changes between **WorldEdit v6**
and **WorldEdit v7** (and FastAsyncWorldEdit variants) seamlessly. Originally developed to
power [SpeedBridge2](https://github.com/Tofpu/SpeedBridge2), it allows developers to write single-codebase plugins that
operate across legacy and modern Minecraft versions.

For more information, visit our [documentation site](https://docs.tofpu.com/libraries/multiworldedit/overview/) for a comprehensive information about this library.

## Key Features

- **Cross-Version Support:** Target Minecraft `1.8` up through modern versions (`26.2+`).
- **Unified Adapter API:** Avoid maintaining separate reflection hacks or conditional modules for v6 vs. v7.
- **Resource Safe:** Cleanly wraps `EditSession` lifetime semantics across differing API specs.

---

## Quick Example: Pasting a Schematic

The example below demonstrates pasting a schematic asynchronously at target coordinates regardless of the underlying
WorldEdit version running on the server:

```java
// Target world, clipboard, and destination coordinates
final World world = ...;
final Clipboard clipboard = ...;
final int x = 0, y = 64, z = 0;

// Create a version-agnostic EditSession
try (final EditSessionWrapper editSessionWrapper = MultiWorldEdit.getInstance().create(world, -1)) {
    final EditSession editSession = editSessionWrapper.to();

    // Builds a version-agnostic Operation
    final Operation operation = MultiWorldEdit.getInstance()
            .create(clipboard, editSession, world)
            .to(x, y, z)
            .ignoreAirBlocks(true)
            .build();

    Operations.completeLegacy(operation);
} catch (IOException e) {
    throw new IllegalStateException("Failed to complete schematic paste operation", e);
}
```

---

## Contributing

This library was primarily made for my personal projects, so it may not cover every edge case of the WorldEdit API. Contributions, bug reports, and pull requests are always welcome! Feel free to open an issue or submit a PR. :)
