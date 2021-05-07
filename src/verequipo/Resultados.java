package verequipo;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

@SuppressWarnings("serial")
public class Resultados extends JFrame {

	private JPanel contentPane;
	private JLabel lblEquipo, lblEscudo, lblResultado, lblResultado1, lblResultado2;
	
	public Resultados() {
		super("Resultados");
		setSize(500,400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblEquipo = new JLabel("Equipo");
		lblEquipo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEquipo.setBounds(177, 26, 74, 28);
		contentPane.add(lblEquipo);
		
		
		lblEscudo = new JLabel("New label");
		lblEscudo.setBounds(142, 65, 124, 112);
		contentPane.add(lblEscudo);
		
		lblResultado = new JLabel("New label");
		lblResultado.setBounds(187, 188, 49, 14);
		contentPane.add(lblResultado);
		
		lblResultado1 = new JLabel("New label");
		lblResultado1.setBounds(187, 213, 49, 14);
		contentPane.add(lblResultado1);
		
		lblResultado2 = new JLabel("New label");
		lblResultado2.setBounds(188, 238, 49, 14);
		contentPane.add(lblResultado2);
		
		setVisible(true);
	}
	//metodo establecer foto equipo
	public void establecerImagen() {
		ImageIcon imagen = new ImageIcon();
		Image imagen2 = imagen.getImage();
		Image imagen3 = imagen2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		imagen = new ImageIcon(imagen3);
		lblEscudo.setIcon(imagen);
	}
}
