package modelo.DAO;

import java.sql.*;
import java.util.*;
import modelo.DTO.EmpleadoDTO;
import modelo.util.ConexionUtil;

public class EmpleadoDAO {

    private Connection cn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<EmpleadoDTO> ListarEmpleados() {
        ArrayList<EmpleadoDTO> lista = new ArrayList<>();

        try {
            cn = ConexionUtil.getInstance().getConexion();
            ps = cn.prepareStatement("SELECT * FROM empleados");
            rs = ps.executeQuery();

            while (rs.next()) {
                EmpleadoDTO empleado = new EmpleadoDTO();

                empleado.setId(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellidos(rs.getString(3));
                empleado.setDni(rs.getString(4));
                empleado.setCargo(rs.getString(5));
                empleado.setSueldoBase(rs.getDouble(6));

                lista.add(empleado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int registrar(EmpleadoDTO empelado) {
        int resultado = 0;

        try {

            cn = ConexionUtil.getInstance().getConexion();
            ps = cn.prepareStatement("INSERT INTO empleados (nombre, apellidos, dni, cargo, sueldo_base) VALUES (?,?,?,?,?)");
            ps.setString(1, empelado.getNombre());
            ps.setString(2, empelado.getApellidos());
            ps.setString(3, empelado.getDni());
            ps.setString(4, empelado.getCargo());
            ps.setDouble(5, empelado.getSueldoBase());

            resultado = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;

    }

    public EmpleadoDTO buscarPorID(int id) {
        EmpleadoDTO empleado = null;

        try {
            cn = ConexionUtil.getInstance().getConexion();
            ps = cn.prepareStatement("SELECT * FROM empleados WHERE id=?");
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                empleado = new EmpleadoDTO();

                empleado.setId(rs.getInt(1));
                empleado.setNombre(rs.getString(2));
                empleado.setApellidos(rs.getString(3));
                empleado.setDni(rs.getString(4));
                empleado.setCargo(rs.getString(5));
                empleado.setSueldoBase(rs.getDouble(6));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return empleado;
    }

    public int editar(EmpleadoDTO empleado) {
        int resultado = 0;

        try {

            cn = ConexionUtil.getInstance().getConexion();
            ps = cn.prepareStatement("UPDATE empleados SET nombre=?, apellidos=?, dni=?, cargo=?, sueldo_base=? WHERE id=?");
            ps.setString(1, empleado.getNombre());
            ps.setString(2, empleado.getApellidos());
            ps.setString(3, empleado.getDni());
            ps.setString(4, empleado.getCargo());
            ps.setDouble(5, empleado.getSueldoBase());
            ps.setInt(6, empleado.getId());

            resultado = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;

    }

    public int eliminar(int id) {
        int resultado = 0;

        try {

            cn = ConexionUtil.getInstance().getConexion();
            ps = cn.prepareStatement("DELETE FROM empleados WHERE id=?");
            ps.setInt(1, id);

            resultado = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;

    }

}
