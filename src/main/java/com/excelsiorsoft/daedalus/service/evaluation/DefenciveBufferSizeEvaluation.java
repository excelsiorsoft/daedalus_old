/**
 * 
 */
package com.excelsiorsoft.daedalus.service.evaluation;

import static com.domainlanguage.money.Money.dollars;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.domainlanguage.money.Money;
import com.excelsiorsoft.daedalus.domain.Strike;

/**
 * @author Simeon
 *
 */
@Component("bidAskSpreadEvaluation")
public class DefenciveBufferSizeEvaluation extends AbstractEvaluation {

	//TODO: have it read in from an internal properties file via @Value
	public static final Money MINIMUM_BID_ASK_SPREAD = dollars(0.09);
	
	
	@Override
	public boolean worthwhile(Strike strike) {/*
		BigDecimal strikeValue = strike.getValue();
		BigDecimal currentPrice = strike.get;
		BigDecimal bidAskSpread = returnCalculator.defenciveBuffer(strike, currentPrice).bidAskSpread(strike.getBid(), strike.getAsk());


	*/
		return false;
	}


	

}
