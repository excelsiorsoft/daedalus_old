package com.excelsiorsoft.daedalus.service.datacollection.process;

import com.excelsiorsoft.daedalus.domain.Quote;
import com.excelsiorsoft.daedalus.domain.QuoteCoordinate;
import com.excelsiorsoft.daedalus.domain.Quote.QuoteBuilder;

public final class ExtractionProcessContext {
	
	public final static String EXTRACTION_CONTEXT="extractionContext";

	private String document;

	private final Quote quote;
	
	private QuoteCoordinate pointer;

	public Quote getQuote() {
		return quote;
	}

	public final String getDocument() {
		return document;
	}

	public ExtractionProcessContext(final String htmlDocument, final String ticker) {
		this.document = htmlDocument;
		this.quote = QuoteBuilder.withTicker(ticker).build();
	}

	public QuoteCoordinate getPointer() {
		return pointer;
	}

	public void setPointer(final QuoteCoordinate pointer) {
		this.pointer = pointer;
	}

	public void setDocument(final String document) {
		this.document = document;
	}

}
