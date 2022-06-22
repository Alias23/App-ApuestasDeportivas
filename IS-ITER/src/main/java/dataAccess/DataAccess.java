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
import domain.*;
import exceptions.EventAlreadyExists;
import exceptions.QuestionAlreadyExist;
import exceptions.UserAlreadyExists;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess {
	protected static EntityManager db;
	protected static EntityManagerFactory emf;
	private int cont = 0;

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

			Equipo e1 = new Equipo(1, "Atlético", UtilDate.newDate(year, month, 17));
			Equipo e2 = new Equipo(2, "Athletic", UtilDate.newDate(year, month, 17));
			Equipo e3 = new Equipo(3, "Eibar", UtilDate.newDate(year, month, 17));
			Equipo e4 = new Equipo(4, "Barcelona", UtilDate.newDate(year, month, 17));
			Equipo e5 = new Equipo(5, "Getafe", UtilDate.newDate(year, month, 17));
			Equipo e6 = new Equipo(6, "Celta", UtilDate.newDate(year, month, 17));
			Equipo e7 = new Equipo(7, "Alavés", UtilDate.newDate(year, month, 17));
			Equipo e8 = new Equipo(8, "Deportivo", UtilDate.newDate(year, month, 17));
			Equipo e9 = new Equipo(9, "Español", UtilDate.newDate(year, month, 17));
			Equipo e10 = new Equipo(10, "Villareal", UtilDate.newDate(year, month, 17));
			Equipo e11 = new Equipo(11, "Las Palmas", UtilDate.newDate(year, month, 17));
			Equipo e12 = new Equipo(12, "Sevilla", UtilDate.newDate(year, month, 17));
			Equipo e13 = new Equipo(13, "Malaga", UtilDate.newDate(year, month, 17));
			Equipo e14 = new Equipo(14, "Valencia", UtilDate.newDate(year, month, 17));
			Equipo e15 = new Equipo(15, "Girona", UtilDate.newDate(year, month, 17));
			Equipo e17 = new Equipo(16, "Leganés", UtilDate.newDate(year, month, 17));
			Equipo e18 = new Equipo(17, "Real Sociedad", UtilDate.newDate(year, month, 17));
			Equipo e19 = new Equipo(18, "Levante", UtilDate.newDate(year, month, 17));
			Equipo e16 = new Equipo(19, "Betis", UtilDate.newDate(year, month, 17));
			Equipo e20 = new Equipo(20, "Real Madrid", UtilDate.newDate(year, month, 17));

			Equipo e21 = new Equipo(21, "Atlético", UtilDate.newDate(year, month, 1));
			Equipo e22 = new Equipo(22, "Athletic", UtilDate.newDate(year, month, 1));
			Equipo e23 = new Equipo(23, "Eibar", UtilDate.newDate(year, month, 1));
			Equipo e24 = new Equipo(24, "Barcelona", UtilDate.newDate(year, month, 1));
			Equipo e25 = new Equipo(25, "Getafe", UtilDate.newDate(year, month, 1));
			Equipo e26 = new Equipo(26, "Celta", UtilDate.newDate(year, month, 1));
			Equipo e27 = new Equipo(27, "Alavés", UtilDate.newDate(year, month, 1));
			Equipo e28 = new Equipo(28, "Deportivo", UtilDate.newDate(year, month, 1));
			Equipo e29 = new Equipo(29, "Español", UtilDate.newDate(year, month, 1));

//			Equipo e30 = new Equipo(30, "Atlético");
//			Equipo e31 = new Equipo(31, "Athletic");
//			Equipo e32 = new Equipo(32, "Eibar");
//			Equipo e33 = new Equipo(33, "Barcelona");
//			Equipo e34 = new Equipo(34, "Getafe");
//			Equipo e35 = new Equipo(35, "Celta");
//			Equipo e36 = new Equipo(36, "Alavés");
//			Equipo e37 = new Equipo(37, "Deportivo");
//			Equipo e38 = new Equipo(38, "Español");

			db.persist(e1);
			db.persist(e2);
			db.persist(e3);
			db.persist(e4);
			db.persist(e5);
			db.persist(e6);
			db.persist(e7);
			db.persist(e8);
			db.persist(e9);
			db.persist(e10);
			db.persist(e11);
			db.persist(e12);
			db.persist(e13);
			db.persist(e14);
			db.persist(e15);
			db.persist(e16);
			db.persist(e17);
			db.persist(e18);
			db.persist(e19);
			db.persist(e20);
			db.persist(e21);
			db.persist(e22);
			db.persist(e23);
			db.persist(e24);
			db.persist(e25);
			db.persist(e26);
			db.persist(e27);
			db.persist(e28);
			db.persist(e29);
//			db.persist(e30);
//			db.persist(e31);
//			db.persist(e32);
//			db.persist(e33);
//			db.persist(e34);
//			db.persist(e35);
//			db.persist(e36);
//			db.persist(e37);
//			db.persist(e38);

//			Jugador j1 = e1.addJugador(1, "Williams", e1.getDescription());
//			db.persist(j1);

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

			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;

			if (Locale.getDefault().equals(new Locale("es"))) {
				q1 = ev1.addQuestion("¿Quién ganará el partido?", 1);
				q2 = ev1.addQuestion("¿Quién meterá el primer gol?", 2);
				q3 = ev11.addQuestion("¿Quién ganará el partido?", 1);
				q4 = ev11.addQuestion("¿Cuántos goles se marcarán?", 2);
				q5 = ev17.addQuestion("¿Quién ganará el partido?", 1);
				q6 = ev17.addQuestion("¿Habrá goles en la primera parte?", 2);
			} else if (Locale.getDefault().equals(new Locale("en"))) {
				q1 = ev1.addQuestion("Who will win the match?", 1);
				q2 = ev1.addQuestion("Who will score first?", 2);
				q3 = ev11.addQuestion("Who will win the match?", 1);
				q4 = ev11.addQuestion("How many goals will be scored in the match?", 2);
				q5 = ev17.addQuestion("Who will win the match?", 1);
				q6 = ev17.addQuestion("Will there be goals in the first half?", 2);
			} else {
				q1 = ev1.addQuestion("Zeinek irabaziko du partidua?", 1);
				q2 = ev1.addQuestion("Zeinek sartuko du lehenengo gola?", 2);
				q3 = ev11.addQuestion("Zeinek irabaziko du partidua?", 1);
				q4 = ev11.addQuestion("Zenbat gol sartuko dira?", 2);
				q5 = ev17.addQuestion("Zeinek irabaziko du partidua?", 1);
				q6 = ev17.addQuestion("Golak sartuko dira lehenengo zatian?", 2);
			}

			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);

			Pronostico p1;
			Pronostico p2;
			Pronostico p3;
			Pronostico p4;
			Pronostico p5;
			Pronostico p6;
			Pronostico p7;
			Pronostico p8;
			Pronostico p9;

			p1 = q1.addProns(0.0, "Atlético", 1);
			p2 = q1.addProns(1.0, "Athletic", 2);

			Pronostico e40 = q2.addProns(0.0, "Berenguer", 11);
			Pronostico e41 = q2.addProns(3.0, "Yuri", 12);
			Pronostico e42 = q2.addProns(1.0, "Muniain", 13);
			Pronostico e43 = q2.addProns(4.0, "Joao", 14);
			Pronostico e44 = q2.addProns(1.5, "Lemar", 15);
			Pronostico e45 = q2.addProns(1.2, "Hermoso", 16);

			db.persist(p1);
			db.persist(p2);

