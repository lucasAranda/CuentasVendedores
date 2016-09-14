<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<jsp:include page="../admin/header.jsp"/>
<jsp:include page="../admin/menu.jsp"/>
<!-- Page Content -->

<div class="main">
    <!--<div id="wrapper" style="min-height: 944px;">-->
        <div id="page-content-wrapper">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12">
                        <c:if test="${pageContext.request.userPrincipal.name != null}">
                            <form id="logoutForm" method="POST" action="${contextPath}/logout">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            </form>

                            <h2>Bienvenido a la Pagina de Adninistracion ${pageContext.request.userPrincipal.name} | <a style="cursor: pointer;"
                                    onclick="document.forms['logoutForm'].submit()">Salir</a></h2>

                        </c:if>

                        <!--<p>Make sure to keep all page content within the <code>#page-content-wrapper</code>.</p>
                        <a href="#menu-toggle" class="btn btn-default" id="menu-toggle">Toggle Menu</a>-->
                    </div>
                </div>
            </div>
        </div>
        <!-- /#page-content-wrapper -->
    <!--</div>-->
</div>
<jsp:include page="../admin/footer.jsp"/>