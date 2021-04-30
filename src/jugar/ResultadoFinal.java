package jugar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesprincipales.Equipo;
import opciones.Opciones;


import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.SwingConstants;
import java.awt.Toolkit;

@SuppressWarnings("serial")
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/icono/icono.png"));
		setSize(700,550);
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
		
		JLabel lblNewLabel_1 = new JLabel("GANADOR");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 37));
		lblNewLabel_1.setBounds(10, 96, 676, 60);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("INICIO");
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(261, 449, 170, 54);
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
		escudoganador.setBounds(10, 170, 676, 260);
		contentPane.add(escudoganador);
		
		JLabel localLBL = new JLabel(nombreLocal);
		localLBL.setHorizontalAlignment(SwingConstants.CENTER);
		localLBL.setFont(new Font("Tahoma", Font.BOLD, 25));
		localLBL.setBounds(10, 10, 280, 49);
		contentPane.add(localLBL);
		
		JLabel resultadoLBL = new JLabel(golesLocal+"-"+golesVisitante);
		resultadoLBL.setHorizontalAlignment(SwingConstants.CENTER);
		resultadoLBL.setFont(new Font("Tahoma", Font.BOLD, 25));
		resultadoLBL.setBounds(300, 10, 100, 49);
		contentPane.add(resultadoLBL);
		
		JLabel visitanteLBL = new JLabel(nombreVisitante);
		visitanteLBL.setHorizontalAlignment(SwingConstants.CENTER);
		visitanteLBL.setFont(new Font("Tahoma", Font.BOLD, 25));
		visitanteLBL.setBounds(410, 10, 266, 49);
		contentPane.add(visitanteLBL);
		
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
