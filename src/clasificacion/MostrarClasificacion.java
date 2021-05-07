package clasificacion;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import clasesprincipales.Equipo;

public class MostrarClasificacion extends JFrame{
	
	private JLabel lblLider;
	private JLabel lblEscudo;
	private JTable table;
	Equipo Lider;
	Icon escudo;
	
	public MostrarClasificacion() throws IOException, ClassNotFoundException {
		super("Clasificacion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("resources/icono/icono.png"));
		setSize(550,400);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		actualizar();
		establecerLider();
		escudo= new ImageIcon(Lider.getFoto());
		
		lblLider = new JLabel("Líder");
		lblLider.setHorizontalAlignment(SwingConstants.CENTER);
		lblLider.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblLider);
		
		lblEscudo = new JLabel();
		lblEscudo.setIcon(escudo);
		add(lblEscudo);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		rellenarTabla();
		add(new JScrollPane(table));
		
		
		setVisible(true);
	}
	
	public static void actualizar() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("resources/Historial.txt"));
		HashMap<String,Integer> puntos=new HashMap<>();
		String [] fichero = null;
		String siguiente=br.readLine();
		while(siguiente!=null) {
			fichero=siguiente.split(";");
			if(Integer.parseInt(fichero[2])>Integer.parseInt(fichero[3])) {
				if(puntos.containsKey(fichero[0])) {
					puntos.put(fichero[0],(puntos.get(fichero[0]))+3);
				}
				else {
					puntos.put(fichero[0],3);
				}
			}
			else {
				if(puntos.containsKey(fichero[1])) {
					puntos.put(fichero[1],(puntos.get(fichero[1])+3));
				}
				else 
					puntos.put(fichero[1],3);
				
			}
				
			siguiente=br.readLine();
		}
		br.close();

		Map<String, Integer> puntos2 = puntos.entrySet()
                .stream()
                .sorted((Map.Entry.<String, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	
		FileWriter fw=new FileWriter("Clasificacion.txt");
		
		Iterator<String> it=puntos2.keySet().iterator();
		
		while(it.hasNext()) {
			String key=(String) it.next();
			fw.write(key+";"+puntos.get(key)+"\n");
		}
		fw.close();
	}
	
	public void establecerLider() throws IOException, ClassNotFoundException{
		BufferedReader br = new BufferedReader(new FileReader("Clasificacion.txt"));
		String siguiente = br.readLine();
		String[] lider= null;
		lider=siguiente.split(";");
		Lider=deserializar(new File("resources/"+lider[0]));

	}
	public static Equipo deserializar(File nombre) throws FileNotFoundException, IOException, ClassNotFoundException {
		@SuppressWarnings("resource")
		ObjectInputStream is = new ObjectInputStream(new FileInputStream(nombre));
		return (Equipo) is.readObject();
	}
	
	public void rellenarTabla() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("Clasificacion.txt"));
		String siguiente = br.readLine();
		DefaultTableModel dt=new DefaultTableModel();
		String[] columns= {"Nombre","Puntos"};
		dt.setColumnIdentifiers(columns);
		while(siguiente!=null) {
			String[] data = siguiente.split(";");
			Object[] fila=new Object[2];
			fila[0]=data[0];
			fila[1]=data[1];
			dt.addRow(fila);
			siguiente=br.readLine();
			}
		table.setModel(dt);
	}
}
