/**
 * 
 */
package com.excelsiorsoft.daedalus.service.datacollection.process;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.stereotype.Component;

import com.excelsiorsoft.daedalus.service.QuoteRequester;

/**
 * @author Simeon
 * 
 */
@Component("buildSubsequentPutStrikeSeries")
public final class BuildSubsequentPutStrikeSeries extends
		AbstractExtractionStep {

	@Autowired
	@Qualifier("strikesChannel")
	private MessageChannel strikesChannel;
	
	@Autowired
	@Qualifier("buildSinglePutStrikeSeries")
	private BuildSinglePutStrikeSeries buildSinglePutStrikeSeries;


	@Autowired
	@Qualifier("quoteRequester")
	private QuoteRequester quoteRequester;

	@Override
	public void process(final ExtractionProcessContext context)
			throws Exception {

		context.setDocument(null);

		/*String currentExpCycle = context.getPointer().getExpirationCycleName();
		
		Quote quote = context.getQuote();
		String url = quote.getExpirationCycles()
				.get(currentExpCycle).getUrl();*/
		/*String ticker = quote.getTicker();*/
		
		quoteRequester.requestQuote(/*ticker,*/ /*url,*/ context, strikesChannel);

	}

}
