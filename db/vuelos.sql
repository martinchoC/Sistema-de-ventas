# Creo de la Base de Datos
CREATE DATABASE vuelos;

#creo el usuario admin con privilegios en vuelos, con acceso local y contraseña 'admin'
GRANT ALL PRIVILEGES ON vuelos.* TO admin@localhost  IDENTIFIED BY 'admin' WITH GRANT OPTION;

# selecciono la base de datos sobre la cual voy a hacer modificaciones
USE vuelos;

# Creación Tablas para las entidades
 create table ubicaciones(
     pais varchar(25) not null,
     estado varchar(25) not null,
     ciudad varchar(25) not null,
     huso smallint not null check(huso>=-12 and huso<=12),
     
     constraint pk_ubicaciones
     primary key (pais,estado,ciudad)
     ) ENGINE=InnoDB;

 create table aeropuertos(
     codigo varchar (6) not null,
     nombre varchar (25) not null,
     telefono varchar (40) not null,
     direccion varchar (40) not null,
     pais varchar(25) not null,
     estado varchar(25) not null,
     ciudad varchar(25) not null,
     
     constraint pk_aeropuertos
     primary key (codigo),
     
     CONSTRAINT FK_UbicadoEn 
     FOREIGN KEY (pais,estado,ciudad) REFERENCES ubicaciones (pais,estado,ciudad)
     ON DELETE RESTRICT ON UPDATE CASCADE
     ) ENGINE=InnoDB;
     
 create table vuelos_programados(
     numero varchar (20) not null,
     aeropuerto_salida varchar (6) not null,
     aeropuerto_llegada varchar (6) not null,
     
     constraint pk_vuelos_programados
     primary key (numero),
     
     CONSTRAINT FK_aeropuerto_salida
     FOREIGN KEY (aeropuerto_salida) REFERENCES aeropuertos (codigo)
     ON DELETE RESTRICT ON UPDATE CASCADE,
     
     CONSTRAINT FK_aeropuerto_llegada
     FOREIGN KEY (aeropuerto_llegada) REFERENCES aeropuertos (codigo)
     ON DELETE RESTRICT ON UPDATE CASCADE
     ) ENGINE=InnoDB;

  create table modelos_avion(
     modelo varchar (20) not null, 
     fabricante varchar (20) not null, 
     cabinas smallint unsigned not null, 
     cant_asientos smallint unsigned not null,
     
     constraint pk_modelos_avion
     primary key (modelo)
     ) ENGINE=InnoDB;     
     
 create table salidas(
      vuelo varchar (40) not null,
      dia enum ('Do','Lu','Ma','Mi','Ju','Vi','Sa') not null,
      hora_sale TIME  not null,
      hora_llega TIME  not null,
      modelo_avion varchar (20) not null,
      
     constraint pk_salidas
     primary key (dia,vuelo),
     
     CONSTRAINT FK_tiene_vuelo
     FOREIGN KEY (vuelo) REFERENCES vuelos_programados (numero)
     ON DELETE RESTRICT ON UPDATE CASCADE,
     
     CONSTRAINT FK_modelos_avion
     FOREIGN KEY (modelo_avion) REFERENCES modelos_avion (modelo)
     ON DELETE RESTRICT ON UPDATE CASCADE
     ) ENGINE=InnoDB;   
         
 create table clases (
    nombre varchar (20) not null,
    porcentaje decimal (2,2) unsigned not null check (porcentaje>=0 and porcentaje<=0.99),
    
     constraint pk_clases
     primary key (nombre)
    
     ) ENGINE=InnoDB;         
           
     
 create table brinda (
      vuelo varchar (40) not null, 
      dia enum ('Do','Lu','Ma','Mi','Ju','Vi','Sa') not null, 
      clase varchar (20) not null, 
      precio decimal (7,2) unsigned not null, 
      cant_asientos smallint unsigned not null,
      
      constraint pk_brinda
      primary key (vuelo,dia,clase),
      
      CONSTRAINT FK_brindaSalida
      FOREIGN KEY (vuelo,dia) REFERENCES salidas (vuelo,dia)
      ON DELETE RESTRICT ON UPDATE CASCADE,
    
      CONSTRAINT FK_brindaClase
      FOREIGN KEY (clase) REFERENCES clases (nombre)
      ON DELETE RESTRICT ON UPDATE CASCADE
      
     ) ENGINE=InnoDB;          
     
 create table comodidades (
     codigo int unsigned not null,
     descripcion text not null,
    
     constraint pk_comodidades
     primary key (codigo)
    
     ) ENGINE=InnoDB;     
     
 create table posee (
      clase varchar (20),
      comodidad int unsigned not null,
    
      constraint pk_posee
      primary key (clase,comodidad),

      CONSTRAINT FK_poseeClase
      FOREIGN KEY (clase) REFERENCES clases (nombre)
      ON DELETE RESTRICT ON UPDATE CASCADE,
    
      CONSTRAINT FK_poseeComodidad
      FOREIGN KEY (comodidad) REFERENCES comodidades (codigo)
      ON DELETE RESTRICT ON UPDATE CASCADE
     
    
     ) ENGINE=InnoDB;      
     
 create table instancias_vuelo (
      vuelo varchar (40) not null,
      fecha date not null,
      dia enum ('Do','Lu','Ma','Mi','Ju','Vi','Sa') not null,
      estado varchar (20),
      
      
     constraint pk_instancias_vuelo
     primary key (vuelo,fecha),
     
      CONSTRAINT FK_instacias_vuelo_salidas
      FOREIGN KEY (vuelo,dia) REFERENCES salidas (vuelo,dia)
      ON DELETE RESTRICT ON UPDATE CASCADE
    
     ) ENGINE=InnoDB;

 create table pasajeros (
      doc_tipo varchar (15) not null,
      doc_nro int unsigned not null,  
      apellido varchar(40) not null, 
      nombre varchar(40) not null, 
      direccion varchar(40) not null, 
      telefono varchar(40) not null, 
      nacionalidad varchar(40) not null,
      
      constraint pk_pasajeros
      primary key (doc_tipo,doc_nro)
      
     ) ENGINE=InnoDB;     

 create table empleados (
      legajo int unsigned not null,
      password char(32) not null,
      doc_tipo varchar (15) not null, 
      doc_nro int unsigned not null, 
      apellido varchar(40) not null, 
      nombre varchar(40) not null, 
      direccion varchar(40) not null, 
      telefono varchar(40) not null,
      
      constraint pk_empleados
      primary key (legajo)
      
     ) ENGINE=InnoDB; 

 create table reservas (
      numero int unsigned not null auto_increment, 
      fecha date not null, 
      vencimiento date not null, 
      estado varchar (20) not null, 
      doc_tipo varchar (15) not null,
      doc_nro int unsigned not null, 
      legajo int unsigned not null,
      
      constraint pk_reservas
      primary key (numero),
      
      CONSTRAINT FK_reservasPasajeros
      FOREIGN KEY (doc_tipo,doc_nro) REFERENCES pasajeros (doc_tipo,doc_nro)
      ON DELETE RESTRICT ON UPDATE CASCADE,
      
      CONSTRAINT FK_reservasEmpleados
      FOREIGN KEY (legajo) REFERENCES empleados (legajo)
      ON DELETE RESTRICT ON UPDATE CASCADE
      
     ) ENGINE=InnoDB;      

 create table reserva_vuelo_clase (
      numero int unsigned not null, 
      vuelo varchar (40) not null, 
      fecha_vuelo date not null , 
      clase varchar (20) not null,
      
      constraint pk_reservas
      primary key (numero,vuelo,fecha_vuelo),
      
      CONSTRAINT FK_reserva_vuelo_clase_RESERVA
      FOREIGN KEY (numero) REFERENCES reservas (numero)
      ON DELETE RESTRICT ON UPDATE CASCADE,
      
      CONSTRAINT FK_reserva_vuelo_clase_instacia_vuelo
      FOREIGN KEY (vuelo,fecha_vuelo) REFERENCES instancias_vuelo (vuelo,fecha)
      ON DELETE RESTRICT ON UPDATE CASCADE,
      
      CONSTRAINT FK_reserva_vuelo_clase_CLASES
      FOREIGN KEY (clase) REFERENCES clases (nombre)
      ON DELETE RESTRICT ON UPDATE CASCADE  
     ) ENGINE=InnoDB; 
    
    
    CREATE VIEW vuelos_disponibles1 AS
     SELECT s.vuelo,s.dia,s.hora_sale, s.hora_llega, s.modelo_avion, (s.hora_llega-s.hora_sale) as tiempodevuelo, ins.fecha
     FROM salidas as s JOIN instancias_vuelo as ins on ins.dia=s.dia AND ins.vuelo=s.vuelo;
     
     CREATE VIEW vuelos_disponibles2 AS
     SELECT v.numero, a1.codigo, a1.nombre,a1.pais, a1.estado, a1.ciudad, a2.codigo as cod2, a2.nombre as nom2,a2.pais as pais2,a2.estado as estado2,a2.ciudad as ciudad2
     FROM (vuelos_programados as v JOIN aeropuertos as a1 on v.aeropuerto_salida=a1.codigo)JOIN aeropuertos as a2 ON v.aeropuerto_llegada=a2.codigo; 
    
     CREATE VIEW vuelos_disponibles3 AS
     SELECT vuelo, fecha_vuelo, clase, COUNT(*) as reservas 
     FROM reserva_vuelo_clase
     GROUP BY vuelo, fecha_vuelo, clase;
   
     
     CREATE VIEW vuelos_disponibles4 AS
     SELECT b.vuelo, b.dia, b.precio, b.clase, convert((b.cant_asientos+c.porcentaje*b.cant_asientos-IFNULL(v3.reservas,0)),UNSIGNED) as cantidaddereservas
     FROM ((brinda as b JOIN clases as c ON b.clase=c.nombre) JOIN instancias_vuelo as i ON i.vuelo=b.vuelo AND i.dia=b.dia) LEFT OUTER JOIN vuelos_disponibles3 as v3 ON v3.vuelo=b.vuelo AND v3.clase=b.clase;
     
     
     CREATE VIEW vuelos_disponibles AS
     SELECT v1.vuelo,v1.dia,v1.hora_sale,v1.hora_llega, v1.modelo_avion, v1.tiempodevuelo ,v1.fecha, v2.codigo as codigo_salida,v2.nombre as nombre_salida,v2.pais as pais_salida,v2.estado as estado_salida,v2.ciudad as ciudad_salida, v2.cod2 as codigo_llegada,v2.nom2 as nombre_llegada,v2.pais2 as pais_llegada,v2.estado2 as estado_llegada,v2.ciudad2 as ciudad_llegada,v4.precio, v4.clase, v4.cantidaddereservas as reservas_disponibles
     FROM (vuelos_disponibles1 as v1 JOIN vuelos_disponibles2 as v2 ON v1.vuelo=v2.numero) JOIN vuelos_disponibles4 as v4 ON v1.vuelo=v4.vuelo AND v1.dia=v4.dia ;
     
     #Creo usuario Empleado
     CREATE USER empleado IDENTIFIED BY 'empleado';
     GRANT SELECT ON vuelos.* to empleado;
     GRANT UPDATE, DELETE, INSERT on vuelos.reservas to empleado;
     GRANT UPDATE, DELETE, INSERT on vuelos.pasajeros to empleado;
     GRANT UPDATE, DELETE, INSERT on vuelos.reserva_vuelo_clase to empleado;
     
     #Creo el usuario cliente
     CREATE USER cliente IDENTIFIED BY 'cliente';
     GRANT SELECT ON vuelos.vuelos_disponibles to cliente;

     
   
   
