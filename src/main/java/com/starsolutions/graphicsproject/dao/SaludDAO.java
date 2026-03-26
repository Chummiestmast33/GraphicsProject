package com.starsolutions.graphicsproject.dao;

import com.starsolutions.graphicsproject.database.DatabaseConnection;
import com.starsolutions.graphicsproject.model.Salud;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SaludDAO {

    private static final String AMBITO_MUNICIPAL = "Municipal";
    private static final String AMBITO_ESTATAL = "Estatal";
    private static final String AMBITO_FEDERAL = "Federal";
    private static final String AMBITO_TODOS = "Todos";

    /**
     * Obtiene todos los registros de Salud_Gobierno_Torreon
     */
    public List<Salud> obtenerSaludGobierno() {
        return obtenerSaludGobierno(AMBITO_MUNICIPAL);
    }

    public List<Salud> obtenerSaludGobierno(String ambito) {
        if (esTodos(ambito)) {
            return obtenerSaludGobiernoTodos();
        }
        return obtenerSaludPorTabla(obtenerTablaGobierno(ambito));
    }

    /**
     * Obtiene todos los registros de Salud_ONG_Torreon
     */
    public List<Salud> obtenerSaludONG() {
        return obtenerSaludONG(AMBITO_MUNICIPAL);
    }

    public List<Salud> obtenerSaludONG(String ambito) {
        if (esTodos(ambito)) {
            return obtenerSaludONGTodos();
        }
        return obtenerSaludPorTabla(obtenerTablaONG(ambito));
    }

        /**
         * Obtiene registros de Salud_Gobierno_Torreon por año
         */
    public List<Salud> obtenerSaludGobiernoPorAnio(int anio) {
        return obtenerSaludGobiernoPorAnio(anio, AMBITO_MUNICIPAL);
    }

    public List<Salud> obtenerSaludGobiernoPorAnio(int anio, String ambito) {
        if (esTodos(ambito)) {
            return combinar(
                obtenerSaludPorTablaYAnio("Salud_Gobierno_Nacional", anio),
                obtenerSaludPorTablaYAnio("Salud_Gobierno_Coahuila", anio),
                obtenerSaludPorTablaYAnio("Salud_Gobierno_Torreon", anio)
            );
        }
        return obtenerSaludPorTablaYAnio(obtenerTablaGobierno(ambito), anio);
    }

        /**
         * Obtiene registros de Salud_ONG_Torreon por año
         */
    public List<Salud> obtenerSaludONGPorAnio(int anio) {
        return obtenerSaludONGPorAnio(anio, AMBITO_MUNICIPAL);
    }

    public List<Salud> obtenerSaludONGPorAnio(int anio, String ambito) {
        if (esTodos(ambito)) {
            return combinar(
                obtenerSaludPorTablaYAnio("Salud_ONG_Nacional", anio),
                obtenerSaludPorTablaYAnio("Salud_ONG_Coahuila", anio),
                obtenerSaludPorTablaYAnio("Salud_ONG_Torreon", anio)
            );
        }
        return obtenerSaludPorTablaYAnio(obtenerTablaONG(ambito), anio);
    }

        /**
         * Obtiene registros de Salud_Gobierno_Torreon por categoría de población
         */
    public List<Salud> obtenerSaludGobiernoPorCategoria(String categoria) {
        return obtenerSaludGobiernoPorCategoria(categoria, AMBITO_MUNICIPAL);
    }

    public List<Salud> obtenerSaludGobiernoPorCategoria(String categoria, String ambito) {
        if (esTodos(ambito)) {
            return combinar(
                obtenerSaludPorTablaYCategoria("Salud_Gobierno_Nacional", categoria),
                obtenerSaludPorTablaYCategoria("Salud_Gobierno_Coahuila", categoria),
                obtenerSaludPorTablaYCategoria("Salud_Gobierno_Torreon", categoria)
            );
        }
        return obtenerSaludPorTablaYCategoria(obtenerTablaGobierno(ambito), categoria);
    }

        /**
         * Inserta un nuevo registro en Salud_Gobierno_Torreon
         */
    public boolean insertarSaludGobierno(Salud salud) {
        return insertarSaludGobierno(salud, AMBITO_MUNICIPAL);
    }

    public boolean insertarSaludGobierno(Salud salud, String ambito) {
        return insertarSaludEnTabla(salud, obtenerTablaGobierno(ambito));
    }

    /**
     * Inserta un nuevo registro en Salud_ONG_Torreon
     */
    public boolean insertarSaludONG(Salud salud) {
        return insertarSaludONG(salud, AMBITO_MUNICIPAL);
    }

    public boolean insertarSaludONG(Salud salud, String ambito) {
        return insertarSaludEnTabla(salud, obtenerTablaONG(ambito));
    }

    private List<Salud> obtenerSaludGobiernoTodos() {
        return combinar(
            etiquetarAmbito(obtenerSaludPorTabla("Salud_Gobierno_Nacional"), "Federal"),
            etiquetarAmbito(obtenerSaludPorTabla("Salud_Gobierno_Coahuila"), "Estatal"),
            etiquetarAmbito(obtenerSaludPorTabla("Salud_Gobierno_Torreon"), "Municipal")
        );
    }

    private List<Salud> obtenerSaludONGTodos() {
        return combinar(
            etiquetarAmbito(obtenerSaludPorTabla("Salud_ONG_Nacional"), "Federal"),
            etiquetarAmbito(obtenerSaludPorTabla("Salud_ONG_Coahuila"), "Estatal"),
            etiquetarAmbito(obtenerSaludPorTabla("Salud_ONG_Torreon"), "Municipal")
        );
    }

    private List<Salud> etiquetarAmbito(List<Salud> datos, String ambito) {
        List<Salud> etiquetados = new ArrayList<>(datos.size());
        for (Salud registro : datos) {
            String categoriaConAmbito = registro.getCategoriaPoblacion() + " (" + ambito + " - " + registro.getAnio() + ")";
            etiquetados.add(new Salud(registro.getAnio(), categoriaConAmbito, registro.getDosisAplicadas()));
        }
        return etiquetados;
    }

    private List<Salud> obtenerSaludPorTabla(String tableName) {
        List<Salud> listaSalud = new ArrayList<>();
        String sql = "SELECT Anio, Categoria_Poblacion, Dosis_Aplicadas FROM " + tableName;

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                listaSalud.add(mapSalud(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos de " + tableName + ": " + e.getMessage());
            e.printStackTrace();
        }
        return listaSalud;
    }

    private List<Salud> obtenerSaludPorTablaYAnio(String tableName, int anio) {
        List<Salud> listaSalud = new ArrayList<>();
        String sql = "SELECT Anio, Categoria_Poblacion, Dosis_Aplicadas FROM " + tableName + " WHERE Anio = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, anio);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    listaSalud.add(mapSalud(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos por año en " + tableName + ": " + e.getMessage());
            e.printStackTrace();
        }
        return listaSalud;
    }

    private List<Salud> obtenerSaludPorTablaYCategoria(String tableName, String categoria) {
        List<Salud> listaSalud = new ArrayList<>();
        String sql = "SELECT Anio, Categoria_Poblacion, Dosis_Aplicadas FROM " + tableName + " WHERE Categoria_Poblacion = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, categoria);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    listaSalud.add(mapSalud(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener datos por categoría en " + tableName + ": " + e.getMessage());
            e.printStackTrace();
        }
        return listaSalud;
    }

    private boolean insertarSaludEnTabla(Salud salud, String tableName) {
        String sql = "INSERT INTO " + tableName + " (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, salud.getAnio());
            pstmt.setString(2, salud.getCategoriaPoblacion());
            pstmt.setLong(3, salud.getDosisAplicadas());
            pstmt.executeUpdate();
            System.out.println("Registro insertado en " + tableName);
            return true;
        } catch (SQLException e) {
            System.err.println("Error al insertar en " + tableName + ": " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    private Salud mapSalud(ResultSet rs) throws SQLException {
        return new Salud(
            rs.getInt("Anio"),
            rs.getString("Categoria_Poblacion"),
            rs.getLong("Dosis_Aplicadas")
        );
    }

    @SafeVarargs
    private final List<Salud> combinar(List<Salud>... lotes) {
        List<Salud> combinado = new ArrayList<>();
        for (List<Salud> lote : lotes) {
            combinado.addAll(lote);
        }
        return combinado;
    }

    private String obtenerTablaGobierno(String ambito) {
        return switch (normalizarAmbito(ambito)) {
            case AMBITO_FEDERAL -> "Salud_Gobierno_Nacional";
            case AMBITO_ESTATAL -> "Salud_Gobierno_Coahuila";
            case AMBITO_MUNICIPAL -> "Salud_Gobierno_Torreon";
            default -> "Salud_Gobierno_Torreon";
        };
    }

    private String obtenerTablaONG(String ambito) {
        return switch (normalizarAmbito(ambito)) {
            case AMBITO_FEDERAL -> "Salud_ONG_Nacional";
            case AMBITO_ESTATAL -> "Salud_ONG_Coahuila";
            case AMBITO_MUNICIPAL -> "Salud_ONG_Torreon";
            default -> "Salud_ONG_Torreon";
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
