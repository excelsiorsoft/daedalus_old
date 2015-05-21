/**
 * 
 */
package com.excelsiorsoft.daedalus.service.impl;

/**
 * @author Simeon
 *
 */
public class StringUtilities {
	
	public static boolean isNumeric(String str)
	{
	  
	  //return str.matches("^[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$");
	  return str.matches("[+-]?(?:\\d+(?:\\.\\d*)?|\\.\\d+)");  
	    //return str.matches("[-+]?\\d*\\.?\\d*"); 
	}

}
