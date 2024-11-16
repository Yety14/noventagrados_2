package noventagrados.textui.excepcion;

/**
 * Excepción lanzada cuando se intenta seleccionar una opción no disponible.
 * 
 * @author Víctor Vidal Vivanco
 * @author Guillermo López de Arechavaleta Zapatero
 * @version 1.0
 * @since 1.0
 */
public class OpcionNoDisponibleException extends java.lang.Exception {
    private static final long serialVersionUID = 1L;

    /** 
     * Constructor por defecto.
     */
    public OpcionNoDisponibleException() {
        super();
    }

    /** 
     * Constructor que permite especificar un mensaje de error.
     * 
     * @param message el mensaje de error
     */
    public OpcionNoDisponibleException(String message) {
        super(message);
    }

    /** 
     * Constructor que permite especificar la causa de la excepción.
     * 
     * @param cause la causa de la excepción
     */
    public OpcionNoDisponibleException(Throwable cause) {
        super(cause);
    }

    /** 
     * Constructor que permite especificar un mensaje y una causa.
     * 
     * @param message el mensaje de error
     * @param cause la causa de la excepción
     */
    public OpcionNoDisponibleException(String message, Throwable cause) {
        super(message, cause);
    }
}