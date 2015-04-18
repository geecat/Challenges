import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;

public class UserServlet extends HttpServlet{ 
 
   public void doGet(HttpServletRequest request, HttpServletResponse response)
                                   throws ServletException,IOException{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String str[] = request.getRequestURI().toString().split("/");

         int id = Integer.parseInt(str[3]);
			System.out.println("MySQL Connect Example.");
			Connection conn = null;
			String url = "jdbc:mysql://localhost:3306/";
			String dbName = "user_register";
			String driver = "com.mysql.jdbc.Driver";
			String userName = "root"; 
			String password = "root";

		//	out.println(request.getRequestURI());
			Statement st;
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url+dbName,userName,password);
				System.out.println("Connected to the database");
				
				
				ArrayList userList=null;
				String query = "select * from userregister where id="+id;
				System.out.println("query " + query);
				st = conn.createStatement();
				ResultSet  rs = st.executeQuery(query);


				while(rs.next())
				{
				  
				  userList =new ArrayList();
				  userList.add(rs.getInt(1));
				  userList.add(rs.getString(2));
				  userList.add(rs.getString(4));
				  userList.add(rs.getString(5));
				  userList.add(rs.getString(6));
				  userList.add(rs.getString(7));
				  userList.add(rs.getString(8));
				
				}

				request.setAttribute("userList",userList);
				
                String nextJSP = "/editUser.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
				dispatcher.forward(request,response);
				conn.close();
				System.out.println("Disconnected from database");
			} catch (Exception e) {
			e.printStackTrace();
			}
  }
}