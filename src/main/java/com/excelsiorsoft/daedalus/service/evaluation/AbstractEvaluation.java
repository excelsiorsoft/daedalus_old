/**
 * 
 */
package com.excelsiorsoft.daedalus.service.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;


/**
 * @author Simeon
 *
 */
public abstract class AbstractEvaluation implements Evaluation {

	@Autowired 
	@Qualifier("potentialReturnCalculator")
	protected PotentialReturnCalculator returnCalculator;


}
