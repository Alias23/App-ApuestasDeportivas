package domain;

import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
public class Pronostico {

	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer eventNumber;
	private String pronostico;
	private double ganancia;
	@XmlIDREF
	private Question question;

	public Pronostico() {
		super();
	}

	public Pronostico(double ganancia, String p, Integer eventNumber) {
		this.ganancia = ganancia;
		this.pronostico = p;
		this.eventNumber = eventNumber;
	}

	public Pronostico(Integer eventNumber, String p) {
		this.pronostico = p;
		this.eventNumber = eventNumber;
	}

	public String getPronostico() {
		return pronostico;
	}

	public Integer getEventNumber() {
		return eventNumber;
	}

	public void setEventNumber(Integer eventNumber) {
		this.eventNumber = eventNumber;
	}

	public void setPronostico(String pronostico) {
		this.pronostico = pronostico;
	}

	public double getGanancia() {
		return ganancia;
	}

	public void setGanancia(double ganancia) {
		this.ganancia = ganancia;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}