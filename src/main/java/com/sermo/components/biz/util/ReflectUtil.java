package com.sermo.components.biz.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.sermo.components.biz.exception.BaseException;

/**
 * 反射机制工具类
 * @author sermo
 * @version 2016年6月24日 
 */
public class ReflectUtil {
	
	/**
	 * 取出 cls 类及其超类的所有带有 anno 注解的方法
	 * @param cls
	 * @param anno
	 * @return
	 */
	public static List<Method> getMethods(Class<?> cls, Class<? extends Annotation> anno) {
		Class<?> sup = cls.getSuperclass();
		List<Method> lists = new ArrayList<>();
		if (sup != null && !Object.class.equals(sup)) {
			lists.addAll(getMethods(sup, anno));
		}
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			if (method.isAnnotationPresent(anno)) {
				lists.add(method);
			}
		}
		return lists;
	}
	
	/**
	 * 取出 cls 类及其超类的所有带有 anno 注解的变量
	 * @param cls
	 * @param anno
	 * @return
	 */
	public static List<Field> getFields(Class<?> cls, Class<? extends Annotation> anno) {
		Class<?> sup = cls.getSuperclass();
		List<Field> lists = new ArrayList<>();
		if (sup != null && !Object.class.equals(sup)) {
			lists.addAll(getFields(sup, anno));
		}
		Field[] fields = sup.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(anno)) {
				lists.add(field);
			}
		}
		return lists;
	}
	
	/**
	 * 取出 cls 类及其超类的所有方法
	 * @param cls
	 * @return
	 */
	public static List<Method> getMethods(Class<?> cls) {
		Class<?> sup = cls.getSuperclass();
		List<Method> lists = new ArrayList<>();
		if (sup != null && !Object.class.equals(sup)) {
			lists.addAll(getMethods(sup));
		}
		Method[] methods = sup.getDeclaredMethods();
		for (Method method : methods) {
			lists.add(method);
		}
		return lists;
	}
	
	/**
	 * 取出 cls 类及其超类的所有变量
	 * @param cls
	 * @return
	 */
	public static List<Field> getField(Class<?> cls) {
		Class<?> sup = cls.getSuperclass();
		List<Field> lists = new ArrayList<>();
		if (sup != null && !Object.class.equals(sup)) {
			lists.addAll(getField(sup));
		}
		Field[] fields = sup.getDeclaredFields();
		for (Field field : fields) {
			lists.add(field);
		}
		return lists;
	}
	
	/**
	 * 取出 cls 类及其超类的 str 变量
	 * @param cls
	 * @param str
	 * @return
	 */
	public static Field getField(Class<?> cls, String str) {
		try {
			return cls.getDeclaredField(str);
		} catch (Exception e) {
			Class<?> sup = cls.getSuperclass();
			if (sup != null && !Object.class.equals(sup)) {
				return getField(sup, str);
			}
		}
		return null;
	}
	
	public static Object getProperty(Object obj, String str) throws BaseException {
		return getProperty(obj, getField(obj.getClass(), str));
	}

	public static Object getProperty(Object obj, Field field) throws BaseException {
		try {
			field.setAccessible(true);
			return field.get(obj);
		} catch (Exception e) {
			try {
				String str = BaseUtil.eq(BaseUtil.BOOLEAN, field.getType()) ? "is" : "get" + StringUtil.firstToCase(field.getName(), true);
				Method method = obj.getClass().getMethod(str);
				if (method != null) {
					return method.invoke(obj);
				}
			} catch (Exception ex) {
				throw ExceptionUtil.exception(0, "Get property error! object[#0], field[#1]", ex, obj, field.getName());
			}
		}
		return null;
	}
	
	public static void setProperty(Object obj, String f, Object value) throws BaseException {
		setProperty(obj, getField(obj.getClass(), f), value);
	}
	
	public static void setProperty(Object obj, Field field, Object value) throws BaseException {
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			try {
				Method method = obj.getClass().getMethod("set" + StringUtil.firstToCase(field.getName(), true), field.getType());
				if (method != null) {
					method.invoke(obj, value);
				}
			} catch (Exception ex) {
				throw ExceptionUtil.exception(0, "Set property error! object[#0], field[#1], value[#2]", ex, obj, field.getName(), value);
			}
		}
	}
	
	public static void setProperty(Object obj, String f, String value) throws BaseException {
		setProperty(obj, getField(obj.getClass(), f), value);
	}
	
	public static void setProperty(Object obj, Field field, String value) throws BaseException {
		setProperty(obj, field, BaseUtil.get(field.getType()).parse(value));
	}
	
	public static Class<?> getSuperClassGenricType(Class<?> cls) {
		return getGenericType(cls, 0);
	}
	
	public static Type[] getGenericTypes(Type type) {
		if (type == null || Object.class.equals(type)) {
			return null;
		}
		if (!(type instanceof ParameterizedType)) {
			return getGenericTypes(((Class<?>)type).getGenericSuperclass());
		}
		return ((ParameterizedType)type).getActualTypeArguments();
	}
	
	public static Class<?> getGenericType(Type type, int index) {
		Type[] types = getGenericTypes(type);
		if (types != null && types.length > 0 && types.length > index) {
			return (Class<?>)types[index];
		}
		return null;
	}
}
