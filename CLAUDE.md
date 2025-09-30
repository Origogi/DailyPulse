# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## 프로젝트 개요
DailyPulse는 Kotlin Multiplatform (KMP)과 Compose Multiplatform을 사용하는 크로스 플랫폼 프로젝트입니다. Android와 iOS를 타겟으로 하며, Jetpack Compose 기반의 공유 UI를 사용합니다.

**학습 목적**: 이 프로젝트는 Kotlin Multiplatform 기술 스택을 학습하고 실습하기 위해 만들어졌습니다.

## 프로젝트 구조

### 핵심 디렉토리
- **composeApp/src/commonMain**: 모든 플랫폼에서 공유되는 코드
  - Compose UI 컴포넌트, 비즈니스 로직, 데이터 레이어
  - `com.origogi.dailypulse` 패키지 사용
- **composeApp/src/androidMain**: Android 전용 구현
  - MainActivity, Platform.android.kt 등 Android 특화 코드
- **composeApp/src/iosMain**: iOS 전용 구현
  - MainViewController, Platform.ios.kt 등 iOS 특화 코드
- **iosApp**: iOS 네이티브 앱 진입점 (SwiftUI/UIKit 코드)

### 빌드 설정
- Java 11 타겟
- Android minSdk 24, compileSdk/targetSdk 36
- iOS: arm64, SimulatorArm64 타겟
- Version Catalog: gradle/libs.versions.toml에서 의존성 관리

## 주요 명령어

### Android 빌드
```bash
# Debug 빌드
./gradlew :composeApp:assembleDebug

# Release 빌드
./gradlew :composeApp:assembleRelease

# Android 앱 실행 (에뮬레이터 필요)
./gradlew :composeApp:installDebug
```

### 테스트
```bash
# 모든 테스트 실행
./gradlew test

# Common 테스트만 실행
./gradlew :composeApp:cleanTestDebugUnitTest :composeApp:testDebugUnitTest

# Android 테스트
./gradlew :composeApp:testDebugUnitTest
```

### iOS 빌드 및 실행
```bash
# Xcode 프로젝트로 빌드
xcodebuild -project iosApp/iosApp.xcodeproj -scheme iosApp -configuration Debug -destination 'platform=iOS Simulator,name=iPhone 16 Pro' build

# 시뮬레이터에서 실행
xcrun simctl boot "DEVICE_ID"  # 시뮬레이터 부팅
xcrun simctl install "DEVICE_ID" "APP_PATH"  # 앱 설치
xcrun simctl launch "DEVICE_ID" com.origogi.dailypulse.DailyPulse  # 앱 실행
```

또는:
- Xcode에서 `iosApp/iosApp.xcodeproj` 열기
- IDE의 iOS run configuration 사용

### 린트 및 코드 품질
```bash
# Gradle 태스크 확인
./gradlew tasks
```

## 아키텍처 가이드

### 플랫폼별 구현
- `expect`/`actual` 키워드로 플랫폼별 구현 분리
- Platform.kt에서 플랫폼별 기능 추상화

### UI 구현
- Compose Multiplatform 사용 (commonMain)
- Material3 디자인 시스템 적용
- `@Preview` 어노테이션으로 미리보기 지원

### 의존성 추가
- gradle/libs.versions.toml에 버전 정의
- composeApp/build.gradle.kts의 sourceSets에 추가
  - commonMain.dependencies: 공유 코드
  - androidMain.dependencies: Android 전용
  - iosMain.dependencies: iOS 전용

## 개발 시 주의사항
- commonMain에서는 플랫폼 특화 API 직접 사용 불가
- 플랫폼별 기능이 필요하면 expect/actual 패턴 사용
- iOS 프레임워크는 static으로 빌드됨
- 리소스는 composeApp/src/commonMain/composeResources에 배치

## Claude Code 작업 지침
- **iOS 빌드 및 실행 요청 시**: 사용자 피드백을 기다리지 말고 즉시 다음 작업을 수행
  1. 시뮬레이터 상태 확인 (필요시 부팅)
  2. clean build 실행
  3. 앱 설치 및 실행
  4. 완료 후 결과만 간단히 보고