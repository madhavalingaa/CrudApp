# JavaFX CRUD App — Student Records

A simple desktop application built with JavaFX that demonstrates basic **CRUD** operations (Create, Read, Update, Delete) on a list of student records. Data is stored in memory (no database setup required), making this ideal for learning or demo purposes.

## Features

- **Create** — Add new student records (ID, Name, Age)
- **Read** — View all records in a table
- **Update** — Edit an existing record by selecting it and modifying the fields
- **Delete** — Remove a selected record
- Input validation (empty fields, non-numeric age)
- Auto-incrementing student ID

## Tech Stack

| Component | Details |
|---|---|
| Language | Java 17 |
| UI Framework | JavaFX 17.0.2 |
| Build Tool | Maven |
| Data Storage | In-memory (`ObservableList`) — no external database |

## Project Structure

```
CrudApp/
├── pom.xml                                  # Maven build configuration
├── mvnw, mvnw.cmd                           # Maven wrapper scripts
├── .gitignore
└── src/
    └── main/
        ├── java/
        │   └── com/ml/crudapp/
        │       ├── HelloApplication.java    # Main app: UI + CRUD logic + Student model
        │       ├── Launcher.java            # Entry point that launches HelloApplication
        │       └── module-info.java         # Java module declarations
        └── resources/
            └── com/ml/crudapp/              # (unused — no FXML in this version)
```

## Prerequisites

Before running this project, make sure you have:

1. **JDK 17 or higher** installed
   - Check with: `java -version`
   - Download from [adoptium.net](https://adoptium.net/) if needed
2. **Maven** (or just use the included `mvnw` / `mvnw.cmd` wrapper — no separate install needed)
3. **An IDE** (recommended: **IntelliJ IDEA Community Edition**, free)
   - Download: [jetbrains.com/idea/download](https://www.jetbrains.com/idea/download/)

> **Note:** JavaFX is not bundled with the JDK since Java 11. This project's `pom.xml` pulls in JavaFX automatically as a Maven dependency, so you don't need to manually download the JavaFX SDK.

## Setup & Installation

### Option A — Open in IntelliJ IDEA (recommended)

1. Open IntelliJ IDEA
2. `File → Open` → select the `CrudApp` folder
3. IntelliJ will detect the `pom.xml` and prompt to load it as a Maven project — click **Load**
4. Wait for Maven to download dependencies (progress shown at the bottom)
5. In the Project panel, navigate to:
   `src/main/java/com/ml/crudapp/Launcher.java`
6. Right-click `Launcher.java` → **Run 'Launcher.main()'**
7. The app window should open

### Option B — Run from the command line

From the project's root folder (where `pom.xml` is located):

**Windows:**
```
mvnw.cmd clean javafx:run
```

**Mac/Linux:**
```
./mvnw clean javafx:run
```

This uses the `javafx-maven-plugin` (already configured in `pom.xml`) to compile and launch the app directly.

## How to Use the App

1. **Add a record** — Type a Name and Age into the form fields, click **Add**
2. **View records** — All records appear in the table above the form
3. **Update a record** — Click a row in the table (it loads into the form fields), edit Name/Age, click **Update**
4. **Delete a record** — Click a row, then click **Delete**
5. **Clear the form** — Click **Clear** to reset the input fields

## Troubleshooting

| Issue | Fix |
|---|---|
| `modules are not supported in -source 8` | In `pom.xml`, make sure `maven.compiler.source`/`target` are set to `17`, not `8` |
| `cannot find symbol class HelloApplication` | Make sure `HelloApplication.java` has `package com.ml.crudapp;` as its first line |
| `class X is public, should be declared in a file named X.java` | The class name inside the file must exactly match the filename |
| JavaFX runtime components missing | Don't run the compiled `.class` file directly — always run via `Launcher.java` or `mvnw javafx:run`, which correctly loads the JavaFX modules |

## Possible Extensions

- Replace the in-memory list with a real database (SQLite/MySQL) via JDBC
- Add search/filter functionality on the table
- Export records to CSV
- Add form validation feedback directly in the UI (red borders, inline error text)

## License

Free to use for learning and academic purposes.
