<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
     String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Administrador</title>
         <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="bg-light d-flex align-items-center justify-content-center vh-100">
        <div class="card shadow p-4 "  style="width: 100%; max-width: 400px; border-radius: 15px;">
            <h3 class="text-center mb-4">Iniciar Sesión</h3>
            <form action="<%=path%>/acceder" method="POST">
                <div class="mb-3">
                    <label class="form-label">Usuario</label>
                    <input type="text" class="form-control" name="username" placeholder="Ingresa tu usuario" required>
                </div>
                <div class="mb-3">
                    <label class="form-label">Contraseña</label>
                    <input type="password" class="form-control" name="password" placeholder="Ingresa tu contraseña" required>
                </div>
                
                <button type="submit" class="btn btn-primary w-100">Ingresar</button>
            </form>           
        </div>
    </body>
</html>

