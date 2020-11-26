<%-- 
    Document   : addUser
    Created on : 26.11.2020, 14:18:09
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Добавление пользователя</title>
    </head>
    <body>
        <h1> [ Библиотека Георга Лаабе ] </h1>
        <h2> Добавить пользователя </h2>
        <form method="POST" action="createUser">
            <input id="name" name="name" style="width: 200px;" maxlength="40" placeholder="Имя" style="border-radius: 55px; margin: 20px" required><br>
            <input id="surname" name="surname" style="width: 200px;" maxlength="40" placeholder="Фамилия"style="border-radius: 3px; margin: 20px"  required><br>
            <input id="nick" name="nick" style="width: 200px;" maxlength="40" placeholder="Псевдоним"style="border-radius: 3px; margin: 20px"  required><br>
            <input id="phone" name="phone" style="width: 200px;" maxlength="20" placeholder="Телефон" style="border-radius: 3px; margin: 20px"  required><br>
            <input type="submit" value=" Подтвердить " style="border-radius: 3px;"><br>
            <h2 style="color: red;"> ${error} </h2>
        </form>
    </body>
</html>
