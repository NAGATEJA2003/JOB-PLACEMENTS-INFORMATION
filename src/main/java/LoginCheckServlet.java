import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class LoginCheckServlet extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		System.out.println("login service called...");
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		String uname = req.getParameter("uname");
		String pwd = req.getParameter("pword");
		try{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			System.out.println("HTML DETAILS : "+uname+" "+pwd);
			
			String vsql = "select * from userreg where username=? and password=?";
			PreparedStatement pstmt = con.prepareStatement(vsql);
			pstmt.setString(1,uname);
			pstmt.setString(2,pwd);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("Query1 Executed");
			
			String checksql = "select * from rejected_candidates where username=? and pword=?";
			PreparedStatement pstmtcheck = con.prepareStatement(checksql);
			pstmtcheck.setString(1, uname);
			pstmtcheck.setString(2, pwd);
			ResultSet rscheck = pstmtcheck.executeQuery();
			System.out.println("Query2 Executed");

			if(!rscheck.next())
			{
				if( rs.next() ){
					HttpSession session = req.getSession();
					session.setAttribute("name", uname);
					req.setAttribute("uname", uname);
					if(rs.getString("occupation").equals("PO"))
					{
						RequestDispatcher rd = req.getRequestDispatcher("/test");
						rd.forward(req, res);
					}
					if(rs.getString("occupation").equals("student"))
					{
						RequestDispatcher rd = req.getRequestDispatcher("student_home.jsp");
						rd.forward(req, res);
					}
					if(rs.getString("occupation").equals("HOD"))
					{
						RequestDispatcher rd = req.getRequestDispatcher("Hod_Portal.jsp");
						rd.forward(req, res);
					}
				}else{
					RequestDispatcher rd = req.getRequestDispatcher("invalid.html");
					rd.include(req,res);
				}
			}
			else {
				res.sendRedirect("Message.html");
			}
			con.close();
		}catch(Exception e){
			res.sendError(500,"Our application is failed due to:" + e);
		}
	}
}
