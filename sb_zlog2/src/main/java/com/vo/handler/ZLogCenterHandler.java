package com.vo.handler;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.vo.conf.ZLogCenterConf;
import com.votool.common.ZPU;
import com.votool.ze.ZE;
import com.votool.ze.ZES;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpRequest;

/**
 *
 * 发送到日志中心
 *
 * @author zhangzhen
 * @date 2022年11月24日
 *
 */
public class ZLogCenterHandler extends ZLogDefaultHandler {

	private final Queue<AddDTO> queue = new LinkedBlockingQueue<>();

	private final String addLock = new String(String.valueOf(UUID.randomUUID()));

	private final ZE zeSINGLE = ZES.newZE(1);
	private final ZLogCenterConf conf;

	public ZLogCenterHandler(final ZLogCenterConf logCenterConf) {
		this.conf = logCenterConf;

		this.zeSINGLE.executeInQueue(() ->{
			this.send();
		});
	}

	@Override
	public String trace(final String message, final Object... args) {
		final String trace = super.trace(message, args);
		this.add(trace);
		return trace;
	}

	@Override
	public String debug(final String message, final Object... args) {
		final String debug = super.debug(message, args);
		this.add(debug);
		return debug;
	}

	@Override
	public String info(final String message, final Object... args) {
		final String info = super.info(message, args);
		this.add(info);
		return info;
	}

	@Override
		public String warn(final String message, final Object... args) {
			final String warn = super.warn(message, args);
			this.add(warn);
			return warn;
		}

	@Override
	public String error(final String message, final Object... args) {
		final String error = super.error(message, args);
		this.add(error);
		return error;
	}

	@Override
	public String fatal(final String message, final Object... args) {
		final String fatal = super.fatal(message, args);
		this.add(fatal);
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

	private void send() {
		System.out.println(java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t"
				+ "ZLogCenterHandler.send()");

		while (true) {
			if (this.queue.isEmpty()) {
				synchronized (this.addLock) {
					try {
						this.addLock.wait(1);
					} catch (final InterruptedException e) {
						e.printStackTrace();
					}
				}
			} else {

				if (this.queue.isEmpty()) {
					continue;
				}

				final List<AddDTO> list = Lists.newArrayListWithCapacity(this.queue.size());
				while (!this.queue.isEmpty()) {
					final AddDTO poll = this.queue.poll();
					if (poll == null) {
						break;
					}
					list.add(poll);
				}

				final List<byte[]> bsList = list.stream().map(ZPU::serialize).collect(Collectors.toList());
				final AddRequest addRequest = new AddRequest(bsList);

				final String jsonString = JSON.toJSONString(addRequest);

				HttpRequest.post(this.conf.getLogCenterURL()).body(jsonString).header("secretKey",this.conf.getSecretKey()).execute().body();

			}

		}
	}

	private void add(final String log) {
		synchronized (this.addLock) {

			final AddDTO addDTO = new AddDTO();
			addDTO.setAppId(this.conf.getAppId());
			addDTO.setAppName(this.conf.getAppName());
			addDTO.setK(log);
			this.queue.add(addDTO);

			this.addLock.notify();
		}

	}

}
