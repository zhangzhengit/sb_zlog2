package com.vo.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

import com.vo.conf.ZFileConf;
import com.vo.log.enums.ZLOutTypeEnum;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

/**
 *
 *
 * @author zhangzhen
 * @date 2022-1-11 20:15:38
 *
 */
public class ZFileHandler extends ZLogDefaultHandler {

	public static final Charset UTF_8 = Charset.forName("UTF-8");
	private File file;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;

	private final AtomicLong length = new AtomicLong(0L);

	private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

	private final ZFileConf conf;

	public ZFileHandler(final ZFileConf conf) {
		this.conf = conf;
		this.initWriteThread(conf);
	}

	@Override
	public String trace(final String message, final Object... args) {
		final String trace = super.trace(message, args);
		this.write(trace);
		return trace;
	}

	@Override
	public String debug(final String message, final Object... args) {
		final String debug = super.debug(message, args);
		this.write(debug);
		return debug;
	}

	@Override
	public String info(final String message, final Object... args) {
		final String info = super.info(message, args);
		this.write(info);
		return info;
	}

	@Override
	public String warn(final String message, final Object... args) {
		final String warn = super.warn(message, args);
		this.write(warn);
		return warn;
	}

	@Override
	public String error(final String message, final Object... args) {
		final String error = super.error(message, args);
		this.write(error);
		return error;
	}

	@Override
	public String fatal(final String message, final Object... args) {
		final String fatal = super.fatal(message, args);
		this.write(fatal);
		return fatal;
	}

	@Override
	public String getLevel() {
		return this.conf.getLevel();
	}

	@Override
	public String getPattern() {
		return this.conf.getPattern();
	}

	private void write(final String message) {
		this.queue.add(message);
	}

	// FIXME 2024年2月12日 下午9:49:17 zhanghen: 好好测试，只是windows上简单测了一下
	private synchronized void write0(final String message) {
		if (this.bufferedWriter != null) {
			try {
				final byte[] bytes = message.getBytes(UTF_8);
				this.length.set(this.length.get() + bytes.length);

				if (this.length.get() >= this.conf.getFileSize() * 1024 * 1024 ) {
					this.closeWriter();
					this.file = this.newFile(this.file);
					this.initWriter(this.file);
				}

				this.bufferedWriter.write(message);
				this.bufferedWriter.newLine();
				// XXX 每次flush？
				this.bufferedWriter.flush();

			} catch (final IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void initWriteThread(final ZFileConf conf) {
		if (!this.conf.getEnable()) {
			System.out.println("@" + ZLOutTypeEnum.FILE + " 未启用");
			return;
		}

		final String path = this.conf.getFilePath();
		final File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		this.file = this.initFile(conf, path);

		this.initWriter(this.file);

		// ---------------------------------------------------------
		// ---------------------------------------------------------
		final Thread thread = new Thread(() -> {
			System.out.println("ZFileHandler.initWriteThread(...).new Runnable() {...}.run()" + "\t"
					+ LocalDateTime.now() + "\t" + Thread.currentThread().getName());

			while (true) {
				try {
					final String message = ZFileHandler.this.queue.take();
					if (StrUtil.isNotEmpty(message)) {
						ZFileHandler.this.write0(message);
					}
				} catch (final InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		thread.setName("zlogfile-write-thread");
		thread.start();
	}

	private void closeWriter() {

		try {
			if (this.bufferedWriter != null) {
				this.bufferedWriter.flush();
				this.bufferedWriter.close();
			}
			if (this.fileWriter != null) {
				this.fileWriter.close();
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}

	}


	private void initWriter(final File file) {
		try {

			this.fileWriter = new FileWriter(file, true);
			this.bufferedWriter = new BufferedWriter(this.fileWriter);
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 初始化文件，有则看是否超过指定大小，超过则新建一个文件，没超过则使用此文件；无则直接新建一个文件
	 *
	 * @param conf
	 * @param path
	 * @return
	 *
	 */
	private File initFile(final ZFileConf conf, final String path) {
		final File file = new File(path + File.separator + conf.getFileName());
		if (!file.exists()) {
			try {
				file.createNewFile();

				this.length.set(file.length());

			} catch (final IOException e) {
				e.printStackTrace();
			}
		} else {
			final long b = file.length();
			final Long fileSize = conf.getFileSize();
			if (b >= fileSize.longValue()) {
				final File newFile = this.newFile(file);
				try {
					newFile.createNewFile();
					this.length.set(file.length());
				} catch (final IOException e) {
					e.printStackTrace();
				}
			} else {
				this.length.set(b);
			}
		}
		return file;
	}


	private  File newFile(final File file) {
		final String absolutePath = file.getAbsolutePath();
		final String now = DateUtil.format(new Date(), DatePattern.PURE_DATETIME_PATTERN);
		final File backupFile = new File(absolutePath + "_backup_" + now);
		final boolean renameTo = file.renameTo(backupFile);

//		try {
//			Files.move(file.toPath(), backupFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
//		} catch (final IOException e) {
//			e.printStackTrace();
//		}

		final File newFile =new File(absolutePath);


		this.length.set(0L);


		return newFile;
	}


	private void ensureFileSize(final File file) {
		final long length = file.length();

	}
}
