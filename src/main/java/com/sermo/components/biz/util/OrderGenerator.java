package com.sermo.components.biz.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单生成器
 * Created by sermo on 2016/11/15.
 */
public class OrderGenerator {
    private static Integer counter = 0;

    private static DateFormat df = new SimpleDateFormat("yyMMddHHmmSSS");

    protected static Integer getCount() {
        synchronized (counter) {
            if (counter > 9999) counter = 0;
            return counter++;
        }
    }

    private static StringBuffer format(Integer value) {
        String str = Integer.toString(value);
        StringBuffer buf = new StringBuffer("0000");
        buf.replace(4 - str.length(), 4, str);
        return buf;
    }

    public static String generate() {

        String text = new StringBuffer(17).append(df.format(new Date())).append(format(getCount())).toString();
//        return md5(text).substring(7, 24);
        return md5(text);
    }

    public static String md5(String inputText) {
        try {

            MessageDigest m = MessageDigest.getInstance("md5");

            m.update(inputText.getBytes("UTF8"));

            byte s[] = m.digest();

            return hex(s);

        } catch (NoSuchAlgorithmException e) {

            e.printStackTrace();

        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();

        }
        return null;
    }

    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(generate());
        }
    }
}
