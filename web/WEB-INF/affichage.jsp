<%-- 
    Document   : affichage
    Created on : 10 oct. 2018, 10:27:27
    Author     : Jeremie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<HTML>
    <head>
        <meta charset="utf-8" />
        <title>Project 1</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style.css">
    </head>
    <body>
        <h1>Show all users</h1>
        
        <table>
            <caption><h2>Total user count : ${fn:length(messages)}</h2></caption>
            <tr>
                <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Login (user name)</th>
            </tr>
            <c:forEach items="${messages}" var="message">
                <tr>      
                    <td>${message.id}</td>
                    <td>${message.first_name}</td>
                    <td>${message.last_name}</td>
                    <td>${message.login}</td>
                </tr>
            </c:forEach>
        </table>
    </body>
</HTML>
