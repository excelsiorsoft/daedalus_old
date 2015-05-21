/**
 * 
 */
package com.excelsiorsoft.daedalus.service.datacollection.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.tidy.DOMTextImpl;

import com.excelsiorsoft.daedalus.domain.ExpirationCycle;
import com.excelsiorsoft.daedalus.domain.Quote;
import com.excelsiorsoft.daedalus.service.api.UrlBuilder;
import com.excelsiorsoft.daedalus.service.impl.DateConverter;

/**
 * @author Simeon
 * 
 */
@Component("buildCurrentExpirationCycle")
public final class BuildCurrentExpirationCycle extends AbstractExtractionStep {

	private final static String CURRENT_EXPIRATION_CYCLE = "//table[@id='yfncsumtab']//tr[@valign='top']/td/strong/text()";

	@Autowired
	@Qualifier(("urlBuilder"))
	private UrlBuilder urlBuilder;

	@Override
	public void process(final ExtractionProcessContext context)
			throws Exception {

		Node node = xpathHelper.getNode(context.getDocument(),
				CURRENT_EXPIRATION_CYCLE);
		String currentExpCycleKey = ((DOMTextImpl) node).getNodeValue();

		Quote quoteInTheWorks = context.getQuote();
		String currentExpCycleValue = urlBuilder.forExpirationCycle(
				quoteInTheWorks.getUnderlying().getSymbol(),
				DateConverter.convert(currentExpCycleKey)); // url

		log.info("current expiration month: " + currentExpCycleKey + " -> "
				+ currentExpCycleValue);
		// TODO: this will need to be created via Builder, when finally build,
		// set the ID field which is composite of mutliple fields of ExpirationCycle type: ticker, name, etc.
		ExpirationCycle expCycle = new ExpirationCycle().setName( 
				currentExpCycleKey).setUrl(currentExpCycleValue);

		quoteInTheWorks.getExpirationCycles().put(currentExpCycleKey, expCycle);
		// TODO: extract via AOP
		log.info("Done with " + this.getClass().getSimpleName()
				+ " step. Resulting quote is: " + context.getQuote());

	}

}
