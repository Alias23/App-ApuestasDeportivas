package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Entity
public class User {
	private String nombre;
	private String apellidos;
	@Id
	private String user;
	private String password;
	@Id
	private String DNI;
	private Date birthdate;
	private String email;
	private boolean admin;
	private double Wallet=0.0;
	private double apuesta=0.0;
	private boolean available=false;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private List<Pronostico> pronosticos = new ArrayList<Pronostico>();
	

	public User(String user, String password, String dNI, Date birthdate, String email, String nombre, String apellidos,
			boolean admin) {
		super();
		this.user = user;
		this.password = password;
		DNI = dNI;
		this.birthdate = birthdate;
		this.email = email;
		this.admin = false;
	}

	public List<Pronostico> getPronosticos() {
		return pronosticos;
	}
	
	public boolean finPron(Pronostico p) {
		for(Pronostico pron : pronosticos) {
			if(pron.getPronostico().equals(p.getPronostico())) {
				return true;
			}
		}
		return false;
	}

	public void setPronosticos(List<Pronostico> pronosticos) {
		this.pronosticos = pronosticos;
	}

	public double getApuesta() {
		return apuesta;
	}

	public void setApuesta(double apuesta) {
		this.apuesta = apuesta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public double getWallet() {
		return Wallet;
	}

	public void setWallet(double wallet) {
		Wallet = wallet;
	}

	public void actualizarWallet(Pronostico p, double cuanto, double ganancia) {
		if(this.isAvailable()) {
			this.setWallet(cuanto * ganancia);
		}
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public User(String user, String password) {
		this.user = user;
		this.password = password;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void addPronostico(Pronostico pron) {
		pronosticos.add(pron);
		
	}

	public boolean getAdmin(String user, String password) {
		if (user.equals("Adminuser") && password.equals("Adminpassword")) {
			this.setAdmin(true);
			return true;
		}
		return this.admin;
	}

}