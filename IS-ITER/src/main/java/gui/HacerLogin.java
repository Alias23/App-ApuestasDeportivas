package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class HacerLogin extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField userField;
	BLFacade facade = MainGUI.getBusinessLogic();
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HacerLogin frame = new HacerLogin();
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
	public HacerLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("HazLogin"));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(151, 16, 132, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Nombredeusuario"));
		lblNewLabel_1.setBounds(29, 62, 149, 20);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Contrase\u00F1a"));
		lblNewLabel_2.setBounds(29, 126, 103, 20);
		contentPane.add(lblNewLabel_2);

		userField = new JTextField();
		userField.setBounds(215, 61, 176, 26);
		contentPane.add(userField);
		userField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(215, 127, 176, 29);
		contentPane.add(passwordField);
		JLabel errorLabel = new JLabel("");
		errorLabel.setBounds(79, 186, 270, 13);
		contentPane.add(errorLabel);

		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("HacerLogin"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (String.valueOf(passwordField.getPassword()) == null) {
					errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Introduzcacontraseña"));
				} else {
					if (userField.getText() == null) {
						errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Introduzcausuario"));
					} else {
						String password = String.valueOf(passwordField.getPassword());
						String user = userField.getText();
						if (facade.getUserLogin(user, password) != null) {
							errorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Logeadoconexito"));
							if ((facade.getUserLogin(user, password).getAdmin(user, password)) == false) {
								HacerPronosticoGUI a = new HacerPronosticoGUI();
								a.setVisible(true);
								VisibleFalse(e);
							} else {

								MainGUI m = new MainGUI();
								m.setVisible(true);
							}
						} else {
							errorLabel.setText(
									ResourceBundle.getBundle("Etiquetas").getString("Usuarioocontraseñainvalidos"));
						}
					}
				}
			}
		});
		btnNewButton.setBounds(151, 199, 132, 29);
		contentPane.add(btnNewButton);

		JButton atrasButton = new JButton("ATRAS");
		atrasButton.setBounds(15, 199, 121, 29);
		contentPane.add(atrasButton);
		atrasButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VisibleFalse(e);
			}
		});
	}

	private void VisibleFalse(ActionEvent e) {
		this.setVisible(false);
	}
}