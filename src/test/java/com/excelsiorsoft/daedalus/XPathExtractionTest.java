package com.excelsiorsoft.daedalus;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.DOMAttrImpl;
import org.w3c.tidy.DOMDocumentImpl;
import org.w3c.tidy.DOMElementImpl;
import org.w3c.tidy.DOMNodeListImpl;
import org.w3c.tidy.DOMTextImpl;

import com.excelsiorsoft.daedalus.service.XPathHelper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:/META-INF/spring/integration/data-collection-context.xml"})
public class XPathExtractionTest {
	
	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired @Qualifier("XPathHelper")
	private XPathHelper xpathHelper;
	
	@Test
	public void testCurrentExpirationCycleExtraction() throws Exception{
		
		Resource resource = context.getResource("/META-INF/spring/integration/initial.xml");
		//Resource resource = context.getResource("/META-INF/spring/integration/ex_from_the_web.html");
		
		String html = IOUtils.toString(resource.getInputStream());
		
/*		NodeList nodeList = xpathHelper.getNodeList(html, "//table[@id='yfncsumtab']//tr[@valign='top']/td/strong");
	
		
		log.info("length: "+nodeList.getLength());
		
		for(int i=0;i<nodeList.getLength();i++){
		Node node = nodeList.item(i);
		log.info("getTagName: " +((DOMElementImpl)node).getTagName());
		log.info("getNodeValue: " +((DOMElementImpl)node).getNodeValue());
		}
		*/
		
		Node node = xpathHelper.getNode(html, "//table[@id='yfncsumtab']//tr[@valign='top']/td/strong/text()");
		log.info("text: " +((DOMTextImpl)node).getNodeValue());
		
		
		/*final QuoteRequestor requestor = context.getBean(QuoteRequestor.class);
		
		requestor.requestQuote("SLW");*/
	}
	
