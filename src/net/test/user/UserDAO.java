package net.test.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(JavaBeanUtilsTest.class);

	public Connection getConnection() {
		String url = "jdbc:mysql://localhost/slipp"; // ����Ϸ��� �����ͺ��̽����� ������ URL ���. tcp/ip ������ �̿��Ѵ�
		String id = "root"; // ����� ����
		String pw = "sungho"; // ����� ������ �н�����

		try {
			Class.forName("com.mysql.jdbc.Driver"); // �����ͺ��̽��� �����ϱ� ���� DriverManager�� ����Ѵ�.
			return DriverManager.getConnection(url,id,pw); // DriverManager ��ü�κ��� Connection ��ü�� ���´�.
			
		} catch(Exception e){
			logger.debug(e.getMessage());
			return null;
		}
	}

	public void addUser(User user) throws SQLException {
		String sql = "insert into users values(?,?,?,?)"; // sql ����
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = getConnection().prepareStatement(sql);
			
			pstmt.setString(1,user.getUserId());
			pstmt.setString(2,user.getPassword());
			pstmt.setString(3,user.getName());
			pstmt.setString(4,user.getEmail());

			pstmt.executeUpdate(); // ������ �����Ѵ�
		} finally{
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
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
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = getConnection().prepareStatement(sql);
			
			pstmt.setString(1, userId);

			pstmt.executeUpdate(); // ������ �����Ѵ�
		} finally{
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}

	public void updateUser(User user) throws SQLException {
		String sql = "update USERS set password= ? , name= ?, email= ? where userId=?";  // sql ����
		Connection conn = null;
		PreparedStatement pstmt = null;
		try{
			conn = getConnection();
			pstmt = getConnection().prepareStatement(sql);
			
			pstmt.setString(1,user.getPassword());
			pstmt.setString(2,user.getName());
			pstmt.setString(3,user.getEmail());
			pstmt.setString(4,user.getUserId());

			pstmt.executeUpdate(); // ������ �����Ѵ�.
		} finally{
			if(pstmt != null){
				pstmt.close();
			}
			if(conn != null){
				conn.close();
			}
		}
	}
}
