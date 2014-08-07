package com.excelsiorsoft.daedalus.domain;

//import java.math.BigDecimal;

import org.apache.commons.lang3.math.NumberUtils;
import static org.apache.commons.lang3.math.NumberUtils.*;

public class AbstractTradableDomain extends AbstractDomain implements Tradable {

	protected /*BigDecimal*/ double bid;
	protected /*BigDecimal*/double ask;
	
	public /*BigDecimal*/ double getBid() {
		return bid;
	}

	public Tradable setBid(/*BigDecimal*/double bid) {
		this.bid = bid;
		return this;
	}
	
	public Tradable setBid(String bid) {
		this.bid = NumberUtils.isNumber(bid)?/*new BigDecimal(*/toDouble(bid)/*)*/:/*new BigDecimal(*/DOUBLE_ZERO.doubleValue()/*)*/;
		return this;
	}

	public /*BigDecimal*/double getAsk() {
		return ask;
	}

	public Tradable setAsk(/*BigDecimal*/double ask) {
		this.ask = ask;
		return this;
	}
	 
	public Tradable setAsk(String ask) {
		this.ask = NumberUtils.isNumber(ask)?/*new BigDecimal(*/toDouble(ask)/*)*/:/*new BigDecimal(*/DOUBLE_ZERO.doubleValue()/*)*/;
		return this;
	}
}
