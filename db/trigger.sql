USE vuelos;
delimiter !
CREATE TRIGGER instancias_nuevas
AFTER INSERT ON salidas
FOR EACH ROW
BEGIN
	DECLARE fecha DATE;
	DECLARE fFin DATE;
	DECLARE diaSem INTEGER;
	DECLARE sem ENUM('Do', 'Lu', 'Ma', 'Mi', 'Ju', 'Vi', 'Sa');
	SELECT NEW.dia INTO sem;
	
	CASE 
	WHEN sem='Do' THEN SELECT 1 INTO diaSem;
	WHEN sem='Lu' THEN SELECT 2 INTO diaSem;
	WHEN sem='Ma' THEN SELECT 3 INTO diaSem;
	WHEN sem='Mi' THEN SELECT 4 INTO diaSem;
	WHEN sem='Ju' THEN SELECT 5 INTO diaSem;
	WHEN sem='Vi' THEN SELECT 6 INTO diaSem;
	WHEN sem='Sa' THEN SELECT 7 INTO diaSem;
	END CASE;
	
	#Se cambia a la fecha proxima siguiente que corresponde al dia de la semana 
	IF (DAYOFWEEK(CURDATE()) > diaSem) THEN
		BEGIN
		SELECT (DATE_ADD(CURDATE(), INTERVAL (7 - DAYOFWEEK(CURDATE()) + diaSem) DAY)) INTO fecha;
		END;
	ELSEIF (DAYOFWEEK(CURDATE()) < diaSem) THEN
		BEGIN
		SELECT (DATE_ADD(CURDATE(), INTERVAL (diaSem-DAYOFWEEK(CURDATE())) DAY)) INTO fecha;
		END;
	ELSE
		BEGIN
		SELECT CURDATE() INTO fecha;
		END;
	END IF;
	
	#Se agraga una salida cada semana por el periodo de un año
	SELECT DATE_ADD(fecha, INTERVAL 1 YEAR) INTO fFin;
	WHILE (DATEDIFF(fecha, fFin) < 0) DO
		BEGIN
			INSERT INTO instancias_vuelo VALUES (NEW.vuelo,fecha,NEW.dia,'En tiempo');
			SELECT DATE_ADD(fecha, INTERVAL 7 DAY) INTO fecha;
		END;
	END WHILE;
	
END; !
delimiter ;