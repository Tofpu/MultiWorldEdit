<div align="center">
  <h1>MultiWorldEdit</h1>
  <p>A lightweight library that abstracts over WorldEdit's API to bring cross-version compatibility for v6 and v7.</p>
</div>

---

## About

**MultiWorldEdit** is a utility library designed to handle API differences between WorldEdit v6 and v7 seamlessly. It was originally built to power the [SpeedBridge2](https://github.com/Tofpu/SpeedBridge2) project.

---

## Installation

Add JitPack to your repository and include the dependency in your `pom.xml` (or equivalent Gradle configuration):

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>[https://jitpack.io](https://jitpack.io)</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.Tofpu.MultiWorldEdit</groupId>
        <artifactId>multiworldedit-api</artifactId>
        <version>[VERSION]</version>
    </dependency>
</dependencies>

```

---

## Quick Start

Initialize the library inside your plugin's `onLoad` or `onEnable` method (it is recommended to add WorldEdit as a `softdepend` or `depend` in your `plugin.yml`):

```java
MultiWorldEditLoader.load(this);
```

---

### Available Wrappers

The library includes several wrappers created with cross-version compatibility in mind:

* **`VectorWrapper`**: Handles the removal/changes of the `Vector` class in WorldEdit v6/v7.
* **`ClipboardWrapper`**: Abstracts clipboard vector methods (`getOrigin`, `getMinimumPoint`, `getMaximumPoint`, `getRegion`).
* **`RegionWrapper`**: Standardizes region bounds retrieval methods across versions.
* **`EditSessionWrapper`**: Manages session closing differences (sessions were not closeable in v6, but are required to be closed in v7 for block changes to reflect).
* **`PasteBuilderWrapper`**: Standardizes paste builder operations and execution methods across WorldEdit v6 and v7.

---

### 1. Retrieve a Clipboard

```java
// Read the schematic file using the library for cross-compatibility
final File schematicFile = new File("test.schematic");
final ClipboardWrapper clipboard = MultiWorldEdit.getInstance().read(schematicFile);
```

### 2. Paste a Schematic

```java
// Target world, clipboard, and target coordinates
final World world = ...;
final Clipboard clipboard = ...;
final int x = 0, y = 64, z = 0;

// Using EditSessionWrapper (WARNING: Must be closed on v7 for block operations to complete)
try (final EditSessionWrapper editSessionWrapper = MultiWorldEdit.getInstance()
        .create(world, -1)) {
    final EditSession editSession = editSessionWrapper.to();

    final Operation operation = MultiWorldEdit.getInstance()
            .create(clipboard, editSession, world)
            .to(x, y, z)
            .ignoreAirBlocks(true)
            .build();

    Operations.completeLegacy(operation);
} catch (IOException e) {
    throw new IllegalStateException(e);
}
```

### 3. Working with Wrappers

```java
final Clipboard clipboard = ...;
final ClipboardWrapper clipboardWrapper = MultiWorldEdit.getInstance().create(clipboard);

// Retrieve the origin vector
final VectorWrapper origin = clipboardWrapper.getOrigin();

// Retrieve region boundaries safely
final RegionWrapper regionWrapper = MultiWorldEdit.getInstance().create(clipboardWrapper.to().getRegion());
final VectorWrapper maximumVector = regionWrapper.getMaximumPoint();
```

---

## Contributing

This library was primarily made for my personal projects, so it may not cover every edge case of the WorldEdit API. Contributions, bug reports, and pull requests are always welcome! Feel free to open an issue or submit a PR. :)