/**
 * 
 */
package com.excelsiorsoft.daedalus.service.evaluation.process;

import java.util.LinkedList;
import java.util.List;

import org.springframework.util.Assert;

import com.excelsiorsoft.daedalus.domain.OptionTradeCandidate;
import com.excelsiorsoft.daedalus.domain.Quote;

/**
 * @author Simeon
 * 
 */
public final class QuoteAssessmentContext {

	private final Quote quote;
	private final List<OptionTradeCandidate> tradeCandidates = new LinkedList<OptionTradeCandidate>();

	public QuoteAssessmentContext(final Quote quote){
		Assert.notNull(quote, "Quote must not be null");
		this.quote = quote;
	}
	
	public final Quote getQuote() {
		return quote;
	}

	/*public final void setQuote(Quote quote) {
		this.quote = quote;
	}*/

	public final List<OptionTradeCandidate> getTradeCandidates() {
		return tradeCandidates;
	}

	/*public final QuoteAssessmentContext setTradeCandidates(
			final List<OptionTradeCandidate> tradeCandidates) {
		this.tradeCandidates = tradeCandidates;
		return this;
	}*/
	
	public final QuoteAssessmentContext addTradeCandidate(
			final OptionTradeCandidate tradeCandidate) {
		tradeCandidates.add(tradeCandidate);
		return this;
	}

}
