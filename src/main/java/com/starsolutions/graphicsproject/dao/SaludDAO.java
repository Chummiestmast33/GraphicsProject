package com.starsolutions.graphicsproject.dao;

import com.starsolutions.graphicsproject.database.DatabaseConnection;
import com.starsolutions.graphicsproject.model.Salud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SaludDAO {

    /**
     * Obtiene todos los registros de Salud_Gobierno
     */
    public List<Salud> obtenerSaludGobierno() {
        List<Salud> listaSalud = new ArrayList<>();
        String sql = "SELECT Anio, Categoria_Poblacion, Dosis_Aplicadas FROM Salud_Gobierno";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Salud salud = new Salud(
                    rs.getInt("Anio"),
                    rs.getString("Categoria_Poblacion"),
                    rs.getLong("Dosis_Aplicadas")
                );
                listaSalud.add(salud);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Salud_Gobierno: " + e.getMessage());
            e.printStackTrace();
        }
        return listaSalud;
    }

    /**
     * Obtiene todos los registros de Salud_ONG
     */
    public List<Salud> obtenerSaludONG() {
        List<Salud> listaSalud = new ArrayList<>();
        String sql = "SELECT Anio, Categoria_Poblacion, Dosis_Aplicadas FROM Salud_ONG";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Salud salud = new Salud(
                    rs.getInt("Anio"),
                    rs.getString("Categoria_Poblacion"),
                    rs.getLong("Dosis_Aplicadas")
                );
                listaSalud.add(salud);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Salud_ONG: " + e.getMessage());
            e.printStackTrace();
        }
        return listaSalud;
    }

    /**
     * Obtiene registros de Salud_Gobierno por año
     */
    public List<Salud> obtenerSaludGobiernoPorAnio(int anio) {
        List<Salud> listaSalud = new ArrayList<>();
        String sql = "SELECT Anio, Categoria_Poblacion, Dosis_Aplicadas FROM Salud_Gobierno WHERE Anio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, anio);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Salud salud = new Salud(
                        rs.getInt("Anio"),
                        rs.getString("Categoria_Poblacion"),
                        rs.getLong("Dosis_Aplicadas")
                    );
                    listaSalud.add(salud);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Salud_Gobierno por año: " + e.getMessage());
            e.printStackTrace();
        }
        return listaSalud;
    }

    /**
     * Obtiene registros de Salud_ONG por año
     */
    public List<Salud> obtenerSaludONGPorAnio(int anio) {
        List<Salud> listaSalud = new ArrayList<>();
        String sql = "SELECT Anio, Categoria_Poblacion, Dosis_Aplicadas FROM Salud_ONG WHERE Anio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, anio);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Salud salud = new Salud(
                        rs.getInt("Anio"),
                        rs.getString("Categoria_Poblacion"),
                        rs.getLong("Dosis_Aplicadas")
                    );
                    listaSalud.add(salud);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Salud_ONG por año: " + e.getMessage());
            e.printStackTrace();
        }
        return listaSalud;
    }

    /**
     * Obtiene registros de Salud_Gobierno por categoría de población
     */
    public List<Salud> obtenerSaludGobiernoPorCategoria(String categoria) {
        List<Salud> listaSalud = new ArrayList<>();
        String sql = "SELECT Anio, Categoria_Poblacion, Dosis_Aplicadas FROM Salud_Gobierno WHERE Categoria_Poblacion = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, categoria);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Salud salud = new Salud(
                        rs.getInt("Anio"),
                        rs.getString("Categoria_Poblacion"),
                        rs.getLong("Dosis_Aplicadas")
                    );
                    listaSalud.add(salud);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Salud_Gobierno por categoría: " + e.getMessage());
            e.printStackTrace();
        }
        return listaSalud;
    }

    /**
     * Inserta un nuevo registro en Salud_Gobierno
     */
    public boolean insertarSaludGobierno(Salud salud) {
        String sql = "INSERT INTO Salud_Gobierno (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, salud.getAnio());
            pstmt.setString(2, salud.getCategoriaPoblacion());
            pstmt.setLong(3, salud.getDosisAplicadas());
            pstmt.executeUpdate();
            System.out.println("Registro insertado en Salud_Gobierno");
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar en Salud_Gobierno: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Inserta un nuevo registro en Salud_ONG
     */
    public boolean insertarSaludONG(Salud salud) {
        String sql = "INSERT INTO Salud_ONG (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, salud.getAnio());
            pstmt.setString(2, salud.getCategoriaPoblacion());
            pstmt.setLong(3, salud.getDosisAplicadas());
            pstmt.executeUpdate();
            System.out.println("Registro insertado en Salud_ONG");
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar en Salud_ONG: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
