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
 * Excepción lanzada cuando se intenta seleccionar una opción no disponible.
 * 
 * @author Víctor Vidal Vivanco
 * @author Guillermo López de Arechavaleta Zapatero
 * @version 1.0
 * @since 1.0
 */

public class MaquinaDelTiempoConArbitros extends MecanismoDeDeshacerAbstracto {
    private List<Jugada> historicoJugadas;
    private List<Tablero> historicoTableros; // Almacena el estado del tablero antes de cada jugada
    private Date fechaInicio;

    public MaquinaDelTiempoConArbitros(Date fecha) {
        super(fecha);
        this.historicoJugadas = new ArrayList<>();
        this.historicoTableros = new ArrayList<>();
        this.fechaInicio = fecha;
        Arbitro arbitroInicial = generarPartidaInicial(); // Inicializa el árbitro con el estado inicial del juego
        historicoTableros.add(arbitroInicial.consultarTablero().clonar()); // Agrega el estado inicial al historial
    }

    @Override
    public Arbitro consultarArbitroActual() {
        Arbitro nuevoArbitro = new Arbitro(new Tablero());
        nuevoArbitro.colocarPiezasConfiguracionInicial();
        
        for (Jugada jugada : historicoJugadas) {
            nuevoArbitro.empujar(jugada); // Aplica cada jugada en el nuevo árbitro
            nuevoArbitro.cambiarTurno();
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
            historicoJugadas.remove(historicoJugadas.size() - 1);

            if (!historicoTableros.isEmpty()) {
                Tablero tableroAnterior = historicoTableros.remove(historicoTableros.size() - 1); // Restaura el tablero
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

    @Override
    public void hacerJugada(Jugada jugada) {
        Arbitro arbitroActual = consultarArbitroActual();
        arbitroActual.empujar(jugada);
        historicoTableros.add(arbitroActual.consultarTablero().clonar());
        historicoJugadas.add(jugada);
    }

    @Override
    public Date obtenerFechaInicio() {
        return fechaInicio; // Devuelve la fecha de inicio del mecanismo de deshacer
    }

    private Arbitro generarPartidaInicial() {
        Arbitro arbitro = new Arbitro(new Tablero()); // Genera un nuevo tablero
        arbitro.colocarPiezasConfiguracionInicial(); // Coloca las piezas en la configuración inicial
        return arbitro; // Devuelve el árbitro inicial
    }
}