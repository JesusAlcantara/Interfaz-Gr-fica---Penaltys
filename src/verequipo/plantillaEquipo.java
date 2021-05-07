package verequipo;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesprincipales.Componente;
import clasesprincipales.Equipo;
import jugar.Jugar;

public class plantillaEquipo extends JFrame {

	private JPanel contentPane;
	private JButton btnMadrid, btnBarcelona, btnCadiz, btnAtletico, btnSevilla;
	private Icon iconMadrid, iconBarcelona, iconCadiz, iconAtletico, iconSevilla;
	static Equipo realMadrid, barcelona, sevilla, atletico, cadiz;
	static Equipo equipo;
	static String[][] jugadoresGlobal = new String[12][4];
	static String[] fotos = new String[12];
	static int contadorGlobal = 0;
	Image ojeador = new ImageIcon("resources/plantilla/ojeador.jpg").getImage();
	ImageIcon ojeadorModificado = new ImageIcon(ojeador.getScaledInstance(150, 150, ojeador.SCALE_SMOOTH));
	Image estadio;
	ImageIcon estadioModificado;
	ImageIcon escudoModificado;

	public plantillaEquipo() {
		super("Ver Equipo");
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/icono/icono.png"));
		JOptionPane.showMessageDialog(plantillaEquipo.this, "Elija el equipo que dese ver.", "Plantilla",
				JOptionPane.PLAIN_MESSAGE, ojeadorModificado);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1250, 500);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));

		iconMadrid = new ImageIcon("resources/escudos/realmadridJugar.png");
		btnMadrid = new JButton();
		btnMadrid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Jugar.escribirOLeer("Real Madrid");
				verEquipo verMadrid = new verEquipo();
				try {
					realMadrid = Jugar.deserializar(new File("resources/Real Madrid"));
					equipo = realMadrid;
					Componente[] jugadores = new Componente[12];
					String[] lineas = new String[12];
					for (int i = 0; i < jugadores.length; i++) {
						jugadores[i] = realMadrid.getPlantilla().get(i);
						fotos[i] = realMadrid.getPlantilla().get(i).getFoto();
						lineas[i] = String.valueOf(jugadores[i]);
					}
					String[][] jugMadrid = returnInformacion(lineas);
					jugadoresGlobal = jugMadrid;
					verMadrid.rellenarFormulario(contadorGlobal);
					estadio = new ImageIcon("resources/estadios/bernabeu.jpg").getImage();
					estadioModificado = new ImageIcon(estadio.getScaledInstance(200, 200, estadio.SCALE_SMOOTH));
					verMadrid.lblEstadio.setIcon(estadioModificado);
					verMadrid.lblEscudo.setIcon(iconMadrid);
					verMadrid.ponerImagen(fotos[contadorGlobal]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				verMadrid.setVisible(true);
				dispose();
			}
		});
		btnMadrid.setIcon(iconMadrid);
		contentPane.add(btnMadrid);

		iconBarcelona = new ImageIcon("resources/escudos/barcelonaJugar.png");
		btnBarcelona = new JButton();
		btnBarcelona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Jugar.escribirOLeer("FC Barcelona");
				verEquipo verBarcelona = new verEquipo();
				try {
					barcelona = Jugar.deserializar(new File("resources/FC Barcelona"));
					equipo = barcelona;
					Componente[] jugadores = new Componente[12];
					String[] lineas = new String[12];
					for (int i = 0; i < jugadores.length; i++) {
						jugadores[i] = barcelona.getPlantilla().get(i);
						fotos[i] = barcelona.getPlantilla().get(i).getFoto();
						lineas[i] = String.valueOf(jugadores[i]);
					}
					String[][] jugBarcelona = returnInformacion(lineas);
					jugadoresGlobal = jugBarcelona;
					verBarcelona.rellenarFormulario(contadorGlobal);
					estadio = new ImageIcon("resources/estadios/campnou.jpg").getImage();
					estadioModificado = new ImageIcon(estadio.getScaledInstance(200, 200, estadio.SCALE_SMOOTH));
					verBarcelona.lblEstadio.setIcon(estadioModificado);
					verBarcelona.lblEscudo.setIcon(iconBarcelona);
					verBarcelona.ponerImagen(fotos[contadorGlobal]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				verBarcelona.setVisible(true);
				dispose();
			}
		});
		btnBarcelona.setIcon(iconBarcelona);
		contentPane.add(btnBarcelona);

		iconCadiz = new ImageIcon("resources/escudos/cadizJugar.png");
		btnCadiz = new JButton();
		btnCadiz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Jugar.escribirOLeer("Cadiz CF");
				verEquipo verCadiz = new verEquipo();
				try {
					cadiz = Jugar.deserializar(new File("resources/Cadiz CF"));
					equipo = cadiz;
					Componente[] jugadores = new Componente[12];
					String[] lineas = new String[12];
					for (int i = 0; i < jugadores.length; i++) {
						jugadores[i] = cadiz.getPlantilla().get(i);
						fotos[i] = cadiz.getPlantilla().get(i).getFoto();
						lineas[i] = String.valueOf(jugadores[i]);
					}
					String[][] jugCadiz = returnInformacion(lineas);
					jugadoresGlobal = jugCadiz;
					verCadiz.rellenarFormulario(contadorGlobal);
					estadio = new ImageIcon("resources/estadios/carranza.jpg").getImage();
					estadioModificado = new ImageIcon(estadio.getScaledInstance(200, 200, estadio.SCALE_SMOOTH));
					verCadiz.lblEstadio.setIcon(estadioModificado);
					verCadiz.lblEscudo.setIcon(iconCadiz);
					verCadiz.ponerImagen(fotos[contadorGlobal]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				verCadiz.setVisible(true);
				dispose();
			}
		});
		btnCadiz.setIcon(iconCadiz);
		contentPane.add(btnCadiz);

		iconSevilla = new ImageIcon("resources/escudos/sevillaJugar.png");
		btnSevilla = new JButton();
		btnSevilla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Jugar.escribirOLeer("Sevilla FC");
				verEquipo verSevilla = new verEquipo();
				try {
					sevilla = Jugar.deserializar(new File("resources/Sevilla FC"));
					equipo = sevilla;
					Componente[] jugadores = new Componente[12];
					String[] lineas = new String[12];
					for (int i = 0; i < jugadores.length; i++) {
						jugadores[i] = sevilla.getPlantilla().get(i);
						fotos[i] = sevilla.getPlantilla().get(i).getFoto();
						lineas[i] = String.valueOf(jugadores[i]);
					}
					String[][] jugSevilla = returnInformacion(lineas);
					jugadoresGlobal = jugSevilla;
					verSevilla.rellenarFormulario(contadorGlobal);
					estadio = new ImageIcon("resources/estadios/pizjuan.jpg").getImage();
					estadioModificado = new ImageIcon(estadio.getScaledInstance(200, 200, estadio.SCALE_SMOOTH));
					verSevilla.lblEstadio.setIcon(estadioModificado);
					verSevilla.lblEscudo.setIcon(iconSevilla);
					verSevilla.ponerImagen(fotos[contadorGlobal]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				verSevilla.setVisible(true);
				dispose();
			}
		});
		btnSevilla.setIcon(iconSevilla);
		contentPane.add(btnSevilla);

		iconAtletico = new ImageIcon("resources/escudos/atleticoJugar.png");
		btnAtletico = new JButton();
		btnAtletico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				Jugar.escribirOLeer("Atletico de Madrid");
				verEquipo verAtletico = new verEquipo();
				try {
					atletico = Jugar.deserializar(new File("resources/Atletico de Madrid"));
					equipo = atletico;
					Componente[] jugadores = new Componente[12];
					String[] lineas = new String[12];
					for (int i = 0; i < jugadores.length; i++) {
						jugadores[i] = atletico.getPlantilla().get(i);
						fotos[i] = atletico.getPlantilla().get(i).getFoto();
						lineas[i] = String.valueOf(jugadores[i]);
					}
					String[][] jugAtletico = returnInformacion(lineas);
					jugadoresGlobal = jugAtletico;
					verAtletico.rellenarFormulario(contadorGlobal);
					estadio = new ImageIcon("resources/estadios/wanda.jpg").getImage();
					estadioModificado = new ImageIcon(estadio.getScaledInstance(200, 200, estadio.SCALE_SMOOTH));
					verAtletico.lblEstadio.setIcon(estadioModificado);
					verAtletico.lblEscudo.setIcon(iconAtletico);
					verAtletico.ponerImagen(fotos[contadorGlobal]);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				verAtletico.setVisible(true);
				dispose();
			}
		});
		btnAtletico.setIcon(iconAtletico);
		contentPane.add(btnAtletico);
		setVisible(true);
		
		contentPane.setBackground(Color.WHITE);
	}

	private String[][] returnInformacion(String[] lineas) {
		String[][] jugadores = new String[12][4];
		String caracterFinal;
		for (int i = 0; i < lineas.length; i++) {
			boolean dato = false;
			int contador = 0;
			String caracter;
			caracterFinal = "";
			for (int j = 0; j < lineas[i].length(); j++) {
				caracter = String.valueOf(lineas[i].charAt(j));
				if (caracter.equals(":"))
					dato = true;
				else if (caracter.equals(",")) {
					dato = false;
				}
				if (dato && !(caracter.equals(":"))) {
					caracterFinal += caracter;
					jugadores[i][contador] = caracterFinal;
				}
				if (caracter.equals(",")) {
					contador++;
					caracterFinal = "";
				}

			}
		}
		return jugadores;
	}
}
