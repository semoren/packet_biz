package com.sermo.components.biz.interfaces;

/**
 * @author sermo
 * @version 2016年6月24日 
 */
public interface BaseType<T> {
	
	public boolean isEmpty(final T value);
	
	public T parse(final Object value);
	
	public T nullToEmpty(final T value);
	
}
