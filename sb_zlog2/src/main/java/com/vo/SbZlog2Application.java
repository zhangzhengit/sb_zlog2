package com.vo;

import java.time.LocalDateTime;

import com.vo.core.ZLog2;

/**
 *
 *
 * @author zhangzhen
 * @date 2022-1-11 18:01:37
 *
 */
public class SbZlog2Application {

	static ZLog2 LOG = ZLog2.getInstance();

	public static void main(final String[] args) {
		LOG.info("OK,now={}", LocalDateTime.now());
		LOG.info("现在时间是[{}]", LocalDateTime.now());

		System.exit(0);

	}

}
