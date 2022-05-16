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
        <title>Registration Page</title>
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
    width: 30%;
    margin-left: auto;
    margin-right: auto;
    background-color: rgba(208, 225, 240, 0.295);
    margin-top: 2.2%;
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
        <script>
            function nofocus(id)
            {
                var data = document.getElementById(id).value;
                if(data != "")
                {
                    document.getElementById(id).style.borderColor = "rgb(179, 177, 177)";
                }
            }

            function sleep(ms)
            {
                return new Promise(resolve => setTimeout(resolve, ms));
            }

            async function divi()
            {
                await sleep(700);
                div.getElementById("line").style.width = "100%";
                div.getElementById("line").style.transition = '1s';
            }
            //divi()

            function myFunction(id)
            {
                var x = document.getElementById(id);
                if (x.type === "password") {
                  x.type = "text";
                } else {
                  x.type = "password";
                }
            }
            
            function register()
            {
                var name = document.getElementById("name").value;
                var grad = document.getElementById("grad").value;
                var cgpa = document.getElementById("cgpa").value;
                var stream = document.getElementById("stream").value;
                var pword = document.getElementById("password").value;
                var conf = document.getElementById("confirm").value;
                if(name == "")
                {
                    document.getElementById("name").style.borderColor = "red";
                    return
                }
                function countChar(str, x)
                {
                    let count = 0;
                    for (let i = 0; i < str.length; i++)
                    {
                        if (str[i] == x)
                            count++;
                    }
                    return count;
                }
                if(stream == "")
                {
                    document.getElementById("stream").style.borderColor = "red";
                    return
                }
                if(cgpa == "" || countChar(cgpa,'.') > 1)
                {
                    document.getElementById("cgpa").style.borderColor = "red";
                    return
                }
                if(pword.length < 8)
                {
                    document.getElementById("password").style.borderColor = "red";
                    return
                }
                if(conf.length < 8)
                {
                    document.getElementById("confirm").style.borderColor = "red";
                    return
                }

                if(pword > conf || pword < conf)
                {
                    document.getElementById("password").style.borderColor = "red";
                    document.getElementById("confirm").style.borderColor = "red";
                    return
                }

                if(pword == conf)
                {
                    alert("Updated Successfully")
                    document.getElementById("reg").submit();
                }
            }
        </script>
    </head>
    <body>
        <table align="center">
            <form id="reg" action="update">
                <tr class="header">
                    <td colspan="2" align="center">UPDATE MY PROFILE</td>
                </tr>

                <tr><td colspan="2"><div class="line"></div></td></tr>

                <tr>
                    <td>Name</td>
                    <td><input type="text" id="name" name="name" value="<%=n%>" onblur="nofocus('name')" maxlength="50"></td>
                </tr>

                <tr>
                    <td>User Name</td>
                    <td><input type="text" id="uname" name="un" value=<%=rs.getString("username") %> onblur="nofocus('uname')" disabled></td>
                    <input type="hidden" name="uname" value=<%=rs.getString("username") %>>
                </tr>

                <tr>
                    <td>Registration Id</td>
                    <td><input type="text" id="regid" name="re" value=<%=rs.getString("id") %> onblur="nofocus('regid')" disabled></td>
                    <input type="hidden" name="regid" value=<%=rs.getString("id") %>>
                </tr>

                <tr>
                    <td>Stream</td>
                    <td><input type="text" id="stream" name="stream" value="<%=rs.getString("stream") %>" onblur="nofocus('stream')" maxlength="20"></td>
                </tr>

                <tr>
                    <td>Graduation</td>
                    <td><select name="grad" class="grad" id="grad">
                        <option value="UG">UNDER GRADUATION (UG)</option>
                        <option value="PG">POST GRADUATION (PG)</option>
                    </select>
                </tr>

                <tr>
                    <td>CGPA</td>
                    <td><input type="text" id="cgpa" value=<%=rs.getString("cgpa") %> name="cgpa" onblur="nofocus('cgpa')" 
                        onkeypress="return isNumber(event)" maxlength="5"></td>
                </tr>

                <tr>
                    <td>Password<p>(min 8 char)</p></td>
                    <td><input type="password" id="password" value="<%=rs.getString("password")%>" onblur="nofocus('password')" name="password" maxlength="20">
                        <input type="checkbox" onclick="myFunction('password')" id="show">
                        <label for="show" class="sh">Show Password</label></td>

                </tr>

                <tr>
                    <td>Confirm Password</td>
                    <td><input type="password" id="confirm" onblur="nofocus('confirm')" name="password" maxlength="20">
                        <input type="checkbox" onclick="myFunction('confirm')" id="shows">
                        <label for="shows" class="sh">Show Password</label></td>
                </tr>

                <tr>
                    <td align="center" colspan="2"><input type="button" value="UPDATE" onclick="register()"></td>
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