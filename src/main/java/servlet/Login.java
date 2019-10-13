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

@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		UserModel userModel = new UserModel();
		int id = userModel.isExisted(new User(username, pass));

		response.setCharacterEncoding("utf-8");
		
		if (isLogin(request.getCookies())) {
			
			response.sendRedirect(request.getContextPath() + "/todo");
			return;
		}

		if (null == username || null == pass || id <= 0) {

			request.setAttribute("action", request.getContextPath() + "/login");
			request.setAttribute("actionButton", "Đăng nhập");

			request.getRequestDispatcher("login.jsp").include(request, response);
		} else {
			Cookie uid = new Cookie("user_id", String.valueOf(id));
			response.addCookie(uid);

			response.sendRedirect(request.getContextPath() + "/todo");
		}
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
