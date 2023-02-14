<html>
    <head>
        <title>Login Page</title>
    </head>

    <body>
        <h1>Login Page</h1>
        <form method="post">
            <label for="name">Name</label>
            <input id="name" type="text" name="name">
            <label for="password">Password</label>
            <input id="password" type="password" name="password">
            <input type="submit" name="submit" id="submit">
        </form>
        <pre>${errorMessage}</pre>
    </body>
</html>