package net.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.test.user.CreateUserServlet;

@WebServlet(name="HelloWorld",urlPatterns={"/helloworld", "/hello", "/hello/world"}) //web.xml ���Ͽ��� �����ϴ� �� ��ſ� ������̼��� ����� �� �ִ�.(web.xml ��� �����ϴ�.) servlet 3.0 annotaions example �� ���ۿ� �˻��Ͽ� Ȯ�ΰ����ϴ�.
public class HelloWorldServlet extends HttpServlet{
	//private String name; //�⺻������ ������ �̱� �ν��Ͻ��̴�. �׷��� �ʵ���� ��� �����尡 �����͸� �����ϴ� ��Ȳ�� �߻��Ѵ�. �ν��Ͻ� ���������� �� �����߻�! �׷��� ���� ������ �� �ʵ�� �������� ����, �޼ҵ� �ȿ��� ���������� Ȱ���ؼ� �����ؾ��Ѵ�.
	private static final Logger logger = LoggerFactory.getLogger(CreateUserServlet.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		logger.debug("Request Success!");
		resp.getWriter().println(name + " Hello World!"); //����� Ŭ���̾�Ʈ���� ������ �� �� response
	}
}

/*
 * �����̶�?
 * ������ ������� �ʾ��� ���� html�� Ȱ���ؼ��� ������ ������, �ѹ� �����Ͱ� �����Ǹ� �ٲ� �� ���� ���������� ����� �־���. 
 * html �Ѱ踦 �غ��ϰ� ��ǰ���, �Խ���, ����ڰ� ���� �߰��ϴ� ���� ������ ���������� ����� ���� ����ϴ� ���. �ڹٿ��� ������ ���������� �����ϱ� ���� ��� �� �ϳ��� �����̴�.
 * php, ruby �� ��κ� ������ ���� ���������� ���� �� �ִ� ����� �����Ѵ�.
 * ������� ��û�� �޾� �׿� �ش��ϴ� url���� path�� �����Ͽ� �׿� �ش��ϴ� ������ ã��, ������ ��û�� ������, �۾��� ������ ������ ������, �� ������ �޾Ƽ� Ŭ���̾�Ʈ�� �ٽ� ������ ������ tomcat ������ �Ѵ�. -> Web Application Server(was) = ���� �����̳�(������ ����ִ� �׸�)
 * 
 * <����>
 * Ŭ���̾�Ʈ���� HTTP Protocol�� ���� ������ ��û�� ������.
 * (��Ĺ�� �����̳� ����) 
 * �����̳ʴ� ������ �� �ش� ������Ʈ�� ������ �����ؼ� ������ �ִ� �����̴�.
 * Ŭ���̾�Ʈ���� ��û�� ������ �����̳ʰ� request, response instance�� �����Ѵ�.
 * ������ request, response instance�� ������ �����Ѵ�.
 * �� ������ ���������̳ʿ� Ư�� ������ �ϳ��� �Ҵ��� �Ǿ ����Ǵ� ���̴�. ��Ĺ�� ��Ƽ������ ����̴�. �� �� �ϳ��� �����忡 ������ �����Ǵ� ���̴�. 
 * ������ �����忡 �Ҵ�ǰ�, �� �� �տ��� ������ request, response�� �޼���(ex. service())�� ������ �ϸ鼭 �����ϰ� �ȴ�.
 * service() �޼��忡�� ��û�� get �Ǵ� post �޼��带 ȣ���ؼ� �������� �ش��ϴ� ����� ��û�� ���� ó���۾��� �Ѵ�.  
 * ó�� �۾��� ������ �������� ������ ������. 
 * �����̳ʰ� �� ������ �޾ƻ�� Ŭ���̾�Ʈ(������)�� �����ϰ� �ȴ�. 
 * Ŭ���̾�Ʈ�� ������ �Ϸ�ȴ�.��� �۾��� ���� ��  
 * �� ������ ���ݱ��� ����ߴ� �����尡 �����ȴ�. 
 * �տ��� �����ߴ� request, response �� �Ҹ��۾��� ���� ������� �ȴ�.
 * 
 * ������, request, response �ڿ��� ����������, ���� �ڿ��� �������� �ʴ´�. �ѹ� ������� instance�� ���� �����̳�(��Ĺ����) �� stop �Ǳ� ������ ����ȴ�.�ϳ��� instance�� ���� ����ڿ��� ���񽺸� �� �� �ִ� ���̴�.
 * 
 */
