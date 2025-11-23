package controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.DAO.EmpleadoDAO;
import modelo.DTO.EmpleadoDTO;

@WebServlet(name = "empleados", urlPatterns = {"/empleados"})
public class EmpleadoControlador extends HttpServlet {

    private EmpleadoDAO empDao = new EmpleadoDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        switch (accion) {
            case "listar":
                listar(request, response);
                break;
            case "agregar":
                agregar(request, response);
                break;
            case "guardar":
                guardar(request, response);
                break;
            case "editar":
                editar(request, response);
                break;
            case "eliminar":
                eliminar(request, response);
                break;
            default:
                response.sendRedirect("empleados?accion=listar");
                break;
        }
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("empleados", empDao.ListarEmpleados());
        request.getRequestDispatcher("vista/ver-empleados.jsp").forward(request, response);
    }

    protected void agregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("empleado", new EmpleadoDTO());
        request.getRequestDispatcher("vista/nuevo-empleado.jsp").forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        EmpleadoDTO empleado = new EmpleadoDTO();
        empleado.setId(Integer.parseInt(request.getParameter("id")));
        empleado.setNombre(request.getParameter("nombres"));
        empleado.setApellidos(request.getParameter("apellidos"));
        empleado.setDni(request.getParameter("dni"));
        empleado.setCargo(request.getParameter("cargo"));
        empleado.setSueldoBase(Double.parseDouble(request.getParameter("sueldo")));

        int resultado;

        if (empleado.getId() == 0) {
            resultado = empDao.registrar(empleado);
        } else {
            resultado = empDao.editar(empleado);
        }

        if (resultado > 0) {
            request.getSession().setAttribute("success", "Datos guardados! ");
            response.sendRedirect("empleados?accion=listar");
        } else {
            request.getSession().setAttribute("error", "No se pudo guardar datos ");
            request.setAttribute("empleado", empleado);
            request.getRequestDispatcher("vista/nuevo-empleado.jsp").forward(request, response);
        }
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        EmpleadoDTO empleado = empDao.buscarPorID(id);
        if (empleado != null) {
            request.setAttribute("empleado", empleado);
            request.getRequestDispatcher("vista/nuevo-empleado.jsp").forward(request, response);
        } else {
            response.sendRedirect("empleados?accion=listar");
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        int resultado = empDao.eliminar(id);

        if (resultado > 0) {
            request.getSession().setAttribute("success", "Empleado con id: " + id + ", Eliminado!");
        } else {
            request.getSession().setAttribute("error", "No se pudo eliminar empleado");
        }
        response.sendRedirect("empleados?accion=listar");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
