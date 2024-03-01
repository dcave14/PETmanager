<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Pets</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css">
</head>
<body>
    <h1>Pets List</h1>
    <a href="AddPetServlet">Add New Pet</a>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Species</th>
            <th>Breed</th>
            <th>Age</th>
            <th>Owner</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="pet" items="${pets}">
            <tr>
                <td>${pet.petId}</td>
                <td>${pet.name}</td>
                <td>${pet.species}</td>
                <td>${pet.breed}</td>
                <td>${pet.age}</td>
                <td>${pet.owner.name}</td>
                <td>
                    <a href="EditPetServlet?petId=${pet.petId}">Edit</a> |
                    <a href="DeletePetServlet?petId=${pet.petId}" onclick="return confirm('Are you sure?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br>
    <a href="index.jsp">Home</a>   
</body>
</html>