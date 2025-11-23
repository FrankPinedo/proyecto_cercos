<%@include file="header.jsp" %>

<!-- Page Heading -->
<h1 class="h3 mb-4 text-gray-800">Página en Construcción</h1>

<div class="row justify-content-center">
    <div class="col-xl-6 col-md-8">
        <div class="card shadow mb-4">
            <div class="card-header py-3">
                <h6 class="m-0 font-weight-bold text-warning">En Desarrollo</h6>
            </div>
            <div class="card-body text-center">
                <i class="fas fa-hard-hat fa-5x text-warning mb-4"></i> <!-- Icono de construcción -->
                <h5 class="card-title">Esta sección está en construcción</h5>
                <p class="card-text text-muted">
                    Estamos trabajando duro para completar esta funcionalidad. Por favor, regresa pronto o verifica el dashboard principal.
                </p>
                <a href="<%=path%>/vista/index.jsp" class="btn btn-primary btn-icon-split">
                    <span class="icon text-white-50"><i class="fas fa-home"></i></span>
                    <span class="text">Volver al Dashboard</span>
                </a>
            </div>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>