package net.test.user.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.test.user.PasswordMismatchException;
import net.test.user.User;
import net.test.user.UserNotFoundException;

@WebServlet("/users/login")
public class LoginServlet extends HttpServlet {
	public static final String SESSION_USER_ID = "userId";	//�ߺ��� �߻��Ҽ� �ְ� �ڵ带 Ȯ���ؼ� �˾ƺ�����

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter(SESSION_USER_ID);
		String password = request.getParameter("password");
		
		try{
			User.login(userId, password);
			HttpSession session = request.getSession();
			session.setAttribute(SESSION_USER_ID, userId); // ����scope �� �����͸� �����ϴ� ������ ���� ������ ���� index_jsp.java ����
			response.sendRedirect("/");
			
		} catch(UserNotFoundException e){
			forwardJSP(request, response, "�������� �ʴ� ����� �Դϴ�. �ٽ� �α����ϼ���."); // �����޽��� ��� 
		} catch(PasswordMismatchException e){
			forwardJSP(request, response, "��й�ȣ�� Ʋ���ϴ�. �ٽ� �α����ϼ���.");
		}
	}

	private void forwardJSP(HttpServletRequest request, HttpServletResponse response, String errorMessage)
			throws ServletException, IOException {
		request.setAttribute("errorMessage", errorMessage); // request scope �� jsp�� ���� �Ұ��ϴ�. ������ ����scope �� �޸𸮰� ��� ���̹Ƿ� ��� �����͸� �ױ�� �δ㽺����.
		RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
		rd.forward(request, response); //request�� ��� �����Ͱ� login.jsp �� ����Ǿ login.jsp���� �����͸� ���� �� �ִ�.
	}
}

