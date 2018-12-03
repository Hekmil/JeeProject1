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
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <link rel="stylesheet" type="text/css" href="style.css">
    </head>
    <body>

        <form METHOD="GET" class="form1"
              ACTION="http://localhost:8080/project1/Servlet">

            <table>
                <caption><h2><b>Input new user(s)</b></h2></caption>

                <tr>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>login</th>
                    <th>Delete this line</th>
                </tr>
                <c:forEach var="i" begin="0" end="3" step="1">
                    <tr>
                        <td>
                            <input type="text" name="first${i}" placeholder="First name" size="30" maxlength="20" />
                        </td>
                        <td>
                            <input type="text" name="last${i}" placeholder="last name" size="30" maxlength="20" />
                        </td>
                        <td>
                            <input type="text" name="login${i}" placeholder="Login (user name)" size="30" maxlength="20" />
                        </td>
                        <td style="text-align: center;">
                            <button id ="del" type='reset'>&#x274C;</button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <INPUT type="submit"> 
        </FORM>

        <script language="javascript">

        </script>
    </body>
</html>
