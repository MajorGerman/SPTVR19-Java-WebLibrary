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
        <title> ~ Изменить данные покупателя ~ </title>
    </head>
    <body>
        <div><strong><a style="font-size: 42px; text-decoration: none; color: #d9a34c;-webkit-text-stroke: 1px black;" href="index.jsp"><<< Магазин Георга Лаабе >>></a></strong></div>
        <h6 style="font-size: 32px;" ${hiddenlogout}hidden> ${upuser}</h6>
        <a href="logout" ${hiddenlogout}hidden style="font-size: 29px;"> > Выйти < </a> 
        <hr><h1> ~ Изменить данные покупателя ~ </h1>
        <form action="editPersonForm2" method="POST">
            <select name="personId" multiple="true">
                <c:forEach var="pers" items="${listPersons}">
                    <option value="${pers.id}">${pers.name} ${pers.surname} (${pers.money}$)</option>
                </c:forEach>
            </select>
            <br><input type="submit" value="Изменить">
        </form>
    </body>
</html>
