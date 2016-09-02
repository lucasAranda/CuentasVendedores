<%--
  Created by IntelliJ IDEA.
  User: maquina0
  Date: 01/09/2016
  Time: 12:50 PM
  To change this template use File | Settings | File Templates.
--%>
<body>
<div id="wrapper">

    <!-- Sidebar -->
    <div id="sidebar-wrapper">
        <ul class="sidebar-nav">
            <li class="sidebar-brand">
                <a href="#">
                    ${pageContext.request.userPrincipal.name}
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/User/">Usuarios</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/AccountInfo/">Estados de Cuenta</a>
            </li>

        </ul>
    </div>
    <!-- /#sidebar-wrapper -->
</div>