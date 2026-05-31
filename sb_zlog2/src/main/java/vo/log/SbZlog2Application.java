package vo.log;

import java.time.LocalDateTime;

import vo.log.core.ZLog2;

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
		final long t1 = System.currentTimeMillis();
		 int n = 125;
		// int n = 10000 * 50;
		final int n2 = n;
		LOG.trace("OK,now={}", LocalDateTime.now());
		LOG.debug("OK,now={}", LocalDateTime.now());
		LOG.info("OK,now={}", LocalDateTime.now());
		LOG.warn("OK,now={}", LocalDateTime.now());
		LOG.error("OK,now={}", LocalDateTime.now());
		LOG.fatal("OK,now={}", LocalDateTime.now());
		while (n-- > 0) {
			LOG.info("现在时间是[{}]", LocalDateTime.now());
		}

		final long t2 = System.currentTimeMillis();
		System.out.println("n = " + n2 + "\t" + "ms = " + (t2 - t1));
		System.exit(0);
	}

}
