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

public class MyModelClientHandler extends Observable implements Runnable {
	
	private Socket someClient;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	public int guy=5;
	public MyModelClientHandler(Socket someClient, ObjectOutputStream out ,ObjectInputStream in ) {

		this.someClient = someClient;
		this.out=out;
		this.in=in;

	}

	@Override
	public void run() {
		try {
			System.out.println("Got object- send for solving");
			setChanged();
			Maze3d mymaze=(Maze3d)in.readObject();
			notifyObservers(mymaze);
		} catch (Exception e) {
			e.printStackTrace();
		}					

	}

	/**
	 * This method Sends notification on the image conversion ends
	 * @param bos
	 * @throws IOException
	 */
	public void sendClientSolution(Object o) throws IOException {
		out.writeObject(o);
		out.flush();
		out.close();
		in.close();
		someClient.close();
		

	}

	/**
	 * This methods reads image from client output stream
	 * @return
	 * @throws IOException
	 */
	private InputStream readInputClient() throws IOException {
		DataInputStream dis = new DataInputStream(someClient.getInputStream());
		int len = dis.readInt();
		System.out.println("Image Size: " + len/1024 + "KB");
		byte[] data = new byte[len];
		dis.readFully(data);
		InputStream ian = new ByteArrayInputStream(data);
		return ian;
	}

	@Override
	public int hashCode(){
		return someClient.toString().hashCode();
	}
	

}
