package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import servicio.EmpleadoService;
import servicio.MaterialService;
import servicio.TipoCambioService;

@WebServlet(name = "dashboard", urlPatterns = {"/dashboard"})
public class DashboardControlador extends HttpServlet {

    private final EmpleadoService empleadoService = new EmpleadoService();
    private final MaterialService materialService = new MaterialService();
    private final TipoCambioService tipoCambioService = new TipoCambioService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute("totalEmpleados", empleadoService.listar().size());
        request.setAttribute("totalMateriales", materialService.listar().size());

        request.setAttribute("tipoCambioUsd",
                tipoCambioService.obtenerCambio("PEN", "USD").orElse(null));
        request.setAttribute("tipoCambioEur",
                tipoCambioService.obtenerCambio("PEN", "EUR").orElse(null));

        request.getRequestDispatcher("vista/index.jsp").forward(request, response);
    }
}
