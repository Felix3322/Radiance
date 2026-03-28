# Repository Guidelines

## Project Structure & Module Organization
- `src/main/java/` — Fabric mod Java sources, mixins, options, UI, and pipeline logic.
- `src/main/resources/` — mod metadata, shaders, packaged DLLs, language files, and module YAMLs.
- `src/main/native/include/` — JNI headers generated from Java native declarations.
- `external/MCVR/` — separate native Vulkan/C++ repository used by this project; it has its **own Git history and remote**.
- `docs/`, `scripts/` — supporting docs and utility scripts.
- `build/`, `external/MCVR/build/`, `*.txt`, `*_digest.*` — generated artifacts/logs; do not rely on them as source of truth.

## Build, Test, and Development Commands
- Fast Java/mixin compile check:
  ```powershell
  $env:JAVA_HOME='C:\Program Files\BellSoft\LibericaJDK-21'
  $env:GRADLE_USER_HOME=(Join-Path $PWD '.gradle-user')
  .\gradlew.bat classes -PradianceAllowIncompleteRuntimePackaging=true
  ```
  Fastest validation for Java sources, mixins, and resource wiring without full packaging.
- Java/JAR build:
  ```powershell
  $env:JAVA_HOME='C:\Program Files\BellSoft\LibericaJDK-21'
  $env:GRADLE_USER_HOME=(Join-Path $PWD '.gradle-user')
  .\gradlew.bat build -PradianceAllowIncompleteRuntimePackaging=true
  ```
  Builds the Fabric JAR and packages Windows runtime assets.
- Test JAR only:
  ```powershell
  $env:JAVA_HOME='C:\Program Files\BellSoft\LibericaJDK-21'
  $env:GRADLE_USER_HOME=(Join-Path $PWD '.gradle-user')
  .\gradlew.bat remapJar -PradianceAllowIncompleteRuntimePackaging=true
  ```
  Produces the distributable test jar in `build/libs/`.
- Client startup / smoke test:
  ```powershell
  $env:JAVA_HOME='C:\Program Files\BellSoft\LibericaJDK-21'
  $env:GRADLE_USER_HOME=(Join-Path $PWD '.gradle-user')
  .\gradlew.bat runClient
  ```
  Use after mixin/render/window/bootstrap changes to verify the game gets past startup and that no new mixin failures appear in `run/logs/latest.log`.
- Native configure/build/install:
  ```powershell
  $cmake='C:\Users\Felix\AppData\Local\Programs\CLion\bin\cmake\win\x64\bin\cmake.exe'
  $env:PATH='C:\VulkanSDK\1.4.341.1\Bin;' + $env:PATH
  & $cmake -S external/MCVR -B external/MCVR/build -G 'Visual Studio 17 2022' -A x64 -DJAVA_PROJECT_ROOT_DIR='C:/Users/Felix/CLionProjects/Radiance'
  & $cmake --build external/MCVR/build --config Release --target install
  ```
  Rebuilds `core.dll` and installs native/shader resources into `src/main/resources/`.
- Native fallback when Visual Studio utility targets misbehave but shader/core targets are valid:
  ```powershell
  $msbuild='C:\Program Files (x86)\Microsoft Visual Studio\2022\BuildTools\MSBuild\Current\Bin\MSBuild.exe'
  & $msbuild external/MCVR/build/src/shader/shaders.vcxproj /t:Build /p:Configuration=Release /p:Platform=x64 /m
  & $msbuild external/MCVR/build/src/core/core.vcxproj /t:Build /p:Configuration=Release /p:Platform=x64 /m
  & $cmake -DBUILD_TYPE=Release -P external/MCVR/build/cmake_install.cmake
  ```
  Useful when `ALL_BUILD` / `INSTALL` succeeds poorly under a polluted Windows environment.

## Coding Style & Naming Conventions
- Java: 4-space indentation, `PascalCase` classes, `camelCase` methods/fields, `UPPER_SNAKE_CASE` constants.
- C++/GLSL: follow existing style in `external/MCVR`; keep braces and spacing consistent with nearby code. Use `external/MCVR/.clang-format` when formatting native code.
- Prefer narrow, surgical changes over broad refactors, especially in render/shader paths.

## Testing Guidelines
- There is currently no dedicated `src/test/` suite. Validation is build-first:
  1. Rebuild `external/MCVR` first if native code or packaged shaders changed.
  2. Run Gradle `classes` as the default quick check after Java, mixin, or resource edits.
  3. Run Gradle `build` when packaging, remapping, bundled runtime assets, or produced jars matter.
  4. Run Gradle `remapJar` before handing a jar to testers; do not assume an older jar in `build/libs/` reflects the current workspace.
  5. Run Gradle `runClient` after mixin, render pipeline, window/bootstrap, or other startup-sensitive changes.
  6. Check `run/logs/latest.log` after `runClient`; if the client crashes hard, also inspect fresh `hs_err_pid*.log`.
  7. If startup hangs before normal client logs continue, capture a thread dump; on this project, Windows `CrashReport.initCrashReport()` can stall inside OSHI/WMI and must not be mistaken for a renderer deadlock.
  8. For mixin regressions, treat “game reached client startup/resource reload” as the minimum smoke-test bar before moving on to later runtime issues.
- In Codex/sandboxed runs, prefer setting `GRADLE_USER_HOME` to a workspace-local `.gradle-user` directory so Gradle does not fail on wrapper lock-file creation outside the workspace.
- `runClient` should keep an explicit heap cap. If it regresses to a machine-sized default heap, the JVM can reserve too much address space, starve native allocations, and crash before world init.
- After native shader changes, verify the exact files expected by `external/MCVR/src/core/render/modules/world/ray_tracing/ray_tracing_module.cpp` are present both under `src/main/resources/shaders/world/ray_tracing/` and `run/radiance/shaders/world/ray_tracing/`.

## Commit & Pull Request Guidelines
- Match existing commit style: `feat(scope): ...`, `fix(scope): ...`, `perf(scope): ...`, `build(scope): ...`.
- Keep subjects imperative and specific (example: `fix(render): validate restored pipelines`).
- PRs should include: summary, affected areas (`Java`, `MCVR`, shaders), build results, runtime validation notes, and screenshots/log snippets for visual changes or crash fixes.

## Repository-Specific Notes
- `external/MCVR` must be committed and pushed separately from the root repo.
- `external/MCVR` may need `git -c safe.directory=...` in sandboxed sessions because ownership differs from the sandbox user; do not confuse that with repository corruption.
- Runtime packaging rule: never silently ship a platform-limited JAR as if it were cross-platform. A normal packaging/build flow must verify that both the Windows runtime (`core.dll` and companion DLLs) and the Linux runtime (`libcore.so`) are present, and must not treat the existence of only one side as “runtime available”.
- If a package is intentionally Windows-only or otherwise incomplete, it must be an explicit opt-in build using `-PradianceAllowIncompleteRuntimePackaging=true`; do not remove or bypass that guard casually.
- When merging runtime assets from an external runtime directory or extracted Windows runtime JAR, preserve the repository’s bundled shader resources instead of replacing/removing `src/main/resources/shaders/**`.
- Do not commit temporary logs, local runtime dumps, or ad-hoc debug files unless they are intentionally part of the change.
