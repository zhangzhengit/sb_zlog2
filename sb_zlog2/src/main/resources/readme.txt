
1、 新工程A中 maven引入依赖
		<dependency>
			<groupId>com.vo</groupId>
			<artifactId>sb_zlog2</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>

2、 	配置文件名称：zlog.properties 
	支持零配置，无zlog.properties 配置文件也可正常启动。
	如果无此文件则，使用下面默认配置值，
	如需自定义输出格式等，则新建 zlog.properties 文件并输出自定义配置
	按需配置，也可不配置，不配置则看下面的逻辑
	配置模板见：zlog.properties_template

	查找此文件的顺序：
	1、jar包目录下 config/zlog.properties
	2、jar包目录下 zlog.properties
	3、jar包内的 resources下的 config/zlog.properties
	4、jar包内的 resources下的 zlog.properties
	
	如果以上4步都没找到 zlog.properties，则使用如下的代码中内置的配置：
	
	#####################默认配置开始#####################
	zlog.console.name=CONSOLE
	zlog.console.enable=true
	zlog.console.level=TRACE
	zlog.console.pattern=[%DATE_TIME]-[%LEVEL]-[%THREAD]-[%CLASS_NAME::%METHOD@%LINE_NUMBER]:[%MESSAGE]

	zlog.file.name=FILE
	zlog.file.enable=true
	zlog.file.level=TRACE
	zlog.file.pattern=[%DATE_TIME]-[%LEVEL]-[%THREAD]-[%CLASS_NAME::%METHOD@%LINE_NUMBER]:[%MESSAGE]
	
	zlog.file.filePath=[程序运行目录下的log目录]
	zlog.file.fileName=[程序名称.log]
	zlog.file.fileSize=100
	#####################默认配置结束#####################

	默认配置如上，如需自定义某个项，则新建 zlog.properties 文件覆盖掉对应的KYE即可
	如：需要自定义fileSize，则在中zlog.properties输入如下即可覆盖默认值的zlog.file.fileSize=100
	log.file.fileSize=200
	

3、 在工程A中 private static final ZLog2 LOG = ZLog2.getInstance();
	然后 LOG.XX记录日志
