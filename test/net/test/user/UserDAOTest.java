package net.test.user;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

public class UserDAOTest {

	private UserDAO userDao;

	@Before
	public void setup(){
		userDao = new UserDAO();
	}
	
	@Test
	public void connection() {
		Connection con = userDao.getConnection();
		assertNotNull(con); 
	}
	
	@Test
	public void crud() throws Exception {
		User user = UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		userDao.addUser(user);
		
		User dbUser = userDao.findByUserId(user.getUserId());
		assertEquals(user, dbUser);
	}
	
	@Test
	public void ��������_�ʴ�_�����_��ȸ() throws Exception {
		User user = UserTest.TEST_USER;
		userDao.removeUser(user.getUserId());
		
		User dbUser = userDao.findByUserId(user.getUserId());
		assertNull(dbUser);
	}
}
