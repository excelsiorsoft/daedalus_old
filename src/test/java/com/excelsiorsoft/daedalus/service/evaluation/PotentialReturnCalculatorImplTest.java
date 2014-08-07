package com.excelsiorsoft.daedalus.service.evaluation;

import static com.domainlanguage.money.Money.dollars;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.domainlanguage.money.Money;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:/META-INF/spring/integration/data-collection-context.xml"})
public class PotentialReturnCalculatorImplTest {

	@Autowired 
	@Qualifier("potentialReturnCalculator")
	PotentialReturnCalculator cut;
	
	@Test
	public void bidAskSpreadComparisons() {
		
		BigDecimal bid = new BigDecimal(0.30);
		BigDecimal ask = new BigDecimal(0.40);
		BigDecimal strike = new BigDecimal(18.00);
		BigDecimal currentPrice = new BigDecimal(26.24);
		
		
		BigDecimal spread = cut.bidAskSpread(bid, ask);
		Money $spread = dollars(spread);
		
		assertFalse("$0.07 < $0.10",dollars(0.07).isGreaterThan($spread));
		assertTrue("$0.12 > $0.10",dollars(0.12).isGreaterThan($spread));
		
		//Money monetarySpread = Money.valueOf(spread, Currency.getInstance("USD"));
	}
	
	@Test
	public void yieldCalculations() {

		BigDecimal bid = new BigDecimal(1.01);
		BigDecimal strike = new BigDecimal(18.00);
		BigDecimal yield = cut.yield(bid, strike);
		assertNotNull("yield cannot be null", yield);
		assertTrue("yield is greater than minimum", BigDecimal.valueOf(YieldSizeEvaluation.MINIMUM_YIELD)
				.compareTo(yield) < 0);
	}

}
