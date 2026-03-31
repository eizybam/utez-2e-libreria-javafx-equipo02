# utez-2e-libreria-javafx-equipo02

Aplicacion de escritorio en JavaFX para administrar el catalogo de una biblioteca escolar.

## Descripcion

Este proyecto implementa un sistema de catalogo de libros con operaciones CRUD y persistencia en archivo local.
La meta es que la informacion permanezca disponible aunque la aplicacion se cierre y se vuelva a abrir.

## Tecnologias

- Java
- JavaFX
- Maven (Maven Wrapper)

## Estado del proyecto

Proyecto en etapa inicial.

- Base JavaFX creada
- Estructura inicial del proyecto configurada
- CRUD, persistencia completa y pantallas finales: en desarrollo

## Funcionalidades objetivo

- Registrar libros
- Listar libros en tabla
- Editar libros
- Eliminar libros con confirmacion
- Ver detalle de libro en una vista separada
- Exportar catalogo a reporte CSV

## Validaciones principales

- Campos obligatorios
- Titulo y autor con longitud minima
- Anio numerico en rango valido
- ISBN/ID unico (sin duplicados)

## Persistencia

La aplicacion utilizara archivo local para guardar el catalogo.

- Al iniciar: se carga el archivo de datos
- Al crear, editar o eliminar: se actualiza el archivo
- Se podra exportar un reporte del catalogo actual

## Estructura actual

```text
repository/
  README.md
  library/
    pom.xml
    mvnw
    mvnw.cmd
    src/main/java/
    src/main/resources/
```

## Ejecucion local

Desde la carpeta `library`.

### Windows

```powershell
./mvnw.cmd clean javafx:run
```

### Linux / macOS

```bash
./mvnw clean javafx:run
```

## Proxima estructura de codigo (planeada)

- `model` para entidades (ejemplo: Libro)
- `service` o `repository` para logica CRUD y archivo
- `controller` separado por pantalla
- `resources` para vistas FXML
- `data` para archivos de catalogo/reporte

## Equipo 02

- Alonso Caleb Astudillo Cuevas (eizybam)
- Diego Sánchez Carteño (esfiio)

## Evidencias

Pendiente agregar capturas cuando esten listas:

- Pantalla principal
- Pantalla formulario
- Pantalla detalle
