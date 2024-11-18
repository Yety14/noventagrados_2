package noventagrados.modelo;

import java.util.Objects;
import noventagrados.util.Color;
import noventagrados.util.TipoPieza;

/**
 * Representa una pieza del juego con un color y tipo.
 * 
 * @author Víctor Vidal Vivanco
 * @author Guillermo López de Arechavaleta Zapatero
 * @version 1.0
 * @since 1.0
 */
public class Pieza {

	/** Color de la pieza. */
	private Color color;

	/** Tipo de la pieza. */
	private TipoPieza tipoPieza;

	/**
	 * Constructor para crear una pieza con tipo y color especificados.
	 *
	 * @param tipoPieza el tipo de la pieza
	 * @param color     el color de la pieza
	 */
	public Pieza(TipoPieza tipoPieza, Color color) {
		this.color = color;
		this.tipoPieza = tipoPieza;
	}

	/**
	 * Convierte la pieza a una representación textual.
	 *
	 * @return La representación en texto de la pieza.
	 */
	public String aTexto() {
		return toString();
	}

	/**
	 * Crea una copia profunda de la pieza.
	 *
	 * @return Nueva instancia de Pieza con las mismas propiedades.
	 */
	public Pieza clonar() {
		return new Pieza(this.tipoPieza, this.color);
	}

	/**
	 * Consulta el color de la pieza.
	 *
	 * @return El color de la pieza.
	 */
	public Color consultarColor() {
		return color;
	}

	/**
	 * Consulta el tipo de la pieza.
	 *
	 * @return El tipo de la pieza.
	 */
	public TipoPieza consultarTipoPieza() {
		return tipoPieza;
	}

	/**
	 * Genera un código hash para la pieza.
	 *
	 * @return Código hash basado en el color y tipo.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(color, tipoPieza);
	}

	/**
	 * Compara esta pieza con otra para determinar si son iguales.
	 *
	 * @param obj el objeto a comparar con esta pieza.
	 * @return true si son iguales, false si no lo son.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pieza other = (Pieza) obj;
		return Objects.equals(color, other.color) && Objects.equals(tipoPieza, other.tipoPieza);
	}

	/**
	 * Devuelve una representación en cadena de la pieza.
	 *
	 * @return Descripción de la pieza con su color y tipo.
	 */
	@Override
	public String toString() {
		return "Pieza [color=" + color + ", tipoPieza=" + tipoPieza + "]";
	}
}
