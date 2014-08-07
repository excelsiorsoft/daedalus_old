/**
 * 
 */
package com.excelsiorsoft.daedalus.service.evaluation;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.util.Assert;

import com.excelsiorsoft.daedalus.domain.Strike;

/**
 * @author Simeon
 *
 */
public abstract class CompositeEvaluation extends AbstractEvaluation {
	
	private List<Evaluation> constituentEvals = new LinkedList<Evaluation>();

	/**
	 * This is were we bootstrap the composite with relevant constituents
	 */
	@PostConstruct
	protected abstract void initialize();
	
	public CompositeEvaluation add(Evaluation eval){
		Assert.notNull(eval, "Non-null evaluation must be passed in.");
		constituentEvals.add(eval);
		return this;
	}
	
	@Override
	public boolean worthwhile(Strike strike) {
		Assert.notNull(strike, "Quote object must not be null");
		boolean result = false;
		for (Evaluation e : constituentEvals) {
			if (!e.worthwhile(strike)) {
				return false;
			} else {
				result = true;
				continue;
			}

		}
		return result;
	}
	


}
