package com.vo.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
public class ZLogHanderCache {

	private static final ConcurrentMap<String, IZLogHandler> map = new ConcurrentHashMap<>();

	public static Collection<IZLogHandler> getAllHandler() {
		final Collection<IZLogHandler> values = map.values();
		return values;
	}

	public static void add(final IZLogHandler zLogHandler) {
		add(zLogHandler.getClass().getName(), zLogHandler);
	}

	public static void add(final String handlerClassName, final IZLogHandler zLogHandler) {
		map.put(handlerClassName, zLogHandler);
	}

}
