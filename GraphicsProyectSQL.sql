
-- Tablitas Salud
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

-- Nacional (México)
-- Datos Salud: Gobierno de México
INSERT INTO Salud_Gobierno_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 568785);
INSERT INTO Salud_Gobierno_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 459352);
INSERT INTO Salud_Gobierno_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 4899744);
INSERT INTO Salud_Gobierno_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 3911142);
INSERT INTO Salud_Gobierno_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 3907482);

-- Datos Salud: ONG - Organización Mundial de la Salud (OMS)
INSERT INTO Salud_ONG_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 1263914);
INSERT INTO Salud_ONG_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 1054452);
INSERT INTO Salud_ONG_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 4364679);
INSERT INTO Salud_ONG_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 10762883);
INSERT INTO Salud_ONG_Nacional (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 9017637);

-- Estatal (Coahuila)
-- Datos Salud: Gobierno del Estado (Estimación de meta oficial)
INSERT INTO Salud_Gobierno_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 36117);
INSERT INTO Salud_Gobierno_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 29168);
INSERT INTO Salud_Gobierno_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 311133);
INSERT INTO Salud_Gobierno_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 248357);
INSERT INTO Salud_Gobierno_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 248125);

-- Datos Salud: ONG - Estimación teórica estatal OMS
INSERT INTO Salud_ONG_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 80258);
INSERT INTO Salud_ONG_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 66957);
INSERT INTO Salud_ONG_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 277157);
INSERT INTO Salud_ONG_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 683443);
INSERT INTO Salud_ONG_Coahuila (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 572620);

-- Municipal (Torreón)
-- Datos Salud: Gobierno Municipal (Estimación de meta regional)
INSERT INTO Salud_Gobierno_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 3395);
INSERT INTO Salud_Gobierno_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 2741);
INSERT INTO Salud_Gobierno_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 29246);
INSERT INTO Salud_Gobierno_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 23345);
INSERT INTO Salud_Gobierno_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 23323);

-- Datos Salud: ONG - Estimación teórica municipal OMS
INSERT INTO Salud_ONG_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Embarazadas', 7544);
INSERT INTO Salud_ONG_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Personal de Salud', 6293);
INSERT INTO Salud_ONG_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Niños (6-59 meses)', 26052);
INSERT INTO Salud_ONG_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Adultos Mayores (60+)', 64243);
INSERT INTO Salud_ONG_Torreon (Anio, Categoria_Poblacion, Dosis_Aplicadas) VALUES (2023, 'Enfermedades Crónicas', 53826);

-- Tablitas Seguridad
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

-- Nacional (México)
-- Datos Oficiales Gobierno (Extraídos del SESNSP 2023)
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violencia familiar', 284140);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Narcomenudeo', 90088);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Lesiones dolosas', 166000);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a casa habitación', 58000);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a negocio', 78000);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violación', 22700);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Homicidio doloso', 29675);
INSERT INTO Seguridad_Gobierno_Nacional (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Extorsión', 10500);

-- Datos ONG (Según el INEGI debido a Cifra Negra Nacional de 92.4%)
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violencia familiar', 3736441);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Narcomenudeo', 1184657);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Lesiones dolosas', 2182900);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a casa habitación', 762700);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a negocio', 1025700);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violación', 298400);
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Homicidio doloso', 29675); 
INSERT INTO Seguridad_ONG_Nacional (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Extorsión', 138075); 

-- Estatal (Coahuila)
-- Datos Oficiales Gobierno (Extraídos del SESNSP 2023)
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violencia familiar', 12379);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Narcomenudeo', 9054);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Lesiones dolosas', 4251);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a casa habitación', 1616);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a negocio', 687);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violación', 494);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Homicidio doloso', 116);
INSERT INTO Seguridad_Gobierno_Coahuila (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Extorsión', 30);

-- Datos ONG (Según el INEGI debido a Cifra Negra Estatal de 91.8%)
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violencia familiar', 150963);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Narcomenudeo', 110414);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Lesiones dolosas', 51841);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a casa habitación', 19707);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a negocio', 8378);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violación', 6024);
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Homicidio doloso', 116); 
INSERT INTO Seguridad_ONG_Coahuila (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Extorsión', 365); 

-- Municipal (Torreón)
-- Datos Oficiales Gobierno (Aproximación SESNSP 2023)
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violencia familiar', 3500);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Narcomenudeo', 3200);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Lesiones dolosas', 1100);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a casa habitación', 450);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Robo a negocio', 250);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Violación', 150);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Homicidio doloso', 35);
INSERT INTO Seguridad_Gobierno_Torreon (Anio, Tipo_Delito, Casos_Oficiales) VALUES (2023, 'Extorsión', 10);

