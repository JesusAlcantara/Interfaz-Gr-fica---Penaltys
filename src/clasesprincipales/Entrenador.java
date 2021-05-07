package clasesprincipales;

import java.io.Serializable;

public class Entrenador extends Componente implements Serializable{

	private int edad;
	private String nacionalidad;

	public Entrenador(String nombre, String foto, int edad, String nacionalidad) {
		super(nombre, foto);
		this.edad = edad;
		this.nacionalidad = nacionalidad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	@Override
	public String toString() {
		return "Nombre: " + getNombre() + ", edad: " + getEdad() + ", nacionalidad: " + nacionalidad;
	}

}
