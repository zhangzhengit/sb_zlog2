package com.vo.handler;

import com.vo.log.enums.ZLOutTypeEnum;

/**
 *
 * 日志接口
 * 
 * @author zhangzhen
 * @date 2020-12-17
 * 
 */
public interface IZLogHandler {

	/**
	 * message中的参数占位符
	 */
	String MESSAGE_ARG_KEYWORD = "{}";

	String trace(final String message, final Object... args);

	String debug(final String message, final Object... args);

	String info(final String message, final Object... args);

	String warn(final String message, final Object... args);

	String error(final String message, final Object... args);

	String fatal(final String message, final Object... args);

	ZLOutTypeEnum outType();
	
	/**
	 * 此handler是否启用
	 * 
	 * @return
	 */
	boolean isEnable();

	/**
	 * 替换 log.xxx(message,args)
	 * 把args从前到后替换为message中的参数占位符 MESSAGE_ARG_KEYWORD
	 * 如：把 log.error("error,name={}", "zhangsan", 200);
	 * 	  替换为        "error,name=zhangsan"
	 * 	  返回          "error,name=zhangsan"
	 * 
	 * @param message		 
	 * @param args
	 * @return	 
	 * 	
	 */
	default StringBuilder parseARGS(final String message, final Object... args) {
		final StringBuilder mb = new StringBuilder(message);
		if (args != null && args.length >= 1) {
			int currentFI = 0;
			for (int ai = 0; ai < args.length; ai++) {
				final int i1 = mb.indexOf(MESSAGE_ARG_KEYWORD, currentFI);
				if (i1 > -1) {
					mb.replace(i1, i1 + MESSAGE_ARG_KEYWORD.length(), String.valueOf(args[ai]));
				}
				currentFI = i1 + MESSAGE_ARG_KEYWORD.length();
			}
		}

		return mb;
	}

}
