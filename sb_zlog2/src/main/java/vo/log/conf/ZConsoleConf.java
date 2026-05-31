package vo.log.conf;

import java.util.List;
import java.util.Objects;

import vo.log.common.STU;
import vo.log.enums.ZLogLevelEnum;

/**
 *
 *
 * @author zhangzhen
 * @date 2022-1-11 18:32:01
 *
 */
public class ZConsoleConf {

	public static final Boolean DEFAULT_ENABLE = true;

	private String name;

	private Boolean enable;

	private String level;

	private String pattern;

	private List<String> excludedClass;

	private List<String> excludedPackage;

	public void setLevel(final String level) {
		if (STU.isEmpty(level)) {
			throw new IllegalArgumentException("zlog.console.level 不能为空");
		}
		ZLogLevelEnum.valueByNameLowerCase(level);
		this.level = level;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		if (STU.isEmpty(name)) {
			throw new IllegalArgumentException("zlog.console.name 不能为空");
		}
		this.name = name;
	}

	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(final Boolean enable) {
		if (Objects.isNull(enable)) {
			throw new IllegalArgumentException("zlog.console.enable 不能为空");
		}
		this.enable = enable;
	}

	public String getPattern() {
		return this.pattern;
	}

	public void setPattern(final String pattern) {
		if (STU.isEmpty(pattern)) {
			throw new IllegalArgumentException("zlog.console.pattern 不能为空");
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
		return "ZConsoleConf [name=" + this.name + ", enable=" + this.enable + ", level=" + this.level + ", pattern=" + this.pattern + ", excludedClass=" + this.excludedClass + ", excludedPackage=" + this.excludedPackage
				+ "]";
	}

}
