package view;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
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

	public void startServer() throws Exception{
		killServer = true;
		new Thread(new Runnable() {
			public void run() {
				executer = Executors.newFixedThreadPool(numOfClients);
				try {
				server=new ServerSocket(port);
				server.setSoTimeout(5000);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.out.println("Open Server accept connections on port " + port);
				System.out.println("Max Client " + numOfClients);
				System.out.println("-----------------------------------------------"+System.getProperty("line.separator"));

			
					try {
						while(killServer){
								System.out.println("Waiting for client");
								try
								{
								Socket someClient = server.accept();
									if (someClient !=null)
									{
										System.out.println("New client connected..");
										ObjectInputStream input=new ObjectInputStream(someClient.getInputStream());
										ObjectOutputStream output=new ObjectOutputStream(someClient.getOutputStream());
										String line = (String) input.readObject();
										if(line.equals(SOLVE)){
											MyModelClientHandler ch=new MyModelClientHandler(someClient,output,input);
											ch.addObserver(presenter);
											executer.execute(new Thread(ch));
										}
									}
								}
								catch(SocketTimeoutException e)
								{
									
								}
							}
						System.out.println("closing server");
					server.close();
					} catch (Exception e) {
						System.out.println("tired of waiting for connection");
					}finally {
						((ExecutorService)executer).shutdown();
					}		
				}
			}).start();
			
		
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