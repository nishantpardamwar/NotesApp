# 📝 Compose Navigation 3 Showcase (NotesApp)

A modern Android notes application built with Jetpack Compose, showcasing the latest Navigation3 Library Released by Google.

## ✨ Features

- **Create, Edit, Delete Notes** - Full CRUD operations for managing notes
- **Material 3 Design** - Modern UI with Material 3 components
- **Offline Storage** - Local database with Room persistence
- **Smooth Navigation** - Seamless screen transitions with Navigation3
- **Responsive Layout** - Adaptive UI that works across different screen sizes

## 🏗️ Architecture

This project demonstrates modern Android development with the following key technologies:

### 🧭 Navigation3
### 💉 Dependency Injection (Hilt)
### 🏛️ MVVM Architecture
### 🗄️ Room Database

## 📱 Demo

https://github.com/user-attachments/assets/ab4d5f32-607e-451e-86e8-246bdf7ae791

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- Android SDK 24+
- Kotlin 1.9+

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/NotesApp.git
   ```

2. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an existing project"
   - Navigate to the cloned directory and select it

3. **Build and Run**
   - Sync the project with Gradle files
   - Run the app on an emulator or physical device

## 📁 Project Structure

```
app/src/main/java/com/nishantpardamwar/notesapp/
├── database/         # Room database and entities
├── di/               # Dependency injection modules
├── repo/             # Repository layer
├── ui/               # UI components and screens
│   ├── screen/       # Compose screens
│   ├── state/        # UI state classes
│   └── theme/        # App theming
├── vm/               # ViewModels
└── MainActivity.kt   # Main activity entry point
```

## 🔧 Key Components

### Navigation3 Implementation
```kotlin
@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun Navigation(modifier: Modifier) {
    val backStack = rememberNavBackStack<ScreenRoutes>(ScreenRoutes.NotesHome)
    // Navigation3 setup with adaptive components
}
```

### Hilt Dependency Injection
```kotlin
@Module
@InstallIn(SingletonComponent::class)
object AppComponent {
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = 
        Room.databaseBuilder(context, AppDatabase::class.java, "appdb").build()
}
```

### MVVM with StateFlow
```kotlin
@HiltViewModel
class VMNotesHome @Inject constructor(
    private val repo: Repo
) : ViewModel() {
    private val _state = MutableStateFlow(NotesHomeState())
    val state = _state.asStateFlow()
}
```

### Room Database
```kotlin
@Database(entities = [NoteEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
}
```

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Navigation3](https://developer.android.com/guide/navigation/navigation-3)
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- [Room](https://developer.android.com/training/data-storage/room)
- [Material 3](https://m3.material.io/)

---

⭐ **Star this repository if you found it helpful!** 
