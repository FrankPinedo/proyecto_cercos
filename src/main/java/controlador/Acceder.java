 package controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.util.ConexionUtil;

@WebServlet(name = "acceder", urlPatterns = {"/acceder"})
public class Acceder extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Connection enlaceConection = ConexionUtil.getInstance().getConexion();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            PreparedStatement ps = enlaceConection.prepareStatement("SELECT id, username, password FROM usuarios WHERE username=? AND password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                HttpSession session = request.getSession();
                session.setAttribute("id", rs.getInt("id"));
                response.sendRedirect("vista/index.jsp");
            }else{
                response.sendRedirect("vista/login.jsp?error");
            }
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Acceder.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Acceder.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
