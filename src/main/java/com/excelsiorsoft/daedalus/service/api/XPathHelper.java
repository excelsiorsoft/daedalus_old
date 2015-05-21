/**
 * 
 */
package com.excelsiorsoft.daedalus.service.api;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Simeon
 *
 */
public interface XPathHelper {
	
	Node getNode(String html, String path) throws Exception;
	
	Node getNode(Document doc, String path) throws Exception;
	
	NodeList getNodeList(String html, String xpathString) throws Exception;

}
