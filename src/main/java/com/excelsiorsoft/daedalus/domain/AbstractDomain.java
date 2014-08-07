/**
 * 
 */
package com.excelsiorsoft.daedalus.domain;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;



import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.util.CollectionUtils;

/**
 * @author Simeon
 *
 */
@Entity
public class AbstractDomain  implements Reportable {

	@Id
	protected Long id;
	
	protected Timestamp timestamp;


	public Long getId() {
		return id;
	}
	
	public AbstractDomain setId(Long id) {
		this.id = id;
		return this;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public AbstractDomain setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
		return this;
	}

	public String toString() {
		   return ReflectionToStringBuilder.toString(this, NullSupressingStyle.INSTANCE);
		 }

 
	 
	 /**
	  * Used by Apache {@code ReflectionToStringBuilder} to supply a mechanism for it
	  * to exclude <code>null</code>-valued properties while logging.
	  * 
	 * @author Simeon
	 *
	 */
	private static class NullSupressingStyle extends ToStringStyle {

		private static final long serialVersionUID = -7510579086302510779L;
		private final static ToStringStyle INSTANCE = new NullSupressingStyle();

		 private NullSupressingStyle() {
			 super();
		 	//setArrayContentDetail(true);
		 	//setUseShortClassName(true);
		 	//setUseClassName(true);
		 	//setUseIdentityHashCode(false);
		 	//setFieldSeparator(", " + SystemUtils.LINE_SEPARATOR + "  ");
			
		 }

		 public static ToStringStyle getInstance() {
		 	return INSTANCE;
		 };

		 /*@Override
		 public void appendDetail(StringBuffer buffer, String fieldName, Object value) {
		 	if (!value.getClass().getName().startsWith("java")) {
		 		buffer.append(ReflectionToStringBuilder.toString(value, instance));
		 	} else {
		 		super.appendDetail(buffer, fieldName, value);
		 	}
		 }*/
		 
		 
		protected final void appendNullText(StringBuffer buffer, String fieldName) {
			buffer.delete(buffer.length()-(fieldName.length()+this.getFieldNameValueSeparator().length()+this.getFieldSeparator().length()), buffer.length());
			return;
		}
		 
		/*@Override
		 public void append(StringBuffer buffer, String fieldName, Object value, Boolean fullDetail) {
		        appendFieldStart(buffer, fieldName);

		        if (value == null) {
		        	buffer.delete(buffer.length()-(fieldName.length()+this.getFieldNameValueSeparator().length()), buffer.length());
		            return;appendNullText(buffer, fieldName);

		        } else {
		            appendInternal(buffer, fieldName, value, isFullDetail(fullDetail));
		        }

		        appendFieldEnd(buffer, fieldName);
		    }*/

		 /*@Override
		 public void appendDetail(StringBuffer buffer, String fieldName, Collection value) {
		 	appendDetail(buffer, fieldName, value.toArray());
		 }*/
		 
		 /**
			 * <p>Ensure <code>Singleton</code> after serialization.</p>
			 * @return the singleton
			 */
			private Object readResolve() {
				return INSTANCE;
			}
	 }
	 
	 
	 
	 /**
	  * Used during serialization of the model into json to exclude <code>null</code>-valued properties
	 * @author Simeon
	 *
	 */
	public static class NullPropertyFilter implements PropertyFilter{

		 @Override
		 public boolean apply( Object source, String name, Object value ) {  
		      if( value == null ){  
		         return true;  
		      }  
		      return false;  
		   }  
		 
	 }
	
}
