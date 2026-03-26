# Guía de Uso - Clases para Consultas a Base de Datos SQLite

## Descripción

Este proyecto incluye un conjunto completo de clases para realizar consultas a una base de datos SQLite. Las clases están organizadas en los siguientes paquetes:

### Estructura de Clases

#### 1. **database** - Manejo de Conexión
- `DatabaseConnection.java` - Gestiona la conexión a SQLite
- `DatabaseInitializer.java` - Crea las tablas e inserta datos iniciales

#### 2. **model** - Clases de Entidad (POJO)
- `Salud.java` - Modelo para datos de Salud
- `Seguridad.java` - Modelo para datos de Seguridad
- `Educacion.java` - Modelo para datos de Educación

#### 3. **dao** - Data Access Objects
- `SaludDAO.java` - Operaciones CRUD para Salud
- `SeguridadDAO.java` - Operaciones CRUD para Seguridad
- `EducacionDAO.java` - Operaciones CRUD para Educación

#### 4. **util** - Utilidades y Ejemplos
- `DatabaseExample.java` - Ejemplos de uso de los DAOs

---

## Cómo Usar

### Inicializar la Base de Datos

```java
import com.starsolutions.graphicsproject.database.DatabaseInitializer;

// Crear las tablas
DatabaseInitializer.initializeDatabse();

// Insertar datos iniciales (solo una vez)
DatabaseInitializer.insertInitialData();
```

### Consultar Datos de Salud

```java
import com.starsolutions.graphicsproject.dao.SaludDAO;
import com.starsolutions.graphicsproject.model.Salud;
import java.util.List;

SaludDAO saludDAO = new SaludDAO();

// Obtener todos los datos de Salud_Gobierno_Torreon
List<Salud> datos = saludDAO.obtenerSaludGobierno();
for (Salud salud : datos) {
    System.out.println(salud);
}

// Obtener datos por año
List<Salud> datosPorAnio = saludDAO.obtenerSaludGobiernoPorAnio(2023);

// Obtener datos por categoría de población
List<Salud> datosPorCategoria = saludDAO.obtenerSaludGobiernoPorCategoria("Embarazadas");
```

### Consultar Datos de Seguridad

```java
import com.starsolutions.graphicsproject.dao.SeguridadDAO;
import com.starsolutions.graphicsproject.model.Seguridad;
import java.util.List;

SeguridadDAO seguridadDAO = new SeguridadDAO();

// Obtener todos los datos de Seguridad_Gobierno_Torreon
List<Seguridad> datos = seguridadDAO.obtenerSeguridadGobierno();

// Obtener todos los datos de Seguridad_ONG_Torreon
List<Seguridad> datosONG = seguridadDAO.obtenerSeguridadONG();

// Obtener datos por año
List<Seguridad> datosPorAnio = seguridadDAO.obtenerSeguridadGobiernoPorAnio(2023);

// Obtener datos por tipo de delito
List<Seguridad> datosPorDelito = seguridadDAO.obtenerSeguridadGobiernoPorDelito("Violencia familiar");
```

### Consultar Datos de Educación

```java
import com.starsolutions.graphicsproject.dao.EducacionDAO;
import com.starsolutions.graphicsproject.model.Educacion;
import java.util.List;

EducacionDAO educacionDAO = new EducacionDAO();

// Obtener todos los datos de Educacion_Gobierno_Torreon
List<Educacion> datos = educacionDAO.obtenerEducacionGobierno();

// Obtener todos los datos de Educacion_ONG_Torreon
List<Educacion> datosONG = educacionDAO.obtenerEducacionONG();

// Obtener un registro específico por nivel educativo
Educacion primaria = educacionDAO.obtenerEducacionGobiernoPorNivel("Primaria");
System.out.println(primaria);

// Insertar un nuevo registro
Educacion nuevaEducacion = new Educacion(
    "Otro Nivel", 100, 50, 500, 480, 20, 30, 50
);
educacionDAO.insertarEducacionGobierno(nuevaEducacion);

// Actualizar un registro
educacionDAO.actualizarEducacionGobierno(primaria);
```

### Insertar Nuevos Datos

