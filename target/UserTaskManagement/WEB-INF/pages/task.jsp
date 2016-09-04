<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Task Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a Task
</h1>

<c:url var="addAction" value="/task/add" ></c:url>

<form:form action="${addAction}" commandName="task">
<table>
	<c:if test="${!empty task.name}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="name">
				<spring:message text="Name"/>
			</form:label>
		</td>
		<td>
			<form:input path="name" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="user">
				<spring:message text="User"/>
			</form:label>
		</td>
		<td>
			<form:select path="user">
				<c:forEach items="${listUsers}" var="user">
					<form:option value="${user.username}">${user.username}</form:option>
				</c:forEach>		
			</form:select>
		</td>
	</tr>
	<tr>
		<td>
			<form:label path="project">
				<spring:message text="Project"/>
			</form:label>
		</td>
		<td>
			<form:select path="project">
				<c:forEach items="${listProjects}" var="project">
					<form:option value="${Integer.toString(project.id)}">${project.name}</form:option>
				</c:forEach>
			</form:select>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty task.name}">
				<input type="submit"
					value="<spring:message text="Edit Task"/>" />
			</c:if>
			<c:if test="${empty task.name}">
				<input type="submit"
					value="<spring:message text="Add Task"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Tasks List</h3>
<c:if test="${!empty listTasks}">
	<table class="tg">
	<tr>
		<th width="80">Task ID</th>
		<th width="120">Task Name</th>
		<th width="60">Task User Name</th>
		<th width="60">Task Project Name</th>		
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listTasks}" var="task">
		<tr>
			<td>${task.id}</td>
			<td>${task.name}</td>
			<td>${task.user.username}</td>
			<td>${task.project.name}</td>	
			<td><a href="<c:url value='/task/edit/${task.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/task/remove/${task.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
<a href="<c:url value='/admin' />" >Admin page</a>
</body>
</html>
