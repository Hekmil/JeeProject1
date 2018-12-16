<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>JEE project 1</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="style.css">
    </head>
    <body>

        <form method="post" class="form1" action="http://localhost:8080/JeeProject1/Servlet">

            <table id="tableau1">
                <caption><h2><b>Input new user(s)</b></h2></caption>
                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Login (user name)</th>
                    <th>Delete this line</th>
                </tr>
                <c:forEach var="i" begin="0" end="3" step="1">
                    <tr>
                        <td>
                            <input type="text" name="first${i}" placeholder="First name" size="30" maxlength="20" />
                        </td>
                        <td>
                            <input type="text" name="last${i}" placeholder="Last name" size="30" maxlength="20" />
                        </td>
                        <td>
                            <input type="text" name="login${i}" placeholder="User name" size="30" maxlength="20" />
                        </td>
                        <td style="text-align: center;">
                            <button id="del" onclick="delLine(${i});" type="button">&#x274C;</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <input type="submit"> 
        </form>

        <form method="post" class="form2" action="http://localhost:8080/JeeProject1/Servlet"> 
            <div style="margin-left : 200px"><h2><b>Search for user</b></h2></div>
            
            <table><tr>
                        <td><select id="selectSearch" name="selectSearch">
                                <option value="*">All names</option>
                                <option value="first_name">First name</option>      
                                <option value="last_name">Last name</option> 
                                <option value="login">Login</option>
                        </select></td>
                        <td><input type="text" name="inputSearch" size="50"/></td>
                        <td><button type="submit">Search</button></td>
            </tr></table>
        </form>

        <script language="javascript">
            function delLine(i) {
                //
                document.getElementById("tableau1").deleteRow(i+1);
                //document.getElementById("last" + i).value = "";
               // document.getElementById("login" + i).value = "";
            }
        </script>
    </body>
</html>

