package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.User;

public class UserModel {

	private static Connection db = DB.connect();
	private String table = "user";

	public int add(User user) {

		int id = 0;

		try {
			PreparedStatement stmt = db.prepareStatement("insert into " + table + " (username, password) values(?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			stmt.executeUpdate();
			
			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

	public int isExisted(User user) {

		int id = 0;

		try {
			PreparedStatement stmt = db
					.prepareStatement("select id from " + table + " where username = ? and password = ?");
			stmt.setString(1, user.getUsername());
			stmt.setString(2, user.getPassword());
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}
}
