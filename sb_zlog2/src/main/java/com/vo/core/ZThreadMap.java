package com.vo.core;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import cn.hutool.core.collection.CollUtil;

/**
 * 只对当前线程有效的 K-V 操作类
 * 
 * @author zhangzhen
 * @date 2020-12-18
 * 
 */
public class ZThreadMap<KT, VT> {

	private final ConcurrentMap<Thread, Map<KT, VT>> map = new ConcurrentHashMap<>();

	public void set(final KT key, final VT v) {
		final Thread thread = Thread.currentThread();
		final Map<KT, VT> vm = map.get(thread);
		if (CollUtil.isNotEmpty(vm)) {
			vm.put(key, v);
			return;
		}

		final Map<KT, VT> nvm = new HashMap<>();
		nvm.put(key, v);
		map.put(thread, nvm);

	}

	public VT get(final KT key) {
		final Map<KT, VT> vm = currentThread_VM();
		if (CollUtil.isEmpty(vm)) {
			return null;
		}
		final VT vt = vm.get(key);
		return vt;
	}

	public void remove(final KT key) {
		final Map<KT, VT> vm = currentThread_VM();
		if (CollUtil.isEmpty(vm)) {
			return;
		}
		vm.remove(key);
	}

	public void clear() {
		final Map<KT, VT> vm = currentThread_VM();
		if (CollUtil.isNotEmpty(vm)) {
			vm.clear();
		}

	}

	public Set<Map.Entry<KT, VT>> entrySet() {
		final Map<KT, VT> vm = currentThread_VM();
		if (CollUtil.isEmpty(vm)) {
			return Collections.emptySet();
		}

		return vm.entrySet();
	}

	public int size() {
		final Map<KT, VT> vm = currentThread_VM();
		return CollUtil.isEmpty(vm) ? 0 : vm.size();
	}

	private Map<KT, VT> currentThread_VM() {
		final Thread currentThread = Thread.currentThread();
		final Map<KT, VT> vm = map.get(currentThread);
		return vm;
	}

	/**
	 *	操作类型定义
	 * 	
	 * @author zhangzhen
	 * @date 2020-12-18
	 * 
	 */
	public enum ZGlobalCacheTypeEnum{
		
		TIME, 
		
		DATE, 
		
		DATE_TIME,

		LOG_XXX_CLASS_NAME,
		
		LOG_XXX_FILE_NAME,
		
		LOG_XXX_METHOD_NAME,
		
		LOG_XXX_LINE_NUMBER,
		
		LOG_XXX_LEVEL,
		
		;
	}

}
