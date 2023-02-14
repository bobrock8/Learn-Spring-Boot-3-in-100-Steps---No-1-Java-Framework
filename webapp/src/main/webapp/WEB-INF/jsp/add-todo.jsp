<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <link rel="stylesheet" href="webjars/bootstrap/5.2.3/css/bootstrap.min.css">
        <title>Add Todo</title>
    </head>

    <body>
        <div class="container">
            <h1>Welcome ${name}</h1>
            <hr>
            <h2>Add Todo</h2>
            <form method="post">
                <label for="description">Description</label>
                <input id="description" type="text" name="description" required>
                <input class="btn btn-warning" type="submit" name="submit" id="submit">
            </form>
        </div>
        <script src="webjars/jquery/3.6.3/jquery.min.js"></script>
        <script src="webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>

    </body>
</html>