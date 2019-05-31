import java.util.Scanner;

import javax.swing.JOptionPane;

public class Principal {

	Usuario usuario;
	
	private static Scanner t = new Scanner(System.in);
	
	public static void main(String[] args) {
		//Conectamos con la BD
		BaseDatos bd = new BaseDatos();
		String usuario, clave;
		if(bd.getConexion()!=null) {
			//Menú inicio sesión
			System.out.println("Selecciona opción");
			System.out.println("0-Salir");
			System.out.println("1-Iniciar sesión");
			System.out.println("2-Registrarse");
			int opcion = t.nextInt();t.nextLine();
			switch(opcion) {
			case 1:
				System.out.println("Introduce usuario");
				usuario = t.nextLine();
				System.out.println("Introduce clave");
				clave = t.nextLine();
				Usuario u = bd.hacerLogin(usuario,
						String.valueOf(clave));
				if(u==null) {
					System.out.println("El usuario no existe");
				}
				else {
					if(u.getClass()==Ciudadano.class) {
						System.out.println("El usuario es ciudadano");
					}
					if(u.getClass()==Autonomo.class) {
						System.out.println("El usuario es autónomo");
					}
				}
				break;
			case 2:
				//Registrar
				System.out.println("Tipo de usuario (A-Autónomo/C-Ciudadadano)");
				String tipo = t.nextLine();
				System.out.println("Introduce correo");
				usuario = t.nextLine();
				System.out.println("Introduce el password");
				clave = t.nextLine();
				if(tipo.equalsIgnoreCase("A")) {
					Autonomo a = new Autonomo();
					a.setEmail(usuario);
					a.setPswd(clave);
					if(!bd.altaUsuario(a)) {
						System.out.println("Error:No se ha creado el usuario");
					}
				}
				else {
					if(tipo.equalsIgnoreCase("C")) {
						Ciudadano c = new Ciudadano();
						c.setEmail(usuario);
						c.setPswd(clave);
						if(!bd.altaUsuario(c)) {
							System.out.println("Error:No se ha creado el usuario");
						}
					}
					else {
						System.out.println("Error:Tipo incorrecto");
					}
				}
				
				
				break;
			}
		}
		else {
			System.out.println("Error:No se ha podido conectar con la BD");
		}
	}
	
}
