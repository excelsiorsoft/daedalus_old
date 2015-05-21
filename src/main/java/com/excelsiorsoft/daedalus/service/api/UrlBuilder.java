
package com.excelsiorsoft.daedalus.service.api;

import com.excelsiorsoft.daedalus.service.datacollection.process.ExtractionProcessContext;

/**
 * @author Simeon
 *
 */
public interface UrlBuilder {

	String forTicker(String ticker);
	
	String forExpirationCycle(String suffix);
	
	String forExpirationCycle(String ticker, String date) throws Exception;
	
	String constructUrl(ExtractionProcessContext context);
	}
