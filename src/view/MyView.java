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

/**
 * The Class MyView.
 */
public class MyView extends Observable implements View
{
	
	/** The cli. */
	CLI cli;
	

	
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
	

	public void startCLI(){
		cli.start();
	}
	
	
	public void setCLI(HashMap<String,Integer> cmdsHM) {
		cli=new CLI(new BufferedReader(new InputStreamReader(System.in)), 
				new PrintWriter(new OutputStreamWriter(System.out)), cmdsHM);
		
	}
	
	
	public class CLI extends Thread{
		
		/** The input . */
		protected BufferedReader in;
		
		/** The output. */
		protected PrintWriter out;
		
		/** String to Commands HashMap. */
		HashMap<String,Integer> cmdsHM;

		/** The line. */
		String line=null;
		
		/**
		 * Instantiates a new cli.
		 *
		 * @param input the input
		 * @param output the output
		 * @param commandsHM the commands hm
		 */
		public CLI(BufferedReader input,PrintWriter output,HashMap<String,Integer> commandsHM){
			in=input;
			out=output;
			cmdsHM=commandsHM;
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
						if (line.equals("")){
							out.write("");
							getInput();
						}
						else if (line.equals("?") || line.toLowerCase().equals("help")){
							PrintHelp();
							getInput();
							
						}
						else
						{
						String[] splited = line.split(" ");
					    ArrayList<String> stringList =new ArrayList<String>(Arrays.asList(splited));
					    int size=stringList.size();
					    Integer cmd = null;
					    if (size > 1){
					    	cmd=cmdsHM.get(splited[0]);
					    }
						if (cmd!=null && !(splited[1].equals("cross") || splited[1].equals("solution"))){
							stringList.remove(0);
							stringList.add(0, cmd.toString());
							splited = stringList.toArray(new String[stringList.size()]);
							setChanged();
							notifyObservers(splited);
						}
						else {
							 if (size>1){
									cmd=cmdsHM.get(stringList.get(0) +" "+stringList.get(1));
							    }
							 else{
								 cmd=null;
							 }
							if (cmd!=null){
								stringList.remove(0);
								stringList.remove(0);
								stringList.add(0, cmd.toString());
								splited = stringList.toArray(new String[stringList.size()]);
								setChanged();
								notifyObservers(splited);
							}
							else{
								String tempStr=null;
								 if (size>2){
									tempStr=stringList.get(0) +" "+stringList.get(1)+ " "+ stringList.get(2);
									cmd=cmdsHM.get(tempStr);
								    }
								 else{
									 cmd=null;
								 }
								if (cmd!=null){
									stringList.remove(0);
									stringList.remove(0);
									stringList.remove(0);
									if (tempStr.equals("generate 3d maze")){
										stringList.add(0, cmd.toString());
										splited = stringList.toArray(new String[stringList.size()]);
										setChanged();
										notifyObservers(splited);
									}
									else{
										stringList.add(0, cmd.toString());
										splited = stringList.toArray(new String[stringList.size()]);
										setChanged();
										notifyObservers(splited);
									}
								}
								else{
									out.write("Wrong command, write 'Help' or '?'");
									out.flush();
									getInput();
								}
							}	
						
						}
						getInput();
				      }
					
					
					}
					if (line.equals("exit")){
						setChanged();
						String arg[] = new String[1];
						arg[0] = cmdsHM.get("exit").toString();
						notifyObservers(arg);
						
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
			out.write("Issue on of the following commands:\n");
			out.write("dir <path>\n"
					+ "generate 3d maze <name> <other params>\n"
					+ "display <name>\n"
					+ "display cross section by {X,Y,Z} <index> for <name>\n"
					+ "save maze <name> <file name>\n"
					+"load maze <file name> <name>\n"
					+"maze size <name>\n"
					+"file size <name>\n"
					+"solve <name> <man\\bfs\\air>\n"
					+"display solution <name>\n"
					+"exit\n"
					);
			out.flush();
		}
		
	}




}

