# UTEZ Integrative Project - JavaFX Library Catalog (Team 02)

Desktop CRUD application built with JavaFX to manage a school library catalog with local file persistence.

## Table of Contents

- [English Version](#english-version)
- [Versión en Español](#version-en-espanol)

---

## English Version

### 1. Project Overview

This project implements a **Library Catalog Management System** for school use.
It supports full CRUD operations for books and persists data in a local CSV file to keep records between executions.

### 2. Objective

Develop a JavaFX desktop CRUD application with:

- Local file persistence
- Basic OOP structure (model, repository/service, controllers)
- Input validations
- Error handling with user feedback

### 3. Core Features

#### Mandatory CRUD

- **Create**: Register a book using a form.
- **Read**: Display all books in a table.
- **Update**: Edit a selected book.
- **Delete**: Remove a selected book with confirmation.

#### Mandatory Persistence

- Data is stored in a local file: `books.csv`.
- On startup, data is loaded from `books.csv`.
- After create/update/delete operations, data is saved back to `books.csv`.

#### Mandatory Validations

- Required fields are validated.
- Title: minimum 3 characters.
- Author: minimum 3 characters.
- Year: numeric and logical range (1500-2026).
- Duplicate prevention by ISBN.

#### Extra Features

- **Book Details View**: shows complete information in a separate window.
- **Export Report**: exports current catalog to a CSV file chosen by the user.

### 4. UI Screens

#### Main Screen

- TableView columns:
  - ISBN
  - Title
  - Author
  - Year
  - Genre
  - Available
- Actions:
  - Add Book
  - Update
  - Delete
  - View Details
  - Export File

#### Form Screen

- Fields for create/edit:
  - ISBN
  - Title
  - Author
  - Year
  - Genre
  - Available
- Buttons:
  - Save
  - Cancel

#### Details Screen

- Displays full selected book information
- Exit button to return

### 5. Project Structure

```text
utez-2e-libreria-javafx-equipo02/
  books.csv
  pom.xml
  mvnw
  mvnw.cmd
  src/
    main/
      java/
        com/resources/library/
          Application.java
          Launcher.java
          controllers/
          models/
          repositories/
          services/
      resources/
        com/resources/library/views/
```

### 6. Technology Stack

- Java 21
- JavaFX 21
- Maven (Wrapper included)

### 7. How to Run

Run from the repository root (where `pom.xml` is located).

#### Windows

```powershell
./mvnw.cmd clean javafx:run
```

#### Linux/macOS

```bash
./mvnw clean javafx:run
```

### 8. Persistence and Report Details

#### Persistence (`books.csv`)

- The repository loads books from `books.csv` at startup.
- Every change in catalog data triggers a full save to `books.csv`.
- This guarantees that data remains available after restarting the app.

#### Exported Report

- Export is available from the main screen button **Export File**.
- The user selects destination and filename using a file chooser.
- The app generates a CSV report with all current books.

### 9. Sample Data

The repository includes `books.csv` with 9 test books.

### 10. Team

- Alonso Caleb Astudillo Cuevas (eizybam)
- Diego Sanchez Carteno (esfiio)

### 11. Branching Model (Git Flow)

- Base branches:
  - `main`
  - `dev`
- Personal branches:
  - `<git-user>/nombre-apellido`
- Integration flow:
  1. Personal branch -> `dev`
  2. Validate in `dev`
  3. `dev` -> `main` for release

---

## Versión en Español

### 1. Descripción general

Este proyecto implementa un **Sistema de Catálogo de Libros** para una biblioteca escolar.
Incluye CRUD completo y persistencia local en archivo CSV para conservar informacion entre ejecuciones.

### 2. Objetivo

Desarrollar una aplicacion de escritorio en JavaFX con:

- Persistencia en archivo local
- Estructura POO basica (modelo, repositorio/servicio, controladores)
- Validaciones de entradas
- Manejo de errores con retroalimentación al usuario

### 3. Funcionalidades principales

#### CRUD obligatorio

- **Alta**: registrar libro por formulario.
- **Consulta**: mostrar catalogo en tabla.
- **Actualización**: editar libro seleccionado.
- **Eliminación**: borrar libro con confirmacion.

#### Persistencia obligatoria

- Los datos se guardan en `books.csv`.
- Al iniciar la app se cargan los datos desde `books.csv`.
- Al agregar/editar/eliminar se actualiza el archivo.

#### Validaciones obligatorias

- Se validan campos requeridos.
- Título: mínimo 3 caracteres.
- Autor: mínimo 3 caracteres.
- Año: numérico y rango lógico (1500-2026).
- Evita duplicados por ISBN.

#### Funcionalidad extra

- **Pantalla de detalle** en una vista separada.
- **Exportación de reporte** del catálogo actual a CSV.

### 4. Pantallas

#### Pantalla principal

- Tabla con columnas:
  - ISBN
  - Título
  - Autor
  - Año
  - Género
  - Disponible
- Botones:
  - Add Book
  - Update
  - Delete
  - View Details
  - Export File

#### Pantalla de formulario

- Campos para alta/edición:
  - ISBN
  - Titulo
  - Autor
  - Año
  - Genero
  - Disponible
- Botones:
  - Save
  - Cancel

#### Pantalla de detalle

- Muestra información completa del libro seleccionado
- Botón para regresar/salir

### 5. Estructura del proyecto

```text
utez-2e-libreria-javafx-equipo02/
  books.csv
  pom.xml
  mvnw
  mvnw.cmd
  src/
    main/
      java/
        com/resources/library/
          Application.java
          Launcher.java
          controllers/
          models/
          repositories/
          services/
      resources/
        com/resources/library/views/
```

### 6. Tecnologías

- Java 21
- JavaFX 21
- Maven (con Wrapper)

### 7. Ejecución

Ejecutar desde la raíz del repositorio (donde esta `pom.xml`).

#### Windows

```powershell
./mvnw.cmd clean javafx:run
```

#### Linux/macOS

```bash
./mvnw clean javafx:run
```

### 8. Persistencia y reporte exportado

#### Persistencia (`books.csv`)

- El repositorio carga libros desde `books.csv` al iniciar.
- Cada cambio en el catálogo guarda nuevamente la lista completa en `books.csv`.
- Esto asegura que la información se mantenga al cerrar y abrir la app.

#### Reporte exportado

- Se genera desde el botón **Export File** en la vista principal.
- El usuario elige ruta y nombre del archivo con un selector.
- Se exporta un CSV con todos los libros actuales del catálogo.

### 9. Datos de prueba

El proyecto incluye `books.csv` con 9 libros de prueba.

### 10. Equipo

- Alonso Caleb Astudillo Cuevas (eizybam)
- Diego Sanchez Carteno (esfiio)

### 11. Flujo de ramas (Git Flow)

- Ramas base:
  - `main`
  - `dev`
- Ramas personales:
  - `<git-user>/nombre-apellido`
- Flujo:
  1. Rama personal -> `dev`
  2. Pruebas en `dev`
  3. `dev` -> `main` para entrega

