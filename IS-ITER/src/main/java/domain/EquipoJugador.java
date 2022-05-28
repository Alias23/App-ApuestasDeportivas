package domain;

import java.util.Date;

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
public class EquipoJugador {
	private static final long serialVersionUID = 1L;
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer eventNumber;
	private String description;
	private Date eventDate;
	
	public EquipoJugador(Integer eventNumber, String description, Date eventDate) {
		this.eventNumber = eventNumber;
		this.description = description;
		this.eventDate = eventDate;
	}

	public EquipoJugador(Integer eventNumber, String description) {
		this.eventNumber = eventNumber;
		this.description = description;
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

}
