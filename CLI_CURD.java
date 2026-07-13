import java.sql.*;
import java.io.*;

public class CLI_CURD {
  static Connection conn;

  public static void main(String[] args) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      System.out.println("Driver Connected");

      String CONN_STRING = "jdbc:mysql://localhost/college";
      String DB_USER = "root";
      String DB_PASS = "";
      conn = DriverManager.getConnection(CONN_STRING, DB_USER, DB_PASS);
      System.out.println("Connected to DataBase");

      // Let get the user input until input is 5
      System.out.println("Enter 1 for Show records");
      System.out.println("Enter 2 for insert records");
      System.out.println("Enter 3 for update records");
      System.out.println("Enter 4 for delete records");
      System.out.println("Enter any for exit");
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int choice = 0;
      do {
        System.out.println("\nEnter Choice : ");
        choice = Integer.parseInt(br.readLine());

        switch (choice) {
          case 1:
            Show();
            break;
          case 2:
            Add();
            break;
          case 3:
            Update();
            break;
          case 4:
            Delete();
            break;
        }
      } while (choice < 5);
      System.out.println("\nExited");
      return;
    } catch (Exception e) {
      System.out.println(e);
    } finally {
      conn.close();
    }
  }

  public static int Show() {
    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("select * from student");
      System.out.println("---------------------------------------------------------------------");
      while (rs.next()) {
        System.out.println("id : " + rs.getInt("id") + " | name : " + rs.getString("sname") + " | age : " + rs.getInt("age") + " | email : " + rs.getString("email"));
      }
      System.out.println("---------------------------------------------------------------------");
      return 1;
    } catch (Exception e) {
      System.out.print(e);
      return 0;
    }
  }

  public static int Add() {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("\nEnter Student id : ");
      int id = Integer.parseInt(br.readLine());

      System.out.print("Enter Student name : ");
      String name = br.readLine();

      System.out.print("Enter Student age : ");
      int age = Integer.parseInt(br.readLine());

      System.out.print("Enter Student email : ");
      String email = br.readLine();

      PreparedStatement pstmt = conn.prepareStatement("insert into student values(?,?,?,?)");
      pstmt.setInt(1, id);
      pstmt.setString(2, name);
      pstmt.setInt(3, age);
      pstmt.setString(4, email);

      System.out.println("\n" + pstmt.executeUpdate() + " Record inserted...");

      return 1;
    } catch (Exception e) {
      System.out.print(e);
      return 0;
    }
  }

  public static int Update() {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("\nEnter Student id to update record : ");
      int old_id = Integer.parseInt(br.readLine());

      System.out.print("Enter Student name : ");
      String name = br.readLine();

      System.out.print("Enter Student age : ");
      int age = Integer.parseInt(br.readLine());

      System.out.print("Enter Student email : ");
      String email = br.readLine();

      PreparedStatement pstmt1 = conn.prepareStatement("update student set sname=?, age=?,email=? where id = ?");
      pstmt1.setString(1, name);
      pstmt1.setInt(2, age);
      pstmt1.setString(3, email);
      pstmt1.setInt(4, old_id);
      System.out.println("\n" + pstmt1.executeUpdate() + " Record Updated...");

      PreparedStatement pstmt2 = conn.prepareStatement("select * from student where id = ?");
      pstmt2.setInt(1, old_id);
      ResultSet rs = pstmt2.executeQuery();

      System.out.println("---------------------------------------------------------------------");
      while (rs.next()) {
        System.out.println("id : " + rs.getInt("id") + " | name : " + rs.getString("sname") + " | age : " + rs.getInt("age") + " | email : " + rs.getString("email"));
      }
      System.out.println("---------------------------------------------------------------------");

      return 1;
    } catch (Exception e) {
      System.out.print(e);
      return 0;
    }
  }

  public static int Delete() {
    try {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.print("\nEnter Student id to Delete record : ");
      int id = Integer.parseInt(br.readLine());

      PreparedStatement pstmt = conn.prepareStatement("delete from student where id = ?");
      pstmt.setInt(1, id);

      System.out.println("\n" + pstmt.executeUpdate() + " Record Deleted...");
      return 1;
    } catch (Exception e) {
      System.out.print(e);
      return 0;
    }
  }
}
