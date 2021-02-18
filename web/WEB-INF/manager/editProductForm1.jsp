<h2 class="display-5"> Изменить товар </h2>   
<form action="editProductForm2" method="POST">
    <select name="productId" multiple="true">
        <c:forEach var="product" items="${listProducts}">
            <option value="${product.id}">${product.name} (${product.price}$)</option>
        </c:forEach>
    </select>
    <br><input type="submit" value="Изменить">
</form>
