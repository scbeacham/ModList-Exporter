# ModList Exporter - Release Notes

## v1.1.0 — Compatibility Expansion

**Release Date**: January 2025  
**Minecraft Versions**: 1.19.4, 1.20.4, 1.21.1  
**Fabric Loader**: 0.15.0+

### ✨ New Features

#### Multi-Version Support
- **Minecraft 1.19.4 Support**: Full compatibility with Minecraft 1.19.4
- **Minecraft 1.20.4 Support**: Full compatibility with Minecraft 1.20.4
- **Minecraft 1.21.1 Support**: Maintained compatibility with Minecraft 1.21.1
- **Version-Specific JARs**: Each Minecraft version gets its own optimized build
  - `modlistexporter-fabric-mc1.19.4-1.1.0.jar`
  - `modlistexporter-fabric-mc1.20.4-1.1.0.jar`
  - `modlistexporter-fabric-mc1.21.1-1.1.0.jar`

#### Multi-Module Architecture
- **Shared Code**: `:common` module contains version-agnostic functionality
- **Version Modules**: Separate modules for each Minecraft version
- **Clean Separation**: Fabric-specific code isolated in version modules
- **Maintainability**: Easier to add new Minecraft versions in the future

#### CI/CD Pipeline
- **Automated Builds**: GitHub Actions builds all versions automatically
- **Parallel Processing**: Matrix strategy builds all versions simultaneously
- **Artifact Management**: Automatic upload of all JAR files as GitHub artifacts
- **Quality Assurance**: Common module testing ensures shared code quality

### 🔧 Technical Improvements

#### Build System
- **Gradle Multi-Module**: Clean, organized project structure
- **Version-Specific Mixins**: Optimized injection points for each Minecraft version
- **Dependency Management**: Proper separation of common and version-specific dependencies
- **Build Optimization**: Gradle caching for faster builds

#### Compatibility
- **Zero-Dependency Design**: Still works with just Fabric Loader (no Fabric API)
- **Backward Compatibility**: All existing features and configurations preserved
- **Cross-Version Testing**: Verified functionality across all supported versions
- **Mixin Compatibility**: Ensured mixin injection points work correctly for each version

### 🎯 What's Included

#### Core Features (All Versions)
- **F9 Keybinding**: Instant export with customizable key
- **`/modlist` Command**: Chat command for export functionality
- **Dual Export**: Plain text (`modlist.txt`) and Markdown (`modlist.md`) formats
- **Clipboard Integration**: Automatic copy to clipboard with AWT/LWJGL fallback
- **Configuration System**: JSON-based settings with auto-export options
- **Translation Support**: English, Spanish, and French with fallback

#### File Output
- **Plain Text**: `config/modlistexporter/modlist.txt`
- **Markdown**: `config/modlistexporter/modlist.md`
- **Configuration**: `config/modlistexporter/config.json`

### 📋 Installation

1. **Choose Your Version**: Download the JAR file matching your Minecraft version
2. **Install**: Place the JAR in your `mods` folder
3. **Launch**: Start Minecraft with Fabric Loader
4. **Configure**: The mod creates its configuration file on first run

### 🔄 Migration from v1.0.0

- **Configuration**: Existing `config.json` files are compatible
- **Keybindings**: F9 keybinding and custom settings are preserved
- **Translations**: All language files work with the new version
- **Functionality**: All features work exactly the same as before

### 🐛 Bug Fixes

- **Mixin Compatibility**: Fixed class loading issues in older Minecraft versions
- **Build System**: Resolved Gradle dependency conflicts in multi-module setup
- **Version Detection**: Improved Minecraft version detection and compatibility

### 📈 Performance

- **Build Speed**: Faster builds with Gradle caching and parallel processing
- **Memory Usage**: Optimized memory usage in multi-module setup
- **Startup Time**: Maintained fast startup times across all versions

### 🛠️ Development

#### For Developers
- **Multi-Module Setup**: Clean architecture for adding new Minecraft versions
- **CI/CD Integration**: Automated testing and building
- **Version Management**: Easy version bumping across all modules
- **Testing**: Comprehensive test coverage for shared code

#### Adding New Versions
1. Add new module to `settings.gradle`
2. Create version-specific `build.gradle`
3. Copy and adjust mixin injection points
4. Update GitHub Actions matrix
5. Test functionality

### 🎉 Community

- **Open Source**: Full source code available on GitHub
- **Contributions**: Welcome community contributions and translations
- **Issues**: Report bugs and request features via GitHub Issues
- **Discussions**: Community discussions and feedback welcome

---

## v1.0.0 — Initial Release

**Release Date**: December 2024  
**Minecraft Version**: 1.21.1  
**Fabric Loader**: 0.15.0+

### ✨ Features

- **Dual Export**: Plain text and Markdown formats
- **Clipboard Integration**: Automatic copy to clipboard
- **F9 Keybinding**: Instant export with customizable key
- **`/modlist` Command**: Chat command for export functionality
- **Configuration System**: JSON-based settings with auto-export
- **Translation Support**: English, Spanish, and French
- **Zero Dependencies**: Works with just Fabric Loader

### 🎯 Core Functionality

- Export mod list to `config/modlistexporter/modlist.txt` and `modlist.md`
- Copy formatted output to system clipboard
- Auto-export on startup (configurable)
- Configurable clipboard format (Markdown or plain text)
- Client-side only (works in singleplayer and multiplayer)

---

*For detailed information about each release, see the [README.md](README.md) and [ROADMAP.md](ROADMAP.md) files.*
