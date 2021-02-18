<h2 class="display-5"> Список пользователей </h2>
<ul class="list-group" name="persId" multiple="true">
        <c:forEach var="user" items="${listUsers}">
            <li class="list-group-item" value="${user.id}">${user.login} (${user.person.name} ${user.person.surname}, ${user.person.money}$)
                <form action="madeManager" method="POST">
                <input name="userId" value="${user.id}" hidden>                
                <button type="submit" class="btn btn-primary"> Сделать менеджером </button>
                </form>
            </li>
        </c:forEach>
    </ul>
    