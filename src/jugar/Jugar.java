package jugar;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import clasesprincipales.Componente;
import clasesprincipales.Entrenador;
import clasesprincipales.Equipo;
import clasesprincipales.Jugador;
import opciones.Opciones;


import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.SystemColor;
import java.awt.Color;

public class Jugar extends JFrame {
	public static Equipo eqLocal;
	public static Equipo eqVisitante;
	public static ArrayList<Componente> plantillaLocal = new ArrayList<>();
	public static ArrayList<Componente> plantillaVisitante = new ArrayList<>();
	public static ArrayList<Equipo> equipos = new ArrayList<>();
	private String nombreLocal;
	private int numLanzamientos;
	private JPanel contentPane;
	public static int golesLocal = 0;
	public static int golesVisitante = 0;
	private JList plantillaLocalList;
	private JList plantillaVisitanteList;
	JLabel entrenadorVLBL;
	JLabel entrenadorLLBL;
	private String entrenadorVtxt;
	private String entrenadorLtxt;
	private JLabel lanzamientosLBL;
	private JButton lanzarBTN;
	private JLabel resultadoLBL;
	private int contador=0;
	private int dorsalLanzador;
	private String[] nombrePL;
	private String[] nombrePV;
	JLabel escudoLocal;
	JLabel escudoVisitante;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Jugar() {
		super("Jugar");
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//setBounds(100, 100, 700, 650);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		golesLocal = 0;
		golesVisitante = 0;
		numLanzamientos=Opciones.numLanzamientos;
		nombreLocal=Opciones.nombreLocal;
		escribirOLeer();
		cargarLocal();
		cargarVisitante();
		
		
		
		JLabel nombreL = new JLabel(eqLocal.getNombre());
		nombreL.setHorizontalAlignment(SwingConstants.CENTER);
		nombreL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nombreL.setBounds(50, 40, 210, 40);
		contentPane.add(nombreL);

		JLabel nombreV = new JLabel(eqVisitante.getNombre());
		nombreV.setHorizontalAlignment(SwingConstants.CENTER);
		nombreV.setFont(new Font("Tahoma", Font.PLAIN, 20));
		nombreV.setBounds(540, 40, 210, 40);
		contentPane.add(nombreV);

		escudoLocal = new JLabel();
		
		escudoLocal.setBounds(50, 91, 210, 170);
		contentPane.add(escudoLocal);

		escudoVisitante = new JLabel();
		escudoVisitante.setBounds(540, 91, 210, 170);
		contentPane.add(escudoVisitante);
		cargarEscudos();
		JLabel lblNewLabel_4 = new JLabel("Jugadores");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(50, 260, 210, 40);
		contentPane.add(lblNewLabel_4);

		String[] nombrePL=mostrarLocal();
		plantillaLocalList = new JList(nombrePL);
		plantillaLocalList.setBounds(50, 301, 210, 201);
		plantillaLocalList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		plantillaLocalList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				JList lista = (JList) e.getSource();
				if(lista.getSelectedValue()!=null) {
				String datos=lista.getSelectedValue().toString();
				String[] dorsal=datos.split(" ");
				dorsalLanzador=Integer.parseInt(dorsal[0]);
				}
				
			}
			
		});
		contentPane.add(plantillaLocalList);
		
		entrenadorLLBL = new JLabel("Entrenador:"+entrenadorLtxt);
		entrenadorLLBL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		entrenadorLLBL.setBounds(50, 513, 170, 40);
		contentPane.add(entrenadorLLBL);
		
		JLabel lblNewLabel_4_2 = new JLabel("Jugadores");
		lblNewLabel_4_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4_2.setBounds(540, 260, 210, 40);
		contentPane.add(lblNewLabel_4_2);
		nombrePV=mostrarVisitante();
		plantillaVisitanteList = new JList(nombrePV);
		plantillaVisitanteList.setBounds(540, 301, 210, 201);
		contentPane.add(plantillaVisitanteList);
		
		entrenadorVLBL = new JLabel("Entrenador: "+entrenadorVtxt);
		entrenadorVLBL.setFont(new Font("Tahoma", Font.PLAIN, 15));
		entrenadorVLBL.setBounds(540, 515, 210, 40);
		contentPane.add(entrenadorVLBL);

		lanzarBTN = new JButton("Lanzar");
		lanzarBTN.setBackground(new Color(153, 180, 209));
		lanzarBTN.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lanzarBTN.setBounds(300, 210, 195, 40);
		lanzarBTN.addActionListener(new botones());
		contentPane.add(lanzarBTN);
		
		lanzamientosLBL = new JLabel("Nº de Penaltis: "+contador+"-"+numLanzamientos);
		lanzamientosLBL.setHorizontalAlignment(SwingConstants.CENTER);
		lanzamientosLBL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lanzamientosLBL.setBounds(300, 47, 195, 27);
		contentPane.add(lanzamientosLBL);
		
		resultadoLBL = new JLabel("RESULTADO: 0-0");
		resultadoLBL.setHorizontalAlignment(SwingConstants.CENTER);
		resultadoLBL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		resultadoLBL.setBounds(300, 97, 195, 27);
		contentPane.add(resultadoLBL);
	}
	private class botones implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==lanzarBTN) {
				if (contador!=numLanzamientos) {
				int lanzamiento=lanzamiento(buscarJugador(dorsalLanzador));
				if(lanzamiento==1) {
					golesLocal++;
					JOptionPane.showMessageDialog(Jugar.this, "Goooool");
				}
				else if(lanzamiento==-1) {
					JOptionPane.showMessageDialog(Jugar.this, "Al paloooo");
				}
				else if(lanzamiento==0){
					JOptionPane.showMessageDialog(Jugar.this, "Fueraaaa");
				}
				plantillaLocal.remove(buscarJugador(dorsalLanzador));
				nombrePL=mostrarLocal();
				plantillaLocalList.removeAll();
				plantillaLocalList.setListData(nombrePL);
				int lanzador2;
				do{
					lanzador2=(int)(Math.random()*11+1);
					
				}while(buscarJugadorV(lanzador2)==null);
				lanzamiento=lanzamiento(buscarJugadorV(lanzador2));
				if(lanzamiento==1) {
					golesVisitante++;
					JOptionPane.showMessageDialog(Jugar.this, "Goooool");
				}
				else if(lanzamiento==-1) {
					JOptionPane.showMessageDialog(Jugar.this, "Al paloooo");
				}
				else if(lanzamiento==0){
					JOptionPane.showMessageDialog(Jugar.this, "Fueraaaa");
				}
				plantillaVisitante.remove(buscarJugadorV(lanzador2));
				nombrePV=mostrarVisitante();
				plantillaVisitanteList.removeAll();
				plantillaVisitanteList.setListData(nombrePV);
				contador++;
				lanzamientosLBL.setText("Nº DE PENALTIS: "+contador+"-"+numLanzamientos);
				resultadoLBL.setText("RESULTADO: "+golesLocal+"-"+golesVisitante);
				}
				else if(contador==numLanzamientos) {
					if(golesLocal!=golesVisitante) {
						try {
							FileWriter fw = new FileWriter("resources/Historial.txt", true);
							fw.write(eqLocal.getNombre()+";"+eqVisitante.getNombre()+";"+golesLocal+";"+golesVisitante+"\n");
							fw.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} 
						
						if(golesLocal>golesVisitante) {
							ResultadoFinal rf=new ResultadoFinal();
							rf.setVisible(true);
							dispose();
						}
						else {
							ResultadoFinal rf=new ResultadoFinal();
							rf.setVisible(true);
							dispose();
						}
					}
					else if(golesLocal==golesVisitante) {
						if(plantillaLocal.size()==1) {
							plantillaLocal=eqLocal.getPlantilla();
							plantillaVisitante=eqLocal.getPlantilla();
							nombrePL=mostrarLocal();
							plantillaLocalList.removeAll();
							plantillaLocalList.setListData(nombrePL);
							nombrePV=mostrarVisitante();
							plantillaVisitanteList.removeAll();
							plantillaVisitanteList.setListData(nombrePV);
						}
						else {
							int lanzamiento=lanzamiento(buscarJugador(dorsalLanzador));
							if(lanzamiento==1) {
								golesLocal++;
								JOptionPane.showMessageDialog(Jugar.this, "Goooool");
							}
							else if(lanzamiento==-1) {
								JOptionPane.showMessageDialog(Jugar.this, "Al paloooo");
							}
							else if(lanzamiento==0){
								JOptionPane.showMessageDialog(Jugar.this, "Fueraaaa");
							}
							plantillaLocal.remove(buscarJugador(dorsalLanzador));
							nombrePL=mostrarLocal();
							plantillaLocalList.removeAll();
							plantillaLocalList.setListData(nombrePL);
							int lanzador2;
							do{
								lanzador2=(int)(Math.random()*11+1);
								
							}while(buscarJugadorV(lanzador2)==null);
							lanzamiento=lanzamiento(buscarJugadorV(lanzador2));
							if(lanzamiento==1) {
								golesVisitante++;
								JOptionPane.showMessageDialog(Jugar.this, "Goooool");
							}
							else if(lanzamiento==-1) {
								JOptionPane.showMessageDialog(Jugar.this, "Al paloooo");
							}
							else if(lanzamiento==0){
								JOptionPane.showMessageDialog(Jugar.this, "Fueraaaa");
							}
							plantillaVisitante.remove(buscarJugadorV(lanzador2));
							nombrePV=mostrarVisitante();
							plantillaVisitanteList.removeAll();
							plantillaVisitanteList.setListData(nombrePV);
							//contador++;
							//lanzamientosLBL.setText("Nº de Penaltis: "+contador+"-"+numLanzamientos);
							resultadoLBL.setText("RESULTADO: "+golesLocal+"-"+golesVisitante);
						}
					}
				}
				
				}
			
		}
		
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public int getNumLanzamientos() {
		return numLanzamientos;
	}

	public void setNumLanzamientos(int numLanzamientos) {
		this.numLanzamientos = numLanzamientos;
	}

	public static Equipo RealMadrid() {
		ArrayList<Componente> plantillaRealMadrid = new ArrayList<>();
		Jugador j1 = new Jugador("Thibaut Courtois", 1, 40);
		Jugador j2 = new Jugador("Dani Carvajal", 2, 70);
		Jugador j3 = new Jugador("Raphael Varane", 3, 60);
		Jugador j4 = new Jugador("Sergio Ramos", 4, 90);
		Jugador j5 = new Jugador("Ferland Mendy", 5, 60);
		Jugador j6 = new Jugador("Casemiro", 6, 70);
		Jugador j7 = new Jugador("Eden Hazard", 7, 95);
		Jugador j8 = new Jugador("Toni Kroos", 8, 95);
		Jugador j9 = new Jugador("Karim Benzema", 9, 90);
		Jugador j10 = new Jugador("Luka Modric", 10, 90);
		Jugador j11 = new Jugador("Marco Asensio", 11, 85);
		Entrenador e1 = new Entrenador("Zidane", 45);
		plantillaRealMadrid.add(j1);
		plantillaRealMadrid.add(j2);
		plantillaRealMadrid.add(j3);
		plantillaRealMadrid.add(j4);
		plantillaRealMadrid.add(j5);
		plantillaRealMadrid.add(j6);
		plantillaRealMadrid.add(j7);
		plantillaRealMadrid.add(j8);
		plantillaRealMadrid.add(j9);
		plantillaRealMadrid.add(j10);
		plantillaRealMadrid.add(j11);
		plantillaRealMadrid.add(e1);
		Equipo eq1 = new Equipo("Real Madrid", "Madrid", "Santiago Bernabeu", plantillaRealMadrid);
		return eq1;
	}

	public static Equipo Barcelona() {
		ArrayList<Componente> plantillaBarcelona = new ArrayList<>();
		Jugador j1 = new Jugador("ter Stegen", 1, 40);
		Jugador j2 = new Jugador("Dest", 2, 60);
		Jugador j3 = new Jugador("Pique Gerard", 3, 70);
		Jugador j4 = new Jugador("Lenglet Clement", 4, 70);
		Jugador j5 = new Jugador("Alba Jordi", 5, 60);
		Jugador j6 = new Jugador("Busquets Sergio", 6, 85);
		Jugador j7 = new Jugador("Pedri", 7, 80);
		Jugador j8 = new Jugador("de Jong Frenkie", 8, 80);
		Jugador j9 = new Jugador("Griezmann Antoine", 9, 70);
		Jugador j10 = new Jugador("Messi Lionel", 10, 90);
		Jugador j11 = new Jugador("Dembele Ousmane", 11, 85);
		Entrenador e1 = new Entrenador("Koeman", 45);
		plantillaBarcelona.add(j1);
		plantillaBarcelona.add(j2);
		plantillaBarcelona.add(j3);
		plantillaBarcelona.add(j4);
		plantillaBarcelona.add(j5);
		plantillaBarcelona.add(j6);
		plantillaBarcelona.add(j7);
		plantillaBarcelona.add(j8);
		plantillaBarcelona.add(j9);
		plantillaBarcelona.add(j10);
		plantillaBarcelona.add(j11);
		plantillaBarcelona.add(e1);
		Equipo eq1 = new Equipo("FC Barcelona", "Barcelona", "Camp Nou", plantillaBarcelona);
		return eq1;
	}

	public static Equipo Cadiz() {
		ArrayList<Componente> plantillaCadiz = new ArrayList<>();
		Jugador j1 = new Jugador("Ledesma Jeremias", 1, 40);
		Jugador j2 = new Jugador("Isaac Carcelen", 2, 60);
		Jugador j3 = new Jugador("Fali", 3, 70);
		Jugador j4 = new Jugador("Juan Cala", 4, 70);
		Jugador j5 = new Jugador("Pacha Espino", 5, 60);
		Jugador j6 = new Jugador("Jose mari", 6, 85);
		Jugador j7 = new Jugador("Salvi", 7, 80);
		Jugador j8 = new Jugador("Alex Fernandez", 8, 90);
		Jugador j9 = new Jugador("Choco Lozano", 9, 70);
		Jugador j10 = new Jugador("Negredo", 10, 90);
		Jugador j11 = new Jugador("Perea", 11, 80);
		Entrenador e1 = new Entrenador("Cervera", 45);
		plantillaCadiz.add(j1);
		plantillaCadiz.add(j2);
		plantillaCadiz.add(j3);
		plantillaCadiz.add(j4);
		plantillaCadiz.add(j5);
		plantillaCadiz.add(j6);
		plantillaCadiz.add(j7);
		plantillaCadiz.add(j8);
		plantillaCadiz.add(j9);
		plantillaCadiz.add(j10);
		plantillaCadiz.add(j11);
		plantillaCadiz.add(e1);
		Equipo eq1 = new Equipo("Cadiz CF", "Cadiz", "Ramon de Carranza", plantillaCadiz);
		return eq1;
	}

	public static Equipo AtleticoDeMadrid() {
		ArrayList<Componente> plantillaAtleticoDeMadrid = new ArrayList<>();
		Jugador j1 = new Jugador("Oblack", 1, 40);
		Jugador j2 = new Jugador("Trippier", 2, 60);
		Jugador j3 = new Jugador("Gimenez", 3, 70);
		Jugador j4 = new Jugador("Savic", 4, 70);
		Jugador j5 = new Jugador("Lodi", 5, 60);
		Jugador j6 = new Jugador("Koke", 6, 80);
		Jugador j7 = new Jugador("Joao Felix", 7, 90);
		Jugador j8 = new Jugador("Marcos Llorente", 8, 80);
		Jugador j9 = new Jugador("Luis Suarez", 9, 90);
		Jugador j10 = new Jugador("Lemar", 10, 90);
		Jugador j11 = new Jugador("Yannick Carrasco", 11, 90);
		Entrenador e1 = new Entrenador("Simeone", 45);
		plantillaAtleticoDeMadrid.add(j1);
		plantillaAtleticoDeMadrid.add(j2);
		plantillaAtleticoDeMadrid.add(j3);
		plantillaAtleticoDeMadrid.add(j4);
		plantillaAtleticoDeMadrid.add(j5);
		plantillaAtleticoDeMadrid.add(j6);
		plantillaAtleticoDeMadrid.add(j7);
		plantillaAtleticoDeMadrid.add(j8);
		plantillaAtleticoDeMadrid.add(j9);
		plantillaAtleticoDeMadrid.add(j10);
		plantillaAtleticoDeMadrid.add(j11);
		plantillaAtleticoDeMadrid.add(e1);
		Equipo eq1 = new Equipo("Atletico de Madrid", "Madrid", "Wanda Metropolitano", plantillaAtleticoDeMadrid);
		return eq1;
	}

	public static Equipo Sevilla() {
		ArrayList<Componente> plantillaSevilla = new ArrayList<>();
		Jugador j1 = new Jugador("Bono", 1, 40);
		Jugador j2 = new Jugador("Jesus Navas", 2, 80);
		Jugador j3 = new Jugador("Diego Carlos", 3, 70);
		Jugador j4 = new Jugador("Kounde", 4, 70);
		Jugador j5 = new Jugador("Acuña", 5, 60);
		Jugador j6 = new Jugador("Fernando", 6, 80);
		Jugador j7 = new Jugador("Suso", 7, 80);
		Jugador j8 = new Jugador("Jordan", 8, 80);
		Jugador j9 = new Jugador("En-Nesyri", 9, 70);
		Jugador j10 = new Jugador("Rakitic", 10, 90);
		Jugador j11 = new Jugador("Ocampos", 11, 85);
		Entrenador e1 = new Entrenador("Lopetegui", 45);
		plantillaSevilla.add(j1);
		plantillaSevilla.add(j2);
		plantillaSevilla.add(j3);
		plantillaSevilla.add(j4);
		plantillaSevilla.add(j5);
		plantillaSevilla.add(j6);
		plantillaSevilla.add(j7);
		plantillaSevilla.add(j8);
		plantillaSevilla.add(j9);
		plantillaSevilla.add(j10);
		plantillaSevilla.add(j11);
		plantillaSevilla.add(e1);
		Equipo eq1 = new Equipo("Sevilla FC", "Sevilla", "Ramon Sanchez Pizjuan", plantillaSevilla);
		return eq1;
	}

	private void escribirOLeer() {
		File f = new File("resources/equipos");
		if (f.exists() && f.isFile()) {
			try {
				equipos = deserializar();
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
		} else if (!f.exists()) {
			ArrayList<Equipo> equipos = new ArrayList<>();
			equipos.add(RealMadrid());
			equipos.add(RealMadrid());
			equipos.add(Barcelona());
			equipos.add(Cadiz());
			equipos.add(Sevilla());
			equipos.add(AtleticoDeMadrid());
			try {
				serializar(equipos);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void serializar(ArrayList<Equipo> eq) throws FileNotFoundException, IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("resources/equipos"));
		os.writeObject(eq);
		os.close();
	}

	private ArrayList<Equipo> deserializar() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream("resources/equipos"));
		return ((ArrayList<Equipo>) is.readObject());
	}

	private void cargarLocal() {
		for (Equipo e : equipos) {
			if (e.getNombre().equalsIgnoreCase(getNombreLocal())) {
				eqLocal=e;
			}

		}
		plantillaLocal=eqLocal.getPlantilla();
	}

	private void cargarVisitante() {
		do {
			int visitante = (int) (Math.random() * 6 + 1);
			eqVisitante = cargarEquipo(visitante);
		} while (eqVisitante.getNombre() == eqLocal.getNombre());
		plantillaVisitante = eqVisitante.getPlantilla();
	}

	public Equipo cargarEquipo(int numero) {
		for(Equipo e: equipos){
			if (numero == 1) {
				if(e.getNombre().equalsIgnoreCase("Real Madrid")) {
					return e;
				}
			} else if (numero == 2) {
				if(e.getNombre().equalsIgnoreCase("FC Barcelona")) {
					return e;
				}
			} else if (numero == 3) {
				if(e.getNombre().equalsIgnoreCase("Cadiz CF")) {
					return e;
				}
			} else if (numero == 4) {
				if(e.getNombre().equalsIgnoreCase("Sevilla FC")) {
					return e;
				}
			} else {
				if(e.getNombre().equalsIgnoreCase("Atletico de Madrid")) {
					return e;
					}
		}
		}
		return null;
	}

	public int lanzamiento(Jugador Jugador)  {
		Random r = new Random();
		int lanza = r.nextInt() * 100 + 1;
		if (lanza <= Jugador.getPrecision()) {
			return 1;
		} else if (lanza >= (100 - Jugador.getPrecision()) / 2) {
			return -1;
		} else
		return 0;
	}

	public Jugador buscarJugador(int numero) {
		for (Componente j : plantillaLocal) {
			if (j instanceof Jugador) {
				if (((Jugador) j).getDorsal() == numero) {
					return (Jugador) j;
				}
			}

		}
		return null;
	}

	public Jugador buscarJugadorV(int numero) {
		for (Componente j : plantillaVisitante) {
			if (j instanceof Jugador) {
				if (((Jugador) j).getDorsal() == numero) {
					return (Jugador) j;
				}
			}
		}
		return null;

	}

	public String[] mostrarLocal() {
		String[] jugadoress = new String[plantillaLocal.size()-1];
		int i = 0;
		for (Componente c : plantillaLocal) {
			if (c instanceof Jugador) {
				jugadoress[i++] = ((Jugador) c).getDorsal()+" - "+c.getNombre()+" P: "+((Jugador)c).getPrecision();
			} else if (c instanceof Entrenador) {
				entrenadorLtxt=c.getNombre();
			}
		}
		
		return jugadoress;
	}
	public String[] mostrarVisitante() {
		String []jugadores=new String[plantillaVisitante.size()];
		int i = 0;
		for (Componente c : plantillaVisitante) {
			if (c instanceof Jugador) {
				jugadores[i++] = ((Jugador) c).getDorsal()+" - "+c.getNombre()+" P: "+((Jugador)c).getPrecision();
			} else if (c instanceof Entrenador) {
				entrenadorVtxt=c.getNombre();
			}
		}
		return jugadores;
	}
	private void cargarEscudos() {
		if(eqLocal.getNombre().equalsIgnoreCase("Real Madrid")) {
			escudoLocal.setIcon(new ImageIcon("resources/escudos/realmadridJugar.png"));
		}
		else if(eqLocal.getNombre().equalsIgnoreCase("FC Barcelona")) {
			escudoLocal.setIcon(new ImageIcon("resources/escudos/barcelonaJugar.png"));
		}
		else if(eqLocal.getNombre().equalsIgnoreCase("Cadiz CF")) {
			escudoLocal.setIcon(new ImageIcon("resources/escudos/cadizJugar.png"));
		}
		else if(eqLocal.getNombre().equalsIgnoreCase("Sevilla FC")) {
			escudoLocal.setIcon(new ImageIcon("resources/escudos/sevillaJugar.png"));
		}
		else if(eqLocal.getNombre().equalsIgnoreCase("Atletico de Madrid")) {
			escudoLocal.setIcon(new ImageIcon("resources/escudos/atleticoJugar.png"));
		}
		if(eqVisitante.getNombre().equalsIgnoreCase("Real Madrid")) {
			escudoVisitante.setIcon(new ImageIcon("resources/escudos/realmadridJugar.png"));
		}
		else if(eqVisitante.getNombre().equalsIgnoreCase("FC Barcelona")) {
			escudoVisitante.setIcon(new ImageIcon("resources/escudos/barcelonaJugar.png"));
		}
		else if(eqVisitante.getNombre().equalsIgnoreCase("Cadiz CF")) {
			escudoVisitante.setIcon(new ImageIcon("resources/escudos/cadizJugar.png"));
		}
		else if(eqVisitante.getNombre().equalsIgnoreCase("Sevilla FC")) {
			escudoVisitante.setIcon(new ImageIcon("resources/escudos/sevillaJugar.png"));
		}
		else if(eqVisitante.getNombre().equalsIgnoreCase("Atletico de Madrid")) {
			escudoVisitante.setIcon(new ImageIcon("resources/escudos/atleticoJugar.png"));
		}
	}
	
}
