package com.vo.handler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.vo.conf.ZDBConf;

/**
 *
 *
 * @author zhangzhen
 * @date 2022年6月30日
 *
 */
public class ZDBHandler extends ZLogDefaultHandler{

	private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();

	private final ZDBConf dbConf;
	private final ZC zc;

	public ZDBHandler(final ZDBConf dbConf) {
		this.dbConf = dbConf;
		this.zc = new ZC(this.dbConf.getUrl(), this.dbConf.getUsername(), this.dbConf.getPassword());
	}

	@Override
	public String debug(final String message, final Object... args) {
		final String debug = super.debug(message, args);
		this.writeToDB(debug);
		return debug;
	}

	@Override
	public String trace(final String message, final Object... args) {
		final String trace = super.trace(message, args);
		this.writeToDB(trace);
		return trace;
	}


	@Override
	public String info(final String message, final Object... args) {
		final String info = super.info(message, args);
		this.writeToDB(info);
		return info;
	}

	@Override
	public String warn(final String message, final Object... args) {
		final String warn = super.warn(message, args);
		this.writeToDB(warn);
		return warn;
	}

	@Override
	public String error(final String message, final Object... args) {
		final String error = super.error(message, args);
		this.writeToDB(error);
		return error;
	}

	@Override
	public String fatal(final String message, final Object... args) {
		final String fatal = super.fatal(message, args);
		this.writeToDB(fatal);
		return fatal;
	}

	private void writeToDB(final String info) {
		this.queue.add(info);

		// FIXME 2022年6月30日 下午9:22:01 zhanghen: 写这里，写入到数据库
//		先写一个连接池吧
		final Connection connection = this.zc.getConnection();

		final String insert = "insert into log (level,class_name,thread_name,content,create_time)"
									+ " values (?,?,?,?,?,?);";
		try {
			final PreparedStatement ps = connection.prepareStatement(insert);
			final boolean execute = ps.execute();
			if (execute) {
				// FIXME 2022年6月30日 下午9:44:26 zhanghen: 这里写另一个方法，异步定时批量insert
			}

		} catch (final SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public String getLevel() {
		return this.dbConf.getLevel();
	}

	@Override
	public String getPattern() {
		return this.dbConf.getPassword();
	}

}
