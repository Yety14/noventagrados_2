package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import java.util.Date;

public interface MecanismoDeDeshacer {
	Arbitro consultarArbitroActual();

	int consultarNumeroJugadasEnHistorico();

	void deshacerJugada();

	void hacerJugada(Jugada jugada);

	Date obtenerFechaInicio();
}
