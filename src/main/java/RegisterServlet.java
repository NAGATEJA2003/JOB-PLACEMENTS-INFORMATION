import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		System.out.println("register service called...");
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		String name = req.getParameter("name");
		String uname = req.getParameter("uname");
		String pword = req.getParameter("password");
		String id = req.getParameter("regid");
		String stream = req.getParameter("stream");
		String grad = req.getParameter("grad");
		String cgpa = req.getParameter("cgpa");
		
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			String querycheck = "select * from candidates where id = ? or username = ?";
			PreparedStatement stmtcheck = con.prepareStatement(querycheck);
			stmtcheck.setString(1,id);
			stmtcheck.setString(2, uname);
			ResultSet rs = stmtcheck.executeQuery();
			if(rs.next())
			{
				out.println("<!DOCTYPE html>\r\n"
						+ "<html>\r\n"
						+ "    <head>\r\n"
						+ "        <title>Confirmation</title>\r\n"
						+ "        <style>\r\n"
						+ "            @import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap');\r\n"
						+ "\r\n"
						+ "            *\r\n"
						+ "            {\r\n"
						+ "                margin: 0;\r\n"
						+ "                padding: 0px;\r\n"
						+ "            }\r\n"
						+ "            body\r\n"
						+ "            {\r\n"
						+ "                background-image: radial-gradient(farthest-corner at 0px 0px, rgb(0, 204, 255) ,#ffc107, #e91e63, rgb(0, 204, 255));\r\n"
						+ "                background-repeat: no-repeat;\r\n"
						+ "                overflow: hidden;\r\n"
						+ "                font-family: 'Ubuntu', sans-serif;\r\n"
						+ "            }\r\n"
						+ "            body,html\r\n"
						+ "            {\r\n"
						+ "                height: 100%;\r\n"
						+ "            }\r\n"
						+ "            .message\r\n"
						+ "            {\r\n"
						+ "                width: fit-content;\r\n"
						+ "                height: fit-content;\r\n"
						+ "                margin-left: auto;\r\n"
						+ "                margin-right: auto;\r\n"
						+ "                margin-top: 15%;\r\n"
						+ "                background-color: #d0d0d082;\r\n"
						+ "                border-radius: 15px;\r\n"
						+ "                box-shadow: 15px 15px 10px rgba(27, 27, 27, 0.382);\r\n"
						+ "                padding: 20px;\r\n"
						+ "                text-align: left;\r\n"
						+ "                font-size: 2em;\r\n"
						+ "                padding-bottom: 25px;\r\n"
						+ "            }\r\n"
						+ "        </style>\r\n"
						+ "    </head>\r\n"
						+ "    <body>\r\n"
						+ "        <div class=\"message\">\r\n"
						+ "            <p>There is already a application registered with Id : &nbsp;"+id+"<br>or with Username : &nbsp;"+uname+"\r\n"
						+ "            <br>Please try with another Id/Username.<br><br>\r\n"
						+ "            <a href=\"registration.html\">Click here to Redirect to Registrations</a>\r\n"
						+ "        </p>\r\n"
						+ "        </div>\r\n"
						+ "    </body>\r\n"
						+ "\r\n"
						+ "</html>");
			}
			else
			{
				String query = "INSERT INTO userreg (username, password, occupation) VALUES (?, ?, ?)";
				PreparedStatement stmt = con.prepareStatement(query);
				stmt.setString(1, uname);
				stmt.setString(2, pword);
				stmt.setString(3, "student");
				stmt.executeUpdate();
				
				
				String query1 = "INSERT INTO candidates (name,id,stream,graduation,cgpa,username,password) VALUES (?, ?, ?, ?, ?, ?, ?)"; 
				PreparedStatement stmt1 = con.prepareStatement(query1);
				stmt1.setString(1, name);
				stmt1.setString(2, id);
				stmt1.setString(3, stream);
				stmt1.setString(4, grad);
				stmt1.setString(5, cgpa);
				stmt1.setString(6, uname);
				stmt1.setString(7, pword);
				stmt1.executeUpdate();
				con.close();
				res.sendRedirect("login.html");
			}
			
		}
		catch(Exception e)
		{
			res.sendError(500,"Our application is failed due to:" + e);
		}
	}
}
