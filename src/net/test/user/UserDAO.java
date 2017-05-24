package net.test.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.test.support.SelectJdbcTemplate;
import net.test.support.jdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	private static final Logger logger = LoggerFactory.getLogger(JavaBeanUtilsTest.class);

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
		SelectJdbcTemplate template = new SelectJdbcTemplate() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
			
			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				if(!rs.next()) {
					return null;
				}
				
				return new User(rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email"));
			}
		};
		
		String sql = "SELECT * FROM users WHERE userId = ?";
		return (User)template.executeQuery(sql);
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
