package com.vo.log.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 *
 * @author zhangzhen
 * @date 2020-12-17 15:16:56
 *
 */
@Getter
@AllArgsConstructor
public enum ZLOutTypeEnum {

	CONSOLE("console"),

	FILE("file"),

	DB("db"),

	LOG_CENTER("log_center"),

	ZMQ("zmq"),

	HTTP("http"),

	;

	private String v;
}
