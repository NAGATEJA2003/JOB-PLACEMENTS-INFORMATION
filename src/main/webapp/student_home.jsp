<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page session="true" %>


<%
try{
	String uname = request.getParameter("uname");
	String uname1 = (String)session.getAttribute("uname1");
	if(uname1 == null)
	{
		session.setAttribute("uname", uname);
	}
	if(uname == null && uname1 == null)
	{
		response.sendRedirect("login.html");
	}
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
	String vsql = "select * from candidates where username=?";
	PreparedStatement pstmt = con.prepareStatement(vsql);
	if(uname == null)
	{
		pstmt.setString(1,uname1);	
	}
	else
	{
		pstmt.setString(1,uname);	
	}
	ResultSet rs = pstmt.executeQuery();

%>


<!DOCTYPE html>
<html>
    <head>
        <title>Student Portal</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap');
            *
            {
                margin: 0;
                font-family: 'Ubuntu', sans-serif;
            }

            body
            {
                transition: 1s;
                background-image: radial-gradient(farthest-corner at 0px 0px, rgb(0, 204, 255) ,#ffc107, #e91e63, rgb(0, 204, 255));
                background-repeat: no-repeat;
                overflow: hidden;
            }

            body,html
            {
                height: 100%;
            }

            header
            {
                text-align: center;
                padding-top: 2%;
                padding-bottom: 2%;
                margin-top: 0%;
                font-weight: 600;
                background: rgba(255, 255, 255, 0.267);
                backdrop-filter: blur(10px);
                font-size: 30px;
            }

            .username
            {
                float: left;
                font-size: 1.25em;
                font-weight: 500;
                margin-left: 5px;
                text-transform: uppercase;
            }
            
            .logout a,.ul a
            {
                cursor: pointer;
                color: #fff;
                text-decoration: none;
            }

            .logout
            {
                overflow: hidden;
            }

            ol
            {
                overflow: hidden;
                float: right;
                margin-top: 10px;
                margin-right: 5px;
            }

            .ul
            {
                overflow: hidden;
                margin-top: -20%;
            }

            .navig
            {
                width: fit-content;
                margin-left: auto;
                margin-right: auto;
                margin-top: 17%;
            }

            button
            {
                padding: 10px;
                font-size: 1em;
                font-weight: 600;
                border-radius: 20px;
                border-color: 2px black;
                margin: 20px;
                transition: 0.35s;
                cursor: pointer;
            }

            button:hover
            {
                background-color: #44413a;
                color: #fff;
            }

        </style>
    </head>
    <body>
        <header>STUDENT PORTAL
        </header>
        <ol>
            <li class="logout"><a href="logout"><p class="logout">LOG OUT</a></li>
            <li class="ul"><a href="logout">________</a></li>
        </ol>
                    <p class="username">user: &nbsp;
            <%
            	if(rs.next()){%>
            		<%=rs.getString("name")%><%
            	}}
				catch(Exception e)
			{
			}%>
            </p>
        <div class="navig">
            <a href="view_profile.jsp"><button>VIEW PROFILE</button></a>
            <a href="update_profile.jsp"><button>UPDATE PROFILE</button></a>
        </div>
    </body>
</html>