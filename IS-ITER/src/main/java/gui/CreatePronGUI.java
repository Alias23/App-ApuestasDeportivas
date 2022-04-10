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

public class CreatePronGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JTable tableEvents = new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private Question ques;

	private JButton createPronButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Pronostico")); //$NON-NLS-1$ //$NON-NLS-2$
	// Local variables
	private domain.Event evento;
	BLFacade facade = MainGUI.getBusinessLogic();
//	private String[] res;

	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),

	};
	private String[] columnNamesQueries = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private String[] columnNamesProns = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("PronN")

	};

	private final JLabel errorLabel = new JLabel(); // $NON-NLS-1$ //$NON-NLS-2$
	private JTextField pronField;
	private Question a;

	public CreatePronGUI() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(772, 564));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 210, 406, 14);
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

		scrollPaneEvents.setBounds(new Rectangle(292, 50, 456, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 234, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEvents.getSelectedRow();
				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2);// obtain ev object
				evento = ev;
				Vector<Question> queries = ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(
							ResourceBundle.getBundle("Etiquetas").getString("NoQueries") + ": " + ev.getDescription());
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
				for (domain.Question q : queries) {

					if (q.getQuestion().equals(tableQueries.getValueAt(u, 1))) {
						domain.Question a = q;
						ques=q;
						a.setQuestion(tableQueries.getValueAt(i, 1).toString());

						break;
					}
				}

			}
		});

		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		errorLabel.setBounds(138, 416, 443, 13);

		getContentPane().add(errorLabel);

		pronField = new JTextField();
		// $NON-NLS-1$ //$NON-NLS-2$
		pronField.setBounds(60, 387, 386, 19);
		getContentPane().add(pronField);
		pronField.setColumns(10);
		
		createPronButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Guardar"));
		createPronButton.setBounds(291, 443, 140, 30);
		getContentPane().add(createPronButton);
		
		JLabel lblRespuestacorrecta = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RespuestaCorrecta")); //$NON-NLS-1$ //$NON-NLS-2$
		lblRespuestacorrecta.setBounds(60, 355, 259, 16);
		getContentPane().add(lblRespuestacorrecta);
		
//		createPronButton.setVisible(true);

//		tableQueries.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				int i = tableEvents.getSelectedRow();
//				int u = tableQueries.getSelectedRow();
//				domain.Event ev = (domain.Event) tableModelEvents.getValueAt(i, 2);
//				Vector<Question> queries = ev.getQuestions();
//				for (domain.Question q : queries) {
//
//					if(q.getQuestion().equals(tableQueries.getValueAt(u, 1))) {
//						a = q;
//						a.setQuestion(tableQueries.getValueAt(i, 1).toString());
//						
//						break;
//					}
//				}							
//
//			}
//		});

		createPronButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pronField.getText() == null) {
					errorLabel.setText("Introduzca una respuesta");
				} else {
					String prons = pronField.getText();
					Pronostico pronCorrecto = new Pronostico(ques, prons);
					pronCorrecto.setCorrecta(prons);
					System.out.println(prons);
					facade.storePronosticoVerdadero(pronCorrecto, ques, evento);
					
					errorLabel.setText("Pronostico creado con exito");
				}
			}
		});

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}