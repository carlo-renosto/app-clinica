
CREATE SCHEMA ClinicaMedica;

USE ClinicaMedica;

CREATE TABLE Usuario
(
id int NOT NULL PRIMARY KEY,
dni int NOT NULL,
nombre char(20) NOT NULL,
contra char(20) NOT NULL,
esAdmin bit NOT NULL,
estado boolean DEFAULT TRUE NOT NULL
);

CREATE TABLE Especialidad
(
Id int NOT NULL PRIMARY KEY,
Nombre varchar(30) NOT NULL
);

CREATE TABLE Paciente
(
Dni int NOT NULL PRIMARY KEY,
Nombre varchar(20) NOT NULL,
Apellido varchar(20)NOT NULL,
Sexo varchar(10)NOT NULL,
Nacionalidad varchar (30)NOT NULL,
Fecha_nac varchar (20)NOT NULL,
Direccion varchar(50)NOT NULL,
Localidad varchar(20)NOT NULL,
Correo_Electronico varchar(50)NOT NULL,
Telefono varchar(20)NOT NULL,
Provincia varchar(20)NOT NULL,
Estado boolean DEFAULT TRUE NOT NULL
);

CREATE TABLE Medico
(
Dni int NOT NULL PRIMARY KEY,
Nombre varchar(20) NOT NULL,
Apellido varchar(20)NOT NULL,
Sexo varchar(10)NOT NULL,
Nacionalidad varchar (30)NOT NULL,
Fecha_nac varchar (20)NOT NULL,
Direccion varchar(50)NOT NULL,
Localidad varchar(20)NOT NULL,
Correo_Electronico varchar(50)NOT NULL,
Telefono varchar(20)NOT NULL,
Provincia varchar(20)NOT NULL,
Especialidad varchar(30) NOT NULL,
Dia_Atencion char(20) NOT NULL,
Hora_Inicio_Atencion int NOT NULL,
Hora_Fin_Atencion int NOT NULL,
Estado boolean DEFAULT TRUE NOT NULL
);

CREATE TABLE Turno
(
Id int NOT NULL PRIMARY KEY,
Especialidad varchar(30) NOT NULL,
Dni_Medico int NOT NULL,
Dni_Paciente int NOT NULL, 
Dia_Turno char(20) NOT NULL,
Hora_Turno int NOT NULL,
Observacion varchar(30),
Estado char(15) DEFAULT 'Libre' NOT NULL
);

INSERT INTO Usuario(id, dni, nombre, contra, esAdmin) VALUES(1, 1, 'Admin', 'admin', 1);
INSERT INTO Usuario(id, dni, nombre, contra, esAdmin) VALUES(2, 1237, 'jorge rodriguez', 'med1', 0);
INSERT INTO Usuario(id, dni, nombre, contra, esAdmin) VALUES(3, 1238, 'oscar peralta', 'med2', 0);
INSERT INTO Usuario(id, dni, nombre, contra, esAdmin) VALUES(4, 1239, 'lucia perez', 'med3', 0);
INSERT INTO Usuario(id, dni, nombre, contra, esAdmin) VALUES(5, 1240, 'maria gonzalez', 'med4', 0);
INSERT INTO Usuario(id, dni, nombre, contra, esAdmin) VALUES(6, 1241, 'fernando lopez', 'med5', 0);
INSERT INTO Usuario(id, dni, nombre, contra, esAdmin) VALUES(7, 1242, 'ana martinez', 'med6', 0);
INSERT INTO Usuario(id, dni, nombre, contra, esAdmin) VALUES(8, 1243, 'gabriel rodriguez', 'med7', 0);
INSERT INTO Usuario(id, dni, nombre, contra, esAdmin) VALUES(9, 1244, 'carolina fernandez', 'med8', 0);
INSERT INTO Usuario(id, dni, nombre, contra, esAdmin) VALUES(10, 1245, 'juan gomez', 'med9', 0);


