package clasesprincipales;

import java.io.Serializable;

public class Jugador extends Componente implements Serializable{
	private int dorsal, precision;

	public Jugador() {

	}

	public Jugador(String nombre, int dorsal, int precision) {
		super(nombre);
		this.dorsal = dorsal;
		this.precision = precision;
	}

	public int getDorsal() {
		return dorsal;
	}

	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	@Override
	public String toString() {
		return "Dorsal:" + dorsal + ", Nombre: " + getNombre() + ", Precision: " + precision;
	}

}
