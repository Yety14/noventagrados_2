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
 * Excepción lanzada cuando se intenta seleccionar una opción no disponible.
 * 
 * @author Víctor Vidal Vivanco
 * @author Guillermo López de Arechavaleta Zapatero
 * @version 1.0
 * @since 1.0
 */

public class MaquinaDelTiempoConJugadas extends MecanismoDeDeshacerAbstracto {
	private List<Jugada> historicoJugadas;
	private List<Tablero> historicoTableros; // Almacena el estado del tablero antes de cada jugada
	private Date fechaInicio;

	public MaquinaDelTiempoConJugadas(Date fecha) {
		super(fecha);
		this.historicoJugadas = new ArrayList<>();
		this.historicoTableros = new ArrayList<>();
		this.fechaInicio = fecha;
	}

	@Override
	public Arbitro consultarArbitroActual() {
		Arbitro nuevoArbitro = new Arbitro(new Tablero());
		nuevoArbitro.colocarPiezasConfiguracionInicial();
		for (int i = 0; i < historicoJugadas.size(); i++) {
			nuevoArbitro.empujar(historicoJugadas.get(i)); // Aplica cada jugada en el nuevo árbitro
		}
		return nuevoArbitro; // Devuelve el árbitro en el estado actual
	}

	@Override
	public int consultarNumeroJugadasEnHistorico() {
		return historicoJugadas.size();
	}

	@Override
	public void deshacerJugada() {
		if (!historicoJugadas.isEmpty()) {
			// Elimina la última jugada del histórico
			historicoJugadas.remove(consultarNumeroJugadasEnHistorico() - 1);

			// Verifica si hay un tablero anterior para restaurar
			if (!historicoTableros.isEmpty()) {
				Tablero tableroAnterior = historicoTableros.remove(historicoTableros.size() - 1); // Restaura el tablero
				// Aquí es donde colocamos todas las piezas en sus posiciones correspondientes
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

				// Coloca las piezas en el tablero actual usando el método colocarPiezas
				Arbitro arbitroActual = consultarArbitroActual(); // Obtiene el árbitro actual
				arbitroActual.cambiarTurno();
				arbitroActual.colocarPiezas(piezas, coordenadas, arbitroActual.consultarTurno());

			}
		}
	}

	@Override
	public void hacerJugada(Jugada jugada) {
		Arbitro arbitroActual = consultarArbitroActual();
		
		System.out.print("Antes"+arbitroActual.consultarTablero());
		Pieza pieza =arbitroActual.consultarTablero().consultarCelda(jugada.origen().consultarCoordenada()).consultarPieza();
		System.out.println(pieza);
		Coordenada coordDestino = jugada.destino().consultarCoordenada();
		System.out.println("Coordenada destino"+coordDestino);
		
		arbitroActual.empujar(jugada);
		historicoTableros.add(arbitroActual.consultarTablero().clonar()); // Guarda el estado actual del tablero
		historicoJugadas.add(jugada);

		arbitroActual.consultarTablero().colocar(pieza, coordDestino);
		System.out.print("Despues"+arbitroActual.consultarTablero());
	}

	@Override
	public Date obtenerFechaInicio() {
		return fechaInicio; // Devuelve la fecha de inicio del mecanismo de deshacer
	}
}