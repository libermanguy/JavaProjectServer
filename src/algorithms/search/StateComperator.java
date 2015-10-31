package algorithms.search;
import java.util.Comparator;

import general.State;


//TODO: Auto-generated Javadoc
/**
* 
*  * <h1> The Class StateComperator.</h1>
* Deciding how to compare states
* 
*  <p>
* @param <T> the generic type
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/


public class StateComperator<T> implements Comparator<State<T>>
{

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(State<T> x, State<T> y)
	    {
	        return Double.compare(x.getCost(),y.getCost());
	    }
}