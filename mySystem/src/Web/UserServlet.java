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
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
//	}
//
//	/**
//	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
//	 */
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}
    public void Login(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

    	request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		UserService userService=new UserService();
		User user=null;
		try{user=userService.Login(email, password);}
		catch(SQLException exception){
			exception.printStackTrace();
		}
		if(user.getName()!=null)
		{
		String remember=request.getParameter("remember");
		System.out.println(remember);
		if (remember!=null) {
			
			Cookie emailCookie=new Cookie("email", email);
			Cookie passwordCookie=new Cookie("password", password);
			emailCookie.setMaxAge(60*10);
			passwordCookie.setMaxAge(60*10);
			response.addCookie(emailCookie);
			response.addCookie(passwordCookie);			
		}
		
			response.sendRedirect("/mySystem/Manage.jsp");}
		else
		{
		request.setAttribute("error", "密码错误！");
		request.getRequestDispatcher("index.jsp").forward(request, response);
//		response.sendRedirect("/mySystem/login.jsp");
		}
			
		
    }
    public void Register(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
    	request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String password=request.getParameter("password");
		String email=request.getParameter("email");
		UserService userService=new UserService();
		boolean register=userService.register(email, name, password);
		if(register){
			
			response.sendRedirect("/mySystem/index.jsp");
		}
		else{
			
		request.setAttribute("RegisterError", "yes");
		request.getRequestDispatcher("index.jsp").forward(request, response);
			
			
		}
    }
}
