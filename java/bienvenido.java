

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.UIManager;
import com.alee.laf.WebLookAndFeel;


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
public class bienvenido extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JTextField textField;
	private JTextField txtPassword;
	private JPasswordField passwordField;
	private JTextField txtUsuario_1;
	private JTextField txtPassword_1;
	private JTextField textField_3;
	private JPasswordField passwordField_1;
	private JTextField txtBienvenidoALa;
	private VuelosConsulta vuelos;
	private String password;
	
	
	public static void main(String[] args) {
				bienvenido inst = new bienvenido();
				inst.setLocationRelativeTo(null);
		
	}
	
	public bienvenido() {
		vuelos=new VuelosConsulta();
		try
		{
		    // Setting up WebLookAndFeel style
		    UIManager.setLookAndFeel ( WebLookAndFeel.class.getCanonicalName () );
		}
		catch ( Throwable e )
		{
		    // Something went wrong
		}
		initGUI();
		setVisible(true);
	}
	
	
	/**
	 * Create the frame.
	 */
	private void initGUI (){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		setBounds(0, 0, 741, 350);
		setResizable(false);
		
		
		{
			contentPane = new JPanel();
			this.setContentPane(contentPane);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setLayout(null);
			
			JTabbedPane panel=new JTabbedPane();
			getContentPane().add(panel);
			panel.setBounds(12, 69, 708, 237);

			{
				JPanel panelAdmin= new JPanel();
				panel.addTab("Administrador", null, panelAdmin, null);
				panelAdmin.setBounds(26, 128, 736, 322);
				panelAdmin.setVisible(true);
				panelAdmin.setLayout(null);
				panelAdmin.setPreferredSize(new java.awt.Dimension(623, 214));
				{
					txtUsuario = new JTextField();
					panelAdmin.add(txtUsuario);
					txtUsuario.setEditable(false);
					txtUsuario.setText("Usuario:");
					txtUsuario.setBounds(25, 5, 116, 23);
					txtUsuario.setColumns(10);
				}
				
				{
					textField = new JTextField();
					panelAdmin.add(textField);
					textField.setBounds(147, 5, 116, 23);
					textField.setColumns(10);
				}
				{
					txtPassword = new JTextField();
					panelAdmin.add(txtPassword);
					txtPassword.setEditable(false);
					txtPassword.setText("Password:");
					txtPassword.setBounds(306, 5, 116, 23);
					txtPassword.setColumns(10);
				}
				{
					passwordField = new JPasswordField();
					panelAdmin.add(passwordField);
					passwordField.setBounds(428, 5, 111, 23);
				}
				{
					JButton btnLogin = new JButton("Login");
					panelAdmin.add(btnLogin);
					btnLogin.setBounds(557, 7, 129, 21);
					Oyente l=new Oyente(this);
					btnLogin.addActionListener(l);
				}
			}
			{
				JPanel panelEmpleado= new JPanel();
				panel.addTab("Empleado", null, panelEmpleado, null);
				panelEmpleado.setBounds(100, 100, 741, 350);
				panelEmpleado.setVisible(true);
				panelEmpleado.setLayout(null);
				{
					txtUsuario_1 = new JTextField();
					panelEmpleado.add(txtUsuario_1);
					txtUsuario_1.setEditable(false);
					txtUsuario_1.setText("Usuario:");
					txtUsuario_1.setBounds(25, 5, 116, 23);
					txtUsuario_1.setColumns(10);
				}
				{
					txtPassword_1 = new JTextField();
					panelEmpleado.add(txtPassword_1);
					txtPassword_1.setEditable(false);
					txtPassword_1.setText("Password:");
					txtPassword_1.setBounds(306, 5, 116, 23);
					txtPassword_1.setColumns(10);
				}
				{
					textField_3 = new JTextField();
					panelEmpleado.add(textField_3);
					textField_3.setBounds(147, 5, 116, 23);
					textField_3.setColumns(10);
				}
				{
					passwordField_1 = new JPasswordField();
					panelEmpleado.add(passwordField_1);
					passwordField_1.setBounds(428, 5, 111, 23);
				}
				{
					JButton btnLogin_1 = new JButton("Login");
					panelEmpleado.add(btnLogin_1);
					btnLogin_1.setBounds(557, 7, 129, 21);
					OyenteBoton emp=new OyenteBoton(this);
					btnLogin_1.addActionListener(emp);
				}
			}
			{
				txtBienvenidoALa = new JTextField();
				contentPane.add(txtBienvenidoALa);
				txtBienvenidoALa.setEditable(false);
				txtBienvenidoALa.setFont(new Font("Verdana", Font.BOLD, 25));
				txtBienvenidoALa.setText("Bienvenido a la base de datos");
				txtBienvenidoALa.setBounds(158, 17, 433, 36);
				txtBienvenidoALa.setColumns(10);
			}
		}

		//creo los listener
	

	}

	private class Oyente implements ActionListener{
		JFrame f;
		
		public Oyente(JFrame frame){
			f=frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if (vuelos.conectarBD(textField.getText(), passwordField.getText()))
			{
					AdminGUI admin=new AdminGUI(vuelos,f);
					textField.setText("");
					passwordField.setText("");
					setVisible(false);
			}
			else 
				JOptionPane.showMessageDialog(null, "Usuario o password incorrecto", "Error",
                    JOptionPane.ERROR_MESSAGE);
		}
		
		
	}
	
	private class OyenteBoton implements ActionListener{
		JFrame f;
		
		public OyenteBoton(JFrame frame){
			f=frame;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			try{
				password= "";
				char[] p=passwordField_1.getPassword();
	            for(char s:p)
	               password=password+s;
	            String aux=MD5(password);
	            password=aux;
	            if (vuelos.conectarBDempleado(Integer.parseInt(textField_3.getText()), password))
				{
						EmpleadoGUI empleado=new EmpleadoGUI(vuelos,f,Integer.parseInt(textField_3.getText()));
						textField_3.setText("");
						passwordField_1.setText("");
						empleado.setVisible(true);
						setVisible(false);
				}
				else 
					JOptionPane.showMessageDialog(null, "Usuario o password incorrecto", "Error",
	                    JOptionPane.ERROR_MESSAGE);
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, "Usuario o password incorrecto", "Error",
	                    JOptionPane.ERROR_MESSAGE);
			}}	
	}
	
	private String MD5(String md5) {
        try {
             java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
             byte[] array = md.digest(md5.getBytes());
             StringBuffer sb = new StringBuffer();
             for (int i = 0; i < array.length; ++i) {
               sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
             return sb.toString();
         } catch (java.security.NoSuchAlgorithmException e) {
         }
         return null;
    }
}

