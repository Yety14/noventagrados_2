package noventagrados.modelo;

/**
 * Representa una jugada en el juego, con un movimiento de una celda de origen a
 * una celda de destino. Utiliza un record para representar de forma inmutable
 * las celdas de origen y destino.
 * 
 * @author Víctor Vidal Vivanco
 * @author Guillermo López de Arechavaleta Zapatero
 * @version 1.0
 * @since 1.0
 */

/**
 * Constructor que crea una nueva instancia de una Coordenada con los valores
 * especificados para fila y columna.
 *
 * @param origen  La celda de origen de la jugada.
 * @param destino La celda de destino de la jugada.
 */
public record Jugada(Celda origen, Celda destino) {

	/**
	 * Convierte la jugada a una representación textual en formato "origen-destino".
	 *
	 * @return Cadena representando la jugada.
	 */
	public String aTexto() {
		return String.format("%d%d-%d%d", origen.consultarCoordenada().fila(), origen.consultarCoordenada().columna(),
				destino.consultarCoordenada().fila(), destino.consultarCoordenada().columna());
	}
}
