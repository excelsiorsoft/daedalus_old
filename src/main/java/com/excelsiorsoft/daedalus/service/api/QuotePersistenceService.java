/**
 * 
 */
package com.excelsiorsoft.daedalus.service.api;

import com.excelsiorsoft.daedalus.domain.Quote;

/**
 * @author Simeon
 *
 */
public interface QuotePersistenceService {
	
	void persist(Quote quote) throws Exception;

}
