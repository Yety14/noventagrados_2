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
				historicoArbitros.add(arbitroInicial.clonar());				
				}
			
			for(int i=0; i<historicoArbitros.size();i++) {
			    System.out.println("\n\n\nElemento en posición " + i + "\n" + historicoArbitros.get(i));
			    }
			
			return historicoArbitros.get(consultarNumeroJugadasEnHistorico());
	
		}
	
		/**
		 * Consulta la cantidad de jugadas realizadas hasta el momento.
		 * 
		 * @return El número de jugadas en el historial
		 */
		@Override
		public int consultarNumeroJugadasEnHistorico() {
			return historicoArbitros.size() - 1;
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
				Arbitro arbitroActual = consultarArbitroActual();
				historicoArbitros.remove(consultarNumeroJugadasEnHistorico());
				arbitroActual = consultarArbitroActual();
			
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
//		    System.out.print("Despues de empujar" + estadoActual.consultarTablero());
//		    historicoArbitros.add(estadoActual.clonar());
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