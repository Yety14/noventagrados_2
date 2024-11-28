package noventagrados.modelo;

import noventagrados.util.Coordenada;
import noventagrados.util.TipoPieza;
import noventagrados.util.Color;
import java.util.Objects;

/**
 * Representa una celda del tablero de juego. Cada celda tiene una coordenada y
 * puede contener una pieza.
 * 
 * La celda puede estar vacía o contener una pieza de tipo específico. También
 * incluye información sobre la coordenada en el tablero, facilitando su
 * ubicación.
 * 
 * @author <a href="vvv1005@alu.ubu.es">Víctor Vidal Vivanco</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @version 1.0
 * @since 1.0
 */
public class Celda {

	/** La coordenada de la celda en el tablero. */
	private Coordenada coordenada;

	/**
	 * El tipo de la pieza en la celda. Parece no utilizarse, considerar su
	 * eliminación. Este campo no es necesario si la celda solo almacena una pieza,
	 * y la pieza tiene el tipo asociado.
	 */
	private TipoPieza tipoPieza;

	/** La pieza que ocupa la celda, null si está vacía. */
	private Pieza pieza;

	/**
	 * Construye una nueva celda con la coordenada especificada. La celda se
	 * inicializa vacía (sin pieza).
	 *
	 * @param coordenada La coordenada de la celda en el tablero.
	 */
	public Celda(Coordenada coordenada) {
		this.coordenada = new Coordenada(coordenada.fila(), coordenada.columna());
		this.pieza = null;
	}

	/**
	 * Crea y devuelve una copia profunda de la celda.
	 *
	 * @return Una nueva instancia de Celda con las mismas propiedades que esta. Si
	 *         la celda tiene una pieza, se clona también la pieza.
	 */
	public Celda clonar() {
		Celda nueva = new Celda(new Coordenada(coordenada.fila(), coordenada.columna()));
		if (this.pieza != null) {
			// Clonación de la pieza si existe en la celda
			nueva.colocar(new Pieza(this.pieza.consultarTipoPieza(), this.pieza.consultarColor()));
		}
		return nueva;
	}

	/**
	 * Coloca una pieza en la celda.
	 *
	 * @param pieza La pieza a colocar en la celda.
	 */
	public void colocar(Pieza pieza) {
		this.pieza = pieza;
	}

	/**
	 * Consulta el color de la pieza en la celda.
	 *
	 * @return El color de la pieza, o null si la celda está vacía.
	 */
	public Color consultarColorDePieza() {
		if (!estaVacia()) {
			return pieza.consultarColor();
		}
		return null;
	}

	/**
	 * Consulta la coordenada de la celda.
	 *
	 * @return Una nueva instancia de Coordenada con la posición de la celda.
	 */
	public Coordenada consultarCoordenada() {
		return new Coordenada(coordenada.fila(), coordenada.columna());
	}

	/**
	 * Consulta la pieza en la celda.
	 *
	 * @return Una copia de la pieza en la celda, o null si está vacía.
	 */
	public Pieza consultarPieza() {
		if (!estaVacia()) {
			// Clonación de la pieza para evitar modificaciones directas.
			return pieza.clonar();
		}
		return null;
	}

	/**
	 * Elimina la pieza de la celda, dejándola vacía.
	 */
	public void eliminarPieza() {
		this.pieza = null;
	}

	/**
	 * Verifica si la celda está vacía (sin pieza).
	 *
	 * @return true si la celda está vacía, false en caso contrario.
	 */
	public boolean estaVacia() {
		return pieza == null;
	}

	/**
	 * Compara esta celda con otro objeto para determinar si son iguales.
	 * 
	 * Dos celdas son iguales si tienen la misma coordenada y el mismo contenido de
	 * pieza (si no están vacías).
	 *
	 * @param obj El objeto a comparar con esta celda.
	 * @return true si son iguales, false en caso contrario.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return Objects.equals(coordenada, other.coordenada) && Objects.equals(pieza, other.pieza)
				&& tipoPieza == other.tipoPieza;
	}

	/**
	 * Genera un código hash para la celda.
	 * 
	 * El código hash se genera a partir de la coordenada y la pieza de la celda,
	 * permitiendo una comparación eficiente en colecciones como HashMap o HashSet.
	 *
	 * @return El código hash de la celda.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(coordenada, pieza);
	}

	/**
	 * Devuelve una representación en cadena de la celda.
	 *
	 * @return Una cadena que describe la celda, incluyendo su coordenada y pieza
	 *         (si existe).
	 */
	@Override
	public String toString() {
		return "Celda [coordenada=" + coordenada + ", pieza=" + (pieza != null ? pieza : "vacía") + "]";
	}
}