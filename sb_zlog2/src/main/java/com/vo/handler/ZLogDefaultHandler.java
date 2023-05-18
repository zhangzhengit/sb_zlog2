package com.vo.handler;

import com.vo.enums.ZLogLevelEnum;
import com.vo.log.enums.ZLOutTypeEnum;
import com.vo.log.enums.ZLPatternEnum;

import cn.hutool.core.util.StrUtil;

/**
 *
 * 默认的Handler，只根据pattern和message和args 解析出日志内容，只返回一个String不做其它操作。
 * 需要往其它组件输入，extends此类 ，在xxx方法中 super().xxx，处理返回的结果。
 * 
 * @author zhangzhen
 * @date 2020-12-17
 * 
 */
public abstract class ZLogDefaultHandler implements IZLogHandler {

	public static final String EMPTY_STRING = "";
	public static final int TRACE_ORDINAL = ZLogLevelEnum.TRACE.ordinal();
	public static final int DEBUG_ORDINAL = ZLogLevelEnum.DEBUG.ordinal();
	public static final int INFO_ORDINAL = ZLogLevelEnum.INFO.ordinal();
	public static final int WARN_ORDINAL = ZLogLevelEnum.WARN.ordinal();
	public static final int ERROR_ORDINAL = ZLogLevelEnum.ERROR.ordinal();
	public static final int FATAL_ORDINAL = ZLogLevelEnum.FATAL.ordinal();

	@Override
	public String trace(final String message, final Object... args) {
		
		if (this.getLevelO() > TRACE_ORDINAL) {
			return EMPTY_STRING;
		}
		
		return parseMessage(message,  getPattern(), args);
	}

	@Override
	public String debug(final String message, final Object... args) {
		if (this.getLevelO() > DEBUG_ORDINAL) {
			return EMPTY_STRING;
		}
		
		return parseMessage(message,  getPattern(), args);
	}

	@Override
	public String info(final String message, final Object... args) {
		if (this.getLevelO() > INFO_ORDINAL) {
			return EMPTY_STRING;
		}
		return parseMessage(message,  getPattern(), args);
	}

	@Override
	public String warn(final String message, final Object... args) {
		if (this.getLevelO() > WARN_ORDINAL) {
			return EMPTY_STRING;
		}
		
		return parseMessage(message, getPattern(), args);
	}

	@Override
	public String error(final String message, final Object... args) {
		if (this.getLevelO() > ERROR_ORDINAL) {
			return EMPTY_STRING;
		}
		return parseMessage(message,  getPattern(), args);
	}

	@Override
	public String fatal(final String message, final Object... args) {
		if (this.getLevelO() > FATAL_ORDINAL) {
			return EMPTY_STRING;
		}
		return parseMessage(message,  getPattern(), args);
	}

	@Override
	public ZLOutTypeEnum outType() {
		return ZLOutTypeEnum.CONSOLE;
	}

	/**
	 * 根据pattern和message 和args解析出完整的日志内容
	 * 
	 * 如： pattern 配置为: %DATE_TIME [%LEVEL]
	 * [%THREAD]-[%CLASS_NAME::%METHOD@%LINE_NUMBER] : [%MESSAGE] 调用log.xxx方法:
	 * log.warn("WARN,name={},id={}", "zhangsan", 200); 返回结果为:
	 * 2011-11-1T11:11:11.111 [WARN]
	 * [main]-[com.vo.log.SbZlog20201217ApplicationTests::test_INFO2@75] :
	 * [WARN,name=zhangsan,id=200]
	 * 
	 * @param message
	 * @param pattern 
	 * @param args
	 * @return
	 */
	protected String parseMessage(final String message, final String pattern, final Object... args) {
		if (StrUtil.isEmpty(pattern)) {
			return EMPTY_STRING;
		}

		if (!this.isEnable()) {
			return EMPTY_STRING;
		}

		final StringBuilder mb = parseARGS(message, args);
		final StringBuilder m = ZLPatternEnum.parse(pattern, mb.toString());

		return m.toString();
	}

	@Override
	public boolean isEnable() {
		return true;
	}

	private int loc = -20;
	
	private int getLevelO() {
		if (this.loc < 0) {
			final String level = this.getLevel();
			final ZLogLevelEnum ke = ZLogLevelEnum.valueByNameLowerCase(level);
			this.loc = ke.ordinal();
		}
		
		return this.loc;
	}
	
	public abstract String getLevel();

	public abstract String getPattern();

}
