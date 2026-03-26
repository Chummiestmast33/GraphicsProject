package com.starsolutions.graphicsproject.dao;

import com.starsolutions.graphicsproject.database.DatabaseConnection;
import com.starsolutions.graphicsproject.model.Educacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class EducacionDAO {

    private static final String AMBITO_MUNICIPAL = "Municipal";
    private static final String AMBITO_ESTATAL = "Estatal";
    private static final String AMBITO_FEDERAL = "Federal";
    private static final String AMBITO_TODOS = "Todos";

    /**
     * Obtiene todos los registros de Educacion_Gobierno_Torreon
     */
    public List<Educacion> obtenerEducacionGobierno() {
        return obtenerEducacionGobierno(AMBITO_MUNICIPAL);
    }

    public List<Educacion> obtenerEducacionGobierno(String ambito) {
        if (esTodos(ambito)) {
            return combinar(
                etiquetarAmbito(obtenerEducacionPorTabla("Educacion_Gobierno_Nacional"), "Federal"),
                etiquetarAmbito(obtenerEducacionPorTabla("Educacion_Gobierno_Coahuila"), "Estatal"),
                etiquetarAmbito(obtenerEducacionPorTabla("Educacion_Gobierno_Torreon"), "Municipal")
            );
        }
        return obtenerEducacionPorTabla(obtenerTablaGobierno(ambito));
    }

    /**
     * Obtiene todos los registros de Educacion_ONG_Torreon
     */
    public List<Educacion> obtenerEducacionONG() {
        return obtenerEducacionONG(AMBITO_MUNICIPAL);
    }

    public List<Educacion> obtenerEducacionONG(String ambito) {
        if (esTodos(ambito)) {
            return combinar(
                etiquetarAmbito(obtenerEducacionPorTabla("Educacion_ONG_Nacional"), "Federal"),
                etiquetarAmbito(obtenerEducacionPorTabla("Educacion_ONG_Coahuila"), "Estatal"),
                etiquetarAmbito(obtenerEducacionPorTabla("Educacion_ONG_Torreon"), "Municipal")
            );
        }
        return obtenerEducacionPorTabla(obtenerTablaONG(ambito));
    }

    private List<Educacion> etiquetarAmbito(List<Educacion> datos, String ambito) {
        List<Educacion> etiquetados = new ArrayList<>(datos.size());
        for (Educacion registro : datos) {
            String nivelConAmbito = registro.getNivelEducativo() + " (" + ambito + ")";
            etiquetados.add(new Educacion(
                nivelConAmbito,
                registro.getEscuelasPublicas(),
                registro.getEscuelasPrivadas(),
                registro.getAlumnosHombres(),
                registro.getAlumnosMujeres(),
                registro.getDocentesHombres(),
                registro.getDocentesMujeres(),
                registro.getDocentesTotales()
            ));
        }
        return etiquetados;
    }

    /**
     * Obtiene un registro de Educacion_Gobierno_Torreon por nivel educativo
     */
    public Educacion obtenerEducacionGobiernoPorNivel(String nivelEducativo) {
        return obtenerEducacionGobiernoPorNivel(nivelEducativo, AMBITO_MUNICIPAL);
    }

    public Educacion obtenerEducacionGobiernoPorNivel(String nivelEducativo, String ambito) {
        if (esTodos(ambito)) {
            List<Educacion> resultados = combinar(
                obtenerEducacionPorTablaYNivel("Educacion_Gobierno_Nacional", nivelEducativo),
                obtenerEducacionPorTablaYNivel("Educacion_Gobierno_Coahuila", nivelEducativo),
                obtenerEducacionPorTablaYNivel("Educacion_Gobierno_Torreon", nivelEducativo)
            );
            return resultados.isEmpty() ? null : resultados.get(0);
        }
        List<Educacion> resultados = obtenerEducacionPorTablaYNivel(obtenerTablaGobierno(ambito), nivelEducativo);
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    /**
     * Obtiene un registro de Educacion_ONG_Torreon por nivel educativo
     */
    public Educacion obtenerEducacionONGPorNivel(String nivelEducativo) {
        return obtenerEducacionONGPorNivel(nivelEducativo, AMBITO_MUNICIPAL);
    }

    public Educacion obtenerEducacionONGPorNivel(String nivelEducativo, String ambito) {
        if (esTodos(ambito)) {
            List<Educacion> resultados = combinar(
                obtenerEducacionPorTablaYNivel("Educacion_ONG_Nacional", nivelEducativo),
                obtenerEducacionPorTablaYNivel("Educacion_ONG_Coahuila", nivelEducativo),
                obtenerEducacionPorTablaYNivel("Educacion_ONG_Torreon", nivelEducativo)
            );
            return resultados.isEmpty() ? null : resultados.get(0);
        }
        List<Educacion> resultados = obtenerEducacionPorTablaYNivel(obtenerTablaONG(ambito), nivelEducativo);
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    /**
     * Inserta un nuevo registro en Educacion_Gobierno_Torreon
     */
    public boolean insertarEducacionGobierno(Educacion educacion) {
        return insertarEducacionGobierno(educacion, AMBITO_MUNICIPAL);
    }

    public boolean insertarEducacionGobierno(Educacion educacion, String ambito) {
        return insertarEducacionEnTabla(educacion, obtenerTablaGobierno(ambito));
    }

    /**
     * Inserta un nuevo registro en Educacion_ONG_Torreon
     */
    public boolean insertarEducacionONG(Educacion educacion) {
        return insertarEducacionONG(educacion, AMBITO_MUNICIPAL);
    }

    public boolean insertarEducacionONG(Educacion educacion, String ambito) {
        return insertarEducacionEnTabla(educacion, obtenerTablaONG(ambito));
    }

    /**
     * Actualiza un registro en Educacion_Gobierno_Torreon
     */
    public boolean actualizarEducacionGobierno(Educacion educacion) {
        return actualizarEducacionGobierno(educacion, AMBITO_MUNICIPAL);
    }

    public boolean actualizarEducacionGobierno(Educacion educacion, String ambito) {
        String tableName = obtenerTablaGobierno(ambito);
        String sql = "UPDATE " + tableName + " SET " +
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
            System.out.println("Registro actualizado en " + tableName);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al actualizar en " + tableName + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private List<Educacion> obtenerEducacionPorTabla(String tableName) {
        List<Educacion> listaEducacion = new ArrayList<>();
        String sql = "SELECT Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, " +
                "Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales " +
                "FROM " + tableName;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                listaEducacion.add(mapEducacion(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de " + tableName + ": " + e.getMessage());
            e.printStackTrace();
        }
        return listaEducacion;
    }

    private List<Educacion> obtenerEducacionPorTablaYNivel(String tableName, String nivel) {
        List<Educacion> listaEducacion = new ArrayList<>();
        String sql = "SELECT Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, " +
                "Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales " +
                "FROM " + tableName + " WHERE Nivel_Educativo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nivel);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    listaEducacion.add(mapEducacion(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos por nivel en " + tableName + ": " + e.getMessage());
            e.printStackTrace();
        }
        return listaEducacion;
    }

    private boolean insertarEducacionEnTabla(Educacion educacion, String tableName) {
        String sql = "INSERT INTO " + tableName + " " +
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
            System.out.println("Registro insertado en " + tableName);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar en " + tableName + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Educacion mapEducacion(ResultSet rs) throws SQLException {
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

    @SafeVarargs
    private final List<Educacion> combinar(List<Educacion>... lotes) {
        List<Educacion> combinado = new ArrayList<>();
        for (List<Educacion> lote : lotes) {
            combinado.addAll(lote);
        }
        return combinado;
    }

    private String obtenerTablaGobierno(String ambito) {
        return switch (normalizarAmbito(ambito)) {
            case AMBITO_FEDERAL -> "Educacion_Gobierno_Nacional";
            case AMBITO_ESTATAL -> "Educacion_Gobierno_Coahuila";
            case AMBITO_MUNICIPAL -> "Educacion_Gobierno_Torreon";
            default -> "Educacion_Gobierno_Torreon";
        };
    }

    private String obtenerTablaONG(String ambito) {
        return switch (normalizarAmbito(ambito)) {
            case AMBITO_FEDERAL -> "Educacion_ONG_Nacional";
            case AMBITO_ESTATAL -> "Educacion_ONG_Coahuila";
            case AMBITO_MUNICIPAL -> "Educacion_ONG_Torreon";
            default -> "Educacion_ONG_Torreon";
        };
    }

    private boolean esTodos(String ambito) {
        return AMBITO_TODOS.equalsIgnoreCase(normalizarAmbito(ambito));
    }

    private String normalizarAmbito(String ambito) {
        if (ambito == null || ambito.isBlank()) {
            return AMBITO_MUNICIPAL;
        }
        String limpio = ambito.trim().toLowerCase(Locale.ROOT);
        return switch (limpio) {
            case "federal", "pais", "país", "nacional" -> AMBITO_FEDERAL;
            case "estatal", "estado" -> AMBITO_ESTATAL;
            case "todos", "todo" -> AMBITO_TODOS;
            default -> AMBITO_MUNICIPAL;
        };
    }
}
