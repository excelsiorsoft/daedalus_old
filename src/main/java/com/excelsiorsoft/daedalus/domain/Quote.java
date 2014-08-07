/**
 * 
 */
package com.excelsiorsoft.daedalus.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.util.Assert;



/**
 * @author Simeon
 * 
 */
@Entity
public final class Quote extends AbstractDomain {

	@Id
	protected Long id;
	
	//public final static String TICKER = "ticker";

	//private final String ticker;
	
	private /*final*/ Underlying underlying/* = new Underlying()*/;
	
	//private OptionType type;

	private final Map<String, ExpirationCycle> expirationCycles = new LinkedHashMap<String /*name TODO: or id?*/, ExpirationCycle /*respective cycle*/ >();
	
	/*private final List<ExpirationCycle> expirationCycles = new LinkedList<ExpirationCycle>();*/

	/*public Quote(String ticker) {
			
		underlying = new Underlying().setType(Instrument.STOCK);

		underlying.setSymbol(ticker);
		this.setTimestamp(new Timestamp(new Date().getTime()));
	}*/
	
	private Quote(){}
	
	public Quote(Underlying underlying){
		this.underlying = underlying;
		this.setTimestamp(new Timestamp(new Date().getTime()));
	}

	public Underlying getUnderlying() {
		return underlying;
	}

	public Map<String, ExpirationCycle> getExpirationCycles() {
		return expirationCycles;
	}

	public final static class QuoteBuilder<T> {
		
		private final Underlying underlying;

		
		/**
		 * Private constructor to be invoked from the static factory methods only.
		 */
		private QuoteBuilder(Underlying underlying) {
			Assert.notNull(underlying, "underlying must not be null");
			this.underlying = underlying;

	
		}
		
		public static <T> QuoteBuilder<T> withUnderlying(Underlying underlying) {
			Assert.notNull(underlying, "underlying must be present");
			Assert.notNull(underlying.getSymbol(), "ticker must be present");
			QuoteBuilder<T> builder = new QuoteBuilder<T>(underlying);
			return builder;
		}
		
		public static <T> QuoteBuilder<T> withTicker(String ticker) {
			Assert.notNull(ticker, "ticker must be present");
			Underlying underlying  = new Underlying().setType(InstrumentType.STOCK).setSymbol(ticker);
			QuoteBuilder<T> builder = new QuoteBuilder<T>(underlying);
			return builder;
		}
		
		public static <T> QuoteBuilder<T> withInstrument(String symbol, InstrumentType typeOfUnderlying) {
			Assert.notNull(symbol, "symbol must be present");
			Assert.notNull(typeOfUnderlying, "type of Underlying instrument must be present");
			Underlying underlying  = new Underlying().setType(typeOfUnderlying).setSymbol(symbol);
			QuoteBuilder<T> builder = new QuoteBuilder<T>(underlying);
			return builder;
		}
		
		public Quote build() {
			
			return new Quote(underlying);
		}
	}

}
