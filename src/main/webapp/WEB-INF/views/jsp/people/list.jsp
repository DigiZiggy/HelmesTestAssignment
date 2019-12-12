<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../components/header.jsp" />

<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>

		<h1>All People</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>First name</th>
					<th>Last name</th>
					<th>Sectors</th>
				</tr>
			</thead>

			<c:forEach var="person" items="${people}">
				<tr>
					<td>
						${person.id}
					</td>
					<td>${person.firstName}</td>
					<td>${person.lastName}</td>
					<td><c:forEach var="sector" items="${person.sectors}" varStatus="loop">
						${sector}
    					<c:if test="${not loop.last}">,</c:if>
						</c:forEach></td>
					<td>
						<spring:url value="/people/${person.id}/delete" var="deleteUrl" />
						<spring:url value="/people/${person.id}/update" var="updateUrl" />

						<button class="btn btn-primary" onclick="location.href='${updateUrl}'">Edit</button>
						<button class="btn btn-danger" onclick="this.disabled=true;post('${deleteUrl}')">Delete</button></td>
				</tr>
			</c:forEach>
		</table>

	</div>

	<jsp:include page="../components/footer.jsp" />

</body>
</html>
