<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> ~ Регистрация покупателя ~</title>
    </head>
    <body>
        <div><strong><a style="font-size: 42px; text-decoration: none; color: #d9a34c;-webkit-text-stroke: 1px black;" href="index.jsp"><<< Магазин Георга Лаабе >>></a></strong></div>
        <h1> ~ Регистрация покупателя ~ </h1>
        <p style="border-style: groove; border-width: ${borderwidth}0px; border-color: #d9a34c;">${info}</p>
        <form action="addPerson" method="POST">
            Имя: <input type="text" name="name" value="${name}"><br>
            Фамилия: <input type="text" name="surname" value="${surname}"><br>
            Телефон: <input type="text" name="phone" value="${phone}"><br>
            Деньги: <input type="number" min="0" name="money" value="${money}"><br>
            Логин: <input type="text" name="login" value="${login}"><br>
            Пароль: <input type="password" name="password" value=""><br>
           <input type="submit" name="submit" value="Отправить"><br>
        </form>
    </body>
</html>
