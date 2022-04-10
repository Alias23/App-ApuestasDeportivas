package domain;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Question implements Serializable {

	@Id
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@GeneratedValue
	private Integer questionNumber;
	private String question;
	private float betMinimum;
	private double gananciasApuesta;

	private String result;
	@XmlIDREF
	private Event event;
	private List<Pronostico> prons = new ArrayList<Pronostico>();
	private List<Pronostico> pronsVerdadero = new ArrayList<Pronostico>();

	public List<Pronostico> getPronsVerdadero() {
		return pronsVerdadero;
	}

	public void setPronsVerdadero(List<Pronostico> pronsVerdadero) {
		this.pronsVerdadero = pronsVerdadero;
	}

	public List<Pronostico> getProns() {
		return prons;
	}

	public void setProns(ArrayList<Pronostico> prons) {
		this.prons = prons;
	}

	public Question() {
		super();
	}
	public void setPron(Pronostico pron) {
		prons.add(pron);
	}

	public Question(Integer queryNumber, String query, float betMinimum, double gananciasApuesta, Event event) {
		super();
		this.questionNumber = queryNumber;
		this.question = query;
		this.betMinimum = betMinimum;
		this.gananciasApuesta = gananciasApuesta;
		this.event = event;

	}

	public Question(String query, float betMinimum, double gananciasApuesta, Event event) {
		super();
		this.question = query;
		this.betMinimum = betMinimum;
		this.gananciasApuesta = gananciasApuesta;
		// this.event = event;
	}

	/**
	 * Get the number of the question
	 * 
	 * @return the question number
	 */
	public Integer getQuestionNumber() {
		return questionNumber;
	}

	/**
	 * Set the bet number to a question
	 * 
	 * @param questionNumber to be setted
	 */
	public void setQuestionNumber(Integer questionNumber) {
		this.questionNumber = questionNumber;
	}
	
	public void addPronsVerdaderos(Pronostico p) {
		this.pronsVerdadero.add(p);
	}

	/**
	 * Get the question description of the bet
	 * 
	 * @return the bet question
	 */

	public String getQuestion() {
		return question;
	}

	/**
	 * Set the question description of the bet
	 * 
	 * @param question to be setted
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @return the minimum bet ammount
	 */

	public float getBetMinimum() {
		return betMinimum;
	}

	/**
	 * Get the minimun ammount of the bet
	 * 
	 * @param betMinimum minimum bet ammount to be setted
	 */

	public void setBetMinimum(float betMinimum) {
		this.betMinimum = betMinimum;
	}

	public double getGananciasApuesta() {
		return gananciasApuesta;
	}

	public void setGananciasApuesta(double gananciasApuesta) {
		this.gananciasApuesta = gananciasApuesta;
	}

	/**
	 * Get the result of the query
	 * 
	 * @return the the query result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Get the result of the query
	 * 
	 * @param result of the query to be setted
	 */

	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Get the event associated to the bet
	 * 
	 * @return the associated event
	 */
	public Event getEvent() {
		return event;
	}

	/**
	 * Set the event associated to the bet
	 * 
	 * @param event to associate to the bet
	 */
	public void setEvent(Event event) {
		this.event = event;
	}

	public String toString() {
		return questionNumber + ";" + question + ";" + Float.toString(betMinimum);
	}
	
	public void addProns(Pronostico pron) {
		prons.add(pron);
	}

}