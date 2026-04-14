# 📱 PostApp

PostApp is a sample Android application built using Modern Android Development (MAD) standards.  
It fetches and manages posts from the JSONPlaceholder API, demonstrating clean architecture, best practices, and scalable UI state management.

---

## ✨ Features

- 📄 **Post Listing**: Displays posts fetched from a REST API  
- ✏️ **Edit Post**: Update title and body via Modal Bottom Sheet  
- ❌ **Swipe to Delete**: Quick local deletion using gesture support  
- 🖼️ **Image Handling**: Optimized image loading with Coil + CircleCrop  
- 🚀 **Modern Splash Screen**: Android 12+ SplashScreen API  
- 📱 **Edge-to-Edge UI**: Fullscreen layout with proper system inset handling  
- 🔄 **State Management**: Handles Loading, Success, and Error states  
- 🧭 **Navigation**: Fully integrated Navigation Component with dialog destinations  

---

## 🛠 Tech Stack & Libraries

- **Language**: Kotlin + Coroutines & Flow  
- **Architecture**: MVVM + Repository Pattern  
- **Navigation**: Single Activity Architecture (Navigation Component)  
- **Dependency Injection**: Hilt  
- **Networking**: Retrofit + OkHttp  
- **Image Loading**: Coil  
- **UI**: Material Design 3, ViewBinding, RecyclerView, BottomSheet  

---

## 🏗 Architecture Overview

The project follows clean architecture and unidirectional data flow:

UI (Fragment)  
↓  
ViewModel (StateFlow)  
↓  
Repository  
↓  
Remote API (Retrofit)  

### Layers

- **UI Layer**
  - Fragments
  - ViewModels
  - UiState (sealed classes)

- **Data Layer**
  - Repository pattern
  - DTO → Domain mapping

- **DI Layer**
  - Hilt modules for dependency management

---

## 🛡️ Notes

- The provided API only supports fetching data (GET).  
- Since there are no endpoints for update or delete operations, these actions are handled locally within the app state.  
- Edit and delete operations are performed on the in-memory list managed by the ViewModel.  
- Images are generated using a stable identifier (`post.id`) instead of adapter position. This prevents image changes when items are removed and ensures a consistent UI experience.  
- The project is designed to be easily extendable for real backend integration (e.g., PUT, DELETE endpoints or Room persistence).  

---

## 🚀 Getting Started

```bash
git clone https://github.com/talhabayhn/PostApp.git
