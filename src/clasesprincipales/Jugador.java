package clasesprincipales;

import java.io.Serializable;

public class Jugador extends Componente implements Serializable{
	private int dorsal, precision;
	private String posicion;

	public Jugador() {}

	public Jugador(String nombre, String posicion, int dorsal, int precision) {
		super(nombre);
		this.posicion = posicion;
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

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	@Override
	public String toString() {
		return "Dorsal:" + dorsal + ", Nombre: " + getNombre() + ", Posición: " + posicion + ", Precision: " + precision;
	}

}
