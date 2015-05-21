/**
 * 
 */
package com.excelsiorsoft.daedalus.service.api;

import org.springframework.integration.MessageChannel;

import com.excelsiorsoft.daedalus.service.datacollection.process.ExtractionProcessContext;

/**
 * @author Simeon
 *
 */
public interface QuoteRequester {
	
	void requestQuote(String forTicker);
	
	void requestQuote(/*String forTicker,*/ /*String url,*/ ExtractionProcessContext context, MessageChannel channel);

}
