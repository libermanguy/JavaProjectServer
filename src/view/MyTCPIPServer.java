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
	int numOfClients = 5;
	public MyTCPIPServer(){
		this.port = 12345;
	}

	public void startServer(){
		System.out.println(numOfClients);
		executer = Executors.newFixedThreadPool(numOfClients);
		try {
			server=new ServerSocket(this.port);
			System.out.println("Open Server accept connections on port " + port);
			System.out.println("Max Client " + numOfClients);
			System.out.println("-----------------------------------------------"+System.getProperty("line.separator"));
					while(killServer){
						System.out.println("Waiting for client");
						Socket someClient = server.accept();
						System.out.println("New client connected..");
						ObjectInputStream input=new ObjectInputStream(someClient.getInputStream());
						ObjectOutputStream output=new ObjectOutputStream(someClient.getOutputStream());
						String line = (String) input.readObject();
						if(line.equals(SOLVE)){
							MyModelClientHandler ch=new MyModelClientHandler(someClient,output,input);
							ch.addObserver(presenter);
							executer.execute(ch);
							
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
	
	public void setNumOfClients(int num){
		this.numOfClients=num;
	}
	
	public void setPort(int newport){
		this.port=newport;
	}

}