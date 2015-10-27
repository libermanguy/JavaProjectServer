 package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.mazeGenerators.*;
import algorithms.search.*;
import general.Position;
import general.Properties;
import general.Solution;
import io.*;

/**
 * The Class MyModel.
 */
public class MyModel extends Observable implements Model {


	HashMap<Maze3d,Solution<Position>> _solutionscache;
	
	/** The openfiles. */
	int openfiles;
	
	/** The openthreads. */
	int openthreads;
	
	ExecutorService executer;
	
	String workspace;
	
	/**
	 * Instantiates a new my model.
	 */
	public MyModel() {
		super();
		executer = Executors.newFixedThreadPool(20); // change later
		openfiles=0;
		openthreads=0;
		workspace = "c:\\temp\\";
		_solutionscache=new HashMap<Maze3d,Solution<Position>>();
	}

	


	public void saveCache(String file) throws Exception
	{
		OutputStream fileOut=new FileOutputStream(file);
		openfiles++;
		GZIPOutputStream zip = new GZIPOutputStream(fileOut);
		ObjectOutputStream out = new ObjectOutputStream(zip);
		out.writeObject(_solutionscache);
        out.close();
        zip.close();
		fileOut.close();	
		openfiles--;
	//	setChanged();
	//	notifyObservers("finish saving caching");
	}



	@SuppressWarnings("unchecked")
	
	public void loadCache(String file) throws Exception
	{
		this._solutionscache = new HashMap<Maze3d,Solution<Position>>();
		InputStream fileIn = new FileInputStream(file);
		openfiles++;
		GZIPInputStream zip = new GZIPInputStream(fileIn);
		ObjectInputStream in = new ObjectInputStream(zip);
		_solutionscache = (HashMap<Maze3d,Solution<Position>>) in.readObject();
		in.close();
		zip.close();
        fileIn.close();
		openfiles--;
	//	setChanged();
	//	notifyObservers("finish loading caching");
	}
	
	/* (non-Javadoc)
	 * @see model.Model#solve(java.lang.String, java.lang.String)
	 */
	@Override
	public void solve(Object maze,String alg,int id)
	{
		Maze3d mymaze=(Maze3d)maze;
		if (_solutionscache.get(mymaze) == null)
		{		
		try
				{
			_solutionscache.put(mymaze, executer.submit(new Callable<Solution<Position>>() {
				@Override
				public Solution<Position> call() throws Exception {
					Solution<Position> sol;
					openthreads++;
					Searcher<Position> searcher = null;
					switch (alg) {
				    case "bfs": 
				    	searcher = new SearcherBFS<Position>();
				    case "man":  
				    	searcher = new SearcherAStar<Position>(new HeuristicManhattan());
				    case "air":  
				    	searcher = new SearcherAStar<Position>(new HeuristicAirLine());
					   }
					sol = searcher.search(new SearchableMaze(mymaze));
					return sol;
				}
			}).get());
			setChanged();
			Object[] o={id,_solutionscache.get(mymaze)};
			notifyObservers(o);
			openthreads--;
				}
				catch (Exception e)
				{
					setChanged();
					notifyObservers("Failed to solve the maze");
					openthreads--;
				}
		}
		else 
		{
		//	setChanged();
		//	notifyObservers("Display,2,2,solution exists" + name);
			openthreads--;
			setChanged();
			Object[] o={id,_solutionscache.get(mymaze)};
			notifyObservers(o);		
		}
		
	}

	
	
	
	/* (non-Javadoc)
	 * @see model.Model#exit()
	 */
	@Override
	public void exit() {
		try{
			saveCache(workspace+ "cache.zip");
			}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("error cache save");
		}
		
		
		while (openfiles > 0 || openthreads > 0)
		{
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				setChanged();
			//	notifyObservers("Display,Error closing Model");
			//	notifyObservers();
			}
			setChanged();
			notifyObservers("Still there are open files: " + openfiles + " open threads:" + openthreads);
		}
		notifyObservers("Finish closing Model");
	}

	
	
	public void setProperties(int threadcount, String newworkspace) throws Exception{
		executer = Executors.newFixedThreadPool(threadcount);
		workspace = newworkspace;
		try{
			loadCache(workspace + "cache.zip");
			}
		catch (Exception e) {
			notifyObservers("Cache doesn`t exist");
		}
		
	}


}
