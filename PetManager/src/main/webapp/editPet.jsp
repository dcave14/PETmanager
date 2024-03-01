<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Pet</title>
</head>
<body>
    <h1>Edit Pet</h1>
    <form action="EditPetServlet" method="post">
        <input type="hidden" name="petId" value="${pet.petId}">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="${pet.name}" required>
        </div>
        <div>
            <label for="species">Species:</label>
            <input type="text" id="species" name="species" value="${pet.species}" required>
        </div>
        <div>
            <label for="breed">Breed:</label>
            <input type="text" id="breed" name="breed" value="${pet.breed}">
        </div>
        <div>
            <label for="age">Age:</label>
            <input type="number" id="age" name="age" value="${pet.age}" min="0" required>
        </div>
        <div>
            <label for="owner">Owner:</label>
				<select name="ownerId">
				    <c:forEach items="${owners}" var="owner">
				        <option value="${owner.ownerId}" ${owner.ownerId == pet.owner.ownerId ? 'selected' : ''}>${owner.name}</option>
				    </c:forEach>
				</select>
        </div>
        <div class="form-group">
            <button type="submit">Update Pet</button>
        </div>
    </form>
    <a href="listPets">Back to Pets List</a>
</body>
</html>
