use vuelos;

#-------------------------------------------------------------------------
# Insercion de datos de prueba

INSERT INTO ubicaciones VALUES ("Argentina", "Buenos Aires", "Bahia Blanca", -3);
INSERT INTO ubicaciones VALUES ("Argentina", "Entre Rios", "Entre Rios", -3);
INSERT INTO ubicaciones VALUES ("Argentina", "Cordoba", "Cordoba", -3);
INSERT INTO ubicaciones VALUES ("Argentina", "Santa Fe", "Rosario", -3);
INSERT INTO ubicaciones VALUES ("EEUU", "Florida", "Orlando", -2);

INSERT INTO aeropuertos VALUES (0001, "Aero1", 2914464566, "12 de Octubre 45", "Argentina", "Buenos Aires", "Bahia Blanca");
INSERT INTO aeropuertos VALUES (0002, "Aero2", 2923446443, "Alem 4575", "Argentina", "Entre Rios", "Entre Rios");
INSERT INTO aeropuertos VALUES (0003, "Aero3", 3875564976, "Godoy Cruz 1244","Argentina", "Cordoba", "Cordoba");
INSERT INTO aeropuertos VALUES (0004, "Aero4", 2921464452, "Sarmiento 453", "Argentina", "Santa Fe", "Rosario");
INSERT INTO aeropuertos VALUES (0005, "Aero5", 2954452957, "Chile 32", "EEUU", "Florida", "Orlando");

INSERT INTO vuelos_programados VALUES ("BC2", 0001,0003);
INSERT INTO vuelos_programados VALUES ("BC3", 0003,0001);
INSERT INTO vuelos_programados VALUES ("101", 0002,0003);
INSERT INTO vuelos_programados VALUES ("102", 0004,0003);
INSERT INTO vuelos_programados VALUES ("103", 0005,0002);
INSERT INTO vuelos_programados VALUES ("104", 0003,0002);
INSERT INTO vuelos_programados VALUES ("105", 0001,0005);
INSERT INTO vuelos_programados VALUES ("106", 0002,0003);
INSERT INTO vuelos_programados VALUES ("107", 0003,0005);

INSERT INTO modelos_avion VALUES ("M1", "Airbus", 3,250);
INSERT INTO modelos_avion VALUES ("M2", "Samsung", 5,200);
INSERT INTO modelos_avion VALUES ("M3", "Avio S.A", 7,300);
INSERT INTO modelos_avion VALUES ("M4", "Aviadores", 11,330);
INSERT INTO modelos_avion VALUES ("M5", "Volando bajo", 4,42);

INSERT INTO salidas VALUES ("BC2", 'Ma', "10:25:00", "11:40:00", "M1");
INSERT INTO salidas VALUES ("BC3", 'Ma', "10:25:00", "11:40:00", "M1");
INSERT INTO salidas VALUES ("104", 'Do', "10:25:00", "11:40:00", "M1");
INSERT INTO salidas VALUES ("101", 'Lu', "06:25:00", "09:30:00", "M4");
INSERT INTO salidas VALUES ("102", 'Ma', "15:00:00", "17:10:00", "M3");
INSERT INTO salidas VALUES ("103", 'Mi', "11:20:00", "14:40:00", "M2");
INSERT INTO salidas VALUES ("104", 'Ju', "10:40:00", "11:55:00", "M1");
INSERT INTO salidas VALUES ("105", 'Vi', "18:25:00", "19:58:00", "M1");

INSERT INTO instancias_vuelo VALUES ("BC2","2017-01-06", "Ma","Por salir");
INSERT INTO instancias_vuelo VALUES ("BC3","2017-01-13", "Ma","Por salir");
INSERT INTO instancias_vuelo VALUES ("101","2017-12-23", "Lu","Por salir");
INSERT INTO instancias_vuelo VALUES ("104","2017-10-02", "Do","Llegando");
INSERT INTO instancias_vuelo VALUES ("103","2017-01-01", "Mi","Saliendo");
INSERT INTO instancias_vuelo VALUES ("104","2017-12-22", "Ju","Por salir");
INSERT INTO instancias_vuelo VALUES ("105","2017-11-27", "Vi","En espera");

INSERT INTO clases VALUES ("Ejecutivo",0.14);
INSERT INTO clases VALUES ("Turista",0.54);
INSERT INTO clases VALUES ("Raton", 0.60);

INSERT INTO comodidades VALUES (54,"asientos reclinables");
INSERT INTO comodidades VALUES (22,"Desayuno incluido");
INSERT INTO comodidades VALUES (11,"Cena incluida");
INSERT INTO comodidades VALUES (39,"Cena y desayuno");

INSERT INTO pasajeros VALUES ("dni",36328547, "Toniolo", "Franco", "Jujuy 1354","4515743","Argentino");
INSERT INTO pasajeros VALUES ("le",36332112, "Ota", "Lucas", "12 de octubre 322","4125743","Boliviano");
INSERT INTO pasajeros VALUES ("dni",36123123, "Caminolo", "Rodrigo", "Godoy Cruz 12","45112343","Argentino");
INSERT INTO pasajeros VALUES ("dni",36111222, "Parquez", "Esteban", "Trelew 32","4537895","Argentino");
INSERT INTO pasajeros VALUES ("dni",38456321, "Chamin", "Alan", "Santa Cruz 1233","4565544","Aleman");

