package net.test.user;

import java.sql.SQLException;
import java.util.List;

import net.test.support.RowMapper;
import net.test.support.jdbcTemplate;

import java.sql.ResultSet;

public class UserDAO {
	public void addUser(User user) throws SQLException {
		jdbcTemplate template = new jdbcTemplate();
		String sql = "insert into users values(?,?,?,?)";
		template.executeUpdate(sql, user.getUserId(), user.getPassword(),
				user.getName(), user.getEmail());
	}

	public User findByUserId(String userId) throws SQLException {
		RowMapper<User> rm = new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(
						rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email"));
			}
		};
			
		jdbcTemplate template = new jdbcTemplate();
		String sql = "SELECT * FROM users WHERE userId = ?";
		return template.executeQuery(sql,rm,userId);
	}

	public void removeUser(String userId) throws SQLException {
		jdbcTemplate template = new jdbcTemplate();
		String sql = "delete from USERS where userId = ?";
		template.executeUpdate(sql,userId);
	}

	public void updateUser(User user) throws SQLException {
		jdbcTemplate template = new jdbcTemplate();
		String sql = "update USERS set password= ? , name= ?, email= ? where userId=?";
		template.executeUpdate(sql,user.getPassword(), user.getName(), user.getEmail(),
				user.getUserId());
	}

	public List<User> findUsers() throws SQLException {
		RowMapper<User> rm = new RowMapper<User>(){

			@Override
			public User mapRow(ResultSet rs) throws SQLException {
				return new User(
						rs.getString("userId"),
						rs.getString("password"),
						rs.getString("name"),
						rs.getString("email"));
			}
		};
			
		jdbcTemplate template = new jdbcTemplate();
		String sql = "SELECT * FROM USERS";
		return template.list(sql, rm);
	}
}