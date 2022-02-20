<div align="center">
  <h1>MutliWorldEdit</h2>
  <p>A library that abstracts over WorldEdit's API to bring cross-version compatibility.</p>
</div>

The MultiWorldEdit library as of now only supports WorldEdit's API v6 & v7. It was made for the [SpeedBridge2](https://github.com/Tofpu/SpeedBridge2) project.

<details>
  <summary>Library Details</summary>

### Wrappers
The library current has the following wrappers:
- __VectorWrapper__
- __ClipboardWrapper__
- __RegionWrapper__
- __EditSessionWrapper__
- __PasteBuilderWrapper__

Each wrapper were created for multi-version cross comatibility in mind - here's the reasons for whose are interested:  
- __VectorWrapper__ - The removal of Vector class on v6 version
- __ClipboardWrapper__ - To wrap Clipboard's vector's method (getOrigin, getMinimumPoint, and getMaximumPoint)  
- __RegionWrapper__ - To wrap Clipboard's vector's method (getMinimumPoint, and getMaximumPoint)  
- __EditSessionWrapper__ - EditSession were not closeable on v6 version, but were required to be closed on v7 version for the changes to be reflected  
- __PasteBuilderWrapper__ - Clipboard#createPaste & PasteBuilder#to methods were not consistant between v6, and v7 versions  

</details>

<details>
  <summary>Examples</summary>

  ### Retrieve a clipboard
```java
  // the schematic file
  final File schematicFile = new File("test.schematic");
  // reading the schematic file with our library - for cross-compatibility purposes
  final Clipboard clipboard = WorldEditAPI.getWorldEdit()
                    .read(schematicFile);
```

### Paste a schematic
```java
        // WorldEdit's world
        final World world = ...;
        // The schematic clipboard we'll be pasting below
        final Clipboard clipboard = ...;

        // the location where the schematic should be pasted at
        final int x, y, z;

        // Using the edit session wrapper for cross-compatibility purposes
        // WARNING: you are required to close the session on v7 for the blocks operation to be completed
        try (final EditSessionWrapper editSessionWrapper = WorldEditAPI.getWorldEdit()
                .create(world, -1)) {
            // retrieving our actual session
            final EditSession editSession = editSessionWrapper.to();

            // creating our operation with the library - for cross-compatibility
            final Operation operation = WorldEditAPI.getWorldEdit()
                    .create(clipboard, editSession, world)
                    .to(x, y, z)
                    .ignoreAirBlocks(true)
                    .build();

            // starting the operation process
            Operations.completeLegacy(operation);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
```

### Other
```java
        // our clipboard experiment for the day
        final Clipboard clipboard = ...;
        // wraping our clipboard object with ClipboardWrapper
        final ClipboardWrapper clipboardWrapper = worldEdit.create(clipboard);

        // retrieving the regions's origin with ClipboardWrapper's wrapper
        final VectorWrapper origin = clipboardWrapper.getOrigin();

        // wraping our region object with RegionWrapper
        final RegionWrapper regionWrapper = worldEdit.create(clipboardWrapper.to().getRegion());
        // retrieving the clipboard's maximumpoint with RegionWrapper's wrapper
        final VectorWrapper maximumVector = regionWrapper.getMaximumPoint();
```
</details>

To get started
---
1. Load the library with Maven/Gradle.

```xml
      <repositories>
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
      </repositories>
    
      <dependencies>
        <dependency>
            <groupId>io.tofpu</groupId>
            <artifactId>multiworldedit-api</artifactId>
            <version>1.0.0</version>
        </dependency>
      </dependencies>
```

2. Load the API on onLoad/onEnable via `WorldEditAPI.load(Plugin)` method (It's recommended to softdepend/depend WorldEdit on your plugin.yml).

Contributors
---
This library was mainly made for my personal projects, therefore do not expect this library to cover everything. If you wanted to contribute something, you're very welcome to do so! :)
