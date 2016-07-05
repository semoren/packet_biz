package com.sermo.components.biz.util;

/**
 * String 工具类
 * @author sermo
 * @version 2016年6月24日 
 */
public class StringUtil {
	
	public static String format(String msg, Object... args) {
		if (msg != null && msg.length() > 0 && msg.indexOf('#') > -1) {
			StringBuilder sb = new StringBuilder();
			boolean isArg = false;
			for (int i = 0; i < msg.length(); i++) {
				char c = msg.charAt(i);
				if (isArg) {
					isArg = true;
					if (Character.isDigit(c)) {
						int val = Character.getNumericValue(c);
						if (val >= 0 && val < args.length) {
							sb.append(args[val]);
							continue;
						}
					}
					sb.append('#');
				}
				if (c == '#') {
					isArg = true;
					continue;
				}
				sb.append(c);
			}
			if (isArg) {
				sb.append('#');
			}
			return sb.toString();
		}
		return msg;
	}
	
	public static String nullToEmpty(Object...objects) {
		StringBuilder sb = new StringBuilder();
		for (Object obj : objects) {
			if (obj != null) {
				sb.append(obj.toString());
			}
		}
		return sb.toString();
	}
	
	public static String firstToCase(String s, boolean c) {
		if (BaseUtil.STRING.isEmpty(s)) {
			return s;
		} else {
			return new StringBuffer(s.length())
					.append(c ? Character.toUpperCase(s.charAt(0)) : Character.toLowerCase(s.charAt(0)))
					.append(s.substring(1))
					.toString();
		}
	}
}
