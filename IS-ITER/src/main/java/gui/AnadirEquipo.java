package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Equipo;
import domain.Jugador;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AnadirEquipo extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldEq = new JTextField();;
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;
	private JLabel jLabelE = new JLabel();

	/**
	 * Create the frame.
	 */
	public AnadirEquipo(Vector<domain.Jugador> v) {

		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void jbInit(Vector<domain.Jugador> v) throws Exception {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(519, 342));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		jCalendar.setBounds(15, 16, 225, 150);

		this.getContentPane().add(jCalendar, null);

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar.getDate());
		paintDaysWithEvents(jCalendar, datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {

				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));

					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);

					if (monthAct != monthAnt) {
						if (monthAct == monthAnt + 2) {
							calendarAct.set(Calendar.MONTH, monthAnt + 1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}

						jCalendar.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar.getDate());
					}

					CreateQuestionGUI.paintDaysWithEvents(jCalendar, datesWithEventsCurrentMonth);

				}
			}
		});

		this.getContentPane().add(jCalendar, null);

		JLabel jLabelEquipo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Introducenombreequipo"));
		jLabelEquipo.setBounds(255, 16, 173, 20);
		contentPane.add(jLabelEquipo);
		textFieldEq.setBounds(255, 52, 146, 26);
		contentPane.add(textFieldEq);
		textFieldEq.setColumns(10);

		JButton jButtoncrear = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Añadir"));
		jButtoncrear.setBounds(138, 199, 115, 29);
		contentPane.add(jButtoncrear);

		jLabelE.setForeground(Color.RED);
		jLabelE.setBounds(255, 146, 69, 20);
		contentPane.add(jLabelE);

		JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		jButtonClose.setBounds(15, 199, 115, 29);
		contentPane.add(jButtonClose);
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		jButtoncrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
	}

	public static void paintDaysWithEvents(JCalendar jCalendar, Vector<Date> datesWithEventsCurrentMonth) {
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
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, today);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

	}

	private void jButtonCreate_actionPerformed(ActionEvent e) {
		jLabelE.setText("");
		BLFacade facade = MainGUI.getBusinessLogic();
		String equipo = textFieldEq.getText();
		Date eventDate = jCalendar.getDate();
		facade.createEquipo(equipo, eventDate);
		jLabelE.setText(ResourceBundle.getBundle("Etiquetas").getString("Añadido"));
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
