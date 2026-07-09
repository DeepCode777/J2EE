import java.sql.*;


class Sqlconnection {

	public static void main(String[] args) {
		try {
			//Driver registration for database connection
			// com.mysql.cj.jdbc.Driver for mysql server connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Connected");
			
			//URL
			//Connection established with mysql using mysql url and user and password
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			
			//Create a statement class object for executing queries on db
			Statement stmt = conn.createStatement();

			//A basic example of insert query
			//The affected data row will be stored in (result) vaiable
			int result = stmt.executeUpdate("insert into student values (5,'raju',19,'raju@gmail.com')");
			
			//In case if any row isn't affected the result variable will be set to 0
			if(result > 0) {
				System.out.println("One row added");
			}else {
				System.out.println("Unable to insert");
			}
			
			//ResultSet is a class
			//To see details for data of a table we use ResultSet
			//The rs is object of ResultSet
			ResultSet rs = stmt.executeQuery("select * from student");
			
			//To fetch data using ResultSet we have to use :
			//getInt for number datatype in sql
			//getString for varchar datatype in sql
			//These two funtions getInt and getString take the argumnet of column name


			//rs.next() will run until all rows are fetched
			while(rs.next()) {
				System.out.println("id : " + rs.getInt("id") + " name : " + rs.getString("sname") + " age : " + rs.getInt("age") + " email : " + rs.getString("email"));
			}
			
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}

}
