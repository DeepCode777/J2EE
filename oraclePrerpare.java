
import java.sql.*;

public class prepare {

	public static void main(String[] args) {
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Driver Connected");
			
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","12345");
			
			PreparedStatement stmt = con.prepareStatement("update kp set name=?,age=? where id=?");
			
			
			stmt.setString(1, "Gaurav");
			stmt.setInt(2, 16);
			stmt.setInt(3, 3);
			
			int i = stmt.executeUpdate();
			
			System.out.println(i+"Record Inserted");
			
			ResultSet rs = stmt.executeQuery("select *from kp");
			
			while(rs.next())
			{
				System.out.println(rs.getInt(1)+ ""+rs.getString(2)+""+rs.getInt(3));
			}
			con.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

}
