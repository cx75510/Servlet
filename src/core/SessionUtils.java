package core;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionUtils {
	private static final Logger logger = LoggerFactory.getLogger(SessionUtils.class);
	
	public static boolean isEmpty(HttpSession session, String key){
		Object object = session.getAttribute(key);
		
		logger.debug((String) object);
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
