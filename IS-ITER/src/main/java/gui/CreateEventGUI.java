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
import domain.Event;
import domain.Question;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Dimension;

public class CreateEventGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEquipo1 = new JScrollPane();
	private JScrollPane scrollPaneEquipo2 = new JScrollPane();
	private JTable tableEquipo1 = new JTable();
	private JTable tableEquipo2 = new JTable();

	private DefaultTableModel tableModelEquipo1;
	private DefaultTableModel tableModelEquipo2;

	private String inputLocalTeam;
	private String inputVisitingTeam;

	private JButton jButtonCreate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent")); //$NON-NLS-1$ //$NON-NLS-2$
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();

	private String[] columnNamesEquipo1 = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EquipoN"),
			ResourceBundle.getBundle("Etiquetas").getString("Equipo"), };

	private String[] columnNamesEquipo2 = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EquipoN"),
			ResourceBundle.getBundle("Etiquetas").getString("Equipo"), };

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	public CreateEventGUI(Vector<domain.Equipo> v) {

		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Equipo> v) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(813, 493));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));

		jButtonCreate.setBounds(new Rectangle(94, 334, 130, 30));

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		
		jButtonClose.setBounds(new Rectangle(258, 334, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(25, 240, 256, 20));
		jLabelMsg.setForeground(Color.red);

		jLabelError.setBounds(new Rectangle(15, 216, 277, 20));
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

		scrollPaneEquipo1.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneEquipo1.setBounds(303, 50, 207, 210);
		getContentPane().add(scrollPaneEquipo1);

		JLabel jLabelEquipo1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption")); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelEquipo1.setBounds(303, 18, 165, 20);
		getContentPane().add(jLabelEquipo1);

		// Code for JCalendar
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
						tableModelEquipo1.setDataVector(null, columnNamesEquipo1);
						tableModelEquipo1.setColumnCount(3); // another column added to allocate ev objects

						tableModelEquipo2.setDataVector(null, columnNamesEquipo2);
						tableModelEquipo2.setColumnCount(3); // another column added to allocate ev objects
					
						

						BLFacade facade = MainGUI.getBusinessLogic();
						Vector<Equipo> equipos = facade.getEquipo(firstDay);

						for (Equipo eq : equipos) {
							Vector<Object> row = new Vector<Object>();
							System.out.println("Equipo: " + eq.getDescription());

							row.add(eq.getEventNumber());
							row.add(eq.getDescription());
							row.add(eq); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEquipo1.addRow(row);
							tableModelEquipo2.addRow(row);

						}
						tableEquipo1.getColumnModel().getColumn(0).setPreferredWidth(60);
						tableEquipo1.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEquipo1.getColumnModel().removeColumn(tableEquipo1.getColumnModel().getColumn(2));

						tableEquipo2.getColumnModel().getColumn(0).setPreferredWidth(60);
						tableEquipo2.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEquipo2.getColumnModel().removeColumn(tableEquipo2.getColumnModel().getColumn(2)); 
					
					} catch (Exception e1) {
						System.out.println("no funtziona");
					}

				}
			}
		});

		this.getContentPane().add(jCalendar, null);

		tableEquipo1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEquipo1.getSelectedRow();
				domain.Equipo eq = (domain.Equipo) tableModelEquipo1.getValueAt(i, 2);// obtain ev object
				inputLocalTeam = eq.getDescription();
			}
		});

		tableEquipo2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = tableEquipo2.getSelectedRow();
				domain.Equipo eq1 = (domain.Equipo) tableModelEquipo2.getValueAt(i, 2);// obtain ev object
				inputVisitingTeam = eq1.getDescription();
			}
		});

		scrollPaneEquipo1.setViewportView(tableEquipo1);
		tableModelEquipo1 = new DefaultTableModel(null, columnNamesEquipo1);

		scrollPaneEquipo1.setViewportView(tableEquipo1);
		this.getContentPane().add(scrollPaneEquipo1, null);
		tableEquipo1.setModel(tableModelEquipo1);

		scrollPaneEquipo2.setViewportView(tableEquipo2);
		tableModelEquipo2 = new DefaultTableModel(null, columnNamesEquipo2);

		scrollPaneEquipo2.setViewportView(tableEquipo2);
		this.getContentPane().add(scrollPaneEquipo2, null);
		tableEquipo2.setModel(tableModelEquipo2);

		scrollPaneEquipo2.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneEquipo2.setBounds(555, 50, 207, 210);
		getContentPane().add(scrollPaneEquipo2);

		JLabel jLabelEquipo2 = new JLabel("SelectOption");
		jLabelEquipo2.setBounds(555, 18, 165, 20);
		getContentPane().add(jLabelEquipo2);

		tableEquipo1.setModel(tableModelEquipo1);
		tableEquipo1.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableEquipo1.getColumnModel().getColumn(1).setPreferredWidth(268);

		tableEquipo2.setModel(tableModelEquipo2);
		tableEquipo2.getColumnModel().getColumn(0).setPreferredWidth(60);
		tableEquipo2.getColumnModel().getColumn(1).setPreferredWidth(268);

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

	private void jButtonCreate_actionPerformed(ActionEvent e) {
		// domain.Event event = ((domain.Event) jComboBoxEvents.getSelectedItem());

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			// Displays an exception if the query field is empty

			String inputDescription = inputLocalTeam + "-" + inputVisitingTeam;

			Date eventDate = jCalendar.getDate();

			int eventNum;

			if (!inputLocalTeam.equals("")) {

				// It could be to trigger an exception if the introduced string is not a number

				if (inputVisitingTeam.equals(""))
					jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventV"));
				else {

					// Obtain the business logic from a StartWindow class (local or remote)
					// BLFacade facade0 = MainGUI.getBusinessLogic();
					BLFacade facade = MainGUI.getBusinessLogic();

					if (facade.getEvents(eventDate) == null) {
						eventNum = 1;
					} else {
						eventNum = facade.getLastEventNumber(eventDate) + 1;
					}

					facade.createEvent(inputDescription, eventDate);

					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryCreated"));
				}
			} else
				jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventL"));
		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
