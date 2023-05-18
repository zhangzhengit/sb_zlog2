package com.vo.log.enums;

import com.vo.core.ZGlobalCache;
import com.vo.core.ZThreadMap.ZGlobalCacheTypeEnum;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * pattern枚举
 *
 * @author zhangzhen
 * @date 2020-12-17
 *
 */
@Getter
@AllArgsConstructor
public enum ZLPatternEnum {

	MESSAGE("%MESSAGE", "要输出的日志内容,{}占位符可选") {
		@Override
		StringBuilder pI(final StringBuilder messageBuilder) {
			return messageBuilder;
		}
	},

	CLASS_NAME("%CLASS_NAME", "输出当前完整的类名") {
		@Override
		StringBuilder pI(final StringBuilder messageBuilder) {
			int fromIndex = 0;
			int currentMI = 0;
			while (true) {
				currentMI = messageBuilder.indexOf(this.getPattern(), fromIndex);
				if (currentMI <= -1) {
					break;
				}
				final String className = String.valueOf(ZGlobalCache.get(ZGlobalCacheTypeEnum.LOG_XXX_CLASS_NAME));
				messageBuilder.replace(currentMI, currentMI + this.getPattern().length(), className);
				fromIndex = currentMI + Math.min(className.length(), this.getPattern().length());
			}
			return messageBuilder;
		}
	},

	FILE_NAME("%FILE_NAME", "输出当前的文件名") {
		@Override
		StringBuilder pI(final StringBuilder messageBuilder) {
			int fromIndex = 0;
			int currentMI = 0;
			while (true) {
				currentMI = messageBuilder.indexOf(this.getPattern(), fromIndex);
				if (currentMI <= -1) {
					break;
				}
				final String fileName = String.valueOf(ZGlobalCache.get(ZGlobalCacheTypeEnum.LOG_XXX_FILE_NAME));
				messageBuilder.replace(currentMI, currentMI + this.getPattern().length(), fileName);
				fromIndex = currentMI + Math.min(fileName.length(), this.getPattern().length());
			}
			return messageBuilder;
		}
	},

	DATE_TIME("%DATE_TIME", "输出log.xxx调用点的日期时间") {
		@Override
		StringBuilder pI(final StringBuilder messageBuilder) {

			int fromIndex = 0;
			int currentMI = 0;
			while (true) {
				currentMI = messageBuilder.indexOf(this.getPattern(), fromIndex);
				if (currentMI <= -1) {
					break;
				}
				final Object globalCache_DATE_TIME = ZGlobalCache.get(ZGlobalCacheTypeEnum.DATE_TIME);
				final String rv = String.valueOf(globalCache_DATE_TIME);
				messageBuilder.replace(currentMI, currentMI + this.getPattern().length(), rv);
				fromIndex = currentMI + Math.min(rv.length(), this.getPattern().length());
			}
			return messageBuilder;
		}
	},

	DATE("%DATE", "输出log.xxx调用点的日期") {
		@Override
		StringBuilder pI(final StringBuilder messageBuilder) {
			int fromIndex = 0;
			int currentMI = 0;
			while (true) {
				currentMI = messageBuilder.indexOf(this.getPattern(), fromIndex);
				if (currentMI <= -1) {
					break;
				}
				final Object globalCache_DATE = ZGlobalCache.get(ZGlobalCacheTypeEnum.DATE);
				final String rv = String.valueOf(globalCache_DATE);
				messageBuilder.replace(currentMI, currentMI + this.getPattern().length(), rv);
				fromIndex = currentMI + Math.min(rv.length(), this.getPattern().length());
			}
			return messageBuilder;
		}
	},

	LEVEL("%LEVEL", "输出log.xxx的这个xxx的方法名对应级别,如info、debug、error等等") {
		@Override
		StringBuilder pI(final StringBuilder messageBuilder) {
			int fromIndex = 0;
			int currentMI = 0;
			while (true) {
				currentMI = messageBuilder.indexOf(this.getPattern(), fromIndex);
				if (currentMI <= -1) {
					break;
				}
				final String name = String.valueOf(ZGlobalCache.get(ZGlobalCacheTypeEnum.LOG_XXX_LEVEL));
				messageBuilder.replace(currentMI, currentMI + this.getPattern().length(), name);
				fromIndex = currentMI + Math.min(name.length(), this.getPattern().length());
			}
			return messageBuilder;
		}
	},

