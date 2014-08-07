package com.excelsiorsoft.daedalus.service.datacollection.process;

import static org.junit.Assert.*;

import java.util.Map;
import java.util.Map.Entry;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:/META-INF/spring/integration/data-collection-context.xml" })
public class BuildExpirationCyclesTest {

	private final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private ApplicationContext context;
	
	@Autowired @Qualifier("buildExpirationCycles")
	private BuildExpirationCycles cut;

	private static String html;
	
	@Before
	public void init() throws Exception {
		if (html == null) {
			html = IOUtils.toString((context
					.getResource("/META-INF/spring/integration/initial.xml"))
					.getInputStream());
		}
	}
	
	@Test
	public void buildFullExpirationCycleMapForOneTicker() throws Exception {
		
		log.info("hello");
		String ticker = "SLW"; //TODO: this needs to be obtained from the message
		ExtractionProcessContext ctx = new ExtractionProcessContext(html, ticker);
		assertTrue("expecting empty Exp cycles map",ctx.getQuote().getExpirationCycles().isEmpty());
		cut.process(ctx);
		assertFalse("expecting current exp cycle entry in the Exp cycles map",ctx.getQuote().getExpirationCycles().isEmpty());
		//check ordering of expiration cycles
		for(Map.Entry<String, ExpirationCycle> e : ctx.getQuote().getExpirationCycles().entrySet()){
			log.info(e.getKey() + " => " +e.getValue().getUrl());
		}
		
		
		
	}

}
