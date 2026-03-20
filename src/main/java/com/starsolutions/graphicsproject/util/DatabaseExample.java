package com.starsolutions.graphicsproject.util;

import com.starsolutions.graphicsproject.dao.SaludDAO;
import com.starsolutions.graphicsproject.dao.SeguridadDAO;
import com.starsolutions.graphicsproject.dao.EducacionDAO;
import com.starsolutions.graphicsproject.model.Salud;
import com.starsolutions.graphicsproject.model.Seguridad;
import com.starsolutions.graphicsproject.model.Educacion;
import com.starsolutions.graphicsproject.database.DatabaseInitializer;

import java.util.List;

/**
 * Clase de ejemplo para demostrar el uso de los DAOs
 */
public class DatabaseExample {

    public static void main(String[] args) {
        // Inicializar la base de datos (crear tablas)
        DatabaseInitializer.initializeDatabse();

        // Insertar datos iniciales (comentar si ya existen los datos)
        // DatabaseInitializer.insertInitialData();

        // Ejemplos de uso de SaludDAO
        System.out.println("\n--- DATOS DE SALUD ---");
        SaludDAO saludDAO = new SaludDAO();

        // Obtener todos los datos de Salud_Gobierno
        System.out.println("\nTodos los datos de Salud_Gobierno:");
        List<Salud> saludGobierno = saludDAO.obtenerSaludGobierno();
        for (Salud salud : saludGobierno) {
            System.out.println(salud);
        }

        // Obtener todos los datos de Salud_ONG
        System.out.println("\nTodos los datos de Salud_ONG:");
        List<Salud> saludONG = saludDAO.obtenerSaludONG();
        for (Salud salud : saludONG) {
            System.out.println(salud);
        }

        // Obtener datos de Salud_Gobierno por año
        System.out.println("\nDatos de Salud_Gobierno para el año 2023:");
        List<Salud> saludPorAnio = saludDAO.obtenerSaludGobiernoPorAnio(2023);
        for (Salud salud : saludPorAnio) {
            System.out.println(salud);
        }

        // Ejemplos de uso de SeguridadDAO
        System.out.println("\n--- DATOS DE SEGURIDAD ---");
        SeguridadDAO seguridadDAO = new SeguridadDAO();

        // Obtener todos los datos de Seguridad_Gobierno_Coahuila
        System.out.println("\nTodos los datos de Seguridad_Gobierno_Coahuila:");
        List<Seguridad> seguridadGobierno = seguridadDAO.obtenerSeguridadGobierno();
        for (Seguridad seguridad : seguridadGobierno) {
            System.out.println(seguridad);
        }

        // Obtener todos los datos de Seguridad_Estimacion_ONG
        System.out.println("\nTodos los datos de Seguridad_Estimacion_ONG:");
        List<Seguridad> seguridadONG = seguridadDAO.obtenerSeguridadONG();
        for (Seguridad seguridad : seguridadONG) {
            System.out.println(seguridad);
        }

        // Ejemplos de uso de EducacionDAO
        System.out.println("\n--- DATOS DE EDUCACIÓN ---");
        EducacionDAO educacionDAO = new EducacionDAO();

        // Obtener todos los datos de Educacion_Gobierno_Torreon
        System.out.println("\nTodos los datos de Educacion_Gobierno_Torreon:");
        List<Educacion> educacionGobierno = educacionDAO.obtenerEducacionGobierno();
        for (Educacion educacion : educacionGobierno) {
            System.out.println(educacion);
        }

        // Obtener todos los datos de Educacion_ONG_Torreon
        System.out.println("\nTodos los datos de Educacion_ONG_Torreon:");
        List<Educacion> educacionONG = educacionDAO.obtenerEducacionONG();
        for (Educacion educacion : educacionONG) {
            System.out.println(educacion);
        }

        // Obtener un registro específico de Educacion_Gobierno_Torreon
        System.out.println("\nDatos de Educacion_Gobierno_Torreon para Primaria:");
        Educacion educacionPrimaria = educacionDAO.obtenerEducacionGobiernoPorNivel("Primaria");
        if (educacionPrimaria != null) {
            System.out.println(educacionPrimaria);
        }
    }
}