	TIME("%TIME", "输出log.xxx调用点的时间") {
		@Override
		StringBuilder pI(final StringBuilder messageBuilder) {
			int fromIndex = 0;
			int currentMI = 0;
			while (true) {
				currentMI = messageBuilder.indexOf(this.getPattern(), fromIndex);
				if (currentMI <= -1) {
					break;
				}
				final Object globalCache_TIME = ZGlobalCache.get(ZGlobalCacheTypeEnum.TIME);
				final String rv = String.valueOf(globalCache_TIME);
				messageBuilder.replace(currentMI, currentMI + this.getPattern().length(), rv);
				fromIndex = currentMI + Math.min(rv.length(), this.getPattern().length());
			}
			return messageBuilder;
		}
	},

	THREAD("%THREAD", "输出log.xxx调用点的线程名") {
		@Override
		StringBuilder pI(final StringBuilder messageBuilder) {
			int fromIndex = 0;
			int currentMI = 0;
			while (true) {
				currentMI = messageBuilder.indexOf(this.getPattern(), fromIndex);
				if (currentMI <= -1) {
					break;
				}
				final String name = Thread.currentThread().getName();
				messageBuilder.replace(currentMI, currentMI + this.getPattern().length(), name);
				fromIndex = currentMI + Math.min(name.length(), this.getPattern().length());
			}
			return messageBuilder;
		}
	},

	METHOD("%METHOD", "输出log.xxx调用点所在的方法名") {
		@Override
		StringBuilder pI(final StringBuilder messageBuilder) {
			int fromIndex = 0;
			int currentMI = 0;
			while (true) {
				currentMI = messageBuilder.indexOf(this.getPattern(), fromIndex);
				if (currentMI <= -1) {
					break;
				}
				final String methodName = String.valueOf(ZGlobalCache.get(ZGlobalCacheTypeEnum.LOG_XXX_METHOD_NAME));
				messageBuilder.replace(currentMI, currentMI + this.getPattern().length(), methodName);
				fromIndex = currentMI + Math.min(methodName.length(), this.getPattern().length());
			}
			return messageBuilder;
		}
	},

	LINE_NUMBER("%LINE_NUMBER", "输出log.xxx所在的行数") {
		@Override
		StringBuilder pI(final StringBuilder messageBuilder) {
			int fromIndex = 0;
			int currentMI = 0;
			while (true) {
				currentMI = messageBuilder.indexOf(this.getPattern(), fromIndex);
				if (currentMI <= -1) {
					break;
				}
				final Object lineNumber = ZGlobalCache.get(ZGlobalCacheTypeEnum.LOG_XXX_LINE_NUMBER);
				messageBuilder.replace(currentMI, currentMI + this.getPattern().length(), String.valueOf(lineNumber));
				fromIndex = currentMI + String.valueOf(lineNumber).length();
			}
			return messageBuilder;
		}
	};

	/**
	 * pattern占位符
	 */
	private final String pattern;
	private final String remark;

	/**
	 * 每个枚举值重写此方法去处理自己的内容
	 *
	 * @param messageBuilder
	 * @return
	 */
	abstract StringBuilder pI(StringBuilder messageBuilder);

	/**
	 * 把pattern中的占位符替换为实际参数，message参数只是用来替换 ZLPatternEnum.MESSAGE。
	 *
	 * @param pattern 配置的handler的pattern，如：ZLogConfiguration.DEFAULT_PATTERN
	 * @param message 已替换过日志内容占位符的日志，如：error,name=zhangsan
	 * @return
	 */
	public static StringBuilder parse(final String pattern, final String message) {
		StringBuilder pbr = new StringBuilder();
		final StringBuilder patternBuilder = new StringBuilder(pattern);
		for (final ZLPatternEnum e : values()) {
			pbr = e.pI(patternBuilder);
		}

		int currentMI;
		int fromIndex = 0;
		while (true) {
			currentMI = pbr.indexOf(MESSAGE.getPattern(), fromIndex);
			if (currentMI < 0) {
				break;
			}
			pbr.replace(currentMI, currentMI + MESSAGE.getPattern().length(), message);
			fromIndex = currentMI + MESSAGE.getPattern().length();
		}
		return pbr;
	}

}
