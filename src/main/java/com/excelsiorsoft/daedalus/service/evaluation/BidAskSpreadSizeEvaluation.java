/**
 * 
 */
package com.excelsiorsoft.daedalus.service.evaluation;

import static com.domainlanguage.money.Money.dollars;

import java.math.BigDecimal;
import static java.math.BigDecimal.valueOf;

import org.springframework.stereotype.Component;

import com.domainlanguage.money.Money;
import com.excelsiorsoft.daedalus.domain.Strike;

/**
 * @author Simeon
 *
 */
@Component("bidAskSpreadSizeEvaluation")
public class BidAskSpreadSizeEvaluation extends AbstractEvaluation {

	//TODO: have it read in from an internal properties file via @Value
	public static final Money MAXIMUM_BID_ASK_SPREAD = dollars(0.09);
	
	
	@Override
	public boolean worthwhile(Strike strike) {
		/*BigDecimal bid = strike.getBid();
		BigDecimal ask = strike.getAsk();
		BigDecimal bidAskSpread = returnCalculator.bidAskSpread(strike.getBid(), strike.getAsk());*/
		return MAXIMUM_BID_ASK_SPREAD.isGreaterThan(dollars(returnCalculator
				.bidAskSpread(valueOf(strike.getBid()), valueOf(strike.getAsk()))));

	}


	

}
