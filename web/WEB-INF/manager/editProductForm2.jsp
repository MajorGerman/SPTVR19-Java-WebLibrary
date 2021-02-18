<h2 class="display-5"> Изменить товар </h2> 
<form action="editProduct" method="POST">
    <input type="hidden" name="productId" value="${product.id}">
    Название: <input type="text" name="name" value="${product.name}"><br>
    Цена: <input type="number" min="1" name="price" value="${product.price}"><br>
   <br><input type="submit" name="submit" value="Отправить"><br>
</form>