##########################################################################
#########		  PROYECTO 3				##########
##########################################################################

 create table asientos_reservados (
      cantidad int unsigned not null, 
      vuelo varchar (20) not null, 
      fecha date not null,
      clase varchar (20) not null,
      
      constraint pk_asientos_reservados
      primary key (vuelo,fecha,clase),
      
      CONSTRAINT FK_instacias_vuelo_Asientos_reservado
      FOREIGN KEY (vuelo,fecha) REFERENCES instancias_vuelo (vuelo,fecha)
      ON DELETE RESTRICT ON UPDATE CASCADE,
      
      CONSTRAINT FK_clase_Asientos_reservado
      FOREIGN KEY (clase) REFERENCES clases (nombre)
      ON DELETE RESTRICT ON UPDATE CASCADE
      
     ) ENGINE=InnoDB;     
 

#############################################STORED PROCEDURES##################################################

delimiter !
CREATE PROCEDURE reservarVueloIda(IN vueloIda VARCHAR(40), IN claseIda VARCHAR(40), IN fechaIda DATE, IN docTipo VARCHAR(40), IN docNumero INTEGER, IN legajo INTEGER, OUT resultado VARCHAR(40))
BEGIN
	DECLARE diaVuelo ENUM('Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa');
	DECLARE asientos_reserva INTEGER;	
	DECLARE asientos_totales INTEGER;		
	DECLARE asientos_disponibles INTEGER;	
	 

