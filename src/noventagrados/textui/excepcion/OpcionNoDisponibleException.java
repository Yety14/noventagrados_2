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

	/**
	 * Identificador único para la versión de la clase, utilizado para asegurar la
	 * compatibilidad durante la deserialización de objetos.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor por defecto. Este constructor llama al constructor de la clase
	 * base (Exception) sin ningún mensaje ni causa proporcionados.
	 */
	public OpcionNoDisponibleException() {
		super();
	}

	/**
	 * Constructor que permite especificar un mensaje de error. Este constructor
	 * llama al constructor de la clase base con el mensaje de error proporcionado.
	 * 
	 * @param mensaje el mensaje de error
	 */
	public OpcionNoDisponibleException(String mensaje) {
		super(mensaje);
	}

	/**
	 * Constructor que permite especificar la causa de la excepción. Este
	 * constructor llama al constructor de la clase base con la causa de la
	 * excepción proporcionada.
	 * 
	 * @param causa la causa de la excepción
	 */
	public OpcionNoDisponibleException(Throwable causa) {
		super(causa);
	}

	/**
	 * Constructor que permite especificar un mensaje y una causa. Este constructor
	 * llama al constructor de la clase base con el mensaje de error y la causa
	 * proporcionados.
	 * 
	 * @param mensaje el mensaje de error
	 * @param causa   la causa de la excepción
	 */
	public OpcionNoDisponibleException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}
}
