package modelo.DAO;

import modelo.DTO.PagoEmpleadoDTO;
import modelo.util.ConexionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagoEmpleadoDAO {

    public List<PagoEmpleadoDTO> listar() throws ClassNotFoundException {
        List<PagoEmpleadoDTO> lista = new ArrayList<>();
        String sql = "SELECT p.id, p.empleado_id, CONCAT(e.nombre, ' ', e.apellidos) AS nombre_empleado, "
                + "p.fecha_pago, p.sueldo_base, p.horas_extras, p.pago_por_hora, p.pago_total "
                + "FROM pagos_empleados p INNER JOIN empleados e ON p.empleado_id = e.id "
                + "ORDER BY p.id DESC";

        try (Connection con = ConexionUtil.getInstance().getConexion(); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PagoEmpleadoDTO p = new PagoEmpleadoDTO();
                p.setId(rs.getInt("id"));
                p.setEmpleadoId(rs.getInt("empleado_id"));
                p.setNombreEmpleado(rs.getString("nombre_empleado"));
                p.setFechaPago(rs.getDate("fecha_pago"));
                p.setSueldoBase(rs.getDouble("sueldo_base"));
                p.setHorasExtras(rs.getInt("horas_extras"));
                p.setPagoPorHora(rs.getDouble("pago_por_hora"));
                p.setPagoTotal(rs.getDouble("pago_total"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    public boolean registrar(PagoEmpleadoDTO p) throws ClassNotFoundException {
        String sql = "INSERT INTO pagos_empleados "
                + "(empleado_id, fecha_pago, sueldo_base, horas_extras, pago_por_hora, pago_total) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = ConexionUtil.getInstance().getConexion(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, p.getEmpleadoId());
            ps.setDate(2, new java.sql.Date(p.getFechaPago().getTime()));
            ps.setDouble(3, p.getSueldoBase());
            ps.setInt(4, p.getHorasExtras());
            ps.setDouble(5, p.getPagoPorHora());
            ps.setDouble(6, p.getPagoTotal());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
