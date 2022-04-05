package gui;

import java.awt.BorderLayout;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.Event;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JRadioButton;

public class MainLoginRegister extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel JContentPane = null;
	private JButton JButtonRegistrarse = null;
	private JButton JButtonHacerLogin = null;

	private static BLFacade FacadeInterface;

	public static BLFacade getBusinessLogic() {
		return FacadeInterface;
	}

	public static void setBussinessLogic(BLFacade afi) {
		FacadeInterface = afi;
	}

	protected JLabel JLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel Jpanel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton JButtonApuestas = null;

	/**
	 * This is the default constructor
	 */
	public MainLoginRegister() {
		super();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					// if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println(
							"Error: " + e1.toString() + " , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (JContentPane == null) {
			JContentPane = new JPanel();
			JContentPane.setLayout(null);
			JContentPane.add(getLblNewLabel());
			JContentPane.add(getBoton3());
			JContentPane.add(getPanel());
			JContentPane.add(getBoton2());
			JContentPane.add(getJButtonApuestas());
		}
		return JContentPane;
	}

	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (JButtonRegistrarse == null) {
			JButtonRegistrarse = new JButton();
			JButtonRegistrarse.setBounds(0, 116, 270, 58);
			JButtonRegistrarse.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
			JButtonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new RegistrarseGUI();
					a.setVisible(true);
				}
			});
		}
		return JButtonRegistrarse;
	}

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (JButtonHacerLogin == null) {
			JButtonHacerLogin = new JButton();
			JButtonHacerLogin.setBounds(0, 59, 270, 58);
			JButtonHacerLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
			JButtonHacerLogin.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new HacerLogin();

					a.setVisible(true);
				}
			});
		}
		return JButtonHacerLogin;
	}

	private JButton getJButtonApuestas() {
		if (JButtonApuestas == null) {
			JButtonApuestas = new JButton();
			JButtonApuestas.setBounds(267, 59, 217, 115);
			JButtonApuestas.setText(ResourceBundle.getBundle("Etiquetas").getString("ApuestasDisponibles"));
			JButtonApuestas.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionsGUI();
					a.setVisible(true);
				}
			});
		}
		return JButtonApuestas;
	}

	private JLabel getLblNewLabel() {
		if (JLabelSelectOption == null) {
			JLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			JLabelSelectOption.setBounds(0, 1, 473, 58);
			JLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			JLabelSelectOption.setForeground(Color.BLACK);
			JLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return JLabelSelectOption;
	}

	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}

	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}

	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: " + Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}

	private JPanel getPanel() {
		if (Jpanel == null) {
			Jpanel = new JPanel();
			Jpanel.setBounds(0, 176, 473, 58);
			Jpanel.add(getRdbtnNewRadioButton_1());
			Jpanel.add(getRdbtnNewRadioButton_2());
			Jpanel.add(getRdbtnNewRadioButton());
		}
		return Jpanel;
	}

	private void redibujar() {
		JLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		JButtonHacerLogin.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		JButtonRegistrarse.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		JButtonApuestas.setText(ResourceBundle.getBundle("Etiquetas").getString("ApuestasDisponibles"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

//	private void visibleFalse(ActionEvent e) {
//		this.setVisible(false);
//	}

}
