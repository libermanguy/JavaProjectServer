package view;

import java.util.HashMap;

import general.Position;
import general.Solution;
import general.State;

// TODO: Auto-generated Javadoc
/**
 * The Interface View.
 */
public interface View {
	
	/**
	 * Display.
	 *
	 * @param arg the arg
	 */
	public void display(Object[] arg);
	
	public void setCLI(HashMap<String, Integer> commands);

	
}
