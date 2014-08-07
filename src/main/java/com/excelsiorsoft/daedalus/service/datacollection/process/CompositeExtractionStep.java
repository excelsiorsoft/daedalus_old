/**
 * 
 */
package com.excelsiorsoft.daedalus.service.datacollection.process;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.springframework.util.Assert;
import static com.excelsiorsoft.daedalus.domain.AbstractDomain.*;

/**
 * @author Simeon
 * 
 */
public abstract class CompositeExtractionStep extends AbstractExtractionStep {

	protected List<ExtractionStep> steps = new LinkedList<ExtractionStep>();

	/**
	 * This is were we bootstrap the composite with relevant constituents
	 */
	@PostConstruct
	protected abstract void initialize();

	public List<ExtractionStep> getSteps() {
		return steps;
	}

	public void setSteps(List<ExtractionStep> steps) {
		this.steps = steps;
	}

	public void process(final ExtractionProcessContext context)
			throws Exception {

		for (Iterator<ExtractionStep> it = steps.iterator(); it.hasNext();) {
			it.next().process(context);
		}

		log.info("Done with " + this.getClass().getSimpleName()
				+ " step. Resulting quote is: " + context.getQuote());

	}

	public CompositeExtractionStep add(ExtractionStep step) {
		Assert.notNull(step, "Non-null step must be passed in.");
		steps.add(step);
		return this;
	}

}
