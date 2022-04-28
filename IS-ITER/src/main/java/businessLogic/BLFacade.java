package businessLogic;

import java.util.Vector;
import java.util.Date;

//import domain.Booking;
import domain.Question;
import domain.User;
import domain.Event;
import domain.Pronostico;
import exceptions.EventAlreadyExists;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExists;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade {

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */
	@WebMethod
	Question createQuestion(Event event, String question, float betMinimum, double gananciasApuesta)
			throws EventFinished, QuestionAlreadyExist;

	@WebMethod
	public void closeEvent(Event e);

//	@WebMethod
//	public double ajustWallet(Event e, User user);

	@WebMethod
	public void eliminarEvent(Event e);

	@WebMethod
	public Vector<Event> getEvents(Date date);

	@WebMethod
	public Vector<Date> getEventsMonth(Date date);

	@WebMethod
	public void initializeBD();

	@WebMethod
	public void storeUser(User user) throws UserAlreadyExists;

	@WebMethod
	public void dbEvent(Event event) throws EventAlreadyExists;

	@WebMethod
	public User getUserLogin(String usuario, String contraseña);

	@WebMethod
	public boolean getUser(String u);

	@WebMethod
	public Event createEvent(int eventNumber, String description, Date eventDate) throws EventFinished;

	@WebMethod
	public int getLastEventNumber(Date date);
	
	@WebMethod
	public User getLogged();
	
	@WebMethod
	public User getLog();
	
}