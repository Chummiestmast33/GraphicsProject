package com.starsolutions.graphicsproject.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DATABASE_URL = "jdbc:sqlite:database.db";
    private static Connection connection;

    /**
     * Obtiene la conexión a la base de datos SQLite.
     * Si no existe, crea una nueva conexión.
     *
     * @return Connection a la base de datos SQLite
     * @throws SQLException si hay error al conectarse
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Cargar el driver de SQLite
            Class.forName("org.sqlite.JDBC");
            
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DATABASE_URL);
                System.out.println("Conexión establecida a SQLite: " + DATABASE_URL);
            }
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de SQLite");
            e.printStackTrace();
            throw new SQLException("Driver de SQLite no encontrado", e);
        }
        return connection;
    }

    /**
     * Cierra la conexión a la base de datos.
     */
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Conexión cerrada");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Verifica si la conexión está activa.
     *
     * @return true si está conectado, false en caso contrario
     */
    public static boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}
