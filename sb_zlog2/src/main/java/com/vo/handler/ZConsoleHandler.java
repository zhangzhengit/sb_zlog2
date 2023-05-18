package com.vo.handler;

import com.vo.conf.ZConsoleConf;
import com.vo.log.enums.ZLOutTypeEnum;

import cn.hutool.core.util.StrUtil;

/**
 * 
 * 
 * @author zhangzhen
 * @date 2022-1-11 18:42:40
 * 
 */
public final class ZConsoleHandler extends ZLogDefaultHandler {
	
	private final ZConsoleConf conf;

	public ZConsoleHandler(final ZConsoleConf conf) {
		this.conf = conf;
	}

	@Override
	public String trace(final String message, final Object... args) {
		final String trace = super.trace(message, args);
		println(trace);
		return trace;
	}

	@Override
	public String debug(final String message, final Object... args) {
		final String debug = super.debug(message, args);
		println(debug);
		return debug;
	}

	@Override
	public String info(final String message, final Object... args) {
		final String info = super.info(message, args);
		println(info);
		return info;
	}

	@Override
	public String warn(final String message, final Object... args) {
		final String warn = super.warn(message, args);
		println(warn);
		return warn;
	}

	@Override
	public String error(final String message, final Object... args) {
		final String error = super.error(message, args);
		println(error);
		return error;
	}

	@Override
	public String fatal(final String message, final Object... args) {
		final String fatal = super.fatal(message, args);
		println(fatal);
		return fatal;
	}

	@Override
	public ZLOutTypeEnum outType() {
		return ZLOutTypeEnum.CONSOLE;
	}

	@Override
	public boolean isEnable() {
		return this.conf.getEnable();
	}

	/**
	 * 此handler私有处理方法，输出到控制台
	 * 
	 * @param message
	 */
	private static void println(final String message) {
		if (StrUtil.isEmpty(message)) {
			return;
		}

		// out
		System.out.println(message);
	}

	@Override
	public String getLevel() {
		return this.conf.getLevel();
	}

	@Override
	public String getPattern() {
		return this.conf.getPattern();
	}
	
	
}
