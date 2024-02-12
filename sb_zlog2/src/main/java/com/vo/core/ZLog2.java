package com.vo.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import com.vo.conf.ZConsoleConf;
import com.vo.conf.ZDBConf;
import com.vo.conf.ZFileConf;
import com.vo.conf.ZLogCenterConf;
import com.vo.core.ZThreadMap.ZGlobalCacheTypeEnum;
import com.vo.enums.ZLogLevelEnum;
import com.vo.handler.IZLogHandler;
import com.vo.handler.ZConsoleHandler;
import com.vo.handler.ZDBHandler;
import com.vo.handler.ZFileHandler;
import com.vo.handler.ZLogCenterHandler;
import com.vo.read.R;

import cn.hutool.core.collection.CollUtil;

/**
 * 输出日志
 *
 * @Autowired ZLog log; 然后log.xxx输出日志
 *
 * @author zhangzhen
 * @date 2020-12-17
 *
 */
public final class ZLog2 {

	private static final  AtomicBoolean G = new AtomicBoolean(false);

	private static final ZLog2 ZLOG2 = new ZLog2();

	public static ZLog2 getInstance() {
		return ZLog2.ZLOG2;
	}

	static {
		if (!ZLog2.G.get()) {
			initConsoleHandler();
			initFileHandler();
			initlogCenterHandler();

			ZLog2.G.set(true);
		}
	}

	private static void initDBHandler() {
		final boolean enable = R.readBoolean("zlog.db.enable");
		if (!enable) {
			return;
		}

		final ZDBConf dbConf = new ZDBConf();
		dbConf.setName(R.readString("zlog.db.name"));
		dbConf.setEnable(enable);
		dbConf.setLevel(R.readString("zlog.db.level"));
		dbConf.setPattern(R.readString("zlog.db.pattern"));
		final ZDBHandler dbHandler = new ZDBHandler(dbConf);
		ZLogHanderCache.add(dbHandler);
	}

	private static void initConsoleHandler() {
		final boolean enable = R.readBoolean("zlog.console.enable");
		if (!enable) {
			return;
		}

		final ZConsoleConf consoleConf = new ZConsoleConf();
		consoleConf.setEnable(enable);
		consoleConf.setName(R.readString("zlog.console.name"));
		consoleConf.setLevel(R.readString("zlog.console.level"));
		consoleConf.setPattern(R.readString("zlog.console.pattern"));
		final ZConsoleHandler consoleHandler = new ZConsoleHandler(consoleConf);
		ZLogHanderCache.add(consoleHandler);
	}

	private static void initFileHandler() {

		final boolean enable = R.readBoolean("zlog.file.enable");
		if (!enable) {
			return;
		}

		final ZFileConf fileConf = new ZFileConf();
		fileConf.setEnable(enable);
		fileConf.setName(R.readString("zlog.file.name"));
		fileConf.setOutTypeEnum(R.readString("zlog.file.outTypeEnum"));
		fileConf.setLevel(R.readString("zlog.file.level"));
		fileConf.setPattern(R.readString("zlog.file.pattern"));
		fileConf.setFilePath(R.readString("zlog.file.filePath"));
		fileConf.setFileName(R.readString("zlog.file.fileName"));
		fileConf.setFileSize(R.readLong("zlog.file.fileSize"));

		final ZFileHandler fileHandler = new ZFileHandler(fileConf);
		ZLogHanderCache.add(fileHandler);
	}

