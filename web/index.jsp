<%-- 
    Document   : index
    Created on : Nov 24, 2020, 2:31:43 PM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Библиотека Георга Лаабе</title>
    </head>
    <body>
        <div><strong><a style="font-size: 42px; text-decoration: none; color: #d9a34c;-webkit-text-stroke: 1px black;" href="index.jsp">Супер Библиотека</a></strong></div>
        <p>${info}</p>
        <br>
        <a href="addBook" onfocus="transform: scale(1.2);"> > Добавить новую книгу < </a><br>
        <a href="addReader"> > Добавить читателя < </a><br>
        <a href="listBooks"> > Список книг < </a><br>
        <a href="listReaders"> > Список читателей < </a><br>
        <a href="takeOnBookForm"> > Выдать книгу читателю < </a><br>
        <a href="returnBookForm"> > Вернуть книгу < </a><br>
    </body>
</html>