//			Pronostico e39 = q5.addProns(0.0, "Málaga", 7);
//			Pronostico e46 = q5.addProns(0.0, "Valencia", 8);
//
			Pronostico e47 = q3.addProns(0.0, "Atlético", 9);
			Pronostico e48 = q3.addProns(0.0, "Athletic", 10);
			Pronostico e49 = q6.addProns(0.0, "Falcao", 17);
			Pronostico e50 = q6.addProns(0.0, "Juaquin", 18);

//			db.persist(e31);
//			db.persist(e32);
//			db.persist(e33);
//			db.persist(e34);
//			db.persist(e35);
//			db.persist(e36);
			db.persist(e40);
			db.persist(e41);
			db.persist(e42);
			db.persist(e43);
			db.persist(e44);
			db.persist(e45);
//			db.persist(e39);
//			db.persist(e46);
			db.persist(e47);
			db.persist(e48);
			db.persist(e49);
			db.persist(e50);

			db.getTransaction().commit();
			System.out.println("Db initialized");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Question createQuestion(Event event, String question, float betMinimum) throws QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= " + event + " question= " + question + " betMinimum="
				+ betMinimum);

		Event ev = db.find(Event.class, event.getEventNumber());

		if (ev.DoesQuestionExists(question))
			throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		db.getTransaction().begin();
		Question q = ev.addQuestion(question, betMinimum);
		db.persist(ev);
		db.getTransaction().commit();
		return q;

	}

	public void wallet(Event event, Question question, Pronostico p, double cuanto, double ganancia) {
		System.out.println(">> DataAccess: wallet=> event= " + event + " question= " + question.getQuestion()
				+ " pronostico=" + p.getPronostico() + " cuanto apuesta el usuario=" + cuanto
				+ " ganancia por euro apostado=" + ganancia);
		db.getTransaction().begin();
		User u = this.getLog();
		u.setAvailable(true);
		u.actualizarWallet(p, cuanto, ganancia);
		db.persist(u);
		db.getTransaction().commit();
	}

	public void closeEvent(Event e) {
		Event ev = null;
		ev = db.find(Event.class, e);
		if (db.find(Event.class, e) != null) {
			db.getTransaction().begin();
			e.setAvailable(false);
			db.remove(ev);
			db.getTransaction().commit();
		}
	}

	public void setApues(double a) {
		User u = this.getLog();
		db.getTransaction().begin();
		u.setApuesta(a);
		System.out.println(u.getApuesta());
		db.persist(u);
		db.getTransaction().commit();
	}

	public void setPron(Pronostico p, String pr) {
		Pronostico pron = db.find(Pronostico.class, p);
		if (db.find(Pronostico.class, p) != null) {
			db.getTransaction().begin();
			User u = this.getLog();
			pron.setPronostico(pr);
			u.addPronostico(pron);
			db.persist(u);
			db.getTransaction().commit();
		}
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

	public Equipo createEquipo(String description, Date eventDate) {
		System.out.println(">> DataAccess: createEvent=> description: " + description + ", fecha=" + eventDate);
		db.getTransaction().begin();
		Equipo eq = new Equipo(description, eventDate);
		db.persist(eq);
		db.getTransaction().commit();
		return eq;
	}

	public Jugador createJugador(String nombre, String equipo, String apellidos, String dorsal, int e) {
		System.out.println(">> DataAccess: createPronostico=> nombre= " + nombre + ", apellidos= " + apellidos
				+ ", dorsal=" + dorsal  + ", equipo=" + equipo);
		Equipo eq = db.find(Equipo.class, e);
		System.out.println(eq.getDescription());
		db.getTransaction().begin();
		Jugador j = eq.addJugador(nombre, equipo, apellidos, dorsal);
		db.persist(j);
		db.getTransaction().commit();
		return j;
	}

	public Event createEvent(String description, Date eventDate) {
		System.out.println(">> DataAccess: createEvent=> date= " + eventDate + ", description: " + description);
		db.getTransaction().begin();
		Event ev = new Event(description, eventDate);
		db.persist(ev);
		db.getTransaction().commit();
		return ev;
	}

	public Pronostico createPronostico(double ganancias, String description, Integer num, int q) {
		System.out.println(">> DataAccess: createPronostico=> ganancias= " + ganancias + ", description: " + description
				+ ", number=" + num);
		Question ques = db.find(Question.class, q);
		System.out.println(ques.getQuestion());
		db.getTransaction().begin();
		if(description==null) {
			Pronostico p = ques.addProns(ganancias, "Lemar", num);
			db.persist(p);
			db.getTransaction().commit();
			return p;
		}else {
			Pronostico p = ques.addProns(ganancias, description, num);
			db.persist(p);
			db.getTransaction().commit();
			return p;
		}
		
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

	public Pronostico getPronostico(String p) {
		System.out.println(">> DataAccess: getPronostico");
		TypedQuery<Pronostico> query = db.createQuery("SELECT p FROM Pronostico p WHERE p.pronostico=?1",
				Pronostico.class);
		query.setParameter(1, p);
		Collection<Pronostico> offers = query.getResultList();
		if (offers.size() != 0) {
			Iterator<Pronostico> it = offers.iterator();
			return it.next();
		}
		return null;
	}

	public int getNumeroEquipo() {
		int cont = 0;
		System.out.println(">> DataAccess: getNumeroEquipo");
		TypedQuery<Equipo> query = db.createQuery("SELECT eq FROM Equipo eq", Equipo.class);
		List<Equipo> equipos = query.getResultList();
		for (Equipo equipo : equipos) {
			if (equipo != null) {
				cont++;
			}
		}
		return cont;
	}

	public Equipo getEq(String equi) {
		System.out.println(">> DataAccess: getEq");
		TypedQuery<Equipo> query = db.createQuery("SELECT eq FROM Equipo eq WHERE eq.description=?1", Equipo.class);
		query.setParameter(1, equi);
		Collection<Equipo> offers = query.getResultList();
		if (offers.size() != 0) {
			Iterator<Equipo> it = offers.iterator();
			return it.next();
		}
		return null;
	}

	public Vector<Equipo> getEquipo(Date date) {
		System.out.println(">> DataAccess: getEquipo");
		Vector<Equipo> res = new Vector<Equipo>();
		TypedQuery<Equipo> query = db.createQuery("SELECT eq FROM Equipo eq WHERE eq.eventDate=?1", Equipo.class);
		query.setParameter(1, date);
		List<Equipo> equipos = query.getResultList();
		for (Equipo equipo : equipos) {
			res.add(equipo);
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
		query.setParameter(50, "Adminuser");
		query.setParameter(51, "Adminpassword");
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

	public User getLog() {
		TypedQuery<User> query = db.createQuery("SELECT o FROM User o", User.class);
		Collection<User> offers = query.getResultList();
		if (offers.size() >= 0) {
			Iterator<User> it = offers.iterator();
			if (it.next().isAdmin()) {
				it.next();
			} else {
				return it.next();
			}
		}
		return null;
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