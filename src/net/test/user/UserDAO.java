package net.test.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {

	public Connection getConnection() {
		String url = "jdbc:mysql://localhost/slipp"; // 사용하려는 데이터베이스명을 포함한 URL 기술. tcp/ip 소켓을 이용한다
		String id = "root"; // 사용자 계정
		String pw = "sungho"; // 사용자 계정의 패스워드

		try {
			Class.forName("com.mysql.jdbc.Driver"); // 데이터베이스와 연동하기 위해 DriverManager에 등록한다.
			return DriverManager.getConnection(url,id,pw); // DriverManager 객체로부터 Connection 객체를 얻어온다.
			
		} catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void addUser(User user) throws SQLException {
		String sql = "insert into users values(?,?,?,?)"; // sql 쿼리
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		
		pstmt.setString(1,user.getUserId());
		pstmt.setString(2,user.getPassword());
		pstmt.setString(3,user.getName());
		pstmt.setString(4,user.getEmail());

		pstmt.executeUpdate(); // 쿼리를 실행한다.

	}

	public User findByUserId(String userId) throws SQLException {
		String sql = "SELECT * FROM users WHERE userId = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		
		pstmt.setString(1, userId);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(!rs.next()) { //예외로직 먼저 처리한 후에 나머지를 리턴하는 방식으로 코딩하면 소스양을 좀더 줄일 수 있다.
			return null;
		}
		
		return new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"), rs.getString("email"));
	}

	public void removeUser(String userId) throws SQLException {
		String sql = "delete from USERS where userId = ?";
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		
		pstmt.setString(1, userId);
		
		pstmt.executeUpdate();
	}

	public void updateUser(User user) throws SQLException {
		String sql = "update USERS set password= ? , name= ?, email= ? where userId=?";  // sql 쿼리
		PreparedStatement pstmt = getConnection().prepareStatement(sql);
		
		pstmt.setString(1,user.getPassword());
		pstmt.setString(2,user.getName());
		pstmt.setString(3,user.getEmail());
		pstmt.setString(4,user.getUserId());

		pstmt.executeUpdate(); // 쿼리를 실행한다.
		
	}
}
