package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class Util {
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/system?useSSL=true", "root", "root");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static UUID getUUID() {
		UUID uuid = UUID.randomUUID();
		return uuid;
	}
	
	public static void free(Connection con,Statement st,ResultSet re){
		try {
			if(con!=null)con.close();
			if(st!=null)st.close();
			if(re!=null)re.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
