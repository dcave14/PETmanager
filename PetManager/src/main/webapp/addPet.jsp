<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Pet</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css">
</head>
<body>
    <h1>Add a New Pet</h1>
    <form action="AddPetServlet" method="post">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div>
            <label for="species">Species:</label>
            <input type="text" id="species" name="species" required>
        </div>
        <div>
            <label for="breed">Breed:</label>
            <input type="text" id="breed" name="breed">
        </div>
        <div>
            <label for="age">Age:</label>
            <input type="number" id="age" name="age" min="0" required>
        </div>
        <div>
			<label for="ownerId">Owner:</label>
			<select name="ownerId" id="ownerId" required>
			    <c:forEach items="${owners}" var="owner">
			        <option value="${owner.ownerId}">${owner.name}</option>
			    </c:forEach>
			</select>
        </div>
        <div>
            <button type="submit">Add Pet</button>
        </div>
    </form>
    <a href="addOwner.jsp" class="btn btn-primary">Add New Owner</a> <br>
    <a href="listPets">Back to Pets List</a><br>
    <a href="index.jsp">Home</a>    
    
</body>
</html>
