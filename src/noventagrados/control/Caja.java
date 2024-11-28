package noventagrados.control;

import noventagrados.util.Color;
import noventagrados.modelo.Pieza;
import noventagrados.util.TipoPieza;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase que representa una caja para almacenar piezas capturadas de un color
 * específico. Cada caja tiene una capacidad máxima y solo puede contener piezas
 * de un color.
 * 
 * @author <a href="vvv1005@alu.ubu.es">Víctor Vidal Vivanco</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @version 1.0
 * @since 1.0
 */
public class Caja {

	/** Lista que almacena las piezas capturadas en esta caja. */
	private List<Pieza> piezas;

	/** El color de las piezas que esta caja puede contener. */
	private Color color;

	/** Capacidad máxima de la caja. */
	private static final int CAPACIDAD_MAXIMA = 7;

	/**
	 * Constructor de la Caja. Inicializa una caja para un color específico.
	 *
	 * @param color El color de las piezas que contendrá la caja
	 */
	public Caja(Color color) {
		this.color = color;
		this.piezas = new ArrayList<>(CAPACIDAD_MAXIMA);
	}

	/**
	 * Añade una pieza a la caja si hay espacio disponible y coincide con el color
	 * de la caja. Si no hay espacio o el color de la pieza no coincide, no se
	 * realiza ninguna acción.
	 *
	 * @param pieza La pieza a añadir a la caja
	 */
	public void añadir(Pieza pieza) {
		if (piezas.size() < CAPACIDAD_MAXIMA && pieza.consultarColor() == this.color) {
			piezas.add(pieza);
		}
	}

	/**
	 * Crea y devuelve una copia profunda de la caja, incluyendo sus piezas. La
	 * copia tiene el mismo color y una lista separada con duplicados de las piezas.
	 *
	 * @return Una nueva instancia de Caja con las mismas propiedades y piezas
	 */
	public Caja clonar() {
		Caja cajaClon = new Caja(this.color);
		for (Pieza pieza : this.piezas) {
			cajaClon.añadir(new Pieza(pieza.consultarTipoPieza(), pieza.consultarColor()));
		}
		return cajaClon;
	}

	/**
	 * Consulta el color de la caja.
	 *
	 * @return El color asociado a la caja
	 */
	public Color consultarColor() {
		return this.color;
	}

	/**
	 * Devuelve una lista con las piezas actualmente en la caja.
	 *
	 * @return Una lista de Pieza con las piezas en la caja
	 */
	public List<Pieza> consultarPiezas() {
		return new ArrayList<>(this.piezas);
	}

	/**
	 * Cuenta el número total de piezas en la caja.
	 *
	 * @return El número de piezas en la caja
	 */
	public int contarPiezas() {
		return piezas.size();
	}

	/**
	 * Cuenta el número de piezas de un tipo específico en la caja.
	 *
	 * @param tipoPieza El tipo de pieza a contar
	 * @return El número de piezas del tipo especificado
	 */
	public int contarPiezas(TipoPieza tipoPieza) {
		int count = 0;
		for (Pieza pieza : piezas) {
			if (pieza.consultarTipoPieza() == tipoPieza) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Compara esta caja con otro objeto. Dos cajas son iguales si tienen el mismo
	 * color y las mismas piezas en la misma cantidad.
	 *
	 * @param obj El objeto con el que se compara
	 * @return true si las cajas son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Caja))
			return false;
		Caja other = (Caja) obj;
		return Objects.equals(piezas, other.piezas) && color == other.color;
	}

	/**
	 * Devuelve el código hash de esta caja. El hash se genera a partir de las
	 * piezas y el color de la caja.
	 *
	 * @return El valor del hash de esta caja
	 */
	@Override
	public int hashCode() {
		return Objects.hash(piezas, color);
	}

	/**
	 * Devuelve una representación en cadena de la caja, incluyendo las piezas y el
	 * color.
	 *
	 * @return Una cadena representando esta caja
	 */
	@Override
	public String toString() {
		return "Caja [piezas=" + piezas + ", color=" + color + "]";
	}
}
