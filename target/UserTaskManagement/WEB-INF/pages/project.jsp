<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Project Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add a Project
</h1>

<c:url var="addAction" value="/project/add" ></c:url>

<form:form action="${addAction}" commandName="project">
<table>
	<c:if test="${!empty project.name}">
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
		<td colspan="2">
			<c:if test="${!empty project.name}">
				<input type="submit"
					value="<spring:message text="Edit Project"/>" />
			</c:if>
			<c:if test="${empty project.name}">
				<input type="submit"
					value="<spring:message text="Add Project"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Projects List</h3>
<c:if test="${!empty listProjects}">
	<table class="tg">
	<tr>
		<th width="80">Project ID</th>
		<th width="120">Project Name</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listProjects}" var="project">
		<tr>
			<td>${project.id}</td>
			<td>${project.name}</td>
			<td><a href="<c:url value='/project/edit/${project.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/project/remove/${project.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
<a href="<c:url value='/tasks' />" >Tasks page</a>
<br />
<a href="<c:url value='/admin' />" >Admin page</a>

</body>
</html>
