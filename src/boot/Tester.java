package boot;

import general.Properties;

public class Tester {

	public static void main(String[] args) throws Exception {
			Properties prop = new Properties();
			
			prop.saveProp("c:\\temp\\prop_server.xml");
			
			prop.loadProp("c:\\temp\\prop_server.xml");
	}

}
