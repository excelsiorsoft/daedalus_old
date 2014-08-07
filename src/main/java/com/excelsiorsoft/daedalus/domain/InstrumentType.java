/**
 * 
 */
package com.excelsiorsoft.daedalus.domain;

import javax.persistence.Entity;

/**
 * @author Simeon
 *
 */
@Entity
public enum InstrumentType {
	OPTION, STOCK, FUTURE;
	@Entity
	public static enum OptionType{
		PUT, CALL;
	}
}
