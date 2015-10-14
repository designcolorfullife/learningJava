package com.zhangwei.learning.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 打日志的小工具
 * @author Administrator
 *
 */
public class LoggerUtils {
	
	private static Logger logger = LoggerFactory.getLogger(LoggerUtils.class);
	
	static {
	}
	
	
	
	/**
	 * 
	 * @param logger
	 * @param info
	 */
	public static void Info(Logger logger, String info){
		if(logger.isInfoEnabled()){
			logger.info(info);
		}
	}
	
	/**
	 * 提供一个默认的logger
	 * @param info
	 */
	public static void Info(String info){
		Info(logger, info);
	}
}