START TRANSACTION;
	SELECT cantidad INTO asientos_reserva FROM asientos_reservados WHERE vuelo=vueloIda AND fecha=fechaIda AND clase=claseIda FOR UPDATE;
	SELECT dia INTO diaVuelo FROM instancias_vuelo WHERE (vuelo=vueloIda) AND (fecha=fechaIda);	
	SELECT cant_asientos INTO asientos_totales FROM brinda WHERE (vuelo=vueloIda) AND (clase=claseIda) AND (dia=diaVuelo);
	SELECT (ROUND(cant_asientos*(1+porcentaje))) INTO asientos_disponibles FROM brinda JOIN clases ON (brinda.clase=clases.nombre) WHERE (vuelo=vueloIda) AND (dia= diaVuelo) AND (clase= claseIda);

	if (asientos_reserva < asientos_totales) THEN 	
		BEGIN
			INSERT INTO reservas (fecha, vencimiento, estado, doc_tipo, doc_nro, legajo) 
				VALUES (CURDATE(), date_sub(CURDATE(), INTERVAL 15 DAY), "confirmada", docTipo, docNumero, legajo);
			
			INSERT INTO reserva_vuelo_clase VALUES(last_insert_id(), vueloIda, fechaIda, claseIda);
			UPDATE asientos_reservados SET cantidad = asientos_reserva+1 WHERE vuelo=vueloIda AND fecha=fechaIda AND clase=claseIda;	
					
			
			SET resultado='Reserva Confirmada';
		END;
	ELSEIF	(asientos_reserva<asientos_disponibles) THEN
		BEGIN
			INSERT INTO reservas (fecha, vencimiento, estado, doc_tipo, doc_nro, legajo) 
				VALUES (CURDATE(), date_sub(CURDATE(), INTERVAL 15 DAY), "en espera", docTipo, docNumero, legajo);
			INSERT INTO reserva_vuelo_clase VALUES(last_insert_id(), vueloIda, fechaIda, claseIda);
			UPDATE asientos_reservados SET cantidad = asientos_reserva+1 WHERE vuelo=vueloIda AND fecha=fechaIda AND clase=claseIda;			
		SET resultado='Reserva en espera';
		END;
	ELSE
		BEGIN 
			SET resultado='No hay asientos disponibles';
		END;
	END IF;


