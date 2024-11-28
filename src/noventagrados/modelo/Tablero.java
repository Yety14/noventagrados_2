package noventagrados.modelo;

import noventagrados.util.Coordenada;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa el tablero del juego. El tablero es una cuadrícula de
 * 7x7 celdas.
 * 
 * @author <a href="vvv1005@alu.ubu.es">Víctor Vidal Vivanco</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @version 1.0
 * @since 1.0
 */

public class Tablero {

	/** Número de filas en el tablero. */
	private static final int FILAS = 7;

	/** Número de columnas en el tablero. */
	private static final int COLUMNAS = 7;

	/**
	 * Lista de listas que representa las celdas del tablero.
	 * 
	 * Cada celda puede contener una pieza del juego o estar vacía.
	 */
	private final List<List<Celda>> celdas;

	/**
	 * Constructor que inicializa el tablero con celdas vacías.
	 */
	public Tablero() {
		celdas = new ArrayList<>(FILAS);
		for (int fila = 0; fila < FILAS; fila++) {
			List<Celda> filaCeldas = new ArrayList<>(COLUMNAS);
			for (int columna = 0; columna < COLUMNAS; columna++) {
				filaCeldas.add(new Celda(new Coordenada(fila, columna)));
			}
			celdas.add(filaCeldas);
		}
	}

	/**
	 * Genera una representación textual del tablero.
	 * 
	 * @return una cadena que representa el estado actual del tablero
	 */
	public String aTexto() {
		StringBuilder sb = new StringBuilder();
		String tipoPieza;
		char colorPieza = ' ';
		sb.append("\n");
		for (int fila = 0; fila < FILAS; fila++) {
			sb.append(fila + " ");
			for (int columna = 0; columna < COLUMNAS; columna++) {
				Celda celda = celdas.get(fila).get(columna);
				tipoPieza = celda.estaVacia() ? " -- " : " " + celda.consultarPieza().consultarTipoPieza().toChar();
				colorPieza = celda.estaVacia() ? ' ' : celda.consultarColorDePieza().toChar();
				sb.append(celda.estaVacia() ? " -- " : tipoPieza + colorPieza + " ");
			}
			sb.append("\n");
			if (fila == FILAS - 1) {
				for (int i = 0; i < COLUMNAS; i++) {
					sb.append("   " + i);
				}
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Crea y devuelve una copia profunda del tablero.
	 * 
	 * @return un nuevo Tablero con el mismo estado que este
	 */
	public Tablero clonar() {
		Tablero clon = new Tablero();
		for (int fila = 0; fila < FILAS; fila++) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				clon.celdas.get(fila).set(columna, this.celdas.get(fila).get(columna).clonar());
			}
		}
		return clon;
	}

	/**
	 * Coloca una pieza en la coordenada especificada del tablero.
	 * 
	 * @param pieza      la pieza a colocar
	 * @param coordenada la coordenada donde colocar la pieza
	 */
	public void colocar(Pieza pieza, Coordenada coordenada) {
		if (pieza != null && coordenada != null && estanEnTablero(coordenada)) {
			celdas.get(coordenada.fila()).get(coordenada.columna()).colocar(pieza);
		}
	}

	/**
	 * Consulta la celda en la coordenada especificada.
	 * 
	 * @param coordenada la coordenada de la celda a consultar
	 * @return un clon de la celda en la coordenada especificada, o null si la
	 *         coordenada es inválida
	 */
	public Celda consultarCelda(Coordenada coordenada) {
		if (estanEnTablero(coordenada)) {
			return celdas.get(coordenada.fila()).get(coordenada.columna()).clonar();
		}
		return null;
	}

	/**
	 * Devuelve una lista con todas las celdas del tablero.
	 * 
	 * @return una lista de Celdas con clones de todas las celdas
	 */
	public List<Celda> consultarCeldas() {
		List<Celda> celdasList = new ArrayList<>(FILAS * COLUMNAS);
		for (int fila = 0; fila < FILAS; fila++) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				celdasList.add(celdas.get(fila).get(columna).clonar());
			}
		}
		return celdasList;
	}

	/**
	 * Devuelve el número de columnas del tablero.
	 * 
	 * @return el número de columnas
	 */
	public int consultarNumeroColumnas() {
		return COLUMNAS;
	}

	/**
	 * Devuelve el número de filas del tablero.
	 * 
	 * @return el número de filas
	 */
	public int consultarNumeroFilas() {
		return FILAS;
	}

	/**
	 * Elimina la pieza de la celda en la coordenada especificada.
	 * 
	 * @param coordenada la coordenada de la celda de la que eliminar la pieza
	 */
	public void eliminarPieza(Coordenada coordenada) {
		if (coordenada != null && estanEnTablero(coordenada)) {
			obtenerCelda(coordenada).eliminarPieza();
		}
	}

	/**
	 * Verifica si una coordenada está dentro del tablero.
	 * 
	 * @param coordenada la coordenada a verificar
	 * @return true si la coordenada está dentro del tablero, false en caso
	 *         contrario
	 */
	public boolean estanEnTablero(Coordenada coordenada) {
		int fila = coordenada.fila();
		int columna = coordenada.columna();
		return fila >= 0 && fila < FILAS && columna >= 0 && columna < COLUMNAS;
	}

	/**
	 * Obtiene la celda en la coordenada especificada sin clonarla.
	 * 
	 * @param coordenada la coordenada de la celda a obtener
	 * @return la celda en la coordenada especificada, o null si la coordenada es
	 *         inválida
	 */
	Celda obtenerCelda(Coordenada coordenada) {
		return estanEnTablero(coordenada) ? celdas.get(coordenada.fila()).get(coordenada.columna()) : null;
	}

	/**
	 * Compara este tablero con otro objeto para determinar si son iguales.
	 * 
	 * @param obj el objeto a comparar con este tablero
	 * @return true si son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Tablero otroTablero = (Tablero) obj;
		for (int fila = 0; fila < FILAS; fila++) {
			for (int columna = 0; columna < COLUMNAS; columna++) {
				if (!this.celdas.get(fila).get(columna).equals(otroTablero.celdas.get(fila).get(columna))) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Genera un código hash para el tablero.
	 * 
	 * @return el código hash del tablero
	 */
	@Override
	public int hashCode() {
		int result = 1;
		for (List<Celda> fila : celdas) {
			for (Celda celda : fila) {
				result = 31 * result + (celda != null ? celda.hashCode() : 0);
			}
		}
		return result;
	}

	/**
	 * Devuelve una representación en cadena del tablero.
	 * 
	 * @return una cadena que representa el estado del tablero
	 */
	@Override
	public String toString() {
		return aTexto();
	}
}
