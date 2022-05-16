<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>

<%
try{
	
	String uname=(String)session.getAttribute("uname");
	if(uname == null)
	{
		response.sendRedirect("login.html");
	}
	session.setAttribute("uname1", uname);
	
	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");

	String vsql = "select * from candidates where username=?";
	PreparedStatement pstmt = con.prepareStatement(vsql);
	pstmt.setString(1,uname);
	ResultSet rs = pstmt.executeQuery();
	if(rs.next())
	{
		String n = rs.getString("name");

%>

<!DOCTYPE html>
<html>
    <head>
        <title>Profile Page</title>
        <style>
        	@import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap');

*
{
    margin: 0;
    font-family: 'Ubuntu', sans-serif;
    overflow-x: hidden;
}


body
{
    background-image: radial-gradient(farthest-corner at 0px 0px, rgb(0, 204, 255) ,#ffc107, #e91e63, rgb(0, 204, 255));
    background-repeat: no-repeat;
}

body,html
{
    height: 100%;
}

table .header
{
    font-size: 1.75em;
    border-radius: 25px;
    padding: 17px;
}

table tr td p
{
    font-size: 0.82em;
}

input[type=checkbox]
{
    margin-top: 3%;
    margin-left: 25px;
}

table tr td .sh
{
    margin-top: 1%;
    margin-left: 1%;
    display: inline;
    font-size: 0.85em;
}

table
{
    display: block;
    width: fit-content;
    margin-left: auto;
    margin-right: auto;
    background-color: rgba(208, 225, 240, 0.295);
    margin-top: 4%;
    margin-bottom: 0.4%;
    padding: 20px;
    border-radius: 15px;
    offset: 0;
    backdrop-filter: blur(15px);
    box-shadow: 10px 10px 5px #3532326e;
    border-collapse: collapse;
}

input[type=button],tr td a .back_button
{
    font-size: 17px;
    padding: 12px 20px;
    position: relative;
    border-radius: 15px;
    margin-top: 3%;
    cursor: pointer;
    border: none;
    background-color: #ffc107;
}

table tr
{
    position: relative;
    overflow: hidden;
    width: 88%;
    padding-top: -50%;
}

.line
{
    margin-left: 1%;
    margin-right: 1%;
    margin-top: 1%;
    margin-bottom: 1%;
    height: 2px;
    width: 100%;
    background:repeating-linear-gradient(to right,rgb(61, 61, 61) 0,rgb(61, 61, 61) 5px)    
}

input[type=text], input[type=password], select
{
    padding: 10px 18px;
    width: 88%;
    margin-left: 9%;
    font-size: 15px;
    display: inline-block;
    border-top: transparent;
    border-left: transparent;
    border-right: transparent;
    border-bottom: 4px solid rgb(179, 177, 177);
    background: rgb(238, 236, 236);
    border-radius: 4px;
    box-sizing: border-box;
    outline: none;
    transition: 1s;
}

table tr td
{
    border-collapse: collapse;
    font-size: 1em;
}

table td
{
    margin-top: -5%;
    font-size: 17px;
    padding: 10px 7px;
    font-weight: 500;
}

.login_link
{
    font-size: 0.85em;
}

a
{
    color: rgb(0, 4, 255);
}
        </style>
    </head>
    <body>
        <table align="center">
            <form id="reg" action="update">
                <tr class="header">
                    <td colspan="2" align="center">MY PROFILE</td>
                </tr>

                <tr><td colspan="2"><div class="line"></div></td></tr>

                <tr>
                    <td>Name</td>
                    <td><input type="text" id="name" name="name" value="<%=n%>" onblur="nofocus('name')" maxlength="50" readonly></td>
                </tr>

                <tr>
                    <td>User Name</td>
                    <td><input type="text" id="uname" name="un" value=<%=rs.getString("username") %> onblur="nofocus('uname')" readonly></td>
                </tr>

                <tr>
                    <td>Registration Id</td>
                    <td><input type="text" id="regid" name="re" value=<%=rs.getString("id") %> onblur="nofocus('regid')" readonly></td>
                </tr>

                <tr>
                    <td>Stream</td>
                    <td><input type="text" id="stream" name="stream" value="<%=rs.getString("stream") %>" readonly onblur="nofocus('stream')" maxlength="20"></td>
                </tr>

                <tr>
                    <td>Graduation</td>
                    <td><input type="text" name="regid" value=<%=rs.getString("graduation") %> readonly></td>
                </tr>

                <tr>
                    <td>CGPA</td>
                    <td><input type="text" id="cgpa" value=<%=rs.getString("cgpa") %> readonly name="cgpa" onblur="nofocus('cgpa')" 
                        onkeypress="return isNumber(event)" maxlength="5"></td>
                </tr>
                
            </form>
            <%
	}
}
catch(Exception e)
{
}
            %>
        </table>
    </body>
</html>