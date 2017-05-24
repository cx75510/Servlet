package net.test.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.test.user.JavaBeanUtilsTest;
import net.test.user.User;

public abstract class SelectJdbcTemplate {
	private static final Logger logger = LoggerFactory.getLogger(SelectJdbcTemplate.class);
	
	public Object executeQuery(String sql) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = ConnectionManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			setParameters(pstmt);
			
			rs = pstmt.executeQuery();
			
			return mapRow(rs);
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
	
	public abstract void setParameters(PreparedStatement pstmt) throws SQLException;

	public abstract Object mapRow(ResultSet rs) throws SQLException; 
}
