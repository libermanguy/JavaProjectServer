package algorithms.search;

import general.State;

//TODO: Auto-generated Javadoc
/**
* 
*  * <h1> The Interface Heuristic. </h1>
* Represents the general  Heuristic
* <p>
* @param <T> the generic type
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/

public interface Heuristic<T> {
	
	/**
	 * Estimation.
	 *
	 * @param current the current
	 * @param goal the goal
	 * @return the double
	 */
	double estimation(State<T> current,State<T> goal);
}
