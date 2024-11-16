package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import java.util.Date;

/**
 * Excepción lanzada cuando se intenta seleccionar una opción no disponible.
 * 
 * @author Víctor Vidal Vivanco
 * @author Guillermo López de Arechavaleta Zapatero
 * @version 1.0
 * @since 1.0
 */

public interface MecanismoDeDeshacer {
	Arbitro consultarArbitroActual();

	int consultarNumeroJugadasEnHistorico();

	void deshacerJugada();

	void hacerJugada(Jugada jugada);

	Date obtenerFechaInicio();
}
