<%-- 
    Document   : editBookForm
    Created on : 10.12.2020, 12:51:09
    Author     : Georg
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Изменить данные покупателя</title>
    </head>
    <body>
        <div><strong><a style="font-size: 42px; text-decoration: none; color: #d9a34c;-webkit-text-stroke: 1px black;" href="index.jsp"><<< Магазин Георга Лаабе >>></a></strong></div>
        <h6 style="font-size: 32px;" ${hiddenlogout}hidden> ${upuser}</h6>
        <a href="logout" ${hiddenlogout}hidden style="font-size: 29px;"> > Выйти < </a> 
        <hr><h1> ~ Изменить данные покупателя ~ </h1>
        <p style="border-style: groove; border-width: ${borderwidth}0px; border-color: #d9a34c;">${info}</p>
        <form action="editPerson" method="POST">
            <input type="hidden" name="persId" value="${pers.id}">
            Имя: <input type="text" name="name" value="${pers.name}"><br>
            Фамилия: <input type="text" name="surname" value="${pers.surname}"><br>
            Телефон: <input type="text" name="phone" value="${pers.phone}"><br>
            Деньги: <input type="number" min="0" name="money" value="${pers.money}"><br>
            Пароль: <input type="password" name="password" value=""><br>
           <br><input type="submit" name="submit" value="Отправить"><br>
        </form>
    </body>
</html>
