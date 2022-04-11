package dataAccess;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Event;
import domain.Pronostico;
import domain.Question;
import domain.User;
import exceptions.EventAlreadyExists;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExists;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;

	ConfigXML c = ConfigXML.getInstance();

	public DataAccess(boolean initializeMode) {

		System.out.println("Creating DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		open(initializeMode);

	}

	public DataAccess() {
		this(false);
	}

	/**
	 * This is the data access method that initializes the database with some events
	 * and questions. This method is invoked by the business logic (constructor of
	 * BLFacadeImplementation) when the option "initialize" is declared in the tag
	 * dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB() {

		db.getTransaction().begin();
		try {

			Calendar today = Calendar.getInstance();

			int month = today.get(Calendar.MONTH);
			month += 1;
			int year = today.get(Calendar.YEAR);
			if (month == 12) {
				month = 0;
				year += 1;
			}
			User u = new User("Adminuser", "Adminpassword");
			db.persist(u);
			Event ev1 = new Event(1, "Atlético-Athletic", UtilDate.newDate(year, month, 17));
			Event ev2 = new Event(2, "Eibar-Barcelona", UtilDate.newDate(year, month, 17));
			Event ev3 = new Event(3, "Getafe-Celta", UtilDate.newDate(year, month, 17));
			Event ev4 = new Event(4, "Alavés-Deportivo", UtilDate.newDate(year, month, 17));
			Event ev5 = new Event(5, "Español-Villareal", UtilDate.newDate(year, month, 17));
			Event ev6 = new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 17));
			Event ev7 = new Event(7, "Malaga-Valencia", UtilDate.newDate(year, month, 17));
			Event ev8 = new Event(8, "Girona-Leganés", UtilDate.newDate(year, month, 17));
			Event ev9 = new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year, month, 17));
			Event ev10 = new Event(10, "Betis-Real Madrid", UtilDate.newDate(year, month, 17));

			Event ev11 = new Event(11, "Atletico-Athletic", UtilDate.newDate(year, month, 1));
			Event ev12 = new Event(12, "Eibar-Barcelona", UtilDate.newDate(year, month, 1));
			Event ev13 = new Event(13, "Getafe-Celta", UtilDate.newDate(year, month, 1));
			Event ev14 = new Event(14, "Alavés-Deportivo", UtilDate.newDate(year, month, 1));
			Event ev15 = new Event(15, "Español-Villareal", UtilDate.newDate(year, month, 1));
			Event ev16 = new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year, month, 1));

			Event ev17 = new Event(17, "Málaga-Valencia", UtilDate.newDate(year, month + 1, 28));
			Event ev18 = new Event(18, "Girona-Leganés", UtilDate.newDate(year, month + 1, 28));
			Event ev19 = new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year, month + 1, 28));
			Event ev20 = new Event(20, "Betis-Real Madrid", UtilDate.newDate(year, month + 1, 28));

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1, 1.5);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2, 1.5);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1, 3);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2, 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1, 1.5);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2, 3);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1, 1.5);
				q2 = ev1.addQuestion("Who will score first?", 2, 1.5);
				q3 = ev11.addQuestion("Who will win the match?", 1, 3);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2, 2);
				q5 = ev17.addQuestion("Who will win the match?", 1, 1.5);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2, 3);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1, 1.5);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2, 1.5);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1, 3);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2, 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1, 1.5);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2, 3);

			}

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Question createQuestion(Event event, String question, float betMinimum, double gananciasApuesta)
			throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum, gananciasApuesta);
		// db.persist(q);
		db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions
						// property of Event class
						// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
		db.getTransaction().commit();
		return q;

	}

	public void storeUser(User user) throws UserAlreadyExists {

		if (db.find(User.class, user) != null) {

			throw new UserAlreadyExists("El usuario ya existe");
		} else {
			db.getTransaction().begin();
			db.persist(user);
			System.out.println("User: " + user.getUser() + " registered");
			db.getTransaction().commit();
		}

	}

	public void upgradeUser(User user) {

	}

	public Event createEvent(int eventNumber, String description, Date eventDate) {
		System.out.println(">> DataAccess: createEvent=> number= " + eventNumber + ", date= " + eventDate
				+ ", description: " + description);

//			db.getTransaction().begin();
		Event ev = new Event(eventNumber, description, eventDate);
		db.persist(ev);
//			db.flush();
//			db.getTransaction().commit();
		return ev;
	}

	public void storePronostico(Pronostico pron, Event ev, User u, Question q) {
		;
		for (Question a : db.find(Event.class, ev.getEventNumber()).getQuestions()) {
			// db.getTransaction().begin();
			if (a.getQuestion().equals(pron.getQuestion().getQuestion())) {

//				System.out.println(pron.getCorrecta());
//				u.addPronostico(pron);
				a.addProns(pron);
				db.persist(ev);
//				db.persist(ev);
				// db.flush();
				System.out.println("Pronostico: " + pron.getRespuesta() + " registered");
			}
		}
	}

//	public void storePronosticoVerdadero(Pronostico pron, Question q, Event ev) {
//		db.getTransaction().begin();
//		for (Question a : db.find(Event.class, ev).getQuestions()) {
//			if (a.equals(q)) {
//				a.setPron(pron);
//				db.persist(ev);
//				break;
//			}
//		}
//		db.getTransaction().commit();
//		System.out.println("Pronostico: " + pron.getRespuesta() + " registered");
//	}

	public void storePronosticoVerdadero(Pronostico pron, Question q, Event ev, String correcta) {

		for (Question a : db.find(Event.class, ev).getQuestions()) {
			// db.getTransaction().begin();
			if (a.getQuestion().equals(pron.getQuestion().getQuestion())) {

				pron.setCorrecta(correcta);

			}
		}
		db.persist(ev);
		// db.flush();
		System.out.println("Pronostico: " + pron.getCorrecta() + " registered");
	}

	/**
	 * This method retrieves from the database the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1", Event.class);
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
		for (Event ev : events) {

			System.out.println(ev.toString());
			res.add(ev);
		}
		return res;
	}

	public void dbEvent(Event event) throws EventAlreadyExists {
		if (db.find(Event.class, event.getEventNumber()) == null) {
			db.getTransaction().begin();
			db.persist(event);
			System.out.println("Insertado en db:" + event);
			db.getTransaction().commit();
		} else {
			throw new EventAlreadyExists();
		}
	}

	/**
	 * This method retrieves from the database the dates a month for which there are
	 * events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();

		Date firstDayMonthDate = UtilDate.firstDayMonth(date);
		Date lastDayMonthDate = UtilDate.lastDayMonth(date);

		TypedQuery<Date> query = db.createQuery(
				"SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2", Date.class);
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
		for (Date d : dates) {
			System.out.println(d.toString());
			res.add(d);
		}
		return res;
	}

	public void open(boolean initializeMode) {

		System.out.println("Opening DataAccess instance => isDatabaseLocal: " + c.isDatabaseLocal()
				+ " getDatabBaseOpenMode: " + c.getDataBaseOpenMode());

		String fileName = c.getDbFilename();
		if (initializeMode) {
			new File(fileName).delete();
			new File(fileName + "$").delete();
//			fileName = fileName + ";drop";
			System.out.println("Deleting the DataBase");
		}
		if (c.isDatabaseLocal()) {
			emf = Persistence.createEntityManagerFactory("objectdb:" + fileName);
			db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			properties.put("javax.persistence.jdbc.user", c.getUser());
			properties.put("javax.persistence.jdbc.password", c.getPassword());

			emf = Persistence.createEntityManagerFactory(
					"objectdb://" + c.getDatabaseNode() + ":" + c.getDatabasePort() + "/" + fileName, properties);

			db = emf.createEntityManager();
		}

	}

	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= " + event + " question= " + question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);
	}

	public void close() {
		db.close();
		System.out.println("DataBase closed");
	}

	public User getUserLogin(String usuario, String contrase�a) { // false si el usuario no existe true si si
		TypedQuery<User> query = db.createQuery("SELECT o FROM User o WHERE o.getUser()=?1 AND o.getPassword()=?2",
				User.class);
		query.setParameter(1, "Adminuser");
		query.setParameter(2, "Adminpassword");
		query.setParameter(1, usuario);
		query.setParameter(2, contrase�a);
		Collection<User> offers = query.getResultList();
		if (offers.size() != 0) {
			Iterator<User> it = offers.iterator();
//			while (it.hasNext()) {
			return it.next();
//			}
		}
		return null;
	}

	public Event closeEvent(Event e) {
		db.getTransaction().begin();
		Event ev = db.find(Event.class, e);
		ev.setAvailable(false);
		db.persist(ev);

		db.getTransaction().commit();
		return ev;
	}

	public void eliminarEvent(Event e) {
//		TypedQuery<Event> query = db.createQuery("DELETE ev FROM Event ev WHERE ev.eventDate=e.eventDate AND ev.eventNumber=e.eventNumber", Event.class);
//		query.executeUpdate();
		if (db.find(Event.class, e.getEventNumber()) != null) {
			db.getTransaction().begin();
			db.remove(e);
			System.out.println("Eliminado de la db:" + e);
			db.getTransaction().commit();
		}
	}

	public double ajustWallet(Event e, User user) {
		double money = 0.0;
		Event ev = db.find(Event.class, e);
		TypedQuery<User> query = db.createQuery("SELECT Us FROM User Us WHERE Us.getDNI()!=?1 ", User.class);
		query.setParameter(1, null);
		List<User> list = query.getResultList();
		for (User u : list) {

			for (Question q : ev.getQuestions()) {
				for (Pronostico p : q.getProns()) {

					if (u.getDNI().equals(p.getDNI())) {
						if (p.getRespuesta().equals(p.getCorrecta())) {
							money = user.getWallet() + ((p.getApuesta()) * (q.getGananciasApuesta()));
							user.setWallet(money);
							db.getTransaction().begin();
							db.persist(user);
							db.getTransaction().commit();
							return db.find(User.class, user).getWallet();
						}
					}

				}
			}
		}
		return 6.9;
//		Pronostico pr = new Pronostico(user.getDNI(), user.getQues());
//		List<Question> q = ev.getQuestions();
//		for (Question question : q) {
//			if (question.getQuestion().equals(pr.getQuestion().getQuestion())) {
//				if (question.equals(pr.getRespuesta())) {
//					money = user.getWallet() + ((pr.getApuesta()) * (question.getGananciasApuesta()));
//					user.setWallet(money);
//				}
//			}
//		}

	}

	public boolean getUser(String usuario) {
		TypedQuery<User> query = db.createQuery("SELECT o FROM User o WHERE o.getUser()=?1", User.class);
		query.setParameter(1, usuario);

		Collection<User> offers = query.getResultList();

		if (offers.size() == 0) {
			return false;
		} else {

			return true;
		}
	}

}