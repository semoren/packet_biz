package com.sermo.components.biz.util;

import com.sermo.components.biz.exception.BaseException;
import com.sermo.components.biz.packet.BasePacket;

/**
 * 业务异常工具类
 * @author sermo
 * @version 2016年6月24日 
 */
public class ExceptionUtil {
	
	public static BaseException exception(int code, String msg) {
		BasePacket packet = new BasePacket();
		packet.setCode(code);
		packet.setMsg(msg);
		return new BaseException(packet);
	}
	
	public static BaseException exception(int code, String msg, Object...objects) {
		BasePacket packet = new BasePacket();
		packet.setCode(code);
		packet.setMsg(StringUtil.format(msg, objects));
		return new BaseException(packet);
	}
	
	public static BaseException exception(int code, String msg, Throwable throwable) {
		BasePacket packet = new BasePacket();
		packet.setCode(code);
		packet.setMsg(msg);
		return new BaseException(packet, throwable);
	}
	
	public static BaseException exception(int code, String msg, Throwable throwable, Object...objects) {
		BasePacket packet = new BasePacket();
		packet.setCode(code);
		packet.setMsg(StringUtil.format(msg, objects));
		return new BaseException(packet, throwable);
	}
}
