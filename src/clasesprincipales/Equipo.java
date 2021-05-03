package clasesprincipales;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipo implements Serializable{

	private String nombre;
	private String ciudad;
	private String estadio;
	private ArrayList<Componente> plantilla;
	private String foto;

	public Equipo(String nombre, String ciudad, String estadio, String foto, ArrayList<Componente> plantilla) {
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.estadio = estadio;
		this.foto = foto;
		this.plantilla = plantilla;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getEstadio() {
		return estadio;
	}

	public void setEstadio(String estadio) {
		this.estadio = estadio;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public ArrayList<Componente> getPlantilla() {
		return plantilla;
	}

	public void setPlantilla(ArrayList<Componente> plantilla) {
		this.plantilla = plantilla;
	}

	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", ciudad=" + ciudad + ", estadio=" + estadio + ", plantilla=" + plantilla
				+ "]";
	}

	public void insertarJugador(Jugador j) {
		getPlantilla().add(j);
	}

}
