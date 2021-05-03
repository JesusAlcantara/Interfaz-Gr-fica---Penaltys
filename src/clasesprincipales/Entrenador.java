package clasesprincipales;

import java.io.Serializable;

public class Entrenador extends Componente implements Serializable{

	private int edad;

	public Entrenador(String nombre, String foto, int edad) {
		super(nombre, foto);
		this.edad = edad;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String toString() {
		return "Entrenador [nombre= " + getNombre() + ", edad= " + getEdad() + "]";
	}

}
