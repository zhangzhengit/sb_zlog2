package com.vo.core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

import com.vo.conf.ZConsoleConf;
import com.vo.conf.ZFileConf;
import com.vo.core.ZThreadMap.ZGlobalCacheTypeEnum;
import com.vo.enums.ZLogLevelEnum;
import com.vo.handler.IZLogHandler;
import com.vo.handler.ZConsoleHandler;
import com.vo.handler.ZFileHandler;
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
			ZLog2.G.set(true);
		}
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

	public final void trace(final String message, final Object... args) {
		if (!ZLogHanderCache.anyoneIsAvailable()) {
			return;
		}

		ZLog2.init_DATE_TIME();
		ZLog2.init_XXX(ZLogLevelEnum.TRACE);

		final List<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (int i = 0; i < allHandler.size(); i++) {
			allHandler.get(i).trace(message, args);
		}
	}

	public final void debug(final String message, final Object... args) {
		if (!ZLogHanderCache.anyoneIsAvailable()) {
			return;
		}

		ZLog2.init_DATE_TIME();
		ZLog2.init_XXX(ZLogLevelEnum.DEBUG);

		final List<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (int i = 0; i < allHandler.size(); i++) {
			allHandler.get(i).debug(message, args);
		}
	}

	public final void info(final String message, final Object... args) {
		if (!ZLogHanderCache.anyoneIsAvailable()) {
			return;
		}

		ZLog2.init_DATE_TIME();
		ZLog2.init_XXX(ZLogLevelEnum.INFO);

		final List<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (int i = 0; i < allHandler.size(); i++) {
			allHandler.get(i).info(message, args);
		}
	}

	public final void warn(final String message, final Object... args) {
		if (!ZLogHanderCache.anyoneIsAvailable()) {
			return;
		}

		ZLog2.init_DATE_TIME();
		ZLog2.init_XXX(ZLogLevelEnum.WARN);

		final List<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (int i = 0; i < allHandler.size(); i++) {
			allHandler.get(i).warn(message, args);
		}
	}

	public final void error(final String message, final Object... args) {
		if (!ZLogHanderCache.anyoneIsAvailable()) {
			return;
		}

		ZLog2.init_DATE_TIME();
		ZLog2.init_XXX(ZLogLevelEnum.ERROR);

		final List<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (int i = 0; i < allHandler.size(); i++) {
			allHandler.get(i).error(message, args);
		}
	}

	public final void fatal(final String message, final Object... args) {
		if (!ZLogHanderCache.anyoneIsAvailable()) {
			return;
		}

		ZLog2.init_DATE_TIME();
		ZLog2.init_XXX(ZLogLevelEnum.FATAL);

		final List<IZLogHandler> allHandler = ZLogHanderCache.getAllHandler();
		for (int i = 0; i < allHandler.size(); i++) {
			allHandler.get(i).fatal(message, args);
		}
	}

	private static boolean anyZLogEnable() {
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

	private static void init_XXX(final ZLogLevelEnum levelEnum) {
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

	private static void init_DATE_TIME() {
		if (ZLog2.anyZLogEnable()) {
			ZGlobalCache.set(ZGlobalCacheTypeEnum.TIME, LocalTime.now());
			ZGlobalCache.set(ZGlobalCacheTypeEnum.DATE, LocalDate.now());
			ZGlobalCache.set(ZGlobalCacheTypeEnum.DATE_TIME, LocalDateTime.now());
		}
	}
}
