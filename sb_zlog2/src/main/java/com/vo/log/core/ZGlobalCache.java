package com.vo.core;

import com.vo.core.ZThreadMap.ZGlobalCacheTypeEnum;

/**
 *
 * 全局缓存，方法操作只对当前线程有效
 * 
 * @author zhangzhen
 * @date 2020-12-18
 * 
 */
public class ZGlobalCache {

	private final static ZThreadMap<ZGlobalCacheTypeEnum, Object> cm = new ZThreadMap<>();

	public static Object get(final ZGlobalCacheTypeEnum key) {
		final Object v = cm.get(key);
		return v;
	}

	public static void set(final ZGlobalCacheTypeEnum key, final Object v) {
		cm.set(key, v);
	}

	public static void remove(final ZGlobalCacheTypeEnum key) {
		cm.remove(key);
	}

}
