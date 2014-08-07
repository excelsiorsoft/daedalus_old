/**
 * 
 */
package com.excelsiorsoft.daedalus.service.evaluation.process;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.excelsiorsoft.daedalus.domain.ExpirationCycle;
import com.excelsiorsoft.daedalus.domain.OptionTradeCandidate;
import com.excelsiorsoft.daedalus.domain.Quote;
import com.excelsiorsoft.daedalus.domain.Strike;
import com.excelsiorsoft.daedalus.service.evaluation.OptionStrikeEvaluator;

import static java.math.BigDecimal.valueOf;


/**
 * @author Simeon
 *
 */
public final class QuoteAssessmentImpl implements QuoteAssessment {

	@Autowired
	@Qualifier("optionStrikeEvaluator")
	private OptionStrikeEvaluator optionStrikeEvaluator;
	
	
	@Override
	public void assess(QuoteAssessmentContext context) {
		
		Quote quote = context.getQuote();
		String ticker = quote.getUnderlying().getSymbol();
		BigDecimal currentPriceOfUnderlying = valueOf(quote.getUnderlying().getBid());
		
		Map<String, ExpirationCycle> expirationCycles = quote.getExpirationCycles();
		
		for(ExpirationCycle expCycle: expirationCycles.values()){
			String expCycleName = expCycle.getName();
			 Map<String, Collection<Strike>> strikesMultiMap =  expCycle.getStrikeSeries();
			 
			 for(Map.Entry<String, Collection<Strike>> entry:strikesMultiMap.entrySet()){
				 
				 String strikeName = entry.getKey();
				 OptionTradeCandidate candidate = null;
				 
				 for(Strike strike: entry.getValue()){
					 /*BigDecimal bid = strike.getBid();
					 BigDecimal ask = strike.getAsk();*/
					 if(!optionStrikeEvaluator.worthwhile(strike)){
						 continue;
					 }else{
						 candidate = new OptionTradeCandidate(strike);
					 }
				 }
			 }
		}
	}

}
