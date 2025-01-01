package com.vo.read;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

/**
 * 读取zlog的配置文件
 *
 * @author zhangzhen
 * @date 2022-1-11 19:10:34
 *
 */
public class R {

	private static final Charset UTF8 = StandardCharsets.UTF_8;
	public static final String CONFG_ZLOG2 = "config/zlog.properties";
	public static final String ZLOG2 = "zlog.properties";

	public static boolean readBoolean(final String key) {
		final String property = properties.getProperty(key);
		return Boolean.parseBoolean(property);
	}

	public static Integer readInteger(final String key) {
		final String v = properties.getProperty(key);
		if (v == null) {
			return null;
		}
		final int i = Integer.parseInt(v);
		return i;
	}

	public static Long readLong(final String key) {
		final String v = properties.getProperty(key);
		if (v == null) {
			return null;
		}

		return Long.parseLong(v);
	}

	public static String readString(final String key) {
		return properties.getProperty(key);
	}

	private static Properties properties;

	static {

		Properties p1 = loadDirConfig(File.separator + CONFG_ZLOG2);
		if (p1 == null) {
			p1 = loadDirConfig(File.separator + ZLOG2);
			if (p1 == null) {
				p1 = loadPResources("/" + CONFG_ZLOG2);
				if (p1 == null) {
					p1 = loadPResources("/" + ZLOG2);
				}
			}
		}

		if (p1 == null) {
			System.out.println("ERROR:zlog2启动失败,zlog.properties配置文件不存在,请编写此配置文件");
			System.exit(0);
		}

		properties = p1;

	}

	private static Properties loadPResources(final String path) {
		final InputStream inputStream = R.class.getResourceAsStream(path);
		if (inputStream == null) {
			return null;
		}

		final Properties p2 = new Properties();
		InputStreamReader reader = null;
		try {
			reader = new InputStreamReader(inputStream, UTF8);
			p2.load(reader);
		} catch (final IOException e1) {
			e1.printStackTrace();
		} finally {
			try {
				inputStream.close();
				if (reader != null) {
					reader.close();
				}
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}

		return p2;
	}

	private static Properties loadDirConfig(final String path) {
		final String userDir = getUseDir();
		final File file1 = new File(userDir + path);
		final Properties properties = new Properties();
		try (FileInputStream in = new FileInputStream(file1);
				final InputStreamReader inputStreamReader = new InputStreamReader(in, UTF8);) {
			properties.load(inputStreamReader);
		} catch (final IOException e) {
			return null;
		}

		return properties;
	}

	private static String getUseDir() {
		return System.getProperty("user.dir");
	}
}
