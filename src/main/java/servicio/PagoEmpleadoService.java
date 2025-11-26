package servicio;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import modelo.DAO.PagoEmpleadoDAO;
import modelo.DTO.PagoEmpleadoDTO;

public class PagoEmpleadoService {

    private final PagoEmpleadoDAO pagoEmpleadoDAO = new PagoEmpleadoDAO();

    public List<PagoEmpleadoDTO> listar() {
        try {
            return pagoEmpleadoDAO.listar();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public double calcularPagoTotal(double sueldoBase, int horasExtras, double pagoPorHora) {
        return sueldoBase + (horasExtras * pagoPorHora);
    }

    public boolean registrar(PagoEmpleadoDTO pago) {
        pago.setFechaPago(new Date());
        pago.setPagoTotal(calcularPagoTotal(pago.getSueldoBase(), pago.getHorasExtras(), pago.getPagoPorHora()));
        try {
            return pagoEmpleadoDAO.registrar(pago);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}
