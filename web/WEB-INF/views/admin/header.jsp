<%--
  Created by IntelliJ IDEA.
  User: maquina0
  Date: 01/09/2016
  Time: 12:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=2.0">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="${contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="<c:url value='/resources/css/sidebar.css' />" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/dataTables.bootstrap.css' />">
    <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/responsive.bootstrap.css' />">
    <spring:url value="/resources/js/jquery-1.12.3.min.js" var="jqueryJs" />
        <script src="${jqueryJs}"></script>
    <spring:url value="/resources/js/jquery.dataTables.js" var="datatable" />
        <script src="${datatable}"></script>
    <spring:url value="/resources/js/dataTables.bootstrap.js" var="databootstrap" />
        <script src="${databootstrap}"></script>
    <spring:url value="/resources/js/dataTables.responsive.js" var="dataresponsive" />
        <script src="${dataresponsive}"></script>
    <spring:url value="/resources/js/responsive.bootstrap.js" var="respboots" />
        <script src="${respboots}"></script>

    <title>Admin page</title>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
    <script src="${contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/js/sidebar.js"></script>
</head>
