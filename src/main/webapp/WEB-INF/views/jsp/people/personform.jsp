<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">

<jsp:include page="../components/header.jsp" />

<div class="container">

    <c:choose>
        <c:when test="${personForm['new']}">
            <h1>Please enter your name and pick the sectors you are currently involved in.</h1>
        </c:when>
        <c:otherwise>
            <h1>Edit details</h1>
        </c:otherwise>
    </c:choose>
    <br />

    <spring:url value="/people" var="personActionUrl"/>

    <form:form class="form-horizontal"  method="post" modelAttribute="personForm" action="${personActionUrl}">

        <form:hidden path="id" />

        <spring:bind path="firstName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">First name: </label>
                <div class="col-sm-10">
                    <form:input path="firstName" type="text" name="firstName" id="firstName" />
                    <form:errors path="firstName" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="lastName">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Last name: </label>
                <div class="col-sm-10">
                    <form:input path="lastName" type="text" name="lastName" id="lastName" />
                    <form:errors path="lastName" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="sectors">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Sectors: </label>
                <div class="col-sm-5">
                    <form:select path="sectors" items="${sectorsList}" multiple="true" size="5" class="form-control" />
                    <form:errors path="sectors" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <spring:bind path="acceptTerms">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <label class="col-sm-2 control-label">Agree to terms</label>
                <div class="col-sm-10">
                    <form:checkbox path="acceptTerms" id="terms" element="label class='checkbox-inline'"/>
                    <br />
                    <form:errors path="acceptTerms" class="control-label" />
                </div>
            </div>
        </spring:bind>

        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${personForm['new']}">
                        <button type="submit" class="btn btn-primary pull-right">Save</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-primary pull-right">Update</button>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </form:form>
</div>

</body>
</html>
