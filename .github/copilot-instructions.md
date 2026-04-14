# Copilot Instructions

## Build and test commands

- Use Java 25+.
- The root build depends on JJazzLab core and plugin artifacts that are **not** built in this repository. Build the main [JJazzLab](https://github.com/jjazzboss/JJazzLab) repo locally first so those artifacts exist in your local Maven repository.
- Full toolkit build: `mvn clean install`
- Faster local iteration: `mvn -PCompileOnly test` or `mvn -PCompileOnly clean install` (`CompileOnly` skips shade, javadoc, and GPG steps).
- Build without bundled plugin dependencies: `mvn -Dexclude.plugins=true clean install`
- Demo module compile: `cd demo && mvn clean compile`
- Demo module run: `cd demo && mvn exec:exec`
- Single-test pattern: `mvn -PCompileOnly -Dtest=YourTestClass test` or `mvn -PCompileOnly -Dtest=YourTestClass#yourMethod test`. There are currently no checked-in tests under `src/test/java`, so this only applies after adding one.

## High-level architecture

- This repository is mainly a **packaging layer** around JJazzLab. The root `pom.xml` pulls in many `org.jjazzlab.core` and `org.jjazzlab.plugins` modules, then uses the Maven Shade plugin to publish a single toolkit JAR with dependencies and merged service descriptors.
- The only production Java code in `src/main/java` is NetBeans-platform compatibility glue:
  - `org.jjazz.toolkit.ToolkitDialogDisplayer`
  - `org.jjazz.toolkit.ToolkitStatusDisplayer`
  These provide minimal `DialogDisplayer` and `StatusDisplayer` services so toolkit consumers can run outside the full JJazzLab desktop app.
- The `demo/` module is the best executable map of the toolkit lifecycle. `ToolkitDemoApp` shows the expected standalone startup sequence:
  1. configure logging/locale
  2. optionally activate FluidSynth
  3. initialize a shared `RhythmDatabase`
  4. load or create a `Song`
  5. modify the chord leadsheet and song structure
  6. create/fix a `MidiMix`
  7. play through `MusicController`
  8. export MIDI, then WAV/MP3 when FluidSynth is available
- Rhythm database setup is not automatic in a standalone app. The demo registers its own `SharedRdbInstanceProvider` backed by `DefaultRhythmDatabaseImpl`; any new standalone entry point that needs rhythms should provide equivalent initialization.

## Key conventions

- Runtime wiring relies on **NetBeans Lookup**. When adding a new lookup-driven service, register it with `@ServiceProvider` rather than manual singleton wiring.
- On JDK 23+, `@ServiceProvider` registration needs annotation processing enabled. The demo `pom.xml` shows the pattern: add `org-openide-util` under `annotationProcessorPaths` when creating a standalone/demo module that declares providers.
- Keep standalone adapters **headless and minimal**. Existing toolkit shims log messages instead of opening UI, and unsupported UI-only paths explicitly throw `UnsupportedOperationException` instead of silently faking behavior.
- Preserve the Maven profile meanings:
  - `CompileOnly` is the fast local-development profile.
  - `exclude.plugins=true` intentionally removes plugin dependencies such as `yamjjazz`, `jjswing`, and `fluidsynthembeddedsynth`.
- For demo or standalone runs that use FluidSynth, keep `--enable-native-access=ALL-UNNAMED` on the Java command line; the demo's `exec-maven-plugin` configuration already does this.
- Demo assets are expected to be project-relative (`demo\JJazzLab-SoundFont.sf2`, `demo\Simple.sng`). The sample app intentionally falls back when they are missing: it can create a new song and can fall back to the Java synth when FluidSynth is unavailable.
