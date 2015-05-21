/**
 * 
 */
package com.excelsiorsoft.daedalus.service.impl;

import org.springframework.stereotype.Component;

import com.excelsiorsoft.daedalus.service.api.UrlBuilder;
import com.excelsiorsoft.daedalus.service.datacollection.process.ExtractionProcessContext;

/**
 * @author Simeon
 * 
 */
@Component("urlBuilder")
public class UrlBuilderImpl implements UrlBuilder {

	private final static String BASE = "http://finance.yahoo.com";
	private final static String INIT_REQUEST_SFFX = "/q/op?s=%s+Options";
	private final static String UNIVERSAL_REQUEST_SFFX = "/q/op?s=%s&m=%s";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excelsiorsoft.daedalus.service.UrlBuilder#forTicker(java.lang.String)
	 */
	@Override
	public String forTicker(String ticker) {
		return String.format(BASE + INIT_REQUEST_SFFX, ticker);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.excelsiorsoft.daedalus.service.UrlBuilder#forExpirationCycle(java
	 * .lang.String)
	 */
	@Override
	public String forExpirationCycle(String suffix) {
		return String.format(BASE + suffix);
	}

	public String forExpirationCycle(String ticker, String date) throws Exception {
		return String.format(BASE + UNIVERSAL_REQUEST_SFFX, ticker,
				date);
	}
	
	
	public String constructUrl(final ExtractionProcessContext context) {

		String ticker = context.getQuote().getUnderlying().getSymbol();

		boolean brandNewRequest = (context.getPointer() == null && context
				.getDocument() == null);

		String url = (brandNewRequest) ? url = /*urlBuilder.*/forTicker(ticker)
				: context.getQuote().getExpirationCycles()
						.get(context.getPointer().getExpirationCycleName())
						.getUrl();

		return url;
	}

}
