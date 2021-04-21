package clasesprincipales;

import java.io.Serializable;

public abstract class Componente implements Serializable{
	private String nombre;
	
	public Componente() {
	}

	public Componente(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "Componente [nombre=" + nombre + "]";
	}

}
