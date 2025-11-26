package servicio;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import modelo.DAO.EmpleadoDAO;
import modelo.DTO.EmpleadoDTO;

public class EmpleadoService {

    private final EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    public List<EmpleadoDTO> listar() {
        try {
            return empleadoDAO.ListarEmpleados();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Optional<EmpleadoDTO> buscarPorId(int id) {
        try {
            return Optional.ofNullable(empleadoDAO.buscarPorID(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public boolean guardar(EmpleadoDTO empleado) {
        if (empleado.getNombre() == null || empleado.getNombre().isEmpty()) {
            return false;
        }

        int resultado;
        if (empleado.getId() == 0) {
            resultado = empleadoDAO.registrar(empleado);
        } else {
            resultado = empleadoDAO.editar(empleado);
        }

        return resultado > 0;
    }

    public boolean eliminar(int id) {
        int resultado = empleadoDAO.eliminar(id);
        return resultado > 0;
    }
}
