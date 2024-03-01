<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>List of Owners</title>
    <link rel="stylesheet" type="text/css" href="styles/main.css">
</head>
<body>
    <h1>Owners List</h1>
    <a href="AddOwnerServlet">Add New Owner</a>
    <table>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Address</th>
            <th>Phone Number</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="owner" items="${owners}">
            <tr>
                <td>${owner.ownerId}</td>
                <td>${owner.name}</td>
                <td>${owner.address}</td>
                <td>${owner.phoneNumber}</td>
                <td>
                    <a href="EditOwnerServlet?ownerId=${owner.ownerId}">Edit</a> |
                    <a href="DeleteOwnerServlet?ownerId=${owner.ownerId}" onclick="return confirm('Are you sure?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="index.jsp">Home</a>   
</body>
</html>
