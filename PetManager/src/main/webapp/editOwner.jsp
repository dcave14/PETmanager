<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Owner</title>
</head>
<body>
    <h1>Edit Owner</h1>
    <form action="EditOwnerServlet" method="post">
        <input type="hidden" name="ownerId" value="${owner.ownerId}">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${owner.name}" required>
        </div>
        <div>
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" value="${owner.address}" required>
        </div>
        <div>
            <label for="phoneNumber">Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" value="${owner.phoneNumber}" required>
        </div>
        <div class="form-group">
            <button type="submit">Update Owner</button>
        </div>
    </form>
    <a href="listOwners">Back to Owners List</a>
</body>
</html>
