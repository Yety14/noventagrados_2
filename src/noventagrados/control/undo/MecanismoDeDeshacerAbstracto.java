package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import java.util.Date;

public abstract class MecanismoDeDeshacerAbstracto implements MecanismoDeDeshacer {
    private Date fechaInicio;

    public MecanismoDeDeshacerAbstracto(Date fecha) {
        this.fechaInicio = fecha;
    }

    @Override
    public Date obtenerFechaInicio() {
        return fechaInicio;
    }

    // Métodos abstractos que deben ser implementados por las subclases
    public abstract Arbitro consultarArbitroActual();
    public abstract int consultarNumeroJugadasEnHistorico();
    public abstract void deshacerJugada();
    public abstract void hacerJugada(Jugada jugada);
}
