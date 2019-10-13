package servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.xdevapi.JsonArray;
import com.mysql.cj.xdevapi.JsonParser;

import model.TodoModel;
import model.UserModel;

@WebServlet("/todo")
public class Todo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		Cookie[] cookies = request.getCookies();
		int uid = 0;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("user_id"))
				uid = Integer.parseInt(cookie.getValue());
		}

		TodoModel todoModel = new TodoModel();

		String content = request.getParameter("content");
		if (null != content) {

			entity.Todo todo = new entity.Todo(content, false, uid);
			todoModel.add(todo);
		}

		List<entity.Todo> done = todoModel.getDone(true, uid);
		List<entity.Todo> undone = todoModel.getDone(false, uid);

		request.setAttribute("done", done);
		request.setAttribute("undone", undone);

		request.getRequestDispatcher("todo.jsp").include(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		TodoModel todoModel = new TodoModel();
		todoModel.update(new entity.Todo(id, true));
		
	}
}
