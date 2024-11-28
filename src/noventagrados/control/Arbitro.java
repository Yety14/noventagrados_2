package noventagrados.control;

import noventagrados.modelo.Tablero;
import noventagrados.modelo.Pieza;
import noventagrados.util.Color;
import noventagrados.util.Coordenada;
import noventagrados.util.TipoPieza;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Celda;
import noventagrados.util.Sentido;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;

/**
 * Clase que representa al árbitro del juego. El árbitro es responsable de
 * gestionar el estado del tablero, los turnos de los jugadores, las jugadas
 * realizadas y determinar el ganador del juego.
 * 
 * @author <a href="vvv1005@alu.ubu.es">Víctor Vidal Vivanco</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * @version 1.0
 * @since 1.0
 */
public class Arbitro {

	/** El color del jugador que tiene el turno actual. */
	private Color turno;

	/** El tablero de juego actual. */
	private Tablero tablero;

	/** El número total de jugadas realizadas en la partida. */
	private int numeroJugadas;

	/** La caja que contiene las piezas blancas capturadas. */
	private Caja cajaBlanca;

	/** La caja que contiene las piezas negras capturadas. */
	private Caja cajaNegra;

	/**
	 * Constructor que inicializa el árbitro con el tablero de juego y las cajas de
	 * piezas capturadas. Establece el número de jugadas en 0 y asigna un color de
	 * turno predeterminado.
	 *
	 * @param tablero El tablero de juego inicial
	 */
	public Arbitro(Tablero tablero) {
		this.tablero = tablero;
		this.numeroJugadas = 0;
		this.cajaBlanca = new Caja(Color.BLANCO);
		this.cajaNegra = new Caja(Color.NEGRO);
	}

	/**
	 * Cambia el turno de juego al color contrario al actual.
	 */
	public void cambiarTurno() {
		turno = turno.consultarContrario();
	}

	/**
	 * Crea un clon del árbitro con el mismo estado (tablero, número de jugadas, y
	 * cajas de piezas capturadas).
	 *
	 * @return Un nuevo objeto Arbitro con el mismo estado que el actual.
	 */
	public Arbitro clonar() {
		Arbitro clon = new Arbitro(this.tablero.clonar());
		clon.turno = this.turno;
		clon.numeroJugadas = this.numeroJugadas;
		clon.cajaBlanca = this.cajaBlanca.clonar();
		clon.cajaNegra = this.cajaNegra.clonar();
		return clon;
	}

	/**
	 * Coloca las piezas en el tablero en las coordenadas especificadas y establece
	 * el turno inicial.
	 *
	 * @param piezas      Lista de piezas a colocar en el tablero.
	 * @param coordenadas Lista de coordenadas donde colocar las piezas.
	 * @param turnoActual El color del jugador que comienza el turno.
	 */
	public void colocarPiezas(List<Pieza> piezas, List<Coordenada> coordenadas, Color turnoActual) {
		this.turno = turnoActual;
		for (int i = 0; i < piezas.size(); i++) {
			tablero.colocar(piezas.get(i), coordenadas.get(i));
		}
	}

	/**
	 * Coloca las piezas en la configuración inicial del juego, con el turno de
	 * inicio en BLANCO.
	 */
	public void colocarPiezasConfiguracionInicial() {
		List<Pieza> piezas = Arrays.asList(new Pieza(TipoPieza.REINA, Color.BLANCO),
				new Pieza(TipoPieza.REINA, Color.NEGRO), new Pieza(TipoPieza.PEON, Color.BLANCO),
				new Pieza(TipoPieza.PEON, Color.BLANCO), new Pieza(TipoPieza.PEON, Color.BLANCO),
				new Pieza(TipoPieza.PEON, Color.BLANCO), new Pieza(TipoPieza.PEON, Color.BLANCO),
				new Pieza(TipoPieza.PEON, Color.BLANCO), new Pieza(TipoPieza.PEON, Color.NEGRO),
				new Pieza(TipoPieza.PEON, Color.NEGRO), new Pieza(TipoPieza.PEON, Color.NEGRO),
				new Pieza(TipoPieza.PEON, Color.NEGRO), new Pieza(TipoPieza.PEON, Color.NEGRO),
				new Pieza(TipoPieza.PEON, Color.NEGRO));

		List<Coordenada> coordenadas = Arrays.asList(new Coordenada(0, 0), new Coordenada(6, 6), new Coordenada(1, 0),
				new Coordenada(2, 0), new Coordenada(3, 0), new Coordenada(0, 1), new Coordenada(0, 2),
				new Coordenada(0, 3), new Coordenada(3, 6), new Coordenada(4, 6), new Coordenada(5, 6),
				new Coordenada(6, 3), new Coordenada(6, 4), new Coordenada(6, 5));
		colocarPiezas(piezas, coordenadas, Color.BLANCO);
		numeroJugadas = 0;
		this.turno = Color.BLANCO;
	}

