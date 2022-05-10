package gui;

import java.awt.BorderLayout;
import java.util.regex.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Pronostico;
import domain.User;
import exceptions.UserAlreadyExists;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;

public class RegistrarseGUI extends JFrame {

	private JPanel contentPane;
	private JTextField userField;
	private JPasswordField passwordField;
	private JTextField emailField;
	private JTextField dniField;
	BLFacade facade = MainGUI.getBusinessLogic();
	private JPasswordField passwordField2;
	private JTextField apellidosField;
	private JTextField nombreField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					RegistrarseGUI frame = new RegistrarseGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public RegistrarseGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 513, 486);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel userLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Usuario"));
		userLabel.setBounds(10, 57, 80, 24);
		contentPane.add(userLabel);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Contrase\u00F1a"));
		lblNewLabel.setBounds(10, 191, 105, 24);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Email"));
		lblNewLabel_1.setBounds(17, 269, 84, 24);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DNI"));
		lblNewLabel_2.setBounds(17, 316, 98, 13);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Fechadenacimiento"));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setBounds(17, 343, 98, 32);
		contentPane.add(lblNewLabel_3);

		userField = new JTextField();
		userField.setBounds(153, 60, 268, 19);
		contentPane.add(userField);
		userField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(153, 194, 268, 19);
		contentPane.add(passwordField);

		emailField = new JTextField();
		emailField.setBounds(153, 272, 268, 19);
		contentPane.add(emailField);
		emailField.setColumns(10);

		dniField = new JTextField();
		dniField.setBounds(153, 313, 268, 19);
		contentPane.add(dniField);
		dniField.setColumns(10);

		JButton atrasButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ATRAS"));
		atrasButton.setBounds(103, 399, 105, 21);
		contentPane.add(atrasButton);
		atrasButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});

		JButton registrarseButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("REGISTRARSE"));
		JLabel errorLabel = new JLabel("");
		errorLabel.setBounds(130, 376, 227, 13);
		contentPane.add(errorLabel);
		JLabel lblNewLabel_7 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Repitacontrase\u00F1a"));
		lblNewLabel_7.setBounds(10, 235, 137, 13);
		contentPane.add(lblNewLabel_7);

		passwordField2 = new JPasswordField();
		passwordField2.setBounds(153, 232, 268, 19);
		contentPane.add(passwordField2);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(153, 347, 268, 19);
		contentPane.add(dateChooser);

		JLabel lblNewLabel_4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Apellidos"));
		lblNewLabel_4.setBounds(10, 151, 91, 13);
		contentPane.add(lblNewLabel_4);

		apellidosField = new JTextField();
		apellidosField.setBounds(153, 148, 269, 19);
		contentPane.add(apellidosField);
		apellidosField.setColumns(10);
		nombreField = new JTextField();
		nombreField.setBounds(153, 107, 268, 19);
		contentPane.add(nombreField);
		nombreField.setColumns(10);

		JLabel nombre = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Nombre"));
		nombre.setBounds(10, 109, 80, 13);
		contentPane.add(nombre);

		registrarseButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {

				if (userField.getText() == null || passwordField.getPassword() == null || emailField.getText() == null
						|| dniField.getText() == null || dateChooser.getDate() == null) {
					errorLabel
							.setText(ResourceBundle.getBundle("Etiquetas").getString("Porfavorcompletetodosloscampos"));
				} else {
					String nombre = nombreField.getText();
					String apellidos = apellidosField.getText();
					String user = userField.getText();
					String password = String.valueOf(passwordField.getPassword());
					String password2 = String.valueOf(passwordField2.getPassword());
					String email = emailField.getText();
					String DNI = dniField.getText();
					Date fecha = dateChooser.getDate();
					LocalDate birthDate = LocalDate.of(fecha.getYear(), fecha.getMonth(), fecha.getDay());
					Date currentDate = Date.from(Instant.now());
					LocalDate currentDate2 = LocalDate.of(currentDate.getYear(), currentDate.getMonth(),
							currentDate.getDay());
					Integer años = calculateAge(birthDate, currentDate2);
					if (años > 18) {
						if (validar(DNI)) {
							if (password.compareTo(password2) == 0) {
								if (emailVerify(email)) {
									if (!facade.getUser(user)) {
//										ArrayList<Pronostico> pron = null;

										User user_new = new User(user, password, DNI, fecha, email, nombre, apellidos,
											 false);
										user_new.setNombre(nombre);
										user_new.setApellidos(apellidos);
										user_new.setUser(user);
										user_new.setDNI(DNI);
										
//										User u = new User(user,password);
										try {
											facade.storeUser(user_new);
											//facade.storeUser(u);
											errorLabel.setText(ResourceBundle.getBundle("Etiquetas")
													.getString("Registradoconexito"));
											try {
												HacerLogin hacerLogin = new HacerLogin();

												hacerLogin.setVisible(true);

											} catch (Exception ex) {
												ex.printStackTrace();
											}
										} catch (UserAlreadyExists e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();
										}
									} else {
										errorLabel.setText(ResourceBundle.getBundle("Etiquetas")
												.getString("Elnombredeusuarioyaexiste"));
									}

								} else {
									errorLabel.setText(
											ResourceBundle.getBundle("Etiquetas").getString("Elemailnoesvalido"));
								}
							} else {
								errorLabel.setText(
										ResourceBundle.getBundle("Etiquetas").getString("Lascontraseñasnocoinciden"));
							}
						} else {
							errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("DNInovalido"));
						}
					} else {
						errorLabel.setText(
								ResourceBundle.getBundle("Etiquetas").getString("Lafechadenacimientonoesválida"));
					}

				}
			}
		});
		registrarseButton.setBounds(245, 399, 137, 21);
		contentPane.add(registrarseButton);
	}

	public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
		if ((birthDate != null) && (currentDate != null)) {
			return Period.between(birthDate, currentDate).getYears();
		} else {
			return 0;
		}
	}

	public static boolean emailVerify(String email) {
		boolean result = true;
		try {
			InternetAddress emailAddr = new InternetAddress(email);
			emailAddr.validate();
		} catch (AddressException ex) {
			result = false;
		}
		return result;
	}

	public boolean validar(String dni) {

		String letraMayuscula = "";

		if (dni.length() != 9 || Character.isLetter(dni.charAt(8)) == false) {
			return false;
		}
		letraMayuscula = (dni.substring(8)).toUpperCase();
		if (soloNumeros(dni) == true && letraDNI(dni).equals(letraMayuscula)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean soloNumeros(String dnia) {

		String numero = "";
		String miDNI = "";
		String[] unoNueve = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

		for (int i = 0; i < dnia.length() - 1; i++) {
			numero = dnia.substring(i, i + 1);
			for (int j = 0; j < unoNueve.length; j++) {
				if (numero.equals(unoNueve[j])) {
					miDNI += unoNueve[j];
				}
			}

		}
		if (miDNI.length() != 8) {
			return false;
		} else {
			return true;
		}
	}

	private String letraDNI(String dnia) {
		int miDNI = Integer.parseInt(dnia.substring(0, 8));
		int resto = 0;
		String miLetra = "";
		String[] asignacionLetra = { "T", "R", "W", "A", "G", "M", "Y", "F", "P", "D", "X", "B", "N", "J", "Z", "S",
				"Q", "V", "H", "L", "C", "K", "E" };

		resto = miDNI % 23;
		miLetra = asignacionLetra[resto];
		return miLetra;

	}
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}