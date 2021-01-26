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
        <title> ~ Изменить книгу ~ </title>
    </head>
    <body>
                <div><strong><a style="font-size: 42px; text-decoration: none; color: #d9a34c;-webkit-text-stroke: 1px black;" href="index.jsp"><<< Магазин Георга Лаабе >>></a></strong></div>
        <h6 style="font-size: 32px;" ${hiddenlogout}hidden> ${upuser}</h6>
        <a href="logout" ${hiddenlogout}hidden style="font-size: 29px;"> > Выйти < </a> 
        <hr><h1> ~ Изменить атрибуты товара ~ </h1>
        <p style="border-style: groove; border-width: ${borderwidth}0px; border-color: #d9a34c;">${info}</p>
        <form action="editProduct" method="POST">
            <input type="hidden" name="productId" value="${product.id}">
            Название: <input type="text" name="name" value="${product.name}"><br>
            Цена: <input type="number" min="1" name="price" value="${product.price}"><br>
           <br><input type="submit" name="submit" value="Отправить"><br>
        </form>
    </body>
</html>
