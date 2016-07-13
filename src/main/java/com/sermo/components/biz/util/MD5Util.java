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
			MessageDigest digest = MessageDigest.getInstance("md5");
			digest.update(inputText.getBytes("UTF8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encryptText;
	}
}
