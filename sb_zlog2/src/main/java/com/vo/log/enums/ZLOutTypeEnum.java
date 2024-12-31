package com.vo.log.enums;

/**
 *
 *
 * @author zhangzhen
 * @date 2020-12-17 15:16:56
 *
 */
public enum ZLOutTypeEnum {

	CONSOLE("console"),

	FILE("file"),

	;

	private final String v;

	ZLOutTypeEnum(final String v) {
		this.v = v;
	}

}
