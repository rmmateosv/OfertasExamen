import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class BaseDatos {
	private Connection conexion; 
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/ofertas";
	private String usuario = "adminOfer";
	private String clave = "admin";
	
	public BaseDatos() {
		//Conectamos con la bd
		try {
			Class.forName(driver);
			conexion = 
					DriverManager.getConnection(url, usuario, clave);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Driver no encontrado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Error al establecer la conexión "
					+ "con Ofertas");
		}
		
	}

	public Connection getConexion() {
		return conexion;
	}

	public void setConexion(Connection conexion) {
		this.conexion = conexion;
	}

	public boolean altaUsuario(Usuario a) {
		// TODO Auto-generated method stub
		boolean resultado = false;
		//Comprobar si el correo no existe en la bd
		//Creamos una sentencia
		try {
			PreparedStatement sentencia =
					conexion.prepareStatement(
			"select email from usuario "
			+ "where email = ?");
			//Indicamos que la  ? es el email de a
			sentencia.setString(1, a.getEmail());
			//Ejecutamos el select
			ResultSet registros = sentencia.executeQuery();
			//Comprobamos si devuelve un registro
			if(registros.next()) {
				//Ya existe el email
				System.out.println("Error:Ya existe ese email");
			}
			else {
				//insertamos registro en tabla usuario
				sentencia = conexion.prepareStatement(
				"insert into usuario values (?,aes_encrypt(?,0),?)"); 
				//rellenamos parámetros ?
				sentencia.setString(1, a.getEmail());
				sentencia.setString(2, a.getPswd());
				//Tipo según clase
				if(a.getClass()==Autonomo.class) {
					sentencia.setString(3, "A");
				}
				else {
					sentencia.setString(3, "C");
				}
				//Ejecutamos insert
				int numReg =  sentencia.executeUpdate();
				if(numReg==1) {
					resultado = true;
				}
				else {
					System.out.println("Error:No se puede insertar "
							+ "el usuario");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultado;
	}

	public Usuario hacerLogin(String usuario, String clave) {
		// TODO Auto-generated method stub
		Usuario resultado = null;
		try {
			PreparedStatement consulta = conexion.prepareStatement(
					"select * from usuario where "
					+ "email = ? and "
					+ "pswd = aes_encrypt(?,0)");
			consulta.setString(1, usuario);
			consulta.setString(2, clave);
			
			ResultSet filas = consulta.executeQuery();
			if(filas.next()) {
				//Comprobar el tipo de usuario
				String tipo = filas.getString(3);
				if(tipo.equals("C")) {
					Ciudadano c = new Ciudadano();
					c.setEmail(usuario);
					resultado=c;
				}
				if(tipo.equals("A")) {
					Autonomo a = new Autonomo();
					a.setEmail(usuario);
					resultado=a;
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultado;
	}
	
	
	
	
	
}
