package noventagrados.control;

import noventagrados.modelo.Tablero;

import java.util.List;

import noventagrados.modelo.Celda;
import noventagrados.util.Sentido;
import noventagrados.util.Coordenada;
import noventagrados.util.Color;
import noventagrados.util.TipoPieza;
import noventagrados.modelo.Pieza;

/**
 * Clase genérica para consultar información del tablero. Proporciona métodos
 * para analizar la posición de piezas, su cantidad, y sus relaciones en el
 * tablero.
 *
 * @param <T> Tipo genérico que extiende la clase Tablero
 * 
 * @author <a href="vvv1005@alu.ubu.es">Víctor Vidal Vivanco</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta Zapatero</a>
 * @version 1.0
 * @since 1.0
 */
public class TableroConsultor<T extends Tablero> {

	/** El tablero que este consultor está observando. */
	private T tablero;

	/**
	 * Constructor del TableroConsultor.
	 * 
	 * @param tablero El tablero a consultar
	 */
	public TableroConsultor(T tablero) {
		this.tablero = tablero;
	}

	/**
	 * Determina el sentido cardinal del movimiento entre dos coordenadas en el
	 * tablero. Un movimiento válido debe ser estrictamente horizontal o vertical.
	 *
	 * @param origen  Coordenada de inicio
	 * @param destino Coordenada de fin
	 * @return El sentido del movimiento (Norte, Sur, Este, Oeste) o null si no es
	 *         válido
	 */
	public Sentido calcularSentido(Coordenada origen, Coordenada destino) {
		if (origen.fila() == destino.fila()) {
			return origen.columna() < destino.columna() ? Sentido.HORIZONTAL_E : Sentido.HORIZONTAL_O;
		} else if (origen.columna() == destino.columna()) {
			return origen.fila() < destino.fila() ? Sentido.VERTICAL_S : Sentido.VERTICAL_N;
		}
		return null;
	}

	/**
	 * Consulta la distancia horizontal entre dos coordenadas.
	 * 
	 * @param origen  Coordenada de origen
	 * @param destino Coordenada de destino
	 * @return La distancia horizontal, o -1 si no están en la misma fila
	 */
	public int consultarDistanciaEnHorizontal(Coordenada origen, Coordenada destino) {
		if (origen.fila() == destino.fila()) {
			return Math.abs(origen.columna() - destino.columna());
		}
		return -1;
	}

	/**
	 * Consulta la distancia vertical entre dos coordenadas.
	 * 
	 * @param origen  Coordenada de origen
	 * @param destino Coordenada de destino
	 * @return La distancia vertical, o -1 si no están en la misma columna
	 */
	public int consultarDistanciaEnVertical(Coordenada origen, Coordenada destino) {
		if (origen.columna() == destino.columna()) {
			return Math.abs(origen.fila() - destino.fila());
		}
		return -1;
	}

	/**
	 * Cuenta la cantidad de piezas de un tipo y color específicos presentes en todo
	 * el tablero.
	 *
	 * @param tipoPieza El tipo de pieza a contar (e.g., REINA, PEON)
	 * @param color     El color de las piezas a contar
	 * @return El número total de piezas que cumplen las condiciones
	 */
	public int consultarNumeroPiezas(TipoPieza tipoPieza, Color color) {
		int count = 0;
		List<Celda> celdasList = tablero.consultarCeldas();
		for (Celda celda : celdasList) {
			if (!celda.estaVacia()) {
				Pieza pieza = celda.consultarPieza();
				if (pieza.consultarTipoPieza() == tipoPieza && pieza.consultarColor() == color) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * Cuenta el número de piezas en la fila de la coordenada dada.
	 * 
	 * @param coordenada La coordenada de referencia
	 * @return El número de piezas en la fila
	 */
	public int consultarNumeroPiezasEnHorizontal(Coordenada coordenada) {
		int count = 0;
		int fila = coordenada.fila();
		for (int columna = 0; columna < tablero.consultarNumeroColumnas(); columna++) {
			Celda celda = tablero.consultarCelda(new Coordenada(fila, columna));
			if (!celda.estaVacia()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Cuenta el número de piezas en la columna de la coordenada dada.
	 * 
	 * @param coordenada La coordenada de referencia
	 * @return El número de piezas en la columna
	 */
	public int consultarNumeroPiezasEnVertical(Coordenada coordenada) {
		int count = 0;
		int columna = coordenada.columna();
		for (int fila = 0; fila < tablero.consultarNumeroFilas(); fila++) {
			Celda celda = tablero.consultarCelda(new Coordenada(fila, columna));
			if (!celda.estaVacia()) {
				count++;
			}
		}
		return count;
	}

	/**
	 * Verifica si la reina de un color específico está situada en el centro del
	 * tablero. El centro se calcula como la celda en las coordenadas medias del
	 * tablero.
	 *
	 * @param color El color de la reina que se desea verificar
	 * @return true si la reina está en el centro, false en caso contrario
	 */
	public boolean estaReinaEnElCentro(Color color) {
		Celda celdaCentro = tablero.consultarCelda(
				new Coordenada(tablero.consultarNumeroFilas() / 2, tablero.consultarNumeroColumnas() / 2));
		if (!celdaCentro.estaVacia()) {
			return celdaCentro.consultarPieza().consultarColor() == color
					&& celdaCentro.consultarPieza().consultarTipoPieza() == TipoPieza.REINA;
		}
		return false;
	}

	/**
	 * Busca la existencia de una reina de un color específico en el tablero.
	 *
	 * @param color El color de la reina a buscar
	 * @return true si hay al menos una reina del color especificado, false si no
	 */
	public boolean hayReina(Color color) {
		for (int fila = 0; fila < tablero.consultarNumeroFilas(); fila++) {
			for (int columna = 0; columna < tablero.consultarNumeroColumnas(); columna++) {
				Celda celda = tablero.consultarCelda(new Coordenada(fila, columna));
				if (!celda.estaVacia()) {
					Pieza pieza = celda.consultarPieza();
					if (pieza.consultarColor() == color && pieza.consultarTipoPieza() == TipoPieza.REINA) {
						return true;
					}
				}
			}
		}
		return false;
	}

	/**
	 * Genera una representación en cadena del TableroConsultor.
	 * 
	 * @return Una cadena que representa el estado del TableroConsultor
	 */
	@Override
	public String toString() {
		return "TableroConsultor [tablero=" + tablero + "]";
	}
}//