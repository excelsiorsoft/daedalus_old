/**
 * 
 */
package com.excelsiorsoft.daedalus.service.datacollection.process;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.excelsiorsoft.daedalus.service.XPathHelper;

/**
 * @author Simeon
 *
 */
public abstract class AbstractExtractionStep implements ExtractionStep{
	
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired @Qualifier("XPathHelper")
	protected XPathHelper xpathHelper;
	
	//protected String underlying;

}
