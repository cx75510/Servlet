package net.test.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.test.user.JavaBeanUtilsTest;
import net.test.user.User;
import net.test.user.UserDAO;

public abstract class jdbcTemplate {
	private static final Logger logger = LoggerFactory.getLogger(JavaBeanUtilsTest.class);

	public void executeUpdate(String sql) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);

			setParameters(pstmt);

			pstmt.executeUpdate();
		} finally {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	public abstract void setParameters(PreparedStatement pstmt) throws SQLException;
}
