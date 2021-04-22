package opciones;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jugar.Jugar;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComboBox;
import java.awt.Font;

public class Opciones extends JFrame {

	private JPanel contentPane;
	private JTextField numLanzamientostxt;
	private JButton btnNewButton;
	private JComboBox seleccionLocal;
	public static String nombreLocal;
	public static int numLanzamientos;
	private JLabel lanzamientos;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Opciones o=new Opciones();
	}

	/**
	 * Create the frame.
	 */
	public Opciones() {
		super("Opciones");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 550, 400);
		setSize(440,310);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		nombreLocal="Real Madrid";
		String [] equipos= {"Real Madrid","FC Barcelona","Atletico de Madrid","Sevilla FC","Cadiz CF"};
		seleccionLocal = new JComboBox(equipos);
		seleccionLocal.setSelectedItem("Real Madrid");
		seleccionLocal.setBounds(230, 40, 180, 40);
		seleccionLocal.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				nombreLocal=(String) e.getItem();
			}
			
		});
		contentPane.add(seleccionLocal);
		
		numLanzamientostxt = new JTextField();
		numLanzamientostxt.setBounds(230, 110, 180, 40);
		numLanzamientostxt.addActionListener(new botones());
		contentPane.add(numLanzamientostxt);
		numLanzamientostxt.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Equipo Local");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(35, 40, 140, 40);
		contentPane.add(lblNewLabel);
		
		lanzamientos = new JLabel("Lanzamientos");
		lanzamientos.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lanzamientos.setBounds(35, 110, 140, 40);
		contentPane.add(lanzamientos);
		
		btnNewButton = new JButton("Siguiente");
		btnNewButton.setBounds(120, 220, 180, 40);
		btnNewButton.addActionListener(new botones());
		contentPane.add(btnNewButton);
		setVisible(true);
	}
	private class botones implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			numLanzamientos=Integer.parseInt(numLanzamientostxt.getText());
			Jugar j=new Jugar();
			j.setVisible(true);
			dispose();
			//setVisible(false);
	
			
			
			
		}
		
	}
}

