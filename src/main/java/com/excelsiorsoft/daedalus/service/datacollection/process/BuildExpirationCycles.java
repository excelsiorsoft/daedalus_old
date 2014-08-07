/**
 * 
 */
package com.excelsiorsoft.daedalus.service.datacollection.process;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Simeon
 * 
 */
@Component("buildExpirationCycles")
public class BuildExpirationCycles extends CompositeExtractionStep {

	@Autowired
	@Qualifier("buildCurrentExpirationCycle")
	private BuildCurrentExpirationCycle buildCurrentExpirationCycle;

	@Autowired @Qualifier("buildConsequentExpirationCycles")
	private BuildConsequentExpirationCycles buildConsequentExpirationCycles;

	@PostConstruct
	protected final void initialize() {

		this.add(buildCurrentExpirationCycle)
			.add(buildConsequentExpirationCycles);

	}

}
