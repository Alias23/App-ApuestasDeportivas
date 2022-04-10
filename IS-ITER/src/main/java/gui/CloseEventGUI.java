package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Dimension;

public class CloseEventGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;
	private JTable tableEvents = new JTable();
	private DefaultTableModel tableModelEvents;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCloseEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent")); //$NON-NLS-1$ //$NON-NLS-2$
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	private Event eve;
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),

	};
	BLFacade facade = MainGUI.getBusinessLogic();
	/**
	 * Create the frame.
	 */

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					CreateEventGUI frame = new CreateEventGUI(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public CloseEventGUI(Vector<domain.Event> v) {

		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {
		
		

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(697, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		jLabelListOfEvents.setBounds(new Rectangle(290, 18, 277, 20));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		
		jButtonClose.setBounds(new Rectangle(310, 259, 215, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(100, 300, 380, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(100, 300, 380, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		
		this.getContentPane().add(jButtonCloseEvent, null);
		this.getContentPane().add(jLabelListOfEvents, null);

		this.getContentPane().add(jCalendar, null);

		
		datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar.getDate());
		paintDaysWithEvents(jCalendar, datesWithEventsCurrentMonth);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);

		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//					this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//						public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: " + calendarAnt.getTime());
					System.out.println("calendarAct: " + calendarAct.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());

					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct != monthAnt) {
						if (monthAct == monthAnt + 2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente,
							// devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt + 1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}

						jCalendar.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar.getDate());
					}

					paintDaysWithEvents(jCalendar, datesWithEventsCurrentMonth);

					// Date firstDay = UtilDate.trim(new
					// Date(jCalendar.getCalendar().getTime().getTime()));
					Date firstDay = UtilDate.trim(calendarAct.getTime());
					try {
					tableModelEvents.setDataVector(null, columnNamesEvents);
					tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

					BLFacade facade = MainGUI.getBusinessLogic();

					Vector<domain.Event> events = facade.getEvents(firstDay);

					if (events.isEmpty())
						jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": "
								+ dateformat1.format(calendarAct.getTime()));
					else
						jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
								+ dateformat1.format(calendarAct.getTime()));
					for (domain.Event ev : events) {
						Vector<Object> row = new Vector<Object>();

						System.out.println("Events " + ev);

						row.add(ev.getEventNumber());
						row.add(ev.getDescription());
						row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
						tableModelEvents.addRow(row);
					}
					tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
					tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
					tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); 
					
					} catch (Exception e1) {

						jLabelError.setText(e1.getMessage());
					}

				}
			}
		});
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				eve = (domain.Event) tableModelEvents.getValueAt(i, 2);
				
			}
		});
		jButtonCloseEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					jLabelError.setText("");
					jLabelMsg.setText("");
					Date eventDate = jCalendar.getDate();
					BLFacade facade = MainGUI.getBusinessLogic();

					User user = facade.getUserLogged();
//					Pronostico pronostico = facade.getStorePronostico();
					facade.closeEvent(eve);
					System.out.println(facade.ajustWallet(eve,facade.getUserLogged()));
					System.out.println(user.getWallet());
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryClosed"));

					// } catch (EventFinished e1) {
					// jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished")
					// + ": "
					// + event.getDescription());
					// } catch (QuestionAlreadyExist e1) {
					// jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
					// } catch (java.lang.NumberFormatException e1) {
					// jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				} catch (Exception e1) {

					e1.printStackTrace();

				}
			}
		});
		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		this.getContentPane().add(scrollPaneEvents, null);

		jButtonCloseEvent.setBounds(new Rectangle(40, 259, 215, 30));
		jButtonCloseEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		
		JScrollPane scrollPaneEvents_1 = new JScrollPane();
		scrollPaneEvents_1.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneEvents_1.setBounds(290, 50, 346, 150);
		getContentPane().add(scrollPaneEvents_1);
		
		
	}

	public static void paintDaysWithEvents(JCalendar jCalendar, Vector<Date> datesWithEventsCurrentMonth) {
		// For each day with events in current month, the background color for that day
		// is changed.

		Calendar calendar = jCalendar.getCalendar();

		int month = calendar.get(Calendar.MONTH);
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;

		for (Date d : datesWithEventsCurrentMonth) {

			calendar.setTime(d);
			System.out.println(d);

			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//				    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, today);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		

	}


	
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}