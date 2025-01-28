1、 新工程A中 maven引入依赖
		<dependency>
			<groupId>com.vo</groupId>
			<artifactId>sb_zlog2</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>

2、 配置文件 zlog.properties 按需配置，也可不配置，不配置则看下面的逻辑
	配置模板见：zlog.properties_template

	查找此文件的顺序：
	1、jar包目录下 config/zlog.properties
	2、jar包目录下 zlog.properties
	3、jar包内的 resources下的 config/zlog.properties
	4、jar包内的 resources下的 zlog.properties
	
	如果以上4步都没找到 zlog.properties，则使用如下的代码中内置的配置：
	 
	zlog.console.name=CONSOLE
	zlog.console.enable=true
	zlog.console.level=TRACE
	zlog.console.pattern=[%DATE_TIME]-[%LEVEL]-[%THREAD]-[%CLASS_NAME::%METHOD@%LINE_NUMBER] : [%MESSAGE]

	zlog.file.name=FILE
	zlog.file.enable=true
	zlog.file.level=TRACE
	zlog.file.pattern=[%DATE_TIME]-[%LEVEL]-[%THREAD]-[%CLASS_NAME::%METHOD@%LINE_NUMBER] : [%MESSAGE]
	
	zlog.file.filePath=[程序运行目录下的log目录]
	zlog.file.fileName=[程序名称.log]
	zlog.file.fileSize=100

	默认配置如上，如需自定义某个项，则新建 zlog.properties 文件覆盖掉对应的KYE即可

3、 在工程A中 public static final ZLog2 LOG = ZLog2.getInstance();
	然后 LOG.XX记录日志
