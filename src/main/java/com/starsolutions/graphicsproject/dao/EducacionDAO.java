package com.starsolutions.graphicsproject.dao;

import com.starsolutions.graphicsproject.database.DatabaseConnection;
import com.starsolutions.graphicsproject.model.Educacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EducacionDAO {

    /**
     * Obtiene todos los registros de Educacion_Gobierno_Torreon
     */
    public List<Educacion> obtenerEducacionGobierno() {
        List<Educacion> listaEducacion = new ArrayList<>();
        String sql = "SELECT Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, " +
                     "Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales " +
                     "FROM Educacion_Gobierno_Torreon";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Educacion educacion = new Educacion(
                    rs.getString("Nivel_Educativo"),
                    rs.getInt("Escuelas_Publicas"),
                    rs.getInt("Escuelas_Privadas"),
                    rs.getInt("Alumnos_Hombres"),
                    rs.getInt("Alumnos_Mujeres"),
                    rs.getInt("Docentes_Hombres"),
                    rs.getInt("Docentes_Mujeres"),
                    rs.getInt("Docentes_Totales")
                );
                listaEducacion.add(educacion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Educacion_Gobierno_Torreon: " + e.getMessage());
            e.printStackTrace();
        }
        return listaEducacion;
    }

    /**
     * Obtiene todos los registros de Educacion_ONG_Torreon
     */
    public List<Educacion> obtenerEducacionONG() {
        List<Educacion> listaEducacion = new ArrayList<>();
        String sql = "SELECT Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, " +
                     "Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales " +
                     "FROM Educacion_ONG_Torreon";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Educacion educacion = new Educacion(
                    rs.getString("Nivel_Educativo"),
                    rs.getInt("Escuelas_Publicas"),
                    rs.getInt("Escuelas_Privadas"),
                    rs.getInt("Alumnos_Hombres"),
                    rs.getInt("Alumnos_Mujeres"),
                    rs.getInt("Docentes_Hombres"),
                    rs.getInt("Docentes_Mujeres"),
                    rs.getInt("Docentes_Totales")
                );
                listaEducacion.add(educacion);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Educacion_ONG_Torreon: " + e.getMessage());
            e.printStackTrace();
        }
        return listaEducacion;
    }

    /**
     * Obtiene un registro de Educacion_Gobierno_Torreon por nivel educativo
     */
    public Educacion obtenerEducacionGobiernoPorNivel(String nivelEducativo) {
        String sql = "SELECT Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, " +
                     "Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales " +
                     "FROM Educacion_Gobierno_Torreon WHERE Nivel_Educativo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nivelEducativo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Educacion(
                        rs.getString("Nivel_Educativo"),
                        rs.getInt("Escuelas_Publicas"),
                        rs.getInt("Escuelas_Privadas"),
                        rs.getInt("Alumnos_Hombres"),
                        rs.getInt("Alumnos_Mujeres"),
                        rs.getInt("Docentes_Hombres"),
                        rs.getInt("Docentes_Mujeres"),
                        rs.getInt("Docentes_Totales")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Educacion_Gobierno_Torreon por nivel: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Obtiene un registro de Educacion_ONG_Torreon por nivel educativo
     */
    public Educacion obtenerEducacionONGPorNivel(String nivelEducativo) {
        String sql = "SELECT Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, " +
                     "Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales " +
                     "FROM Educacion_ONG_Torreon WHERE Nivel_Educativo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nivelEducativo);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Educacion(
                        rs.getString("Nivel_Educativo"),
                        rs.getInt("Escuelas_Publicas"),
                        rs.getInt("Escuelas_Privadas"),
                        rs.getInt("Alumnos_Hombres"),
                        rs.getInt("Alumnos_Mujeres"),
                        rs.getInt("Docentes_Hombres"),
                        rs.getInt("Docentes_Mujeres"),
                        rs.getInt("Docentes_Totales")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de Educacion_ONG_Torreon por nivel: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Inserta un nuevo registro en Educacion_Gobierno_Torreon
     */
    public boolean insertarEducacionGobierno(Educacion educacion) {
        String sql = "INSERT INTO Educacion_Gobierno_Torreon " +
                     "(Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, " +
                     "Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, educacion.getNivelEducativo());
            pstmt.setInt(2, educacion.getEscuelasPublicas());
            pstmt.setInt(3, educacion.getEscuelasPrivadas());
            pstmt.setInt(4, educacion.getAlumnosHombres());
            pstmt.setInt(5, educacion.getAlumnosMujeres());
            pstmt.setInt(6, educacion.getDocentesHombres());
            pstmt.setInt(7, educacion.getDocentesMujeres());
            pstmt.setInt(8, educacion.getDocentesTotales());
            pstmt.executeUpdate();
            System.out.println("Registro insertado en Educacion_Gobierno_Torreon");
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar en Educacion_Gobierno_Torreon: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Inserta un nuevo registro en Educacion_ONG_Torreon
     */
    public boolean insertarEducacionONG(Educacion educacion) {
        String sql = "INSERT INTO Educacion_ONG_Torreon " +
                     "(Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, " +
                     "Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, educacion.getNivelEducativo());
            pstmt.setInt(2, educacion.getEscuelasPublicas());
            pstmt.setInt(3, educacion.getEscuelasPrivadas());
            pstmt.setInt(4, educacion.getAlumnosHombres());
            pstmt.setInt(5, educacion.getAlumnosMujeres());
            pstmt.setInt(6, educacion.getDocentesHombres());
            pstmt.setInt(7, educacion.getDocentesMujeres());
            pstmt.setInt(8, educacion.getDocentesTotales());
            pstmt.executeUpdate();
            System.out.println("Registro insertado en Educacion_ONG_Torreon");
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar en Educacion_ONG_Torreon: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Actualiza un registro en Educacion_Gobierno_Torreon
     */
    public boolean actualizarEducacionGobierno(Educacion educacion) {
        String sql = "UPDATE Educacion_Gobierno_Torreon SET " +
                     "Escuelas_Publicas = ?, Escuelas_Privadas = ?, Alumnos_Hombres = ?, " +
                     "Alumnos_Mujeres = ?, Docentes_Hombres = ?, Docentes_Mujeres = ?, Docentes_Totales = ? " +
                     "WHERE Nivel_Educativo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, educacion.getEscuelasPublicas());
            pstmt.setInt(2, educacion.getEscuelasPrivadas());
            pstmt.setInt(3, educacion.getAlumnosHombres());
            pstmt.setInt(4, educacion.getAlumnosMujeres());
            pstmt.setInt(5, educacion.getDocentesHombres());
            pstmt.setInt(6, educacion.getDocentesMujeres());
            pstmt.setInt(7, educacion.getDocentesTotales());
            pstmt.setString(8, educacion.getNivelEducativo());
            pstmt.executeUpdate();
            System.out.println("Registro actualizado en Educacion_Gobierno_Torreon");
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar en Educacion_Gobierno_Torreon: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
