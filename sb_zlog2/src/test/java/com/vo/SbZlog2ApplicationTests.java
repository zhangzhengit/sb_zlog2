package com.vo;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.vo.core.ZLog2;

//@SpringBootTest
class SbZlog2ApplicationTests {

	static final ZLog2 LOG = ZLog2.getInstance();

	@Test
	void contextLoads() {
		System.out.println(Thread.currentThread().getName() + "\t" + LocalDateTime.now() + "\t"
				+ "SbZlog2ApplicationTests.contextLoads()");


		final int i = 10000 * 20;

		for (int n = 1; n <= i; n++) {
			LOG.info("这是行数={}", n);
		}
	}

}
