package domain;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Equipo {
	private static final long serialVersionUID = 1L;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer eventNumber;
	private String description;
	private Date eventDate;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	public Equipo(Integer eventNumber, String description, Date eventDate) {
		this.eventNumber = eventNumber;
		this.description = description;
		this.eventDate = eventDate;
	}

	public Equipo( String description,Date eventDate) {
		this.description = description;
		this.eventDate = eventDate;
	}
	
	public Equipo(Integer eventNumber,String description) {
		this.description = description;
		this.eventNumber=eventNumber;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Integer getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Jugador addJugador(String nombre, String e, String apellidos,String dorsal) {
		Jugador j = new Jugador(nombre, e,apellidos,dorsal);
		jugadores.add(j);
		return j;
	}

}
