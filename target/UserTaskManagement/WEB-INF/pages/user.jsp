<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>User Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a User
</h1>

<c:url var="addAction" value="/user/add" ></c:url>

<form:form action="${addAction}" commandName="user">
<table>
	
	<tr>
		<td>
			<form:label path="username">
				<spring:message text="Username"/>
			</form:label>
		</td>
		<td>
			<form:input path="username" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="password">
				<spring:message text="Password"/>
			</form:label>
		</td>
		<td>
			<form:input type="password" path="password" />
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="enabled">
				<spring:message text="Enabled"/>
			</form:label>
		</td>
		<td>
			<form:checkbox  path="enabled" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty user.username}">
				<input type="submit"
					value="<spring:message text="Edit User"/>" />
			</c:if>
			<c:if test="${empty user.username}">
				<input type="submit"
					value="<spring:message text="Add User"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Users List</h3>
<c:if test="${!empty listUsers}">
	<table class="tg">
	<tr>
	    <th width="80">User enabled</th>
		<th width="120">User Name</th>
		<th width="120">User Password</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listUsers}" var="user">
		<tr>
			<td><input type="checkbox" ${user.enabled?"checked":"" } disabled="disabled" /></td>
			
			<td>${user.username}</td>
			<td>${user.password}</td>
			<td><a href="<c:url value='/user/edit/${user.username}' />" >Edit</a></td>
			<td><a href="<c:url value='/user/remove/${user.username}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>

<a href="<c:url value='/projects' />">Projects page</a>
<br/>
<a href="<c:url value='/tasks' />">Tasks page</a>
<br />
<a href="<c:url value='/admin' />" >Admin page</a>
</body>
</html>
