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

// TODO: Auto-generated Javadoc
/**
* 
*  * <h1>MyTCPIPServer</h1>
* The Server that responsible for managing the new client connected to get a solution for thier mazes
* <p>
* 
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/
public class MyTCPIPServer {

	/** The Constant SOLVE. */
	private static final String SOLVE = "solve";
	
	/** The port. */
	private int port;
	
	/** The executer. */
	private Executor executer;
	
	/** The server. */
	private ServerSocket server;
	
	/** The kill server. */
	private boolean killServer = true;
	
	/** The presenter. */
	private Presenter presenter;
	
	/** The num of clients. */
	int numOfClients = 5;
	
	/**
	 * Instantiates a new my tcpip server.
	 */
	public MyTCPIPServer(){
		this.port = 12345;
	}

	/**
	 * Start server every 5 sec will restart the socket
	 *when a new client connected an new client handler will be created to manage this client
	 * @throws Exception the exception
	 */
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
	
	/**
	 * Stop server.
	 */
	public void stopServer(){
		killServer = false;
	}

	/**
	 * Sets the presenter.
	 *
	 * @param p the new presenter
	 */
	public void setPresenter(Presenter p){
		this.presenter=p;
	}
	
	/**
	 * Sets the num of clients.
	 *
	 * @param num the new num of clients
	 */
	public void setNumOfClients(int num){
		this.numOfClients=num;
	}
	
	/**
	 * Sets the port.
	 *
	 * @param newport the new port
	 */
	public void setPort(int newport){
		this.port=newport;
	}

}