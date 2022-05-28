package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;

public class Profile extends JFrame {
	BLFacade facade = MainGUI.getBusinessLogic();

	private JPanel contentPane;

	public Profile() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	private void jbInit() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 351);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton jButtonClose = new JButton();
		jButtonClose.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		jButtonClose.setBounds(186, 250, 115, 29);
		contentPane.add(jButtonClose);

		JLabel jLabelNombre = new JLabel();
		jLabelNombre.setText(ResourceBundle.getBundle("Etiquetas").getString("Nombre"));
		jLabelNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jLabelNombre.setBounds(15, 27, 87, 20);
		contentPane.add(jLabelNombre);

		JLabel jLabelApellidos = new JLabel();
		jLabelApellidos.setText(ResourceBundle.getBundle("Etiquetas").getString("Apellidos"));
		jLabelApellidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jLabelApellidos.setBounds(15, 63, 106, 20);
		contentPane.add(jLabelApellidos);

		JLabel jLabelUser = new JLabel();
		jLabelUser.setText(ResourceBundle.getBundle("Etiquetas").getString("Usuario"));
		jLabelUser.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jLabelUser.setBounds(15, 103, 87, 20);
		contentPane.add(jLabelUser);

		JLabel jLabelDNI = new JLabel();
		jLabelDNI.setText(ResourceBundle.getBundle("Etiquetas").getString("DNI"));
		jLabelDNI.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jLabelDNI.setBounds(15, 139, 75, 20);
		contentPane.add(jLabelDNI);

		JLabel jLabelWallet = new JLabel();
		jLabelWallet.setText(ResourceBundle.getBundle("Etiquetas").getString("Wallet"));
		jLabelWallet.setFont(new Font("Tahoma", Font.PLAIN, 20));
		jLabelWallet.setBounds(15, 175, 87, 20);
		contentPane.add(jLabelWallet);

		User u = facade.getLog();

		JLabel jLabelNom = new JLabel();
		jLabelNom.setText(u.getNombre());
		jLabelNom.setBounds(168, 29, 115, 20);
		contentPane.add(jLabelNom);

		JLabel jLabelAp = new JLabel();
		jLabelAp.setText(u.getApellidos());
		jLabelAp.setBounds(168, 65, 164, 20);
		contentPane.add(jLabelAp);

		JLabel jLabelUs = new JLabel();
		jLabelUs.setText(u.getUser());
		jLabelUs.setBounds(168, 105, 133, 20);
		contentPane.add(jLabelUs);

		JLabel jLabeldni = new JLabel();
		jLabeldni.setText(u.getDNI());
		jLabeldni.setBounds(168, 141, 133, 20);
		contentPane.add(jLabeldni);

		JLabel jLabelcartera = new JLabel();
		jLabelcartera.setText(String.valueOf(u.getWallet()));
		jLabelcartera.setBounds(168, 177, 145, 20);
		contentPane.add(jLabelcartera);

		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButton2_actionPerformed(e);
			}
		});
	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