```java
import com.starsolutions.graphicsproject.dao.SaludDAO;
import com.starsolutions.graphicsproject.model.Salud;

SaludDAO saludDAO = new SaludDAO();

// Crear un nuevo objeto
Salud nuevoRegistro = new Salud(2024, "Nueva Categoría", 1000000);

// Insertar en Salud_Gobierno_Torreon
boolean exito = saludDAO.insertarSaludGobierno(nuevoRegistro);

// Insertar en Salud_ONG_Torreon
boolean exito = saludDAO.insertarSaludONG(nuevoRegistro);

if (exito) {
    System.out.println("Registro insertado correctamente");
}
```

---

## Descripción de los DAOs

### SaludDAO
- `obtenerSaludGobierno()` - Obtiene todos los registros de Salud_Gobierno_Torreon
- `obtenerSaludONG()` - Obtiene todos los registros de Salud_ONG_Torreon
- `obtenerSaludGobiernoPorAnio(int anio)` - Filtra por año
- `obtenerSaludONGPorAnio(int anio)` - Filtra por año
- `obtenerSaludGobiernoPorCategoria(String categoria)` - Filtra por categoría
- `insertarSaludGobierno(Salud salud)` - Inserta un nuevo registro
- `insertarSaludONG(Salud salud)` - Inserta un nuevo registro

### SeguridadDAO
- `obtenerSeguridadGobierno()` - Obtiene todos los registros de Seguridad_Gobierno_Torreon
- `obtenerSeguridadONG()` - Obtiene todos los registros de Seguridad_ONG_Torreon
- `obtenerSeguridadGobiernoPorAnio(int anio)` - Filtra por año
- `obtenerSeguridadONGPorAnio(int anio)` - Filtra por año
- `obtenerSeguridadGobiernoPorDelito(String tipoDelito)` - Filtra por tipo de delito
- `insertarSeguridadGobierno(Seguridad seguridad)` - Inserta un nuevo registro
- `insertarSeguridadONG(Seguridad seguridad)` - Inserta un nuevo registro

### EducacionDAO
- `obtenerEducacionGobierno()` - Obtiene todos los registros de Educacion_Gobierno_Torreon
- `obtenerEducacionONG()` - Obtiene todos los registros de Educacion_ONG_Torreon
- `obtenerEducacionGobiernoPorNivel(String nivelEducativo)` - Obtiene un nivel específico
- `obtenerEducacionONGPorNivel(String nivelEducativo)` - Obtiene un nivel específico
- `insertarEducacionGobierno(Educacion educacion)` - Inserta un nuevo registro
- `insertarEducacionONG(Educacion educacion)` - Inserta un nuevo registro
- `actualizarEducacionGobierno(Educacion educacion)` - Actualiza un registro existente

---

## Notas Importantes

1. **Archivo de Base de Datos**: Se usa en el directorio raíz el archivo `GraphicsProyectSQL.db`
2. **Primera Ejecución**: Ejecutar `DatabaseInitializer.initializeDatabse()` para crear las tablas
3. **Datos Iniciales**: Ejecutar `DatabaseInitializer.insertInitialData()` solo una vez
4. **Manejo de Errores**: Todos los DAOs incluyen try-catch para manejar excepciones SQL
5. **Conexión**: Se mantiene una conexión reutilizable para mejor rendimiento

---

## Ejemplo Completo

```java
import com.starsolutions.graphicsproject.database.DatabaseInitializer;
import com.starsolutions.graphicsproject.dao.*;
import com.starsolutions.graphicsproject.model.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // 1. Inicializar BD (solo primera vez)
        DatabaseInitializer.initializeDatabse();
        DatabaseInitializer.insertInitialData();

        // 2. Usar los DAOs
        SaludDAO saludDAO = new SaludDAO();
        List<Salud> salud = saludDAO.obtenerSaludGobierno();
        
        SeguridadDAO seguridadDAO = new SeguridadDAO();
        List<Seguridad> seguridad = seguridadDAO.obtenerSeguridadGobierno();
        
        EducacionDAO educacionDAO = new EducacionDAO();
        List<Educacion> educacion = educacionDAO.obtenerEducacionGobierno();

        // 3. Mostrar resultados
        System.out.println("Total de registros de Salud: " + salud.size());
        System.out.println("Total de registros de Seguridad: " + seguridad.size());
        System.out.println("Total de registros de Educación: " + educacion.size());
    }
}
```

---

## Dependencias Requeridas

- JDK 11+
- SQLite JDBC (ya incluida en el pom.xml)

---

¡Listo! Ahora puedes realizar consultas a tu base de datos SQLite desde tu aplicación JavaFX.
