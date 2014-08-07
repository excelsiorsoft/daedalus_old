/**
 * 
 */
package com.excelsiorsoft.daedalus.domain;

import java.sql.Timestamp;

/**
 * @author Simeon
 *
 */
public interface Traceable {
	
	Timestamp getTimestamp();

	Traceable setTimestamp(Timestamp timestamp);

}
