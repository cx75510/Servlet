package net.test.user;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	public static User TEST_USER = new User("userId", "asdf", "name", "jeongsh0202@gmail.com");
	private UserDAO userDao; 
	
	@Before
	public void setup() throws SQLException{
		userDao = new UserDAO();
		userDao.removeUser(TEST_USER.getUserId());
	}
	
	@Test
	public void matchPassword() {
		assertTrue(TEST_USER.matchPassword("asdf"));
	}
	
	@Test
	public void notMatchPassword() {
		assertFalse(TEST_USER.matchPassword("asdf2"));
	}
	
	@Test
	public void login() throws Exception {
		User user = UserTest.TEST_USER;
		userDao.addUser(user);
		assertTrue(User.login(TEST_USER.getUserId(), TEST_USER.getPassword()));
	}

	@Test(expected=UserNotFoundException.class)
	public void loginWhenNotExists() throws Exception {
		User.login("userId2", TEST_USER.getPassword());
	}

	@Test(expected=PasswordMismatchException.class)
	public void loginWhenPasswordMismatch() throws Exception {
		User user = UserTest.TEST_USER;
		UserDAO userDao = new UserDAO();
		userDao.addUser(user);
		
		User.login(TEST_USER.getUserId(), "password2");
	}
}
