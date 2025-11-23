package controlador;

import modelo.DAO.MaterialDAO;
import modelo.DTO.MaterialDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "materiales", urlPatterns = {"/materiales"})
public class MaterialControlador extends HttpServlet {

    private MaterialDAO dao = new MaterialDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
                response.sendRedirect("materiales?accion=listar");
                break;
        }
    }

    protected void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("materiales", dao.listar());
        request.getRequestDispatcher("vista/ver-materiales.jsp").forward(request, response);
    }

    protected void agregar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("material", new MaterialDTO());
        request.getRequestDispatcher("vista/nuevo-material.jsp").forward(request, response);
    }

    protected void guardar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MaterialDTO m = new MaterialDTO();
        m.setId(Integer.parseInt(request.getParameter("id")));
        m.setNombre(request.getParameter("nombre"));
        m.setUnidadDeMedida(request.getParameter("unidadMedida"));
        m.setCostoUnitario(Double.parseDouble(request.getParameter("costoUnitario")));
        m.setStockActual(Double.parseDouble(request.getParameter("stockActual")));

        int res;

        if (m.getId() == 0) {
            res = dao.registrar(m);
        } else {
            res = dao.editar(m);
        }
        
        
        if (res > 0) {
            request.getSession().setAttribute("success", "Material guardado!");
            response.sendRedirect("materiales?accion=listar");
        } else {
            request.getSession().setAttribute("error", "Error al guardar material");
            request.setAttribute("material", m);
            request.getRequestDispatcher("vista/nuevo-material.jsp").forward(request, response);
        }
    }

    protected void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        MaterialDTO m = dao.buscarPorID(id);
        if (m != null) {
            request.setAttribute("material", m);
            request.getRequestDispatcher("vista/nuevo-material.jsp").forward(request, response);
        } else {
            response.sendRedirect("materiales?accion=listar");
        }
    }

    protected void eliminar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int res = dao.eliminar(id);
        if (res > 0) {
            request.getSession().setAttribute("success", "Material eliminado!");
        } else {
            request.getSession().setAttribute("error", "No se pudo eliminar material");
        }
        response.sendRedirect("materiales?accion=listar");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
