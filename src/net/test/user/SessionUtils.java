package net.test.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {
	public static boolean isEmpty(HttpSession session, String key){
		Object object = session.getAttribute(key);
		System.out.println(object);
		if(object == null){
			return false;
		}			//login üũ
		return true;
	}
	
	public static String getStringValue(HttpSession session, String key){
		if(!isEmpty(session, key)){
			return null;
		}
		
		return (String)session.getAttribute(key);
	}
}
