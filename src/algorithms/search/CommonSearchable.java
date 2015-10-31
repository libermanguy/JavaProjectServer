package algorithms.search;

import java.util.ArrayList;

import general.State;
//TODO: Auto-generated Javadoc
/**
* 
*  * <h1> The Class CommonSearchable. </h1>
* Represents the general searchable problem
* <p>
* 
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/

public abstract class CommonSearchable<T> implements Searchable<T> {

	/** The _start state. */
	State<T> _startState;
	
	/** The _goal state. */
	State<T> _goalState;
	
	/**
	 * Instantiates a new common searchable.
	 *
	 * @param _startState the _start state
	 * @param _goalState the _goal state
	 */
	public CommonSearchable(State<T> _startState, State<T> _goalState) {
		super();
		this._startState = _startState;
		this._goalState = _goalState;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getStartState()
	 */
	@Override
	public State<T> getStartState() {
		return _startState;
	}

	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getGoalState()
	 */
	@Override
	public State<T> getGoalState() {
		return _goalState;
	}
	
	/* (non-Javadoc)
	 * @see algorithms.search.Searchable#getAllPossibleStates(algorithms.search.State)
	 */
	@Override
	abstract public ArrayList<State<T>> getAllPossibleStates(State<T> s);
}
