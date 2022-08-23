import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.WindowConstants;






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
public class AdminGUI extends javax.swing.JFrame {
	private JScrollPane jScrollPane1;
	private JScrollPane jScrollPane2;
	private JComboBox<String> jComboBox1;
	private JTable tablaConsultas;
	private JScrollBar jScrollBar1;
	private JButton Regresar;
	private JTable tablaCaracteristicas;
	private JScrollPane jScrollPane4;
	private JTextArea ConsultaArea;
	private JButton BotonMostrar;
	private JTable tablaNombres;
    private JScrollPane jScrollPane3;
	protected JFrame j;
	protected VuelosConsulta v;
	protected static ModificarTabla m=new ModificarTabla();
	protected static ModificarBD mod=new ModificarBD();

	public AdminGUI(VuelosConsulta vuelos, JFrame contentPane) {
		super();
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
			{
				jScrollPane1 = new JScrollPane();
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(60, 30, 576, 176);
				{
					ConsultaArea = new JTextArea();
					jScrollPane1.setViewportView(ConsultaArea);
					ConsultaArea.setText("Escriba aqu�");
					ConsultaArea.setBounds(115, 33, 480, 155);
					OyenteArea ml=new OyenteArea();
					ConsultaArea.addMouseListener(ml);
				}
			}
			{
				jScrollPane2 = new JScrollPane();
				getContentPane().add(jScrollPane2);
				jScrollPane2.setBounds(60, 240, 576, 81);
				{
					
					tablaConsultas = new JTable();
					jScrollPane2.setViewportView(tablaConsultas);
					tablaConsultas.setAutoCreateRowSorter(true);
				}
			}
			{
				BotonMostrar = new JButton();
				getContentPane().add(BotonMostrar);
				BotonMostrar.setText("Mostrar");
				BotonMostrar.setBounds(492, 212, 143, 23);
				OyenteMostrar o=new OyenteMostrar();
				BotonMostrar.addActionListener(o);
			}
			{
				jScrollPane3 = new JScrollPane();
				getContentPane().add(jScrollPane3);
				jScrollPane3.setBounds(714, 30, 415, 176);
				{
					
					tablaNombres = new JTable();
					jScrollPane3.setViewportView(tablaNombres);
					tablaNombres.setAutoCreateRowSorter(true);
					m.refrescarTabla("Show Tables", tablaNombres,v);
					OyenteTabla o1=new OyenteTabla();
					tablaNombres.addMouseListener(o1);
					
				}
			}
			{
				jScrollPane4 = new JScrollPane();
				getContentPane().add(jScrollPane4);
				jScrollPane4.setBounds(714, 240, 415, 81);
				{
					tablaCaracteristicas = new JTable();
					jScrollPane4.setViewportView(tablaCaracteristicas);
					tablaCaracteristicas.setAutoCreateRowSorter(true);
				}
			}
			{
				Regresar = new JButton();
				getContentPane().add(Regresar);
				Regresar.setText("Regresar");
				Regresar.setBounds(1161, 321, 125, 23);
				OyenteRegresar o2=new OyenteRegresar(this);
				Regresar.addActionListener(o2);
			}
			{
				jScrollBar1 = new JScrollBar();
				getContentPane().add(jScrollBar1);
				jScrollBar1.setBounds(140, 307, 417, 14);
			}
			{
				ComboBoxModel<String> jComboBox1Model = 
						new DefaultComboBoxModel<String>(
								new String[] { "Consulta", "Modificaci�n" });
				jComboBox1 = new JComboBox<String>();
				getContentPane().add(jComboBox1);
				jComboBox1.setModel(jComboBox1Model);
				jComboBox1.setBounds(375, 212, 111, 22);
			}
			pack();
			this.setSize(1318, 397);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	   private class OyenteMostrar implements ActionListener {
		
		@Override
		
		public void actionPerformed(ActionEvent arg0) {
			if (jComboBox1.getSelectedIndex()==0)
			{
			m.refrescarTabla(ConsultaArea.getText().trim(),tablaConsultas,v);//Muestra la tabla con el resultado de la consulta
			m.refrescarTabla("Show Tables", tablaNombres,v);//Refresca la tabla que muestra los nombres de la tablas de la base de datos
			}
			else 
			{
			mod.modificar(ConsultaArea.getText().trim(),v);
			m.refrescarTabla("Show Tables", tablaNombres,v);//Refresca la tabla que muestra los nombres de la tablas de la base de datos
			}
		}
		   
	   }
	   
	   private class OyenteTabla implements MouseListener{

		  
		@Override
		public void mouseClicked(MouseEvent arg0) {
			int row = tablaNombres.rowAtPoint(arg0.getPoint());
	        if (row >= 0) {
	        	String valor=(String) tablaNombres.getValueAt(row,0);
	        	m.refrescarTabla("Describe "+valor+";",tablaCaracteristicas,v);//Muestra la informacion de las tablas seleccionadas
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
			//Se vuelve a mostrar el menu principal
			public void actionPerformed(ActionEvent e) {
				j.setVisible(true);
				v.desconectarBD();
				f.dispose();
				
				
	   }}
	   private class OyenteArea implements MouseListener
	   {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			if (ConsultaArea.getText().equals("Escriba aqu�"))
				ConsultaArea.setText("");
			
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
}
	



