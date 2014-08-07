/**
 * 
 */
package com.excelsiorsoft.daedalus.service.datacollection.process;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.MessageChannel;
import org.springframework.stereotype.Component;

import com.excelsiorsoft.daedalus.domain.ExpirationCycle;
import com.excelsiorsoft.daedalus.domain.Quote;
import com.excelsiorsoft.daedalus.domain.QuoteCoordinate;

/**
 * @author Simeon
 * 
 */
@Component("buildAllPutStrikeSeries")
public final class BuildAllPutStrikeSeries extends
		AbstractExtractionStep {

	@Autowired
	@Qualifier("strikesChannel")
	private MessageChannel strikesChannel;
	
	@Autowired
	@Qualifier("buildSinglePutStrikeSeries")
	private BuildSinglePutStrikeSeries buildSinglePutStrikeSeries;


	@Autowired
	@Qualifier("buildSubsequentPutStrikeSeries")
	private BuildSubsequentPutStrikeSeries buildSubsequentPutStrikeSeries;

	@Override
	public void process(final ExtractionProcessContext context)
			throws Exception {

		Quote quote = context.getQuote();
		
		Map<String, ExpirationCycle> calendarSeries = quote.getExpirationCycles();
		int i = 0;
		for (Map.Entry<String, ExpirationCycle> entry : calendarSeries.entrySet()) {

			// expiration cycle based on iterator index
			String expCycleName = entry.getValue().getName();

			if (i == 0) { // for default (current) calendar cycle

				// no get request, pull data from passed html doc
				
				
				buildSinglePutStrikeSeries.process(resetPointer(context, expCycleName));

				

			} else { // for the rest of calendar cycles (excluding current cycle)
					
				// make a get request based on an iterator index

				buildSubsequentPutStrikeSeries.process(resetPointer(context, expCycleName));
				

			}
			
			i++;
			
			log.info("Done populating strikes for " + expCycleName
					+ " expiration cycle. Resulting quote is: " + quote);
		}

	}

	/**
	 * Caution: side effect! <br>
	 * This method modifies the pointer on the context to point to the passed in expiration cycle
	 * 
	 * @param context context to be changed
	 * @param expCycleName new location to be set on the context
	 * @return modified context
	 */
	private ExtractionProcessContext resetPointer(
			final ExtractionProcessContext context, final String expCycleName) {

		String ticker = context.getQuote().getUnderlying().getSymbol();

		QuoteCoordinate pointer = context.getPointer();

		pointer = (pointer == null) ? pointer = new QuoteCoordinate()
				.setTicker(ticker).setExpirationCycleName(expCycleName)

		: pointer.setTicker(ticker).setExpirationCycleName(expCycleName);

		context.setPointer(pointer);
		return context;
	}

}
