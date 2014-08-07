/**
 * 
 */
package com.excelsiorsoft.daedalus.service.evaluation;

import static java.math.BigDecimal.*;
import java.math.BigDecimal;
import java.math.MathContext;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import static java.math.BigDecimal.*;
import static java.math.RoundingMode.*;


/**
 * @author Simeon
 *
 */
@Component("potentialReturnCalculator")
public class PotentialReturnCalculatorImpl implements PotentialReturnCalculator {

	/* (non-Javadoc)
	 * @see com.excelsiorsoft.daedalus.service.PotentialReturnCalculator#yield(java.math.BigDecimal, java.math.BigDecimal)
	 */
	@Override
	public BigDecimal yield(BigDecimal bid, BigDecimal strike) {
		return bid.divide(strike, new MathContext(3,HALF_UP));
	}

	/* (non-Javadoc)
	 * @see com.excelsiorsoft.daedalus.service.PotentialReturnCalculator#defenciveBuffer(java.math.BigDecimal, java.math.BigDecimal)
	 */
	@Override
	public BigDecimal defenciveBuffer(BigDecimal strike, BigDecimal currentPrice) {
		BigDecimal result = null;
		result = ONE.subtract(strike.divide(currentPrice, new MathContext(
				6/* significant digits */, UP/* rounding */)));
		return result;
	}

	/* (non-Javadoc)
	 * @see com.excelsiorsoft.daedalus.service.PotentialReturnCalculator#bidAskSpread(java.math.BigDecimal, java.math.BigDecimal)
	 */
	@Override
	public BigDecimal bidAskSpread(BigDecimal bid, BigDecimal ask) {
		Assert.notNull(bid, "Bid price must not be null");
		Assert.notNull(ask, "Ask price must not be null");
		return ask.subtract(bid, new MathContext(2,HALF_UP));//assume bid-ask spread never exceeds $1.00

	}

}
