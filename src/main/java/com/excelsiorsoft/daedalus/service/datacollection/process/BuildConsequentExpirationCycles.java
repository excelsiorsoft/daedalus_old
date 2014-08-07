/**
 * 
 */
package com.excelsiorsoft.daedalus.service.datacollection.process;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.tidy.DOMAttrImpl;
import org.w3c.tidy.DOMTextImpl;

import com.excelsiorsoft.daedalus.domain.ExpirationCycle;
import com.excelsiorsoft.daedalus.service.UrlBuilder;

/**
 * @author Simeon
 * 
 */
@Component("buildConsequentExpirationCycles")
public final class BuildConsequentExpirationCycles extends
		AbstractExtractionStep {

	private final static String CONSEQUENT_EXPIRATION_CYCLE_NAMES = "//table[@id='yfncsumtab']//tr[@valign='top']/td/a/text()";
	private final static String CONSEQUENT_EXPIRATION_CYCLE_VALUES = "//table[@id='yfncsumtab']//tr[@valign='top']/td/a/@href";

	@Autowired
	@Qualifier(("urlBuilder"))
	private UrlBuilder urlBuilder;

	@Override
	public void process(final ExtractionProcessContext context)
			throws Exception {

		String html = context.getDocument();

		NodeList nodeListofValues = xpathHelper.getNodeList(html,
				CONSEQUENT_EXPIRATION_CYCLE_VALUES);
		NodeList nodeListofNames = xpathHelper.getNodeList(html,
				CONSEQUENT_EXPIRATION_CYCLE_NAMES);

		log.info("length: " + nodeListofValues.getLength());

		//Map<String, String> expCycles = null;
		Map<String, ExpirationCycle> expCycles = null;

		for (int value = 0, name = 0; value < nodeListofValues.getLength()
				&& name < nodeListofNames.getLength(); value++, name++) {
			Node valueNode = nodeListofValues.item(value);
			Node nameNode = nodeListofNames.item(name);

			String key = ((DOMTextImpl) nameNode).getNodeValue();
			String url = urlBuilder
					.forExpirationCycle(((DOMAttrImpl) valueNode)
							.getNodeValue());

			log.info("expirationCycle" + "[" + (value + 1) + "]: " + key
					+ " -> " + url);
			expCycles = context.getQuote().getExpirationCycles();
			
			// TODO: this will need to be created via Builder, when finally build,
			// set the ID field which is composite of mutliple fields of ExpirationCycle type: ticker, name, etc.
			ExpirationCycle expCycle = new ExpirationCycle().setName(
					key).setUrl(url);
			
			expCycles.put(key, expCycle);

		}
		log.info("Expiration cycles hydrated: " + expCycles);
		// TODO: extract via AOP
		log.info("Done with " + this.getClass().getSimpleName()
				+ " step. Resulting quote is: " + context.getQuote());
	}

}
