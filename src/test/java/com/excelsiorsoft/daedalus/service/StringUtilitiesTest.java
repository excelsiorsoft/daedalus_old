package com.excelsiorsoft.daedalus.service;

import static org.junit.Assert.*;

import org.apache.commons.lang3.math.NumberUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.excelsiorsoft.daedalus.service.impl.StringUtilities;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath*:/META-INF/spring/integration/data-collection-context.xml"})
public class StringUtilitiesTest {

	
	private StringUtilities cut;
	
	@SuppressWarnings("static-access")
	@Test
	public void testIsNumeric() {
		cut.isNumeric("N/A");
		assertFalse("",cut.isNumeric("N/A"));
		assertFalse("",cut.isNumeric("-"));
		assertFalse("",cut.isNumeric("."));
		assertTrue("",cut.isNumeric("0.5"));
		assertTrue("",cut.isNumeric(".5"));
		assertTrue("",cut.isNumeric("-.5"));
		assertTrue("",cut.isNumeric("-0.5"));
		assertTrue("",cut.isNumeric("+.5")); //<- different treatment here from NumberUtils.isNumber()
		assertTrue("",cut.isNumeric("+0.5"));//<- different treatment here from NumberUtils.isNumber()
		assertFalse("",cut.isNumeric(" "));
		assertFalse("",cut.isNumeric(""));
		assertTrue("",cut.isNumeric("42.5"));
		assertTrue("",cut.isNumeric("42."));
		
		assertFalse("",NumberUtils.isNumber("N/A"));
		assertFalse("",NumberUtils.isNumber("-"));
		assertFalse("",NumberUtils.isNumber("."));
		assertTrue("",NumberUtils.isNumber("0.5"));
		assertTrue("",NumberUtils.isNumber(".5"));
		assertTrue("",NumberUtils.isNumber("-.5"));
		assertTrue("",NumberUtils.isNumber("-0.5"));
		assertTrue("",NumberUtils.isNumber("0.0"));
		assertTrue("",NumberUtils.isNumber("0.5"));
		assertFalse("",NumberUtils.isNumber("+0.5"));
		assertFalse("",NumberUtils.isNumber("+.5"));
		assertFalse("",NumberUtils.isNumber(" "));
		assertFalse("",NumberUtils.isNumber(""));
		assertTrue("",NumberUtils.isNumber("42.5"));
		assertTrue("",NumberUtils.isNumber("42."));
	}

}
