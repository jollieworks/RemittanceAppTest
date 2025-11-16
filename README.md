# RemittanceApp MVP

A modern Android remittance application MVP that facilitates money transfers from the Netherlands (EUR) to Suriname (SRD) with a mocked flow implementation.

## 📱 Overview

This project demonstrates a production-ready modular architecture for a financial remittance application. The MVP focuses on providing a clean, maintainable codebase using modern Android development best practices and a fully modular Clean Architecture approach.

## 🎯 Purpose

The RemittanceApp enables users to:
- Send money from the Netherlands (EUR) to Suriname (SRD)
- View exchange rates and calculate transfer amounts
- Track remittance history and transaction status
- Manage recipient information

**Note:** This MVP uses mocked data flows for demonstration purposes.

## 🛠️ Tech Stack

### Core Technologies
- **Language:** Kotlin
- **UI Framework:** Jetpack Compose
- **Architecture:** Clean Architecture (multi-module)
- **Dependency Injection:** Hilt
- **Navigation:** Navigation Compose
- **Asynchronous:** Kotlin Coroutines & Flow

### Additional Libraries
- **Networking:** Retrofit / OkHttp
- **JSON Parsing:** Kotlinx Serialization / Moshi
- **Local Storage:** Room / DataStore
- **Testing:** JUnit, Mockk, Turbine

## 📦 Module Structure

The project follows a modular Clean Architecture pattern with clear separation of concerns:

```
RemittanceApp/
├── app/                    # Main application module
│   ├── Application entry point
│   ├── Dependency injection setup (Hilt)
│   └── Main activity & navigation host
│
├── core/                   # Shared core utilities
│   ├── Common utilities and extensions
│   ├── Base classes and interfaces
│   └── Shared UI components & theme
│
├── data/                   # Data layer
│   ├── Repository implementations
│   ├── Data sources (local & remote)
│   └── Data models and mappers
│
├── domain/                 # Business logic layer
│   ├── Use cases
│   ├── Domain models
│   └── Repository interfaces
│
├── network/                # Network layer
│   ├── API service definitions
│   ├── Network models (DTOs)
│   └── Retrofit configuration
│
└── features/               # Feature modules
    ├── transfer/          # Money transfer feature
    ├── history/           # Transaction history feature
    ├── recipients/        # Recipient management feature
    └── auth/              # Authentication feature
```

### Module Dependencies

```
features → domain ← data → network
    ↓        ↓       ↓
         core
    ↓
  app (aggregates all)
```

## 🚀 Getting Started

### Prerequisites

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK 34
- Gradle 8.0+

### Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/jollieworks/RemittanceAppTest.git
   cd RemittanceAppTest
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the cloned repository and select it

3. **Sync Gradle**
   - Android Studio should automatically prompt to sync Gradle
   - If not, click `File → Sync Project with Gradle Files`

4. **Build the project**
   ```bash
   ./gradlew build
   ```

5. **Run the app**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio or use:
   ```bash
   ./gradlew installDebug
   ```

### Configuration

The app uses mocked data by default. To configure API endpoints or other settings:

1. Create a `local.properties` file in the project root (if not exists)
2. Add your configuration:
   ```properties
   api.base.url=https://your-api-endpoint.com/
   ```

## 🏗️ Architecture Overview

### Clean Architecture Layers

1. **Presentation Layer** (features/*)
   - Jetpack Compose UI
   - ViewModels with state management
   - UI state models

2. **Domain Layer** (domain/)
   - Business logic use cases
   - Domain models
   - Repository interfaces

3. **Data Layer** (data/)
   - Repository implementations
   - Data source coordination
   - Data mapping

4. **Network Layer** (network/)
   - API clients
   - Network DTOs
   - Network error handling

### Key Architectural Principles

- **Separation of Concerns:** Each module has a single, well-defined responsibility
- **Dependency Rule:** Dependencies point inward (features → domain ← data)
- **Testability:** Each layer can be tested independently
- **Scalability:** New features can be added as separate modules

## 🧪 Testing

Run tests using:

```bash
# Run all tests
./gradlew test

# Run unit tests
./gradlew testDebugUnitTest

# Run instrumented tests
./gradlew connectedDebugAndroidTest
```

## 📝 Code Style

This project follows the official Kotlin coding conventions. Code formatting is enforced using:

- **ktlint** for Kotlin code style
- **detekt** for static code analysis

Run code quality checks:

```bash
./gradlew ktlintCheck
./gradlew detekt
```

## 🤝 Contributing

1. Create a feature branch from `main`
2. Make your changes following the project's code style
3. Write/update tests as needed
4. Ensure all tests pass and code quality checks pass
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Contact

For questions or support, please open an issue in the GitHub repository.

---

**Note:** This is an MVP (Minimum Viable Product) demonstration project using mocked data flows. It is not intended for production use without proper backend integration and security implementations.
