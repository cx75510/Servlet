package net.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="HelloWorld",urlPatterns={"/helloworld", "/hello", "/hello/world"}) //web.xml 파일에서 설정하는 것 대신에 어노테이션을 사용할 수 있다.(web.xml 없어도 가능하다.) servlet 3.0 annotaions example 을 구글에 검색하여 확인가능하다.
public class HelloWorldServlet extends HttpServlet{
	//private String name; //기본적으로 서블릿은 싱글 인스턴스이다. 그래서 필드들은 모든 스레드가 데이터를 공유하는 상황이 발생한다. 인스턴스 동시접속할 때 문제발생! 그래서 서블릿 개발할 때 필드로 개발하지 말고, 메소드 안에서 지역변수를 활용해서 개발해야한다.
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		System.out.println("Request Success!");
		resp.getWriter().println(name + " Hello World!"); //결과를 클라이언트에게 응답을 줄 때 response
	}
}

/*
 * 서블릿이란?
 * 서블릿을 사용하지 않았을 때는 html을 활용해서는 정적인 데이터, 한번 데이터가 결정되면 바꿀 수 없는 웹페이지만 만들수 있었다. 
 * html 한계를 극복하고 상품목록, 게시판, 사용자가 글을 추가하는 등의 동적인 웹페이지를 만들기 위해 사용하는 기술. 자바에서 동적인 웹페이지를 개발하기 위한 기술 중 하나가 서블릿이다.
 * php, ruby 등 대부분 언어에서도 동적 웹페이지를 만들 수 있는 기술을 지원한다.
 * 사용자의 요청을 받아 그에 해당하는 url에서 path를 추출하여 그에 해당하는 서블릿을 찾아, 서블릿의 요청을 보내고, 작업이 끝난뒤 응답을 보내고, 그 응답을 받아서 클라이언트에 다시 보내는 역할을 tomcat 서버가 한다. -> Web Application Server(was) = 서블릿 컨테이너(서블릿을 답고있는 그릇)
 * 
 * <과정>
 * 클라이언트에서 HTTP Protocol을 통해 서버로 요청을 보낸다.
 * (톰캣이 컨테이너 역할) 
 * 컨테이너는 시작할 때 해당 프로젝트의 서블릿을 생성해서 가지고 있는 상태이다.
 * 클라이언트에서 요청이 들어오면 컨테이너가 request, response instance를 생성한다.
 * 생성한 request, response instance를 서블릿에 전달한다.
 * 이 서블릿은 서블릿컨테이너에 특정 스레드 하나에 할당이 되어서 실행되는 것이다. 톰캣은 멀티스레드 기반이다. 그 중 하나의 스레드에 서블릿이 배정되는 것이다. 
 * 서블릿이 스레드에 할당되고, 이 때 앞에서 생성한 request, response를 메서드(ex. service())에 전달을 하면서 실행하게 된다.
 * service() 메서드에서 요청이 get 또는 post 메서드를 호출해서 서버에서 해당하는 사용자 요청에 대한 처리작업을 한다.  
 * 처리 작업이 끝나면 서블릿에서 응답을 보낸다. 
 * 컨테이너가 그 응답을 받아사ㅓ 클라이언트(브라우저)에 전송하게 된다. 
 * 클라이언트가 응답이 완료된다.모든 작업이 끝난 것  
 * 그 시점에 지금까지 사용했던 스레드가 해제된다. 
 * 앞에서 생성했던 request, response 도 소멸작업을 거쳐 사라지게 된다.
 * 
 * 스레드, request, response 자원은 해제되지만, 서블릿 자원은 해제되지 않는다. 한번 만들어진 instance는 서블릿 컨테이너(톰캣서버) 가 stop 되기 전까지 재사용된다.하나의 instance가 여러 사용자에게 서비스를 할 수 있는 것이다.
 * 
 */