INSERT INTO Especialidad(Id, Nombre) VALUES(1, 'Urologia');
INSERT INTO Especialidad(Id, Nombre) VALUES(2, 'Cardiologia');
INSERT INTO Especialidad(Id, Nombre) VALUES(3, 'Odontologia');
INSERT INTO Especialidad(Id, Nombre) VALUES(4, 'Neurologia');
INSERT INTO Especialidad(Id, Nombre) VALUES(5, 'Anestesiologia');
INSERT INTO Especialidad(Id, Nombre) VALUES(6, 'Angiologia');
INSERT INTO Especialidad(Id, Nombre) VALUES(7, 'Pediatria');
INSERT INTO Especialidad(Id, Nombre) VALUES(8, 'Psiquiatria');
INSERT INTO Especialidad(Id, Nombre) VALUES(9, 'Infectologia');
INSERT INTO Especialidad(Id, Nombre) VALUES(10, 'Genetica');
INSERT INTO Especialidad(Id, Nombre) VALUES(11, 'Endocrinologia');
INSERT INTO Especialidad(Id, Nombre) VALUES(12, 'Nutriologia');
INSERT INTO Especialidad(Id, Nombre) VALUES(13, 'Oncologia');
INSERT INTO Especialidad(Id, Nombre) VALUES(14, 'Toxicologia');
INSERT INTO Especialidad(Id, Nombre) VALUES(15, 'Hematologia');

INSERT INTO Paciente(Dni,Nombre,Apellido,Sexo,Nacionalidad,Fecha_nac,Direccion,Localidad,Correo_Electronico,Telefono,Provincia) VALUES(1222,'hugo','marquez','Masculino','Argentina','20/05/1996','calle falsa 123','Pilar','abc@gmail.com','11556622','Buenos Aires');
INSERT INTO Paciente(Dni,Nombre,Apellido,Sexo,Nacionalidad,Fecha_nac,Direccion,Localidad,Correo_Electronico,Telefono,Provincia) VALUES(1223,'margarita','zipola','Femenino','Uruguay','23/06/1998','calle real 123','La Calera','anc@gmail.com','11558822','Cordoba');
INSERT INTO Paciente(Dni,Nombre,Apellido,Sexo,Nacionalidad,Fecha_nac,Direccion,Localidad,Correo_Electronico,Telefono,Provincia) VALUES(1224,'mariano','fernandez','Otro','Brasil','28/09/1988','av.libertador 900','Godoy Cruz','anp@gmail.com','11659822','Mendoza');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1225, 'Laura', 'González', 'Femenino', 'Argentina', '12/03/1990', 'Avenida Corrientes 456', 'Pilar', 'lgonzalez@gmail.com', '11223344', 'Buenos Aires');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1226, 'Juan', 'López', 'Masculino', 'Uruguay', '04/08/1985', 'Calle San Martín 789', 'Montecristo', 'jlopez@gmail.com', '11556677', 'Cordoba');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1227, 'Carolina', 'Fernández', 'Femenino', 'Brasil', '19/11/1992', 'Calle Mitre 321', 'Pilar', 'cfernandez@gmail.com', '11667788', 'Buenos Aires');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1228, 'Martín', 'Rodríguez', 'Masculino', 'Argentina', '08/07/1991', 'Avenida Rivadavia 654', 'Pilar', 'mrodriguez@gmail.com', '11224466', 'Buenos Aires');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1229, 'María', 'Sánchez', 'Femenino', 'Uruguay', '25/09/1980', 'Calle Belgrano 987', 'Guaymallen', 'msanchez@gmail.com', '11668899', 'Mendoza');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1230, 'Lucas', 'Giménez', 'Masculino', 'Argentina', '14/02/1982', 'Avenida Independencia 234', 'La Paz', 'lgimenez@gmail.com', '11336699', 'Mendoza');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1231, 'Florencia', 'López', 'Femenino', 'Argentina', '29/06/1993', 'Calle Sarmiento 567', 'Los Cedros', 'flopez@gmail.com', '11223355', 'Cordoba');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1232, 'Gustavo', 'Ríos', 'Masculino', 'Uruguay', '11/11/1988', 'Avenida Mayo 345', 'Moreno', 'grios@gmail.com', '11558899', 'Buenos Aires');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1233, 'Valentina', 'Martínez', 'Femenino', 'Argentina', '17/10/1995', 'Calle San Juan 876', 'Guaymallen', 'vmartinez@gmail.com', '11335577', 'Mendoza');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1234, 'Luis', 'Gómez', 'Masculino', 'Brasil', '03/09/1987', 'Avenida Santa Fe 432', 'Benavidez', 'lgomez@gmail.com', '11227788', 'Buenos Aires');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1235, 'Camila', 'Torres', 'Femenino', 'Argentina', '09/12/1997', 'Calle Maipú 678', 'Moreno', 'ctorres@gmail.com', '11669988', 'Buenos Aires');
INSERT INTO Paciente(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia) VALUES(1236, 'Pablo', 'Mendoza', 'Masculino', 'Brasil', '21/04/1994', 'Avenida San Martín 567', 'La Calera', 'pmendoza@gmail.com', '11559977', 'Cordoba');


