package algorithms.search;

import general.Solution;

//TODO: Auto-generated Javadoc
/**
* 
*  * <h1> The Interface Searcher.</h1>
* Interface which defines the methods needed for searcher class 
* <p>
* @param <T> the generic type
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/


public interface Searcher<T>
{
    
    /**
     * Search.
     *
     * @param s the s
     * @return the solution
     */
    public Solution<T> search(Searchable<T> s);
    
    /**
     * Gets the number of nodes evaluated.
     *
     * @return the number of nodes evaluated
     */
    public int getNumberOfNodesEvaluated();
    
}
