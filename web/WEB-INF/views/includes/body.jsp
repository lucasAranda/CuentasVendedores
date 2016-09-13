<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<body>
<div class="main">
    <div id="page-content-wrapper">
        <div class="container">
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <form id="logoutForm" method="POST" action="${contextPath}/logout">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>

                <h2>Welcome ${pageContext.request.userPrincipal.name} | <a
                        onclick="document.forms['logoutForm'].submit()">Logout</a>
                </h2>

            </c:if>
        </div>
        <h1>Resumen de Cuenta</h1>
        <h2>${vendedor.nombre}</h2>
        <h3>Total a cancelar: ${vendedor.montoTotal}</h3>
        <h4>Total Hormicon: ${vendedor.totalHormicon} Total Insucon: ${vendedor.totalInsucon} Total
            Este: ${vendedor.totalEste} Total Uco: ${vendedor.totalUco}</h4>
        <!--<img class="dataTableExample" src="resources/images/JQueryDatatableandSpringMVC.png">-->

        <table id="example" class="table table-striped table-bordered dt-responsive" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>Cliente</th>
                <th>Empresa</th>
                <th>Tipo de Comprobante</th>
                <th>Nro de Comprobante</th>
                <th>Fecha de Comprobante</th>
                <th>Importe ($)</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        console.log(${lstComprobante});
        var data = eval('${lstComprobante}');
        console.log(data);
        var table = $('#example').DataTable({
            "aaData": data,
            "aoColumns": [
                {"mData": "cliente"},
                {"mData": "empresa"},
                {"mData": "tipoComprobante"},
                {"mData": "numeroComprobante"},
                {"mData": "fechaComprobante"},
                {"mData": "importe"}
            ],
            "paging": true,
            "pageLength": 20,
            "ordering": true
        });
    });
</script>
</body>
</html>
