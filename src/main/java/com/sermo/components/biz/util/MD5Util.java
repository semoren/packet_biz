package com.sermo.components.biz.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author sermo
 * @version 2016年7月13日 
 */
public class MD5Util {
	
	private final static String SALT = "OTsKtruI&Dp#Y6Fe";
	
	/**
	 * 
	 * @param inputText
	 * @return
	 */
	public static String encrypt(String inputText) {
		
		if (inputText == null || "".equals(inputText.trim())) {
			throw new IllegalArgumentException("请输入要加密的内容");
		}
		
		String encryptText = null;
		
		inputText = inputText + SALT;
		try {
			MessageDigest digest = MessageDigest.getInstance("MD5");
			digest.update(inputText.getBytes("UTF8"));
			byte b[] = digest.digest();
			return hex(b);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptText;
	}
	
	// 返回十六进制字符串
		private static String hex(byte[] arr) {

			StringBuffer sb = new StringBuffer();

			for (int i = 0; i < arr.length; ++i) {

				sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
						3));

			}

			return sb.toString();

		}
}
