package net.test.user;

import java.sql.SQLException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.test.support.PreparedStatementSetter;
import net.test.support.RowMapper;
import net.test.support.jdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
	public void addUser(User user) throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,user.getUserId());
				pstmt.setString(2,user.getPassword());
				pstmt.setString(3,user.getName());
				pstmt.setString(4,user.getEmail());
			}
		};
		jdbcTemplate template = new jdbcTemplate();
		String sql = "insert into users values(?,?,?,?)";
		template.executeUpdate(sql, pss);
	}

	public User findByUserId(String userId) throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		RowMapper rm = new RowMapper(){

			@Override
			public Object mapRow(ResultSet rs) throws SQLException {
				return new User(rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email"));
			}
		};
			
		jdbcTemplate template = new jdbcTemplate();
		String sql = "SELECT * FROM users WHERE userId = ?";
		return (User)template.executeQuery(sql,pss,rm);
	}

	public void removeUser(String userId) throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		jdbcTemplate template = new jdbcTemplate();
		String sql = "delete from USERS where userId = ?";
		template.executeUpdate(sql,pss);
	}

	public void updateUser(User user) throws SQLException {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setParameters(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1,user.getPassword());
				pstmt.setString(2,user.getName());
				pstmt.setString(3,user.getEmail());
				pstmt.setString(4,user.getUserId());
			}
		};
		jdbcTemplate template = new jdbcTemplate();
		String sql = "update USERS set password= ? , name= ?, email= ? where userId=?";  // sql ����
		template.executeUpdate(sql,pss);
	}
}