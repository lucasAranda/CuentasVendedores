<%--
  Created by IntelliJ IDEA.
  User: maquina0
  Date: 04/08/2016
  Time: 01:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Create an account</title>

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="${contextPath}/resources/css/common.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>

<div class="container">

    <spring:url value="/User/registration" var="profileActionUrl" />

    <form:form method="POST" modelAttribute="userForm" class="form-signin" action="${profileActionUrl}">

        <c:choose>
            <c:when test="${not empty id}">
                <h2 class="form-signin-heading">Editar Usuario</h2>
            </c:when>
            <c:otherwise>
                <h2 class="form-signin-heading">Crear Usuario</h2>
            </c:otherwise>
        </c:choose>

        <form:hidden path="id" />

        <spring:bind path="nombre">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="nombre" class="form-control" placeholder="Nombre del Usuario"
                            autofocus="true"></form:input>
                <form:errors path="nombre"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="username">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="username" class="form-control" placeholder="Usuario"></form:input>
                <form:errors path="username"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="role">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:select path="role" class="form-control" id="role" placeholder="Permiso" multiple="false">
                        <form:option value="NONE" label="Seleccione Permiso"/>
                        <c:forEach items="${roleList}" var="role">
                            <form:option value="${role.name}"/>
                        </c:forEach>

                    </form:select>
                <form:errors path="role" class="control-label"/>
            </div>
        </spring:bind>

        <spring:bind path="sheet">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="text" path="sheet" class="form-control" placeholder="Hoja de Excel"></form:input>
                <form:errors path="sheet"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="password">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="password" class="form-control" placeholder="Password"></form:input>
                <form:errors path="password"></form:errors>
            </div>
        </spring:bind>

        <spring:bind path="passwordConfirm">
            <div class="form-group ${status.error ? 'has-error' : ''}">
                <form:input type="password" path="passwordConfirm" class="form-control"
                            placeholder="Repita Password"></form:input>
                <form:errors path="passwordConfirm"></form:errors>
            </div>
        </spring:bind>

        <button class="btn btn-lg btn-primary btn-block" type="submit">Guardar</button>
    </form:form>

</div>
<!-- /container -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>