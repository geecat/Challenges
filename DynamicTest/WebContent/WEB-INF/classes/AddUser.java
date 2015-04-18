import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;

public class AddUser extends HttpServlet{ 
 
   public void doPost(HttpServletRequest request, HttpServletResponse response)
                                   throws ServletException,IOException{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();

			System.out.println("MySQL Connect Example.");
			Connection conn = null;
			String url = "jdbc:mysql://localhost:3306/";
			String dbName = "user_register";
			String driver = "com.mysql.jdbc.Driver";
			String userName = "root"; 
			String password = "root";

			String username = request.getParameter("username").toString();
			String first_name = request.getParameter("first_name").toString();
			String last_name = request.getParameter("last_name").toString();
			String city = request.getParameter("city").toString();
			String state = request.getParameter("state").toString();
			String country = request.getParameter("country").toString();
		
			
			Statement stmt;
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url+dbName,userName,password);
				System.out.println("Connected to the database");
				
				ArrayList al=null;
				ArrayList userList =new ArrayList();
				String query = "insert into userregister set username='"+username+"',first_name='"+first_name+"',last_name='"+last_name+"',city='"+city+"',state='"+state+"',country='"+country+"'";
				stmt = conn.createStatement();
            
			    int i = stmt.executeUpdate(query);
				System.out.println("query" + query);
				if(i>0)
				{
					response.sendRedirect("servletRecord");
				}
				conn.close();
				System.out.println("Disconnected from database");
			} catch (Exception e) {
			e.printStackTrace();
			}
	  }
}