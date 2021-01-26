 <%-- 
    Document   : listBooks
    Created on : 03.12.2020, 13:07:53
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> ~ Список покупателей ~ </title>
    </head>
    <body>
        <div><strong><a style="font-size: 42px; text-decoration: none; color: #d9a34c;-webkit-text-stroke: 1px black;" href="index.jsp"><<< Магазин Георга Лаабе >>></a></strong></div>
        <h6 style="font-size: 32px;" ${hiddenlogout}hidden> ${upuser}</h6>
        <a href="showLoginForm" ${hiddenlogin} style="font-size: 29px;"> > Войти < </a>
        <a href="logout" ${hiddenlogout}hidden style="font-size: 29px;"> > Выйти < </a> 
        <hr>
        <br><h1> ~ Покупатели ~ </h1>
        <ul name="persId" multiple="true">
            <c:forEach var="pers" items="${listPersons}">
                <li value="${pers.id}">${pers}</li>
            </c:forEach>
        </ul>
    </body>
</html>