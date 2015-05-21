/**
 * 
 */
package com.excelsiorsoft.daedalus.service.impl;

//import static com.excelsiorsoft.daedalus.domain.Quote.TICKER;
import static com.excelsiorsoft.daedalus.service.datacollection.process.ExtractionProcessContext.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.stereotype.Component;

import com.excelsiorsoft.daedalus.service.api.DefaultMessageHandler;
import com.excelsiorsoft.daedalus.service.datacollection.process.BuildSinglePutStrikeSeries;
import com.excelsiorsoft.daedalus.service.datacollection.process.ExtractionProcessContext;

/**
 * @author Simeon
 * 
 */
@Component("strikesMessageHandler")
public class StrikeSeriesMessageHandlerImpl implements DefaultMessageHandler {

	private final Log log = LogFactory.getLog(getClass());

	@Autowired
	@Qualifier("buildSinglePutStrikeSeries")
	private BuildSinglePutStrikeSeries buildSinglePutStrikeSeries;

	public void handleMessage(Message<String> message) throws Exception {
		log.info("ticker: " + message.getHeaders().get("ticker"));
		log.info("full payload: " + message.getPayload());

		String htmlDoc = (String) message.getPayload();
		ExtractionProcessContext context = (ExtractionProcessContext) message.getHeaders().get(EXTRACTION_CONTEXT);
		context.setDocument(htmlDoc);
		
		buildSinglePutStrikeSeries.process(context);
	}

}
