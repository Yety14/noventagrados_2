package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import java.util.Date;

/**
 * Clase abstracta que implementa la interfaz MecanismoDeDeshacer.
 * 
 * Esta clase proporciona una implementación parcial para el manejo de un mecanismo de deshacer en un juego.
 * Se encarga de almacenar la fecha de inicio del mecanismo de deshacer y deja algunos métodos
 * como abstractos para que las clases concretas definan su comportamiento específico en relación
 * con el manejo de jugadas y el estado del árbitro.
 * 
 * @author <a href="vvv1005@alu.ubu.es">Víctor Vidal Vivanco</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta Zapatero</a>
 * @version 1.0
 * @since 1.0
 */
public abstract class MecanismoDeDeshacerAbstracto implements MecanismoDeDeshacer {
    
    /** La fecha de inicio del mecanismo de deshacer. */
    private Date fechaInicio;
    
    /**
     * Constructor de la clase abstracta.
     * 
     * Inicializa el mecanismo de deshacer con la fecha de inicio proporcionada.
     * 
     * @param fecha La fecha y hora en la que se inicia el mecanismo de deshacer.
     */
    public MecanismoDeDeshacerAbstracto(Date fecha) {
        this.fechaInicio = fecha;
    }

    /**
     * Obtiene la fecha de inicio del mecanismo de deshacer.
     * 
     * @return La fecha de inicio del mecanismo de deshacer.
     */
    @Override
    public Date obtenerFechaInicio() {
        return fechaInicio;
    }
    
    /**
     * Consulta el árbitro actual con el estado actualizado del juego.
     * 
     * @return El árbitro con el estado actual del juego.
     */
    public abstract Arbitro consultarArbitroActual();
    
    /**
     * Consulta el número de jugadas almacenadas en el historial de jugadas.
     * 
     * @return El número de jugadas en el historial.
     */
    public abstract int consultarNumeroJugadasEnHistorico();
    
    /**
     * Deshace la última jugada realizada y restaura el estado anterior del juego.
     */
    public abstract void deshacerJugada();
    
    /**
     * Realiza una nueva jugada en el juego y la almacena en el historial.
     * 
     * @param jugada La jugada a realizar.
     */
    public abstract void hacerJugada(Jugada jugada);
}
