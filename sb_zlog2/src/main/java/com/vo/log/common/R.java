package com.vo.log.common;

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
			// 2025年1月29日 上午12:03:34 zhangzhen : 不要提示了，而是使用内置的一个默认配置
			// System.out.println("ERROR:zlog2启动失败,zlog.properties配置文件不存在,请编写此配置文件");
			// System.exit(0);

			final Properties pd = new Properties();
			pd.setProperty("zlog.console.name", "CONSOLE");
			pd.setProperty("zlog.console.enable", "true");
			pd.setProperty("zlog.console.level", "TRACE");
			pd.setProperty("zlog.console.pattern",
					"[%DATE_TIME]-[%LEVEL]-[%THREAD]-[%CLASS_NAME::%METHOD@%LINE_NUMBER] : [%MESSAGE]");

			pd.setProperty("zlog.file.name", "FILE");
			pd.setProperty("zlog.file.enable", "true");
			pd.setProperty("zlog.file.level", "TRACE");
			pd.setProperty("zlog.file.pattern",
					"[%DATE_TIME]-[%LEVEL]-[%THREAD]-[%CLASS_NAME::%METHOD@%LINE_NUMBER] : [%MESSAGE]");
			// FIXME 2025年9月2日 上午1:37:42 zhangzhen: getAppName和gFIlePath不对，
			// 记得改：当前时取得目录名称，而非jar名称
			pd.setProperty("zlog.file.filePath", gFilePath());
			pd.setProperty("zlog.file.fileName", getAppName() + ".log");
			pd.setProperty("zlog.file.fileSize", "100");
	
			properties = pd;

		} else {
			properties = p1;
		}

	}

	private static String gFilePath() {
		final String userDir = System.getProperty("user.dir");

		final String logPath = userDir + File.separator + "log";
		final File dir = new File(logPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		return dir.getAbsolutePath();
	}
	
	private static String getAppName() {
		final String userDir = System.getProperty("user.dir");
		final String projectName = userDir.substring(userDir.lastIndexOf(File.separator) + 1);
		return projectName;
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
