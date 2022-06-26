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
import domain.*;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Dimension;

public class CreatePronosticoGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));
	private final JLabel jLabelGanancia = new JLabel(
			ResourceBundle.getBundle("Etiquetas").getString("GananciasPorEuroApostado"));

	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private String inputDescription;
	private double ganan = 0.0;
	private int ques = 0;

	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JScrollPane scrollPanePronostico1 = new JScrollPane();

	private JTable tableEvents = new JTable();
	private JTable tableQueries = new JTable();
	private JTable tablePronostico1 = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelPronostico1;

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent")); //$NON-NLS-1$ //$NON-NLS-2$
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();

	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"), };
	private String[] columnNamesQueries = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query") };

	private String[] columnNamesPronostico1 = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EquipoN"),
			ResourceBundle.getBundle("Etiquetas").getString("Equipo"), };

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private JTextField textFieldGanancia;

	public CreatePronosticoGUI(Vector<domain.Pronostico> v) {

		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Pronostico> v) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(813, 686));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));

		jButtonCreate.setBounds(new Rectangle(78, 560, 130, 30));

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(248, 560, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(97, 495, 256, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(97, 531, 277, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);

		this.getContentPane().add(jCalendar, null);

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar.getDate());
		paintDaysWithEvents(jCalendar, datesWithEventsCurrentMonth);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);

		scrollPanePronostico1.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPanePronostico1.setBounds(40, 269, 207, 210);
		getContentPane().add(scrollPanePronostico1);

		JLabel jLabelEquipo1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption")); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelEquipo1.setBounds(40, 233, 165, 20);
		getContentPane().add(jLabelEquipo1);

		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {

				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
//							jCalendar1.setCalendar(calendarAct);
					Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));

					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);

					if (monthAct != monthAnt) {
						if (monthAct == monthAnt + 2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2
							// de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt + 1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}

						jCalendar.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar.getDate());
					}

					CreateQuestionGUI.paintDaysWithEvents(jCalendar, datesWithEventsCurrentMonth);

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
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not
																												// shown
																												// in
																												// JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			}
		});

		this.getContentPane().add(jCalendar, null);

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2);// obtain ev object
				Vector<Question> queries = ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				if (ev.isAvailable() == true) {
					if (queries.isEmpty())
						jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries") + ": "
								+ ev.getDescription());
					else
						jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent") + " "
								+ ev.getDescription());

					for (domain.Question q : queries) {
						Vector<Object> row = new Vector<Object>();

						row.add(q.getQuestionNumber());
						row.add(q.getQuestion());
						tableModelQueries.addRow(row);
					}
					tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
					tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				} else {
					jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries"));
				}
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);

		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				int u = tableQueries.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2);
				Vector<Question> queries = ev.getQuestions();
				tableModelPronostico1.setDataVector(null, columnNamesPronostico1);
				if (ev.isAvailable() == true) {
					for (domain.Question q : queries) {
						if (q.getQuestion().equals(tableQueries.getValueAt(u, 1))) {
							ques = q.getQuestionNumber();
							System.out.println(ques);
							Vector<Pronostico> prons = (Vector<Pronostico>) q.getProns();
							for (domain.Pronostico p : prons) {
								if (p.getGanancia() == 0.0) {
									Vector<Object> row = new Vector<Object>();
									row.add(p.getGanancia());
									row.add(p.getPronostico());
									tableModelPronostico1.addRow(row);
								}
							}
							tablePronostico1.getColumnModel().getColumn(0).setPreferredWidth(60);
							tablePronostico1.getColumnModel().getColumn(1).setPreferredWidth(268);

							q.setQuestion(tableQueries.getValueAt(u, 1).toString());
						}
					}
					jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries"));
				}
			}

		});

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneQueries, null);
		this.getContentPane().add(scrollPaneEvents, null);

		tablePronostico1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				int u = tableQueries.getSelectedRow();
				int z = tablePronostico1.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2);
				Vector<Question> queries = ev.getQuestions();
				if (ev.isAvailable()) {
					for (domain.Question q : queries) {
						if (q.getQuestion().equals(tableQueries.getValueAt(u, 1))) {
							Vector<Pronostico> prons = (Vector<Pronostico>) q.getProns();
							for (Pronostico p : prons) {
								System.out.println(p.getPronostico());
								if (p.getPronostico().equals(tablePronostico1.getValueAt(z, 1))) {
									System.out.println(p.getPronostico());
									inputDescription = p.getPronostico();
									p.setPronostico(tablePronostico1.getValueAt(z, 1).toString());
									ganan = p.getGanancia();
								}
							}
						}
					}
				}
			}
		});

		scrollPanePronostico1.setViewportView(tablePronostico1);
		tableModelPronostico1 = new DefaultTableModel(null, columnNamesPronostico1);

		scrollPanePronostico1.setViewportView(tablePronostico1);
		this.getContentPane().add(scrollPanePronostico1, null);
		tablePronostico1.setModel(tableModelPronostico1);

		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneEvents.setBounds(318, 50, 346, 150);
		getContentPane().add(scrollPaneEvents);

		jLabelEvents.setBounds(318, 18, 259, 16);
		getContentPane().add(jLabelEvents);

		scrollPaneQueries.setBounds(new Rectangle(138, 234, 406, 116));
		scrollPaneQueries.setBounds(318, 269, 292, 116);
		getContentPane().add(scrollPaneQueries);

		jLabelQueries.setBounds(318, 236, 292, 14);
		getContentPane().add(jLabelQueries);

		textFieldGanancia = new JTextField();
		textFieldGanancia.setBounds(318, 453, 146, 26);
		getContentPane().add(textFieldGanancia);
		textFieldGanancia.setColumns(10);

		jLabelGanancia.setBounds(318, 417, 292, 20);
		getContentPane().add(jLabelGanancia);
		tablePronostico1.getColumnModel().getColumn(0).setPreferredWidth(60);
		tablePronostico1.getColumnModel().getColumn(1).setPreferredWidth(268);

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
//					    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, today);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

	}

	private void jButtonCreate_actionPerformed(ActionEvent e) {
		// domain.Event event = ((domain.Event) jComboBoxEvents.getSelectedItem());

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			// Displays an exception if the query field is empty

			Date eventDate = jCalendar.getDate();

			int eventNum = 0;

			// It could be to trigger an exception if the introduced string is not a number

			// Obtain the business logic from a StartWindow class (local or remote)
			// BLFacade facade0 = MainGUI.getBusinessLogic();
			BLFacade facade = MainGUI.getBusinessLogic();

			if (facade.getEvents(eventDate) == null) {
				eventNum = 1;
			} else {
				eventNum = facade.getLastEventNumber(eventDate) + 16;
			}

			facade.createPronostico(Double.parseDouble(textFieldGanancia.getText()), inputDescription, ques);

			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryCreated"));

		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
