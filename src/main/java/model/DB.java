package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {

	private static Connection db = null;

	public static Connection connect() {

		try {
			if (null == db || db.isClosed()) {

				Class.forName("com.mysql.cj.jdbc.Driver");
				db = DriverManager.getConnection("jdbc:mysql://localhost:3306/todos?useUnicode=true&characterEncoding=UTF-8", "root", "root");
			}
		} catch (SQLException e) {

			System.out.println("connection failed");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		return db;
	}
}
