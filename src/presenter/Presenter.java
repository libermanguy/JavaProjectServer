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

public class Presenter implements Observer {

	/** The model. */
	Model model;

	/** The view. */
	View view;
	
	MyTCPIPServer server;
	
	HashMap<Integer,MyModelClientHandler> map;
	
	Properties prop;
	

	public Presenter(Model model, View view, MyTCPIPServer server) {
		super();
		this.model = model;
		this.view = view;
		this.server=server;
		map=new HashMap<Integer,MyModelClientHandler>();
	}

	@Override
	public void update(Observable o, Object arg) {
		
		
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

	public void setProperties(String file) throws Exception{
		this.prop = new Properties();
		prop.loadProp(file);
		model.setProperties(prop.getThreadcount(), prop.getWorkspace());
		server.setNumOfClients(prop.getConcurrentusers());
		server.setPort(prop.getPort());
	}
}
