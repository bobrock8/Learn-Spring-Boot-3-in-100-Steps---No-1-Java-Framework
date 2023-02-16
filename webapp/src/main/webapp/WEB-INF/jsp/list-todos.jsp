<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <link rel="stylesheet" href="webjars/bootstrap/5.2.3/css/bootstrap.min.css">
        <title>List of Todos</title>
    </head>

    <body>
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
        <script src="webjars/jquery/3.6.3/jquery.min.js"></script>
        <script src="webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>

    </body>
</html>