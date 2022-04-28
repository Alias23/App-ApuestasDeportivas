package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlIDREF;

@Entity
public class Pronostico {

	@OneToOne
	private Event event;
	private String equipo;
	private String jugador;

	private String pronostico;
	@XmlIDREF
	private Question question;
	private float minBet;
	
	public Pronostico() {
		super();
	}
	public Pronostico(float minbet,String p) {
		this.minBet=minbet;
		this.pronostico=p;
	}
	public String getPronostico() {
		return pronostico;
	}

	public void setPronostico(String pronostico) {
		this.pronostico = pronostico;
	}
	
	

	public Event getEvent() {
		return event;
	}

	public String getEquipo() {
		return equipo;
	}

	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	public String getJugador() {
		return jugador;
	}

	public void setJugador(String jugador) {
		this.jugador = jugador;
	}

	public float getMinBet() {
		return minBet;
	}

	public void setMinBet(float minBet) {
		this.minBet = minBet;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}
}