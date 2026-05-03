package com.vo.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.vo.handler.IZLogHandler;

/**
 *
 *
 * @author zhangzhen
 * @date 2020-12-21 10:41:53
 *
 */
class ZLogHanderCache {

	private static final List<IZLogHandler> LIST = new ArrayList<>(2);

	private static int aC = 0;

	static boolean anyoneIsAvailable() {
		return aC > 0;
	}

	static List<IZLogHandler> getAllHandler() {
		return LIST;
	}

	static void add(final IZLogHandler zLogHandler) {
		LIST.add(zLogHandler);
		synchronized (ZLogHanderCache.class) {
			aC++;
		}
	}

}