COMMIT;



END; !
delimiter ;

delimiter !
CREATE PROCEDURE reservarVueloIdaVuelta
	(IN vueloIda VARCHAR(40), IN vueloVuelta VARCHAR(40), IN claseIda VARCHAR(40), IN claseVuelta VARCHAR(40), IN fechaIda DATE, IN fechaVuelta DATE, IN docTipo VARCHAR(40), IN docNumero INTEGER, IN legajo INTEGER, OUT resultado VARCHAR(128))	

BEGIN 
	DECLARE asientos_disponiblesIDA INTEGER;	
	DECLARE asientos_reservaIDA INTEGER;	
	DECLARE asientos_totalesIDA INTEGER;		
	DECLARE diaVueloIDA ENUM('Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'); 

	DECLARE asientos_reservaVUELTA INTEGER;	
	DECLARE asientos_totalesVUELTA INTEGER;		
	DECLARE asientos_disponiblesVUELTA INTEGER;	
	DECLARE diaVueloVUELTA ENUM('Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa'); 


START TRANSACTION;
	#Variables Vuelo IDA
	SELECT cantidad INTO asientos_reservaIDA FROM asientos_reservados WHERE vuelo=vueloIda AND fecha=fechaIda AND clase=claseIda FOR UPDATE;
	SELECT dia INTO diaVueloIDA FROM instancias_vuelo WHERE (vuelo=vueloIda) AND (fecha=fechaIda);
	SELECT cant_asientos INTO asientos_totalesIDA FROM brinda WHERE (vuelo=vueloIda) AND (clase=claseIda) AND (diaVueloIDA= dia);
	SELECT (ROUND(cant_asientos*(1+porcentaje))) INTO asientos_disponiblesIDA FROM brinda JOIN clases ON (brinda.clase=clases.nombre) WHERE (vuelo=vueloIda) AND (dia= diaVueloIDA) AND (clase= claseIda);

	#Variables Vuelo VUELTA
	SELECT cantidad INTO asientos_reservaVUELTA FROM asientos_reservados WHERE vuelo=vueloVuelta AND fecha=fechaVuelta AND clase=claseVuelta FOR UPDATE;
	SELECT dia INTO diaVueloVUELTA FROM instancias_vuelo WHERE (vuelo=vueloVuelta) AND (fecha=fechaVuelta);
	SELECT cant_asientos INTO asientos_totalesVUELTA FROM brinda WHERE (vuelo=vueloVuelta) AND (clase=claseVuelta) AND (diaVueloVUELTA= dia);
	SELECT (ROUND(cant_asientos*(1+porcentaje))) INTO asientos_disponiblesVUELTA FROM brinda JOIN clases ON (brinda.clase=clases.nombre) WHERE (vuelo=vueloVuelta) AND (dia= diaVueloVUELTA) AND (clase= claseVuelta);


		if ((asientos_reservaIDA < asientos_totalesIDA) AND (asientos_reservaVUELTA<asientos_totalesVuelta)) THEN 	
		BEGIN	#queda ida y vuelta confirmado

			INSERT INTO reservas (fecha, vencimiento, estado, doc_tipo, doc_nro, legajo) 
				VALUES (CURDATE(), date_sub(CURDATE(), INTERVAL 15 DAY), "confirmada", docTipo, docNumero, legajo);
			
			INSERT INTO reserva_vuelo_clase VALUES(last_insert_id(), vueloIda, fechaIda, claseIda);
		
			UPDATE asientos_reservados SET cantidad = asientos_reservaIDA+1 WHERE vuelo=vueloIda AND fecha=fechaIda AND clase=claseIda;
			
			INSERT INTO reserva_vuelo_clase VALUES(last_insert_id(), vueloVuelta, fechaVuelta, claseVuelta);
		
			UPDATE asientos_reservados SET cantidad = asientos_reservaVUELTA+1 WHERE vuelo=vueloVuelta AND fecha=fechaVuelta AND clase=claseVuelta;			
						
			SET resultado='Reserva Confirmada tanto de ida como de vuelta';
		END; 
		ELSEIF	((asientos_reservaIDA<asientos_totalesIDA) AND (asientos_reservaVUELTA<asientos_disponiblesVUELTA)) THEN
		BEGIN	#queda ida confirmado y vuelta en espera
			
			INSERT INTO reservas (fecha, vencimiento, estado, doc_tipo, doc_nro, legajo) 
				VALUES (CURDATE(), date_sub(CURDATE(), INTERVAL 15 DAY), "en espera", docTipo, docNumero, legajo);
			
			INSERT INTO reserva_vuelo_clase VALUES(last_insert_id(), vueloIda, fechaIda, claseIda);
		
			UPDATE asientos_reservados SET cantidad = asientos_reservaIDA+1 WHERE vuelo=vueloIda AND fecha=fechaIda AND clase=claseIda;
			
			INSERT INTO reserva_vuelo_clase VALUES(last_insert_id(), vueloVuelta, fechaVuelta, claseVuelta);
		
			UPDATE asientos_reservados SET cantidad = asientos_reservaVUELTA+1 WHERE vuelo=vueloVuelta AND fecha=fechaVuelta AND 					clase=claseVuelta;	
			SET resultado='Reserva en espera' ;		
			
		END;
		ELSEIF	((asientos_reservaIDA<asientos_disponiblesIDA) AND (asientos_reservaVUELTA<asientos_totalesVUELTA)) THEN
		BEGIN	#queda ida en espera y vuelta confirmado
			
			INSERT INTO reservas (fecha, vencimiento, estado, doc_tipo, doc_nro, legajo) 
				VALUES (CURDATE(), date_sub(CURDATE(), INTERVAL 15 DAY), "en espera", docTipo, docNumero, legajo);
			
			INSERT INTO reserva_vuelo_clase VALUES(last_insert_id(), vueloIda, fechaIda, claseIda);
		
			UPDATE asientos_reservados SET cantidad = asientos_reservaIDA+1 WHERE vuelo=vueloIda AND fecha=fechaIda AND clase=claseIda;
			
			INSERT INTO reserva_vuelo_clase VALUES(last_insert_id(), vueloVuelta, fechaVuelta, claseVuelta);
		
			UPDATE asientos_reservados SET cantidad = asientos_reservaVUELTA+1 WHERE vuelo=vueloVuelta AND fecha=fechaVuelta AND 					clase=claseVuelta;	
			SET resultado='Reserva en espera';		

		END;
	ELSEIF	((asientos_reservaIDA<asientos_disponiblesIDA) AND (asientos_reservaVUELTA<asientos_disponiblesVUELTA)) THEN
		BEGIN	#queda ida y vuelta en espera
			
			##Se actualizan todos los datos correspondientes al vuelo de ida
			INSERT INTO reservas (fecha, vencimiento, estado, doc_tipo, doc_nro, legajo) 
				VALUES (CURDATE(), date_sub(CURDATE(), INTERVAL 15 DAY), "en espera", docTipo, docNumero, legajo);
			
			INSERT INTO reserva_vuelo_clase VALUES(last_insert_id(), vueloIda, fechaIda, claseIda);
		
			UPDATE asientos_reservados SET cantidad = asientos_reservaIDA+1 WHERE vuelo=vueloIda AND fecha=fechaIda AND clase=claseIda;
			
			INSERT INTO reserva_vuelo_clase VALUES(last_insert_id(), vueloVuelta, fechaVuelta, claseVuelta);
		
			UPDATE asientos_reservados SET cantidad = asientos_reservaVUELTA+1 WHERE vuelo=vueloVuelta AND fecha=fechaVuelta AND clase=claseIda;	
			SET resultado='La reserva en espera';		

		END;
	ELSE
		BEGIN 	
			##No se pueden agregar las reservas
			SET resultado='Al menos una de las reservas no esta disponible';
		END;
	END IF;
	

COMMIT;

END; !
delimiter ;
 
###############################################################################################

     #Le doy permiso al empleado para poder reservar vuelos.
     GRANT execute on procedure vuelos.reservarVueloIda to 'empleado'@'%';
     GRANT execute on procedure vuelos.reservarVueloIdaVuelta to 'empleado'@'%';

###############################################################################################     