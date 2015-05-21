/**
 * 
 */
package com.excelsiorsoft.daedalus.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.excelsiorsoft.daedalus.domain.AbstractDomain.NullPropertyFilter;
import com.excelsiorsoft.daedalus.domain.Quote;
import com.excelsiorsoft.daedalus.service.api.QuotePersistenceService;

/**
 * @author Simeon
 *
 */
@Component("quotePersistenceService")
public class QuotePersistenceServiceImpl implements QuotePersistenceService {

	private final Log log = LogFactory.getLog(getClass());
	
	/*@PersistenceContext
	private EntityManager entityManager;*/
	
	/* (non-Javadoc)
	 * @see com.excelsiorsoft.daedalus.service.QuotePersistenceService#persist(com.excelsiorsoft.daedalus.domain.Quote)
	 */
	@Override
	//@Transactional()
	public void persist(Quote quote) throws Exception {
		

		
		/*JsonConfig jsonConfig = new JsonConfig();  
		jsonConfig.setJsonPropertyFilter( new NullPropertyFilter());  */
		
		log.info("persisting as an object: "+quote);
		//log.info("persisting in json:" +JSONObject.fromObject(quote,jsonConfig));
		log.info("persisting in json:" +JSONObject.fromObject(quote,JsonConfigBuilder.filter(new NullPropertyFilter()).build()));
	}

}
