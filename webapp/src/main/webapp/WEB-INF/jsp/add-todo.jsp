<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
    <head>
        <link rel="stylesheet" href="webjars/bootstrap/5.2.3/css/bootstrap.min.css">
        <link rel="stylesheet" href="webjars/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css">
        <title>Add Todo</title>
    </head>

    <body>
        <div class="container">
            <h1>Welcome ${name}</h1>
            <hr>
            <h2>Add Todo</h2>
            <form:form method="post" modelAttribute="todo">
                <fieldset class="mb-3">
                    <form:label path="description">Description</form:label>
                    <form:input type="text" path="description" required="required"/>
                    <form:errors path="description" cssClass="text-warning"/>
                </fieldset>

                <fieldset class="mb-3">
                    <form:label path="targetDate">Target Date:</form:label>
                    <form:input type="text" path="targetDate" required="required"/>
                    <form:errors path="targetDate" cssClass="text-warning"/>
                </fieldset>

                <form:input path="id" type="hidden"/>
                <form:input path="done" type="hidden"/>
                <input class="btn btn-warning" type="submit" name="submit" id="submit"/>
            </form:form>
        </div>
        <script src="webjars/jquery/3.6.3/jquery.min.js"></script>
        <script src="webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>
        <script src="webjars/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js"></script>

        <script type="text/javascript">
        $('#targetDate').datepicker({
            format: 'yyyy-mm-dd',
        });
        </script>

    </body>
</html>