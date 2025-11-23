
package modelo.DTO;

public class MaterialDTO {

    private int id;
    private String nombre;
    private String unidadDeMedida;
    private double costoUnitario;
    private double stockActual;

    public MaterialDTO() {
    }

    public MaterialDTO(int id, String nombre, String unidadDeMedida, double costoUnitario, double stockActual) {
        this.id = id;
        this.nombre = nombre;
        this.unidadDeMedida = unidadDeMedida;
        this.costoUnitario = costoUnitario;
        this.stockActual = stockActual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadDeMedida() {
        return unidadDeMedida;
    }

    public void setUnidadDeMedida(String unidadDeMedida) {
        this.unidadDeMedida = unidadDeMedida;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getStockActual() {
        return stockActual;
    }

    public void setStockActual(double stockActual) {
        this.stockActual = stockActual;
    }

    @Override
    public String toString() {
        return "MaterialDTO{" + "id=" + id + ", nombre=" + nombre + ", unidadDeMedida=" + unidadDeMedida + ", costoUnitario=" + costoUnitario + ", stockActual=" + stockActual + '}';
    }
}
