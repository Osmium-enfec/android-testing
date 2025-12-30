# My Android Assignment

A structured Android project for testing and learning Android development with **Robolectric** for fast, emulator-free unit testing.

## Project Structure

```
my-android-assignment/
├── episodes/
│   ├── Episode01/
│   │   ├── Assessment01/
│   │   │   ├── app/
│   │   │   │   ├── src/
│   │   │   │   │   ├── main/
│   │   │   │   │   │   ├── AndroidManifest.xml
│   │   │   │   │   │   ├── java/com/example/
│   │   │   │   │   │   │   └── MainActivity.kt
│   │   │   │   │   │   └── res/
│   │   │   │   │   │       ├── layout/
│   │   │   │   │   │       │   └── activity_main.xml
│   │   │   │   │   │       └── values/
│   │   │   │   │   │           └── strings.xml
│   │   │   │   │   └── test/
│   │   │   │   │       └── java/com/example/
│   │   │   │   │           └── MainActivityTest.kt
│   │   │   │   └── build.gradle.kts
│   │   │   ├── build.gradle.kts
│   │   │   ├── settings.gradle.kts
│   │   │   └── local.properties
│   │   └── Assessment02/ (coming soon)
│   └── Episode02/ (coming soon)
└── README.md
```

## Key Features

- ✅ **No Emulator Required**: Uses Robolectric for fast testing
- ✅ **Fast Execution**: Tests run in 15-30 seconds (vs 60-120s with emulator)
- ✅ **CI/CD Ready**: Perfect for automated testing pipelines
- ✅ **Student Friendly**: Quick feedback loop for learning
- ✅ **Modern Stack**: Kotlin + AndroidX + Gradle Kotlin DSL

## Setup Instructions

### Prerequisites

- **Android SDK** installed (API 33 or higher)
- **Java 17** or later
- **Gradle** 7.5 or later
- **Kotlin** 1.9.0

### 1. Configure Android SDK Path

Update the SDK path in `local.properties`:

```properties
sdk.dir=/path/to/your/Android/sdk
```

**macOS default:**
```properties
sdk.dir=/Users/YourUsername/Library/Android/sdk
```

**Linux default:**
```properties
sdk.dir=/home/YourUsername/Android/sdk
```

**Windows default:**
```properties
sdk.dir=C:\\Users\\YourUsername\\AppData\\Local\\Android\\sdk
```

### 2. Build the Project

```bash
cd episodes/Episode01/Assessment01
gradle build
```

### 3. Run Tests (Robolectric - NO EMULATOR!)

```bash
gradle testDebug
```

**Output:**
```
BUILD SUCCESSFUL in 15s
8 actionable tasks: 8 executed
```

### 4. View Test Results

Test reports are generated at:
```
app/build/reports/tests/testDebugUnitTest/index.html
```

Open in a browser to view detailed test results.

## Test Framework: Robolectric

### Why Robolectric?

| Feature | Robolectric | Emulator |
|---------|-------------|---------|
| Startup Time | 2-5 sec | 30-60 sec |
| Test Speed | 15-30 sec | 60-120 sec |
| CI/CD | ✅ Excellent | ⚠️ Complex |
| Emulator Need | ❌ No | ✅ Yes |
| Debugging | Limited | ✅ Full |

### Example Test

```kotlin
@RunWith(RobolectricTestRunner::class)
class MainActivityTest {
    
    @Test
    fun testActivityLaunches() {
        val activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .resume()
            .get()
        
        assertNotNull(activity)
    }
}
```

## Gradle Dependencies

### Main Dependencies
- `androidx.appcompat:appcompat:1.6.1` - AppCompat support
- `androidx.constraintlayout:constraintlayout:2.1.4` - Layout system
- `com.google.android.material:material:1.9.0` - Material Design

### Test Dependencies
- `org.robolectric:robolectric:4.10.3` - Robolectric testing framework
- `junit:junit:4.13.2` - JUnit for assertions
- `androidx.test:core:1.5.0` - AndroidX test core
- `androidx.test.ext:junit:1.1.5` - AndroidX JUnit extensions

## Common Gradle Commands

```bash
# Build the app
gradle build

# Run all unit tests (Robolectric)
gradle testDebug

# Run tests with output
gradle testDebug --info

# Clean build
gradle clean build

# Check dependencies
gradle dependencies

# Run specific test class
gradle testDebug --tests "com.example.MainActivityTest"
```

## File Descriptions

| File | Purpose |
|------|---------|
| `AndroidManifest.xml` | App configuration and permissions |
| `MainActivity.kt` | Main activity implementation |
| `activity_main.xml` | UI layout for main activity |
| `strings.xml` | String resources |
| `MainActivityTest.kt` | Robolectric unit tests |
| `app/build.gradle.kts` | App module dependencies and build config |
| `build.gradle.kts` | Root project build configuration |
| `settings.gradle.kts` | Project settings and module configuration |
| `local.properties` | Local SDK/NDK paths |

## Troubleshooting

### Issue: "SDK not found"
**Solution:** Update the Android SDK path in `local.properties`

### Issue: "Gradle sync failed"
**Solution:** 
```bash
gradle clean
gradle --refresh-dependencies
```

### Issue: Tests timeout
**Solution:** Increase timeout in test configuration (Robolectric default is 300s)

### Issue: "Cannot resolve symbol R"
**Solution:** Ensure the project structure matches and run `gradle build`

## Next Steps

1. **Add more assessments** under `Episode01/Assessment02/`
2. **Add more episodes** by creating `Episode02/`
3. **Implement features** in `MainActivity.kt`
4. **Write more tests** in `MainActivityTest.kt`
5. **Use fragments** for more complex UIs
6. **Add database support** with Room or SQLite

## Resources

- [Robolectric Official Docs](http://robolectric.org/)
- [Android Testing Guide](https://developer.android.com/training/testing)
- [JUnit 4 Documentation](https://junit.org/junit4/)
- [Gradle Build Tool](https://gradle.org/)

## License

This project is for educational purposes.

---

**Happy Testing! 🚀**
