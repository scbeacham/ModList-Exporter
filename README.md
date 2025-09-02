# ModList Exporter

A lightweight Minecraft Fabric mod that exports your installed mod list in multiple formats for easy sharing and documentation.

## ✨ Features

- **Multiple Export Formats**: Plain text and Markdown table formats
- **Dual Output**: Save to files and copy to clipboard simultaneously
- **Configurable Behavior**: JSON configuration for auto-export and clipboard format
- **Easy Access**: F9 keybind and `/modlist` command
- **International Support**: Community translations with fallback to English
- **No Dependencies**: Works with just Fabric Loader (no Fabric API required)
- **Robust Error Handling**: Graceful fallbacks and user-friendly error messages

## 🚀 Installation

### Requirements
- **Minecraft**: 1.19.4, 1.20.4, 1.21.1
- **Fabric Loader**: 0.15.0+
- **Java**: 21+

## 🚀 Installation

### Requirements
- **Minecraft**: 1.19.4, 1.20.4, 1.21.1
- **Fabric Loader**: 0.15.0+
- **Java**: 21+

### Steps
1. Download the latest release JAR for your Minecraft version from the [releases page](https://github.com/yourname/ModList-Exportor/releases)
2. Place the JAR file in your Minecraft `mods` folder
3. Launch Minecraft with Fabric Loader
4. The mod will automatically create its configuration file on first run

> **Note**: v1.1.0 introduces multi-version support! Choose the JAR file matching your Minecraft version (e.g., `modlistexporter-fabric-mc1.20.4-1.1.0.jar` for Minecraft 1.20.4).

## 📖 Usage

### Method 1: Keybinding (Recommended)
- **Default Key**: Press **F9** to export instantly
- **Customize**: Go to Options → Controls → ModList Exporter to change the key
- **Functionality**: Creates both files and copies to clipboard using your configured format

### Method 2: Chat Command
- **Command**: Type `/modlist` in chat
- **Functionality**: Same as keybinding - exports to files and clipboard
- **Client-side**: Works in singleplayer and multiplayer (client-side only)

### Auto-Export on Startup
- **Configuration**: Enable in `config/modlistexporter/config.json`
- **Behavior**: Automatically exports when joining a world
- **Delay**: 5-second delay to ensure everything is loaded

## ⚙️ Configuration

The mod creates a configuration file at `config/modlistexporter/config.json`:

```json
{
  "autoExportOnStartup": false,
  "clipboardFormat": "markdown"
}
```

### Options
- **autoExportOnStartup**: `true` to export automatically when joining a world, `false` for manual only
- **clipboardFormat**: `"markdown"` for Markdown table, `"plaintext"` for simple text

### File Locations
- **Configuration**: `config/modlistexporter/config.json`
- **Plain Text Export**: `config/modlistexporter/modlist.txt`
- **Markdown Export**: `config/modlistexporter/modlist.md`

## 📋 Example Output

### Plain Text (`modlist.txt`)
```
Fabric Loader – 0.15.11 (FabricMC)
Minecraft – 1.21.1 (Unknown)
ModList Exporter – 1.1.0 (Stephen Beacham)
```

### Markdown (`modlist.md`)
```markdown
| Name | Version | Author |
|------|---------|--------|
| Fabric Loader | 0.15.11 | FabricMC |
| Minecraft | 1.21.1 | Unknown |
| ModList Exporter | 1.1.0 | Stephen Beacham |
```

## 🌍 Translations

ModList Exporter supports community translations! The mod automatically uses your Minecraft language setting.

### Supported Languages
- **English (en_us)** - Default language
- **Spanish (es_es)** - Complete translation
- **French (fr_fr)** - Complete translation

### Adding Translations
See the [Contributing](#contributing) section below for how to add new languages.

## 🛠️ Development

### Building
```bash
git clone https://github.com/yourname/ModList-Exportor.git
cd ModList-Exportor
./gradlew build
```

## 🛠️ Development

### Building
```bash
git clone https://github.com/yourname/ModList-Exportor.git
cd ModList-Exportor
./gradlew build
```

The project uses a multi-module setup:
- `:common` - Shared code (version-agnostic)
- `:fabric-1.19.4` - Minecraft 1.19.4 support
- `:fabric-1.20.4` - Minecraft 1.20.4 support  
- `:fabric-1.21.1` - Minecraft 1.21.1 support

### Running
```bash
# Run specific version
./gradlew :fabric-1.21.1:runClient
./gradlew :fabric-1.20.4:runClient
./gradlew :fabric-1.19.4:runClient
```

### Testing
```bash
./gradlew test
```

## 🤝 Contributing

We welcome contributions! Here's how you can help:

### Adding Translations
1. Create a new language file in `src/main/resources/assets/modlistexporter/lang/`
2. Use the format: `{language_code}_{country_code}.json` (e.g., `de_de.json`)
3. Copy the structure from `en_us.json` and translate all keys
4. Submit a pull request with your translation

### Reporting Issues
- Use the [Issues](https://github.com/yourname/ModList-Exportor/issues) page
- Include Minecraft version, Fabric Loader version, and error details
- Check existing issues before creating a new one

### Feature Requests
- Open an issue with the "enhancement" label
- Describe the feature and its use case
- Check the [ROADMAP.md](ROADMAP.md) for planned features

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
