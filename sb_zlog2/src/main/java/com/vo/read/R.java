package com.vo.read;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import cn.hutool.core.util.StrUtil;

/**
 * 读取zlog的配置文件
 *
 * @author zhangzhen
 * @date 2022-1-11 19:10:34
 *
 */
public class R {

	public static final String ZLOG_PATH = "src/main/resources/zlog.properties";
	public static final String ZLOG_PATH_1 = "src/main/resources/config/zlog.properties";
	public static final String ZLOG_PATH_2 = "config/zlog.properties";
	public static final String ZLOG_PATH_3 = "zlog.properties";

	public static boolean readBoolean(final String key) {
		final boolean v = propertiesConfiguration.getBoolean(key);
		return v;
	}

	public static Integer readInteger(final String key) {
		final String v = propertiesConfiguration.getString(key);
		if (StrUtil.isEmpty(v)) {
			return null;
		}

		return Integer.parseInt(v);
	}

	public static Long readLong(final String key) {
		final String v = propertiesConfiguration.getString(key);
		if (StrUtil.isEmpty(v)) {
			return null;
		}

		return Long.parseLong(v);
	}

	public static String readString(final String key) {
		final String v = propertiesConfiguration.getString(key);
		return v;
	}

	private static PropertiesConfiguration propertiesConfiguration = null;

	static {
		try {
			propertiesConfiguration = new PropertiesConfiguration(ZLOG_PATH);
		} catch (final ConfigurationException e) {
			try {
				propertiesConfiguration = new PropertiesConfiguration(ZLOG_PATH_1);
			} catch (final ConfigurationException e1) {
				try {
					propertiesConfiguration = new PropertiesConfiguration(ZLOG_PATH_2);
				} catch (final ConfigurationException e2) {
					try {
						propertiesConfiguration = new PropertiesConfiguration(ZLOG_PATH_3);
					} catch (final ConfigurationException e3) {
						e3.printStackTrace();
					}
				}
			}
		}
	}
}
