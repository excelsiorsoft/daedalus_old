package com.excelsiorsoft.daedalus.service.datacollection.process;

import static org.junit.Assert.*;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excelsiorsoft.daedalus.domain.ExpirationCycle;
import com.excelsiorsoft.daedalus.domain.QuoteCoordinate;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/integration/data-collection-context.xml" })
public class BuildSinglePutStrikeSeriesTest {

private final Log log = LogFactory.getLog(getClass());
	
	private static final String expCycleOfInterest= "Nov 12";

	@Autowired
	private ApplicationContext context;
	
	@Autowired @Qualifier("buildSinglePutStrikeSeries")
	private BuildSinglePutStrikeSeries cut;

	private static String html;
	
	@Before
	public void init() throws Exception {
		if (html == null) {
			html = IOUtils.toString((context
					.getResource("/META-INF/spring/integration/SLWwithDups.xml"))
					.getInputStream());
		}
	}
	
	@Test
	public void testDuplicateStrikesBehavior() throws Exception {
		
		
		ExtractionProcessContext context = new ExtractionProcessContext("SLW", html);
		
		context.getQuote().getExpirationCycles().put(expCycleOfInterest, new ExpirationCycle().setName(expCycleOfInterest));
		
		QuoteCoordinate pointer = new QuoteCoordinate().setTicker("SLW").setExpirationCycleName(expCycleOfInterest);
		context.setPointer(pointer);
		
		cut.process(context);
	}

}
