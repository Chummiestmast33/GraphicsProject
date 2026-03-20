package com.starsolutions.graphicsproject.dao;

import com.starsolutions.graphicsproject.database.DatabaseConnection;
import com.starsolutions.graphicsproject.model.Seguridad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeguridadDAO {

    /**
     * Obtiene todos los registros de Seguridad_Gobierno_Coahuila
     */
    public List<Seguridad> obtenerSeguridadGobierno() {
        List<Seguridad> listaSeguridad = new ArrayList<>();
        String sql = "SELECT Anio, Tipo_Delito, Casos_Oficiales FROM Seguridad_Gobierno_Coahuila";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Seguridad seguridad = new Seguridad(
                    rs.getInt("Anio"),
                    rs.getString("Tipo_Delito"),
                    rs.getInt("Casos_Oficiales")
                );
                listaSeguridad.add(seguridad);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Seguridad_Gobierno_Coahuila: " + e.getMessage());
            e.printStackTrace();
        }
        return listaSeguridad;
    }

    /**
     * Obtiene todos los registros de Seguridad_Estimacion_ONG
     */
    public List<Seguridad> obtenerSeguridadONG() {
        List<Seguridad> listaSeguridad = new ArrayList<>();
        String sql = "SELECT Anio, Tipo_Delito, Casos_Estimados_Reales FROM Seguridad_Estimacion_ONG";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Seguridad seguridad = new Seguridad(
                    rs.getInt("Anio"),
                    rs.getString("Tipo_Delito"),
                    rs.getInt("Casos_Estimados_Reales")
                );
                listaSeguridad.add(seguridad);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Seguridad_Estimacion_ONG: " + e.getMessage());
            e.printStackTrace();
        }
        return listaSeguridad;
    }

    /**
     * Obtiene registros de Seguridad_Gobierno_Coahuila por año
     */
    public List<Seguridad> obtenerSeguridadGobiernoPorAnio(int anio) {
        List<Seguridad> listaSeguridad = new ArrayList<>();
        String sql = "SELECT Anio, Tipo_Delito, Casos_Oficiales FROM Seguridad_Gobierno_Coahuila WHERE Anio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, anio);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Seguridad seguridad = new Seguridad(
                        rs.getInt("Anio"),
                        rs.getString("Tipo_Delito"),
                        rs.getInt("Casos_Oficiales")
                    );
                    listaSeguridad.add(seguridad);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Seguridad_Gobierno_Coahuila por año: " + e.getMessage());
            e.printStackTrace();
        }
        return listaSeguridad;
    }

    /**
     * Obtiene registros de Seguridad_Estimacion_ONG por año
     */
    public List<Seguridad> obtenerSeguridadONGPorAnio(int anio) {
        List<Seguridad> listaSeguridad = new ArrayList<>();
        String sql = "SELECT Anio, Tipo_Delito, Casos_Estimados_Reales FROM Seguridad_Estimacion_ONG WHERE Anio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, anio);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Seguridad seguridad = new Seguridad(
                        rs.getInt("Anio"),
                        rs.getString("Tipo_Delito"),
                        rs.getInt("Casos_Estimados_Reales")
                    );
                    listaSeguridad.add(seguridad);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Seguridad_Estimacion_ONG por año: " + e.getMessage());
            e.printStackTrace();
        }
        return listaSeguridad;
    }

    /**
     * Obtiene registros de Seguridad_Gobierno_Coahuila por tipo de delito
     */
    public List<Seguridad> obtenerSeguridadGobiernoPorDelito(String tipoDelito) {
        List<Seguridad> listaSeguridad = new ArrayList<>();
        String sql = "SELECT Anio, Tipo_Delito, Casos_Oficiales FROM Seguridad_Gobierno_Coahuila WHERE Tipo_Delito = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, tipoDelito);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Seguridad seguridad = new Seguridad(
                        rs.getInt("Anio"),
                        rs.getString("Tipo_Delito"),
                        rs.getInt("Casos_Oficiales")
                    );
                    listaSeguridad.add(seguridad);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Seguridad_Gobierno_Coahuila por delito: " + e.getMessage());
            e.printStackTrace();
        }
        return listaSeguridad;
    }

    /**
     * Inserta un nuevo registro en Seguridad_Gobierno_Coahuila
     */
    public boolean insertarSeguridadGobierno(Seguridad seguridad) {
        String sql = "INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, seguridad.getAnio());
            pstmt.setString(2, seguridad.getTipoDelito());
            pstmt.setInt(3, seguridad.getCasos());
            pstmt.executeUpdate();
            System.out.println("Registro insertado en Seguridad_Gobierno_Coahuila");
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar en Seguridad_Gobierno_Coahuila: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Inserta un nuevo registro en Seguridad_Estimacion_ONG
     */
    public boolean insertarSeguridadONG(Seguridad seguridad) {
        String sql = "INSERT INTO Seguridad_Estimacion_ONG (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, seguridad.getAnio());
            pstmt.setString(2, seguridad.getTipoDelito());
            pstmt.setInt(3, seguridad.getCasos());
            pstmt.executeUpdate();
            System.out.println("Registro insertado en Seguridad_Estimacion_ONG");
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar en Seguridad_Estimacion_ONG: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
