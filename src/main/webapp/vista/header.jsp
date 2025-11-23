<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();

    if (session == null || session.getAttribute("id") == null) {
        response.sendRedirect(path + "/vista/login.jsp?denegado");
        return;
    }

%>

<!DOCTYPE html>    
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <link href="<%=path%>/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
        <link href="<%=path%>/css/sb-admin-2.min.css" rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.1/font/bootstrap-icons.css" rel="stylesheet">


</head>
<body id="page-top">

    <div id="wrapper">

        <ul class="navbar-nav bg-dark sidebar sidebar-dark accordion" id="accordionSidebar">

            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="<%=path%>/vista/index.jsp">
                <div class="sidebar-brand-text mx-2"> Sistema de Cercos</div>
            </a>

            <hr class="sidebar-divider my-0">

            <li class="nav-item active">
                <a class="nav-link" href="<%=path%>/vista/index.jsp">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Panel Principal</span></a>
            </li>

            <hr class="sidebar-divider">

            <div class="sidebar-heading">
                Gestión
            </div>

            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseEmpleados"
                   aria-expanded="true" aria-controls="collapseEmpleados">
                    <i class="fas fa-fw fa-users"></i>
                    <span>Empleados</span>
                </a>
                <div id="collapseEmpleados" class="collapse" aria-labelledby="headingEmpleados" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones:</h6>
                        <a class="collapse-item" href="<%=path%>/empleados?accion=listar">Ver Empleados</a>
                        <a class="collapse-item" href="<%=path%>/empleados?accion=agregar">Agregar Empleado</a>
                        <a class="collapse-item" href="<%=path%>/pago-empleado?accion=listar">Ver Pagos</a>
                        <a class="collapse-item" href="<%=path%>/pago-empleado?accion=nuevo">Hacer Pagos</a>
                    </div>
                </div>
            </li>

            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseMateriales"
                   aria-expanded="true" aria-controls="collapseMateriales">
                    <i class="fas fa-fw fa-box"></i>
                    <span>Materiales</span>
                </a>
                <div id="collapseMateriales" class="collapse" aria-labelledby="headingMateriales"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones:</h6>
                        <a class="collapse-item" href="<%=path%>/materiales?accion=listar">Ver Materiales</a>
                        <a class="collapse-item" href="<%=path%>/materiales?accion=agregar">Agregar Material</a>
                    </div>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseProductos"
                   aria-expanded="true" aria-controls="collapseProductos">
                    <i class="fas fa-fw fa-cube"></i>
                    <span>Productos</span>
                </a>
                <div id="collapseProductos" class="collapse" aria-labelledby="headingProductos"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones:</h6>
                        <a class="collapse-item" href="<%=path%>/vista/en-proceso.jsp">Ver Productos</a>
                        <a class="collapse-item" href="<%=path%>/vista/en-proceso.jsp">Agregar Producto</a>
                    </div>
                </div>
            </li>

            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseVentas"
                   aria-expanded="true" aria-controls="collapseVentas">
                    <i class="fas fa-fw fa-shopping-cart"></i>
                    <span>Ventas</span>
                </a>
                <div id="collapseVentas" class="collapse" aria-labelledby="headingVentas"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Acciones:</h6>
                        <a class="collapse-item" href="<%=path%>/vista/en-proceso.jsp">Ver Ventas</a>
                        <a class="collapse-item" href="<%=path%>/vista/en-proceso.jsp">Registrar Venta</a>
                    </div>
                </div>
            </li>

            <hr class="sidebar-divider">

        </ul>

        <div id="content-wrapper" class="d-flex flex-column">

            <div id="content">

                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                    <h1 class="h3 mb-0 text-gray-800"><strong>Panel de Administrador</strong></h1>
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">

                            <a class="nav-link dropdown-toggle" href="<%=path%>/vista/en-proceso.jsp" id="userDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Administrador</span>
                                <img class="img-profile rounded-circle"
                                     src="<%=path%>/img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                 aria-labelledby="userDropdown">                                    

                                <a class="dropdown-item" href="<%=path%>/vista/logout.jsp" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Cerrar Sesión
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>

