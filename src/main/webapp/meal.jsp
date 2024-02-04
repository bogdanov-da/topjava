<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html lang="ru">
<head>
    <title>Your meal</title>
</head>
<body>
<h1>${param.action == "edit" ? "Edit meal" : "Add meal"}</h1>
<form method="post" action='meals' name="addMeal">
        <input type="hidden" name="id" value="${meal.id}">
        DateTime: <input type="datetime-local" name="dateTime" value="${meal.dateTime}" /> <br/>
        Description: <input type="text" name="description" value="${meal.description}" /> <br/>
        Calories: <input type="number" name="calories" value="${meal.calories}" /> <br/>
        <input type="submit" value="${param.action == "edit" ? "Update" : "Create"}"/>
        <input onclick="window.history.back()" type="button" value="Cancel"/>
</form>
</body>
</html>