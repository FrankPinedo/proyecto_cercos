
<%@include file="header.jsp" %>

<div class="container mt-4">
    <h3 class="text-primary fw-bold mb-3">Registrar Pago de Empleado</h3>

    <form action="pago-empleado" method="post" class="card p-4 shadow-sm">

        <div class="mb-3">
            <label class="form-label fw-semibold">Empleado</label>
            <div class="input-group ">

                <select name="empleado_id" id="empleado" class="form-select border-primary input-group  "required>
                    <option value="" selected disabled>Seleccione un empleado...</option>
                    <c:forEach var="emp" items="${empleados}">
                        <option value="${emp.id}" data-sueldo="${emp.sueldoBase}">
                            ${emp.nombre} ${emp.apellidos}
                        </option>
                    </c:forEach>
                </select>   
            </div>
        </div>

        <div class="mb-3">
            <label class="form-label fw-semibold">Sueldo Base</label>
            <input type="number" step="0.01" class="form-control" id="sueldo_base" name="sueldo_base" readonly>
        </div>

        <div class="form-check mb-3">
            <input class="form-check-input" type="checkbox" id="tiene_extras" name="tiene_extras">
            <label class="form-check-label" for="tiene_extras">
                Incluir pago por horas extras
            </label>
        </div>

        <div id="extras" class="border rounded p-3 bg-light mb-3 " style="display:none;">
            <div class="row">
                <div class="col-md-6 mb-3">
                    <label class="form-label">Pago por Hora</label>
                    <input type="number" step="0.01" class="form-control" id="pago_por_hora" name="pago_por_hora" >
                </div>
                <div class="mb-3">
                    <label class="form-label fw-semibold">Horas Extras</label>
                    <input type="number" 
                           name="horas_extras" 
                           id="horas_extras" 
                           class="form-control" 
                           min="0" 
                           step="1" 
                           placeholder="Ingrese horas extras"
                           
                           >
                    <div class="form-text">Solo se permiten números enteros.</div>
                </div>

            </div>
        </div>

        <button type="submit" class="btn btn-primary w-100 fw-bold">
            <i class="fas fa-save me-2"></i> Registrar Pago
        </button>
    </form>
</div>

<script>
    document.getElementById("empleado").addEventListener("change", function () {
        const sueldo = this.options[this.selectedIndex].getAttribute("data-sueldo");
        document.getElementById("sueldo_base").value = sueldo || '';
    });

    document.getElementById("tiene_extras").addEventListener("change", function () {
        document.getElementById("extras").style.display = this.checked ? "block" : "none";
    });
</script>

<%@include file="footer.jsp" %>
