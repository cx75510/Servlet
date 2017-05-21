package net.test.user;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.apache.commons.beanutils.BeanUtilsBean;

import net.test.support.MyValidatorFactory;

@WebServlet("/users/update")
public class UpdateUserServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sessionUserId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		if(sessionUserId == null){
			response.sendRedirect("/");
			return;
		}
		
		User user = new User();
		
		try {
			BeanUtilsBean.getInstance().populate(user, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e1) {
			throw new ServletException(e1);
		}
		
		if(!user.isSameUser(sessionUserId)){
			response.sendRedirect("/");
			return;
		}
		
		Validator validator = MyValidatorFactory.createValidator();
		/*
		 * 회원가입에서 아이디와 비밀번호를 입력하고 이름을 입력하지 않았을 때
		 * userID가 이미 존재해서 개인정보 수정으로 인식함
		 * user를 넘기기때문에 
		 */
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if(constraintViolations.size() > 0){
			request.setAttribute("isUpdate", true);
			request.setAttribute("user", user);
			String errorMessage = constraintViolations.iterator().next().getMessage();
			forwardJSP(request, response, errorMessage);
			return;
		}
		
		UserDAO userDAO = new UserDAO();
		try {
			userDAO.updateUser(user);
		} catch (SQLException e) {
		}
		
		response.sendRedirect("/"); // redirect 방식으로 이동
	}
	
	private void forwardJSP(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = request.getRequestDispatcher("/form.jsp");
		rd.forward(request, response);
	}
}
