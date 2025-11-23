<%@include file="header.jsp" %>

<div class="container mt-3">
    <div class="card">
        <div class="card-body">
            <h3>${material.id == 0 || material.id == null ? "Nuevo" : "Editar"} Material</h3>
            <hr />

            <form action="materiales" method="post">
                <div class="mb-3">
                    <label>Nombre:</label>
                    <input name="nombre" value="${material.nombre}" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label>Unidad de Medida:</label>
                    <input name="unidadMedida" value="${material.unidadDeMedida}" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label>Costo Unitario (S/):</label>
                    <input name="costoUnitario" type="number" step="0.01" value="${material.costoUnitario}" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label>Stock Actual:</label>
                    <input name="stockActual" type="number" step="1" value="${material.stockActual}" class="form-control" required>
                </div>

                <input type="hidden" name="id" value="${material.id}">
                <input type="hidden" name="accion" value="guardar">

                <button class="btn btn-success btn-sm">
                    <i class="fa fa-save"></i> Guardar
                </button>
                <a href="<%=path%>/materiales?accion=listar" class="btn btn-dark btn-sm">
                    <i class="fa fa-arrow-left"></i> Ver Materiales
                </a>
            </form>
        </div>
    </div>
</div>

<%@include file="footer.jsp" %>
