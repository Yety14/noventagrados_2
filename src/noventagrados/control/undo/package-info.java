/**
 * @author <a href="vvv1005@alu.ubu.es">Víctor Vidal Vivanco</a>
 * @author <a href="glz1001@alu.ubu.es">Guillermo López de Arechavaleta
 *         Zapatero</a>
 * 
 *         Proporciona el mecanismo para deshacer jugadas en el juego ("undo"),
 *         implementado mediante una jerarquía de herencia que incluye una
 *         interfaz, una clase abstracta y dos clases concretas. Se soportan dos
 *         enfoques para deshacer: almacenamiento de jugadas realizadas o
 *         almacenamiento de clones de árbitros en diferentes estados de la
 *         partida.
 * 
 *         Proyecto desarrollado como parte de la asignatura de Metodología de la 
 * 		   Programación, 2º Grado Ingeniería Informática, Universidad de Burgos.
 * 
 * @since 1.0
 * @see noventagrados.control.undo.MaquinaDelTiempoConArbitros
 * @see noventagrados.control.undo.MaquinaDelTiempoConJugadas
 * @see noventagrados.control.undo.MecanismoDeDeshacer
 * @see noventagrados.control.undo.MecanismoDeDeshacerAbstracto
 * 
 */
package noventagrados.control.undo;
