<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Owner</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css">
</head>
<body>
    <h1>Add a New Owner</h1>
    <form action="AddOwnerServlet" method="post">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div>
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" required>
        </div>
        <div>
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" required>
        </div>
        <div class="form-group">
            <button type="submit">Add Owner</button>
        </div>
    </form>
    <a href="listOwners">Back to Owners List</a> <br>
    <a href="index.jsp">Home</a>   
</body>
</html>
