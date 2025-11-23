<%@include file="header.jsp" %>

<div class="container mt-4">
    <a href="<%=path%>/materiales?accion=agregar" class="btn btn-success mb-3 text-white text-decoration-none">
        <i class="fas fa-plus me-1"></i> Agregar Material
    </a>

    <%@include file="alertas.jsp" %>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
            <tr>
                <th>Nombre</th>
                <th>Unidad de Medida</th>
                <th>Costo Unitario (S/)</th>
                <th>Stock Actual</th>
                <th>Editar</th>
                <th>Eliminar</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="item" items="${materiales}">
                <tr>
                    <td>${item.nombre}</td>
                    <td>${item.unidadDeMedida}</td>
                    <td>S/. ${item.costoUnitario}</td>
                    <td>${item.stockActual}</td>
                    <td>
                        <a href="<%=path%>/materiales?accion=editar&id=${item.id}" class="btn btn-primary btn-sm">
                            <i class="fa fa-edit"></i>
                        </a>
                    </td>
                    <td>
                        <a href="<%=path%>/materiales?accion=eliminar&id=${item.id}" 
                           onclick="return confirm('¿Seguro que deseas eliminar ${item.nombre}?')" 
                           class="btn btn-danger btn-sm">
                            <i class="fa fa-trash"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="footer.jsp" %>
