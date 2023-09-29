package pack1;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class Loginservlet
 */
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter out = response.getWriter();
		//out.println("Welcome");	

try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
				      +"punee","root","puni123");
		String s1 = request.getParameter("t1");
		String s2 = request.getParameter("t2");	
		
 PreparedStatement ps = con.prepareStatement("select uname from login where uname=? and password=?");
		
     ps.setString(1, s1);
     ps.setString(2, s2);
     
     ResultSet rs = ps.executeQuery();
     //ResultSet rs = st.executeQuery("select * from employee2 where empSal = 300000;");
		if(rs.next()) {
			RequestDispatcher rd = request.getRequestDispatcher("Welcome.jsp");
			rd.forward(request, response);
			}
		else
		 {
			out.println("<font color = red size=5>Incorrect Username or Password!!<br> ");
			out.println("<a href=Index.html>Try Again!!</a>");
			
			}
	} catch(Exception e) {
		System.out.println(e);
	}
		
}
}