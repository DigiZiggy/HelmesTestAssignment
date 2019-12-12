<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<title>Helmes</title>

<spring:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
</head>

<spring:url value="/people" var="urlHome" />
<spring:url value="/people/add" var="urlAddPerson" />

<nav class="navbar navbar-inverse ">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">People by sectors</a>
		</div>
		<div id="navbar">
			<ul class="nav navbar-nav navbar-right">
				<li class="active"><a class="btn btn-default" href="${urlAddPerson}">Add Person</a></li>
			</ul>
		</div>
	</div>
</nav>
