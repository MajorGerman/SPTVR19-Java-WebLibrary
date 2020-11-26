<%-- 
    Document   : addBook
    Created on : 24.11.2020, 14:00:12
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление книги</title>
    </head>
    <body>
        <h1> [ Библиотека Георга Лаабе ] </h1>
        <h2> Добавить книгу </h2>
        <form method="POST" action="createBook">
            <input id="name" name="name" style="width: 200px;" maxlength="40" placeholder="Название" style="border-radius: 55px; margin: 20px" required><br>
            <input id="author" name="author" style="width: 200px;" maxlength="40" placeholder="Автор"style="border-radius: 3px; margin: 20px"  required><br>
            <input type="number" id="year" name="year" style="width: 200px;" maxlength="5" placeholder="Год издания" style="border-radius: 3px; margin: 20px"  required><br>
            <input type="submit" value=" Подтвердить " style="border-radius: 3px;"><br>
            <h2 style="color: red;"> ${error} </h2>
        </form>
    </body>
</html>
