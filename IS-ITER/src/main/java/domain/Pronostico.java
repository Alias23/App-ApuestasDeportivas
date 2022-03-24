package domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Pronostico {
	
	
	private String DNI;
	private String respuesta;
	@OneToOne
	private Event event;
	private float apuesta;
	
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public float getApuesta() {
		return apuesta;
	}

	public void setApuesta(Integer apuesta) {
		this.apuesta = apuesta;
	}

	public Pronostico(String dNI, String respuesta, Event event, float apuesta){
		super();
		DNI = dNI;
		this.respuesta = respuesta;
		this.event = event;
		this.apuesta = apuesta;
	}
	

}