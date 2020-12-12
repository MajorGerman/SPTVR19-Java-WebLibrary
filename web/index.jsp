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
        <p style="border-style: groove; border-width: ${borderwidth}0px; border-color: #d9a34c;">${info}</p>
        <a href="addProductForm"> > Добавить новый товар < </a><br>
        <a href="listProducts"> > Список товаров < </a><br>
        <a href="editProductForm1"> > Изменить товар < </a><br><br>
        <a href="addPersonForm"> > Добавить нового покупателя < </a><br>
        <a href="listPersons"> > Список покупателей < </a><br>
        <a href="editPersonForm1"> > Изменить данные покупателя < </a><br><br>
        <a href="buyProductForm"> > Купить товар < </a><br>      

    </body>
    
</html>
