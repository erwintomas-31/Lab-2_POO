public class Ubicacion {
    public static final int RIESGO_MIN = 1;
    public static final int RIESGO_MAX = 10;
    private String codigo;
    private String nombre;
    private String direccion;
    private int nivelRiesgo;
    private String estado;
 
    public Ubicacion(String codigo, String nombre, String direccion, int nivelRiesgo, String estado) {
        setCodigo(codigo);
        setNombre(nombre);
        setDireccion(direccion);
        setNivelRiesgo(nivelRiesgo);
        setEstado(estado);
    }
 
    public String getCodigo() {
        return codigo;
    }
 
    public void setCodigo(String codigo) {
        validarTexto(codigo, "Código de la ubicación");
        this.codigo = codigo.trim();
    }
 
    public String getNombre() {
        return nombre;
    }
 
    public void setNombre(String nombre) {
        validarTexto(nombre, "Nombre de la ubicación");
        this.nombre = nombre.trim();
    }
 
    public String getDireccion() {
        return direccion;
    }
 
    public void setDireccion(String direccion) {
        validarTexto(direccion, "Dirección de la ubicación");
        this.direccion = direccion.trim();
    }
 
    public int getNivelRiesgo() {
        return nivelRiesgo;
    }
 
    public void setNivelRiesgo(int nivelRiesgo) {
        if (nivelRiesgo < RIESGO_MIN || nivelRiesgo > RIESGO_MAX) {
            throw new IllegalArgumentException("El nivel de riesgo debe estar entre " + RIESGO_MIN + " y " + RIESGO_MAX);
        }
        this.nivelRiesgo = nivelRiesgo;
    }
 
    public String getEstado() {
        return estado;
    }
 
    public void setEstado(String estado) {
        validarTexto(estado, "Estado de la ubicación");
        this.estado = estado.trim();
    }
 
    private static void validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío");
        }
    }
 
    @Override
    public String toString() {
        return "Código: " + codigo + " Nombre: " + nombre + " Dirección: " + direccion + " Riesgo: " + nivelRiesgo + " Estado: " + estado;
    }
}