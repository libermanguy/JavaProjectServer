	package model;
import algorithms.mazeGenerators.Maze3d;
import general.Position;
import general.Solution;

// TODO: Auto-generated Javadoc
/**
* 
*  * <h1>Model</h1>
* Interface for a server side model
* <p>
* 
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/
public interface Model {
	
	
	/**
	 * Solve the game using the specific algorithm that is given.
	 *
	 * @param maze the maze
	 * @param alg the alg
	 * @param id the id
	 */
	public void solve(Object maze,String alg,int id );
	
	/**
	 * Display solution.
	 *
	 * @return the solution
	 */
	
	public void exit();
	
	/**
	 * Sets the properties.
	 *
	 * @param threadcount the number of threads in the pool
	 * @param newworkspace the where related files will be saved
	 * @throws Exception the exception
	 */
	public void setProperties(int threadcount, String newworkspace) throws Exception;
}
