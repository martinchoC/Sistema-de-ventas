import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;


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
public class AgregarUsuarioGUI extends javax.swing.JFrame {
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField nombre;
	private JTextField apellido;
	private JButton aceptar;
	private JButton cancelar;
	private JTextField direccion;
	private JTextField telefono;
	private JTextField nacionalidad;
	private JTextField nroDoc;
	private JTextField tipoDoc;
	private JLabel jLabel8;
	private JLabel jLabel7;
	private JLabel jLabel6;
	private VuelosConsulta v;
	private JTable tablaUsuarios;

	public AgregarUsuarioGUI(VuelosConsulta vuelos, JTable t) {
		super();
		v= vuelos;
		tablaUsuarios=t;
		initGUI();
		setResizable(false);
		setVisible(true);
	}
	
	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout(1, 1);
			thisLayout.setColumns(2);
			thisLayout.setRows(8);
			thisLayout.setHgap(5);
			thisLayout.setVgap(5);
			getContentPane().setLayout(thisLayout);
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			{
				jLabel1 = new JLabel();
				getContentPane().add(jLabel1);
				jLabel1.setText("Nombre:");
			}
			{
				nombre = new JTextField();
				getContentPane().add(nombre);
			}
			{
				jLabel2 = new JLabel();
				getContentPane().add(jLabel2);
				jLabel2.setText("Apellido:");
			}
			{
				apellido = new JTextField();
				getContentPane().add(apellido);
			}
			{
				jLabel3 = new JLabel();
				getContentPane().add(jLabel3);
				jLabel3.setText("Tipo de Documento:");
			}
			{
				tipoDoc = new JTextField();
				getContentPane().add(tipoDoc);
			}
			{
				jLabel4 = new JLabel();
				getContentPane().add(jLabel4);
				jLabel4.setText("Nro de Documento:");
			}
			{
				nroDoc = new JTextField();
				getContentPane().add(nroDoc);
			}
			{
				jLabel5 = new JLabel();
				getContentPane().add(jLabel5);
				jLabel5.setText("Nacionalidad:");
			}
			
			{
				nacionalidad = new JTextField();
				getContentPane().add(nacionalidad);
			}
			{
				jLabel6 = new JLabel();
				getContentPane().add(jLabel6);
				jLabel6.setText("Tel�fono:");
			}

			{
				telefono = new JTextField();
				getContentPane().add(telefono);
			}
			{
				jLabel7 = new JLabel();
				getContentPane().add(jLabel7);
				jLabel7.setText("Direcci�n:");
			}
		
			{
				direccion = new JTextField();
				getContentPane().add(direccion);
			}
			{
				aceptar = new JButton();
				getContentPane().add(aceptar);
				aceptar.setText("Aceptar");
				OyenteAceptar oa=new OyenteAceptar(this);
				aceptar.addActionListener(oa);
			}
			{
				cancelar = new JButton();
				getContentPane().add(cancelar);
				cancelar.setText("Cancelar");
				cancelar.setPreferredSize(new java.awt.Dimension(120, 28));
				OyenteCancelar oc=new OyenteCancelar(this);
				cancelar.addActionListener(oc);
			}
			

			pack();
			this.setSize(269, 310);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	private class OyenteCancelar implements ActionListener
	{
		 JFrame f;
		 public OyenteCancelar(JFrame frame){
			 f=frame;
		 }
		@Override
		public void actionPerformed(ActionEvent arg0) {
			f.dispose();
			
		}
		
	}
	
	private class OyenteAceptar implements ActionListener
	{
		 JFrame f;
		 public OyenteAceptar(JFrame frame){
			 f=frame;
		 }
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (nombre.getText().length()==0 || apellido.getText().length()==0 || tipoDoc.getText().length()==0 || nroDoc.getText().length()==0 || nacionalidad.getText().length()==0 || telefono.getText().length()==0 || direccion.getText().length()==0)
			{
				JOptionPane.showMessageDialog(new JFrame(),
                        "Se deben ingresar todos los campos\n", 
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
			}
			else 
			{
				ModificarBD m=new ModificarBD();
				ModificarTabla mod=new ModificarTabla();
				System.out.println("INSERT INTO pasajeros VALUES ('"+tipoDoc.getText()+"', "+Integer.parseInt(nroDoc.getText())+", '"+apellido.getText()+"' ,'"+nombre.getText()+"', '"+direccion.getText()+"', '"+telefono.getText()+"', '"+nacionalidad.getText()+"');");
				try{m.modificar("INSERT INTO pasajeros VALUES ('"+tipoDoc.getText()+"', "+Integer.parseInt(nroDoc.getText())+", '"+apellido.getText()+"' ,'"+nombre.getText()+"', '"+direccion.getText()+"', '"+telefono.getText()+"', '"+nacionalidad.getText()+"');",v);}
				
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(new JFrame(),
	                        "El campo Nro de Documento debe ser num�rico\n", 
	                        "Error",
	                        JOptionPane.ERROR_MESSAGE);
				}

				mod.refrescarTabla("select * from pasajeros", tablaUsuarios,v);
				f.dispose();
				
			}
			
			}
			
	}
		
}



