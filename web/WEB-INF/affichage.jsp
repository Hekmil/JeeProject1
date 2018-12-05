<%-- 
    Document   : affichage
    Created on : 10 oct. 2018, 10:27:27
    Author     : Jeremie
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<HTML>
    <head>
        <meta charset="utf-8" />
        <title>JDBC</title>
        <link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
    </head>
    <body>
        <h1>Tests JDBC</h1>
        
        <c:forEach items="${messages}" var="message" varStatus="boucle">
            <p>${message}</p>
        </c:forEach>
    </body>
</HTML>
