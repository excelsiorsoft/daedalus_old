/**
 * 
 */
package com.excelsiorsoft.daedalus.domain;

//import java.math.BigDecimal;

/**
 * @author Simeon
 *
 */
public interface Tradable {
	
	//public static final String ZERO = "0";
	//public static final double ZERO = 0.0;

	/*BigDecimal*/ double getBid() ;
	
	Tradable setBid(/*BigDecimal*/double  bid);
	
	Tradable setBid(String bid);

	/*BigDecimal*/double getAsk();

	Tradable setAsk(/*BigDecimal*/double ask);
	
	Tradable setAsk(String ask);

}
