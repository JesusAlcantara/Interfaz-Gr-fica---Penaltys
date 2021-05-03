package verequipo;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import clasesprincipales.Componente;
import clasesprincipales.Equipo;
import clasesprincipales.Jugador;
import jugar.Jugar;

public class verEquipo extends JFrame {

	private JPanel contentPane;
	JTextField textNombre, textPosicion, textDorsal, textPrecision;
	private JPanel panel, panel_1, panel_2, panel_3;
	private JLabel lblNombre, lblPosicion, lblDorsal, lblPrecision;
	private JButton btnModificar, btnFirst, btnNext, btnPrev;
	JLabel lblImagen;
	private JButton btnImage, btnLast;
	private JButton btnEntrenador, btnResultados, btnInicio;
	private JLabel lblEstadio, lblEscudo;
	private Image inicio;
	private ImageIcon inicioModificado;

	public verEquipo() {
		super("Ver Equipo");
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/icono/icono.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(650, 350);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 2));

		panel = new JPanel();
		contentPane.add(panel);

		lblNombre = new JLabel("Nombre");
		panel.add(lblNombre);

		textNombre = new JTextField("");
		panel.add(textNombre);
		textNombre.setColumns(20);

		lblPosicion = new JLabel("Posición");
		panel.add(lblPosicion);

		textPosicion = new JTextField("");
		panel.add(textPosicion);
		textPosicion.setColumns(20);

		lblDorsal = new JLabel("Dorsal");
		panel.add(lblDorsal);

		textDorsal = new JTextField("");
		panel.add(textDorsal);
		textDorsal.setColumns(20);

		lblPrecision = new JLabel("Precisión");
		panel.add(lblPrecision);

		textPrecision = new JTextField("");
		panel.add(textPrecision);
		textPrecision.setColumns(20);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				ArrayList<Componente> plantilla = plantillaEquipo.equipo.getPlantilla();
				int i = 0;
				for (Componente c : plantilla) {
					if (c instanceof Jugador) {
						if (i == plantillaEquipo.contadorGlobal) {
							// Nombre
							if (comprobarString(textNombre.getText()) == false
									|| isEmpty(textNombre.getText()) == false)
								JOptionPane.showMessageDialog(verEquipo.this, "Nombre incorrecto.", "Error",
										JOptionPane.ERROR_MESSAGE);
							else {
								((Jugador) c).setNombre(textNombre.getText());
								plantillaEquipo.jugadoresGlobal[plantillaEquipo.contadorGlobal][1] = textNombre
										.getText();
							}

							// Dorsal
							try {
								((Jugador) c).setDorsal(Integer.parseInt(textDorsal.getText()));
								plantillaEquipo.jugadoresGlobal[plantillaEquipo.contadorGlobal][0] = textDorsal
										.getText();
							} catch (Exception e) {
								JOptionPane.showMessageDialog(verEquipo.this, "Dorsal incorrecto.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

							// Posicion
							if (comprobarString(textPosicion.getText()) == false
									|| isEmpty(textPosicion.getText()) == false)
								JOptionPane.showMessageDialog(verEquipo.this, "Posición incorrecta.", "Error",
										JOptionPane.ERROR_MESSAGE);
							else {
								((Jugador) c).setPosicion(textPosicion.getText());
								plantillaEquipo.jugadoresGlobal[plantillaEquipo.contadorGlobal][2] = textPosicion
										.getText();
							}

							// Precision
							try {
								((Jugador) c).setPrecision(Integer.parseInt(textPrecision.getText()));
								plantillaEquipo.jugadoresGlobal[plantillaEquipo.contadorGlobal][3] = textPrecision
										.getText();
							} catch (Exception e) {
								JOptionPane.showMessageDialog(verEquipo.this, "Precisión incorrecta.", "Error",
										JOptionPane.ERROR_MESSAGE);
							}

						}
					}
					i++;
				}
				Equipo eq1 = new Equipo(plantillaEquipo.equipo.getNombre(), plantillaEquipo.equipo.getCiudad(),
						plantillaEquipo.equipo.getEstadio(), plantillaEquipo.equipo.getFoto(), plantilla);
				try {
					Jugar.serializar(eq1, plantillaEquipo.equipo.getNombre());
					JOptionPane.showMessageDialog(verEquipo.this, "Datos modificados.", "Modificación",
							JOptionPane.PLAIN_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel.add(btnModificar);

		panel_2 = new JPanel();
		contentPane.add(panel_2);

		lblImagen = new JLabel("");
		panel_2.add(lblImagen);

		btnImage = new JButton("Select Image");
		btnImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				ArrayList<Componente> plantilla = plantillaEquipo.equipo.getPlantilla();

				String rutaOriginal = plantilla.get(plantillaEquipo.contadorGlobal).getFoto();
				String ruta = "";
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filtrado = new FileNameExtensionFilter("JPG, PNG, JPEG & GIF", "jpg", "png",
						"jpeg", "gif");
				fileChooser.setFileFilter(filtrado);
				int respuesta = fileChooser.showOpenDialog(verEquipo.this);
				if (respuesta == fileChooser.APPROVE_OPTION) {
					ruta = fileChooser.getSelectedFile().getPath();
					Image imagen = new ImageIcon(ruta).getImage();
					ImageIcon icon = new ImageIcon(imagen.getScaledInstance(150, 150, imagen.SCALE_SMOOTH));
					lblImagen.setIcon(icon);
				}

				for (Componente c : plantilla) {
					if (c instanceof Jugador) {
						if (((Jugador) c).getFoto().equals(rutaOriginal)) {
							((Jugador) c).setFoto(ruta);
							plantillaEquipo.fotos[plantillaEquipo.contadorGlobal] = ruta;
						}
					}
				}

				Equipo eq1 = new Equipo(plantillaEquipo.equipo.getNombre(), plantillaEquipo.equipo.getCiudad(),
						plantillaEquipo.equipo.getEstadio(), plantillaEquipo.equipo.getFoto(), plantilla);
				try {
					Jugar.serializar(eq1, plantillaEquipo.equipo.getNombre());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		panel_2.add(btnImage);

		panel_1 = new JPanel();
		contentPane.add(panel_1);

		btnFirst = new JButton("<< First");
		btnFirst.setEnabled(false);
		btnFirst.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				btnPrev.setEnabled(false);
				btnNext.setEnabled(true);
				btnFirst.setEnabled(false);
				btnLast.setEnabled(true);
				plantillaEquipo.contadorGlobal = 0;
				rellenarFormulario(plantillaEquipo.contadorGlobal);
				ponerImagen(plantillaEquipo.fotos[plantillaEquipo.contadorGlobal]);
			}
		});
		panel_1.add(btnFirst);

		btnPrev = new JButton("< Prev");
		btnPrev.setEnabled(false);
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				btnNext.setEnabled(true);
				btnLast.setEnabled(true);
				plantillaEquipo.contadorGlobal--;
				if (plantillaEquipo.contadorGlobal < 0)
					plantillaEquipo.contadorGlobal = 0;
				rellenarFormulario(plantillaEquipo.contadorGlobal);
				ponerImagen(plantillaEquipo.fotos[plantillaEquipo.contadorGlobal]);
				if (plantillaEquipo.contadorGlobal == 0) {
					btnPrev.setEnabled(false);
					btnFirst.setEnabled(false);
				}
			}
		});
		panel_1.add(btnPrev);
		btnNext = new JButton("Next >");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				btnPrev.setEnabled(true);
				btnFirst.setEnabled(true);
				plantillaEquipo.contadorGlobal++;
				if (plantillaEquipo.contadorGlobal >= plantillaEquipo.jugadoresGlobal.length)
					plantillaEquipo.contadorGlobal--;
				rellenarFormulario(plantillaEquipo.contadorGlobal);
				ponerImagen(plantillaEquipo.fotos[plantillaEquipo.contadorGlobal]);
				if (plantillaEquipo.contadorGlobal == (plantillaEquipo.jugadoresGlobal.length - 1)) {
					btnNext.setEnabled(false);
					btnLast.setEnabled(false);
				}
			}
		});
		panel_1.add(btnNext);

		btnLast = new JButton("Last >>");
		btnLast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				btnPrev.setEnabled(true);
				btnNext.setEnabled(false);
				btnFirst.setEnabled(true);
				btnLast.setEnabled(false);
				plantillaEquipo.contadorGlobal = 10;
				rellenarFormulario(plantillaEquipo.contadorGlobal);
				ponerImagen(plantillaEquipo.fotos[plantillaEquipo.contadorGlobal]);
			}
		});
		panel_1.add(btnLast);
		
		lblEstadio = new JLabel("");
		panel_1.add(lblEstadio);

		panel_3 = new JPanel();
		contentPane.add(panel_3);

		btnEntrenador = new JButton("Entrenador");
		panel_3.add(btnEntrenador);

		btnResultados = new JButton("Resultados");
		panel_3.add(btnResultados);

		inicio = new ImageIcon("resources/plantilla/casaInicio.png").getImage();
		inicioModificado = new ImageIcon(inicio.getScaledInstance(15, 15, inicio.SCALE_SMOOTH));
		btnInicio = new JButton("Inicio", inicioModificado);
		btnInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				verEquipo.this.setVisible(false);
				plantillaEquipo plantilla = new plantillaEquipo();
				plantillaEquipo.contadorGlobal = 0;
				plantilla.setVisible(true);
				dispose();
			}
		});
		panel_3.add(btnInicio);
		
		lblEscudo = new JLabel("");
		panel_3.add(lblEscudo);
	}

	public void ponerImagen(String foto) {
		ImageIcon imagen = new ImageIcon(foto);
		Image imagen2 = imagen.getImage();
		Image imagen3 = imagen2.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
		imagen = new ImageIcon(imagen3);
		lblImagen.setIcon(imagen);
	}

	public void rellenarFormulario(int contador) {
		textNombre.setText(quitarEspacios(plantillaEquipo.jugadoresGlobal[contador][1]));
		textDorsal.setText(quitarEspacios(plantillaEquipo.jugadoresGlobal[contador][0]));
		textPosicion.setText(quitarEspacios(plantillaEquipo.jugadoresGlobal[contador][2]));
		textPrecision.setText(quitarEspacios(plantillaEquipo.jugadoresGlobal[contador][3]));
	}

	private String quitarEspacios(String palabra) {
		int i;
		for (i = 0; i < palabra.length(); i++) {
			if (!(palabra.substring(i, i + 1).equals(" "))) {
				break;
			}
		}
		return palabra.substring(i);
	}

	private boolean comprobarString(String palabra) {
		for (int i = 0; i < palabra.length(); i++) {
			char c = palabra.charAt(i);
			if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == ' ')) {
				return false;
			}
		}
		return true;
	}

	private boolean isEmpty(String palabra) {
		if (palabra.isEmpty())
			return false;
		return true;
	}
}
