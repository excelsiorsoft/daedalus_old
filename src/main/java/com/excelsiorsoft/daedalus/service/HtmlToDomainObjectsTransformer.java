/**
 * 
 */
package com.excelsiorsoft.daedalus.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.annotation.Transformer;
import org.springframework.stereotype.Component;
import org.w3c.dom.NodeList;

import com.excelsiorsoft.daedalus.domain.Quote;

/**
 * @author Simeon
 * 
 */
@Component
public class HtmlToDomainObjectsTransformer {

	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired @Qualifier("XPathHelper")
	private XPathHelper xpathHelper;

	@SuppressWarnings("unused")
	@Transformer
	public List<Quote> transform(String payload) throws Exception {
		List<Quote> result = new ArrayList<Quote>();


		NodeList nodeList = xpathHelper.getNodeList(payload, "id('yfncsumtab')/x:tbody/x:tr[2]/x:td[1]/x:strong");
		log.info("received payload: " + payload);

		

		return result;
	}

}
