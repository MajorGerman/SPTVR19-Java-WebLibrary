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
        <title>Магазин Георга Лаабе</title>
    </head>
    <body>
        <div><strong><a style="font-size: 42px; text-decoration: none; color: #d9a34c;-webkit-text-stroke: 1px black;" href="index.jsp"><<< Магазин Георга Лаабе >>></a></strong></div>
        <h6 style="font-size: 32px;" ${hiddenlogout}hidden> ${upuser}</h6>
        <a href="showLoginForm" ${hiddenlogin} style="font-size: 29px;"> > Войти < </a>
        <a href="logout" ${hiddenlogout}hidden style="font-size: 29px;"> > Выйти < </a> 
        <p style="border-style: groove; border-width: ${borderwidth}0px; border-color: #d9a34c;">${info}</p>
        <hr>
        <a href="addProductForm"> > Добавить новый товар < </a><br>
        <a href="listProducts"> > Список товаров < </a><br>
        <a href="editProductForm1"> > Изменить товар < </a><br>
        <hr>
        <a href="addPersonForm"> > Регистрация покупателя < </a><br>
        <a href="listPersons"> > Список покупателей < </a><br>
        <a href="editPersonForm1"> > Изменить данные покупателя < </a><br>
        <hr>
        <a href="buyProductForm"> > Купить товар < </a><br>      

    </body>
    
</html>
