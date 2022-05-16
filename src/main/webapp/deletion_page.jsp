<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.HttpSession"%>
<%@ page import="javax.servlet.http.*"%>


<%
	String id = request.getParameter("candidate");
	if(id == null)
	{
		response.sendRedirect("/test");
	}
%>

<!DOCTYPE html>
<html>
    <head>
        <title>Confirmation</title>
        <style>
            @import url('https://fonts.googleapis.com/css2?family=Ubuntu:wght@300;400;500;700&display=swap');

            *
            {
                margin: 0;
                padding: 0px;
            }
            body
            {
                background-image: radial-gradient(farthest-corner at 0px 0px, rgb(0, 204, 255) ,#ffc107, #e91e63, rgb(0, 204, 255));
                background-repeat: no-repeat;
                overflow: hidden;
                font-family: 'Ubuntu', sans-serif;
            }

            html,body
            {
                height: 100%;
            }

            input[type="submit"]
            {
                padding: 10px;
                width: 100px;
                margin-bottom: 10px;
                border: 2px black solid;
                border-radius: 17px;
                font-size: 0.75em;
                cursor: pointer;
                font-weight: 1;
            }
            .message
            {
                width: fit-content;
                height: fit-content;
                margin-left: auto;
                margin-right: auto;
                margin-top: 15%;
                background-color: #d0d0d082;
                border-radius: 15px;
                box-shadow: 15px 15px 10px rgba(27, 27, 27, 0.382);
                padding: 20px;
                text-align: left;
                font-size: 2em;
                padding-bottom: 25px;
            }
        </style>
    </head>
    <body>
        <div class="message">
            <p>Are you sure you want to delete the application with
           <%=id%><br><br>
            <form action="delete">
                <input type="hidden" name="user" value=<%=id%>>
                <input type="submit" value="Yes">
            </form>
            <form action="test">
                <input type="submit" value="No">
            </form>
        </p>
        </div>
    </body>

</html>