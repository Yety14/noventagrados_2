package noventagrados.util;

import noventagrados.util.TipoPieza;

/**
 * Enumeración que representa los tipos de piezas en el juego.
 *
 * @author <a href="vvv1005@alu.ubu.es">Víctor Vidal Vivanco</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta Zapatero</a>
 * @version 1.0
 * @since 1.0
 */

public enum TipoPieza {

	/** Representa el peón, una pieza defensora. */
	PEON('P'),

	/** Representa la reina, la pieza principal del juego. */
	REINA('R');

	/** Carácter que representa el tipo de pieza. */
	private char caracter;

	/**
	 * Constructor privado para la enumeración TipoPieza.
	 *
	 * @param caracter El carácter que representa el tipo de pieza
	 */
	private TipoPieza(char caracter) {
		this.caracter = caracter;
	}

	/**
	 * Devuelve el carácter que representa este tipo de pieza.
	 *
	 * @return El carácter asociado al tipo de pieza
	 */
	public char toChar() {
		return caracter;
	}
}