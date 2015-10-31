package view;


import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import algorithms.mazeGenerators.Maze3d;

// TODO: Auto-generated Javadoc
/**
* 
*  * <h1>MyModelClientHandler</h1>
* Handles the new client thats connected to the server
* <p>
* 
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/
public class MyModelClientHandler extends Observable implements Runnable {
	
	/** The some client. */
	private Socket someClient;
	
	/** The out. */
	private ObjectOutputStream out;
	
	/** The in. */
	private ObjectInputStream in;
	
	/**
	 * Instantiates a new my model client handler.
	 *
	 * @param someClient the new client that connected
	 * @param out the out
	 * @param in the in
	 */
	public MyModelClientHandler(Socket someClient, ObjectOutputStream out ,ObjectInputStream in ) {

		this.someClient = someClient;
		this.out=out;
		this.in=in;

	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			setChanged();
			Maze3d mymaze=(Maze3d)in.readObject();
			notifyObservers(mymaze);
		} catch (Exception e) {
			e.printStackTrace();
		}					

	}

	/**
	 * This method Sends the solution to the client when its ready.
	 *
	 * @param o the o
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void sendClientSolution(Object o) throws IOException {
		out.writeObject(o);
		out.flush();
		out.close();
		in.close();
		someClient.close();
		

	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode(){
		return someClient.toString().hashCode();
	}
	

}
