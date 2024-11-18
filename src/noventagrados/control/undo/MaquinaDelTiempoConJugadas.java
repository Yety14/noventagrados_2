package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;
import noventagrados.util.Coordenada;
import noventagrados.modelo.Pieza;
import noventagrados.modelo.Celda;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la funcionalidad de deshacer y rehacer jugadas, guardando un
 * historial de jugadas y tableros. Permite regresar a estados previos del
 * juego.
 * 
 * @author Víctor Vidal Vivanco
 * @author Guillermo López de Arechavaleta Zapatero
 * @version 1.0
 * @since 1.0
 */
public class MaquinaDelTiempoConJugadas extends MecanismoDeDeshacerAbstracto {

	/** Historial de las jugadas realizadas en el juego. */
	private List<Jugada> historicoJugadas;

	/** Historial de los tableros correspondientes a cada jugada realizada. */
	private List<Tablero> historicoTableros;

	/** Fecha de inicio del juego. */
	private Date fechaInicio;

	/**
	 * Constructor que inicializa el historial de jugadas, tableros y la fecha de
	 * inicio.
	 * 
	 * @param fecha Fecha de inicio de la partida
	 */
	public MaquinaDelTiempoConJugadas(Date fecha) {
		super(fecha);
		this.historicoJugadas = new ArrayList<>();
		this.historicoTableros = new ArrayList<>();
		this.fechaInicio = fecha;
	}

	/**
	 * Consulta el árbitro actual después de aplicar todas las jugadas realizadas,
	 * retornando el estado del tablero y el turno actual.
	 * 
	 * @return El árbitro con el estado actual del juego
	 */
	@Override
	public Arbitro consultarArbitroActual() {
		Arbitro nuevoArbitro = new Arbitro(new Tablero());
		nuevoArbitro.colocarPiezasConfiguracionInicial();

		// Aplica todas las jugadas del historial al nuevo árbitro
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
	 * Deshace la última jugada realizada. Restaura el tablero y el turno anterior.
	 * 
	 * Si existen jugadas previas, se elimina la última jugada y se restaura el
	 * tablero correspondiente.
	 */
	@Override
	public void deshacerJugada() {
		// Verifica si hay jugadas en el historial para deshacer
		if (!historicoJugadas.isEmpty()) {
			// Elimina la última jugada realizada
			historicoJugadas.remove(consultarNumeroJugadasEnHistorico() - 1);

			// Si existen tableros previos, se restaura el tablero y sus piezas
			if (!historicoTableros.isEmpty()) {
				Tablero tableroAnterior = historicoTableros.remove(historicoTableros.size() - 1); // Restaura el tablero

				// Se preparan listas para almacenar las piezas y coordenadas del tablero
				// restaurado
				List<Pieza> piezas = new ArrayList<>();
				List<Coordenada> coordenadas = new ArrayList<>();

				// Recorre el tablero anterior para obtener las piezas y sus posiciones
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

				// Se crea un nuevo árbitro con el tablero restaurado y las piezas
				// correspondientes
				Arbitro arbitroActual = consultarArbitroActual();
				arbitroActual.cambiarTurno();
				arbitroActual.colocarPiezas(piezas, coordenadas, arbitroActual.consultarTurno());
			}
		}
	}

	/**
	 * Realiza una jugada y actualiza el historial de jugadas y tableros. Guarda el
	 * estado del tablero después de la jugada en el historial.
	 * 
	 * @param jugada La jugada que se va a realizar
	 */
	@Override
	public void hacerJugada(Jugada jugada) {
		// Realiza la jugada con el árbitro actual
		Arbitro arbitroActual = consultarArbitroActual();
		arbitroActual.empujar(jugada);

		// Guarda el nuevo tablero después de la jugada y añade la jugada al historial
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
}
