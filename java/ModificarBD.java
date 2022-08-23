import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;


public class ModificarBD {

	public void modificar(String modificacion , VuelosConsulta v)
	{
		try{
		 // se crea una sentencia o comando jdbc para realizar la consulta
        Statement stmt =v.getConexionBD().createStatement();
        
        stmt.executeUpdate(modificacion);
        
        JOptionPane.showMessageDialog(null, "Modificacion realizada correctamente.","Informaci�n"
				, JOptionPane.INFORMATION_MESSAGE);

        stmt.close();
     }
     catch (SQLException ex)
     {
        
        JOptionPane.showMessageDialog(new JFrame(),
                                      ex.getMessage() + "\n", 
                                      "Error al ejecutar la consulta.",
                                      JOptionPane.ERROR_MESSAGE);
        
     }
  }
}

