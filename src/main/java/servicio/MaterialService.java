package servicio;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import modelo.DAO.MaterialDAO;
import modelo.DTO.MaterialDTO;

public class MaterialService {

    private final MaterialDAO materialDAO = new MaterialDAO();

    public List<MaterialDTO> listar() {
        try {
            return materialDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public Optional<MaterialDTO> obtenerPorId(int id) {
        try {
            return Optional.ofNullable(materialDAO.buscarPorID(id));
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public boolean guardar(MaterialDTO material) {
        if (material.getNombre() == null || material.getNombre().isEmpty()) {
            return false;
        }

        int resultado;
        if (material.getId() == 0) {
            resultado = materialDAO.registrar(material);
        } else {
            resultado = materialDAO.editar(material);
        }

        return resultado > 0;
    }

    public boolean eliminar(int id) {
        int resultado = materialDAO.eliminar(id);
        return resultado > 0;
    }
}
