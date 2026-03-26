package com.starsolutions.graphicsproject.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DatabaseInitializer {

    /**
     * Crea todas las tablas de la base de datos SQLite
     */
    public static void initializeDatabse() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            // Salud Gobierno
            stmt.execute("CREATE TABLE IF NOT EXISTS Salud_Gobierno_Nacional (" +
                "Anio INT," +
                "Categoria_Poblacion VARCHAR(100)," +
                "Dosis_Aplicadas BIGINT" +
                ")");

            stmt.execute("CREATE TABLE IF NOT EXISTS Salud_Gobierno_Coahuila (" +
                "Anio INT," +
                "Categoria_Poblacion VARCHAR(100)," +
                "Dosis_Aplicadas BIGINT" +
                ")");

            // Crear tabla Salud_Gobierno_Torreon
            stmt.execute("CREATE TABLE IF NOT EXISTS Salud_Gobierno_Torreon (" +
                    "Anio INT," +
                    "Categoria_Poblacion VARCHAR(100)," +
                    "Dosis_Aplicadas BIGINT" +
                    ")");

            // Salud ONG
            stmt.execute("CREATE TABLE IF NOT EXISTS Salud_ONG_Nacional (" +
                "Anio INT," +
                "Categoria_Poblacion VARCHAR(100)," +
                "Dosis_Aplicadas BIGINT" +
                ")");

            stmt.execute("CREATE TABLE IF NOT EXISTS Salud_ONG_Coahuila (" +
                "Anio INT," +
                "Categoria_Poblacion VARCHAR(100)," +
                "Dosis_Aplicadas BIGINT" +
                ")");

            // Crear tabla Salud_ONG_Torreon
            stmt.execute("CREATE TABLE IF NOT EXISTS Salud_ONG_Torreon (" +
                    "Anio INT," +
                    "Categoria_Poblacion VARCHAR(100)," +
                    "Dosis_Aplicadas BIGINT" +
                    ")");

            // Seguridad Gobierno
            stmt.execute("CREATE TABLE IF NOT EXISTS Seguridad_Gobierno_Nacional (" +
                "Anio INT," +
                "Tipo_Delito VARCHAR(100)," +
                "Casos_Oficiales INT" +
                ")");

            stmt.execute("CREATE TABLE IF NOT EXISTS Seguridad_Gobierno_Coahuila (" +
                "Anio INT," +
                "Tipo_Delito VARCHAR(100)," +
                "Casos_Oficiales INT" +
                ")");

            // Crear tabla Seguridad_Gobierno_Torreon
            stmt.execute("CREATE TABLE IF NOT EXISTS Seguridad_Gobierno_Torreon (" +
                    "Anio INT," +
                    "Tipo_Delito VARCHAR(100)," +
                    "Casos_Oficiales INT" +
                    ")");

            // Seguridad ONG
            stmt.execute("CREATE TABLE IF NOT EXISTS Seguridad_ONG_Nacional (" +
                "Anio INT," +
                "Tipo_Delito VARCHAR(100)," +
                "Casos_Estimados_Reales INT" +
                ")");

            stmt.execute("CREATE TABLE IF NOT EXISTS Seguridad_ONG_Coahuila (" +
                "Anio INT," +
                "Tipo_Delito VARCHAR(100)," +
                "Casos_Estimados_Reales INT" +
                ")");

            // Crear tabla Seguridad_ONG_Torreon
            stmt.execute("CREATE TABLE IF NOT EXISTS Seguridad_ONG_Torreon (" +
                    "Anio INT," +
                    "Tipo_Delito VARCHAR(100)," +
                    "Casos_Estimados_Reales INT" +
                    ")");

            // Educacion Gobierno
            stmt.execute("CREATE TABLE IF NOT EXISTS Educacion_Gobierno_Nacional (" +
                "Nivel_Educativo VARCHAR(100)," +
                "Escuelas_Publicas INT," +
                "Escuelas_Privadas INT," +
                "Alumnos_Hombres INT," +
                "Alumnos_Mujeres INT," +
                "Docentes_Hombres INT," +
                "Docentes_Mujeres INT," +
                "Docentes_Totales INT" +
                ")");

            stmt.execute("CREATE TABLE IF NOT EXISTS Educacion_Gobierno_Coahuila (" +
                "Nivel_Educativo VARCHAR(100)," +
                "Escuelas_Publicas INT," +
                "Escuelas_Privadas INT," +
                "Alumnos_Hombres INT," +
                "Alumnos_Mujeres INT," +
                "Docentes_Hombres INT," +
                "Docentes_Mujeres INT," +
                "Docentes_Totales INT" +
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

                    // Educacion ONG
                    stmt.execute("CREATE TABLE IF NOT EXISTS Educacion_ONG_Nacional (" +
                        "Nivel_Educativo VARCHAR(100)," +
                        "Escuelas_Publicas INT," +
                        "Escuelas_Privadas INT," +
                        "Alumnos_Hombres INT," +
                        "Alumnos_Mujeres INT," +
                        "Docentes_Hombres INT," +
                        "Docentes_Mujeres INT," +
                        "Docentes_Totales INT" +
                        ")");

                    stmt.execute("CREATE TABLE IF NOT EXISTS Educacion_ONG_Coahuila (" +
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
        List<String> inserts = leerInsertsDesdeScript("GraphicsProyectSQL.sql");
        if (inserts.isEmpty()) {
            System.out.println("No se encontraron INSERT en el script. No se insertaron datos iniciales.");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false);
            for (String insert : inserts) {
                stmt.execute(insert);
            }
            conn.commit();
            System.out.println("Datos iniciales insertados exitosamente desde el script SQL.");
        } catch (SQLException e) {
            System.err.println("Error al insertar datos desde script: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static List<String> leerInsertsDesdeScript(String scriptFileName) {
        List<String> inserts = new ArrayList<>();
        Path scriptPath = Path.of(System.getProperty("user.dir"), scriptFileName);
        if (!Files.exists(scriptPath)) {
            System.err.println("No se encontró el script SQL en: " + scriptPath);
            return inserts;
        }

        StringBuilder statement = new StringBuilder();
        try {
            for (String line : Files.readAllLines(scriptPath)) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || trimmed.startsWith("--")) {
                    continue;
                }

                statement.append(trimmed).append(' ');
                if (!trimmed.endsWith(";")) {
                    continue;
                }

                String sql = statement.toString().trim();
                statement.setLength(0);
                String sqlUpper = sql.toUpperCase(Locale.ROOT);
                if (sqlUpper.startsWith("INSERT INTO ")) {
                    inserts.add(sql.substring(0, sql.length() - 1));
                }
            }
        } catch (Exception e) {
            System.err.println("Error al leer script SQL: " + e.getMessage());
            e.printStackTrace();
        }
        return inserts;
    }
}
