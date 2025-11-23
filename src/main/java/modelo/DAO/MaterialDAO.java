package modelo.DAO;

import modelo.DTO.MaterialDTO;
import modelo.util.ConexionUtil;
import java.sql.*;
import java.util.ArrayList;

public class MaterialDAO {

    public ArrayList<MaterialDTO> listar() {

        ArrayList<MaterialDTO> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM materiales";

        try (Connection cn = ConexionUtil.getInstance().getConexion(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                MaterialDTO m = new MaterialDTO();
                m.setId(rs.getInt("id"));
                m.setNombre(rs.getString("nombre"));
                m.setUnidadDeMedida(rs.getString("unidad_medida"));
                m.setCostoUnitario(rs.getDouble("costo_unitario"));
                m.setStockActual(rs.getDouble("stock_actual"));
                lista.add(m);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }

    public int registrar(MaterialDTO m) {

        int resultado = 0;
        String sql = "INSERT INTO materiales (nombre, unidad_medida, costo_unitario, stock_actual) VALUES (?,?,?,?)";

        try (Connection cn = ConexionUtil.getInstance().getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setString(2, m.getUnidadDeMedida());
            ps.setDouble(3, m.getCostoUnitario());
            ps.setDouble(4, m.getStockActual());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public MaterialDTO buscarPorID(int id) {

        MaterialDTO m = null;
        String sql = "SELECT * FROM materiales WHERE id=?";

        try (Connection cn = ConexionUtil.getInstance().getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    m = new MaterialDTO();
                    m.setId(rs.getInt("id"));
                    m.setNombre(rs.getString("nombre"));
                    m.setUnidadDeMedida(rs.getString("unidad_medida"));
                    m.setCostoUnitario(rs.getDouble("costo_unitario"));
                    m.setStockActual(rs.getDouble("stock_actual"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return m;
    }

    public int editar(MaterialDTO m) {
        int resultado = 0;
        
        String sql = "UPDATE materiales SET nombre=?, unidad_medida=?, costo_unitario=?, stock_actual=? WHERE id=?";
        try (Connection cn = ConexionUtil.getInstance().getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, m.getNombre());
            ps.setString(2, m.getUnidadDeMedida());
            ps.setDouble(3, m.getCostoUnitario());
            ps.setDouble(4, m.getStockActual());
            ps.setInt(5, m.getId());

            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }

    public int eliminar(int id) {
        int resultado = 0;
        String sql = "DELETE FROM materiales WHERE id=?";
        try (Connection cn = ConexionUtil.getInstance().getConexion(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            resultado = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultado;
    }
}
