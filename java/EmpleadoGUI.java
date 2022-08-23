

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
@SuppressWarnings("serial")
public class EmpleadoGUI extends javax.swing.JFrame {

	/**
	* Auto-generated main method to display this JFrame
	*/
	private JLabel jLabel2;
	private JComboBox<String> ListaVuelta;
	private JButton botonUsuarios;
	private JTable tablaUsuarios;
	private JScrollPane jScrollPane4;
	private JLabel usuarios;
	private JButton botonReserva;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private JScrollPane jScrollPane2;
	private JTable Tabla_VueloVuelta;
	private JTable Tabla_VueloIda;
	private JScrollPane jScrollPane3;
	private JTable Tabla_Vuelta;
	private JTable Tabla_Ida;
	private JScrollPane jScrollPane1;
	private JScrollPane PanelIda;
	private JLabel jLabel5;
	private JButton jButton1;
	private JLabel jLabel4;
	private JLabel jLabel3;
	private JComboBox<String> Seleccionar;
	private JLabel jLabel1;
	private JButton Regresar;
	private JComboBox <String>ListaIda;
	DefaultComboBoxModel <String>jList2Model;
	DefaultComboBoxModel <String>jListModel;
	private JDateChooser fIda;
	private JDateChooser fVuelta;
	protected JFrame j;
	protected VuelosConsulta v;
	protected static ModificarTabla m=new ModificarTabla();
    private int legajo;
	
	public EmpleadoGUI(VuelosConsulta vuelos, JFrame contentPane,int l) {
		super();
		legajo=l;
		j=contentPane;
		v=vuelos;
		initGUI();
		setVisible(true);
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			getContentPane().setLayout(null);
			this.setResizable(false);
			jList2Model = new DefaultComboBoxModel<String>();
			jListModel = new DefaultComboBoxModel<String>();
			cargarListas();
			
			{
				ListaIda=new JComboBox<String>();
				ListaIda.setModel(jList2Model);
				getContentPane().add(ListaIda);
				ListaIda.setBounds(97, 11, 110, 19);
			}
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Ciudad Origen:");
				jLabel1.setBounds(11, 12, 112, 16);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Ciudad Destino:");
				jLabel2.setBounds(219, 12, 112, 16);
			}
			
			{
				ListaVuelta = new JComboBox<String>();
				ListaVuelta.setModel(jListModel);
				getContentPane().add(ListaVuelta);
				ListaVuelta.setBounds(309, 11, 119, 19);
			}
			{
				ComboBoxModel<String> SeleccionarModel = 
						new DefaultComboBoxModel<String>(
								new String[] { "Ida", "Ida y Vuelta" });
				Seleccionar = new JComboBox<String>();
				getContentPane().add(Seleccionar);
				Seleccionar.setModel(SeleccionarModel);
				Seleccionar.setBounds(522, 11, 117, 18);
				OyenteSeleccionar o=new OyenteSeleccionar();
				Seleccionar.addActionListener(o);
			}
		
				fIda=new JDateChooser();
				getContentPane().add(fIda);
				fIda.setDateFormatString("dd/MM/yyyy");
				fIda.setBounds(732, 12, 134, 22);
				((JTextFieldDateEditor)fIda.getDateEditor()).setEditable(false);
				
		
			
			fVuelta=new JDateChooser();
			getContentPane().add(fVuelta);
			fVuelta.setDateFormatString("dd/MM/yyyy");
			fVuelta.setBounds(991, 12, 134, 22);
			((JTextFieldDateEditor)fVuelta.getDateEditor()).setEditable(false);
			fVuelta.setVisible(false);
			{
				jLabel3 = new JLabel();
				jLabel3.setText("Seleccione:");
				 getContentPane().add(jLabel3);
				jLabel3.setBounds(441, 12, 74, 16);
			}
			
			{
				jLabel4 = new JLabel();
				jLabel4.setText("Fecha Ida:");
				 getContentPane().add(jLabel4);
				jLabel4.setBounds(670, 13, 70, 16);
			}
			
