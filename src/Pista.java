public class Pista {
    public static final int IMPORTANCIA_MIN = 1;
    public static final int IMPORTANCIA_MAX = 10;
    public static final int CONFIABILIDAD_MIN = 0;
    public static final int CONFIABILIDAD_MAX = 100;
    private String codigo;
    private String descripcion;
    private String tipoEvidencia;
    private int nivelImportancia;
    private int nivelConfiabilidad;
 
    public Pista(String codigo, String descripcion, String tipoEvidencia, int nivelImportancia, int nivelConfiabilidad){
        setCodigo(codigo);
        setDescripcion(descripcion);
        setTipoEvidencia(tipoEvidencia);
        setNivelImportancia(nivelImportancia);
        setNivelConfiabilidad(nivelConfiabilidad);
    }

    public String getCodigo() {
        return codigo;
    }
 
    public String getDescripcion() {
        return descripcion;
    }
 
    public String getTipoEvidencia() {
        return tipoEvidencia;
    }
 
    public int getNivelImportancia() {
        return nivelImportancia;
    }
 
    public int getNivelConfiabilidad() {
        return nivelConfiabilidad;
    }

    public void setCodigo(String codigo) {
        validarTexto(codigo, "Código de la pista");
        this.codigo = codigo.trim();
    }
 
    public void setDescripcion(String descripcion) {
        validarTexto(descripcion, "Descripción de la pista");
        this.descripcion = descripcion.trim();
    }
 
    public void setTipoEvidencia(String tipoEvidencia) {
        validarTexto(tipoEvidencia, "Tipo de evidencia");
        this.tipoEvidencia = tipoEvidencia.trim();
    }
 
    public void setNivelImportancia(int nivelImportancia) {
        if (nivelImportancia < IMPORTANCIA_MIN || nivelImportancia > IMPORTANCIA_MAX) {
            throw new IllegalArgumentException("El nivel de importancia debe estar entre " + IMPORTANCIA_MIN + " y " + IMPORTANCIA_MAX);
        }
        this.nivelImportancia = nivelImportancia;
    }
 
    public void setNivelConfiabilidad(int nivelConfiabilidad) {
        if (nivelConfiabilidad < CONFIABILIDAD_MIN || nivelConfiabilidad > CONFIABILIDAD_MAX) {
            throw new IllegalArgumentException("El nivel de confiabilidad debe estar entre " + CONFIABILIDAD_MIN + " y " + CONFIABILIDAD_MAX);
        }
        this.nivelConfiabilidad = nivelConfiabilidad;
    }
 
    private static void validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío");
        }
    }
 
    @Override
    public String toString() {
        return "Código: " + codigo + " Descripción: " + descripcion + " Tipo: " + tipoEvidencia + " Importancia: " + nivelImportancia + " Confiabilidad: " + nivelConfiabilidad + "%";
    }
}