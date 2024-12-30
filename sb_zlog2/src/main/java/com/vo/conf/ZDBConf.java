package com.vo.conf;

import java.util.List;

/**
 *
 *
 * @author zhangzhen
 * @date 2022年6月30日
 *
 */
public class ZDBConf {

	private String name;

	private Boolean enable;

	private String level;

	private String outTypeEnum;

	private String pattern;

	private String url;

	private String username;

	private String password;

	private List<String> excludedClass;

	private List<String> excludedPackage;


	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(final Boolean enable) {
		this.enable = enable;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(final String level) {
		this.level = level;
	}

	public String getOutTypeEnum() {
		return this.outTypeEnum;
	}

	public void setOutTypeEnum(final String outTypeEnum) {
		this.outTypeEnum = outTypeEnum;
	}

	public String getPattern() {
		return this.pattern;
	}

	public void setPattern(final String pattern) {
		this.pattern = pattern;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(final String password) {
		this.password = password;
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

	public ZDBConf(final String name, final Boolean enable, final String level, final String outTypeEnum, final String pattern, final String url,
			final String username, final String password, final List<String> excludedClass, final List<String> excludedPackage) {
		this.name = name;
		this.enable = enable;
		this.level = level;
		this.outTypeEnum = outTypeEnum;
		this.pattern = pattern;
		this.url = url;
		this.username = username;
		this.password = password;
		this.excludedClass = excludedClass;
		this.excludedPackage = excludedPackage;
	}

	public ZDBConf() {
	}

}
