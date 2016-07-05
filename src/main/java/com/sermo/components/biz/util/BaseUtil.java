package com.sermo.components.biz.util;

import java.util.HashMap;
import java.util.Map;

import com.sermo.components.biz.interfaces.BaseType;

/**
 * 基础数据类型工具类
 * @author sermo
 * @version 2016年6月24日 
 */
public class BaseUtil {
	
	public static final String EMPTY = "";
	
	public static final int ZERO = 0;
	
	/**
	 * 字符串
	 */
	public static final BaseType<String> STRING = new BaseType<String>() {
		
		@Override
		public String parse(Object value) {
			if (value == null) {
				return EMPTY;
			}
			if (value instanceof String) {
				return (String) value;
			}
			return value.toString();
		}
		
		@Override
		public String nullToEmpty(String value) {
			return value == null ? EMPTY : value;
		}
		
		@Override
		public boolean isEmpty(String value) {
			return value == null || value.length() == 0;
		}
	};
	
	/**
	 * 布尔类型
	 */
	public static final BaseType<Boolean> BOOLEAN = new BaseType<Boolean>() {

		@Override
		public boolean isEmpty(Boolean value) {
			return value == null || !value;
		}

		@Override
		public Boolean parse(Object value) {
			if (value == null) {
				return false;
			}
			if (value instanceof Boolean) {
				return (Boolean) value;
			}
			return Boolean.parseBoolean(value.toString()) || "1".equals(value.toString());
		}

		@Override
		public Boolean nullToEmpty(Boolean value) {
			return value == null ? false : value;
		}
	};
	
	/**
	 * 整型
	 */
	public static final BaseType<Integer> INTEGER = new BaseType<Integer>() {

		@Override
		public boolean isEmpty(Integer value) {
			return value == null;
		}

		@Override
		public Integer parse(Object value) {
			if (value == null) {
				return ZERO;
			}
			if (value instanceof Integer) {
				return (Integer) value;
			}
			if (value instanceof Number) {
				return ((Number) value).intValue();
			}
			return Integer.parseInt(value.toString());
		}

		@Override
		public Integer nullToEmpty(Integer value) {
			return value == null ? ZERO : value;
		}
	};
	
	/**
	 * 长整型
	 */
	public static final BaseType<Long> LONG = new BaseType<Long>() {

		@Override
		public boolean isEmpty(Long value) {
			return value == null;
		}

		@Override
		public Long parse(Object value) {
			if (value == null) {
				return (long) ZERO;
			}
			if (value instanceof Long) {
				return (Long) value;
			}
			if (value instanceof Number) {
				return ((Number) value).longValue();
			}
			return Long.parseLong(value.toString());
		}

		@Override
		public Long nullToEmpty(Long value) {
			return value == null ? (long) ZERO : value;
		}
	};
	
	/**
	 * 浮点型
	 */
	public static final BaseType<Double> DOUBLE = new BaseType<Double>() {

		@Override
		public boolean isEmpty(Double value) {
			return value == null;
		}

		@Override
		public Double parse(Object value) {
			if (value == null) {
				return (double) ZERO;
			}
			if (value instanceof Double) {
				return (Double) value;
			}
			if (value instanceof Number) {
				return ((Number) value).doubleValue();
			}
			return Double.parseDouble(value.toString());
		}

		@Override
		public Double nullToEmpty(Double value) {
			return value == null ? (double) ZERO : value;
		}
	};
	
	private static Map<String, BaseType<?>> map = new HashMap<>();
	
	static {
		map.put("STRING", STRING);
		map.put("BOOLEAN", BOOLEAN);
		map.put("INT", INTEGER);
		map.put("INTEGER", INTEGER);
		map.put("LONG", LONG);
		map.put("DOUBLE", DOUBLE);
	}
	
	public static boolean contain(String type) {
		String t = type.toUpperCase();
		return !STRING.isEmpty(t) && map.containsKey(t);
	}
	
	public static BaseType<?> get(String type) {
		String t = type.toUpperCase();
		return contain(t) ? map.get(t) : STRING;
	}
	
	public static boolean contain(Class<?> cls) {
		String t = cls.getSimpleName().toUpperCase();
		return map.containsKey(t);
	}
	
	public static BaseType<?> get(Class<?> cls) {
		String t = cls.getSimpleName().toUpperCase();
		return map.containsKey(t) ? map.get(t) : STRING;
	}
	
	public static boolean eq(BaseType<?> bt, Class<?> cls) {
		String t = cls.getSimpleName().toUpperCase();
		return map.containsKey(t) ? bt.equals(map.get(t)) : false;
	}
	
	public static <T> boolean isEmpty(BaseType<T> bt, Object o) {
		return bt.isEmpty(bt.parse(o));
	}
}