	private static void initlogCenterHandler() {

		final boolean enable = R.readBoolean("zlog.logcenter.enable");
		if (!enable) {
			return;
		}

		final ZLogCenterConf centerConf = new ZLogCenterConf();
		centerConf.setSecretKey(R.readString("zlog.logcenter.secretKey"));
		centerConf.setAppId(R.readInteger("zlog.logcenter.appId"));
		centerConf.setAppName(R.readString("zlog.logcenter.appName"));
		centerConf.setName(R.readString("zlog.logcenter.name"));
		centerConf.setEnable(enable);
		centerConf.setOutTypeEnum(R.readString("zlog.logcenter.outTypeEnum"));
		centerConf.setLevel(R.readString("zlog.logcenter.level"));
		centerConf.setPattern(R.readString("zlog.logcenter.pattern"));
		centerConf.setLogCenterURL(R.readString("zlog.logcenter.logCenterUrl"));

		final ZLogCenterHandler logCenterHandler = new ZLogCenterHandler(centerConf);
		ZLogHanderCache.add(logCenterHandler);
	}

	public final void trace(final String message, final Object... args) {
		this.init_DATE_TIME();
		this.init_XXX(ZLogLevelEnum.TRACE);

		final Collection<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (final IZLogHandler h : allHandler) {
			h.trace(message, args);
		}
	}

	public final void debug(final String message, final Object... args) {
		this.init_DATE_TIME();
		this.init_XXX(ZLogLevelEnum.DEBUG);

		final Collection<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (final IZLogHandler h : allHandler) {
			h.debug(message, args);
		}
	}

	public final void info(final String message, final Object... args) {
		this.init_DATE_TIME();
		this.init_XXX(ZLogLevelEnum.INFO);

		final Collection<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (final IZLogHandler h : allHandler) {
			h.info(message, args);
		}
	}

	public final void warn(final String message, final Object... args) {
		this.init_DATE_TIME();
		this.init_XXX(ZLogLevelEnum.WARN);

		final Collection<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (final IZLogHandler h : allHandler) {
			h.warn(message, args);
		}
	}

	public final void error(final String message, final Object... args) {
		this.init_DATE_TIME();
		this.init_XXX(ZLogLevelEnum.ERROR);


		final Collection<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (final IZLogHandler h : allHandler) {
			h.error(message, args);
		}
	}

	public final void fatal(final String message, final Object... args) {
		this.init_DATE_TIME();
		this.init_XXX(ZLogLevelEnum.FATAL);

		final Collection<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (final IZLogHandler h : allHandler) {
			h.fatal(message, args);
		}

	}

	private boolean anyZLogEnable() {
		final Collection<IZLogHandler> ah = ZLogHanderCache.getAllHandler();
		if (CollUtil.isEmpty(ah)) {
			return false;
		}

		for (final IZLogHandler a : ah) {
			if (a.isEnable()) {
				return true;
			}
		}

		return false;

	}

	private void init_XXX(final ZLogLevelEnum levelEnum) {
		ZGlobalCache.set(ZGlobalCacheTypeEnum.LOG_XXX_LEVEL, levelEnum.name());

		final StackTraceElement ste = getSTE_FOR_log_xxx();
		ZGlobalCache.set(ZGlobalCacheTypeEnum.LOG_XXX_METHOD_NAME, ste.getMethodName());
		ZGlobalCache.set(ZGlobalCacheTypeEnum.LOG_XXX_LINE_NUMBER, ste.getLineNumber());
		ZGlobalCache.set(ZGlobalCacheTypeEnum.LOG_XXX_CLASS_NAME, ste.getClassName());
		ZGlobalCache.set(ZGlobalCacheTypeEnum.LOG_XXX_FILE_NAME, ste.getFileName());
	}

	private static StackTraceElement getSTE_FOR_log_xxx() {
		// XXX d写死
		final int d = 4;
		final StackTraceElement[] stA = Thread.currentThread().getStackTrace();
		return stA[d];
	}

	private void init_DATE_TIME() {
		if (this.anyZLogEnable()) {
			ZGlobalCache.set(ZGlobalCacheTypeEnum.TIME, LocalTime.now());
			ZGlobalCache.set(ZGlobalCacheTypeEnum.DATE, LocalDate.now());
			ZGlobalCache.set(ZGlobalCacheTypeEnum.DATE_TIME, LocalDateTime.now());
		}
	}

}
