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
			session.setAttribute("userId", userId); // ����scope �� �����͸� �����ϴ� ������ ���� ������ ���� index_jsp.java ����
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


/* 
 * redirect, forward ����
 * 
 * 1) redirect 
 * Ŭ���̾�Ʈ,���� ���� �ι��� ��û, ������ �����
 * a.jsp ���� �����͸� ��Ƽ� b.jsp �� �������� ���� �Ұ����ϴ�.
 * http protocol �� ���¸� ������ �� ���� ���� 
 * �ƿ� url �� b.jsp�� �̵��ع�����.
 * 
 * 2) forward
 * redirect�� �ȵǴ� ���� �Ҷ� => �����͸� �����Ϸ��� ������ forward ����� �̿��ؾ� �Ѵ�. 
 * �������� Ŭ���̾�Ʈ�� ��ġ�� �ʰ� b.jsp�� �ٷ� �̵��� �ϱ� ������ ����
 * a ������� b�� �����ϰ� �ʹ�
 * url�� b.jsp �� �ִ� ���� �ƴ϶� a�� �ӹ��� �ִ�. �������� b.jsp�� ������ �Ѵ�.
 * 
 */
