/**
 * 
 */
package com.excelsiorsoft.daedalus.service.datacollection.process;

/**
 * @author Simeon
 *
 */
public interface ExtractionStep {
	
	void process(ExtractionProcessContext context) throws Exception;

}
