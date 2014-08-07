/**
 * 
 */
package com.excelsiorsoft.daedalus.domain;

import java.math.BigDecimal;

import com.excelsiorsoft.daedalus.domain.InstrumentType.OptionType;

/**
 * @author Simeon
 * 
 */
public final class OptionTradeCandidate extends AbstractTradableDomain {

	private OptionType optionType; // put or call
	private String underlyingSymbol; // ticker, current price, market cap
	private String expCycle; // expirationCycleName

	private String strikeName;

	// statistics
	private BigDecimal defenciveBuffer;
	private BigDecimal spreadSize;
	private BigDecimal yield;
	
	public OptionTradeCandidate(Strike strike){
		strikeName = Double.toString(strike.getValue())/*.toString()*/;
		optionType = strike.getType();
		
	}

	public final OptionType getOptionType() {
		return optionType;
	}

	/*public final void setOptionType(OptionType optionType) {
		this.optionType = optionType;
	}*/

	public final String getUnderlyingSymbol() {
		return underlyingSymbol;
	}

	public final void setUnderlyingSymbol(String underlyingSymbol) {
		this.underlyingSymbol = underlyingSymbol;
	}

	public final String getExpCycle() {
		return expCycle;
	}

	public final void setExpCycle(String expCycle) {
		this.expCycle = expCycle;
	}

	public final String getStrikeName() {
		return strikeName;
	}

	/*public final void setStrikeName(String strikeName) {
		this.strikeName = strikeName;
	}*/

	public final BigDecimal getDefenciveBuffer() {
		return defenciveBuffer;
	}

	public final void setDefenciveBuffer(BigDecimal defenciveBuffer) {
		this.defenciveBuffer = defenciveBuffer;
	}

	public final BigDecimal getSpreadSize() {
		return spreadSize;
	}

	public final void setSpreadSize(BigDecimal spreadSize) {
		this.spreadSize = spreadSize;
	}

	public final BigDecimal getYield() {
		return yield;
	}

	public final void setYield(BigDecimal yield) {
		this.yield = yield;
	}

}
