
<%@include file="header.jsp" %>

<div class="container mt-4">
    
    <a href="<%=path%>/empleados?accion=agregar" class="btn btn-success mb-3 text-white text-decoration-none">
        <i class="fas fa-user-plus me-1"></i> Agregar Empleado
    </a>

    <%@include file="alertas.jsp" %>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
            <tr>
                <th>Nombres</th>
                <th>Apellidos</th>
                <th>DNI</th>
                <th>Cargo</th>
                <th>Sueldo Base</th>
                <th>Editar</th>
                <th>Elimina</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${empleados}" var="item">
                <tr>
                    <td>${item.nombre}</td>
                    <td>${item.apellidos}</td>
                    <td>${item.dni}</td>
                    <td>${item.cargo}</td>
                    <td>S/. ${item.sueldoBase}</td>
                    <td>
                        <a href="<%=path%>/empleados?accion=editar&id=${item.id}" class="btn btn-primary btn-sm">
                            <i class="fas  fa-edit"></i>
                        </a>                
                    </td>
                    <td>
                        <a href="<%=path%>/empleados?accion=eliminar&id=${item.id}" 
                           onclick="return confirm('Estas seguro que quieres eliminar el empleado ${item.nombre} ${item.apellidos}')"
                           class="btn btn-danger btn-sm">
                            <i class="fas fa-trash-alt"></i>
                        </a>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="footer.jsp" %>