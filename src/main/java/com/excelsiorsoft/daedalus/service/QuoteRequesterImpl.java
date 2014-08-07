/**
 * 
 */
package com.excelsiorsoft.daedalus.service;

import static com.excelsiorsoft.daedalus.domain.Quote.TICKER;
import static com.excelsiorsoft.daedalus.service.datacollection.process.ExtractionProcessContext.EXTRACTION_CONTEXT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

import com.excelsiorsoft.daedalus.service.datacollection.process.ExtractionProcessContext;

/**
 * @author Simeon
 * 
 */
@Component("quoteRequester")
public class QuoteRequesterImpl implements QuoteRequester {

	public final static String URL = "URL";

	@Autowired
	@Qualifier("tickerChannel")
	private MessageChannel tickerChannel;

	@Autowired
	@Qualifier("urlBuilder")
	private UrlBuilder urlBuilder;

	public QuoteRequesterImpl() {
	}

	public void requestQuote(String ticker) {

		ExtractionProcessContext context = new ExtractionProcessContext(ticker, null);
		requestQuote(context, tickerChannel);

	}

	public void requestQuote(ExtractionProcessContext context, MessageChannel channel) {

		String ticker = context.getQuote().getUnderlying().getSymbol();
		//String url = constructUrl(context);
		MessagingTemplate template = new MessagingTemplate();

		
		Message<String> message = MessageBuilder.withPayload(ticker)
				/*.setHeader(TICKER, ticker)*/.setHeader(URL, urlBuilder.constructUrl(context)).setHeader(EXTRACTION_CONTEXT, context).build();

		template.send(channel, message);

	}

	
	/*private String constructUrl(final ExtractionProcessContext context) {

		String ticker = context.getQuote().getUnderlying().getSymbol();

		boolean brandNewRequest = (context.getPointer() == null && context
				.getDocument() == null);

		String url = (brandNewRequest) ? url = urlBuilder.forTicker(ticker)
				: context.getQuote().getExpirationCycles()
						.get(context.getPointer().getExpirationCycleName())
						.getUrl();

		return url;
	}
*/
}
