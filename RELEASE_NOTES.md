# ModList Exporter v1.0.0

## 🎉 Initial Release

ModList Exporter is now ready for production use! This lightweight Fabric mod provides easy mod list export functionality with multiple formats and robust error handling.

## ✨ Features

### Core Functionality
- **Dual Export Formats**: Plain text and Markdown table formats
- **File & Clipboard**: Save to files and copy to clipboard simultaneously
- **Easy Access**: F9 keybind and `/modlist` command
- **No Dependencies**: Works with just Fabric Loader (no Fabric API required)

### User Experience
- **Configurable Behavior**: JSON configuration for auto-export and clipboard format
- **Auto-Export**: Optional automatic export when joining worlds
- **Customizable Keybinding**: Change the default F9 key in Controls menu
- **Client-side Only**: Works in singleplayer and multiplayer

### International Support
- **Community Translations**: English, Spanish, and French included
- **Automatic Language Detection**: Uses your Minecraft language setting
- **Graceful Fallback**: Falls back to English if translation missing
- **Easy Contribution**: Simple process for adding new languages

### Robust Error Handling
- **File I/O Protection**: Handles directory creation and file writing errors
- **Clipboard Fallback**: Hybrid AWT + LWJGL clipboard approach
- **User-Friendly Messages**: Clean error messages without stack traces
- **Non-Critical Operations**: Clipboard failures don't break file export

## 📋 System Requirements

- **Minecraft**: 1.21.1
- **Fabric Loader**: 0.15.0+
- **Java**: 21+
- **Platform**: Windows, macOS, Linux

## 🚀 Installation

1. Download `modlistexporter-1.0.0.jar`
2. Place in your Minecraft `mods` folder
3. Launch Minecraft with Fabric Loader
4. Configuration file will be created automatically

## 📖 Usage

### Quick Start
- Press **F9** to export instantly
- Type `/modlist` in chat for the same functionality
- Check `config/modlistexporter/` for exported files

### Configuration
Edit `config/modlistexporter/config.json`:
```json
{
  "autoExportOnStartup": false,
  "clipboardFormat": "markdown"
}
```

## 🔧 Technical Details

### File Locations
- **Configuration**: `config/modlistexporter/config.json`
- **Plain Text Export**: `config/modlistexporter/modlist.txt`
- **Markdown Export**: `config/modlistexporter/modlist.md`

### Export Formats

#### Plain Text Example
```
Fabric Loader – 0.15.11 (FabricMC)
Minecraft – 1.21.1 (Unknown)
ModList Exporter – 1.0.0 (Stephen Beacham)
```

#### Markdown Example
```markdown
| Name | Version | Author |
|------|---------|--------|
| Fabric Loader | 0.15.11 | FabricMC |
| Minecraft | 1.21.1 | Unknown |
| ModList Exporter | 1.0.0 | Stephen Beacham |
```

## 🌍 Community

### Contributing
- **Translations**: Add new language files
- **Bug Reports**: Use GitHub Issues
- **Feature Requests**: Check ROADMAP.md for planned features

### Support
- **GitHub Issues**: For bug reports and feature requests
- **Documentation**: See README.md for detailed usage
- **Roadmap**: Check ROADMAP.md for future plans

## 🔮 What's Next

See [ROADMAP.md](ROADMAP.md) for detailed future plans including:
- Enhanced export formats (JSON, CSV)
- In-game GUI configuration
- Forge port
- Mod Menu integration
- Server-side commands

## 📄 License

MIT License - see [LICENSE](LICENSE) file for details.

---

**Thank you for using ModList Exporter!** 🎮
