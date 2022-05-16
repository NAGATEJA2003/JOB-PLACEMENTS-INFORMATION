import java.io.IOException;  
import java.io.PrintWriter;  
  
import javax.servlet.ServletException;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;  


public class LogoutServlet extends HttpServlet {
		protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
			res.setContentType("text/html");  
			HttpSession session=req.getSession();  
			session.setAttribute("uname", null);
			session.setAttribute("uname1", null);
			System.out.println("session name" + session.getAttribute("name"));
            session.setAttribute("name","logout");
            System.out.println("session name" + session.getAttribute("name"));
            res.sendRedirect("login.html");
		}

}
