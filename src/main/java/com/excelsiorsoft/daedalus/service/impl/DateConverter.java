package com.excelsiorsoft.daedalus.service.impl;

/**
 * 
 * Converts from one String-based date representation to another
 * @author Simeon
 *
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

	public static final String IN_FORMAT = "MMM yy";
	public static final String OUT_FORMAT = "yyyy-MM";

	public static String convert(final String in) throws Exception {

		return convert(in, IN_FORMAT, OUT_FORMAT);
	}

	public static String convert(String in, String inFormat, String outFormat)
			throws Exception {
		String result = null;

		SimpleDateFormat sdfSource = new SimpleDateFormat(IN_FORMAT);

		Date date = sdfSource.parse(in);

		SimpleDateFormat sdfDestination = new SimpleDateFormat(OUT_FORMAT);

		result = sdfDestination.format(date);

		return result;
	}
}
