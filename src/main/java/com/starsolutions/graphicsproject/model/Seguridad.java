package com.starsolutions.graphicsproject.model;

public class Seguridad {
    private int anio;
    private String tipoDelito;
    private int casos;

    public Seguridad() {
    }

    public Seguridad(int anio, String tipoDelito, int casos) {
        this.anio = anio;
        this.tipoDelito = tipoDelito;
        this.casos = casos;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getTipoDelito() {
        return tipoDelito;
    }

    public void setTipoDelito(String tipoDelito) {
        this.tipoDelito = tipoDelito;
    }

    public int getCasos() {
        return casos;
    }

    public void setCasos(int casos) {
        this.casos = casos;
    }

    @Override
    public String toString() {
        return "Seguridad{" +
                "anio=" + anio +
                ", tipoDelito='" + tipoDelito + '\'' +
                ", casos=" + casos +
                '}';
    }
}
