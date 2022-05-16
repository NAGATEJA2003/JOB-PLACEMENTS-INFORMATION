
<!DOCTYPE html>
<html>
    <link rel="stylesheet" type="text/css" href="regis.css">
    <head>
        <title>PO Registration Page</title>
        <style>
            table
            {
                width: 450px;
                margin-top: 6%;
            }
            header
            {
                text-align: center;
                padding-top: 2%;
                padding-bottom: 2%;
                font-weight: 600;
                background: rgba(255, 255, 255, 0.267);
                backdrop-filter: blur(10px);
                font-size: 30px;
            }

            .username
            {
                float: left;
                margin-top: 6px;
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


            function isNumber(evt)
            {
                evt = (evt) ? evt : window.event;
                var charcode = (evt.which) ? evt.which : evt.keyCode;
                if((charcode > 31 && charcode < 48 || charcode > 57) && charcode != 46)
                {
                    return false;
                }
                return true;
            }

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
                var uname = document.getElementById("uname").value;
                var pword = document.getElementById("password").value;
                var conf = document.getElementById("confirm").value;
                if(uname == "")
                {
                    document.getElementById("uname").style.borderColor = "red";
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
                    document.getElementById("reg").submit();
                }
            }
        </script>
    </head>
    <body>
        <header>HOD PORTAL</header>
        <ol>
            <li class="logout"><a href="logout"><p class="logout">LOG OUT</a></li>
            <li class="ul"><a href="logout">________</a></li>
        </ol>
        <table align="center">
            <form id="reg" method="get" action="po_register">
                <tr class="header">
                    <td colspan="2" align="center">Placement Officer<br>Registration Form</td>
                </tr>

                <tr><td colspan="2"><div class="line"></div></td></tr>

                <tr>
                    <td>User Name</td>
                    <td><input type="text" id="uname" name="uname" onblur="nofocus('uname')" maxlength="20"></td>
                </tr>

                <tr>
                    <td>Password<p>(min 8 char)</p></td>
                    <td><input type="password" id="password" onblur="nofocus('password')" name="password" maxlength="20">
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
                    <td colspan="2" align="center"><input type="button" value="REGISTER" onclick="register()"></td>
                </tr>

            </form>
        </table>
    </body>
</html>