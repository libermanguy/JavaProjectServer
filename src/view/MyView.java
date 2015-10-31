package view;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;

import general.Position;
import general.Solution;
import general.State;

// TODO: Auto-generated Javadoc
/**
* 
*  * <h1>MyView</h1>
* View Layer that manages the input from the client and forward the commands to the model 
* <p>
* 
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/
public class MyView extends Observable implements View
{
	
	/** The cli. */
	CLI cli;
	
/**
 * Instantiates a new my view.
 */
public MyView(){
	setCLI();
}
	
	/* (non-Javadoc)
	 * @see view.View#display(java.lang.Object[])
	 */
	@Override
	public void display(Object[] arg){
		System.out.println(arg);
	}

	/* (non-Javadoc)
	 * @see view.View#displayStr(java.lang.String)
	 */
	public void displayStr(String arg){
		System.out.println(arg);
	}
	

	/**
	 * Start cli.
	 */
	public void startCLI(){
		cli.start();
	}
	
	
	/* (non-Javadoc)
	 * @see view.View#setCLI()
	 */
	public void setCLI() {
		cli=new CLI(new BufferedReader(new InputStreamReader(System.in)), 
				new PrintWriter(new OutputStreamWriter(System.out)));
		
	}
	
	
	/**
	 * The Class CLI.
	 */
	public class CLI extends Thread{
		
		/** The input . */
		protected BufferedReader in;
		
		/** The output. */
		protected PrintWriter out;
		

		/** The line. */
		String line=null;
		
		/**
		 * Instantiates a new cli.
		 *
		 * @param input the input
		 * @param output the output
		 */
		public CLI(BufferedReader input,PrintWriter output){
			in=input;
			out=output;
		}
		
		
		/* (non-Javadoc)
		 * @see java.lang.Thread#start()
		 */
		public void start(){
			new Thread(new Runnable() {
				@Override
				public void run() {
					
					getInput();
					
					while(!line.equals("exit")){
						String[] splited = line.split(" ");
						line=splited[0];
						if (line.equals("")){
							out.write("");
							getInput();
						}
						else if (line.equals("?") || line.toLowerCase().equals("help")){
							PrintHelp();
							getInput();
							
						}
						else if (line.toLowerCase().equals("start")){
							setChanged();
							notifyObservers(splited);
							getInput();
						}
						
						else if (line.toLowerCase().equals("stop")){
							setChanged();
							notifyObservers(splited);
							getInput();
						}
						else if (line.toLowerCase().equals("load")){
							setChanged();
							notifyObservers(splited);
							getInput();

						}
						else{
							out.write("Wrong command, write 'Help' or '?'");
							out.flush();
							getInput();
						}
					}	
				

				    }
				  }).start();


				
			}
		
		/**
		 * Gets the input.
		 *
		 * @return the input
		 */
		public void getInput(){
			try {
				line = in.readLine();
			} 
			catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		/**
		 * Prints the help.
		 */
		public void PrintHelp(){
			out.write("\nIssue on of the following commands:\n");
			out.write("load properties\n"
					+ "start\n"
					+ "stop\n\n"
					);
			out.flush();
		}
		
	}




}

