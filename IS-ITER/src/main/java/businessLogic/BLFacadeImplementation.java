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

import domain.*;

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
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);

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

	}

	@WebMethod
	public void dbEvent(Event event) throws EventAlreadyExists {
		dbManager.open(false);
		dbManager.dbEvent(event);
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

	@WebMethod
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
	public int getNumeroEquipo() {
		int cont = 0;
		dbManager.open(false);
		cont = dbManager.getNumeroEquipo();
		dbManager.close();
		return cont;
	}

	@WebMethod
	public Event createEvent(String description, Date eventDate) throws EventFinished {
		dbManager.open(false);
		Event ev = null;

		if (new Date().compareTo(eventDate) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorDateHasPasses"));

		ev = dbManager.createEvent(description, eventDate);

		dbManager.close();

		return ev;

	}

	@WebMethod
	public Pronostico getPronostico(String p) {
		dbManager.open(false);
		Pronostico pr = dbManager.getPronostico(p);
		dbManager.close();
		return pr;
	}

	@WebMethod
	public void closeEvent(Event e) {
		dbManager.open(false);
		dbManager.closeEvent(e);
		dbManager.close();
	}

	@WebMethod
	public void wallet(Event event, Question question, Pronostico p, double cuanto, double ganancia) {
		dbManager.open(false);
		dbManager.wallet(event, question, p, cuanto, ganancia);
		dbManager.close();
	}

	@WebMethod
	public void setPron(Pronostico p, String pr) {
		dbManager.open(false);
		dbManager.setPron(p, pr);
		dbManager.close();
	}

	@WebMethod
	public Pronostico createPronostico(double ganancias, String description, int q) {
		dbManager.open(false);
		Pronostico p = dbManager.createPronostico(ganancias, description, q);
		dbManager.close();
		return p;
	}

	@WebMethod
	public Equipo createEquipo(String description, Date eventDate) {
		dbManager.open(false);
		Equipo eq = dbManager.createEquipo(description, eventDate);
		dbManager.close();
		return eq;
	}

	@WebMethod
	public Jugador createJugador(String nombre, String equipo, String apellidos, String dorsal, int e) {
		dbManager.open(false);
		Jugador j = dbManager.createJugador(nombre, equipo, apellidos, dorsal, e);
		dbManager.close();
		return j;
	}

	@WebMethod
	public Equipo getEq(String eq) {
		dbManager.open(false);
		Equipo e = dbManager.getEq(eq);
		dbManager.close();
		return e;
	}

	@WebMethod
	public Vector<Equipo> getEquipo(Date date) {
		dbManager.open(false);
		Vector<Equipo> e = dbManager.getEquipo(date);
		dbManager.close();
		return e;
	}

	@WebMethod
	public void setApues(double a) {
		dbManager.open(false);
		dbManager.setApues(a);
		dbManager.close();

	}

	@WebMethod
	public User getLog() {
		dbManager.open(false);
		User u = dbManager.getLog();
		dbManager.close();
		return u;
	}
}