INSERT INTO empleados VALUES (1234,md5('pwadmin'),"dni",36456789,"Sanchez", "Alan","Roca 123","45678912");
INSERT INTO empleados VALUES (312,md5('pwroot'),"dni",36123987,"Gartner", "Gabriel","Jujuy 1344","45111912");
INSERT INTO empleados VALUES (167,md5('pwpe'),"dni",30123658,"Gartner", "German","Jujuy 1344","45111912");
INSERT INTO empleados VALUES (125,md5('pwgi'),"dni",38156789,"Gonzalez", "Jorge","Espana 511","4569852");
INSERT INTO empleados VALUES (123,md5('pwdot'),"dni",3896547,"Quero", "Emmanuel","Alem 321","45698452");

INSERT INTO reservas(fecha, vencimiento,estado,doc_tipo,doc_nro,legajo) VALUES("2011-11-12","2017-01-01","Pendiente","dni",36328547,1234);
INSERT INTO reservas(fecha, vencimiento,estado,doc_tipo,doc_nro,legajo) VALUES("2012-11-25","2017-01-01","Aceptado","le",36332112,1234);
INSERT INTO reservas(fecha, vencimiento,estado,doc_tipo,doc_nro,legajo) VALUES("2011-11-12","2017-01-01","Pendiente","dni",36328547,312);
INSERT INTO reservas(fecha, vencimiento,estado,doc_tipo,doc_nro,legajo) VALUES("2012-12-24","2017-01-01","En Espera","dni",38456321,125);
INSERT INTO reservas(fecha, vencimiento,estado,doc_tipo,doc_nro,legajo) VALUES("2011-11-12","2017-01-01","Pendiente","dni",36123123,125);
INSERT INTO reservas(fecha, vencimiento,estado,doc_tipo,doc_nro,legajo) VALUES("2010-05-01","2017-01-01","Aceptado","dni",36328547,167);
INSERT INTO reservas(fecha, vencimiento,estado,doc_tipo,doc_nro,legajo) VALUES("2010-05-01","2017-01-01","En Espera","dni",36111222,125);

INSERT INTO brinda VALUES ("BC2", 'Ma',"Ejecutivo",45612.10,46);
INSERT INTO brinda VALUES ("BC3", 'Ma',"Ejecutivo",45612.10,46);
INSERT INTO brinda VALUES ("104", 'Do',"Ejecutivo",45612.10,46);
INSERT INTO brinda VALUES ("104", 'Ju',"Turista",12587.90,50);
INSERT INTO brinda VALUES ("103", 'Mi',"Ejecutivo",45614.00,101);
INSERT INTO brinda VALUES ("105", 'Vi',"Ejecutivo",12365.99,78);
INSERT INTO brinda VALUES ("102", 'Ma',"Raton",78941.50,98);
INSERT INTO brinda VALUES ("105", 'Vi',"Raton",65478.25,46);
INSERT INTO brinda VALUES ("101", 'Lu',"Raton",65478.25,46);

INSERT INTO posee VALUES ("Ejecutivo",54);
INSERT INTO posee VALUES ("Raton",54);
INSERT INTO posee VALUES ("Turista",22);
INSERT INTO posee VALUES ("Ejecutivo",11);
INSERT INTO posee VALUES ("Raton",11);
INSERT INTO posee VALUES ("Turista",39);

INSERT INTO reserva_vuelo_clase VALUES (1,"105","2017-11-27","Raton");
INSERT INTO reserva_vuelo_clase VALUES (2,"105","2017-11-27","Raton");
INSERT INTO reserva_vuelo_clase VALUES (3,"105","2017-11-27","Raton");
INSERT INTO reserva_vuelo_clase VALUES (4,"105","2017-11-27","Raton");
INSERT INTO reserva_vuelo_clase VALUES (1,"104","2017-12-22","Ejecutivo");

INSERT INTO asientos_reservados VALUES (1,"BC2","2017-01-06","Ejecutivo");
INSERT INTO asientos_reservados VALUES (2,"104","2017-10-02","Ejecutivo");
INSERT INTO asientos_reservados VALUES (3,"104","2017-10-02","Turista");
INSERT INTO asientos_reservados VALUES (4,"103","2017-01-01","Ejecutivo");
INSERT INTO asientos_reservados VALUES (5,"104","2017-12-22","Ejecutivo");
INSERT INTO asientos_reservados VALUES (6,"104","2017-12-22","Turista");
INSERT INTO asientos_reservados VALUES (7,"105","2017-11-27","Raton");
INSERT INTO asientos_reservados VALUES (8,"105","2017-11-27","Ejecutivo");
INSERT INTO asientos_reservados VALUES (9,"BC3","2017-01-13","Ejecutivo");


#-------------------------------------------------------------------------
