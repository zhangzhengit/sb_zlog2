package com.vo.conf;

import java.util.List;
import java.util.Objects;

import com.vo.enums.ZLogLevelEnum;
import com.vo.log.enums.ZLOutTypeEnum;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

/**
 * 
 * 
 * @author zhangzhen
 * @date 2022-1-11 18:32:01
 * 
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ZConsoleConf {

	public static final Boolean DEFAULT_ENABLE = true;

	private String name;

	private Boolean enable;

	private String level;

	private String outTypeEnum;

	private String pattern;

	private List<String> excludedClass;

	private List<String> excludedPackage;

	public String getOutTypeEnum() {
		return this.outTypeEnum;
	}

	public void setOutTypeEnum(final String outTypeEnum) {
		if (StrUtil.isEmpty(outTypeEnum)) {
			throw new IllegalArgumentException("zlog.console.outTypeEnum 不能为空");
		}
		ZLOutTypeEnum.valueOf(outTypeEnum);
		this.outTypeEnum = outTypeEnum;
	}

	public void setLevel(final String level) {
		if (StrUtil.isEmpty(level)) {
			throw new IllegalArgumentException("zlog.console.level 不能为空");
		}
		ZLogLevelEnum.valueByNameLowerCase(level);
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		if (StrUtil.isEmpty(name)) {
			throw new IllegalArgumentException("zlog.console.name 不能为空");
		}
		this.name = name;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(final Boolean enable) {
		if (Objects.isNull(enable)) {
			throw new IllegalArgumentException("zlog.console.enable 不能为空");
		}
		this.enable = enable;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(final String pattern) {
		if (StrUtil.isEmpty(pattern)) {
			throw new IllegalArgumentException("zlog.console.pattern 不能为空");
		}
		this.pattern = pattern;
	}

	public List<String> getExcludedClass() {
		return excludedClass;
	}

	public void setExcludedClass(final List<String> excludedClass) {
		this.excludedClass = excludedClass;
	}

	public List<String> getExcludedPackage() {
		return excludedPackage;
	}

	public void setExcludedPackage(final List<String> excludedPackage) {
		this.excludedPackage = excludedPackage;
	}

	public String getLevel() {
		return level;
	}

	@Override
	public String toString() {
		return "ZConsoleConf [name=" + name + ", enable=" + enable + ", level=" + level + ", outTypeEnum=" + outTypeEnum
				+ ", pattern=" + pattern + ", excludedClass=" + excludedClass + ", excludedPackage=" + excludedPackage
				+ "]";
	}

}
