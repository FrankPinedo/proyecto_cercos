package vista.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import modelo.DTO.MaterialDTO;
import servicio.MaterialService;
import servicio.TipoCambioService;

@Named("materialBean")
@SessionScoped
public class MaterialBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private final MaterialService materialService = new MaterialService();
    private final TipoCambioService tipoCambioService = new TipoCambioService();

    private List<MaterialDTO> materiales = new ArrayList<>();
    private MaterialDTO materialActual = new MaterialDTO();
    private String monedaDestino = "USD";
    private Double tipoCambioActual;

    @PostConstruct
    public void init() {
        cargarMateriales();
        actualizarTipoCambio();
    }

    public void cargarMateriales() {
        materiales = materialService.listar();
    }

    public void prepararNuevo() {
        materialActual = new MaterialDTO();
    }

    public void editar(MaterialDTO material) {
        materialActual = materialService.obtenerPorId(material.getId()).orElse(material);
    }

    public void guardar() {
        boolean guardado = materialService.guardar(materialActual);
        FacesContext context = FacesContext.getCurrentInstance();
        if (guardado) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    context.getApplication().evaluateExpressionGet(context, "#{msg['materiales.guardado']}", String.class), null));
            materialActual = new MaterialDTO();
            cargarMateriales();
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    context.getApplication().evaluateExpressionGet(context, "#{msg['materiales.error']}", String.class), null));
        }
    }

    public void eliminar(MaterialDTO material) {
        boolean eliminado = materialService.eliminar(material.getId());
        FacesContext context = FacesContext.getCurrentInstance();
        if (eliminado) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    context.getApplication().evaluateExpressionGet(context, "#{msg['materiales.eliminado']}", String.class), null));
            cargarMateriales();
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    context.getApplication().evaluateExpressionGet(context, "#{msg['materiales.error']}", String.class), null));
        }
    }

    public void actualizarTipoCambio() {
        tipoCambioActual = tipoCambioService.obtenerCambio("PEN", monedaDestino).orElse(null);
    }

    public double obtenerCostoConvertido(MaterialDTO material) {
        if (tipoCambioActual == null) {
            return 0;
        }
        return tipoCambioService.convertir(material.getCostoUnitario(), tipoCambioActual);
    }

    public List<MaterialDTO> getMateriales() {
        return materiales;
    }

    public MaterialDTO getMaterialActual() {
        return materialActual;
    }

    public void setMaterialActual(MaterialDTO materialActual) {
        this.materialActual = materialActual;
    }

    public String getMonedaDestino() {
        return monedaDestino;
    }

    public void setMonedaDestino(String monedaDestino) {
        this.monedaDestino = monedaDestino;
    }

    public Double getTipoCambioActual() {
        return tipoCambioActual;
    }
}