	/**
	 * Consulta la caja de piezas capturadas para un color específico.
	 * 
	 * @param color El color de la caja de piezas capturadas a consultar.
	 * @return La caja correspondiente al color.
	 */
	public Caja consultarCaja(Color color) {
		return (color == Color.BLANCO) ? cajaBlanca : cajaNegra;
	}

	/**
	 * Consulta el número de jugadas realizadas en la partida.
	 * 
	 * @return El número de jugadas realizadas.
	 */
	public int consultarNumeroJugada() {
		return numeroJugadas;
	}

	/**
	 * Consulta el estado actual del tablero.
	 * 
	 * @return Una copia del tablero actual.
	 */
	public Tablero consultarTablero() {
		return tablero.clonar();
	}

	/**
	 * Consulta el color del jugador que tiene el turno actual.
	 * 
	 * @return El color del turno actual.
	 */
	public Color consultarTurno() {
		return turno;
	}

	/**
	 * Determina el color del ganador si la partida ha finalizado.
	 * 
	 * @return El color del ganador, o null si la partida no ha terminado o no tiene
	 *         ganador.
	 */
	public Color consultarTurnoGanador() {
		Color ganador = null;
		TableroConsultor<Tablero> tableroCons = new TableroConsultor<>(tablero);

		if (estaFinalizadaPartida()) {
			if (tableroCons.estaReinaEnElCentro(Color.BLANCO)) {
				ganador = Color.BLANCO;
			}
			if (tableroCons.estaReinaEnElCentro(Color.NEGRO)) {
				ganador = Color.NEGRO;
			}
			if (!tableroCons.hayReina(Color.BLANCO)) {
				ganador = Color.NEGRO;
			}
			if (!tableroCons.hayReina(Color.NEGRO)) {
				ganador = Color.BLANCO;
			}
			if (!tableroCons.hayReina(Color.BLANCO) && !tableroCons.hayReina(Color.NEGRO)) {
				ganador = null;
			}
		}
		return ganador;
	}

	/**
	 * Realiza un movimiento de empuje en el tablero.
	 * 
	 * @param jugada La jugada que debe realizarse.
	 */
	public void empujar(Jugada jugada) {
		numeroJugadas++;
		TableroConsultor<Tablero> tableroCons = new TableroConsultor<>(tablero);
		Celda origen = jugada.origen();
		Celda destino = jugada.destino();
		Coordenada coordOrigen = origen.consultarCoordenada();
		Coordenada coordDestino = destino.consultarCoordenada();
		Pieza pieza = tablero.consultarCelda(coordOrigen).consultarPieza();
		Sentido sentido = tableroCons.calcularSentido(coordOrigen, coordDestino);

		if (!tablero.estanEnTablero(coordDestino) && pieza != null) {
			(pieza.consultarColor() == Color.BLANCO ? cajaBlanca : cajaNegra).añadir(pieza);
		} else {
			if (sentido.consultarDesplazamientoEnColumnas() == 0) {
				moverPieza(coordOrigen, coordDestino, sentido, true);
			} else if (sentido.consultarDesplazamientoEnFilas() == 0) {
				moverPieza(coordOrigen, coordDestino, sentido, false);
			}
		}
		tablero.colocar(pieza, coordDestino);
	}

