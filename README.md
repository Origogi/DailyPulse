# DailyPulse

Kotlin Multiplatform (KMP)과 Compose Multiplatform을 학습하기 위한 크로스 플랫폼 모바일 애플리케이션 프로젝트입니다.

## 📚 학습 목적

이 프로젝트는 다음 기술들을 학습하고 실습하기 위해 만들어졌습니다:

- **Kotlin Multiplatform (KMP)**: Android와 iOS 간 코드 공유
- **Compose Multiplatform**: 선언적 UI 프레임워크를 사용한 크로스 플랫폼 UI 개발
- **공유 비즈니스 로직**: 플랫폼 간 공통 로직 구현
- **플랫폼별 구현**: `expect`/`actual` 패턴을 활용한 플랫폼 특화 기능

## 🎓 레퍼런스 강의

이 프로젝트는 다음 Udemy 강의를 참고하여 진행됩니다:

[Kotlin Multiplatform Masterclass: Build Android & iOS Apps](https://www.udemy.com/course/kotlin-multiplatform-masterclass)

## 🏗️ 프로젝트 구조

```
DailyPulse/
├── composeApp/
│   └── src/
│       ├── commonMain/       # 공유 코드 (Android + iOS)
│       ├── androidMain/      # Android 전용 코드
│       └── iosMain/          # iOS 전용 코드
└── iosApp/                   # iOS 앱 진입점
```

## 🛠️ 기술 스택

- **언어**: Kotlin 2.2.20
- **UI 프레임워크**: Compose Multiplatform 1.9.0
- **Android**: minSdk 24, targetSdk 36
- **iOS**: iOS 18.2+
- **빌드 도구**: Gradle 8.11.2

## 🚀 시작하기

### 필수 조건

- JDK 11 이상
- Android Studio (Android 개발용)
- Xcode (iOS 개발용, macOS만 해당)

### Android 빌드 및 실행

```bash
# Debug 빌드
./gradlew :composeApp:assembleDebug

# 에뮬레이터에 설치 및 실행
./gradlew :composeApp:installDebug
```

### iOS 빌드 및 실행

Xcode에서 `iosApp/iosApp.xcodeproj` 파일을 열고 실행하거나, 명령줄에서:

```bash
xcodebuild -project iosApp/iosApp.xcodeproj \
  -scheme iosApp \
  -configuration Debug \
  -destination 'platform=iOS Simulator,name=iPhone 16 Pro' \
  build
```

## 🧪 테스트

```bash
# 모든 테스트 실행
./gradlew test

# Android 테스트만 실행
./gradlew :composeApp:testDebugUnitTest
```

## 📖 더 알아보기

- [Kotlin Multiplatform 공식 문서](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- [Android 개발 가이드](https://developer.android.com/kotlin)

## 📝 라이선스

이 프로젝트는 학습 목적으로 만들어졌습니다.