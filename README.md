# APK Builder Hub

A mobile-first web interface for preparing website projects for Android APK/AAB builds.

## Current features

- ZIP project upload interface
- App name and package-name configuration
- Version, orientation and minimum SDK settings
- APK and AAB build controls
- Build progress/status UI
- Responsive Android-phone-friendly design

## Important

The frontend is backend-ready but does **not** compile Android binaries inside the browser. A real APK/AAB requires an Android build backend or external build API. The UI deliberately reports this instead of pretending that a build succeeded.

## GitHub Pages

The project is a static `index.html`, so it can be published with GitHub Pages. GitHub supports publishing source from a repository branch through Pages. See the official GitHub documentation for Pages setup.
