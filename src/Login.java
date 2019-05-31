import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class Login extends JFrame{
	
	//Declaramos los elementos de la ventana
	JLabel lU, lP;
	JTextField tU;
	JPasswordField tP;
	JButton boton;

	public Login(BaseDatos bd) {
		
		this.setTitle("Login Ofertas");
		this.setSize(400,400);
		
		
		//Obtener el contenedor de la ventana
		Container contenedor = getContentPane();
		
		//Creamos el layout
		GridBagLayout l = new GridBagLayout();
		
		//Asignamos el layout al contenedor de la ventana
		contenedor.setLayout(l);
		
		//Creamos un objeto que va a tener las propiedades de
		//los elementos que se pongan en la ventana
		GridBagConstraints propiedades = new GridBagConstraints();
		//Asignamos alineaci�n izquierda
		propiedades.anchor = GridBagConstraints.WEST;
		//Todos los elementos van a ocupar una celda del layout
		propiedades.gridwidth = 1;
		propiedades.gridheight = 1;
		
		//Etiqueta usuario
		lU = new JLabel("Usuario");
		//Inidcamos en que columna y fila se va a situar
		propiedades.gridx=0;
		propiedades.gridy=0;
		//A�adimos la etiqueta la contenedor
		contenedor.add(lU,propiedades);
		//TextField Usuario
		tU = new JTextField(20);
		//Inidcamos en que columna y fila se va a situar
		propiedades.gridx=1;
		propiedades.gridy=0;
		contenedor.add(tU,propiedades);
		//Etiqueta password
		lP = new JLabel("Contrase�a");
		//Inidcamos en que columna y fila se va a situar
		propiedades.gridx=0;
		propiedades.gridy=1;
		//A�adimos la etiqueta la contenedor
		contenedor.add(lP,propiedades);
		//TextField Password
		tP = new JPasswordField(20);
		//Inidcamos en que columna y fila se va a situar
		propiedades.gridx=1;
		propiedades.gridy=1;
		contenedor.add(tP,propiedades);
		
		boton = new JButton("Iniciar Sesi�n");
		propiedades.gridx=0;
		propiedades.gridy=2;
		contenedor.add(boton,propiedades);
		
		//Programamos el bot�n
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(tU.getText().isEmpty()||
						String.valueOf(tP.getPassword()).isEmpty()) {
					JOptionPane.showMessageDialog(null, 
							"Debes rellenar usuario y contrase�a");
					
				}
				else {
					Usuario u = bd.hacerLogin(tU.getText(),
							String.valueOf(tP.getPassword()));
					if(u==null) {
						JOptionPane.showMessageDialog(null, 
								"El usuario no existe");
					}
					else {
						if(u.getClass()==Ciudadano.class) {
							
						}
						if(u.getClass()==Autonomo.class) {
							
						}
					}
				}
			}
		});
	}
	

}
