<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<body>
<a href="/login">Login</a>
&emsp;
<a href="/pass-data">Pass data</a>
&emsp;
<a href="/employee/all">All employee</a>
&emsp;
<a href="/rest/employee/list">Rest employee list</a>
&emsp;
<a href="/logout">Logout</a>
<p>This view has name index.</p>
<br/>
<sec:authorize access="hasAuthority('ROLE_ADMIN')">
    <h1>Only admin can see this header.</h1>
</sec:authorize>

<sec:authorize access="hasAuthority('ROLE_USER')">
    <h1>Header for authenticated user!</h1>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <h2>Hello, <sec:authentication property="name"/></h2>
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    <h3><sec:authentication property="name"/></h3>
    <p><sec:authentication property="details"/></p>
    <p><sec:authentication property="principal"/></p>
</sec:authorize>

<%--<a href="/authorize-me">Authorize</a>--%>
<%--<br/>--%>
<%--<a href="/pass-data">Pass-data</a>--%>
<%--<br/>--%>
<%--<a href="/unauthorize-me">Unauthorize</a>--%>
</body>
</html>
