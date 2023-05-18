package com.vo.conf;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
public class ZLogCenterConf {

	public static final Boolean DEFAULT_ENABLE = true;

	/**
	 * server 秘钥
	 */
	private String secretKey;

	private Integer appId;
	private String appName;
	private String name;

	private Boolean enable;

	private String level;

	private String outTypeEnum;

	private String pattern;

	private String logCenterURL;


	private List<String> excludedClass;

	private List<String> excludedPackage;

	public String getOutTypeEnum() {
		return this.outTypeEnum;
	}

	public void setOutTypeEnum(final String outTypeEnum) {
		if (StrUtil.isEmpty(outTypeEnum)) {
			throw new IllegalArgumentException("zlog.logcenter.outTypeEnum 不能为空");
		}
		ZLOutTypeEnum.valueOf(outTypeEnum);
		this.outTypeEnum = outTypeEnum;
	}

	public void setLevel(final String level) {
		if (StrUtil.isEmpty(level)) {
			throw new IllegalArgumentException("zlog.logcenter.level 不能为空");
		}
		ZLogLevelEnum.valueByNameLowerCase(level);
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		if (StrUtil.isEmpty(name)) {
			throw new IllegalArgumentException("zlog.logcenter.name 不能为空");
		}
		this.name = name;
	}

	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(final Boolean enable) {
		if (Objects.isNull(enable)) {
			throw new IllegalArgumentException("zlog.logcenter.enable 不能为空");
		}
		this.enable = enable;
	}

	public String getPattern() {
		return this.pattern;
	}

	public void setPattern(final String pattern) {
		if (StrUtil.isEmpty(pattern)) {
			throw new IllegalArgumentException("zlog.logcenter.pattern 不能为空");
		}
		this.pattern = pattern;
	}

	public List<String> getExcludedClass() {
		return this.excludedClass;
	}

	public void setExcludedClass(final List<String> excludedClass) {
		this.excludedClass = excludedClass;
	}

	public List<String> getExcludedPackage() {
		return this.excludedPackage;
	}

	public void setExcludedPackage(final List<String> excludedPackage) {
		this.excludedPackage = excludedPackage;
	}

	public String getLevel() {
		return this.level;
	}


	@Override
	public String toString() {
		return "ZLogCenterConf [name=" + this.name + ", enable=" + this.enable + ", level=" + this.level
				+ ", outTypeEnum=" + this.outTypeEnum + ", pattern=" + this.pattern + ", excludedClass="
				+ this.excludedClass + ", excludedPackage=" + this.excludedPackage + "]";
	}



	public String getLogCenterURL() {
		return this.logCenterURL;
	}


	public void setLogCenterURL(final String logCenterURL) {
		if (StrUtil.isEmpty(logCenterURL)) {
			throw new IllegalArgumentException("zlog.logcenter.logCenterURL 不能为空");
		}
		this.logCenterURL = logCenterURL;
	}

	public Integer getAppId() {
		return this.appId;
	}

	public void setAppId(final Integer appId) {
		if (Objects.isNull(appId)) {
			throw new IllegalArgumentException("zlog.logcenter.appId 不能为空");
		}

		this.appId = appId;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setAppName(final String appName) {
		if (StrUtil.isEmpty(appName)) {
			throw new IllegalArgumentException("zlog.logcenter.appName 不能为空");
		}
		this.appName = appName;
	}

	public String getSecretKey() {
		return this.secretKey;
	}

	public void setSecretKey(final String secretKey) {
		if (StrUtil.isEmpty(secretKey)) {
			throw new IllegalArgumentException("zlog.logcenter.secretKey 不能为空");
		}
		this.secretKey = secretKey;
	}

}
