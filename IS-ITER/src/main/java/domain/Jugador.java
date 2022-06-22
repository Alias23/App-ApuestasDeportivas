package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Jugador {
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer jugadorNumber;
	private String nombre;
	private String apellidos;
	private String dorsal;
	@XmlIDREF
	private String equipo;

	public Jugador(Integer jugadorNumber, String nombre, String equipo, String apellidos, String dorsal) {
		this.jugadorNumber = jugadorNumber;
		this.nombre = nombre;
		this.equipo = equipo;
		this.apellidos = apellidos;
		this.dorsal = dorsal;
	}

	public Jugador(String nombre, String equipo, String apellidos, String dorsal) {
		this.nombre = nombre;
		this.equipo = equipo;
		this.apellidos = apellidos;
		this.dorsal = dorsal;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDorsal() {
		return dorsal;
	}

	public void setDorsal(String dorsal) {
		this.dorsal = dorsal;
	}

	public Integer getJugadorNumber() {
		return jugadorNumber;
	}

	public void setJugadorNumber(Integer jugadorNumber) {
		this.jugadorNumber = jugadorNumber;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

}
