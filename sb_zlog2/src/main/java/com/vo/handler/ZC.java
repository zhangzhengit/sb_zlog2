package com.vo.handler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;

import org.springframework.web.context.annotation.ApplicationScope;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
			if (c2.getBusy() == false) {
				c2.setBusy(true);
				return c2.getConnection();
			}
		}
		throw new IllegalStateException("没有可用的连接了");
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class C2 {
		private Boolean busy;
		private Connection connection;
	}

}
