/**
 * 
 */
package com.excelsiorsoft.daedalus.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.StringWriter;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;

/**
 * @author Simeon
 *
 */
@Component("htmlCleaner")
public class HtmlCleaner {
	
	private final Log log = LogFactory.getLog(getClass());
	
	public Document clean(String html) throws Exception{
		Document xml = null;
		InputStream htmlInputStream = null;
		ByteArrayOutputStream baos = null;
		PrintStream ps = null;
		/*try{*/
		htmlInputStream = IOUtils.toInputStream(html, /*encoding*/"UTF-8");
		
		baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
        
        Tidy tidy = new Tidy();
        tidy.setMakeClean(true);
        tidy.setTidyMark(false);
        tidy.setHideComments(true);
        tidy.setInputEncoding("UTF-8");
	    tidy.setOutputEncoding("UTF-8");
	    tidy.setWraplen(Integer.MAX_VALUE);
	    tidy.setPrintBodyOnly(false);
	    //tidy.setXmlOut(true);
	    tidy.setSmartIndent(true);
	    tidy.setFixUri(true);
	    tidy.setDocType("auto");
	    tidy.setXHTML(true);
	    tidy.setForceOutput(true);//important
	    
	    StringWriter writer = new StringWriter();
	    tidy.getConfiguration().printConfigOptions(writer, true);
	    log.info("configurations: "+writer.toString());
        
		xml = tidy.parseDOM(htmlInputStream, baos);
		
		log.info("pretty printed: \n");
		tidy.pprint(xml, System.out);
		
	    
	  
	    log.info("\n"+baos.toString("UTF-8"));
	    log.info("xml: "+xml);
	    log.info(xml.getDocumentElement().getNodeName());
	    log.info(xml.getDocumentElement().getChildNodes());
		 
		return xml;
		/*}finally{
			htmlInputStream.close();
			baos.close();
			ps.close();
			
		}*/
	}
	
	
	public String prettyPrint(String html) throws Exception{
		Document xml = null;
		InputStream htmlInputStream = null;
		ByteArrayOutputStream baos = null;
		PrintStream ps = null;
		/*try{*/
		htmlInputStream = IOUtils.toInputStream(html, /*encoding*/"UTF-8");
		
		baos = new ByteArrayOutputStream();
        //ps = new PrintStream(baos);
        
        Tidy tidy = new Tidy();
        tidy.setMakeClean(true);
        tidy.setTidyMark(false);
        tidy.setHideComments(true);
        tidy.setInputEncoding("UTF-8");
	    tidy.setOutputEncoding("UTF-8");
	    tidy.setWraplen(Integer.MAX_VALUE);
	    //tidy.setPrintBodyOnly(true);
	    tidy.setXmlOut(true);
	    tidy.setSmartIndent(true);
	    tidy.setFixUri(true);
	    tidy.setDocType("auto");
	    tidy.setXHTML(true);
	    tidy.setForceOutput(true);//important
	    
	    StringWriter writer = new StringWriter();
	    tidy.getConfiguration().printConfigOptions(writer, true);
	    log.info("configurations: "+writer.toString());
        
		xml = tidy.parseDOM(htmlInputStream, baos);
		
		log.info("pretty printed: \n");
		tidy.pprint(xml, baos);
		
	    
	  
	    log.info("\n"+baos.toString("UTF-8"));
	    log.info("xml: "+xml);
	    log.info(xml.getDocumentElement().getNodeName());
	    log.info(xml.getDocumentElement().getChildNodes());
		 
		return baos.toString("UTF-8");
		/*}finally{
			htmlInputStream.close();
			baos.close();
			ps.close();
			
		}*/
	}

}
