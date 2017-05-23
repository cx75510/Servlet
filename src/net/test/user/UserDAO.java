package net.test.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.test.support.jdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(JavaBeanUtilsTest.class);

	public Connection getConnection() {
		String url = "jdbc:mysql://localhost/slipp";
		String id = "root";
		String pw = "sungho";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url,id,pw);
			
		} catch(Exception e){
			logger.debug(e.getMessage());
			return null;
		}
	}

	public void addUser(User user) throws SQLException {
		jdbcTemplate template = new jdbcTemplate(){

			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,user.getUserId());
				pstmt.setString(2,user.getPassword());
				pstmt.setString(3,user.getName());
				pstmt.setString(4,user.getEmail());
			}
		};
		String sql = "insert into users values(?,?,?,?)";
		template.executeUpdate(sql);
	}

	public User findByUserId(String userId) throws SQLException {
		String sql = "SELECT * FROM users WHERE userId = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = getConnection();
			pstmt = getConnection().prepareStatement(sql);
			pstmt.setString(1, userId);
			
			rs = pstmt.executeQuery();
			
			if(!rs.next()) { //���ܷ��� ���� ó���� �Ŀ� �������� �����ϴ� ������� �ڵ��ϸ� �ҽ����� ���� ���� �� �ִ�.
				return null;
			}
			
			return new User(rs.getString("userId"),
					rs.getString("password"),
					rs.getString("name"),
					rs.getString("email"));
		}finally{
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
			if(rs != null){
				rs.close();
			}
		}
	}

	public void removeUser(String userId) throws SQLException {
		String sql = "delete from USERS where userId = ?";
		
		jdbcTemplate template = new jdbcTemplate() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		template.executeUpdate(sql);
	}

	public void updateUser(User user) throws SQLException {
		jdbcTemplate template = new jdbcTemplate() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,user.getPassword());
				pstmt.setString(2,user.getName());
				pstmt.setString(3,user.getEmail());
				pstmt.setString(4,user.getUserId());
			}
		};
		String sql = "update USERS set password= ? , name= ?, email= ? where userId=?";  // sql ����
		template.executeUpdate(sql);
	}
}
