package com.vo.conf;

import java.util.List;
import java.util.Objects;

import com.vo.enums.ZLogLevelEnum;
import com.vo.log.enums.ZLOutTypeEnum;

import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 *
 * @author zhangzhen
 * @date 2022-1-11 18:01:53
 *
 */
@NoArgsConstructor
@AllArgsConstructor
public class ZFileConf {

	private String name;

	private Boolean enable;

	private String level;

	private String outTypeEnum;

	private String pattern;

	private String filePath;

	private String fileName;

	private List<String> excludedClass;

	private List<String> excludedPackage;

	public String getOutTypeEnum() {
		return this.outTypeEnum;
	}


	public void setOutTypeEnum(final String outTypeEnum) {
		if (StrUtil.isEmpty(outTypeEnum)) {
			throw new IllegalArgumentException("zlog.file.outTypeEnum 不能为空");
		}
		ZLOutTypeEnum.valueOf(outTypeEnum);
		this.outTypeEnum = outTypeEnum;
	}

	public void setLevel(final String level) {
		if (StrUtil.isEmpty(level)) {
			throw new IllegalArgumentException("zlog.file.level 不能为空");
		}
		ZLogLevelEnum.valueOf(level);
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		if (StrUtil.isEmpty(name)) {
			throw new IllegalArgumentException("zlog.file.name 不能为空");
		}
		this.name = name;
	}

	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(final Boolean enable) {
		if (Objects.isNull(enable)) {
			throw new IllegalArgumentException("zlog.file.enable 不能为空");
		}
		this.enable = enable;
	}

	public String getPattern() {
		return this.pattern;
	}

	public void setPattern(final String pattern) {
		if (Objects.isNull(pattern)) {
			throw new IllegalArgumentException("zlog.file.pattern 不能为空");
		}
		this.pattern = pattern;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(final String filePath) {
		if (Objects.isNull(filePath)) {
			throw new IllegalArgumentException("zlog.file.filePath 不能为空");
		}
		this.filePath = filePath;
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


	public String getFileName() {
		return this.fileName;
	}


	public void setFileName(final String fileName) {
		if (Objects.isNull(fileName)) {
			throw new IllegalArgumentException("zlog.file.fileName 不能为空");
		}
		this.fileName = fileName;
	}

}
