package view;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import presenter.Presenter;

public class MyTCPIPServer {

	private static final String SOLVE = "solve";
	private int port;
	private Executor executer;
	private ServerSocket server;
	private boolean killServer = true;
	private Presenter presenter;
	public MyTCPIPServer(int port){
		this.port = port;
	}

	public void startServer(int numOfClients){
		executer = Executors.newFixedThreadPool(numOfClients);
		try {
			server=new ServerSocket(this.port);
			while(killServer){
				System.out.print("Waiting for client");
				Socket someClient = server.accept();
				System.out.println("New client connected..");
				ObjectInputStream input=new ObjectInputStream(someClient.getInputStream());
				ObjectOutputStream output=new ObjectOutputStream(someClient.getOutputStream());
				String line = (String) input.readObject();
				if(line.equals(SOLVE)){
					System.out.println("Start solving");
					executer.execute(new Thread(new MyModelClientHandler(someClient)));
					
				}
			}
			server.close();
		} catch (Exception e) {
			System.out.println("tiered of waiting for connection");
		}finally {
			((ExecutorService)executer).shutdown();
		}		
	}
	public void stopServer(){
		killServer = false;
	}

	public void setPresenter(Presenter p){
		this.presenter=p;
	}

}