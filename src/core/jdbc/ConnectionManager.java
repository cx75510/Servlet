package core.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.test.user.JavaBeanUtilsTest;

public class ConnectionManager {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionManager.class);
	
	public static Connection getConnection() {
		String url = "jdbc:mysql://localhost/slipp";
		String id = "root";
		String pw = "sungho";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, id, pw);

		} catch (Exception e) {
			logger.debug(e.getMessage());
			return null;
		}
	}
}
