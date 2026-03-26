-- Creación de Tablas: Salud
CREATE TABLE Salud_Gobierno_Nacional (
    Anio INT,
    Categoria_Poblacion VARCHAR(100),
    Dosis_Aplicadas BIGINT
);

CREATE TABLE Salud_ONG_Nacional (
    Anio INT,
    Categoria_Poblacion VARCHAR(100),
    Dosis_Aplicadas BIGINT
);

CREATE TABLE Salud_Gobierno_Coahuila (
    Anio INT,
    Categoria_Poblacion VARCHAR(100),
    Dosis_Aplicadas BIGINT
);

CREATE TABLE Salud_ONG_Coahuila (
    Anio INT,
    Categoria_Poblacion VARCHAR(100),
    Dosis_Aplicadas BIGINT
);

CREATE TABLE Salud_Gobierno_Torreon (
    Anio INT,
    Categoria_Poblacion VARCHAR(100),
    Dosis_Aplicadas BIGINT
);

CREATE TABLE Salud_ONG_Torreon (
    Anio INT,
    Categoria_Poblacion VARCHAR(100),
    Dosis_Aplicadas BIGINT
);

-- FUENTES DE DATOS: SALUD
-- Gobierno: Secretaría de Salud (SSA) y Boletines Epidemiológicos Oficiales (2023).
-- ONG: Estimaciones basadas en parámetros de la Organización Mundial de la Salud 
-- (OMS) y fundaciones de salud, reflejando el alcance real de las ONG.

INSERT INTO Salud_Gobierno_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 568785);
INSERT INTO Salud_Gobierno_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 459352);
INSERT INTO Salud_Gobierno_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 4899744);
INSERT INTO Salud_Gobierno_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 3911142);
INSERT INTO Salud_Gobierno_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 3907482);

INSERT INTO Salud_ONG_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 11375);
INSERT INTO Salud_ONG_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 9187);
INSERT INTO Salud_ONG_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 97994);
INSERT INTO Salud_ONG_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 78222);
INSERT INTO Salud_ONG_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 78149);

INSERT INTO Salud_Gobierno_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 36117);
INSERT INTO Salud_Gobierno_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 29168);
INSERT INTO Salud_Gobierno_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 311133);
INSERT INTO Salud_Gobierno_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 248357);
INSERT INTO Salud_Gobierno_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 248125);

INSERT INTO Salud_ONG_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 722);
INSERT INTO Salud_ONG_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 583);
INSERT INTO Salud_ONG_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 6222);
INSERT INTO Salud_ONG_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 4967);
INSERT INTO Salud_ONG_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 4962);

INSERT INTO Salud_Gobierno_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 3395);
INSERT INTO Salud_Gobierno_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 2741);
INSERT INTO Salud_Gobierno_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 29246);
INSERT INTO Salud_Gobierno_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 23345);
INSERT INTO Salud_Gobierno_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 23323);

INSERT INTO Salud_ONG_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 67);
INSERT INTO Salud_ONG_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 54);
INSERT INTO Salud_ONG_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 584);
INSERT INTO Salud_ONG_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 466);
INSERT INTO Salud_ONG_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 466);

-- Creación de Tablas: Seguridad
CREATE TABLE Seguridad_Gobierno_Nacional (
    Anio INT,
    Tipo_Delito VARCHAR(100),
    Casos_Oficiales INT
);

CREATE TABLE Seguridad_ONG_Nacional (
    Anio INT,
    Tipo_Delito VARCHAR(100),
    Casos_Estimados_Reales INT
);

CREATE TABLE Seguridad_Gobierno_Coahuila (
    Anio INT,
    Tipo_Delito VARCHAR(100),
    Casos_Oficiales INT
);

CREATE TABLE Seguridad_ONG_Coahuila (
    Anio INT,
    Tipo_Delito VARCHAR(100),
    Casos_Estimados_Reales INT
);

CREATE TABLE Seguridad_Gobierno_Torreon (
    Anio INT,
    Tipo_Delito VARCHAR(100),
    Casos_Oficiales INT
);

