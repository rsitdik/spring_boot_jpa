<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>

<%--<style>--%>
<%--    table, th, td {--%>
<%--        border: 1px solid black;--%>
<%--        padding: 10px;--%>
<%--        margin: 5px;--%>
<%--    }--%>

<%--    input[type=text] {--%>
<%--        width: 15%;--%>
<%--        padding: 12px 15px;--%>
<%--        margin: 8px 0;--%>
<%--        box-sizing: border-box;--%>
<%--    }--%>
<%--</style>--%>


<h1>Employees table : </h1>
${employees}

<%--<table>--%>
<%--    <thead>--%>
<%--    <tr>--%>
<%--        <th>#</th>--%>
<%--        <th>ID</th>--%>
<%--        <th>Name</th>--%>
<%--        <th>Position</th>--%>
<%--        <th>Phone</th>--%>
<%--        <th>Action</th>--%>
<%--    </tr>--%>
<%--    </thead>--%>
<%--    <tbody>--%>
<%--    <c:forEach items="${employees}" var="employee" varStatus="status">--%>
<%--        <tr>--%>
<%--            <td>${status.index + 1}</td>--%>
<%--            <td>${employee.id}</td>--%>
<%--            <td>${employee.name}</td>--%>
<%--            <td>${employee.position}</td>--%>
<%--            <td>${employee.phone}</td>--%>
<%--            <td><a href="/employee/remove/${employee.id}">Remove</a></td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--    </tbody>--%>
<%--</table>--%>


<br/>
<h2>To add new employee press "Add employee" button</h2>
<form method="post" action="${pageContext.request.contextPath}/employee/add">
    <label for="name">Employee name</label>
    <input type="text" name="name" id="name">
    <br/>
    <br/>
    <label for="position">Employee position</label>
    <input type="text" name="position" id="position">
    <br/>
    <br/>
    <label for="phone">Employee phone</label>
    <input type="text" name="phone" id="phone">
    <br/>
    <input type="submit" value="Add employee">
</form>
<br/>
<form method="get" action="${pageContext.request.contextPath}/employee/remove">
    <label for="id">Remove employee by id</label>
    <input type="text" name="id" id="id">
    <input type="submit" value="Remove employee">
</form>
<br/>
<form method="post" action="${pageContext.request.contextPath}/employee/findByName">
    Name: <input type="text" name="name">
    <input type="submit" value="Search by name">
</form>
<br/>
<form method="post" action="${pageContext.request.contextPath}/employee/findByNameAndPosition">
    Name: <input type="text" name="name">
    Position: <input type="text" name="position">
    <input type="submit" value="Search by name and position">
</form>
<br/>
<form method="post" action="${pageContext.request.contextPath}/employee/findByNameAndPhone">
    Name: <input type="text" name="name">
    Phone: <input type="text" name="phone">
    <input type="submit" value="Search by name and phone">
</form>
<br/>
<a href="/logout">Logout</a>
<br/>
<a href="/">Home</a>
</body>
</html>
