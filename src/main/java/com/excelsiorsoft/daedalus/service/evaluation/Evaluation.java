/**
 * 
 */
package com.excelsiorsoft.daedalus.service.evaluation;

import com.excelsiorsoft.daedalus.domain.Strike;

/**
 * @author Simeon
 *
 */
public interface Evaluation {

	boolean worthwhile(Strike strike);
}
