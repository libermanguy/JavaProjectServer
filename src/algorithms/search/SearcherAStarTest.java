package algorithms.search;


import static org.junit.Assert.*;

import org.junit.Test;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import general.Position;
//TODO: Auto-generated Javadoc
/**
* 
*  * <h1> The Class SearcherAStarTest.</h1>
* Junit testing for class SearcherAStarTest
* <p>
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/
import model.SearchableMaze;

// TODO: Auto-generated Javadoc
/**
 * 
 */
public class SearcherAStarTest {

	/**
	 * Test get number of nodes evaluated.
	 */
	@Test
	public void testGetNumberOfNodesEvaluated() {
		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d newmaze  = mg.generate(10,10,10);	
		new SearchableMaze(newmaze);
		Searcher<Position> searcher = new SearcherAStar<Position>(new HeuristicAirLine());
		assertEquals(0, searcher.getNumberOfNodesEvaluated());
	}

	/**
	 * Test searcher a star.
	 */
	@Test
	public void testSearcherAStar() {
		new SearcherAStar<Position>(null);
	}

	/**
	 * Test search.
	 */
	@Test
	public void testSearch() {
		MyMaze3dGenerator mg = new MyMaze3dGenerator();
		Maze3d newmaze  = mg.generate(10,10,10);	
		new SearchableMaze(newmaze);
		Searcher<Position> searcher = new SearcherAStar<Position>(new HeuristicAirLine());
		searcher.search(null);
		searcher = new SearcherAStar<Position>(new HeuristicManhattan());
		searcher.search(null);
	}
}
