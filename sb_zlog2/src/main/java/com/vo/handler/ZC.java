package com.vo.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 *	自定义连接池
 *
 * @author zhangzhen
 * @date 2022年6月30日
 *
 */
public class ZC {

	private final ImmutableList<C2> list;

	public ZC(final String url, final String username, final String password) {

		final int c = 5;
		final List<C2> cl = new ArrayList<>(c);
		for (int i = 1; i <= c; i++) {
			try {
				final Connection connection = DriverManager.getConnection(url, username, password);
				final C2 c2 = new C2(false, connection);
				cl.add(c2);
			} catch (final SQLException e) {
				e.printStackTrace();
			}
		}
		this.list = ImmutableList.copyOf(cl);
	}


	public void returnConnection(final Connection connection) {
		// 不考虑是否 connection.isClosed()
		for (final C2 c2 : this.list) {
			if(c2.getConnection() == connection) {
				c2.setBusy(false);
				return;
			}
		}

	}

	public Connection getConnection() {
		for (final C2 c2 : this.list) {
			if (!c2.getBusy()) {
				c2.setBusy(true);
				return c2.getConnection();
			}
		}
		throw new IllegalStateException("没有可用的连接了");
	}

	public static class C2 {
		private Boolean busy;
		private Connection connection;

		public C2(final Boolean busy, final Connection connection) {
			this.busy = busy;
			this.connection = connection;
		}

		public Boolean getBusy() {
			return this.busy;
		}

		public Connection getConnection() {
			return this.connection;
		}

		public void setBusy(final Boolean busy) {
			this.busy = busy;
		}

		public void setConnection(final Connection connection) {
			this.connection = connection;
		}

	}

}
