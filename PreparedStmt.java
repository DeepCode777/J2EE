import java.sql.*;

public class preparedStmt {

	public static void main(String[] args) {
		try {

			//com.mysql.cj.jdbc.Driver is driver name for sql driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Connected");
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			System.out.println("Connection ok");
			
			//Create a statement class object for executing queries on db
			Statement stmt = conn.createStatement();
			
			//ResultSet is used to stroe fetched data from table
			ResultSet rs1 = stmt.executeQuery("SELECT * from student");
			while(rs1.next()) {
				System.out.println("id = " + rs1.getInt("id") + " | name = " + rs1.getString("sname"));
			}
			
			//simple sql query
			String sql = "select * from student where sname = ?";

			//The PreparedStatement class is used to create dynamic queries
			//The pstmt is way to run query by using user input
			PreparedStatement pstmt = conn.prepareStatement(sql);

			//The pstmt contains (?) and called parameter marker
			//The value of (?) is given by pstmt.setXXX(sequenceNum, value);
			String sname = "ashish";
			pstmt.setString(1, sname); // 1 --> sequenceNum, sname --> value
			//The (?) works in sequence like if there is three (?,?,?) so the value will be given in squence as well
			
			//Used to fetch rows from table based on query
			ResultSet rs2 = pstmt.executeQuery();
			
			while(rs2.next()) {
				System.out.println("id = " + rs2.getInt("id") + " | name = " + rs2.getString("sname"));
			}
			
			//It essential to close all opened connection to save resources.
			rs1.close(); stmt.close();
			rs2.close(); pstmt.close();
			conn.close();
			
			
		}catch(Exception e){
			System.out.print(e);
		}

	}

}
