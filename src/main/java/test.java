import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;


public class test extends HttpServlet
{
	public void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
	{
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

		PrintWriter out = res.getWriter();
		String username = (String)req.getParameter("uname");
		System.out.println(username);
		String opt = "";
		String limit = "";
		if(req.getParameter("grad") != null || req.getParameter("cgpa") != null)
		{
			opt = req.getParameter("grad");
			limit = req.getParameter("cgpa");
		}
		
		System.out.println(opt+" "+limit);
		String vsql="";
		if(opt.equals("ug") && opt != null)
		{
			vsql = "select * from candidates where (graduation = 'UG' or graduation = 'ug') and cgpa >= ?";
			System.out.println("UG Candidates");
		}
		else if(opt.equals("pg") && opt != null)
		{
			vsql = "select * from candidates where (graduation = 'PG' or graduation = 'pg') and cgpa >= ?";
			System.out.println("PG Candidates");
		}
		else
		{
			vsql = "select * from candidates where cgpa >= ?";
			System.out.println("All Candidates");
		}	
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
			PreparedStatement pstmt = con.prepareStatement(vsql);
			pstmt.setString(1,limit);
			
			HttpSession session=req.getSession(false);
			String us_name = (String) session.getAttribute("name");
			
			ResultSet rs = pstmt.executeQuery();
					out.println("<html>\r\n"
							+ "							 \r\n"
							+ "    <head>\r\n"
							+ "        <title>Jobs Placement Information</title>\r\n"
							+ "    </head>\r\n"
							+ "    \r\n"
							+ "    <style>\r\n"
							+ "		@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap');\r\n"
							+ "        *\r\n"
							+ "        {\r\n"
							+ "        margin: 0;\r\n"
							+ "        padding: 0;\r\n"
							+ "        font-family: 'Ubuntu', sans-serif;\r\n"
							+ "        overflow-x: hidden;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        body\r\n"
							+ "        {\r\n"
							+ "        background-image: radial-gradient(farthest-corner at 0px 0px, rgb(0, 204, 255) ,#ffc107, #e91e63, rgb(0, 204, 255));\r\n"
							+ "        background-repeat: no-repeat;\r\n"
							+ "        overflow: hidden;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .logout a,.ul a\r\n"
							+ "        {\r\n"
							+ "        cursor: pointer;\r\n"
							+ "        color: #fff;\r\n"
							+ "        text-decoration: none\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        p\r\n"
							+ "        {\r\n"
							+ "            float: right;\r\n"
							+ "            font-size: 0.5em;\r\n"
							+ "            font-weight: 500;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        ol\r\n"
							+ "        {\r\n"
							+ "        float: right;\r\n"
							+ "        height: 50px;\r\n"
							+ "        display : block;\r\n"
							+ "        margin-right: 15px;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .ul\r\n"
							+ "        {\r\n"
							+ "        margin-top: -20%;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        header\r\n"
							+ "        {\r\n"
							+ "        align-items: center;\r\n"
							+ "        text-align: center;\r\n"
							+ "        font-size: 2.5em;\r\n"
							+ "        padding: 15px;\r\n"
							+ "        transform: translateZ(100%);\r\n"
							+ "        background-color: rgba(255, 228, 228, 0.712);\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        html,body\r\n"
							+ "        {\r\n"
							+ "        margin: 0;\r\n"
							+ "        height: 100%;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        body::before\r\n"
							+ "        {\r\n"
							+ "        content: '';\r\n"
							+ "        position: fixed;\r\n"
							+ "        background: fixed;\r\n"
							+ "        top: 0;\r\n"
							+ "        left: 0;\r\n"
							+ "        width: 100%;\r\n"
							+ "        height: 100%;\r\n"
							+ "        background: linear-gradient(580deg, #ffee00 750px, #e91e62, #e91e62);\r\n"
							+ "        clip-path: circle(18% at 8% 20%);\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .cand\r\n"
							+ "        {\r\n"
							+ "        margin-left: 5%;\r\n"
							+ "        margin-right:15%;\r\n"
							+ "        display: block;\r\n"
							+ "        padding: 15px;\r\n"
							+ "        border-radius: 10px;\r\n"
							+ "        backdrop-filter: blur(25px);\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .gra\r\n"
							+ "        {\r\n"
							+ "        width: 25%;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .grad\r\n"
							+ "        {\r\n"
							+ "        width: 100%;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .table-wrapper\r\n"
							+ "        {\r\n"
							+ "        margin-top: 4%;\r\n"
							+ "        display: block;\r\n"
							+ "        margin-left: 5%;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        table\r\n"
							+ "        {\r\n"
							+ "        background-image: radial-gradient(farthest-corner at 0px 0px, rgba(0, 204, 255, 0.815), rgba(245, 171, 245, 0.781));\r\n"
							+ "        max-height: 500px;\r\n"
							+ "        max-width: 1200px;\r\n"
							+ "        overflow-y: scroll;\r\n"
							+ "        overflow-x: hidden;\r\n"
							+ "        border-radius: 25px;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .line\r\n"
							+ "        {\r\n"
							+ "        margin-left: 1%;\r\n"
							+ "        margin-right: 1%;\r\n"
							+ "        margin-top: 1%;\r\n"
							+ "        height: 2px;\r\n"
							+ "        background:repeating-linear-gradient(to right,rgb(255, 255, 255) 0,rgb(255, 255, 255) 5px,transparent 5px,transparent 7px)    \r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "\r\n"
							+ "        table::-webkit-scrollbar\r\n"
							+ "        {\r\n"
							+ "        width: 0px;\r\n"
							+ "        }\r\n"
							+ "        .cand tr td\r\n"
							+ "        {\r\n"
							+ "        padding: 25px;\r\n"
							+ "        font-size: 1em;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .job, .empty\r\n"
							+ "        {\r\n"
							+ "        background-color: rgba(27, 118, 209, 0.514);\r\n"
							+ "        transition: 0.5s;\r\n"
							+ "        width: 100%;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .job:hover\r\n"
							+ "        {\r\n"
							+ "        background-color: rgba(240, 248, 255, 0.493);\r\n"
							+ "        transform: translate(1%);\r\n"
							+ "        border-radius: 10px;\r\n"
							+ "        transition: 0.5s;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .select_button\r\n"
							+ "        {\r\n"
							+ "        font-size: 0.75em;\r\n"
							+ "        font-weight: 900;\r\n"
							+ "        padding: 10px;\r\n"
							+ "        border: 2.5px rgb(112, 112, 112) solid;\r\n"
							+ "        border-radius: 20px;\r\n"
							+ "        cursor: pointer;\r\n"
							+ "        transition: 0.5s;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        select\r\n"
							+ "        {\r\n"
							+ "        border-radius: 7px;\r\n"
							+ "        font-size: 900;\r\n"
							+ "        padding: 7px;\r\n"
							+ "        margin-top: 10px;\r\n"
							+ "        font-size: 0.75em;\r\n"
							+ "        cursor: pointer;\r\n"
							+ "        border: 2.5px rgb(112, 112, 112) solid;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        .delete\r\n"
							+ "        {\r\n"
							+ "            border-radius: 17px;\r\n"
							+ "            font-size: 900;\r\n"
							+ "            padding: 10px;\r\n"
							+ "            font-size: 0.75em;\r\n"
							+ "            cursor: pointer;\r\n"
							+ "            border: 2.5px red solid;\r\n"
							+ "            font-weight: 900;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        option\r\n"
							+ "        {\r\n"
							+ "        font-weight: 900;\r\n"
							+ "        }\r\n"
							+ "\r\n"
							+ "        input[type=text]\r\n"
							+ "        {\r\n"
							+ "        border-radius: 7px;\r\n"
							+ "        padding: 7px;\r\n"
							+ "        border: 2.5px rgb(112, 112, 112) solid;\r\n"
							+ "        margin-top: 10px;\r\n"
							+ "        font-size: 0.75em;\r\n"
							+ "        width: 30px;\r\n"
							+ "        }\r\n"
							+ "        </style>\r\n"
							+ "							 \r\n"
							+ "        <script>\r\n"
							+ "            function isNumber(evt)\r\n"
							+ "            {\r\n"
							+ "                evt = (evt) ? evt : window.event;\r\n"
							+ "                var charCode = (evt.which) ? evt.which : evt.keyCode;\r\n"
							+ "                if (charCode > 31 && (charCode < 48 || charCode > 57))\r\n"
							+ "                {\r\n"
							+ "                    return false;\r\n"
							+ "                }\r\n"
							+ "                return true;\r\n"
							+ "            }\r\n"
							+ "            function submit_form()\r\n"
							+ "            {\r\n"
							+ "                var gradu = document.getElementsByClassName(\"grad\").value;\r\n"
							+ "                var gpa = document.getElementsByClassName(\"cgpa\").value;\r\n"
							+ "                document.getElementById(\"sort\").submit();\r\n"
							+ "                alert(\"Data Updated\");\r\n"
							+ "            }\r\n"
							+ "        </script>\r\n"
							+ "							 \r\n"
							+ "        <body>\r\n"
							+ "            <header>JOB CANDIDATES\r\n"
							+ "                <p>"+us_name.toUpperCase()+"</p>\r\n"
							+ "            </header>\r\n"
							+ "            <br>\r\n"
							+ "            <p><br>\r\n"
							+ "\r\n"
							+ "                <ol>\r\n"
							+ "                    <li class=\"logout\"><a href=\"logout\">LOG OUT</a></li>\r\n"
							+ "                    <li class=\"ul\"><a href=\"logout\">________</a></li>\r\n"
							+ "                </ol>\r\n"
							+ "            </p>\r\n"
							+ "            <form class=\"sort\" id=\"sort\" action=\"test\">  \r\n"
							+ "            <div class=\"table-wrapper\">\r\n"
							+ "\r\n"
							+ "        <table class=\"cand\" cellpadding=\"0\" cellspacing=\"0\" width=\"90%\">\r\n"
							+ "\r\n"
							+ "                <tr class=\"head\">\r\n"
							+ "                        <td>NO</td>\r\n"
							+ "                        <td>NAME</td>\r\n"
							+ "                        <td>REG ID</td>\r\n"
							+ "                        <td>STREAM</td>\r\n"
							+ "                        <td class=\"gra\">\r\n"
							+ "                            GRADUATION : \r\n"
							+ "                            <select name=\"grad\" class=\"grad\" id=\"grad\">\r\n"
							+ "                                <option selected value=\"all\">ALL</option>\r\n"
							+ "                                <option value=\"ug\">UNDER GRADUATION (UG)</option>\r\n"
							+ "                                <option value=\"pg\">POST GRADUATION (PG)</option>\r\n"
							+ "                            </select>\r\n"
							+ "                        </td>\r\n"
							+ "                        <td>CGPA >= <input type=\"text\" name=\"cgpa\" id=\"cgpa\" value=\"0\" class=\"cgpa\" onkeypress=\"return isNumber(event)\" maxlength=\"2\">\r\n"
							+ "                        </td>\r\n"
							+ "                        <td><input type=\"button\" class=\"select_button\" value=\"SELECT\" onclick=\"submit_form()\"></td>     \r\n"
							+ "                </tr>");
					int i=1;
						try {
							out.println("<form action=\"deletion_page.jsp\"></form>");;
							if(rs.next())
							{
								String name = rs.getString("name");
								String id = rs.getString("id");
								String stream = rs.getString("stream");
								String grad = rs.getString("graduation");
								String cgpa = rs.getString("cgpa");
								
								out.println(
										"                <tr class=\"job\">\r\n"
										+ "                <tr class=\"job\">\r\n"
										+ "                    <td>"+(i++)+"</td>\r\n"
										+						"<td>"+name.toUpperCase()+"</td>\r\n"
										+ 						"<td>"+id +"</td>\r\n"
										+ "                    <td>"+stream.toUpperCase() +"</td>\r\n"
										+ "                    <td>"+grad.toUpperCase() +"</td>\r\n"
										+ "                    <td>"+cgpa +"</td>\r\n"
										+ "                    <td><form action=\"deletion_page.jsp\">\r\n"
										+ "                        <input type=\"hidden\" name=\"candidate\" value=\""+id+"\">\r\n"
										+ "                        <input class=\"delete\" type=\"submit\" value=\"Delete\">\r\n"
										+ "                    </form></td> \r\n"
										+ "                </tr>\r\n"
										);
								
								while(rs.next())
								{
									name = rs.getString("name");
									id = rs.getString("id");
									stream = rs.getString("stream");
									grad = rs.getString("graduation");
									cgpa = rs.getString("cgpa");
									
									out.println(
											"                <tr class=\"job\">\r\n"
											+ "                <tr class=\"job\">\r\n"
											+ "                    <td>"+(i++)+"</td>\r\n"
											+						"<td>"+name.toUpperCase()+"</td>\r\n"
											+ 						"<td>"+id +"</td>\r\n"
											+ "                    <td>"+stream.toUpperCase() +"</td>\r\n"
											+ "                    <td>"+grad.toUpperCase() +"</td>\r\n"
											+ "                    <td>"+cgpa +"</td>\r\n"
											+ "                    <td><form action=\"deletion_page.jsp\">\r\n"
											+ "                        <input type=\"hidden\" name=\"candidate\" value=\""+id+"\">\r\n"
											+ "                        <input class=\"delete\" type=\"submit\" value=\"Delete\">\r\n"
											+ "                    </form></td> \r\n"
											+ "                </tr>\r\n"
											);
							}
								}
							else
							{
								int a=1/0;
							}
						}
						catch(Exception e)
						{
							System.out.println("No rows are there "+e);
							out.println("                <tr class=\"empty\" align=\"center\">\r\n"
									+ "                    <td colspan=\"7\">No Candidates are Selected</td>\r\n"
									+ "                </tr>");
						}
						
						out.println(
								"        </table>\r\n"
								+ "        </div>\r\n"
								+ "    </form>\r\n"
								+ "    </body>\r\n"
								+ "</html>");
		con.close();
		}catch(Exception e)
		{
			res.sendError(500,"Our application is failed due to:" + e);
		}
}
}