			{
				jLabel5 = new JLabel();
				jLabel5.setText("Fecha Vuelta:");
				 getContentPane().add(jLabel5);
				jLabel5.setBounds(912, 13, 79, 16);
				jLabel5.setVisible(false);
			}
			
			{
				jButton1 = new JButton();
				jButton1.setText("Buscar");
				 getContentPane().add(jButton1);
				jButton1.setBounds(1182, 10, 119, 23);
				OyenteBuscar ob= new OyenteBuscar();
				jButton1.addActionListener(ob);
			}
			{
				PanelIda = new JScrollPane();
				getContentPane().add(PanelIda);
				PanelIda.setBounds(17, 92, 763, 108);
				Tabla_Ida = new JTable();
				PanelIda.add(Tabla_Ida);
				PanelIda.setViewportView(Tabla_Ida);
				Tabla_Ida.setAutoCreateRowSorter(true);
				OyenteTablaIda oi=new OyenteTablaIda();
				Tabla_Ida.addMouseListener(oi);
				PanelIda.setVisible(true);
				Tabla_Ida.setVisible(true);
			}
			
			{
				jScrollPane1 = new JScrollPane();
				jScrollPane1.setVisible(true);
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(17, 236, 763, 100);
				Tabla_Vuelta = new JTable();
				jScrollPane1.add(Tabla_Vuelta);
				jScrollPane1.setViewportView(Tabla_Vuelta);
				Tabla_Vuelta.setAutoCreateRowSorter(true);
				OyenteTablaVuelta ov=new OyenteTablaVuelta();
				Tabla_Vuelta.addMouseListener(ov);
				Tabla_Vuelta.setVisible(true);
			}
			{
				jScrollPane2 = new JScrollPane();
				jScrollPane2.setVisible(true);
				getContentPane().add(jScrollPane2);
				jScrollPane2.setBounds(880, 92, 313, 108);
				Tabla_VueloIda = new JTable();
				jScrollPane2.setViewportView(Tabla_VueloIda);
				Tabla_VueloIda.setAutoCreateRowSorter(true);
				Tabla_VueloIda.setVisible(true);
				
			}
			{
				jScrollPane3 = new JScrollPane();
				jScrollPane3.setVisible(true);
				getContentPane().add(jScrollPane3);
				jScrollPane3.setBounds(880, 235, 313, 100);
				Tabla_VueloVuelta = new JTable();
				jScrollPane3.setViewportView(Tabla_VueloVuelta);
				Tabla_VueloVuelta.setAutoCreateRowSorter(true);
				Tabla_VueloVuelta.setVisible(true);
			}
			{
				Regresar = new JButton();
				getContentPane().add(Regresar);
				Regresar.setText("Regresar");
				Regresar.setBounds(1182, 515, 119, 21);
				OyenteRegresar o2=new OyenteRegresar(this);
				Regresar.addActionListener(o2);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setBounds(17, 60, 513, 26);
				jLabel6.setText("Vuelos de ida");
				jLabel6.setFont(new java.awt.Font("Segoe UI",1,16));
			}
			{	
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("Vuelos de vuelta");
				jLabel7.setBounds(17, 206, 693, 24);
				jLabel7.setFont(new java.awt.Font("Segoe UI",1,16));
			}
			{
				botonReserva = new JButton();
				getContentPane().add(botonReserva);
				botonReserva.setText("Reservar");
				botonReserva.setBounds(1182, 482, 119, 23);
				botonReserva.setEnabled(false);
				
				OyenteReserva or= new OyenteReserva();
				botonReserva.addActionListener(or);
			}
			{
				usuarios = new JLabel();
				getContentPane().add(usuarios);
				usuarios.setText("Usuarios disponbles");
				usuarios.setBounds(17, 359, 219, 16);
				usuarios.setFont(new java.awt.Font("Segoe UI",1,16));
			}
			{
				jScrollPane4 = new JScrollPane();
				getContentPane().add(jScrollPane4);
				jScrollPane4.setBounds(17, 387, 763, 103);
				{
					tablaUsuarios = new JTable();
					jScrollPane4.setViewportView(tablaUsuarios);
					tablaUsuarios.setAutoCreateRowSorter(true);
					m.refrescarTabla("select * from pasajeros", tablaUsuarios,v);
				}
			}
			{
				botonUsuarios = new JButton();
				getContentPane().add(botonUsuarios);
				botonUsuarios.setText("Ingresar Usuarios");
				botonUsuarios.setBounds(623, 514, 163, 23);
				OyenteUsuarios ou=new OyenteUsuarios();
				botonUsuarios.addActionListener(ou);
			}

