package noventagrados.control.undo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;

/**
 * Clase que maneja la funcionalidad de deshacer y rehacer acciones en un juego,
 * manteniendo un historial de árbitros, permitiendo regresar a estados previos.
 * 
 * @author Víctor Vidal Vivanco
 * @author Guillermo López de Arechavaleta Zapatero
 * @version 1.0
 * @since 1.0
 */
public class MaquinaDelTiempoConArbitros extends MecanismoDeDeshacerAbstracto {

	/** Historial de árbitros en diferentes estados. */
	private List<Arbitro> historicoArbitros;

	/** El árbitros de la jugada. */
	private Arbitro arbitroActual;

	/**
	 * Constructor de la MaquinaDelTiempoConArbitros. Inicializa el historial de
	 * árbitros y la fecha de inicio. Configura el árbitro inicial para comenzar la
	 * partida.
	 * 
	 * @param fecha Fecha de inicio de la partida
	 */
	public MaquinaDelTiempoConArbitros(Date fecha) {
		super(fecha);
		this.historicoArbitros = new ArrayList<>();
		this.arbitroActual = new Arbitro(new Tablero());
		this.arbitroActual.colocarPiezasConfiguracionInicial();
		this.historicoArbitros.add(arbitroActual.clonar());
	}

	/**
	 * Consulta el árbitro actual, que representa el estado del juego.
	 * 
	 * @return El árbitro con el estado actual del juego, incluyendo el tablero
	 */
	@Override
	public Arbitro consultarArbitroActual() {
		return arbitroActual.clonar();
	}

	/**
	 * Consulta la cantidad de jugadas realizadas hasta el momento.
	 * 
	 * @return El número de jugadas en el historial
	 */
	@Override
	public int consultarNumeroJugadasEnHistorico() {
		return historicoArbitros.size() - 1;
	}

	/**
	 * Deshace la última acción realizada, restaurando el estado anterior del
	 * árbitro. También decrementa el número de jugadas realizadas.
	 */
	@Override
	public void deshacerJugada() {
		if (historicoArbitros.size() >= 2 && !arbitroActual.estaFinalizadaPartida()) {
			historicoArbitros.remove(consultarNumeroJugadasEnHistorico());
			arbitroActual.cambiarTurno();
			arbitroActual = historicoArbitros.get(consultarNumeroJugadasEnHistorico()).clonar();
			
		}
	}

	/**
	 * Realiza una jugada y actualiza el historial de árbitros. El nuevo estado del
	 * árbitro después de la jugada se guarda en el historial.
	 * 
	 * @param jugada La jugada que se va a realizar
	 */
	@Override
	public void hacerJugada(Jugada jugada) {
		arbitroActual.empujar(jugada);
		arbitroActual.cambiarTurno();
		historicoArbitros.add(arbitroActual.clonar());
	}
	
}//