<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<jsp:include page="../admin/header.jsp"/>
<jsp:include page="../admin/menu.jsp"/>

<div class="main">
    <!--<div id="wrapper" style="min-height: 944px;">-->
        <div id="page-content-wrapper">
            <spring:url value="/AccountInfo/info" var="infoActionUrl"/>
            <div class="container">
                <h1>Resumen de Cuenta por Vendedor</h1>
                <form:form method="GET" modelAttribute="userForm" class="form-inline" action="${infoActionUrl}">
                    <div class="form-group">
                        <form:select path="sheet" class="form-control" id="vendedor" placeholder="Vendedor"
                                     multiple="false">
                            <form:option value="NONE" label="Seleccione Vendedor"/>
                            <form:options items="${lstVendedores}"/>

                        </form:select>
                    </div>
                    <button class="btn btn-primary" type="submit">Consultar Cuenta</button>
                </form:form>
            </div>
            <br>
            <br>
            <h2>${vendedor.nombre}</h2>
            <h3>Total a cancelar: ${vendedor.montoTotal}</h3>
            <h4>Total Hormicon: ${vendedor.totalHormicon} Total Insucon: ${vendedor.totalInsucon} Total
                Este: ${vendedor.totalEste} Total Uco: ${vendedor.totalUco}</h4>
            <!--<img class="dataTableExample" src="resources/images/JQueryDatatableandSpringMVC.png">-->
            <table id="table_users" class="display" cellspacing="0" width="100%" style="overflow-x:auto">
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
    <!--</div>-->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        console.log(${lstComprobante});
        var data = eval('${lstComprobante}');
        console.log(data);
        var table = $('#table_users').DataTable({
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

<jsp:include page="../admin/footer.jsp"/>