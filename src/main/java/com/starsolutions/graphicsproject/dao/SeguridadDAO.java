package com.starsolutions.graphicsproject.dao;

import com.starsolutions.graphicsproject.database.DatabaseConnection;
import com.starsolutions.graphicsproject.model.Seguridad;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SeguridadDAO {

    private static final String AMBITO_MUNICIPAL = "Municipal";
    private static final String AMBITO_ESTATAL = "Estatal";
    private static final String AMBITO_FEDERAL = "Federal";
    private static final String AMBITO_TODOS = "Todos";

    /**
     * Obtiene todos los registros de Seguridad_Gobierno_Torreon
     */
    public List<Seguridad> obtenerSeguridadGobierno() {
        return obtenerSeguridadGobierno(AMBITO_MUNICIPAL);
    }

    public List<Seguridad> obtenerSeguridadGobierno(String ambito) {
        if (esTodos(ambito)) {
            return combinar(
                etiquetarAmbito(obtenerSeguridadPorTabla("Seguridad_Gobierno_Nacional"), "Federal"),
                etiquetarAmbito(obtenerSeguridadPorTabla("Seguridad_Gobierno_Coahuila"), "Estatal"),
                etiquetarAmbito(obtenerSeguridadPorTabla("Seguridad_Gobierno_Torreon"), "Municipal")
            );
        }
        return obtenerSeguridadPorTabla(obtenerTablaGobierno(ambito));
    }

    /**
     * Obtiene todos los registros de Seguridad_ONG_Torreon
     */
    public List<Seguridad> obtenerSeguridadONG() {
        return obtenerSeguridadONG(AMBITO_MUNICIPAL);
    }

    public List<Seguridad> obtenerSeguridadONG(String ambito) {
        if (esTodos(ambito)) {
            return combinar(
                etiquetarAmbito(obtenerSeguridadPorTabla("Seguridad_ONG_Nacional"), "Federal"),
                etiquetarAmbito(obtenerSeguridadPorTabla("Seguridad_ONG_Coahuila"), "Estatal"),
                etiquetarAmbito(obtenerSeguridadPorTabla("Seguridad_ONG_Torreon"), "Municipal")
            );
        }
        return obtenerSeguridadPorTabla(obtenerTablaONG(ambito));
    }

        /**
         * Obtiene registros de Seguridad_Gobierno_Torreon por año
         */
    public List<Seguridad> obtenerSeguridadGobiernoPorAnio(int anio) {
        return obtenerSeguridadGobiernoPorAnio(anio, AMBITO_MUNICIPAL);
    }

    public List<Seguridad> obtenerSeguridadGobiernoPorAnio(int anio, String ambito) {
        if (esTodos(ambito)) {
            return combinar(
                etiquetarAmbito(obtenerSeguridadPorTablaYAnio("Seguridad_Gobierno_Nacional", anio), "Federal"),
                etiquetarAmbito(obtenerSeguridadPorTablaYAnio("Seguridad_Gobierno_Coahuila", anio), "Estatal"),
                etiquetarAmbito(obtenerSeguridadPorTablaYAnio("Seguridad_Gobierno_Torreon", anio), "Municipal")
            );
        }
        return obtenerSeguridadPorTablaYAnio(obtenerTablaGobierno(ambito), anio);
    }

        /**
         * Obtiene registros de Seguridad_ONG_Torreon por año
         */
    public List<Seguridad> obtenerSeguridadONGPorAnio(int anio) {
        return obtenerSeguridadONGPorAnio(anio, AMBITO_MUNICIPAL);
    }

    public List<Seguridad> obtenerSeguridadONGPorAnio(int anio, String ambito) {
        if (esTodos(ambito)) {
            return combinar(
                etiquetarAmbito(obtenerSeguridadPorTablaYAnio("Seguridad_ONG_Nacional", anio), "Federal"),
                etiquetarAmbito(obtenerSeguridadPorTablaYAnio("Seguridad_ONG_Coahuila", anio), "Estatal"),
                etiquetarAmbito(obtenerSeguridadPorTablaYAnio("Seguridad_ONG_Torreon", anio), "Municipal")
            );
        }
        return obtenerSeguridadPorTablaYAnio(obtenerTablaONG(ambito), anio);
    }

        /**
         * Obtiene registros de Seguridad_Gobierno_Torreon por tipo de delito
         */
    public List<Seguridad> obtenerSeguridadGobiernoPorDelito(String tipoDelito) {
        return obtenerSeguridadGobiernoPorDelito(tipoDelito, AMBITO_MUNICIPAL);
    }

    public List<Seguridad> obtenerSeguridadGobiernoPorDelito(String tipoDelito, String ambito) {
        if (esTodos(ambito)) {
            return combinar(
                etiquetarAmbito(obtenerSeguridadPorTablaYDelito("Seguridad_Gobierno_Nacional", tipoDelito), "Federal"),
                etiquetarAmbito(obtenerSeguridadPorTablaYDelito("Seguridad_Gobierno_Coahuila", tipoDelito), "Estatal"),
                etiquetarAmbito(obtenerSeguridadPorTablaYDelito("Seguridad_Gobierno_Torreon", tipoDelito), "Municipal")
            );
        }
        return obtenerSeguridadPorTablaYDelito(obtenerTablaGobierno(ambito), tipoDelito);
    }

    private List<Seguridad> etiquetarAmbito(List<Seguridad> datos, String ambito) {
        List<Seguridad> etiquetados = new ArrayList<>(datos.size());
        for (Seguridad registro : datos) {
            String delitoConAmbito = registro.getTipoDelito() + " (" + ambito + " - " + registro.getAnio() + ")";
            etiquetados.add(new Seguridad(registro.getAnio(), delitoConAmbito, registro.getCasos()));
        }
        return etiquetados;
    }

        /**
         * Inserta un nuevo registro en Seguridad_Gobierno_Torreon
         */
    public boolean insertarSeguridadGobierno(Seguridad seguridad) {
        return insertarSeguridadGobierno(seguridad, AMBITO_MUNICIPAL);
    }

    public boolean insertarSeguridadGobierno(Seguridad seguridad, String ambito) {
        return insertarEnTabla(seguridad, obtenerTablaGobierno(ambito), true);
    }

    /**
     * Inserta un nuevo registro en Seguridad_ONG_Torreon
     */
    public boolean insertarSeguridadONG(Seguridad seguridad) {
        return insertarSeguridadONG(seguridad, AMBITO_MUNICIPAL);
    }

    public boolean insertarSeguridadONG(Seguridad seguridad, String ambito) {
        return insertarEnTabla(seguridad, obtenerTablaONG(ambito), false);
    }

    private List<Seguridad> obtenerSeguridadPorTabla(String tableName) {
        List<Seguridad> listaSeguridad = new ArrayList<>();
        String sql = "SELECT Anio, Tipo_Delito, " +
                (tableName.contains("_ONG_") ? "Casos_Estimados_Reales" : "Casos_Oficiales") +
                " AS Casos FROM " + tableName;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                listaSeguridad.add(mapSeguridad(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de " + tableName + ": " + e.getMessage());
            e.printStackTrace();
        }
        return listaSeguridad;
    }

    private List<Seguridad> obtenerSeguridadPorTablaYAnio(String tableName, int anio) {
        List<Seguridad> listaSeguridad = new ArrayList<>();
        String sql = "SELECT Anio, Tipo_Delito, " +
                (tableName.contains("_ONG_") ? "Casos_Estimados_Reales" : "Casos_Oficiales") +
                " AS Casos FROM " + tableName + " WHERE Anio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, anio);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    listaSeguridad.add(mapSeguridad(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos por año en " + tableName + ": " + e.getMessage());
            e.printStackTrace();
        }
        return listaSeguridad;
    }

    private List<Seguridad> obtenerSeguridadPorTablaYDelito(String tableName, String tipoDelito) {
        List<Seguridad> listaSeguridad = new ArrayList<>();
        String sql = "SELECT Anio, Tipo_Delito, " +
                (tableName.contains("_ONG_") ? "Casos_Estimados_Reales" : "Casos_Oficiales") +
                " AS Casos FROM " + tableName + " WHERE Tipo_Delito = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tipoDelito);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    listaSeguridad.add(mapSeguridad(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos por delito en " + tableName + ": " + e.getMessage());
            e.printStackTrace();
        }
        return listaSeguridad;
    }

    private boolean insertarEnTabla(Seguridad seguridad, String tableName, boolean gobierno) {
        String columnaCasos = gobierno ? "Casos_Oficiales" : "Casos_Estimados_Reales";
        String sql = "INSERT INTO " + tableName + " (Anio, Tipo_Delito, " + columnaCasos + ") VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, seguridad.getAnio());
            pstmt.setString(2, seguridad.getTipoDelito());
            pstmt.setInt(3, seguridad.getCasos());
            pstmt.executeUpdate();
            System.out.println("Registro insertado en " + tableName);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar en " + tableName + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Seguridad mapSeguridad(ResultSet rs) throws SQLException {
        return new Seguridad(
            rs.getInt("Anio"),
            rs.getString("Tipo_Delito"),
            rs.getInt("Casos")
        );
    }

    @SafeVarargs
    private final List<Seguridad> combinar(List<Seguridad>... lotes) {
        List<Seguridad> combinado = new ArrayList<>();
        for (List<Seguridad> lote : lotes) {
            combinado.addAll(lote);
        }
        return combinado;
    }

    private String obtenerTablaGobierno(String ambito) {
        return switch (normalizarAmbito(ambito)) {
            case AMBITO_FEDERAL -> "Seguridad_Gobierno_Nacional";
            case AMBITO_ESTATAL -> "Seguridad_Gobierno_Coahuila";
            case AMBITO_MUNICIPAL -> "Seguridad_Gobierno_Torreon";
            default -> "Seguridad_Gobierno_Torreon";
        };
    }

    private String obtenerTablaONG(String ambito) {
        return switch (normalizarAmbito(ambito)) {
            case AMBITO_FEDERAL -> "Seguridad_ONG_Nacional";
            case AMBITO_ESTATAL -> "Seguridad_ONG_Coahuila";
            case AMBITO_MUNICIPAL -> "Seguridad_ONG_Torreon";
            default -> "Seguridad_ONG_Torreon";
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
