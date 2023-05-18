package com.vo.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.boot.autoconfigure.BackgroundPreinitializer;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * 
 * @author zhangzhen
 * @date 2020-12-17 14:09:33
 * 
 */
@Getter
@AllArgsConstructor
public enum ZLogLevelEnum {

	TRACE("TRACE"),

	DEBUG("DEBUG"),

	INFO("INFO"),

	WARN("WARN"),

	ERROR("ERROR"),

	FATAL("FATAL"),

	;

	private static Map<String, ZLogLevelEnum> map = new HashMap<>();

	public static ZLogLevelEnum valueByNameLowerCase(final String name) {
		if (StrUtil.isEmpty(name)) {
			throw new IllegalArgumentException("name 不能为空");
		}
		
		final ZLogLevelEnum zLogLevelEnum = map.get(name.toLowerCase());
		if (Objects.isNull(zLogLevelEnum)) {
			throw new IllegalArgumentException("ZLogLevelEnum 配置错误");
		}
		return zLogLevelEnum;
	}
	
	static {
		final ZLogLevelEnum[] es = values();
		for (final ZLogLevelEnum zlLevelEnum : es) {
			map.put(zlLevelEnum.name().toLowerCase(), zlLevelEnum);
		}
	}

	private String level;
	
}
