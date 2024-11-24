package noventagrados.control.undo;

import noventagrados.control.Arbitro;
import noventagrados.modelo.Jugada;
import noventagrados.modelo.Tablero;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la funcionalidad de deshacer y rehacer acciones en un juego,
 * manteniendo un historial de árbitros, permitiendo regresar a estados previos.
 * 
 * @author Víctor Vidal Vivanco
 * @author Guillermo López de Arechavaleta Zapatero
 * @version 1.0
 * @since 1.0
 */
public class MaquinaDelTiempoConArbitros extends MecanismoDeDeshacerAbstracto {

	/** Historial de árbitros en diferentes estados. */
	private List<Arbitro> historicoArbitros;

	/** Fecha de inicio del juego. */
	private Date fechaInicio;

	/**
	 * Constructor de la MaquinaDelTiempoConArbitros. Inicializa el historial de
	 * árbitros y la fecha de inicio. Configura el árbitro inicial para comenzar la
	 * partida.
	 * 
	 * @param fecha Fecha de inicio de la partida
	 */
	public MaquinaDelTiempoConArbitros(Date fecha) {
		super(fecha);
		this.historicoArbitros = new ArrayList<>();
		this.fechaInicio = fecha;
	}

	/**
	 * Consulta el árbitro actual, que representa el estado del juego.
	 * 
	 * @return El árbitro con el estado actual del juego, incluyendo el tablero
	 */
	@Override
	public Arbitro consultarArbitroActual() {
		if (historicoArbitros.isEmpty()) {
			Arbitro arbitroInicial = new Arbitro(new Tablero());
			arbitroInicial.colocarPiezasConfiguracionInicial();
			//historicoArbitros.add(arbitroInicial.clonar());
			return arbitroInicial;
		}

		for (int i = 0; i < historicoArbitros.size(); i++) {
			System.out.println("\n\n\nElemento en posición " + i + "\n" + historicoArbitros.get(i));
		}
		// No se puede acceder de esta la "#Forma 1" porque si el numero de jugadas es 1 por
		// ejemplo y en el historicoArbitros solo hay un elemento, estarás intentando acceder a la posicion 1 de un
		// arraylist que solo tiene un elemento. Lo que quieres hacer es acceder a su elemento 0 (el primer elemento)
		// entonces hay que restar uno al numeroJugadas historico "#Forma 2" o implementar esto de otra forma "#Forma 3". 
		// No estoy seguro de la relacion entre el numero de jugadas y los arbitros parece que esta bien
		// Luego acuerdate de borrar esta barbaridad de texto
		
		//return historicoArbitros.get(consultarNumeroJugadasEnHistorico()); #Forma 1
		
		//if (consultarNumeroJugadasEnHistorico() > 0) {
			//return historicoArbitros.get(consultarNumeroJugadasEnHistorico() - 1); #Forma 2
		//}
		
		// En este caso veo que hay que clonar el arbitro que se obtiene de la lista. Tambien se indica en la pagina 12
		// del pdf asique estará bien. Aveces no se si entiendo esto de clonar o no.
		return historicoArbitros.getLast().clonar(); //#Forma 3
		// Otra forma de obtener el ultimo arbi directamente. Si haces click en la función getLast(),
		// indica que el lo mimso que hacer el size() - 1, o sea obtener el último elemento.
		// No se si podeis usarlo pero si no, hazlo asi :
		// return historicoArbitros.get(historicoArbitros.size() -1).clonar(); //#Forma 3
	}

	/**
	 * Consulta la cantidad de jugadas realizadas hasta el momento.
	 * 
	 * @return El número de jugadas en el historial
	 */
	@Override
	public int consultarNumeroJugadasEnHistorico() {
		// return historicoArbitros.size() - 1;
		// Aqui se debe devolver el número de elementos sin modificacion para evitar
		// calculos erroneos
		return historicoArbitros.size();
	}

	/**
	 * Deshace la última acción realizada, restaurando el estado anterior del
	 * árbitro. También decrementa el número de jugadas realizadas.
	 */
	@Override
	public void deshacerJugada() {
//			for(int i=0; i<historicoArbitros.size();i++) {
//			    System.out.println("Elemento en posición " + i + "\n" + historicoArbitros.get(i));
//			    }
		if (!historicoArbitros.isEmpty()) {
			// El mismo comentario que el metodo de consultarArbitroActual() sobre lo de utilizar
			// el numero de jugadas como indice, parte de porque daba problemas.
			// historicoArbitros.remove(consultarNumeroJugadasEnHistorico());
			historicoArbitros.remove(historicoArbitros.size() - 1);
			// Importante cambiar los turnos despues de deshacer la jugada, supongo
			// que no puede ir el mismo dos veces o no estoy seguro
			consultarArbitroActual().cambiarTurno();
		}
	}

	/**
	 * Realiza una jugada y actualiza el historial de árbitros. El nuevo estado del
	 * árbitro después de la jugada se guarda en el historial.
	 * 
	 * @param jugada La jugada que se va a realizar
	 */
	@Override
	public void hacerJugada(Jugada jugada) {
		Arbitro estadoActual = consultarArbitroActual();
//		    System.out.print("Antes de empujar" + estadoActual.consultarTablero());
		estadoActual.empujar(jugada);
		//Cambiar de turno depues de haber empujado
		estadoActual.cambiarTurno();
//		    System.out.print("Despues de empujar" + estadoActual.consultarTablero());
		// Añadir el arbitro a la lista de historicos despues de empujar la jugada.
		// No se si es necesario clonarlo pero funciona de las dos formas.
		historicoArbitros.add(estadoActual);
		//historicoArbitros.add(estadoActual.clonar());
	}

	/**
	 * Obtiene la fecha de inicio de la partida.
	 * 
	 * @return La fecha de inicio de la partida
	 */
	@Override
	public Date obtenerFechaInicio() {
		return fechaInicio;
	}
}