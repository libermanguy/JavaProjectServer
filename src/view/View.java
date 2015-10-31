package view;

import java.util.HashMap;

import general.Position;
import general.Solution;
import general.State;

// TODO: Auto-generated Javadoc
/**
* 
*  * <h1>View</h1>
* Interface that defines the Functions for a server side view
* <p>
* 
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/
public interface View {
	
	/**
	 * Display.
	 *
	 * @param arg the arg
	 */
	public void display(Object[] arg);
	
	/**
	 * Sets the cli.
	 */
	public void setCLI();

	/**
	 * Display str
	 *
	 * @param arg the arg
	 */
	public void displayStr(String arg);
	
}
