import java.sql.*;


class Sqlconnection {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Connected");
			
			//URL
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			
			//statements
			
			Statement stmt = conn.createStatement();
			int result = stmt.executeUpdate("insert into student values (5,'raju',19,'raju@gmail.com')");
			
			if(result > 0) {
				System.out.println("One row added");
			}else {
				System.out.println("Unable to insert");
			}
			
			ResultSet rs = stmt.executeQuery("select * from student");
			
			while(rs.next()) {
				System.out.println("id : " + rs.getInt("id") + " name : " + rs.getString("sname") + " age : " + rs.getInt("age") + " email : " + rs.getString("email"));
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
