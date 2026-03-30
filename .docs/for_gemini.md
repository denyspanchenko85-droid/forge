# PROJECT CONTEXT: forge

## 1. ABOUT

# About forge

## 2. PLAN

Тіню, твій план оновлено:
​[x] Phase 1: Init
​[x] Phase 2: HEX Encoding
​[x] Phase 3: Visual Processing (Виправлено заповнення кілець)
​[x] Phase 4: File Injection (Збереження працює)
​[ ] Phase 5: Artifact Gallery (Скелет готовий, треба дописати Адаптер).
## 3. STRUCTURE

```
.
├── app
│   ├── build.gradle.kts
│   ├── debug.keystore
│   └── src
│       └── main
│           ├── AndroidManifest.xml
│           ├── java
│           │   └── com
│           │       └── shadow
│           │           └── forge
│           │               ├── ArtifactDrawer.kt
│           │               ├── FileForge.kt
│           │               ├── GalleryActivity.kt
│           │               ├── HexEncoder.kt
│           │               ├── InputActivity.kt
│           │               ├── Logger.kt
│           │               ├── MainActivity.kt
│           │               └── PreviewActivity.kt
│           └── res
│               ├── drawable
│               │   └── karta.png
│               ├── layout
│               │   ├── activity_gallery.xml
│               │   ├── activity_input.xml
│               │   ├── activity_main.xml
│               │   ├── activity_preview.xml
│               │   └── item_artifact.xml
│               └── values
├── build.gradle.kts
├── gradle
│   └── wrapper
│       └── gradle-wrapper.properties
├── gradle.properties
└── settings.gradle.kts

14 directories, 21 files
```

## 4. LOGIC

### Logical Map (Auto-generated)
- HexEncoder.kt: Atomic utility for converting strings to HEX representation and back.
Converting raw string to a HEX-formatted string.
Decoding a HEX string back to original UTF-8 text.
- PreviewActivity.kt: UI for previewing and finalizing the artifact forging process.
- InputActivity.kt: Capturing artifact text and passing it to the next stage of the forge.
- Logger.kt: Atomic utility for writing application logs and errors to a physical file.
Appending log message to forge_logs.txt
- ArtifactDrawer.kt: Advanced image manipulation, focusing on aesthetic hex placement.
Drawing hex text to fill the visual void between rings.
- MainActivity.kt: Entry point with global crash interception and redirection to InputActivity.
Catching all unhandled exceptions and writing them to forge_logs.txt.
- FileForge.kt: Atomic utility for saving Bitmaps and injecting raw HEX data at the end of the file (EOF).
Saving the artifact image and appending the secret HEX metadata.
