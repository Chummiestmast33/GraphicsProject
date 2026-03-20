package com.starsolutions.graphicsproject.model;

public class Salud {
    private int anio;
    private String categoriaPoblacion;
    private long dosisAplicadas;

    public Salud() {
    }

    public Salud(int anio, String categoriaPoblacion, long dosisAplicadas) {
        this.anio = anio;
        this.categoriaPoblacion = categoriaPoblacion;
        this.dosisAplicadas = dosisAplicadas;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getCategoriaPoblacion() {
        return categoriaPoblacion;
    }

    public void setCategoriaPoblacion(String categoriaPoblacion) {
        this.categoriaPoblacion = categoriaPoblacion;
    }

    public long getDosisAplicadas() {
        return dosisAplicadas;
    }

    public void setDosisAplicadas(long dosisAplicadas) {
        this.dosisAplicadas = dosisAplicadas;
    }

    @Override
    public String toString() {
        return "Salud{" +
                "anio=" + anio +
                ", categoriaPoblacion='" + categoriaPoblacion + '\'' +
                ", dosisAplicadas=" + dosisAplicadas +
                '}';
    }
}
