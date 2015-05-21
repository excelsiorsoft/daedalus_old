/**
 * 
 */
package com.excelsiorsoft.daedalus.service.impl;

import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 * @author Simeon
 *
 */
public class JsonConfigBuilder {
	
	private static JsonConfig config;
	
	private JsonConfigBuilder(){
		config = new JsonConfig();
	}
	
	public static JsonConfigBuilder filter(PropertyFilter filter){

		JsonConfigBuilder builder = new JsonConfigBuilder();
		config.setJsonPropertyFilter(filter);
		return builder;
	}
	
	public JsonConfig build(){
		return config;
	}

}
