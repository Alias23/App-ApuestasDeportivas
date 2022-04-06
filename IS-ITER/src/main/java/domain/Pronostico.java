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
	private String correcta;
	private Question question;
//	private float cuota;
	
	public String getDNI() {
		return DNI;
	}

	public void setDNI(String dNI) {
		DNI = dNI;
	}

//	public String getRespuesta() {
//		return respuesta;
//	}
//
//	public void setRespuesta(String respuesta) {
//		this.respuesta = respuesta;
//	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public float getApuesta() {
		return apuesta;
	}
//
//	public String[] getRespuestas() {
//		return respuestas;
//	}
//
//	public void setRespuestas(String[] respuestas) {
//		this.respuestas = respuestas;
//	}

//	public String getCorrecta() {
//		return correcta;
//	}
//
//	public void setCorrecta(String correcta) {
//		this.correcta = correcta;
//	}
//
//	public float getCuota() {
//		return cuota;
//	}
//
//	public void setCuota(float cuota) {
//		this.cuota = cuota;
//	}

	public void setApuesta(float apuesta) {
		this.apuesta = apuesta;
	}

	public void setApuesta(Integer apuesta) {
		this.apuesta = apuesta;
	}
	public Pronostico(Question q,String correcta){	
		this.question = q;
		this.correcta = correcta;
	}
	
//	public Pronostico(String dNI, String[] respuestas,String correcta,float cuota, float apuesta){
//		super();
//		DNI = dNI;
//		this.respuestas = respuestas;
//		this.correcta = correcta;
//		this.cuota = cuota;
//		this.apuesta = apuesta;
//	}

	public String getCorrecta() {
		return correcta;
	}

	public void setCorrecta(String correcta) {
		this.correcta = correcta;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public Pronostico(String dni2, String respuesta, Question q, float apuesta) {
		super();
		this.DNI = dni2;
		this.respuesta=respuesta;
		this.question=q;
		this.apuesta=apuesta;
	}
	public Pronostico(String dni,Question q) {
		this.DNI=dni;
		this.question=q;
	}
	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
	

}