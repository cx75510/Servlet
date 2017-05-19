package net.test.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/login")
public class LoginServlet extends HttpServlet {
	public static final String SESSION_USER_ID = "userId";	//중복이 발생할수 있고 코드를 확인해서 알아봐야함

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter(SESSION_USER_ID);
		String password = request.getParameter("password");
		
		try{
			User.login(userId, password);
			HttpSession session = request.getSession();
			session.setAttribute(SESSION_USER_ID, userId); // 세션scope 에 데이터를 저장하는 과정을 통해 데이터 유지 index_jsp.java 참고
			response.sendRedirect("/");
			
		} catch(UserNotFoundException e){
			forwardJSP(request, response, "존재하지 않는 사용자 입니다. 다시 로그인하세요."); // 에러메시지 담기 
		} catch(PasswordMismatchException e){
			forwardJSP(request, response, "비밀번호가 틀립니다. 다시 로그인하세요.");
		}
	}

	private void forwardJSP(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage); // request scope 는 jsp에 접근 불가하다. 하지만 세션scope 는 메모리가 계속 쌓이므로 모든 데이터를 쌓기는 부담스럽다.
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response); //request에 담긴 데이터가 login.jsp 에 저장되어서 login.jsp에서 데이터를 꺼낼 수 있다.
	}
}