	@Test
	public void testRestExpirationCyclesExtraction() throws Exception{
		
		Resource resource = context.getResource("/META-INF/spring/integration/initial.xml");
		//Resource resource = context.getResource("/META-INF/spring/integration/ex_from_the_web.html");
		
		String html = IOUtils.toString(resource.getInputStream());
		
		NodeList nodeListofValues = xpathHelper.getNodeList(html, "//table[@id='yfncsumtab']//tr[@valign='top']/td/a/@href");
		NodeList nodeListofNames = xpathHelper.getNodeList(html, "//table[@id='yfncsumtab']//tr[@valign='top']/td/a/text()");
	
		
		log.info("length: "+nodeListofValues.getLength());
		
		for(int value=0, name=0;value<nodeListofValues.getLength()&&name<nodeListofNames.getLength();value++,name++){
		Node valueNode = nodeListofValues.item(value);
		Node nameNode = nodeListofNames.item(name);

		
		log.info("expirationCycle" +"["+(value+1)+"]: "+  ((DOMTextImpl)nameNode).getNodeValue() +" => "+((DOMAttrImpl)valueNode).getNodeValue() );
		//log.info("getNodeValue: " +((DOMAttrImpl)valueNode).getNodeValue());
		}
		
		
		
		/*log.info("text: " +((DOMTextImpl)node).getNodeValue());*/
		
		
		/*final QuoteRequestor requestor = context.getBean(QuoteRequestor.class);
		
		requestor.requestQuote("SLW");*/
	}
	
	
	
	
	@Test
	public void testFirstStrikePricesExtraction() throws Exception{
		
		Resource resource = context.getResource("/META-INF/spring/integration/initial.xml");
		//Resource resource = context.getResource("/META-INF/spring/integration/ex_from_the_web.html");
		
		String html = IOUtils.toString(resource.getInputStream());
		//"//table[@id='yfncsumtab']//tr[@valign='top']//table[@class='yfnc_datamodoutline1']//tr[2]/td[class='yfnc_h']/strong/text()
		Node strikeRow = xpathHelper.getNode(html, "//table[@id='yfncsumtab']//tr[@valign='top']//table[6]//tr[2]");
		
		log.info("strikeRow: " +strikeRow);
		
		DOMNodeListImpl strikeAttributes = (DOMNodeListImpl)strikeRow.getChildNodes();
		
		log.info("strike attributes list: [" +strikeAttributes.getLength()+"]");
		
		
		for (int strkAttrNumbr = 0; strkAttrNumbr < strikeAttributes
				.getLength(); strkAttrNumbr++) {
			String attrValue = null;
			switch (strkAttrNumbr) {
			case 0:
				attrValue = ((DOMTextImpl) strikeAttributes.item(strkAttrNumbr)
						.getFirstChild().getFirstChild().getFirstChild())
						.getNodeValue();
				log.info("strike: " + attrValue);
				break;

			case 4:
				attrValue = ((DOMTextImpl) strikeAttributes.item(strkAttrNumbr)
						.getFirstChild()).getNodeValue();
				log.info("bid: " + attrValue);
				break;

			case 5:
				attrValue = ((DOMTextImpl) strikeAttributes.item(strkAttrNumbr)
						.getFirstChild()).getNodeValue();
				log.info("ask: " + attrValue);
				break;
			}

		}
		
		/*NodeList nodeListofNames = xpathHelper.getNodeList(html, "//table[@id='yfncsumtab']//tr[@valign='top']/td/a/text()");
	
		
		log.info("length: "+nodeListofValues.getLength());
		
		for(int value=0, name=0;value<nodeListofValues.getLength()&&name<nodeListofNames.getLength();value++,name++){
		Node valueNode = nodeListofValues.item(value);
		Node nameNode = nodeListofNames.item(name);

		
		log.info("expirationCycle" +"["+(value+1)+"]: "+  ((DOMTextImpl)nameNode).getNodeValue() +" => "+((DOMAttrImpl)valueNode).getNodeValue() );
		
		}*/
		
		
		
		
	}
	
	
	@Test
	public void testAllStrikePricesExtraction() throws Exception{
		
		Resource resource = context.getResource("/META-INF/spring/integration/initial.xml");
		//Resource resource = context.getResource("/META-INF/spring/integration/ex_from_the_web.html");
		
		String html = IOUtils.toString(resource.getInputStream());
		
		Node allStrikesRowsContainer = xpathHelper.getNode(html, "//table[@id='yfncsumtab']//tr[@valign='top']//table[6]/tr/td/table");
		
		NodeList strikes = allStrikesRowsContainer.getChildNodes();
		int numOfPutStrikes = strikes.getLength();
		
		log.info("strikeRow: " +numOfPutStrikes);
		
		for(int i=1/*skip header*/;i<numOfPutStrikes;i++){
			Node strikeRow = strikes.item(i);
			
			
			DOMNodeListImpl strikeAttributes = (DOMNodeListImpl)strikeRow.getChildNodes();
			
			log.info("----------- strike [" +i+"]--------------------");
			
			
			for (int strkAttrNumbr = 0; strkAttrNumbr < strikeAttributes
					.getLength(); strkAttrNumbr++) {
				String attrValue = null;
				switch (strkAttrNumbr) {
				case 0:
					attrValue = ((DOMTextImpl) strikeAttributes.item(strkAttrNumbr)
							.getFirstChild().getFirstChild().getFirstChild())
							.getNodeValue();
					log.info("strike: " + attrValue);
					break;

				case 4:
					attrValue = ((DOMTextImpl) strikeAttributes.item(strkAttrNumbr)
							.getFirstChild()).getNodeValue();
					log.info("bid: " + attrValue);
					break;

				case 5:
					attrValue = ((DOMTextImpl) strikeAttributes.item(strkAttrNumbr)
							.getFirstChild()).getNodeValue();
					log.info("ask: " + attrValue);
					break;
				}

			}
		}
		
		/*DOMNodeListImpl strikeAttributes = (DOMNodeListImpl)strikeRow.getChildNodes();
		
		log.info("strike attributes list: [" +strikeAttributes.getLength()+"]");
		
		
		for (int strkAttrNumbr = 0; strkAttrNumbr < strikeAttributes
				.getLength(); strkAttrNumbr++) {
			String attrValue = null;
			switch (strkAttrNumbr) {
			case 0:
				attrValue = ((DOMTextImpl) strikeAttributes.item(strkAttrNumbr)
						.getFirstChild().getFirstChild().getFirstChild())
						.getNodeValue();
				log.info("strike: " + attrValue);
				break;

			case 4:
				attrValue = ((DOMTextImpl) strikeAttributes.item(strkAttrNumbr)
						.getFirstChild()).getNodeValue();
				log.info("bid: " + attrValue);
				break;

			case 5:
				attrValue = ((DOMTextImpl) strikeAttributes.item(strkAttrNumbr)
						.getFirstChild()).getNodeValue();
				log.info("ask: " + attrValue);
				break;
			}

		}
		*/

		
	}

}
