<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    // Invalida la sesión
    session.invalidate();
    // Redirige al login
    response.sendRedirect(path);
%>
