package com.excelsiorsoft.daedalus.service.evaluation;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excelsiorsoft.daedalus.domain.Strike;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:/META-INF/spring/integration/data-collection-context.xml"})
public class BidAskSpreadSizeEvaluationTest {

	@Autowired 
	@Qualifier("bidAskSpreadSizeEvaluation")
	BidAskSpreadSizeEvaluation cut;
	
	@Test
	public void test() {
		

		Strike strike = (Strike) new Strike().setAsk("1.10").setBid("1.01");
		boolean worthwhile = cut.worthwhile(strike);
		assertFalse("result should be false",worthwhile);
		
	}

}
