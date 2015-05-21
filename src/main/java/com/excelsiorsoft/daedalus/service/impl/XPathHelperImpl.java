/**
 * 
 */
package com.excelsiorsoft.daedalus.service.impl;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.excelsiorsoft.daedalus.service.api.XPathHelper;

/**
 * @author Simeon
 * 
 */
@Component("XPathHelper")
public class XPathHelperImpl implements XPathHelper{
	
	private final Log log = LogFactory.getLog(getClass());

	private DocumentBuilder builder;
	private static final XPathFactory xpathFactory = XPathFactory.newInstance();
	
	NamespaceContext context = new NamespaceContextMap(
	        "x", "http://www.w3.org/1999/xhtml");
	
	@Autowired
	private HtmlCleaner htmlCleaner;
	
	XPathHelperImpl() throws Exception {


		DocumentBuilderFactory domFactory = DocumentBuilderFactory
				.newInstance();
		domFactory.setNamespaceAware(true);
		domFactory.setValidating(false);

		
			/*domFactory.setFeature("http://xml.org/sax/features/namespaces",
					false);

			domFactory.setFeature("http://xml.org/sax/features/validation",
					false);
			domFactory
					.setFeature(
							"http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
							false);
			domFactory
					.setFeature(
							"http://apache.org/xml/features/nonvalidating/load-external-dtd",
							false);*/

			builder = domFactory.newDocumentBuilder();

			
			  builder.setEntityResolver(new EntityResolver() {
			  
			  @Override public InputSource resolveEntity(String publicId,
			  String systemId) throws SAXException, IOException {
			  
			  return new InputSource(new StringReader(""));
			  
			  } });

	}
	
	public Node getNode(String html, String path) throws Exception {
		return getNode(getDocument(html), path);
	}

	public Node getNode(Document doc, String path) throws Exception {
		XPath xpath = xpathFactory.newXPath();
		return (Node) xpath.evaluate(path, doc, XPathConstants.NODE);
	}


	
	public Document getDocument(String xml) throws Exception {

		Document result = htmlCleaner.clean(xml);
		log.info(result.getDocumentElement().getNodeName());
		log.info(result.getNodeName());
		return result/*.getDocumentElement().getOwnerDocument()*/;
	}
	
	
	public NodeList getNodeList(String html, String xpathString)
			throws Exception {

		Document document = getDocument(html);
		XPath xpath = xpathFactory.newXPath();
		xpath.setNamespaceContext(context);
		return (NodeList) xpath.evaluate(xpathString, document,
				XPathConstants.NODESET);

	}

}
