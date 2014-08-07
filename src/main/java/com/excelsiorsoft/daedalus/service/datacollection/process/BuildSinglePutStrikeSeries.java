/**
 * 
 */
package com.excelsiorsoft.daedalus.service.datacollection.process;

import static com.excelsiorsoft.daedalus.domain.InstrumentType.OptionType.PUT;


import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.DOMNodeListImpl;
import org.w3c.tidy.DOMTextImpl;

import com.excelsiorsoft.daedalus.domain.ExpirationCycle;
import com.excelsiorsoft.daedalus.domain.Strike;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;

/**
 * @author Simeon
 * 
 */
@Component("buildSinglePutStrikeSeries")
public final class BuildSinglePutStrikeSeries extends AbstractExtractionStep {

	private final static String ALL_STRIKES_ROWS_CONTAINTER = "//table[@id='yfncsumtab']//tr[@valign='top']//table[6]/tr/td/table";

	
	private void validate(final ExtractionProcessContext context) throws Exception{
		
		Assert.notNull(context, "Expect non-null ExtractionProcessContext");
		
		String html = context.getDocument();
		
		Assert.notNull(html, "HTML doc must be passed in.");
		
		String currExpCycleKey = context.getPointer().getExpirationCycleName();
		
		Assert.notNull(currExpCycleKey, "Current calendar cycle must be passed in.");
		Assert.state(context.getQuote().getExpirationCycles().keySet().contains(currExpCycleKey), "Pointer is pointing to a value not present in the current Quote on the context.");
		
		
	}
	
	@Override
	public void process(final ExtractionProcessContext context)
			throws Exception {
		
		//TODO: candidate for AOP injection, as before method exec
		validate(context);

		String html = context.getDocument();
	
		String currExpCycleKey = context.getPointer().getExpirationCycleName();
		
		ExpirationCycle currentExpirationCycle = context.getQuote().getExpirationCycles().get(currExpCycleKey);
		
		Node allStrikesRowsContainer = xpathHelper.getNode(html,
				ALL_STRIKES_ROWS_CONTAINTER);

		NodeList strikes = allStrikesRowsContainer.getChildNodes();
		int numOfPutStrikes = strikes.getLength();

		log.info("strikeRow: " + numOfPutStrikes);
		
		Strike strike = null;
		//Map<String, Strike> strikesForCurrentCalendarCycle = currentExpirationCycle.getStrikeSeries();
		ListMultimap<String, Strike> strikesForCurrentCalendarCycle = LinkedListMultimap.create();
		//String currentStrikeName = null;
		

		for (int i = 1/* skip header */; i < numOfPutStrikes; i++) {
			Node strikeRow = strikes.item(i);

			strike = new Strike();
			strike.setType(PUT);
			
			DOMNodeListImpl strikeAttributes = (DOMNodeListImpl) strikeRow
					.getChildNodes();

			log.info("----------- strike [" + i + "]--------------------");

			
			//TODO: need to take care of hydrating other attributes - id, volume, etc.
			for (int strkAttrNumbr = 0; strkAttrNumbr < strikeAttributes
					.getLength(); strkAttrNumbr++) {
				String attrValue = null;
				switch (strkAttrNumbr) {
				case 0:
					attrValue = ((DOMTextImpl) strikeAttributes
							.item(strkAttrNumbr).getFirstChild()
							.getFirstChild().getFirstChild()).getNodeValue();
					log.info("strike: " + attrValue);
					
					//currentStrikeName = attrValue;
					
					strike.setValue(attrValue);
					break;

				case 4:
					attrValue = ((DOMTextImpl) strikeAttributes.item(
							strkAttrNumbr).getFirstChild()).getNodeValue();
					log.info("bid: " + attrValue);
										
					
					strike.setBid(attrValue);
					break;

				case 5:
					attrValue = ((DOMTextImpl) strikeAttributes.item(
							strkAttrNumbr).getFirstChild()).getNodeValue();
					log.info("ask: " + attrValue);
					
					
					strike.setAsk(attrValue);
					break;
				}

				
			}
			
			strikesForCurrentCalendarCycle.put(Double.toString(strike.getValue())/*.toString()*/, strike);
		}
		
		currentExpirationCycle.setStrikeSeries(strikesForCurrentCalendarCycle.asMap());
		log.info("Done hydrating strike series for " + currExpCycleKey
				+ " expiration cycle. Resulting quote is: " + context.getQuote());

	}

}
