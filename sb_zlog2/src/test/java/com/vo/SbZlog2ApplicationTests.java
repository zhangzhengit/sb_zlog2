package com.vo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.vo.core.ZLog2;

import cn.hutool.log.Log;

@SpringBootTest
class SbZlog2ApplicationTests {

	static final ZLog2 LOG = ZLog2.getInstance();


	@Test
	void contextLoads() {
		System.out.println(java.time.LocalDateTime.now() + "\t" + Thread.currentThread().getName() + "\t"
				+ "SbZlog2ApplicationTests.contextLoads()");


		final int i = 10000 * 20;

		for (int n = 1; n <= i; n++) {
			LOG.info("AAAAAAAAAAAAAAAAAAAAAAAAAAA");
		}
	}

}
