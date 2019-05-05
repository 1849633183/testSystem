package Web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Bean.User;
import Service.UserService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		System.out.println(email+"****"+password);
		UserService userService=new UserService();
		User user=null;
		try{user=userService.Login(email, password);}
		catch(SQLException exception){
			exception.printStackTrace();
		}
		if(user.getName()!=null)
		{
			String remember=request.getParameter("remember");
		if (remember.equals("yes")) {
			Cookie emailCookie=new Cookie("email", email);
			Cookie passwordCookie=new Cookie("password", password);
			emailCookie.setMaxAge(60*10);
			passwordCookie.setMaxAge(60*10);
			response.addCookie(emailCookie);
			response.addCookie(passwordCookie);
			
		}
		
			response.sendRedirect("/mySystem/zb.jsp");}
		else
		{response.sendRedirect("/mySystem/zbb.jsp");}
		
		
	}

}
