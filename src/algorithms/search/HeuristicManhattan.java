package algorithms.search;

import general.Position;
import general.State;

//TODO: Auto-generated Javadoc
/**
* 
*  * <h1> The Class HeuristicManhattan. </h1>
* Represents the  Heuristic which calculates by Manhattan distance 
* <p>
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/

public class HeuristicManhattan implements Heuristic<Position> {

	/* (non-Javadoc)
	 * @see algorithms.search.Heuristic#estimation(algorithms.search.State, algorithms.search.State)
	 */
	@Override
	public double estimation(State<Position> current, State<Position> goal) {
		Position c = new Position(current.getState());
		Position g = new Position(goal.getState());
		double cost = Math.abs(c.getX()-g.getX())+Math.abs(c.getY()-g.getY())+Math.abs(c.getZ()-g.getZ());
		//System.out.println(c.getX()+","+g.getX()+","+c.getY()+","+g.getY()+","+c.getZ()+","+g.getZ());
		//System.out.println(cost);
		return cost;
	}

}
