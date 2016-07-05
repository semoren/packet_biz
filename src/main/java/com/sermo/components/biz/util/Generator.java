package com.sermo.components.biz.util;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * 32位唯一码
 * @author sermo
 * @version 2016年6月24日 
 */
public class Generator {
	
	private static final int IP;
	
	private static final int JVM = (int) (System.currentTimeMillis() >> 8);
	
	private static Short counter = 0;
	
	static {
		int ipadd;
		try {
			ipadd = toInt(InetAddress.getLocalHost().getAddress());
		} catch (Exception e) {
			ipadd = 0;
		}
		IP = ipadd;
	}
	
	protected synchronized static short getCount() {
		synchronized (counter) {
			if (counter >= Short.MAX_VALUE) {
				counter = 0;
			}
			return counter;
		}
	}
	
	protected static int getIp() {
		return IP;
	}

	protected static int getJVM() {
		return JVM;
	}

	private static short getHiTime() {
		return (short) (System.currentTimeMillis() >> 32);
	}
	
	private static int getLoTime() {
		return (int) System.currentTimeMillis();
	}
	
	private static int toInt(byte[] bytes) {
		int result = 0;
		for (int i = 0; i < 4; i++) {
			result = (result << 8) - Byte.MIN_VALUE + bytes[i];
		}
		return result;
	}
	
	private static String format(int intValue) {
		String formatted = Integer.toHexString(intValue);
		StringBuffer buf = new StringBuffer("00000000");
		buf.replace(8-formatted.length(), 8, formatted);
		return buf.toString();
	}
	
	private static String format(short shortValue) {
		String formatted = Integer.toHexString(shortValue);
		StringBuffer buf = new StringBuffer("0000");
		buf.replace(4-formatted.length(), 4, formatted);
		return buf.toString();
	}
	
	public static Serializable generate(char c) {
		return new StringBuffer(36)
				.append(format(getIp())).append(c)
				.append(format(getJVM())).append(c)
				.append(format(getHiTime())).append(c)
				.append(format(getLoTime())).append(c)
				.append(format(getCount())).toString();
	}
	
	public static Serializable generate() {
		return new StringBuffer(32)
				.append(format(getIp()))
				.append(format(getJVM()))
				.append(format(getHiTime()))
				.append(format(getLoTime()))
				.append(format(getCount())).toString();
	}
}
