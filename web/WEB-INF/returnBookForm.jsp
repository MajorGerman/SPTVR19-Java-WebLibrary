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
        <title>Возврат книг</title>
    </head>
    <body>
        <div><strong><a style="font-size: 42px; text-decoration: none; color: #d9a34c;-webkit-text-stroke: 1px black;" href="index.jsp">Супер Библиотека</a></strong></div>
        <h1>Возврат книги</h1>
        <form action="returnBook" method="POST">
            <select name="historyId" multiple="true">
                <option value="">Список книг</option>
                <c:forEach var="history" items="${listHistoriesWithReadBook}">
                    <option value="${history.id}">"${history.book.name}" читает ${history.reader.name} ${history.reader.lastname}</option>
                </c:forEach>
            </select><br>
            <input type="submit" value="Вернуть книгу"> 
        </form>
    </body>
</html>
