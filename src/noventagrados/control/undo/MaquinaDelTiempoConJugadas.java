package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la funcionalidad de deshacer y rehacer jugadas, guardando un
 * historial de jugadas. Permite regresar a estados previos del juego.
 * 
 * @author <a href="vvv1005@alu.ubu.es">Víctor Vidal Vivanco</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @version 1.0
 * @since 1.0
 */
public class MaquinaDelTiempoConJugadas extends MecanismoDeDeshacerAbstracto {

	/** Historial de las jugadas realizadas en el juego. */
	private List<Jugada> historicoJugadas;

	/**
	 * Constructor que inicializa el historial de jugadas, tableros y la fecha de
	 * inicio.
	 * 
	 * @param fecha Fecha de inicio de la partida
	 */
	public MaquinaDelTiempoConJugadas(Date fecha) {
		super(fecha);
		this.historicoJugadas = new ArrayList<>();
	}

	/**
	 * Consulta el árbitro actual después de aplicar todas las jugadas realizadas,
	 * retornando el árbitro actual.
	 * 
	 * @return El árbitro con el estado actual del juego
	 */
	@Override
	public Arbitro consultarArbitroActual() {
		Arbitro nuevoArbitro = new Arbitro(new Tablero());
		nuevoArbitro.colocarPiezasConfiguracionInicial();

		for (int i = 0; i < historicoJugadas.size(); i++) {
			nuevoArbitro.empujar(historicoJugadas.get(i));
			nuevoArbitro.cambiarTurno();
		}

		return nuevoArbitro;
	}

	/**
	 * Consulta la cantidad de jugadas realizadas hasta el momento.
	 * 
	 * @return El número de jugadas en el historial
	 */
	@Override
	public int consultarNumeroJugadasEnHistorico() {
		return historicoJugadas.size();
	}

	/**
	 * Deshace la última jugada realizada. Restaura el juego a un modo anterior.
	 * 
	 * Si existen jugadas previas, se elimina la última jugada y se restaura al modo
	 * correspondiente.
	 */
	@Override
	public void deshacerJugada() {
		if (!historicoJugadas.isEmpty()) {
			historicoJugadas.remove(consultarNumeroJugadasEnHistorico() - 1);
			Arbitro arbitroActual = consultarArbitroActual();
			arbitroActual.cambiarTurno();
			for (Jugada jgd : historicoJugadas) {
				arbitroActual.empujar(jgd);
			}
		}
	}

	/**
	 * Realiza una jugada y actualiza el historial de jugadas. Guarda la jugada en
	 * la lista una vez realizado el movimiento.
	 * 
	 * @param jugada La jugada que se va a realizar
	 */
	@Override
	public void hacerJugada(Jugada jugada) {
		Arbitro arbitroActual = consultarArbitroActual();
		arbitroActual.empujar(jugada);
		historicoJugadas.add(jugada);
	}

}