-- Datos ONG (Según el INEGI debido a Cifra Negra Municipal de 91.8%)
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violencia familiar', 42665);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Narcomenudeo', 39008);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Lesiones dolosas', 13409);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a casa habitación', 5485);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Robo a negocio', 3047);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Violación', 1828);
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Homicidio doloso', 35); 
INSERT INTO Seguridad_ONG_Torreon (Anio, Tipo_Delito, Casos_Estimados_Reales) VALUES (2023, 'Extorsión', 121); 

-- Tablitas Educación
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

-- Nacional (México)
-- Datos Educación: Oficiales Gobierno (Principales Cifras SEP 2023-2024 con estimación de género docente)
INSERT INTO Educacion_Gobierno_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 74516, 13290, 2109658, 2133546, 6944, 224523, 231467);
INSERT INTO Educacion_Gobierno_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 87260, 9189, 6473999, 6680355, 171596, 400390, 571986);
INSERT INTO Educacion_Gobierno_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 36530, 5716, 3117447, 3166930, 188079, 229874, 417953);
INSERT INTO Educacion_Gobierno_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 14736, 6513, 2867777, 2640795, 212913, 212913, 425826);
INSERT INTO Educacion_Gobierno_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 3186, 5780, 2909002, 2484385, 288880, 207477, 496357);

-- Datos Educación: ONG
INSERT INTO Educacion_ONG_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 74516, 13290, 1898692, 1920191, 6944, 224523, 231467);
INSERT INTO Educacion_ONG_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 87260, 9189, 5826599, 6012319, 171596, 400390, 571986);
INSERT INTO Educacion_ONG_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 36530, 5716, 2805702, 2850237, 188079, 229874, 417953);
INSERT INTO Educacion_ONG_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 14736, 6513, 2581000, 2376715, 212913, 212913, 425826);
INSERT INTO Educacion_ONG_Nacional (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 3186, 5780, 2618101, 2235946, 288880, 207477, 496357);

-- Estatal (Coahuila)
-- Datos Educación: Oficiales Gobierno (Principales Cifras SEP 2023-2024 con estimación de género docente)
INSERT INTO Educacion_Gobierno_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 1395, 422, 64844, 63631, 160, 5175, 5335);
INSERT INTO Educacion_Gobierno_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 1582, 257, 170042, 165134, 3737, 8720, 12457);
INSERT INTO Educacion_Gobierno_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 462, 199, 78258, 76998, 5325, 6508, 11833);
INSERT INTO Educacion_Gobierno_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 374, 328, 64808, 71813, 5844, 5845, 11689);
INSERT INTO Educacion_Gobierno_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 110, 168, 67536, 73656, 7632, 5481, 13113);

-- Datos Educación: ONG
INSERT INTO Educacion_ONG_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 1395, 422, 58359, 57267, 160, 5175, 5335);
INSERT INTO Educacion_ONG_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 1582, 257, 153037, 148620, 3737, 8720, 12457);
INSERT INTO Educacion_ONG_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 462, 199, 70432, 69298, 5325, 6508, 11833);
INSERT INTO Educacion_ONG_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 374, 328, 58327, 64631, 5844, 5845, 11689);
INSERT INTO Educacion_ONG_Coahuila (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 110, 168, 60782, 66290, 7632, 5481, 13113);

-- Municipal (Torreón)
-- Datos Educación: Oficiales Gobierno (Extraídos de la SEP 2022-2023)
INSERT INTO Educacion_Gobierno_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 202, 167, 13845, 13843, 12, 1117, 1129);
INSERT INTO Educacion_Gobierno_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 234, 99, 35302, 34037, 631, 1980, 2611);
INSERT INTO Educacion_Gobierno_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 66, 73, 16682, 16411, 779, 1063, 1842);
INSERT INTO Educacion_Gobierno_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 47, 119, 14426, 14736, 1284, 1482, 2766);
INSERT INTO Educacion_Gobierno_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 22, 42, 18667, 20253, 2466, 1772, 4238);

-- Datos Educación: ONG - Consejo Cívico de las Instituciones (CCI) Laguna
INSERT INTO Educacion_ONG_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Preescolar', 202, 167, 13845, 13843, 14, 1198, 1212);
INSERT INTO Educacion_ONG_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Primaria', 234, 99, 35302, 34037, 640, 1995, 2635);
INSERT INTO Educacion_ONG_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Secundaria', 66, 73, 16682, 16411, 1301, 1556, 2857);
INSERT INTO Educacion_ONG_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Media Superior', 43, 117, 13892, 14290, 1249, 1428, 2677);
INSERT INTO Educacion_ONG_Torreon (Nivel_Educativo, Escuelas_Publicas, Escuelas_Privadas, Alumnos_Hombres, Alumnos_Mujeres, Docentes_Hombres, Docentes_Mujeres, Docentes_Totales) VALUES ('Superior', 7, 35, 16747, 17929, 1906, 1369, 3275);