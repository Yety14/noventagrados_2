package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import java.util.Date;

/**
 * Interfaz que define el mecanismo de deshacer jugadas en un juego.
 * 
 * Esta interfaz establece los métodos necesarios para gestionar un mecanismo de
 * deshacer jugadas en un juego de noventa grados, permitiendo realizar
 * operaciones de retroceso (deshacer jugadas) y avance (hacer jugadas) dentro
 * del historial del juego.
 * 
 * @author <a href="vvv1005@alu.ubu.es">Víctor Vidal Vivanco</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @version 1.0
 * @since 1.0
 */
public interface MecanismoDeDeshacer {

	/**
	 * Consulta el árbitro actual del juego, que refleja el estado del tablero y las
	 * jugadas realizadas hasta el momento.
	 * 
	 * @return El árbitro actual con el estado actualizado del juego.
	 */
	Arbitro consultarArbitroActual();

	/**
	 * Consulta el número de jugadas almacenadas en el historial de jugadas.
	 * 
	 * @return El número de jugadas en el historial.
	 */
	int consultarNumeroJugadasEnHistorico();

	/**
	 * Deshace la última jugada realizada en el juego.
	 * 
	 * Este método elimina la última jugada del historial y restaura el estado del
	 * tablero y las piezas a como estaban antes de esa jugada.
	 */
	void deshacerJugada();

	/**
	 * Realiza una nueva jugada en el juego.
	 * 
	 * Este método aplica una jugada al juego y la almacena en el historial de
	 * jugadas, actualizando el estado del tablero.
	 * 
	 * @param jugada La jugada que se va a realizar en el juego.
	 */
	void hacerJugada(Jugada jugada);

	/**
	 * Obtiene la fecha de inicio del mecanismo de deshacer.
	 * 
	 * @return La fecha y hora en la que se inició el mecanismo de deshacer.
	 */
	Date obtenerFechaInicio();
}
