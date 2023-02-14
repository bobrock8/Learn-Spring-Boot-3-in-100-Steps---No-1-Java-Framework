<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <title>List of Todos</title>
    </head>

    <body>
        <h1>Welcome ${name}</h1>
        <hr>

        <h2>List of your Todos</h2>
        <p>${todos}</p>
        <table>
            <thead>
                <tr>
                    <th>id</th>
                    <th>Description</th>
                    <th>Target Date</th>
                    <th>Is Done</th>
                </tr>
            </thead>
             <tbody>
                 <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.id}</td>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.done}</td>
                     </tr>
                 </c:forEach>
            </tbody>
        </table>
    </body>
</html>