<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<style type="text/css">
.column{
	text-align: center;
}
</style>
</head>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

		<!-- For login user -->
		<c:url value="/j_spring_security_logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>

			<h2>
				User : ${pageContext.request.userPrincipal.name} | <a
					href="javascript:formSubmit()"> Logout</a>
			</h2>
			
			<table>
				<caption><h2>My tasks</h2> </caption>
				<thead>
					<tr>
						<th >
					   		Task id
					   </th>
					   <th>
					   		Task name
					   </th>
					   <th>
					   		Project id
					   </th>
					    <th>
					   		Project name
					   </th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="task" items="${tasks}" >
						<tr>
							<td class="column">
								${task.id}
							</td>
							<td class="column">
								${task.name}
							</td>
							<td class="column">
								${task.project.id}
							</td>
							<td class="column">
								${task.project.name}
							</td>
						</tr>
					</c:forEach>
				
					
				
				</tbody>
				
			</table>
			
			
			


</body>
</html>