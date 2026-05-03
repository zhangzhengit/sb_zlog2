package com.vo.handler;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

/**
 * 压缩备份的日志文件
 *
 * @author zhangzhen
 * @date 2025年1月3日 下午7:31:45
 *
 */
public class Compression {

	private static final String THREAD_NAME = "zlog2-compression-Thread";
	private static final String GZ = ".gz";
	private static final int BUFFER_SIZE = 1024 * 100;

	/**
	 * 把重命名后的备份日志文件压缩为.zip文件，并且删除原备份文件
	 *
	 * @param backFile
	 */
	public static void compression(final File backFile) {
		if (backFile == null) {
			return;
		}

		final Thread thread = new Thread(() -> {
			action(backFile);
		});

		thread.setName(THREAD_NAME);
		thread.start();
	}

	private static void action(final File backFile) {

		final File zipFile = new File(backFile.getAbsolutePath() + GZ);
		try {
			final BufferedInputStream bufferedInputStream = new BufferedInputStream(
					new FileInputStream(backFile));
			final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
					new FileOutputStream(zipFile));
			final byte[] b = new byte[BUFFER_SIZE];
			while (true) {
				final int read = bufferedInputStream.read(b);
				if (read <= 0) {
					break;
				}

				if (read >= BUFFER_SIZE) {
					final byte[] compress1 = ZGzip.compress(b);
					bufferedOutputStream.write(compress1);
				} else {
					final byte[] copyOfRange = Arrays.copyOfRange(b, 0, read);
					final byte[] compress2 = ZGzip.compress(copyOfRange);
					bufferedOutputStream.write(compress2);
				}

			}
			bufferedOutputStream.flush();
			bufferedOutputStream.close();

			bufferedInputStream.close();

			// 最后删除原文件
			backFile.delete();
			backFile.deleteOnExit();

		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