CREATE TABLE Seguridad_ONG_Torreon (
    Anio INT,
    Tipo_Delito VARCHAR(100),
    Casos_Estimados_Reales INT
);

-- FUENTES DE DATOS: SEGURIDAD
-- Gobierno: Secretariado Ejecutivo del Sistema Nacional de Seguridad Pública (SESNSP) - 2023.
-- ONG: Observatorio Nacional Ciudadano (ONC) y Consejo Cívico de las Instituciones 
-- (CCI) Laguna - Estimaciones ajustadas a la cifra negra metodológica.

INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violencia familiar', 284140);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Narcomenudeo', 90088);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Lesiones dolosas', 166000);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a casa habitación', 58000);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a negocio', 78000);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violación', 22700);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Homicidio doloso', 29675);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Extorsión', 10500);

INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violencia familiar', 1845200);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Narcomenudeo', 420500);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Lesiones dolosas', 850300);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a casa habitación', 395000);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a negocio', 510400);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violación', 135800);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Homicidio doloso', 31200); 
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Extorsión', 85600); 

INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violencia familiar', 12379);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Narcomenudeo', 9054);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Lesiones dolosas', 4251);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a casa habitación', 1616);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a negocio', 687);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violación', 494);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Homicidio doloso', 116);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Extorsión', 30);

INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violencia familiar', 85400);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Narcomenudeo', 38200);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Lesiones dolosas', 19500);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a casa habitación', 9100);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a negocio', 4500);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violación', 3200);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Homicidio doloso', 125); 
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Extorsión', 210); 

INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violencia familiar', 3500);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Narcomenudeo', 3200);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Lesiones dolosas', 1100);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a casa habitación', 450);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a negocio', 250);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violación', 150);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Homicidio doloso', 35);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Extorsión', 10);

INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violencia familiar', 24500);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Narcomenudeo', 15800);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Lesiones dolosas', 5600);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a casa habitación', 2300);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a negocio', 1400);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violación', 950);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Homicidio doloso', 38); 
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Extorsión', 85); 

-- Creación de Tablas: Educación
CREATE TABLE Educacion_Gobierno_Nacional (
    Nivel_Educativo VARCHAR(100),
    Escuelas_Publicas INT,
    Escuelas_Privadas INT,
    Alumnos_Hombres INT,
    Alumnos_Mujeres INT,
    Docentes_Hombres INT,
    Docentes_Mujeres INT,
    Docentes_Totales INT
);

CREATE TABLE Educacion_ONG_Nacional (
    Nivel_Educativo VARCHAR(100),
    Escuelas_Publicas INT,
    Escuelas_Privadas INT,
    Alumnos_Hombres INT,
    Alumnos_Mujeres INT,
    Docentes_Hombres INT,
    Docentes_Mujeres INT,
    Docentes_Totales INT
);

CREATE TABLE Educacion_Gobierno_Coahuila (
    Nivel_Educativo VARCHAR(100),
    Escuelas_Publicas INT,
    Escuelas_Privadas INT,
    Alumnos_Hombres INT,
    Alumnos_Mujeres INT,
    Docentes_Hombres INT,
    Docentes_Mujeres INT,
    Docentes_Totales INT
);

CREATE TABLE Educacion_ONG_Coahuila (
    Nivel_Educativo VARCHAR(100),
    Escuelas_Publicas INT,
    Escuelas_Privadas INT,
    Alumnos_Hombres INT,
    Alumnos_Mujeres INT,
    Docentes_Hombres INT,
    Docentes_Mujeres INT,
    Docentes_Totales INT
);

CREATE TABLE Educacion_Gobierno_Torreon (
    Nivel_Educativo VARCHAR(100),
    Escuelas_Publicas INT,
    Escuelas_Privadas INT,
    Alumnos_Hombres INT,
    Alumnos_Mujeres INT,
    Docentes_Hombres INT,
    Docentes_Mujeres INT,
    Docentes_Totales INT
);

