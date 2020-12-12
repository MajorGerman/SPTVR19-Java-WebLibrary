<%-- 
    Document   : takeBookForm
    Created on : 03.12.2020, 13:37:38
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Купить товар</title>
    </head>
    <body>
        <div><strong><a style="font-size: 42px; text-decoration: none; color: #d9a34c;-webkit-text-stroke: 1px black;" href="index.jsp">Супер Библиотека</a></strong></div>
        <h1>Купить товар</h1>
        <form action="buyProduct" method="POST">
            <select name="productId" multiple="true">
                <c:forEach var="product" items="${listProducts}">
                    <option value="${product.id}">${product.name} (${product.price}$)</option>
                </c:forEach>
            </select>
            <br>
            <select name="persId" multiple="true">
                <c:forEach var="pers" items="${listPersons}">
                    <option value="${pers.id}">"${pers.name}" ${pers.surname} (${pers.money}$) </option>
                </c:forEach>
            </select>    
            <br>
            <input type="submit" value="Купить товар">
        </form>    
    </body>
</html>
