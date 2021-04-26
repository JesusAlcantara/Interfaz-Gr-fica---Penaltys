package jugar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesprincipales.Equipo;
import opciones.Opciones;


import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.SwingConstants;

public class ResultadoFinal extends JFrame {
	private int golesLocal;
	private int golesVisitante;
	private String nombreLocal;
	private String nombreVisitante;
	private Equipo eqLocal;
	private Equipo eqVisitante;
	JLabel escudoganador;
	private JPanel contentPane;

	public ResultadoFinal() {
		super("Resultado");
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		eqLocal=Jugar.eqLocal;
		eqVisitante=Jugar.eqVisitante;
		nombreLocal=Jugar.eqLocal.getNombre();
		nombreVisitante=Jugar.eqVisitante.getNombre();
		golesLocal=Jugar.golesLocal;
		golesVisitante=Jugar.golesVisitante;
		
		JLabel lblNewLabel = new JLabel(nombreLocal+" "+golesLocal+ " - " +golesVisitante+" "+nombreVisitante);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblNewLabel.setBounds(10, 25, 764, 60);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("GANADOR");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblNewLabel_1.setBounds(10, 96, 764, 60);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("INICIO");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(316, 496, 170, 54);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Opciones o=new Opciones();
				o.setVisible(true);
				dispose();
				
			}
			
		});
		contentPane.add(btnNewButton);
		
		escudoganador = new JLabel();
		escudoganador.setHorizontalAlignment(SwingConstants.CENTER);
		escudos();
		escudoganador.setBounds(10, 170, 764, 300);
		contentPane.add(escudoganador);
		
	}
	private void escudos() {
		if(golesLocal>golesVisitante) {
			escudoganador.setIcon(new ImageIcon(eqLocal.getFoto()));
		}
		else if(golesLocal<golesVisitante) {
			escudoganador.setIcon(new ImageIcon(eqVisitante.getFoto()));
		}
	}
}
