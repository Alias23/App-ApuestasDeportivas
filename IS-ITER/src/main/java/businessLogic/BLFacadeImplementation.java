package businessLogic;

//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;

import domain.User;
import domain.Event;
import domain.Pronostico;
import exceptions.EventAlreadyExists;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExists;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	DataAccess dbManager;

	User loggedUser;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			dbManager = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
		} else
			dbManager = new DataAccess();
		dbManager.close();

	}

	public BLFacadeImplementation(DataAccess da) {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();
		}
		dbManager = da;
	}

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
	public Question createQuestion(Event event, String question, float betMinimum, double gananciasApuesta)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum, gananciasApuesta);

		dbManager.close();

		return qry;
	}

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEvents(Date date) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	public void close() {
		DataAccess dB4oManager = new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD() {
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@WebMethod
	public void storeUser(User user) throws UserAlreadyExists {
		dbManager.open(false);
		dbManager.storeUser(user);
		dbManager.close();
		// TODO Auto-generated method stub

	}

	@WebMethod
	public void dbEvent(Event event) throws EventAlreadyExists {
		dbManager.open(false);
		dbManager.dbEvent(event);
		dbManager.close();
		// TODO Auto-generated method stub

	}

	@WebMethod
	public void storePronostico(Pronostico pron) {
		dbManager.open(false);
		dbManager.storePronostico(pron);
		dbManager.close();
	}

	public User getUserLogin(String usuario, String contraseña) {
		dbManager.open(false);
		User a = dbManager.getUserLogin(usuario, contraseña);
		dbManager.close();
		loggedUser = a;
		return a;

	}

	public User getUserLogged() {

		return loggedUser;

	}

	public boolean getUser(String user) {

		dbManager.open(false);
		boolean a = dbManager.getUser(user);
		dbManager.close();
		return a;
	}

	public int getLastEventNumber(Date date) {
		int num = 0;
		dbManager.open(false);
		List<Event> eventList = dbManager.getEvents(date);
		for (Event e : eventList) {
			if (e != null) {
				num++;
			}
		}
		return num;
	}

	@WebMethod
	public Event createEvent(int eventNumber, String description, Date eventDate) throws EventFinished {
		dbManager.open(false);
		Event ev = null;

		if (new Date().compareTo(eventDate) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorDateHasPasses"));

		ev = dbManager.createEvent(eventNumber, description, eventDate);

		dbManager.close();

		return ev;

	}
	@WebMethod
	public void storePronosticoVerdadero(Pronostico pron, Question q,Event ev) {
		dbManager.open(false);
		dbManager.storePronosticoVerdadero(pron, q, ev);
		dbManager.close();
	}
	@WebMethod
	public void eliminarEvent(Event e) {
		dbManager.open(false);
		dbManager.eliminarEvent(e);
		dbManager.close();
	}
	@WebMethod
	public void closeEvent(Date eventDate) {
		dbManager.open(false);
		dbManager.closeEvent(eventDate);
		dbManager.close();
	}
	
	@WebMethod
	public void ajustWallet(Date eventDate, User user) {
		dbManager.open(false);
		dbManager.adjustWallet(eventDate, user);
		dbManager.close();
	}

}