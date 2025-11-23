package controlador;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.DAO.EmpleadoDAO;
import modelo.DTO.EmpleadoDTO;
import modelo.DAO.PagoEmpleadoDAO;
import modelo.DTO.PagoEmpleadoDTO;

@WebServlet(name = "pago-empleado", urlPatterns = {"/pago-empleado"})
public class PagoEmpleadoControlador extends HttpServlet {

    private final PagoEmpleadoDAO daoPago = new PagoEmpleadoDAO();
    private final EmpleadoDAO daoEmpleado = new EmpleadoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        try {
            if (accion == null || accion.equals("listar")) {
                List<PagoEmpleadoDTO> pagos = daoPago.listar();
                request.setAttribute("pagos", pagos);
                request.getRequestDispatcher("vista/pago-empleado.jsp").forward(request, response);

            } else if (accion.equals("nuevo")) {
                List<EmpleadoDTO> empleados = daoEmpleado.ListarEmpleados();
                request.setAttribute("empleados", empleados);
                request.getRequestDispatcher("vista/nuevo-pago-empleado.jsp").forward(request, response);
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PagoEmpleadoControlador.class.getName()).log(Level.SEVERE, null, ex);
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

            double pagoTotal = sueldoBase + (horasExtras * pagoPorHora);

            PagoEmpleadoDTO p = new PagoEmpleadoDTO();
            p.setEmpleadoId(empleadoId);
            p.setFechaPago(new Date());
            p.setSueldoBase(sueldoBase);
            p.setHorasExtras(horasExtras);
            p.setPagoPorHora(pagoPorHora);
            p.setPagoTotal(pagoTotal);

            boolean exito = daoPago.registrar(p);

            response.sendRedirect("pago-empleado?accion=listar");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PagoEmpleadoControlador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("error", "Datos numéricos inválidos en el formulario.");
            request.getRequestDispatcher("vista/nuevo-pago-empleado.jsp").forward(request, response);
        }
    }
}
