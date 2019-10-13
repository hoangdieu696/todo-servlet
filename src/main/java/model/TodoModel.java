package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Todo;

public class TodoModel {

	private static Connection db = DB.connect();
	private String table = "todo";

	public int add(Todo todo) {

		int id = 0;

		try {
			PreparedStatement stmt = db.prepareStatement("insert into " + table + " (content, done, uid) values(?, ?, ?)");
			stmt.setString(1, todo.getContent());
			stmt.setBoolean(2, todo.isDone());
			stmt.setInt(3, todo.getUserId());
			id = stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}
	
	public void update(Todo todo) {
		
		try {
			PreparedStatement stmt = db.prepareStatement("update " + table + " set done = ? where id = ?");
			stmt.setBoolean(1, todo.isDone());
			stmt.setInt(2, todo.getId());
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Todo> getDone(boolean isDone, int uid) {
		
		List<Todo> todos = new ArrayList<Todo>();
		
		try {
			PreparedStatement stmt = db.prepareStatement("select id, content from " + table + " where done = ? and uid = ?");
			stmt.setBoolean(1, isDone);
			stmt.setInt(2, uid);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				
				int id = rs.getInt("id");
				String content = rs.getString("content");
				todos.add(new Todo(id, content, isDone));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return todos;
	}
}
