# APK Builder Hub

A mobile-first website for preparing web projects as Android apps, with a real GitHub Actions Android build pipeline.

## Included

- Mobile-friendly APK Builder web interface
- ZIP project configuration UI
- Android WebView wrapper project
- GitHub Actions workflow that builds a debug APK and release AAB
- Workflow artifacts for downloading the generated files
- Manual workflow input for the website URL and version

## Build the Android app

Open the repository's **Actions** tab and run **Build Android APK and AAB** manually. You can provide a website URL and version, or use the defaults.

The workflow uses a GitHub-hosted Linux runner and Gradle to compile the Android project. GitHub Actions supports workflow files under `.github/workflows`, and build outputs can be stored as workflow artifacts. See the official GitHub Actions documentation for details.

## Important limitation

The current browser page can configure a build, but it cannot safely send an arbitrary uploaded ZIP directly to GitHub Actions without an authenticated backend. The included workflow therefore builds the Android wrapper around the configured website URL. A future backend can connect the page's Build buttons to the workflow/API for per-user ZIP builds.

## Play Store

The generated AAB is a build artifact, not a Play Store-ready signed release. For Play Store publishing, configure a secure Android signing key and signing secrets in GitHub Actions before distributing the release bundle.
