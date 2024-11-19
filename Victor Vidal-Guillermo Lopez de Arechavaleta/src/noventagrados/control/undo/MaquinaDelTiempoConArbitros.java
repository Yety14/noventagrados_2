package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;
import noventagrados.modelo.Pieza;
import noventagrados.modelo.Celda;
import noventagrados.util.Coordenada;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la funcionalidad de deshacer y rehacer jugadas en un juego,
 * manteniendo un historial de jugadas y tableros, permitiendo regresar a
 * estados previos. Se integra con el Arbitro para gestionar el flujo del juego.
 *
 * @author Víctor Vidal Vivanco
 * @author Guillermo López de Arechavaleta Zapatero
 * @version 1.0
 * @since 1.0
 */
public class MaquinaDelTiempoConArbitros extends MecanismoDeDeshacerAbstracto {

	/** Historial de jugadas realizadas en el juego. */
	private List<Jugada> historicoJugadas;

	/** Historial de tableros en cada estado posterior a una jugada. */
	private List<Tablero> historicoTableros;

	/** Fecha de inicio del juego. */
	private Date fechaInicio;

	/**
	 * Constructor de la MaquinaDelTiempoConArbitros. Inicializa el historial de
	 * jugadas, tableros, y la fecha de inicio. Configura el arbitro inicial para
	 * comenzar la partida.
	 * 
	 * @param fecha Fecha de inicio de la partida
	 */
	public MaquinaDelTiempoConArbitros(Date fecha) {
		super(fecha);
		this.historicoJugadas = new ArrayList<>();
		this.historicoTableros = new ArrayList<>();
		this.fechaInicio = fecha;
		Arbitro arbitroInicial = generarPartidaInicial();
		historicoTableros.add(arbitroInicial.consultarTablero().clonar());
	}

	/**
	 * Consulta el árbitro actual, aplicando todas las jugadas previas almacenadas
	 * en el historial y actualizando el estado del tablero.
	 * 
	 * @return El arbitro con el estado actual del juego, incluyendo el tablero
	 */
	@Override
	public Arbitro consultarArbitroActual() {
		Arbitro nuevoArbitro = new Arbitro(new Tablero());
		nuevoArbitro.colocarPiezasConfiguracionInicial();

		for (Jugada jugada : historicoJugadas) {
			nuevoArbitro.empujar(jugada);
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
	 * Deshace la última jugada realizada, restaurando el estado anterior del
	 * tablero. Si hay jugadas previas, se elimina la última jugada y se restaura el
	 * tablero correspondiente.
	 */
	@Override
	public void deshacerJugada() {
		if (!historicoJugadas.isEmpty()) {
			historicoJugadas.remove(historicoJugadas.size() - 1);

			if (!historicoTableros.isEmpty()) {
				Tablero tableroAnterior = historicoTableros.remove(historicoTableros.size() - 1);
				List<Pieza> piezas = new ArrayList<>();
				List<Coordenada> coordenadas = new ArrayList<>();

				for (int fila = 0; fila < tableroAnterior.consultarNumeroFilas(); fila++) {
					for (int columna = 0; columna < tableroAnterior.consultarNumeroColumnas(); columna++) {
						Coordenada coordenada = new Coordenada(fila, columna);
						Celda celda = new Celda(coordenada);
						Pieza pieza = celda.consultarPieza();

						if (pieza != null) {
							piezas.add(pieza);
							coordenadas.add(coordenada);
						}
					}
				}

				Arbitro arbitroActual = consultarArbitroActual();
				arbitroActual.cambiarTurno();
				arbitroActual.colocarPiezas(piezas, coordenadas, arbitroActual.consultarTurno());
			}
		}
	}

	/**
	 * Realiza una jugada y actualiza el historial de jugadas y el tablero. El nuevo
	 * estado del tablero después de la jugada se guarda en el historial.
	 * 
	 * @param jugada La jugada que se va a realizar
	 */
	@Override
	public void hacerJugada(Jugada jugada) {
		Arbitro arbitroActual = consultarArbitroActual();
		arbitroActual.empujar(jugada);
		historicoTableros.add(arbitroActual.consultarTablero().clonar());
		historicoJugadas.add(jugada);
	}

	/**
	 * Obtiene la fecha de inicio de la partida.
	 * 
	 * @return La fecha de inicio de la partida
	 */
	@Override
	public Date obtenerFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Genera el arbitro inicial con la configuración de piezas para comenzar una
	 * nueva partida.
	 * 
	 * @return El arbitro con el tablero configurado
	 */
	private Arbitro generarPartidaInicial() {
		Arbitro arbitro = new Arbitro(new Tablero());
		arbitro.colocarPiezasConfiguracionInicial();
		return arbitro;
	}
}
