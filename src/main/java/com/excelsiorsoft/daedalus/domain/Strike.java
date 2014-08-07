/**
 * 
 */
package com.excelsiorsoft.daedalus.domain;

import static org.apache.commons.lang3.math.NumberUtils.DOUBLE_ZERO;
import static org.apache.commons.lang3.math.NumberUtils.toDouble;

import javax.persistence.Entity;

import org.apache.commons.lang3.math.NumberUtils;

import com.excelsiorsoft.daedalus.domain.InstrumentType.OptionType;

/**
 * @author Simeon
 * 
 */
@Entity
public final class Strike extends AbstractTradableDomain/* implements Tradable*/ {
	
	private OptionType type;
	private /*BigDecimal*/ double value;
	/*private BigDecimal bid;
	private BigDecimal ask;*/

	
	public /*BigDecimal*/double getValue() {
		return value;
	}

	public Tradable setValue(/*BigDecimal*/double value) {
		this.value = value;
		return this;
	}
	
	public Tradable setValue(String value) {
		this.value=NumberUtils.isNumber(value)?/*new BigDecimal(*/toDouble(value)/*)*/:/*new BigDecimal(*/DOUBLE_ZERO.doubleValue()/*)*/;
		
		return this;
	}

	/*public BigDecimal getBid() {
		return bid;
	}*/

	/*public Tradable setBid(BigDecimal bid) {
		this.bid = bid;
		return this;
	}*/
	
	/*public Tradable setBid(String bid) {
		this.bid = NumberUtils.isNumber(bid)?new BigDecimal(bid):new BigDecimal(ZERO);
		return this;
	}*/

	/*public BigDecimal getAsk() {
		return ask;
	}*/

	/*public Tradable setAsk(BigDecimal ask) {
		this.ask = ask;
		return this;
	}*/
	 
	/*public Tradable setAsk(String ask) {
		this.ask = NumberUtils.isNumber(ask)?new BigDecimal(ask):new BigDecimal(ZERO);
		return this;
	}*/


	public OptionType getType() {
		return type;
	}

	public Tradable setType(OptionType type) {
		this.type = type;
		return this;
	}
}
