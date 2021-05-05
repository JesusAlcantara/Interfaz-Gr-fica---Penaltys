package inicio;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import opciones.Opciones;
import verequipo.plantillaEquipo;

public class Inicio extends JFrame {

	private JPanel contentPane, panel, panel_1, panel_2;
	private JButton btnJugar, btnverEquipo, btnClasifisicacion;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Inicio() {
		super("Inicio");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/icono/icono.png"));
		setBounds(100, 100, 450, 300);
		setSize(300,300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3,1));
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
		
		btnJugar = new JButton("Jugar");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				setVisible(false);
				Opciones o = new Opciones();
				o.setVisible(true);
				dispose();
			}
		});
		panel.add(btnJugar);
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		contentPane.add(panel_1);
		
		btnverEquipo = new JButton("Ver Equipo");
		btnverEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				setVisible(false);
				plantillaEquipo p = new plantillaEquipo();
				p.setVisible(true);
				dispose();
			}
		});
		panel_1.add(btnverEquipo);
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.WHITE);
		contentPane.add(panel_2);
		
		btnClasifisicacion = new JButton("Clasificación");
		panel_2.add(btnClasifisicacion);
	}

}
