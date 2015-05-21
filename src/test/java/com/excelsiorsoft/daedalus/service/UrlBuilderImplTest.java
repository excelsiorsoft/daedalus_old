/**
 * 
 */
package com.excelsiorsoft.daedalus.service;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excelsiorsoft.daedalus.service.api.UrlBuilder;

/**
 * @author Simeon
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:/META-INF/spring/integration/data-collection-context.xml"})
public class UrlBuilderImplTest {
	
	@Autowired @Qualifier("urlBuilder")
	private UrlBuilder cut;

	/**
	 * Test method for {@link com.excelsiorsoft.daedalus.service.impl.UrlBuilderImpl#forTicker(java.lang.String)}.
	 */
	@Test
	public void testForTicker() {
		String url = cut.forTicker("SLW");
		assertTrue("wrong url constructed",url.equalsIgnoreCase("http://finance.yahoo.com/q/op?s=SLW+Options"));
	}

	/**
	 * Test method for {@link com.excelsiorsoft.daedalus.service.impl.UrlBuilderImpl#forExpirationCycle(java.lang.String)}.
	 */
	@Test
	public void testForExpirationCycle() {
		String url = cut.forExpirationCycle("/q/op?s=SLW&m=2013-03");
		assertTrue("wrong url constructed",url.equalsIgnoreCase("http://finance.yahoo.com/q/op?s=SLW&m=2013-03"));
	}

}
