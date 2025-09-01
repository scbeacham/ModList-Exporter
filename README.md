# ModList Exporter

A Minecraft Fabric mod for 1.21.x that exports your installed mod list as plain text and markdown, saving to both a file and the clipboard.

## User Guide

### What This Mod Does

ModList Exporter allows you to easily export your Minecraft mod list in multiple formats:
- **Plain Text**: Simple list of mod names and versions
- **Markdown**: Formatted table suitable for sharing on platforms like GitHub, Discord, or forums
- **File Export**: Save the mod list to files in your Minecraft directory
- **Clipboard Copy**: Copy the formatted list directly to your clipboard

### Installation

1. Download the latest release JAR file from the releases page
2. Place the JAR file in your Minecraft `mods` folder
3. Ensure you have Fabric Loader 0.15.0+ installed
4. Launch Minecraft with Fabric

### How to Use

#### Day 2 Features - `/modlist` Command

The mod now includes a client-side `/modlist` command that exports your installed mod list:

1. **In-Game Command**: Type `/modlist` in the chat
2. **Automatic Export**: The mod will:
   - Collect all installed mods via Fabric Loader
   - Format each mod as "Name – Version (Author)" for plain text
   - Create a Markdown table with Name, Version, and Author columns
   - Save plain text to `modlist.txt` in your Minecraft game directory
   - Save Markdown to `modlist.md` in your Minecraft game directory
   - Copy the Markdown table to your system clipboard
   - Send a confirmation message: "✅ Exported mod list to modlist.txt and modlist.md (Markdown copied to clipboard)"

#### Example Output

The `modlist.txt` file will contain entries like:
```
Fabric Loader – 0.15.11 (FabricMC)
Minecraft – 1.21.1 (Unknown)
ModList Exporter – 1.0.0 (Stephen Beacham)
```

The `modlist.md` file will contain a formatted table like:
```markdown
| Name | Version | Author |
|------|---------|--------|
| Fabric Loader | 0.15.11 | FabricMC |
| Minecraft | 1.21.1 | Unknown |
| ModList Exporter | 1.0.0 | Stephen Beacham |
```

#### File Location

The exported files will be saved in your Minecraft game directory at:
- `config/modlistexporter/modlist.txt` (plain text)
- `config/modlistexporter/modlist.md` (Markdown table)

## Developer Guide

### Building the Project

1. Clone the repository:
   ```bash
   git clone https://github.com/yourname/ModList-Exportor.git
   cd ModList-Exportor
   ```

2. Build the project:
   ```bash
   ./gradlew build
   ```

### Running the Mod

To run Minecraft with the mod loaded:

```bash
./gradlew runClient
```

This will:
- Download Minecraft 1.21.1 if not already present
- Download Fabric Loader and API
- Launch Minecraft with the mod loaded
- You should see "Hello World from ModList Exporter!" in the logs

### Testing

The project includes JUnit 5 for unit testing:

```bash
./gradlew test
```

Test classes are located in `src/test/java/com/yourname/modlistexporter/`:
- `ExportFormatterTest.java` - Tests for mod list formatting functionality (plain text and Markdown)
- `ClipboardHelperTest.java` - Tests for clipboard operations

### Project Structure

```
src/
├── main/
│   ├── java/com/yourname/modlistexporter/
│   │   ├── ModListExporter.java          # Main mod entry point
│   │   ├── commands/
│   │   │   └── ExportCommand.java       # /modlist command handler
│   │   ├── mixin/
│   │   │   └── ChatScreenMixin.java     # Chat interception mixin
│   │   └── utils/
│   │       ├── ExportFormatter.java     # Mod list formatting utility (plain text & Markdown)
│   │       └── ClipboardHelper.java    # Clipboard operations utility
│   └── resources/
│       ├── fabric.mod.json              # Mod metadata
│       ├── modlistexporter.mixins.json  # Mixin configuration
│       └── assets/modlistexporter/
│           └── lang/en_us.json          # English translations
└── test/
    └── java/com/yourname/modlistexporter/
        ├── ExportFormatterTest.java     # Formatting tests (plain text & Markdown)
        └── ClipboardHelperTest.java    # Clipboard tests
```

### Dependencies

- **Minecraft**: 1.21.1
- **Fabric Loader**: 0.15.0+
- **Java**: 21+
- **Gradle**: 8.6+

## License

This project is licensed under the MIT License.
