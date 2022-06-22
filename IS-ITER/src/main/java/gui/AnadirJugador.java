package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Equipo;
import domain.Jugador;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;

public class AnadirJugador extends JFrame {

	private JPanel contentPane;

	private String inputLocalTeam;

	private String[] columnNamesEquipo1 = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EquipoN"),
			ResourceBundle.getBundle("Etiquetas").getString("Equipo"), };
	private JTextField textFieldEquipo;
	private JTextField textFieldNombre;
	private JTextField textFieldApellidos;
	private JTextField textFieldDorsal;
	private JLabel jLabelE = new JLabel(); 


	public AnadirJugador() {

		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 619, 402);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		this.getContentPane().setLayout(null);

		JLabel jLabelEquipo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Equipo"));
		jLabelEquipo.setBounds(506, 16, 124, 20);
		contentPane.add(jLabelEquipo);

		JButton jButtonAñadirJugador = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AñadirJugador"));
		jButtonAñadirJugador.setBounds(288, 415, 115, 29);
		contentPane.add(jButtonAñadirJugador);

		JButton jButtonCerrar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		jButtonCerrar.setBounds(452, 415, 115, 29);
		contentPane.add(jButtonCerrar);

		textFieldEquipo = new JTextField();
//		textFieldEquipo.setText(ResourceBundle.getBundle("Etiquetas").getString("A\u00F1adirJugador.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		textFieldEquipo.setBounds(506, 61, 146, 26);
		contentPane.add(textFieldEquipo);
		textFieldEquipo.setColumns(10);

		JLabel jLabelNombre = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name"));
		jLabelNombre.setBounds(28, 16, 161, 20);
		contentPane.add(jLabelNombre);

		textFieldNombre = new JTextField();
//		textFieldNombre.setText(ResourceBundle.getBundle("Etiquetas").getString("A\u00F1adirJugador.textField.text")); 
		textFieldNombre.setBounds(28, 61, 146, 26);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);

		JLabel jLabelApellidos = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Apellidos")); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelApellidos.setBounds(28, 126, 146, 20);
		contentPane.add(jLabelApellidos);

		textFieldApellidos = new JTextField();
//		textFieldApellidos.setText(ResourceBundle.getBundle("Etiquetas").getString("A\u00F1adirJugador.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		textFieldApellidos.setBounds(28, 174, 146, 26);
		contentPane.add(textFieldApellidos);
		textFieldApellidos.setColumns(10);

		JLabel jLabelDorsal = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Dorsal")); //$NON-NLS-1$ //$NON-NLS-2$
		jLabelDorsal.setBounds(28, 238, 146, 20);
		contentPane.add(jLabelDorsal);

		textFieldDorsal = new JTextField();
//		textFieldDorsal.setText(ResourceBundle.getBundle("Etiquetas").getString("A\u00F1adirJugador.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		textFieldDorsal.setBounds(28, 285, 146, 26);
		contentPane.add(textFieldDorsal);
		textFieldDorsal.setColumns(10);
		jLabelE.setForeground(Color.RED);
		
		jLabelE.setBounds(356, 343, 102, 20);
		contentPane.add(jLabelE);
		this.setSize(new Dimension(889, 548));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("HacerApuesta"));
		jButtonAñadirJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

	}

	private void jButtonCreate_actionPerformed(ActionEvent e) {
//		Jugador j = new Jugador(textFieldNombre.getText(), textFieldEquipo.getText(), textFieldApellidos.getText(),
//				textFieldDorsal.getCaretPosition());
		jLabelE.setText("");
		BLFacade facade = MainGUI.getBusinessLogic();
		Equipo eq = facade.getEq(textFieldEquipo.getText());
		facade.createJugador(textFieldNombre.getText(), textFieldEquipo.getText(), textFieldApellidos.getText(),
				textFieldDorsal.getText(), eq.getEventNumber());
		jLabelE.setText(ResourceBundle.getBundle("Etiquetas").getString("Añadido"));
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
