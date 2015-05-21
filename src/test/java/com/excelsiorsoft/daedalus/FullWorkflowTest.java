package com.excelsiorsoft.daedalus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excelsiorsoft.daedalus.service.api.QuoteRequester;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:/META-INF/spring/integration/data-collection-context.xml"})
public class FullWorkflowTest {
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired @Qualifier("quoteRequester")
	private QuoteRequester requestor;
	
	@Test
	public void testQuoteRequestFlow(){
		
		//final QuoteRequester requestor = context.getBean(QuoteRequester.class);
		
		requestor.requestQuote("SLW");
		//TODO: here we can query the quote from the persistent storage once that functionality is implemented
	}

}
