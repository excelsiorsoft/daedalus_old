/**
 * 
 */
package com.excelsiorsoft.daedalus.service.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author Simeon
 * 
 */
@Component("optionStrikeEvaluator")
public class OptionStrikeEvaluator extends CompositeEvaluation {

	@Autowired
	@Qualifier("bidAskSpreadSizeEvaluation")
	private BidAskSpreadSizeEvaluation bidAskSpreadSizeEvaluation;

	@Autowired
	@Qualifier("yieldSizeEvaluation")
	private YieldSizeEvaluation yieldSizeEvaluation;

	@Override
	protected void initialize() {
		this.add(bidAskSpreadSizeEvaluation).add(yieldSizeEvaluation);

	}

}
