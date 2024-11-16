package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.control.TableroConsultor;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;
import noventagrados.util.Coordenada;
import noventagrados.modelo.Pieza;
import noventagrados.modelo.Celda;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

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
		Arbitro nuevoArbitro = generarPartidaInicial(); // Crea un nuevo árbitro en estado inicial
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
			historicoJugadas.remove(historicoJugadas.size() - 1);

			// Verifica si hay un tablero anterior para restaurar
			if (!historicoTableros.isEmpty()) {
				Tablero tableroAnterior = historicoTableros.remove(historicoTableros.size() - 1); // Restaura el tablero
																									// anterior
				TableroConsultor<Tablero> tableroCons = new TableroConsultor<>(tableroAnterior);
				// Aquí es donde colocamos todas las piezas en sus posiciones correspondientes
				List<Pieza> piezas = new ArrayList<>();
				List<Coordenada> coordenadas = new ArrayList<>();

				// Recorremos todas las posiciones del tablero anterior para obtener las piezas
				// y sus coordenadas
				for (int fila = 0; fila < tableroAnterior.consultarNumeroFilas(); fila++) {
					for (int columna = 0; columna < tableroAnterior.consultarNumeroColumnas(); columna++) {
						Coordenada coordenada = new Coordenada(fila, columna);
						Celda celda=new Celda(coordenada);
						Pieza pieza = celda.consultarPieza();
						
						if (pieza != null) {
							piezas.add(pieza);
							coordenadas.add(coordenada);
						}
					}
				}

				// Coloca las piezas en el tablero actual usando el método colocarPiezas
				Arbitro arbitroActual = consultarArbitroActual(); // Obtiene el árbitro actual
				arbitroActual.colocarPiezas(piezas, coordenadas, arbitroActual.consultarTurno());
			}
		}
	}

	@Override
	public void hacerJugada(Jugada jugada) {
		Arbitro arbitroActual = consultarArbitroActual();
		historicoTableros.add(arbitroActual.consultarTablero().clonar()); // Guarda el estado actual del tablero
		historicoJugadas.add(jugada);
		arbitroActual.empujar(jugada); // Aplica la jugada
	}

	private Arbitro generarPartidaInicial() {
		Arbitro arbitro = new Arbitro(new Tablero());
		arbitro.colocarPiezasConfiguracionInicial(); // Coloca las piezas en la configuración inicial
		return arbitro;
	}

	@Override
	public Date obtenerFechaInicio() {
		return fechaInicio; // Devuelve la fecha de inicio del mecanismo de deshacer
	}
}