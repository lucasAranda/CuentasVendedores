<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<jsp:include page="../admin/header.jsp"/>
<jsp:include page="../admin/menu.jsp"/>

<div class="main">
    <!--<div id="wrapper" style="min-height: 944px;">-->
        <div id="page-content-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">Usuarios</h1>
                    <sub><a class="btn btn-info" role="button"
                            href="${pageContext.request.contextPath}/User/registration">Agregar
                        Usuario</a></sub>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <br>
            <br>
            <table id="table_users" class="table table-striped table-bordered dt-responsive" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>Usuario</th>
                    <th>Nombre</th>
                    <th>Hoja de Excel</th>
                    <th>Permiso</th>
                    <th>Editar / Borrar</th>
                </tr>
                </thead>
            </table>
        </div>
    <!--</div>-->
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Edit record


        var data = eval('${userList}');
        var table = $('#table_users').DataTable({
            "aaData": data,
            "aoColumns": [
                {"mData": "username"},
                {"mData": "nombre"},
                {"mData": "sheet"},
                {"mData": "role"},
                {
                    "mData": "id",

                    "mRender": function (data, type, full) {
                        return '<a class="btn btn-info" role="button" href="${pageContext.request.contextPath}/User/edit/' + data + '"> Editar </a>  <a class="btn btn-warning" role="button" href="${pageContext.request.contextPath}/User/delete/' + data + '">Borrar</a>';
                    }
                }]
        });
    });
</script>

<jsp:include page="../admin/footer.jsp"/>