	/**
	 * Ejecuta un movimiento para una pieza en la dirección indicada (horizontal o
	 * vertical).
	 * 
	 * @param origen     La coordenada de origen.
	 * @param destino    La coordenada de destino.
	 * @param sentido    El sentido del movimiento (horizontal o vertical).
	 * @param esVertical Si el movimiento es horizontal.
	 */
	private void moverPieza(Coordenada origen, Coordenada destino, Sentido sentido, boolean esVertical) {
		int origenPos = esVertical ? origen.fila() : origen.columna();
		int destinoPos = esVertical ? destino.fila() : destino.columna();
		int desplazamiento = esVertical ? sentido.consultarDesplazamientoEnFilas()
				: sentido.consultarDesplazamientoEnColumnas();

		Coordenada siguienteCoordenada = origen;

		while (origenPos != destinoPos) {
			tablero.eliminarPieza(siguienteCoordenada);
			origenPos += desplazamiento;
			siguienteCoordenada = esVertical ? new Coordenada(origenPos, origen.columna())
					: new Coordenada(origen.fila(), origenPos);
			Celda siguienteCelda = tablero.consultarCelda(siguienteCoordenada);
			if (siguienteCelda != null && !siguienteCelda.estaVacia()) {
				Coordenada coordDestPieza2 = esVertical
						? new Coordenada(destino.fila() + desplazamiento, destino.columna())
						: new Coordenada(destino.fila(), destino.columna() + desplazamiento);
				Celda destinoNuevo = new Celda(coordDestPieza2);
				Jugada jugadanueva = new Jugada(siguienteCelda, destinoNuevo);
				numeroJugadas--;
				empujar(jugadanueva);
			}
		}
	}

	/**
	 * Verifica si un movimiento es legal según las reglas del juego.
	 * 
	 * @param jugada La jugada a verificar.
	 * @return true si el movimiento es legal, false en caso contrario.
	 */
	public boolean esMovimientoLegal(Jugada jugada) {
		Coordenada origen = jugada.origen().consultarCoordenada();
		Coordenada destino = jugada.destino().consultarCoordenada();
		TableroConsultor<Tablero> tableroCons = new TableroConsultor<>(tablero);
		boolean result = false;

		if (tablero.estanEnTablero(origen) && tablero.estanEnTablero(destino) && !origen.equals(destino)) {
			if (!jugada.origen().estaVacia()) {
				if (this.turno == jugada.origen().consultarColorDePieza()) {
					Sentido sentido = tableroCons.calcularSentido(origen, destino);
					if (sentido == null) {
						return false;
					}
					// Movimiento Horizontal
					if (sentido.consultarDesplazamientoEnFilas() == 0) {
						if (tableroCons.consultarDistanciaEnHorizontal(origen, destino) == tableroCons
								.consultarNumeroPiezasEnVertical(origen)) {
							result = true;
						}
					}
					// Movimiento Vertical
					if (sentido.consultarDesplazamientoEnColumnas() == 0) {
						if (tableroCons.consultarDistanciaEnVertical(origen, destino) == tableroCons
								.consultarNumeroPiezasEnHorizontal(origen)) {
							result = true;
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * Verifica si la partida ha finalizado.
	 * 
	 * @return true si la partida ha finalizado, false en caso contrario
	 */
	public boolean estaFinalizadaPartida() {
		TableroConsultor<Tablero> tableroCons = new TableroConsultor<>(tablero);
		return !tableroCons.hayReina(Color.BLANCO) || !tableroCons.hayReina(Color.NEGRO)
				|| tableroCons.estaReinaEnElCentro(Color.BLANCO) || tableroCons.estaReinaEnElCentro(Color.NEGRO);
	}

	/**
	 * Compara esta instancia de Arbitro con otro objeto para determinar si son
	 * iguales. Dos árbitros se consideran iguales si tienen los mismos atributos
	 * (cajas, tablero, número de jugadas y turno).
	 *
	 * @param obj El objeto con el que se compara
	 * @return true si los objetos son iguales, false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Arbitro other = (Arbitro) obj;
		return Objects.equals(cajaBlanca, other.cajaBlanca) && Objects.equals(cajaNegra, other.cajaNegra)
				&& numeroJugadas == other.numeroJugadas && Objects.equals(tablero, other.tablero)
				&& turno == other.turno;
	}

	/**
	 * Devuelve el valor hash de este Arbitro. El valor hash se calcula a partir de
	 * sus atributos: cajaBlanca, cajaNegra, número de jugadas, tablero y turno.
	 * 
	 * @return El valor del hash de este Arbitro
	 */
	@Override
	public int hashCode() {
		return Objects.hash(cajaBlanca, cajaNegra, numeroJugadas, tablero, turno);
	}

	/**
	 * Genera una representación en cadena del estado actual del Arbitro, mostrando
	 * los valores de turno, tablero, número de jugadas, caja blanca y caja negra.
	 * 
	 * @return Una cadena que representa el estado del Arbitro
	 */
	@Override
	public String toString() {
		return "Arbitro [turno=" + turno + ", tablero=" + tablero + ", numeroJugadas=" + numeroJugadas + ", cajaBlanca="
				+ cajaBlanca + ", cajaNegra=" + cajaNegra + "]";
	}

}//