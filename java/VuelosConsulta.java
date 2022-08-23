
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/* CERRAR LOS RS Y STMT */





public class VuelosConsulta {
	
//protected Connection conexionBD = null;
	protected Connection conexionBD = null;

public VuelosConsulta(){} 

/**
 * El tiempo limite para comprobar la conexion es de 20 seg.
 * @param usuario, es el usuario que desea conectarse a la base de datos
 * @param clave, es la clave del usuario
 * @return true si se conecto exitosamente false en caso contrario
 */
public boolean conectarBD(String usuario,String clave)
	   {
	      if (this.conexionBD == null)
	      {
	         try
	         {
	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	         }
	         catch (Exception ex)
	         {
	            System.out.println(ex.getMessage());
	         }
	   
	         try
	         {
	            String servidor = "localhost:3306";
	            String baseDatos = "vuelos";
	            String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos +"?noAccessToProcedureBodies=true";
	   
	            this.conexionBD =  DriverManager.getConnection(uriConexion, usuario, clave);	
		        return (conexionBD.isValid(20)); 
	            
	         }
	         catch (SQLException ex)
	         {
	          
	         }  
	         }
	      return false;
	   }


/*
 * Permite que un usuario se conecte a la base de datos con permisos de empleado
 */
public boolean conectarBDempleado(int legajo,String clave)
{
   if (this.conexionBD == null)
   {
      try
      {
         Class.forName("com.mysql.jdbc.Driver").newInstance();
      }
      catch (Exception ex)
      {
         
      }

      try
      {
         String servidor = "localhost:3306";
         String baseDatos = "vuelos";
         String uriConexion = "jdbc:mysql://" + servidor + "/" + baseDatos +"?noAccessToProcedureBodies=true";
               
        this.conexionBD =  DriverManager.getConnection(uriConexion, "admin", "admin");
        java.sql.ResultSet rs=consulta("select distinct legajo,password from empleados where legajo="+legajo+" and password="+"'"+clave+"';");
        if (rs.next()){ //si existe alguna tupla significa que el password y legajo es correcto
        	 desconectarBD();
        	 this.conexionBD = (Connection) DriverManager.getConnection(uriConexion, "empleado", "empleado");
        	 return true;}
        else{
           desconectarBD();
           return false;}
      }
      catch (SQLException ex)
      {
      }   
      }
   return false;
}

/*
 * Desconecta a un usuario de la base de datos
 */
public void desconectarBD()
{
   if (this.conexionBD != null)
   {
      try
      {
         this.conexionBD.close();
         this.conexionBD = null;
      }
      catch (SQLException ex)
      {
      }
   }
}

public Connection getConexionBD()
{
	return conexionBD;
}

private java.sql.ResultSet consulta (String sql){
	try
	{
		// Se crea una sentencia jdbc para realizar la consulta
		java.sql.Statement stmt = conexionBD.createStatement();
		// Se ejecuta la sentencia y se recibe un resultado
		java.sql.ResultSet rs = stmt.executeQuery(sql);
		return rs;
		/*
		rs.close();
		stmt.close();*/
	}
	catch (java.sql.SQLException ex) {}
	return null;
}

}