			pack();
			this.setSize(1318, 584);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	/*
	 * Carga los nombres de las ciudades
	 */
	private void cargarListas()
	{
		try{
		java.sql.Statement stmt=v.getConexionBD().createStatement();
		String sql="Select ciudad from ubicaciones";
		java.sql.ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			String ciudad=rs.getString("ciudad");
			jList2Model.addElement(ciudad);
			jListModel.addElement(ciudad);
		}
		rs.close();
		stmt.close();
	}
		catch(Exception e){}
	}
	
	
	private class OyenteBuscar implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			Tabla_VueloVuelta.setVisible(false);
			Tabla_VueloIda.setVisible(false);
			Tabla_Vuelta.setVisible(false);
			Tabla_Ida.setVisible(false);
			jLabel6.setText("Vuelos de ida");
			jLabel7.setText("Vuelos de vuelta");
			String ns= (String) ListaIda.getSelectedItem();
			String nv= (String) ListaVuelta.getSelectedItem();
			if (ns.equals(nv))
				JOptionPane.showMessageDialog(new JFrame(),new String("Seleccione ciudades distintas"), 
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
			//Si todos los datos son correctamente ingresados, se muestran los vuelos de ida entre las ciudaddes especificadas un determinado dia
			else if (Seleccionar.getSelectedIndex()==0){
				
				java.sql.Date fechaIda=pasarFechas(((JTextFieldDateEditor)fIda.getDateEditor()));
				if (fechaIda!=null)
				{
					jLabel6.setText("Vuelos de ida desde "+ns+" hasta "+nv+".");
					String consulta ="SELECT DISTINCT VUELO, NOMBRE_SALIDA, HORA_SALE, NOMBRE_LLEGADA, HORA_LLEGA, MODELO_AVION, TIEMPODEVUELO FROM VUELOS_DISPONIBLES WHERE ciudad_salida='"+ns+"' AND ciudad_llegada='"+nv+"' AND fecha='"+fechaIda+"';"; 
					m.refrescarTabla(consulta, Tabla_Ida,v);
					Tabla_Ida.setVisible(true);
					botonReserva.setEnabled(true);

				}
				
				
			}
			//Si todos los datos son correctamente ingresados, se muestran los vuelos de ida y vuelta entre las ciudaddes especificadas un determinado dia
			else{
				
				java.sql.Date fechaIda=pasarFechas(((JTextFieldDateEditor)fIda.getDateEditor()));
				java.sql.Date fechaVuelta=pasarFechas(((JTextFieldDateEditor)fVuelta.getDateEditor()));
				
				if (fechaVuelta!=null && fechaIda!=null) 
					
				//Verifica que la fecha de ida no sea posterior a la de vuelta
					if(!fechaIda.after(fechaVuelta)){
					String consulta ="SELECT DISTINCT VUELO, NOMBRE_SALIDA, HORA_SALE, NOMBRE_LLEGADA, HORA_LLEGA, MODELO_AVION, TIEMPODEVUELO FROM VUELOS_DISPONIBLES WHERE ciudad_salida='"+ns+"' AND ciudad_llegada='"+nv+"' AND fecha='"+fechaIda+"';"; 
					m.refrescarTabla(consulta, Tabla_Ida,v);
					String consulta1 ="SELECT DISTINCT VUELO, NOMBRE_SALIDA, HORA_SALE, NOMBRE_LLEGADA, HORA_LLEGA, MODELO_AVION, TIEMPODEVUELO FROM VUELOS_DISPONIBLES WHERE ciudad_llegada='"+ns+"' AND ciudad_salida='"+nv+"' AND fecha='"+fechaVuelta+"';"; 
					m.refrescarTabla(consulta1, Tabla_Vuelta,v);
					jLabel6.setText("Vuelos de ida desde "+ns+" hasta "+nv+".");
					jLabel7.setText("Vuelos de vuelta, desde "+nv+" hasta "+ns+".");
					Tabla_Ida.setVisible(true);
					Tabla_Vuelta.setVisible(true);
					botonReserva.setEnabled(true);

					}
				
					else 
					JOptionPane.showMessageDialog(new JFrame(),new String("Fecha de vuelta anterior a la de ida"), 
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
			}
		}
	}
		
	
	//Convierte una fecha al formato requerido por mysql, verificando adem�s que no haya otros errores
	private java.sql.Date pasarFechas(JTextFieldDateEditor f) {
		String mensajeError=null;
		if (f==null || f.getText().isEmpty())
	      {
	         mensajeError = "Debe ingresar un valor para el campo 'Fecha'.";
	      }
	      else if (! Fechas.validar(f.getText().trim()))
	      {
	         mensajeError = "En el campo 'Fecha' debe ingresar un valor con el formato dd/mm/aaaa.";
	      }

	      if (mensajeError != null)
	      {
	         JOptionPane.showMessageDialog(getContentPane(),
	                                       mensajeError,
	                                       "Error",
	                                       JOptionPane.ERROR_MESSAGE);
	      }
	      java.sql.Date fecha = Fechas.convertirStringADateSQL(f.getText().trim());
	      
	      return fecha;
	}
	
	private class OyenteTablaIda implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			
			int row = Tabla_Ida.rowAtPoint(arg0.getPoint());
	        if (row >= 0) {
	        	String numero=(String) Tabla_Ida.getValueAt(row,0);
	        	String salida= (String) Tabla_Ida.getValueAt(row,1);
	        	Time h_salida= (Time) Tabla_Ida.getValueAt(row,2);
	        	String llegada=(String) Tabla_Ida.getValueAt(row,3);
	        	Time h_llegada= (Time) Tabla_Ida.getValueAt(row,4);
	        	String modelo=(String) Tabla_Ida.getValueAt(row,5);
	        	//Muestra los datos de un determinado vuelo
	        	m.refrescarTabla("SELECT DISTINCT CLASE,RESERVAS_DISPONIBLES,PRECIO FROM VUELOS_DISPONIBLES WHERE VUELO='"+numero+"' AND NOMBRE_SALIDA='"+salida+"' AND HORA_SALE='"+h_salida+"' AND NOMBRE_LLEGADA='"+llegada+"' AND HORA_LLEGA='"+h_llegada+"' AND MODELO_AVION='"+modelo+"';",Tabla_VueloIda,v);
	        	Tabla_VueloIda.setVisible(true);
	        }
			
		}
	

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	private class OyenteTablaVuelta implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent arg0) {
			int row = Tabla_Vuelta.rowAtPoint(arg0.getPoint());
	        if (row >= 0) {
	        	String numero=(String) Tabla_Vuelta.getValueAt(row,0);
	        	String salida= (String) Tabla_Vuelta.getValueAt(row,1);
	        	Time h_salida= (Time) Tabla_Vuelta.getValueAt(row,2);
	        	String llegada=(String) Tabla_Vuelta.getValueAt(row,3);
	        	Time h_llegada= (Time) Tabla_Vuelta.getValueAt(row,4);
	        	String modelo=(String) Tabla_Vuelta.getValueAt(row,5);
	        	//Muestra los datos de un determinado vuelo
	        	m.refrescarTabla("SELECT DISTINCT CLASE,RESERVAS_DISPONIBLES,PRECIO FROM VUELOS_DISPONIBLES WHERE VUELO='"+numero+"' AND NOMBRE_SALIDA='"+salida+"' AND HORA_SALE='"+h_salida+"' AND NOMBRE_LLEGADA='"+llegada+"' AND HORA_LLEGA='"+h_llegada+"' AND MODELO_AVION='"+modelo+"';",Tabla_VueloVuelta,v);
	        	
				Tabla_VueloVuelta.setVisible(true);
	        }
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
	}
	
	 private class OyenteRegresar implements ActionListener
	   {
		 JFrame f;
		 public OyenteRegresar(JFrame frame){
			 f=frame;
		 }
		@Override
		public void actionPerformed(ActionEvent e) {
			
			j.setVisible(true);
			v.desconectarBD();
			f.dispose();
			
		}
		   
	   }
	 
	 private class OyenteSeleccionar implements ActionListener
	   {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			botonReserva.setEnabled(false);
			Tabla_VueloVuelta.setVisible(false);
			Tabla_VueloIda.setVisible(false);
			Tabla_Vuelta.setVisible(false);
			Tabla_Ida.setVisible(false);
			jLabel6.setText("Vuelos de ida");
			jLabel7.setText("Vuelos de vuelta");
			Tabla_VueloIda.setVisible(false);
			Tabla_VueloVuelta.setVisible(false);
			if (Seleccionar.getSelectedIndex()==0)
			{
				fVuelta.setVisible(false);
				jLabel5.setVisible(false);
			}
			else 
			{
				fVuelta.setVisible(true);
				jLabel5.setVisible(true);
			}
		}
		   
	   }
	 
	 private class OyenteUsuarios implements ActionListener
	   {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			new AgregarUsuarioGUI(v, tablaUsuarios);
		}
		   
	   }
	 
	 private class OyenteReserva implements ActionListener
	   {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Obtiene las filas seleccionadas
			int rowIda,rowClaseIda, rowVuelta, rowClaseVuelta, rowPasajero;
			rowIda = Tabla_Ida.getSelectedRow();
			rowClaseIda = Tabla_VueloIda.getSelectedRow();
			rowPasajero = tablaUsuarios.getSelectedRow();
			if (rowIda==-1 || rowClaseIda==-1 || rowPasajero==-1)
			{
				JOptionPane.showMessageDialog(new JFrame(),
                        "Falta seleccionar algun par�metro\n", 
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				CallableStatement cs = null;
				if (Seleccionar.getSelectedIndex()==0)
				{//Lleva a cabo el stored procedure
					try {
						cs = v.getConexionBD().prepareCall("{ call reservarVueloIda(?,?,?,?,?,?,?)}");
						cs.setString(1,(String)Tabla_Ida.getValueAt(rowIda,0));
						cs.setString(2,(String)Tabla_VueloIda.getValueAt(rowClaseIda,0));
						cs.setDate(3,pasarFechas(((JTextFieldDateEditor)fIda.getDateEditor())));
						cs.setString(4,(String)tablaUsuarios.getValueAt(rowPasajero,0));
						cs.setLong(5,(Long)tablaUsuarios.getValueAt(rowPasajero,1));
						cs.setInt(6,legajo);
						cs.registerOutParameter(7, Types.VARCHAR);
						cs.execute();					
							JOptionPane.showMessageDialog(null, cs.getString(7),"Informaci�n"
									, JOptionPane.INFORMATION_MESSAGE);
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(new JFrame(),
		                        "No se pudo realizar la operacion\n", 
		                        "Error",
		                        JOptionPane.ERROR_MESSAGE);
					}
				}
				else
				{//Lleva a cabo el stored procedure
					try {
						rowVuelta = Tabla_Vuelta.getSelectedRow();
						rowClaseVuelta = Tabla_VueloVuelta.getSelectedRow();
						if (rowVuelta==-1 || rowClaseVuelta==-1 )
						{
							JOptionPane.showMessageDialog(new JFrame(),
			                        "Falta seleccionar algun par�metro\n", 
			                        "Error",
			                        JOptionPane.ERROR_MESSAGE);
						}
						else{
								cs = v.getConexionBD().prepareCall("{ call reservarVueloIdaVuelta(?,?,?,?,?,?,?,?,?,?)}");
								cs.setString(1,(String)Tabla_Ida.getValueAt(rowIda,0));
								cs.setString(2,(String)Tabla_Vuelta.getValueAt(rowIda,0));
								cs.setString(3,(String)Tabla_VueloIda.getValueAt(rowClaseIda,0));
								cs.setString(4,(String)Tabla_VueloVuelta.getValueAt(rowClaseIda,0));
								cs.setDate(5,pasarFechas(((JTextFieldDateEditor)fIda.getDateEditor())));
								cs.setDate(6,pasarFechas(((JTextFieldDateEditor)fVuelta.getDateEditor())));
								
								cs.setString(7,(String)tablaUsuarios.getValueAt(rowPasajero,0));
								cs.setLong(8,(Long)tablaUsuarios.getValueAt(rowPasajero,1));
								cs.setInt(9,legajo);
								
								cs.registerOutParameter(10, Types.VARCHAR);
								cs.execute();					
									JOptionPane.showMessageDialog(null, cs.getString(10),"Informaci�n"
											, JOptionPane.INFORMATION_MESSAGE);
									//Actualizo las tablas que se modificaron
									String numero=(String) Tabla_Vuelta.getValueAt(rowVuelta,0);
						        	String salida= (String) Tabla_Vuelta.getValueAt(rowVuelta,1);
						        	Time h_salida= (Time) Tabla_Vuelta.getValueAt(rowVuelta,2);
						        	String llegada=(String) Tabla_Vuelta.getValueAt(rowVuelta,3);
						        	Time h_llegada= (Time) Tabla_Vuelta.getValueAt(rowVuelta,4);
						        	String modelo=(String) Tabla_Vuelta.getValueAt(rowVuelta,5);
						        	//Muestra los datos de un determinado vuelo
						        	m.refrescarTabla("SELECT DISTINCT CLASE,RESERVAS_DISPONIBLES,PRECIO FROM VUELOS_DISPONIBLES WHERE VUELO='"+numero+"' AND NOMBRE_SALIDA='"+salida+"' AND HORA_SALE='"+h_salida+"' AND NOMBRE_LLEGADA='"+llegada+"' AND HORA_LLEGA='"+h_llegada+"' AND MODELO_AVION='"+modelo+"';",Tabla_VueloVuelta,v);
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(new JFrame(),
		                        "No se pudo realizar la operacion\n", 
		                        "Error",
		                        JOptionPane.ERROR_MESSAGE);
					}
				
				
				}
				//Actualizo las tablas que se modificaron
				String numero=(String) Tabla_Ida.getValueAt(rowIda,0);
	        	String salida= (String) Tabla_Ida.getValueAt(rowIda,1);
	        	Time h_salida= (Time) Tabla_Ida.getValueAt(rowIda,2);
	        	String llegada=(String) Tabla_Ida.getValueAt(rowIda,3);
	        	Time h_llegada= (Time) Tabla_Ida.getValueAt(rowIda,4);
	        	String modelo=(String) Tabla_Ida.getValueAt(rowIda,5);
	        	//Muestra los datos de un determinado vuelo
	        	m.refrescarTabla("SELECT DISTINCT CLASE,RESERVAS_DISPONIBLES,PRECIO FROM VUELOS_DISPONIBLES WHERE VUELO='"+numero+"' AND NOMBRE_SALIDA='"+salida+"' AND HORA_SALE='"+h_salida+"' AND NOMBRE_LLEGADA='"+llegada+"' AND HORA_LLEGA='"+h_llegada+"' AND MODELO_AVION='"+modelo+"';",Tabla_VueloIda,v);}
			
		}
		   
	   }
	}

