package presenter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.Executors;

import algorithms.mazeGenerators.Maze3d;
import general.Position;
import general.Properties;
import model.Model;
import view.MyModelClientHandler;
import view.MyTCPIPServer;
import view.View;

// TODO: Auto-generated Javadoc
/**
* 
*  * <h1>Presenter</h1>
* Server side Presenter for the Maze Game
* <p>
* 
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/
public class Presenter implements Observer {

	/** The model. */
	Model model;

	/** The view. */
	View view;
	
	/** The server. */
	MyTCPIPServer server;
	
	/** The map. */
	HashMap<Integer,MyModelClientHandler> map;
	
	/** The prop. */
	Properties prop;
	

	/**
	 * Instantiates a new presenter.
	 *
	 * @param model the model
	 * @param view the view
	 * @param server the server
	 */
	public Presenter(Model model, View view, MyTCPIPServer server) {
		super();
		this.model = model;
		this.view = view;
		this.server=server;
		map=new HashMap<Integer,MyModelClientHandler>();
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		
		if (o==view){
			String strArr[] = (String[])arg;
			switch(strArr[0])
			{
				case "start": 
				try {
					server.startServer();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
				
				case "stop": 
					server.stopServer();
				break;
				case "load":
				try {
					setProperties(strArr[1]);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					
					view.displayStr("Please load valid XML file or server will fail");
				}
					//to complete
				break;
			}
		}
		if (o.getClass()==MyModelClientHandler.class){
			MyModelClientHandler my=(MyModelClientHandler)o;
			map.put(my.hashCode(),my);
			model.solve(arg,"air",my.hashCode());
		}
		
		if (o==model){
			Object [] args=(Object[])arg;
			if (map.get((Integer)args[0])!=null){
				try {
					map.get((Integer)args[0]).sendClientSolution(args[1]);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		

	
	}

	/**
	 * Sets the properties.
	 *
	 * @param file the new properties
	 * @throws Exception the exception
	 */
	public void setProperties(String file) throws Exception{
		this.prop = new Properties();
		prop.loadProp(file);
		model.setProperties(prop.getThreadcount(), prop.getWorkspace());
		server.setNumOfClients(prop.getConcurrentusers());
		server.setPort(prop.getPort());
	}
}