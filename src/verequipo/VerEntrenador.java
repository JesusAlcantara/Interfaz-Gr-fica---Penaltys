package verequipo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
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
import verequipo.plantillaEquipo;
import verequipo.verEquipo;

@SuppressWarnings("serial")
public class VerEntrenador extends JFrame {

	private JPanel contentPane;
	private JTextField txtNombre, txtEdad, txtNacionalidad;
	private JLabel lblNombre, lblEdad, lblNacionalidad, lblEntrenador;
	private JButton btnModificar, btnResultados, btnVolver, btnSeleccion;

	public VerEntrenador() {
		super("Entrenador");
		setSize(500, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(38, 49, 49, 14);
		contentPane.add(lblNombre);

		lblEdad = new JLabel("Edad");
		lblEdad.setBounds(38, 93, 49, 14);
		contentPane.add(lblEdad);

		lblNacionalidad = new JLabel("Nacionalidad");
		lblNacionalidad.setBounds(38, 138, 69, 14);
		contentPane.add(lblNacionalidad);

		txtNombre = new JTextField();
		txtNombre.setBounds(126, 46, 96, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);

		txtEdad = new JTextField();
		txtEdad.setBounds(126, 90, 96, 20);
		contentPane.add(txtEdad);
		txtEdad.setColumns(10);

		txtNacionalidad = new JTextField();
		txtNacionalidad.setBounds(126, 135, 96, 20);
		contentPane.add(txtNacionalidad);
		txtNacionalidad.setColumns(10);

		btnModificar = new JButton("Modificar");
		btnModificar.setBounds(38, 200, 106, 23);
		contentPane.add(btnModificar);

		btnResultados = new JButton("Resultados");
		btnResultados.setBounds(169, 200, 106, 23);
		contentPane.add(btnResultados);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(300, 200, 106, 23);
		contentPane.add(btnVolver);

		btnSeleccion = new JButton("Selecciona Imagen");
		btnSeleccion.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnSeleccion.setBounds(287, 138, 119, 23);
		contentPane.add(btnSeleccion);

		lblEntrenador = new JLabel("New label");
		lblEntrenador.setBounds(287, 49, 49, 14);
		establecerImagen();
		contentPane.add(lblEntrenador);

		setVisible(true);

		ManejadorEventos m = new ManejadorEventos();
		btnModificar.addActionListener(m);
		btnSeleccion.addActionListener(m);
		btnResultados.addActionListener(m);
	}

	private void establecerImagen() {
		// TODO Auto-generated method stub
		ImageIcon imagen = new ImageIcon();
		Image imagen2 = imagen.getImage();
		Image imagen3 = imagen2.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		imagen = new ImageIcon(imagen3);
		lblEntrenador.setIcon(imagen);
	}

	public class ManejadorEventos implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = (Object) e.getSource();
			if (o == btnResultados) {
				ArrayList<Componente> plantilla = plantillaEquipo.equipo.getPlantilla();
				int cont = plantillaEquipo.contadorGlobal;

			} else if (o == btnSeleccion) {/*
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
					ImageIcon icon = new ImageIcon(imagen.getScaledInstance(200, 200, imagen.SCALE_SMOOTH));
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
			*/	
			} else if (o == btnModificar) {/*
				ArrayList<Componente> plantilla = plantillaEquipo.equipo.getPlantilla();
				int i = 0;
				boolean estado = false;
				for (Componente c : plantilla) {
					if (c instanceof Jugador) {
						if (i == plantillaEquipo.contadorGlobal) {
							// Nombre
							if (comprobarString(textNombre.getText()) == false
									|| isEmpty(textNombre.getText()) == false) {
								JOptionPane.showMessageDialog(verEquipo.this, "Nombre incorrecto.", "Error",
										JOptionPane.ERROR_MESSAGE);
								estado = true;
							} else {
								((Jugador) c).setNombre(textNombre.getText());
								plantillaEquipo.jugadoresGlobal[plantillaEquipo.contadorGlobal][1] = textNombre
										.getText();
							}

							// Dorsal
							try {
								((Jugador) c).setDorsal(Integer.parseInt(textDorsal.getText()));
								plantillaEquipo.jugadoresGlobal[plantillaEquipo.contadorGlobal][0] = textDorsal
										.getText();
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(verEquipo.this, "Dorsal incorrecto.", "Error",
										JOptionPane.ERROR_MESSAGE);
								estado = true;
							}

							// Posicion
							if (comprobarString(textPosicion.getText()) == false
									|| isEmpty(textPosicion.getText()) == false) {
								JOptionPane.showMessageDialog(verEquipo.this, "Posición incorrecta.", "Error",
										JOptionPane.ERROR_MESSAGE);
								estado = true;
							} else {
								((Jugador) c).setPosicion(textPosicion.getText());
								plantillaEquipo.jugadoresGlobal[plantillaEquipo.contadorGlobal][2] = textPosicion
										.getText();
							}

							// Precision
							try {
								((Jugador) c).setPrecision(Integer.parseInt(textPrecision.getText()));
								plantillaEquipo.jugadoresGlobal[plantillaEquipo.contadorGlobal][3] = textPrecision
										.getText();
							} catch (NumberFormatException e) {
								JOptionPane.showMessageDialog(verEquipo.this, "Precisión incorrecta.", "Error",
										JOptionPane.ERROR_MESSAGE);
								estado = true;
							}

						}
					}
					i++;
				}
				Equipo eq1 = new Equipo(plantillaEquipo.equipo.getNombre(), plantillaEquipo.equipo.getCiudad(),
						plantillaEquipo.equipo.getEstadio(), plantillaEquipo.equipo.getFoto(), plantilla);

			}
		*/
}
	}
}
	// Crear metodo con los datos de entrenador
	public void DatosEntrenador() {

	}
}
