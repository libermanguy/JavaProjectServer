	package model;
import algorithms.mazeGenerators.Maze3d;
import general.Position;
import general.Solution;

// TODO: Auto-generated Javadoc
/**
 * The Interface Model.
 */
public interface Model {
	
	
	/**
	 * Solve.
	 *
	 * @param name the name
	 * @param alg the alg
	 */
	public void solve(Object maze,String alg,int id );
	
	/**
	 * Display solution.
	 *
	 * @param name the name
	 * @return the solution
	 * @throws Exception 
	 */
	
	public void exit();
	
	public void setProperties(String file) throws Exception;
}