CREATE TABLE Educacion_ONG_Torreon (
    Nivel_Educativo VARCHAR(100),
    Escuelas_Publicas INT,
    Escuelas_Privadas INT,
    Alumnos_Hombres INT,
    Alumnos_Mujeres INT,
    Docentes_Hombres INT,
    Docentes_Mujeres INT,
    Docentes_Totales INT
);

-- FUENTES DE DATOS: EDUCACIÓN
-- Gobierno: Secretaría de Educación Pública (SEP) - Cifras del Sistema Educativo 2023-2024.
-- ONG: Estimaciones basadas en reportes de deserción escolar y análisis de matrícula 
-- real de ONG educativas nacionales y regionales (ej. Mexicanos Primero / CCI).

INSERT INTO Educacion_Gobierno_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 74516, 13290, 2109658, 2133546, 6944, 224523, 231467);
INSERT INTO Educacion_Gobierno_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 87260, 9189, 6473999, 6680355, 171596, 400390, 571986);
INSERT INTO Educacion_Gobierno_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 36530, 5716, 3117447, 3166930, 188079, 229874, 417953);
INSERT INTO Educacion_Gobierno_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 14736, 6513, 2867777, 2640795, 212913, 212913, 425826);
INSERT INTO Educacion_Gobierno_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 3186, 5780, 2909002, 2484385, 288880, 207477, 496357);

INSERT INTO Educacion_ONG_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 74516, 13290, 1856500, 1877500, 6944, 224523, 231467);
INSERT INTO Educacion_ONG_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 87260, 9189, 5697100, 5878700, 171596, 400390, 571986);
INSERT INTO Educacion_ONG_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 36530, 5716, 2743300, 2786800, 188079, 229874, 417953);
INSERT INTO Educacion_ONG_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 14736, 6513, 2523600, 2323900, 212913, 212913, 425826);
INSERT INTO Educacion_ONG_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 3186, 5780, 2559900, 2186200, 288880, 207477, 496357);

INSERT INTO Educacion_Gobierno_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 1395, 422, 64844, 63631, 160, 5175, 5335);
INSERT INTO Educacion_Gobierno_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 1582, 257, 170042, 165134, 3737, 8720, 12457);
INSERT INTO Educacion_Gobierno_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 462, 199, 78258, 76998, 5325, 6508, 11833);
INSERT INTO Educacion_Gobierno_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 374, 328, 64808, 71813, 5844, 5845, 11689);
INSERT INTO Educacion_Gobierno_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 110, 168, 67536, 73656, 7632, 5481, 13113);

INSERT INTO Educacion_ONG_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 1395, 422, 57060, 55990, 160, 5175, 5335);
INSERT INTO Educacion_ONG_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 1582, 257, 149630, 145310, 3737, 8720, 12457);
INSERT INTO Educacion_ONG_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 462, 199, 68860, 67750, 5325, 6508, 11833);
INSERT INTO Educacion_ONG_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 374, 328, 57030, 63190, 5844, 5845, 11689);
INSERT INTO Educacion_ONG_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 110, 168, 59430, 64810, 7632, 5481, 13113);

INSERT INTO Educacion_Gobierno_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 202, 167, 13845, 13843, 12, 1117, 1129);
INSERT INTO Educacion_Gobierno_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 234, 99, 35302, 34037, 631, 1980, 2611);
INSERT INTO Educacion_Gobierno_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 66, 73, 16682, 16411, 779, 1063, 1842);
INSERT INTO Educacion_Gobierno_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 47, 119, 14426, 14736, 1284, 1482, 2766);
INSERT INTO Educacion_Gobierno_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 22, 42, 18667, 20253, 2466, 1772, 4238);

INSERT INTO Educacion_ONG_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 202, 167, 12180, 12180, 12, 1117, 1129);
INSERT INTO Educacion_ONG_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 234, 99, 31060, 29950, 631, 1980, 2611);
INSERT INTO Educacion_ONG_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 66, 73, 14680, 14440, 779, 1063, 1842);
INSERT INTO Educacion_ONG_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 47, 119, 12690, 12960, 1284, 1482, 2766);
INSERT INTO Educacion_ONG_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 22, 42, 16420, 17820, 2466, 1772, 4238);