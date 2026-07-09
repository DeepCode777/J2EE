import java.sql.*;

public class preparedStmt {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Driver Connected");
			
			//connection
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/college","root","");
			
			System.out.println("Connection ok");
			
			//create statement
			Statement stmt = conn.createStatement();
			
			ResultSet rs1 = stmt.executeQuery("SELECT * from student");
			
			while(rs1.next()) {
				System.out.println("id = " + rs1.getInt("id") + " | name = " + rs1.getString("sname"));
			}
			
			String sql = "select * from student where sname = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			String sname = "ashish";
			pstmt.setString(1, sname);
			
			ResultSet rs2 = pstmt.executeQuery();
			
			while(rs2.next()) {
				System.out.println("id = " + rs2.getInt("id") + " | name = " + rs2.getString("sname"));
			}
			
			rs1.close(); stmt.close();
			rs2.close(); pstmt.close();
			conn.close();
			
			
		}catch(Exception e){
			System.out.print(e);
		}

	}

}
