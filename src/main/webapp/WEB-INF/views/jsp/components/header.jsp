<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Helmes</title>

<spring:url value="/resources/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
</head>

<spring:url value="/people" var="urlHome" />
<spring:url value="/people/add" var="urlAddPerson" />

<nav class="navbar navbar-default">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">People by sectors</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${urlAddPerson}">Add Person</a></li>
			</ul>
		</div>
	</div>
</nav>
