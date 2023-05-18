package com.vo.handler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.vo.conf.ZFileConf;
import com.vo.log.enums.ZLOutTypeEnum;

import cn.hutool.core.util.StrUtil;

/**
 *
 *
 * @author zhangzhen
 * @date 2022-1-11 20:15:38
 *
 */
public class ZFileHandler extends ZLogDefaultHandler {

	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;

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

	private void write0(final String message) {
		if (this.bufferedWriter != null) {
			try {
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

		final File file = new File(path + File.separator + conf.getFileName());
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (final IOException e) {
				e.printStackTrace();
			}
		}

		try {
			this.fileWriter = new FileWriter(file, true);
			this.bufferedWriter = new BufferedWriter(this.fileWriter);
		} catch (final IOException e) {
			e.printStackTrace();
		}

		// ---------------------------------------------------------
		// ---------------------------------------------------------
		final Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
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
			}
		});

		thread.setName("zlogfile-write-thread");
		thread.start();
	}

}
