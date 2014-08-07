/**
 * 
 */
package com.excelsiorsoft.daedalus.service.evaluation;

import static com.domainlanguage.money.Money.dollars;

import java.math.BigDecimal;
import static java.math.BigDecimal.valueOf;

import org.apache.commons.validator.routines.BigDecimalValidator;
import org.springframework.stereotype.Component;

import com.domainlanguage.money.Money;
import com.excelsiorsoft.daedalus.domain.Strike;

/**
 * @author Simeon
 *
 */
@Component("yieldSizeEvaluation")
public class YieldSizeEvaluation extends AbstractEvaluation {

	//TODO: have it read in from an internal properties file via @Value
	public static final double MINIMUM_YIELD = 0.03; //3%
	
	
	@Override
	public boolean worthwhile(Strike strike) {

		//true if yield is greater than preset minimum
		return BigDecimalValidator.getInstance().minValue(
				returnCalculator.yield(valueOf(strike.getBid()), valueOf(strike.getValue())),
				MINIMUM_YIELD);

	}


	

}
