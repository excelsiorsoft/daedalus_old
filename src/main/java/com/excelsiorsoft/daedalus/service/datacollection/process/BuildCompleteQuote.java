/**
 * 
 */
package com.excelsiorsoft.daedalus.service.datacollection.process;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Simeon
 *
 */
@Component("buildCompleteQuote")
public class BuildCompleteQuote extends CompositeExtractionStep {

	@Autowired @Qualifier("buildExpirationCycles")
	private BuildExpirationCycles buildExpirationCyclesStep;
	
	@Autowired @Qualifier("buildAllPutStrikeSeries")
	private BuildAllPutStrikeSeries buildAllPutStrikeSeries;
	/* (non-Javadoc)
	 * @see com.excelsiorsoft.daedalus.service.datacollection.process.CompositeExtractionStep#initialize()
	 */
	@Override
	protected void initialize() {
		add(buildExpirationCyclesStep).add(buildAllPutStrikeSeries);

	}

}
