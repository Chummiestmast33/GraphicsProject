package com.starsolutions.graphicsproject.model;

public class Educacion {
    private String nivelEducativo;
    private int escuelasPublicas;
    private int escuelasPrivadas;
    private int alumnosHombres;
    private int alumnosMujeres;
    private int docentesHombres;
    private int docentesMujeres;
    private int docentesTotales;

    public Educacion() {
    }

    public Educacion(String nivelEducativo, int escuelasPublicas, int escuelasPrivadas,
                     int alumnosHombres, int alumnosMujeres, int docentesHombres,
                     int docentesMujeres, int docentesTotales) {
        this.nivelEducativo = nivelEducativo;
        this.escuelasPublicas = escuelasPublicas;
        this.escuelasPrivadas = escuelasPrivadas;
        this.alumnosHombres = alumnosHombres;
        this.alumnosMujeres = alumnosMujeres;
        this.docentesHombres = docentesHombres;
        this.docentesMujeres = docentesMujeres;
        this.docentesTotales = docentesTotales;
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public int getEscuelasPublicas() {
        return escuelasPublicas;
    }

    public void setEscuelasPublicas(int escuelasPublicas) {
        this.escuelasPublicas = escuelasPublicas;
    }

    public int getEscuelasPrivadas() {
        return escuelasPrivadas;
    }

    public void setEscuelasPrivadas(int escuelasPrivadas) {
        this.escuelasPrivadas = escuelasPrivadas;
    }

    public int getAlumnosHombres() {
        return alumnosHombres;
    }

    public void setAlumnosHombres(int alumnosHombres) {
        this.alumnosHombres = alumnosHombres;
    }

    public int getAlumnosMujeres() {
        return alumnosMujeres;
    }

    public void setAlumnosMujeres(int alumnosMujeres) {
        this.alumnosMujeres = alumnosMujeres;
    }

    public int getDocentesHombres() {
        return docentesHombres;
    }

    public void setDocentesHombres(int docentesHombres) {
        this.docentesHombres = docentesHombres;
    }

    public int getDocentesMujeres() {
        return docentesMujeres;
    }

    public void setDocentesMujeres(int docentesMujeres) {
        this.docentesMujeres = docentesMujeres;
    }

    public int getDocentesTotales() {
        return docentesTotales;
    }

    public void setDocentesTotales(int docentesTotales) {
        this.docentesTotales = docentesTotales;
    }

    @Override
    public String toString() {
        return "Educacion{" +
                "nivelEducativo='" + nivelEducativo + '\'' +
                ", escuelasPublicas=" + escuelasPublicas +
                ", escuelasPrivadas=" + escuelasPrivadas +
                ", alumnosHombres=" + alumnosHombres +
                ", alumnosMujeres=" + alumnosMujeres +
                ", docentesHombres=" + docentesHombres +
                ", docentesMujeres=" + docentesMujeres +
                ", docentesTotales=" + docentesTotales +
                '}';
    }
}
