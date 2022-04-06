package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

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
	private boolean admin=false;
//	private ArrayList<Pronostico> pronosticos;
	private Question ques;
	private double Wallet;

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
//	public ArrayList<Pronostico> getPronosticos() {
//		return pronosticos;
//	}
//	public void setPronosticos(ArrayList<Pronostico> pronosticos) {
//		this.pronosticos = pronosticos;
//	}
	public double getWallet() {
		return Wallet;
	}
	public void setWallet(double wallet) {
		Wallet = wallet;
	}

	public User(String user, String password, String dNI, Date birthdate, String email, String nombre, String apellidos,
			ArrayList<Pronostico> pron, boolean admin) {
		super();
		this.user = user;
		this.password = password;
		DNI = dNI;
		this.birthdate = birthdate;
		this.email = email;
//		this.pronosticos = pron;
		this.Wallet =0.0;
	}
	public Question getQues() {
		return ques;
	}
	public void setQues(Question ques) {
		this.ques = ques;
	}
	public User(String user, String password) {
		this.user=user;
		this.password=password;
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

//	public void addPronostico(Pronostico pron) {
//		pronosticos.add(pron);
//	}

	public boolean getAdmin(String user, String password) {
		if (user.equals("Adminuser") && password.equals("Adminpassword")) {
			return true;
		}
		return  this.admin;
	}
	

}