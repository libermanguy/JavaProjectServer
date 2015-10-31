/*
 * 
 */
package general;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

// TODO: Auto-generated Javadoc
/**
* 
*  * <h1>Properties</h1>
* The Server Properties file
* <p>
* 
*
* @author  Guy Liberman & Omri Polnikviat
* @version 1.0
* @since   2015-10-31
*/
@SuppressWarnings("serial")
public class Properties implements Serializable 
{
	
	/** The threadcount. */
	int threadcount;
	
	/** The port. */
	int port;
	
	/** The concurrentusers. */
	int concurrentusers; 
	
	/** The workspace. */
	String workspace;
	
	/**
	 * Instantiates a new properties.
	 */
	public Properties() {
		threadcount = 5;
		port=2000;
		concurrentusers = 5;
		workspace="default";
	}
	
	/**
	 * Load prop.
	 *
	 * @param file the properties file
	 * @throws Exception the exception
	 */
	public void loadProp(String file) throws Exception
	{
		File inputfile = new File(file);
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
		Document doc = dbuilder.parse(inputfile);
		doc.getDocumentElement().normalize();
		NodeList nlist = doc.getElementsByTagName("properties");
		Node n = nlist.item(0);
		Element e = (Element)n;
		threadcount = Integer.parseInt(e.getElementsByTagName("threadcount").item(0).getTextContent());
		port = Integer.parseInt(e.getElementsByTagName("port").item(0).getTextContent());
		concurrentusers = Integer.parseInt(e.getElementsByTagName("concurrentusers").item(0).getTextContent());
		workspace =  e.getElementsByTagName("workspace").item(0).getTextContent();
	}
	
	/**
	 * Save prop.
	 *
	 * @param file the properties file
	 * @throws Exception the exception
	 */
	public void saveProp(String file) throws Exception
	{
		File outputfile = new File(file);
		DocumentBuilderFactory dbfactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dbuilder = dbfactory.newDocumentBuilder();
		Document doc = dbuilder.newDocument();
		Element e = doc.createElement("properties");
		doc.appendChild(e);
		
		Element e2 = doc.createElement("threadcount");
		e2.setTextContent(threadcount+"");
		e.appendChild(e2);

		Element e4 = doc.createElement("port");
		e4.setTextContent(port+"");
		e.appendChild(e4);
		
		Element e5 = doc.createElement("concurrentusers");
		e5.setTextContent(concurrentusers+"");
		e.appendChild(e5);
				
		Element e6 = doc.createElement("workspace");
		e6.setTextContent(workspace	);
		e.appendChild(e6);
		
		TransformerFactory ts = TransformerFactory.newInstance();
		Transformer transformer = ts.newTransformer();
		DOMSource dm = new DOMSource(doc);
		transformer.transform(dm, new StreamResult(outputfile));
		}

	/**
	 * Gets the thread count from the properties file.
	 *
	 * @return the threadcount
	 */
	public int getThreadcount() {
		return threadcount;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * Gets the allowed number of clients.
	 *
	 * @return the concurrentusers
	 */
	public int getConcurrentusers() {
		return concurrentusers;
	}

	/**
	 * Gets the workspace.
	 *
	 * @return the workspace
	 */
	public String getWorkspace() {
		return workspace;
	}
	
}
