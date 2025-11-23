package modelo.DTO;

public class EmpleadoDTO {
    private int id;
    private String nombre, apellidos, dni, cargo;
    private double sueldoBase;

    public EmpleadoDTO() {
    }

    public EmpleadoDTO(int id, String nombre, String apellidos, String dni, String cargo, double sueldoBase) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.dni = dni;
        this.cargo = cargo;
        this.sueldoBase = sueldoBase;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSueldoBase() {
        return sueldoBase;
    }

    public void setSueldoBase(double sueldoBase) {
        this.sueldoBase = sueldoBase;
    }

    @Override
    public String toString() {
        return "EmpleadoDTO{" + "id=" + id + ", nombre=" + nombre + ", apellidos=" + apellidos + ", dni=" + dni + ", cargo=" + cargo + ", sueldoBase=" + sueldoBase + '}';
    }

}
