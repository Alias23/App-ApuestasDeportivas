package gui;

import businessLogic.BLFacade;

import configuration.UtilDate;

import com.toedter.calendar.JCalendar;

import domain.Pronostico;
import domain.Question;
import domain.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class HacerPronosticoGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));
	private JLabel jLabelApuestas = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApuestasDisponibles"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private Question ques;
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JScrollPane scrollPaneApuestas = new JScrollPane();

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents = new JTable();
	private JTable tableQueries = new JTable();
	private JTable tableApuesta = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelApuesta;
	private JButton pronButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Pronostico")); //$NON-NLS-1$ //$NON-NLS-2$
	// Local variables
	private float minBet;
	private domain.Event evento;
	BLFacade facade = MainGUI.getBusinessLogic();

	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),

	};
	private String[] columnNamesQueries = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};

	private String[] columnNamesApuestas = new String[] { ResourceBundle.getBundle("Etiquetas").getString("ApuestaMin"),
			ResourceBundle.getBundle("Etiquetas").getString("Disponibles")

	};

	public HacerPronosticoGUI() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(889, 550));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("HacerApuesta"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(138, 210, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(138, 443, 130, 30));

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);

		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {

				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay = UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

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

						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
					}

					CreateQuestionGUI.paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);

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

		this.getContentPane().add(jCalendar1, null);

		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(138, 234, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2);// obtain ev object
				evento = ev;
				Vector<Question> queries = ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				if (ev.isAvailable()) {
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
//					gananciasLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryClosed"));
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
				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelApuesta.setDataVector(null, columnNamesApuestas);
				if (ev.isAvailable()) {
					for (domain.Question q : queries) {
						if (q.getQuestion().equals(tableQueries.getValueAt(u, 1))) {
							Vector<Pronostico> prons = (Vector<Pronostico>) q.getProns();
							if (prons.isEmpty())
								jLabelApuestas.setText(ResourceBundle.getBundle("Etiquetas").getString("NoApuestas")
										+ ": " + q.getQuestion());
							else
								jLabelApuestas.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")
										+ ": " + q.getQuestion());
							for (domain.Pronostico p : prons) {

								Vector<Object> row = new Vector<Object>();
								row.add(p.getMinBet());
								row.add(p.getPronostico());
								tableModelApuesta.addRow(row);
							}
							tableApuesta.getColumnModel().getColumn(0).setPreferredWidth(130);
							tableApuesta.getColumnModel().getColumn(1).setPreferredWidth(228);
//				domain.Question a = q;
//				ques = q;
							q.setQuestion(tableQueries.getValueAt(i, 1).toString());
							System.out.println("Apuesta minima: " + ques.getBetMinimum());
//						minBetLabel.setText(String.valueOf(a.getBetMinimum()));
							System.out.println(q.getGananciasApuesta());
//						gananciasLabel.setText(String.valueOf(a.getGananciasApuesta()));
							minBet = q.getBetMinimum();
						}
					}
				}
			}

//					gananciasLabel.setText("El evento no esta disponible");		
		});

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneQueries, null);
		this.getContentPane().add(scrollPaneEvents, null);

//		pronButton.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (Float.parseFloat(betField.getText()) <= minBet) {
//					gananciasLabel.setText("Introduzca una apuesta valida");
//				} else {
//					if (pronField.getText() == null) {
//						gananciasLabel.setText("Introduzca un pronostico");
//					} else {
//						User user = facade.getLog();
//						System.out.println("si"+user.getNombre());
////						String[] prons = new String[] {pronField.getText()};
//						Pronostico pron = new Pronostico(user.getDNI(), pronField.getText(),ques,
//								Float.parseFloat(betField.getText()));
////						Pronostico pron = new Pronostico(user.getDNI(), prons, null, 0,
////								Float.parseFloat(betField.getText()));
//						pron.setRespuesta(pronField.getText());
//						
////						USER.ADDPRONOSTICO(PRON);
//						facade.storePronostico(pron,evento,user,ques);
//						gananciasLabel.setText("Apuesta realizada con exito");
////						user.addPronostico(pron);
////						
//
//					}
//				}
//
//			}
//		});
		pronButton.setBounds(291, 443, 140, 30);
		getContentPane().add(pronButton);
		pronButton.setVisible(true);

		JButton jButtonLogOut = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LogOut"));
		jButtonLogOut.setBounds(466, 444, 115, 29);

		getContentPane().add(jButtonLogOut);

		JButton jButtonProfile = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Profile")); //$NON-NLS-1$ //$NON-NLS-2$
		jButtonProfile.setBounds(15, 234, 115, 116);
		getContentPane().add(jButtonProfile);

		scrollPaneApuestas = new JScrollPane();
		scrollPaneApuestas.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneApuestas.setBounds(627, 234, 225, 239);
		getContentPane().add(scrollPaneApuestas, null);

		tableApuesta = new JTable();
		scrollPaneApuestas.setColumnHeaderView(tableApuesta);

		scrollPaneApuestas.setViewportView(tableApuesta);
		tableModelApuesta = new DefaultTableModel(null, columnNamesApuestas);

		tableApuesta.setModel(tableModelApuesta);
		tableApuesta.getColumnModel().getColumn(0).setPreferredWidth(130);
		tableApuesta.getColumnModel().getColumn(1).setPreferredWidth(228);
		tableApuesta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				int u = tableQueries.getSelectedRow();
				int z = tableApuesta.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2);
				Vector<Question> queries = ev.getQuestions();
				if (ev.isAvailable()) {
					for (domain.Question q : queries) {
						if (q.getQuestion().equals(tableQueries.getValueAt(u, 1))) {
							Vector<Pronostico> prons = (Vector<Pronostico>) q.getProns();
							for (domain.Pronostico p : prons) {
								if (p.getPronostico().equals(tableApuesta.getValueAt(z, 1))) {
									domain.Pronostico s = p;
									s.setPronostico(tableApuesta.getValueAt(u, 1).toString());
								}
							}
						}
					}
//					domain.Question a = q;
//					ques = q;
//					a.setQuestion(tableQueries.getValueAt(i, 1).toString());
//					System.out.println(a.getBetMinimum());
////				minBetLabel.setText(String.valueOf(a.getBetMinimum()));
//					System.out.println(a.getGananciasApuesta());
////				gananciasLabel.setText(String.valueOf(a.getGananciasApuesta()));
//					minBet = a.getBetMinimum();
//					break;
				} else {
					jLabelApuestas.setText("La apuesta no esta disponible");
				}
			}
		});

		jLabelApuestas.setBounds(633, 207, 219, 20);

		getContentPane().add(jLabelApuestas);

		jButtonProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
				Profile frame = new Profile();
				frame.setVisible(true);
			}
		});

		jButtonLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
				MainLoginRegister frame = new MainLoginRegister();
				frame.setVisible(true);
			}
		});
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}