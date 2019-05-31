public class Usuario {

	private String email;
	private String pswd;

	public Usuario() {
		
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	public String getPswd() {
		return pswd;
	}

	public void consultarOfertas() {
		throw new UnsupportedOperationException();
	}

	public boolean login() {
		throw new UnsupportedOperationException();
	}

	public boolean registrarse() {
		throw new UnsupportedOperationException();
	}
}
