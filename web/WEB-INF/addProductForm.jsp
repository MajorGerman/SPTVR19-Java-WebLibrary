<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link type="text/css" rel="stylesheet" href="style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> ~ Добавить товар ~ </title>
    </head>
    <body>
        <div><strong><a style="font-size: 42px; text-decoration: none; color: #d9a34c;-webkit-text-stroke: 1px black;" href="index.jsp"><<< Магазин Георга Лаабе >>></a></strong></div>
        <h6 style="font-size: 32px;" ${hiddenlogout}hidden> ${upuser}</h6>
        <a href="logout" ${hiddenlogout}hidden style="font-size: 29px;"> > Выйти < </a> 
        <hr><h1> ~ Добавить товар ~</h1>
        <p style="border-style: groove; border-width: ${borderwidth}0px; border-color: #d9a34c;">${info}</p>
        <form action="addProduct" method="POST" enctype="multipart/form-data">
            Название: <input type="text" name="name" value="${name}"><br>
            Цена: <input type="number" min="1" name="price" value="${price}"><br>
            Обложка: <input type="file" name="file"><br>
            Описание: <input type="text" name="description" value="${desription}"><br>
            <input type="submit" name="submit" value="Отправить"><br>
        </form>
    </body>
</html>
