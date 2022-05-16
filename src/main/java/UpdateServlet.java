import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class UpdateServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		System.out.println("update service called...");
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		
		String name = req.getParameter("name");
		String uname = req.getParameter("uname");
		String id = req.getParameter("regid");
		String stream = req.getParameter("stream");
		String grad = req.getParameter("grad");
		String cgpa = req.getParameter("cgpa");
		String password = req.getParameter("password");
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");

			String vsql = "select * from candidates where username=?";
			PreparedStatement pstmt = con.prepareStatement(vsql);
			pstmt.setString(1,uname);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Query1 Executed"+" "+uname+name+id+stream+grad+cgpa);
			
			if(rs.next())
			{
				vsql = "update candidates set name = ?, stream = ?, graduation = ?, cgpa = ?, password = ? where username=?";
				pstmt = con.prepareStatement(vsql);
				pstmt.setString(1, name);
				pstmt.setString(2, stream);
				pstmt.setString(3, grad);
				pstmt.setString(4, cgpa);
				pstmt.setString(5, password);
				pstmt.setString(6, uname);
				pstmt.executeUpdate();
				System.out.println("Query Executed");
				res.sendRedirect("update_profile.jsp");
			}
			
			con.close();
		}catch(Exception e){
			res.sendError(500,"Our application is failed due to:" + e);
		}
	}
}
