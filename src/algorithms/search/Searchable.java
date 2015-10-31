package algorithms.search;

import java.util.ArrayList;

import general.State;

//TODO: Auto-generated Javadoc
/**
* 
*  * <h1> The Interface Searchable.</h1>
* Interface which defines the methods needed for searchable class 
* <p>
* @param <T> the generic type
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/

public interface Searchable<T> {
	
	/**
	 * Gets the start state.
	 *
	 * @return the start state
	 */
	State<T> getStartState();
	
	/**
	 * Gets the goal state.
	 *
	 * @return the goal state
	 */
	State<T> getGoalState();
	
	/**
	 * Gets the all possible states.
	 *
	 * @param s the s
	 * @return the all possible states
	 */
	ArrayList<State<T>> getAllPossibleStates(State<T> s);
}
