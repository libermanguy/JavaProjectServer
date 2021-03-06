package algorithms.search;

import java.util.Iterator;
import java.util.PriorityQueue;

import general.State;

//TODO: Auto-generated Javadoc
/**
* 
*  * <h1> The Class CommonSearcher. </h1>
* Represents the general searcher 
* <p>
* @param <T> the generic type
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/

public abstract class CommonSearcher<T> implements Searcher<T>
{
	
	/** The open list. */
	protected PriorityQueue<State<T>> openList;
	
	/** The evaluated nodes. */
	protected int evaluatedNodes;

	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#getNumberOfNodesEvaluated()
	 */
	public int getNumberOfNodesEvaluated()
	{
		return evaluatedNodes;
	}
	
	/**
	 * Instantiates a new common searcher.
	 */
	public CommonSearcher() {
		  openList=new PriorityQueue<State<T>>(new StateComperator<T>());
		  evaluatedNodes=0;
		 }

	/**
	 * Pop open list.
	 *
	 * @return the state
	 */
	public State<T> popOpenList() {
		  evaluatedNodes++;
		  return openList.poll();
		 }
	
	/**
	 * Adds the to open list.
	 *
	 * @param s the s
	 */
	public void addToOpenList(State<T> s) {
		  openList.add(s);
		 }
	
	/**
	 * Open list contains.
	 *
	 * @param s the s
	 * @return true, if successful
	 */
	public boolean openListContains(State<T> s)
	{
		return openList.contains(s);
	}
	
	/**
	 * Gets the state.
	 *
	 * @param curr the current state
	 * @return the state
	 */
	public State<T> getState(State<T> curr) 
	{
		for (Iterator<State<T>> it = openList.iterator(); it.hasNext(); ) {
			State<T> f = it.next();
	        if (f.equals(curr))
	            return f;
		}
		  return null;
	}
}
	
