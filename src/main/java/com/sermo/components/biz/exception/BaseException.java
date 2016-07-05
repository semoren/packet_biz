package com.sermo.components.biz.exception;

import com.sermo.components.biz.packet.BasePacket;

/**
 * 业务异常类
 * @author sermo
 * @version 2016年6月24日  
 */
public class BaseException extends Exception{

	private static final long serialVersionUID = 6193314902396086987L;
	
	private final BasePacket packet;
	
	public BaseException(BasePacket packet) {
		super(packet.toString());
		this.packet = packet;
	}
	
	public BaseException(BasePacket packet, Throwable throwable) {
		super(packet.toString(), throwable);
		this.packet = packet;
	}

	/**
	 * 异常业务数据包
	 * @return
	 */
	public BasePacket getPacket() {
		return packet;
	}
	
}
