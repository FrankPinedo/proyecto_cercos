<%@include file="header.jsp" %>
<div class="container mt-3">
    <div class="card">
        <div class="card-body">
            <h3>${empleado.id == 0 || empleado.id == null ? "Nuevo" : "Editar"} Empleado</h3>
            <hr />

            <form action="empleados" method="post">
                <div class="mb-3">
                    <label>Nombres:</label>
                    <input value="${empleado.nombre}" name="nombres" type="text" maxlength="50" 
                           class="form-control" required="">
                </div>

                <div class="mb-3">
                    <label>Apellidos:</label>
                    <input value="${empleado.apellidos}" name="apellidos" type="text" maxlength="50" 
                           class="form-control" required="">
                </div>

                <div class="mb-3">
                    <label>DNI:</label>
                    <input value="${empleado.dni}" name="dni" type="text" 
                           class="form-control" required="">
                </div>

                <div class="mb-3">
                    <label>Cargo:</label>
                    <input value="${empleado.cargo}" name="cargo" type="text" 
                           class="form-control" required="">
                </div>

                <div class="mb-3">
                    <label>Sueldo Base:</label>
                    <input value="${empleado.sueldoBase}" name="sueldo" type="number" 
                           class="form-control" required="">
                </div>

                <div class="mb-3">
                    <input type="hidden" name="id" value="${empleado.id}">
                    <input type="hidden" name="accion" value="guardar">
                    <button class="btn btn-success btn-sm">
                        <i class="fa fa-save"></i> Guardar
                    </button>
                    <a href="<%=path%>/empleados?accion=listar" 
                       class="btn btn-dark btn-sm">
                        <i class="fa fa-arrow-left"></i> Ver Empleados
                    </a>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="footer.jsp" %>