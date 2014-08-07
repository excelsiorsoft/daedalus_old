/**
 * 
 */
package com.excelsiorsoft.daedalus.service.evaluation;

import java.math.BigDecimal;

/**
 * @author Simeon
 *
 */
public interface PotentialReturnCalculator {
	
	BigDecimal yield(BigDecimal bid, BigDecimal strike);
	BigDecimal defenciveBuffer(BigDecimal strike, BigDecimal currentPrice);
	BigDecimal bidAskSpread(BigDecimal bid, BigDecimal ask);

}
