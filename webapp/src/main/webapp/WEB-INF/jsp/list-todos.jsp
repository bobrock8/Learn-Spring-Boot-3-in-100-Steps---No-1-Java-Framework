<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
        <div class="container">
            <h1>Welcome ${name}</h1>
            <hr>
            <h2>List of your Todos</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th>Description</th>
                        <th>Target Date</th>
                        <th>Is Done</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                 <tbody>
                     <c:forEach items="${todos}" var="todo">
                        <tr>
                            <td>${todo.description}</td>
                            <td>${todo.targetDate}</td>
                            <td>${todo.done}</td>
                            <td><a class="btn btn-danger" href="delete-todo?id=${todo.id}">Delete</a></td>
                            <td><a class="btn btn-info" href="update-todo?id=${todo.id}">Update</a></td>
                         </tr>
                     </c:forEach>
                </tbody>
            </table>
            <a href="add-todo" class="btn btn-success">Add Todo</a>
        </div>
<%@ include file="common/footer.jspf" %>