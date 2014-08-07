package com.excelsiorsoft.daedalus.service.evaluation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excelsiorsoft.daedalus.domain.Strike;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:/META-INF/spring/integration/data-collection-context.xml"})
public class OptionStrikeEvaluatorTest {

	@Autowired
	@Qualifier("optionStrikeEvaluator")
	private OptionStrikeEvaluator cut;
	
	@Test
	public void largeSpreadSize() {
		Strike strike = (Strike) new Strike().setValue("18.00").setBid("1.01").setAsk("1.10");
		boolean result = cut.worthwhile(strike);
		assertFalse("expect false result", result);
	}
	
	@Test
	public void smallSpreadSize() {
		Strike strike = (Strike) new Strike().setValue("18.00").setBid("1.01").setAsk("1.07");
		boolean result = cut.worthwhile(strike);
		assertTrue("expect true result", result);
	}
	
	@Test
	public void smallSpreadSizeAndSmallYield() {
		Strike strike = (Strike) new Strike().setValue("39.00").setBid("1.01").setAsk("1.07");
		boolean result = cut.worthwhile(strike);
		assertFalse("expect false result", result);
	}
	
	@Test
	public void smallSpreadSizeAndLargeYield() {
		Strike strike = (Strike) new Strike().setValue("39.00").setBid("3.01").setAsk("3.07");
		boolean result = cut.worthwhile(strike);
		assertTrue("expect true result", result);
	}

}
