/**
 * 
 */
package com.excelsiorsoft.daedalus.domain;

import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author Simeon
 *
 */
public final class QuoteCoordinate extends AbstractDomain {
	
	//TODO: do we need it here?
	private Timestamp timestamp;
	
	//TODO: do we need it here?
	private String ticker;
	
	private String expirationCycleName;
	
	private String expirationCycleId;
	
	private BigInteger strikeName;
	
	private BigInteger strikeId;

	public String getExpirationCycleName() {
		return expirationCycleName;
	}

	public QuoteCoordinate setExpirationCycleName(String expirationCycleName) {
		this.expirationCycleName = expirationCycleName;
		return this;
	}

	public String getExpirationCycleId() {
		return expirationCycleId;
	}

	public QuoteCoordinate setExpirationCycleId(String expirationCycleId) {
		this.expirationCycleId = expirationCycleId;
		return this;
	}

	public BigInteger getStrikeName() {
		return strikeName;
	}

	public QuoteCoordinate setStrikeName(BigInteger strikeName) {
		this.strikeName = strikeName;
		return this;
	}

	public BigInteger getStrikeId() {
		return strikeId;
	}

	public QuoteCoordinate setStrikeId(BigInteger strikeId) {
		this.strikeId = strikeId;
		return this;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public QuoteCoordinate setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public String getTicker() {
		return ticker;
	}

	public QuoteCoordinate setTicker(String ticker) {
		this.ticker = ticker;
		return this;
	}

}
