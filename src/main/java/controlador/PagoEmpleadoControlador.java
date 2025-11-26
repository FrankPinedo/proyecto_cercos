package controlador;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import modelo.DTO.EmpleadoDTO;
import modelo.DTO.PagoEmpleadoDTO;
import servicio.EmpleadoService;
import servicio.PagoEmpleadoService;

@WebServlet(name = "pago-empleado", urlPatterns = {"/pago-empleado"})
public class PagoEmpleadoControlador extends HttpServlet {

    private final PagoEmpleadoService pagoEmpleadoService = new PagoEmpleadoService();
    private final EmpleadoService empleadoService = new EmpleadoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null || accion.equals("listar")) {
            List<PagoEmpleadoDTO> pagos = pagoEmpleadoService.listar();
            request.setAttribute("pagos", pagos);
            request.getRequestDispatcher("vista/pago-empleado.jsp").forward(request, response);

        } else if (accion.equals("nuevo")) {
            List<EmpleadoDTO> empleados = empleadoService.listar();
            request.setAttribute("empleados", empleados);
            request.getRequestDispatcher("vista/nuevo-pago-empleado.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int empleadoId = Integer.parseInt(request.getParameter("empleado_id"));
            double sueldoBase = Double.parseDouble(request.getParameter("sueldo_base"));
            boolean tieneExtras = request.getParameter("tiene_extras") != null;

            int horasExtras = 0;
            double pagoPorHora = 0;

            if (tieneExtras) {
                horasExtras = Integer.parseInt(request.getParameter("horas_extras"));
                pagoPorHora = Double.parseDouble(request.getParameter("pago_por_hora"));
            }

            PagoEmpleadoDTO p = new PagoEmpleadoDTO();
            p.setEmpleadoId(empleadoId);
            p.setFechaPago(new Date());
            p.setSueldoBase(sueldoBase);
            p.setHorasExtras(horasExtras);
            p.setPagoPorHora(pagoPorHora);
            p.setPagoTotal(pagoEmpleadoService.calcularPagoTotal(sueldoBase, horasExtras, pagoPorHora));

            boolean exito = pagoEmpleadoService.registrar(p);

            if (exito) {
                request.getSession().setAttribute("success", "Pago registrado correctamente");
            } else {
                request.getSession().setAttribute("error", "No se pudo registrar el pago");
            }

            response.sendRedirect("pago-empleado?accion=listar");

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Datos numéricos inválidos en el formulario.");
            request.getRequestDispatcher("vista/nuevo-pago-empleado.jsp").forward(request, response);
        }
    }
}
