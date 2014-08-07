package com.excelsiorsoft.daedalus;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class FindElementsByIdWithXPath {

	public static void main(String[] args) throws Exception {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setValidating(false);
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.parse(FindElementsByIdWithXPath.class.getResourceAsStream("ex_from_the_web.html"));
		//Document doc = db.parse(new FileInputStream(new File("ex_from_the_web.html")));

		XPathFactory factory = XPathFactory.newInstance();

		XPath xpath = factory.newXPath();

		String expression;
		Node node;
		NodeList nodeList;

		// 1. elements with id '1'
		expression = "id('1')";
		node = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
		System.out.println("1. " + node.getNodeName());

		// 2. all elements under element with id '1'
		expression = "id('1')/entry";
		nodeList = (NodeList) xpath.evaluate(expression, doc,
				XPathConstants.NODESET);
		System.out.print("2. ");
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.print(nodeList.item(i).getNodeName() + " ");
		}
		System.out.println();

		// 3. elements with id 1, 2 or 4
		expression = "id('1 2 4')";
		nodeList = (NodeList) xpath.evaluate(expression, doc,
				XPathConstants.NODESET);
		System.out.print("3. ");
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.print(nodeList.item(i).getNodeName() + " ");
		}
		System.out.println();

		// 4. element that does not exist
		expression = "id('UNKNOWN')";
		node = (Node) xpath.evaluate(expression, doc, XPathConstants.NODE);
		System.out.println("4. " + node);
	}
}
