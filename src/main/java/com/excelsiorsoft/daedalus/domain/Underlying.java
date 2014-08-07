/**
 * 
 */
package com.excelsiorsoft.daedalus.domain;

//import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author Simeon
 *
 */
public class Underlying extends AbstractTradableDomain /*implements Tradable*/ {
	
	private InstrumentType type;
	
	//akin to a ticker for stocks but wider based on instrument type
	private String symbol;
	
	private String description;
	
	/*private BigDecimal bid;
	private BigDecimal ask;*/
	
	private List<Notion> affectedBy = new ArrayList<Notion>();

	public InstrumentType getType() {
		return type;
	}

	public Underlying setType(InstrumentType type) {
		this.type = type;
		return this;
	}

	public String getSymbol() {
		return symbol;
	}

	public Underlying setSymbol(String symbol) {
		this.symbol = symbol;
		return this;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

/*	public BigDecimal getBid() {
		return bid;
	}

	public Underlying setBid(BigDecimal bid) {
		this.bid = bid;
		return this;
	}
	
	public Underlying setBid(String bid) {
		this.bid = NumberUtils.isNumber(bid)?new BigDecimal(bid):new BigDecimal(ZERO);
		return this;
	}

	public BigDecimal getAsk() {
		return ask;
	}

	public Underlying setAsk(BigDecimal ask) {
		this.ask = ask;
		return this;
	}
	
	public Underlying setAsk(String ask) {
		this.ask = NumberUtils.isNumber(ask)?new BigDecimal(ask):new BigDecimal(ZERO);
		return this;
	}*/

	public List<Notion> getAffectedBy() {
		return affectedBy;
	}

	public void setAffectedBy(List<Notion> affectedBy) {
		this.affectedBy = affectedBy;
	}


}
