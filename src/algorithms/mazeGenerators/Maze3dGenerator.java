package algorithms.mazeGenerators;

//TODO: Auto-generated Javadoc
/**
* 
*  * <h1> The Interface Maze3dGenerator. </h1>
* 
* <p>
* 
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/

public interface Maze3dGenerator 
{
	
	/**
	 * Generate.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the maze3d
	 */
	public Maze3d generate(int x , int y , int z);
	
	/**
	 * Measure algorithm time.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the string
	 */
	public String measureAlgorithmTime(int x , int y , int z);
}
