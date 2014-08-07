/**
 * 
 */
package com.excelsiorsoft.daedalus.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.stereotype.Component;

import static com.excelsiorsoft.daedalus.domain.Quote.TICKER;
import static com.excelsiorsoft.daedalus.service.datacollection.process.ExtractionProcessContext.EXTRACTION_CONTEXT;

import com.excelsiorsoft.daedalus.domain.Quote;
import com.excelsiorsoft.daedalus.service.datacollection.process.BuildCompleteQuote;
import com.excelsiorsoft.daedalus.service.datacollection.process.ExtractionProcessContext;

/**
 * @author Simeon
 * 
 */
@Component("defaultMessageHandler")
public class DefaultMessageHandlerImpl implements DefaultMessageHandler {

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("buildCompleteQuote")
	private BuildCompleteQuote buildCompleteQuote;
	
	@Autowired
	@Qualifier("quotePersistenceService")
	private QuotePersistenceService quotePersistenceService;

	public void handleMessage(Message message) throws Exception {
		log.info("ticker: " + message.getHeaders().get("ticker"));
		log.info("full payload: " + message.getPayload());

		String htmlDoc = (String) message.getPayload();
		ExtractionProcessContext context = (ExtractionProcessContext) message.getHeaders().get(EXTRACTION_CONTEXT);
		context.setDocument(htmlDoc);
		
		buildCompleteQuote.process(context);
		
		quotePersistenceService.persist(context.getQuote());
	}

}
