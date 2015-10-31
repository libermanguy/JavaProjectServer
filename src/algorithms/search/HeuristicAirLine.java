package algorithms.search;

import general.Position;
import general.State;

//TODO: Auto-generated Javadoc
/**
* 
*  * <h1> The Class HeuristicAirLine. </h1>
* Represents the  Heuristic which calculates by Air distance
* <p>
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/


public class HeuristicAirLine implements Heuristic<Position> {

	/* (non-Javadoc)
	 * @see algorithms.search.Heuristic#estimation(algorithms.search.State, algorithms.search.State)
	 */
	@Override
	public double estimation(State<Position> current, State<Position> goal) {
		Position c = current.getState();
		Position g = goal.getState();
		double cost = Math.sqrt(Math.pow(c.getX()-g.getX(),2)+Math.pow(c.getY()-g.getY(),2)+Math.pow(c.getZ()-g.getZ(),2));
		return cost;
	}

}
