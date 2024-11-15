package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class MaquinaDelTiempoConArbitros extends MecanismoDeDeshacerAbstracto {
    private List<Arbitro> historicoArbitros;

    public MaquinaDelTiempoConArbitros(Date fecha) {
        super(fecha);
        this.historicoArbitros = new ArrayList<>();
    }

    @Override
    public Arbitro consultarArbitroActual() {
        if (!historicoArbitros.isEmpty()) {
            return historicoArbitros.get(historicoArbitros.size() - 1);
        }
        return null;
    }

    @Override
    public int consultarNumeroJugadasEnHistorico() {
        return historicoArbitros.size();
    }

    @Override
    public void deshacerJugada() {
        if (!historicoArbitros.isEmpty()) {
            historicoArbitros.remove(historicoArbitros.size() - 1);
        }
    }

    @Override
    public void hacerJugada(Jugada jugada) {
        // Método hacerJugada puede estar relacionado con registrar el estado del árbitro
        // Implementar la lógica según sea necesario
    }
}
