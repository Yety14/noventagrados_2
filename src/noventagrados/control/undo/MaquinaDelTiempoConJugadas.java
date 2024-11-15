package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class MaquinaDelTiempoConJugadas extends MecanismoDeDeshacerAbstracto {
    private List<Jugada> historicoJugadas;

    public MaquinaDelTiempoConJugadas(Date fecha) {
        super(fecha);
        this.historicoJugadas = new ArrayList<>();
    }

    @Override
    public Arbitro consultarArbitroActual() {
        // Implementación específica para consultar el árbitro en este contexto
        return null;
    }

    @Override
    public int consultarNumeroJugadasEnHistorico() {
        return historicoJugadas.size();
    }

    @Override
    public void deshacerJugada() {
        if (!historicoJugadas.isEmpty()) {
            historicoJugadas.remove(historicoJugadas.size() - 1);
        }
    }

    @Override
    public void hacerJugada(Jugada jugada) {
        historicoJugadas.add(jugada);
    }
}
