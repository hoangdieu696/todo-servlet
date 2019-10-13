package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.User;
import model.UserModel;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		
		UserModel userModel = new UserModel();
		User user = new User(username, pass);
		int id = userModel.isExisted(user);
		
		response.setCharacterEncoding("utf-8");

		if (null != username && null != pass && 0 == id) {
			
			id = userModel.add(new User(username, pass));
			Cookie uid = new Cookie("user_id", String.valueOf(id));
			response.addCookie(uid);

			response.sendRedirect("/todo");
			return;
		}

		if (!isLogin(request.getCookies())) {
			
			request.setAttribute("action", request.getContextPath() + "/register");
			request.setAttribute("actionButton", "Đăng kí");

			request.getRequestDispatcher("login.jsp").include(request, response);
		}
		else response.sendRedirect(request.getContextPath() + "/todo");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	public boolean isLogin(Cookie[] cookies) {

		if (null != cookies)
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("user_id") && null != cookie.getValue())
					return true;
			}

		return false;
	}
}
