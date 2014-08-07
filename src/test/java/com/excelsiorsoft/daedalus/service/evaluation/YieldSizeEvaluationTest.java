package com.excelsiorsoft.daedalus.service.evaluation;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excelsiorsoft.daedalus.domain.Strike;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:/META-INF/spring/integration/data-collection-context.xml"})
public class YieldSizeEvaluationTest {

	@Autowired 
	@Qualifier("yieldSizeEvaluation")
	YieldSizeEvaluation cut;
	
	@Test
	public void test() {
		
		Strike strike = (Strike) new Strike().setValue("18.00").setBid("1.01");
		
		boolean worthwhile = cut.worthwhile(strike);
		assertTrue("result should be 'true'", worthwhile);
	}

}