INSERT INTO Medico(Dni,Nombre,Apellido,Sexo,Nacionalidad,Fecha_nac,Direccion,Localidad,Correo_Electronico,Telefono,Provincia,Especialidad,Dia_Atencion,Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1237,'jorge','rodriguez','Masculino','Argentina','28/09/1978','av.libertador 240','Pilar','jrg@gmail.com','1152313','Buenos Aires', "Urologia", 'Lunes', 8, 18);
INSERT INTO Medico(Dni,Nombre,Apellido,Sexo,Nacionalidad,Fecha_nac,Direccion,Localidad,Correo_Electronico,Telefono,Provincia,Especialidad,Dia_Atencion,Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1238,'oscar','peralta','Masculino','Argentina','23/04/1965','av.rosario 253','La Calera','osp@gmail.com','11034923','Cordoba', "Cardiologia", 'Miercoles', 10, 18);
INSERT INTO Medico(Dni,Nombre,Apellido,Sexo,Nacionalidad,Fecha_nac,Direccion,Localidad,Correo_Electronico,Telefono,Provincia,Especialidad,Dia_Atencion,Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1239,'lucia','perez','Femenino','Brasil','15/08/1990','av.marquez 1500','Godoy Cruz','lup@gmail.com','11034912','Mendoza', "Odontologia", 'Jueves', 9, 17);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1240, 'María', 'González', 'Femenino', 'Argentina', '10/05/1985', 'Calle San Martín 567', 'Pilar', 'mgonzalez@gmail.com', '11558877', 'Buenos Aires', "Neurologia", 'Lunes', 8, 18);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1241, 'Fernando', 'López', 'Masculino', 'Brasil', '15/06/1976', 'Avenida Corrientes 123', 'Pilar', 'flopez@gmail.com', '11223366', 'Buenos Aires', "Anestesiologia", 'Martes', 9, 18);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1242, 'Ana', 'Martínez', 'Femenino', 'Uruguay', '20/07/1988', 'Calle Rivadavia 456', 'Montecristo', 'amartinez@gmail.com', '11556699', 'Cordoba', "Angiologia", 'Miercoles', 10, 16);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1243, 'Gabriel', 'Rodríguez', 'Masculino', 'Brasil', '05/09/1992', 'Avenida Mayo 789', 'Moreno', 'grodriguez@gmail.com', '11667722', 'Buenos Aires', "Pediatria", 'Jueves', 10, 18);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1244, 'Carolina', 'Fernández', 'Femenino', 'Uruguay', '10/03/1985', 'Calle San Juan 456', 'La Paz', 'cfernandez@gmail.com', '11558899', 'Mendoza', "Psiquiatria", 'Viernes', 10, 18);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1245, 'Juan', 'Gómez', 'Masculino', 'Argentina', '18/06/1980', 'Avenida San Martín 234', 'Guaymallen', 'jgomez@gmail.com', '11224455', 'Mendoza', "Infectologia", 'Lunes', 8, 16);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1246, 'Marcela', 'Torres', 'Femenino', 'Uruguay', '22/09/1982', 'Calle Belgrano 987', 'Los Cedros', 'mtorres@gmail.com', '11669911', 'Cordoba', "Genetica", 'Miercoles', 10, 17);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1247, 'Luis', 'Mendoza', 'Masculino', 'Argentina', '07/12/1995', 'Avenida Independencia 654', 'Pilar', 'lmendoza@gmail.com', '11336699', 'Buenos Aires', "Endocrinologia", 'Jueves', 9, 17);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1248, 'Camila', 'Sánchez', 'Femenino', 'Uruguay', '14/02/1987', 'Calle Sarmiento 567', 'Montecristo', 'csanchez@gmail.com', '11223355', 'Cordoba', "Nutriologia", 'Viernes', 9, 18);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1249, 'Gustavo', 'Pérez', 'Masculino', 'Argentina', '29/06/1990', 'Avenida Mayo 345', 'Benavidez', 'gperez@gmail.com', '11558888', 'Buenos Aires', "Oncologia", 'Lunes', 8, 10);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1250, 'Valentina', 'Martínez', 'Femenino', 'Argentina', '17/10/1985', 'Calle San Juan 876', 'Guaymallen', 'vmartinez@gmail.com', '11335577', 'Mendoza', "Toxicologia", 'Miercoles', 8, 18);
INSERT INTO Medico(Dni, Nombre, Apellido, Sexo, Nacionalidad, Fecha_nac, Direccion, Localidad, Correo_Electronico, Telefono, Provincia, Especialidad, Dia_Atencion, Hora_Inicio_Atencion, Hora_Fin_Atencion) VALUES(1251, 'Santiago', 'Martinez', 'Masculino', 'Argentina', '28/12/1996', 'Avenida libertador 15600', 'Moreno', 'smartinez@gmail.com', '11335236', 'Buenos Aires', "Hematologia", 'Miércoles', 8, 18);

INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(1, 'Urologia', 1237, 1222, 'Lunes', 8, '', 'Ocupado');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(2, 'Odontologia', 1239, 1227, 'Jueves', 8, '', 'Ocupado');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(3, 'Cardiologia', 1238, 1236, 'Miercoles', 16, '', 'Ocupado');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(4, 'Neurologia', 1240, 1224, 'Lunes', 16, '', 'Presente');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(5, 'Anestesiologia', 1241, 1235, 'Martes', 15, '', 'Ausente');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(6, 'Angiologia', 1242, 1231, 'Miercoles', 16, '', 'Libre');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(7, 'Pediatria', 1243, 1230, 'Jueves', 16, '', 'Ocupado');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(8, 'Psiquiatria', 1244, 1223, 'Viernes', 16, '', 'Presente');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(9, 'Infectologia', 1245, 1226, 'Lunes', 10, '', 'Ocupado');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(10, 'Genetica', 1246, 1227, 'Miercoles', 16, '', 'Ocupado');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(11, 'Endocrinologia', 1247, 1229, 'Jueves', 16, '', 'Ocupado');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(12, 'Nutriologia', 1248, 1232, 'Viernes', 12, '', 'Ocupado');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(13, 'Oncologia', 1249, 1234, 'Lunes', 14, '', 'Ocupado');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(14, 'Toxicologia', 1250, 1235, 'Miercoles', 14, '', 'Ocupado');
INSERT INTO Turno(Id, Especialidad, Dni_Medico, Dni_Paciente, Dia_Turno, Hora_Turno, Observacion, Estado) VALUES(15, 'Hematologia', 1251, 1228, 'Miercoles', 10, '', 'Ocupado');
