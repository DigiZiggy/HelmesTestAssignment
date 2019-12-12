<%--
  Created by IntelliJ IDEA.
  User: sigridnarep
  Date: 06/12/2019
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../components/header.jsp" />
<spring:url value="/people" var="urlHome" />

<div class="container">

    <c:if test="${not empty msg}">
        <div class="alert alert-${css} alert-dismissible" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <strong>${msg}</strong>
        </div>
    </c:if>

    <h1>Person Details</h1>
    <br />

    <div class="row">
        <label class="col-sm-2">ID</label>
        <div class="col-sm-10">${person.id}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">First name</label>
        <div class="col-sm-10">${person.firstName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Last name</label>
        <div class="col-sm-10">${person.lastName}</div>
    </div>

    <div class="row">
        <label class="col-sm-2">Sectors</label>
        <div class="col-sm-10">${person.sectors}</div>
    </div>

    <div class="row">
        <a class="btn-lg btn-primary pull-right" href="${urlHome}" role="button">Go to list</a>
    </div>
</div>

<jsp:include page="../components/footer.jsp" />

</body>
</html>
