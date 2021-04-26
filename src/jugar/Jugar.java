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
import javax.swing.Icon;
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
	private Equipo equipo;
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
	ImageIcon gol=new ImageIcon("resources/escudos/realmadrid.png");
	String [] equipos= {"Real Madrid","FC Barcelona","Atletico de Madrid","Sevilla FC","Cadiz CF"};
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Jugar() {
		super("Jugar");
		setSize(800,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		golesLocal = 0;
		golesVisitante = 0;
		numLanzamientos=Opciones.numLanzamientos;
		nombreLocal=Opciones.nombreLocal;
		escribirOLeer(nombreLocal);
		escribirOLeer(nombreLocal);
		eqLocal=equipo;
		plantillaLocal=eqLocal.getPlantilla();
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
			try {
			if(e.getSource()==lanzarBTN) {
				if (contador!=numLanzamientos) {
				int lanzamiento=lanzamiento(buscarJugador(dorsalLanzador));
				if(lanzamiento==1) {
					golesLocal++;
					JOptionPane.showMessageDialog(Jugar.this, "Gooool","Gol",JOptionPane.INFORMATION_MESSAGE/*,gol*/);
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
							plantillaVisitante=eqVisitante.getPlantilla();
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
							resultadoLBL.setText("RESULTADO: "+golesLocal+"-"+golesVisitante);
						}
					}
				}
				
				}
			}catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(Jugar.this, "Seleccione un jugador de la plantilla","Error",JOptionPane.ERROR_MESSAGE);
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

	public void RealMadrid() throws FileNotFoundException, IOException {
		ArrayList<Componente> plantillaRealMadrid = new ArrayList<>();
		Jugador j1 = new Jugador("Thibaut Courtois","resources/plantilla/realmadrid/courtois.png","Portero", 1, 40);
		Jugador j2 = new Jugador("Dani Carvajal","resources/plantilla/realmadrid/courtois.png","	Defensa", 2, 70);
		Jugador j3 = new Jugador("Raphael Varane","resources/plantilla/realmadrid/courtois.png","Defensa", 3, 60);
		Jugador j4 = new Jugador("Sergio Ramos","resources/plantilla/realmadrid/courtois.png","Defensa", 4, 90);
		Jugador j5 = new Jugador("Ferland Mendy","resources/plantilla/realmadrid/courtois.png","Defensa", 5, 60);
		Jugador j6 = new Jugador("Casemiro","resources/plantilla/realmadrid/courtois.png","Centrocampista", 6, 70);
		Jugador j7 = new Jugador("Eden Hazard","resources/plantilla/realmadrid/courtois.png","Extremo", 7, 95);
		Jugador j8 = new Jugador("Toni Kroos","resources/plantilla/realmadrid/courtois.png","Centrocampista", 8, 95);
		Jugador j9 = new Jugador("Karim Benzema","resources/plantilla/realmadrid/courtois.png","Delantero", 9, 90);
		Jugador j10 = new Jugador("Luka Modric","resources/plantilla/realmadrid/courtois.png","Centrocampista", 10, 90);
		Jugador j11 = new Jugador("Marco Asensio","resources/plantilla/realmadrid/courtois.png","Extremo", 11, 85);
		Entrenador e1 = new Entrenador("Zidane","resources/plantilla/realmadrid/courtois.png", 45);
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
		Equipo eq1 = new Equipo("Real Madrid","", "Madrid", "Santiago Bernabeu", plantillaRealMadrid);
		serializar(eq1,"Real Madrid");
	}

	public void Barcelona() throws FileNotFoundException, IOException {
		ArrayList<Componente> plantillaBarcelona = new ArrayList<>();
		Jugador j1 = new Jugador("ter Stegen","","", 1, 40);
		Jugador j2 = new Jugador("Dest","resources/plantilla/realmadrid/courtois.png","", 2, 60);
		Jugador j3 = new Jugador("Pique Gerard","resources/plantilla/realmadrid/courtois.png","", 3, 70);
		Jugador j4 = new Jugador("Lenglet Clement","resources/plantilla/realmadrid/courtois.png","", 4, 70);
		Jugador j5 = new Jugador("Alba Jordi","resources/plantilla/realmadrid/courtois.png","", 5, 60);
		Jugador j6 = new Jugador("Busquets Sergio","resources/plantilla/realmadrid/courtois.png","", 6, 85);
		Jugador j7 = new Jugador("Pedri","resources/plantilla/realmadrid/courtois.png","", 7, 80);
		Jugador j8 = new Jugador("de Jong Frenkie","resources/plantilla/realmadrid/courtois.png","", 8, 80);
		Jugador j9 = new Jugador("Griezmann Antoine","resources/plantilla/realmadrid/courtois.png","", 9, 70);
		Jugador j10 = new Jugador("Messi Lionel","resources/plantilla/realmadrid/courtois.png","", 10, 90);
		Jugador j11 = new Jugador("Dembele Ousmane","resources/plantilla/realmadrid/courtois.png","", 11, 85);
		Entrenador e1 = new Entrenador("Koeman","resources/plantilla/realmadrid/courtois.png", 45);
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
		Equipo eq1 = new Equipo("FC Barcelona","resources/plantilla/realmadrid/courtois.png", "Barcelona", "Camp Nou", plantillaBarcelona);
		serializar(eq1,"FC Barcelona");
	}

	public void Cadiz() throws FileNotFoundException, IOException {
		ArrayList<Componente> plantillaCadiz = new ArrayList<>();
		Jugador j1 = new Jugador("Ledesma Jeremias","resources/plantilla/realmadrid/courtois.png","", 1, 40);
		Jugador j2 = new Jugador("Isaac Carcelen","resources/plantilla/realmadrid/courtois.png","", 2, 60);
		Jugador j3 = new Jugador("Fali","resources/plantilla/realmadrid/courtois.png","", 3, 70);
		Jugador j4 = new Jugador("Juan Cala","resources/plantilla/realmadrid/courtois.png","", 4, 70);
		Jugador j5 = new Jugador("Pacha Espino","resources/plantilla/realmadrid/courtois.png","", 5, 60);
		Jugador j6 = new Jugador("Jose mari","resources/plantilla/realmadrid/courtois.png","", 6, 85);
		Jugador j7 = new Jugador("Salvi","resources/plantilla/realmadrid/courtois.png","", 7, 80);
		Jugador j8 = new Jugador("Alex Fernandez","resources/plantilla/realmadrid/courtois.png","", 8, 90);
		Jugador j9 = new Jugador("Choco Lozano","resources/plantilla/realmadrid/courtois.png","", 9, 70);
		Jugador j10 = new Jugador("Negredo","resources/plantilla/realmadrid/courtois.png","", 10, 90);
		Jugador j11 = new Jugador("Perea","resources/plantilla/realmadrid/courtois.png","", 11, 80);
		Entrenador e1 = new Entrenador("Cervera","resources/plantilla/realmadrid/courtois.png", 45);
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
		Equipo eq1 = new Equipo("Cadiz CF","resources/plantilla/realmadrid/courtois.png", "Cadiz", "Ramon de Carranza", plantillaCadiz);
		serializar(eq1,"Cadiz CF");
	}

	public void AtleticoDeMadrid() throws FileNotFoundException, IOException {
		ArrayList<Componente> plantillaAtleticoDeMadrid = new ArrayList<>();
		Jugador j1 = new Jugador("Oblak","resources/plantilla/Atletico Madrid/oblak.png","", 1, 40);
		Jugador j2 = new Jugador("Trippier","resources/plantilla/Atletico Madrid/trippier.png","", 2, 60);
		Jugador j3 = new Jugador("Gimenez","resources/plantilla/Atletico Madrid/gimenez.png","", 3, 70);
		Jugador j4 = new Jugador("Savic","resources/plantilla/Atletico Madrid/savic.png","", 4, 70);
		Jugador j5 = new Jugador("Lodi","resources/plantilla/Atletico Madrid/lodi.png","", 5, 60);
		Jugador j6 = new Jugador("Koke","resources/plantilla/Atletico Madrid/koke.png","", 6, 80);
		Jugador j7 = new Jugador("Joao Felix","resources/plantilla/Atletico Madrid/joao.png","", 7, 90);
		Jugador j8 = new Jugador("Marcos Llorente","resources/plantilla/Atletico Madrid/llorente.png","", 8, 80);
		Jugador j9 = new Jugador("Luis Suarez","resources/plantilla/Atletico Madrid/suarez.png","", 9, 90);
		Jugador j10 = new Jugador("Lemar","resources/plantilla/Atletico Madrid/lemar.png","", 10, 90);
		Jugador j11 = new Jugador("Yannick Carrasco","resources/plantilla/Atletico Madrid/carrasco.png","", 11, 90);
		Entrenador e1 = new Entrenador("Simeone","resources/plantilla/Atletico Madrid/simeone.png", 45);
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
		Equipo eq1 = new Equipo("Atletico de Madrid","resources/escudos/atleticoJugar.png", "Madrid", "Wanda Metropolitano", plantillaAtleticoDeMadrid);
		serializar(eq1,"Atletico de Madrid");
	}

	public void Sevilla() throws FileNotFoundException, IOException {
		ArrayList<Componente> plantillaSevilla = new ArrayList<>();
		Jugador j1 = new Jugador("Bono","resources/plantilla/realmadrid/courtois.png","", 1, 40);
		Jugador j2 = new Jugador("Jesus Navas","resources/plantilla/realmadrid/courtois.png","", 2, 80);
		Jugador j3 = new Jugador("Diego Carlos","resources/plantilla/realmadrid/courtois.png","", 3, 70);
		Jugador j4 = new Jugador("Kounde","resources/plantilla/realmadrid/courtois.png","", 4, 70);
		Jugador j5 = new Jugador("Acuña","resources/plantilla/realmadrid/courtois.png","", 5, 60);
		Jugador j6 = new Jugador("Fernando","resources/plantilla/realmadrid/courtois.png","", 6, 80);
		Jugador j7 = new Jugador("Suso","resources/plantilla/realmadrid/courtois.png","", 7, 80);
		Jugador j8 = new Jugador("Jordan","resources/plantilla/realmadrid/courtois.png","", 8, 80);
		Jugador j9 = new Jugador("En-Nesyri","resources/plantilla/realmadrid/courtois.png","", 9, 70);
		Jugador j10 = new Jugador("Rakitic","resources/plantilla/realmadrid/courtois.png","", 10, 90);
		Jugador j11 = new Jugador("Ocampos","resources/plantilla/realmadrid/courtois.png","", 11, 85);
		Entrenador e1 = new Entrenador("Lopetegui","resources/plantilla/realmadrid/courtois.png", 45);
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
		Equipo eq1 = new Equipo("Sevilla FC","resources/plantilla/realmadrid/courtois.png", "Sevilla", "Ramon Sanchez Pizjuan", plantillaSevilla);
		serializar(eq1,"Sevilla FC");
	}

	private void escribirOLeer(String nombre) {
		File f = new File("resources/"+nombre);
		if (f.exists() && f.isFile()) {
			try {
				equipo = deserializar(nombre);
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
			try {
				if(nombre.equalsIgnoreCase("Real Madrid")) {
					RealMadrid();
				}
				else if(nombre.equalsIgnoreCase("FC Barcelona")) {
					Barcelona();
				}
				else if(nombre.equalsIgnoreCase("Cadiz CF")) {
					Cadiz();
				}
				else if(nombre.equalsIgnoreCase("Sevilla FC")) {
					Sevilla();
				}
				else if(nombre.equalsIgnoreCase("Atletico de Madrid")) {
					AtleticoDeMadrid();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void serializar(Equipo eq,String nombre) throws FileNotFoundException, IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("resources/"+nombre));
		os.writeObject(eq);
		os.close();
	}

	private Equipo deserializar(String nombre) throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream("resources/"+nombre));
		return (Equipo) is.readObject();
	}

	private void cargarVisitante() {
		String nvisitante;
		do {
			int visitante = (int) (Math.random() * 6 + 1);
			nvisitante=cargarEquipo(visitante);
		} while (nvisitante == eqLocal.getNombre());
		escribirOLeer(nvisitante);
		escribirOLeer(nvisitante);
		eqVisitante=equipo;
		plantillaVisitante=eqVisitante.getPlantilla();
		
	}

	public String cargarEquipo(int numero) {
		for(String e: equipos){
			if (numero == 1) {
				if(e.equalsIgnoreCase("Real Madrid")) {
					return e;
				}
			} else if (numero == 2) {
				if(e.equalsIgnoreCase("FC Barcelona")) {
					return e;
				}
			} else if (numero == 3) {
				if(e.equalsIgnoreCase("Cadiz CF")) {
					return e;
				}
			} else if (numero == 4) {
				if(e.equalsIgnoreCase("Sevilla FC")) {
					return e;
				}
			} else {
				if(e.equalsIgnoreCase("Atletico de Madrid")) {
					return e;
					}
		}
		}
		return null;
	}
	public Equipo cargarEquipo() {
		
		
		return equipo;
		
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
