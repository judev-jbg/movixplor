# Movixplor

A native Android application for browsing and discovering movies, powered by the [TMDB API](https://www.themoviedb.org/). Built with modern Android development practices: Jetpack Compose, clean architecture, and Kotlin Coroutines.

---

## Screenshots

> _Home screen · Search · Movie detail_

![Demos screen](./screenshots/screens.png)

---

## Features

- Browse popular and top-rated movies
- Real-time search with debounce to minimize API calls
- Full movie detail view with poster, synopsis, rating and release info
- Graceful error handling: no connection, timeout, HTTP errors
- Empty state feedback for searches with no results

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose + Material 3 |
| Architecture | Clean Architecture (UI / Domain / Data) |
| DI | Hilt |
| Networking | Retrofit + OkHttp + kotlinx.serialization |
| Image loading | Coil |
| Navigation | Compose Navigation |
| Async | Coroutines + StateFlow |
| Build system | Gradle (Kotlin DSL) + Version Catalog |

---

## Architecture

The project follows a strict layered architecture where dependencies only point inward — each layer is unaware of the layers above it.

```
ui/
  home/          → HomeScreen, HomeViewModel, HomeUiState
  detail/        → DetailScreen, DetailViewModel, DetailUiState
  navigation/    → NavGraph, Routes

domain/
  model/         → Movie (domain model)
  repository/    → MovieRepository (interface)
  usecase/       → GetPopularMoviesUseCase, SearchMoviesUseCase,
                   GetMovieDetailUseCase

data/
  remote/
    api/         → TmdbApi (Retrofit interface)
    dto/         → MovieDto, MovieListResponse
    datasource/  → MovieRemoteDataSource
    mapper/      → MovieDto.toDomain()
  repository/    → MovieRepositoryImpl

di/              → NetworkModule, RepositoryModule
util/            → NetworkError
```

### Data flow

```
User interaction
      ↓
Composable (event)
      ↓
ViewModel (viewModelScope.launch)
      ↓
UseCase
      ↓
Repository
      ↓
RemoteDataSource → Retrofit → TMDB API
      ↓             (suspend — coroutine suspended)
mapper.toDomain()
      ↓
StateFlow update
      ↓
Compose recomposition → UI update
```

---

## Getting Started

### Prerequisites

- Android Studio Hedgehog or later
- JDK 21
- A free [TMDB API key](https://www.themoviedb.org/settings/api)

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/movixplor.git
   ```

2. Create a `local.properties` file in the project root (if it doesn't exist) and add your TMDB API key:
   ```properties
   TMDB_API_KEY=your_api_key_here
   ```
   > `local.properties` is listed in `.gitignore` and will never be committed.

3. Open the project in Android Studio and let Gradle sync.

4. Run the app on an emulator or physical device (API 26+).

---

## Requirements

- Minimum SDK: API 26 (Android 8.0)
- Target SDK: API 35 (Android 15)

---

## API

This app uses the [TMDB API v3](https://developer.themoviedb.org/docs). The following endpoints are consumed:

| Endpoint | Usage |
|---|---|
| `GET /movie/popular` | Home screen — popular movies |
| `GET /movie/top_rated` | Top rated movies |
| `GET /search/movie` | Real-time search |
| `GET /movie/{id}` | Movie detail screen |

---

## License

```
MIT License

Copyright (c) 2026 Judev

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```