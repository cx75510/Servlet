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
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		try{
			User.login(userId, password);
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId); // 세션scope 에 데이터를 저장하는 과정을 통해 데이터 유지 index_jsp.java 참고
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


/* 
 * redirect, forward 차이
 * 
 * 1) redirect 
 * 클라이언트,서버 간의 두번의 요청, 응답이 생긴다
 * a.jsp 에서 데이터를 담아서 b.jsp 로 가져오는 것이 불가능하다.
 * http protocol 이 상태를 공유할 수 없는 문제 
 * 아예 url 이 b.jsp로 이동해버린다.
 * 
 * 2) forward
 * redirect도 안되는 것을 할때 => 데이터를 전달하려면 무조건 forward 방식을 이용해야 한다. 
 * 서버에서 클라이언트를 거치지 않고 b.jsp로 바로 이동을 하기 때문에 가능
 * a 결과값을 b에 전달하고 싶다
 * url이 b.jsp 에 있는 것이 아니라 a에 머물러 있다. 응답결과만 b.jsp로 동작을 한다.
 * 
 */
