
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="header.jsp"%>

<div class="container mt-4">
    <a href="<%=path%>/pago-empleado?accion=nuevo" 
       class="btn btn-success mb-3 text-white text-decoration-none">
        <i class="fas fa-plus me-1"></i> Registrar Pago
    </a>

    <%@include file="alertas.jsp" %>

    <table class="table table-bordered table-hover">
        <thead class="table-dark">
            <tr>
                <th>Empleado</th>
                <th>Fecha de Pago</th>
                <th>Sueldo Base (S/)</th>
                <th>Horas Extras</th>
                <th>Pago por Horas Extras</th>
                <th>Total Pagado (S/)</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="p" items="${pagos}">
                <tr>
                    <td>${p.nombreEmpleado}</td>
                    <td>${p.fechaPago}</td>
                    <td>S/. ${p.sueldoBase}</td>
                    <td>${p.horasExtras == 0 ? '0' : p.horasExtras}</td>
                    <td>S/. ${p.pagoPorHora == 0 ? '0.0' : p.pagoPorHora}</td>
                    <td><strong>S/. ${p.pagoTotal}</strong></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="footer.jsp"%>
