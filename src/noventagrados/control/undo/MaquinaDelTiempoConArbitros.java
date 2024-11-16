package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Tablero;
import noventagrados.modelo.Jugada;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class MaquinaDelTiempoConArbitros extends MecanismoDeDeshacerAbstracto {
    private List<Arbitro> historicoArbitros; // Historial de árbitros

    public MaquinaDelTiempoConArbitros(Date fecha) {
        super(fecha); // Llama al constructor de la clase abstracta
        this.historicoArbitros = new ArrayList<>();
        Arbitro arbitroInicial = generarPartidaInicial(); // Inicializa el árbitro con el estado inicial del juego
        historicoArbitros.add(arbitroInicial); // Agrega el estado inicial al historial
    }

    @Override
    public Arbitro consultarArbitroActual() {
        return historicoArbitros.get(historicoArbitros.size() - 1); // Devuelve el árbitro actual
    }

    @Override
    public int consultarNumeroJugadasEnHistorico() {
        return historicoArbitros.size() - 1; // Devuelve el tamaño del historial de árbitros, excluyendo el estado inicial
    }

    @Override
    public void deshacerJugada() {
        if (historicoArbitros.size() > 1) { // Asegúrate de no eliminar el estado inicial
            historicoArbitros.remove(historicoArbitros.size() - 1);
        }
    }

    @Override
    public void hacerJugada(Jugada jugada) {
        Arbitro nuevoArbitro = clonarArbitro(consultarArbitroActual()); // Clona el árbitro actual
        aplicarJugada(nuevoArbitro, jugada); // Aplica la jugada al nuevo árbitro
        historicoArbitros.add(nuevoArbitro); // Agrega el nuevo estado al historial
    }

    private void aplicarJugada(Arbitro arbitro, Jugada jugada) {
        arbitro.empujar(jugada); // Aplica la jugada al árbitro
    }

    private Arbitro clonarArbitro(Arbitro arbitro) {
        return new Arbitro(arbitro.consultarTablero().clonar()); // Clona el árbitro y su estado
    }

    private Arbitro generarPartidaInicial() {
        Arbitro arbitro = new Arbitro(new Tablero()); // Genera un nuevo tablero
        arbitro.colocarPiezasConfiguracionInicial(); // Coloca las piezas en la configuración inicial
        return arbitro; // Devuelve el árbitro inicial
    }
}