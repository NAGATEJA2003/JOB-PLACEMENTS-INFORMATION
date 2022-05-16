import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class DeleteServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		System.out.println("delete service called...");
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		String id = req.getParameter("user");
		try
		{
			HttpSession session=req.getSession(false);
			String us_name = (String) session.getAttribute("name");
			System.out.println("session name" + session.getAttribute("name"));
			
			System.out.println("test service called...");
			if(session.getAttribute("name").equals("logout"))
			{
				System.out.println("You already have logged out, Please login.");
				res.sendRedirect("login.html");
			}
		}
		catch(Exception e)
		{
			System.out.println("You yet not loggined to the page, Please login.");
			res.sendRedirect("login.html");
		}
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			String vsql = "select * from candidates where id=?";
			PreparedStatement pstmt = con.prepareStatement(vsql);
			pstmt.setString(1,id);
			ResultSet rs = pstmt.executeQuery();
			
			
			String sql = "insert into rejected_candidates(username, pword) values(?, ?)";
			PreparedStatement sqlst = con.prepareStatement(sql);
			if(rs.next())
			{
				sqlst.setString(1, rs.getString("username"));
				sqlst.setString(2, rs.getString("password"));
			}
			sqlst.executeUpdate();
			
			
			sql = "delete from candidates where id="+id;
			sqlst = con.prepareStatement(sql);
			sqlst.executeUpdate();
			res.sendRedirect("test");
			con.close();
			
			
		}catch(Exception e){
			res.sendError(500,"Our application is failed due to:" + e);
		}
	}
}
