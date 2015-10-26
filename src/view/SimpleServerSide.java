package view;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServerSide {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerSocket server=new ServerSocket(12345);
			Socket someClient = server.accept();
			ObjectOutputStream output=new ObjectOutputStream(someClient.getOutputStream());
			ObjectInputStream input=new ObjectInputStream(someClient.getInputStream());
			output.writeObject("you are connected to the server");
			output.flush();

			String messageIn=(String)input.readObject();
			System.out.println("message from the client: "+messageIn);
			output.writeObject("bye");
			output.flush();

			output.close();
			input.close();
			someClient.close();
			server.close();
		} catch (Exception e) {
			System.out.println("tiered of waiting for connection");
		}


	}

}
