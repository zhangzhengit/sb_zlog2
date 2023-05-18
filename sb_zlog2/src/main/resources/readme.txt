1、 新工程A中 maven引入依赖
		<dependency>
			<groupId>com.vo</groupId>
			<artifactId>sb_zlog2</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>

2、 配置文件 zlog.properties 按需配置

3、 在工程A中 public static final ZLog2 LOG = ZLog2.getInstance();
	然后 LOG.XX记录日志
