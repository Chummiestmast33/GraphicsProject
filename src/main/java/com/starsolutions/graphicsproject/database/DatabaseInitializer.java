package com.starsolutions.graphicsproject.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    /**
     * Crea todas las tablas de la base de datos SQLite
     */
    public static void initializeDatabse() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Crear tabla Salud_Gobierno
            stmt.execute("CREATE TABLE IF NOT EXISTS Salud_Gobierno (" +
                    "Anio INT," +
                    "Categoria_Poblacion VARCHAR(100)," +
                    "Dosis_Aplicadas BIGINT" +
                    ")");

            // Crear tabla Salud_ONG
            stmt.execute("CREATE TABLE IF NOT EXISTS Salud_ONG (" +
                    "Anio INT," +
                    "Categoria_Poblacion VARCHAR(100)," +
                    "Dosis_Aplicadas BIGINT" +
                    ")");

            // Crear tabla Seguridad_Gobierno_Coahuila
            stmt.execute("CREATE TABLE IF NOT EXISTS Seguridad_Gobierno_Coahuila (" +
                    "Anio INT," +
                    "Tipo_Delito VARCHAR(100)," +
                    "Casos_Oficiales INT" +
                    ")");

            // Crear tabla Seguridad_Estimacion_ONG
            stmt.execute("CREATE TABLE IF NOT EXISTS Seguridad_Estimacion_ONG (" +
                    "Anio INT," +
                    "Tipo_Delito VARCHAR(100)," +
                    "Casos_Estimados_Reales INT" +
                    ")");

            // Crear tabla Educacion_Gobierno_Torreon
            stmt.execute("CREATE TABLE IF NOT EXISTS Educacion_Gobierno_Torreon (" +
                    "Nivel_Educativo VARCHAR(100)," +
                    "Escuelas_Publicas INT," +
                    "Escuelas_Privadas INT," +
                    "Alumnos_Hombres INT," +
                    "Alumnos_Mujeres INT," +
                    "Docentes_Hombres INT," +
                    "Docentes_Mujeres INT," +
                    "Docentes_Totales INT" +
                    ")");

            // Crear tabla Educacion_ONG_Torreon
            stmt.execute("CREATE TABLE IF NOT EXISTS Educacion_ONG_Torreon (" +
                    "Nivel_Educativo VARCHAR(100)," +
                    "Escuelas_Publicas INT," +
                    "Escuelas_Privadas INT," +
                    "Alumnos_Hombres INT," +
                    "Alumnos_Mujeres INT," +
                    "Docentes_Hombres INT," +
                    "Docentes_Mujeres INT," +
                    "Docentes_Totales INT" +
                    ")");

            System.out.println("Todas las tablas han sido creadas exitosamente.");

        } catch (SQLException e) {
            System.err.println("Error al crear las tablas: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Inserta los datos iniciales en la base de datos
     * (Ejecutar solo una vez)
     */
    public static void insertInitialData() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Insertar datos de Salud_Gobierno
            stmt.execute("INSERT INTO Salud_Gobierno VALUES (2023, 'Embarazadas', 568785)");
            stmt.execute("INSERT INTO Salud_Gobierno VALUES (2023, 'Personal de Salud', 459352)");
            stmt.execute("INSERT INTO Salud_Gobierno VALUES (2023, 'Niños (6-59 meses)', 4899744)");
            stmt.execute("INSERT INTO Salud_Gobierno VALUES (2023, 'Adultos Mayores (60+)', 3911142)");
            stmt.execute("INSERT INTO Salud_Gobierno VALUES (2023, 'Enfermedades Crónicas', 3907482)");

            // Insertar datos de Salud_ONG
            stmt.execute("INSERT INTO Salud_ONG VALUES (2023, 'Embarazadas', 1263914)");
            stmt.execute("INSERT INTO Salud_ONG VALUES (2023, 'Personal de Salud', 1054452)");
            stmt.execute("INSERT INTO Salud_ONG VALUES (2023, 'Niños (6-59 meses)', 4364679)");
            stmt.execute("INSERT INTO Salud_ONG VALUES (2023, 'Adultos Mayores (60+)', 10762883)");
            stmt.execute("INSERT INTO Salud_ONG VALUES (2023, 'Enfermedades Crónicas', 9017637)");

            // Insertar datos de Seguridad_Gobierno_Coahuila
            stmt.execute("INSERT INTO Seguridad_Gobierno_Coahuila VALUES (2023, 'Violencia familiar', 12379)");
            stmt.execute("INSERT INTO Seguridad_Gobierno_Coahuila VALUES (2023, 'Narcomenudeo', 9054)");
            stmt.execute("INSERT INTO Seguridad_Gobierno_Coahuila VALUES (2023, 'Lesiones dolosas', 4251)");
            stmt.execute("INSERT INTO Seguridad_Gobierno_Coahuila VALUES (2023, 'Robo a casa habitación', 1616)");
            stmt.execute("INSERT INTO Seguridad_Gobierno_Coahuila VALUES (2023, 'Robo a negocio', 687)");
            stmt.execute("INSERT INTO Seguridad_Gobierno_Coahuila VALUES (2023, 'Violación', 494)");
            stmt.execute("INSERT INTO Seguridad_Gobierno_Coahuila VALUES (2023, 'Homicidio doloso', 116)");
            stmt.execute("INSERT INTO Seguridad_Gobierno_Coahuila VALUES (2023, 'Extorsión', 30)");

            // Insertar datos de Seguridad_Estimacion_ONG
            stmt.execute("INSERT INTO Seguridad_Estimacion_ONG VALUES (2023, 'Violencia familiar', 150963)");
            stmt.execute("INSERT INTO Seguridad_Estimacion_ONG VALUES (2023, 'Narcomenudeo', 110414)");
            stmt.execute("INSERT INTO Seguridad_Estimacion_ONG VALUES (2023, 'Lesiones dolosas', 51841)");
            stmt.execute("INSERT INTO Seguridad_Estimacion_ONG VALUES (2023, 'Robo a casa habitación', 19707)");
            stmt.execute("INSERT INTO Seguridad_Estimacion_ONG VALUES (2023, 'Robo a negocio', 8378)");
            stmt.execute("INSERT INTO Seguridad_Estimacion_ONG VALUES (2023, 'Violación', 6024)");
            stmt.execute("INSERT INTO Seguridad_Estimacion_ONG VALUES (2023, 'Homicidio doloso', 116)");
            stmt.execute("INSERT INTO Seguridad_Estimacion_ONG VALUES (2023, 'Extorsión', 365)");

            // Insertar datos de Educacion_Gobierno_Torreon
            stmt.execute("INSERT INTO Educacion_Gobierno_Torreon VALUES ('Preescolar', 202, 167, 13845, 13843, 12, 1117, 1129)");
            stmt.execute("INSERT INTO Educacion_Gobierno_Torreon VALUES ('Primaria', 234, 99, 35302, 34037, 631, 1980, 2611)");
            stmt.execute("INSERT INTO Educacion_Gobierno_Torreon VALUES ('Secundaria', 66, 73, 16682, 16411, 779, 1063, 1842)");
            stmt.execute("INSERT INTO Educacion_Gobierno_Torreon VALUES ('Media Superior', 47, 119, 14426, 14736, 1284, 1482, 2766)");
            stmt.execute("INSERT INTO Educacion_Gobierno_Torreon VALUES ('Superior', 22, 42, 18667, 20253, 2466, 1772, 4238)");

            // Insertar datos de Educacion_ONG_Torreon
            stmt.execute("INSERT INTO Educacion_ONG_Torreon VALUES ('Preescolar', 202, 167, 13845, 13843, 14, 1198, 1212)");
            stmt.execute("INSERT INTO Educacion_ONG_Torreon VALUES ('Primaria', 234, 99, 35302, 34037, 640, 1995, 2635)");
            stmt.execute("INSERT INTO Educacion_ONG_Torreon VALUES ('Secundaria', 66, 73, 16682, 16411, 1301, 1556, 2857)");
            stmt.execute("INSERT INTO Educacion_ONG_Torreon VALUES ('Media Superior', 43, 117, 13892, 14290, 1249, 1428, 2677)");
            stmt.execute("INSERT INTO Educacion_ONG_Torreon VALUES ('Superior', 7, 35, 16747, 17929, 1906, 1369, 3275)");

            System.out.println("Datos iniciales insertados exitosamente.");

        } catch (SQLException e) {
            System.err.println("Error al insertar datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
