import java.util.ArrayList;

public class Caso {
    private String nombreCaso;
    private String codigoIdentificacion;
    private String detectiveResponsable;
    private Ubicacion[] ubicaciones;
    private ArrayList<Pista> pistas;
 
    public Caso(String nombreCaso, String codigoIdentificacion, String detectiveResponsable) {
        setNombreCaso(nombreCaso);
        setCodigoIdentificacion(codigoIdentificacion);
        setDetectiveResponsable(detectiveResponsable);
        this.ubicaciones = new Ubicacion[5];
        this.pistas = new ArrayList<Pista>();
    }
 
    public String getNombreCaso() {
        return nombreCaso;
    }
 
    public void setNombreCaso(String nombreCaso) {
        validarTexto(nombreCaso, "Nombre del caso");
        this.nombreCaso = nombreCaso.trim();
    }
 
    public String getCodigoIdentificacion() {
        return codigoIdentificacion;
    }
 
    public void setCodigoIdentificacion(String codigoIdentificacion) {
        validarTexto(codigoIdentificacion, "Código de identificación");
        this.codigoIdentificacion = codigoIdentificacion.trim();
    }
 
    public String getDetectiveResponsable() {
        return detectiveResponsable;
    }
 
    public void setDetectiveResponsable(String detectiveResponsable) {
        validarTexto(detectiveResponsable, "El detective responsable");
        this.detectiveResponsable = detectiveResponsable.trim();
    }

    public boolean registrarUbicacion(int posicion, Ubicacion ubicacion) {
        if (!esPosicionValida(posicion)) {
            throw new IllegalArgumentException("La posición debe estar entre 0 y 4");
        }
        if (ubicacion == null || !esPosicionDisponible(posicion)) {
            return false;
        }
        ubicaciones[posicion] = ubicacion;
        return true;
    }
 
    public Ubicacion obtenerUbicacion(int posicion) {
        if (!esPosicionValida(posicion)) {
            throw new IllegalArgumentException("La posición debe estar entre 0 y 4");
        }
        return ubicaciones[posicion];
    }

    public boolean modificarUbicacion(int posicion, int nuevoNivelRiesgo, String nuevoEstado) {
        if (!esPosicionValida(posicion)) {
            throw new IllegalArgumentException("La posición debe estar entre 0 y 4");
        }
        Ubicacion u = ubicaciones[posicion];
        if (u == null) {
            return false;
        }
        new Ubicacion(u.getCodigo(), u.getNombre(), u.getDireccion(), nuevoNivelRiesgo, nuevoEstado);
        u.setNivelRiesgo(nuevoNivelRiesgo);
        u.setEstado(nuevoEstado);
        return true;
    }
 
    public boolean descartarUbicacion(int posicion) {
        if (!esPosicionValida(posicion)) {
            throw new IllegalArgumentException("La posición debe estar entre 0 y 4");
        }
        if (ubicaciones[posicion] == null) {
            return false;
        }
        ubicaciones[posicion] = null;
        return true;
    }
 
    public Ubicacion[] obtenerTodasUbicaciones() {
        Ubicacion[] activas = new Ubicacion[cantidadUbicacionesRegistradas()];
        int indice = 0;
        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null) {
                activas[indice] = ubicaciones[i];
                indice++;
            }
        }
        return activas;
    }

    public boolean registrarPista(Pista pista) {
        if (pista == null || existeCodigoPista(pista.getCodigo())) {
            return false;
        }
        pistas.add(pista);
        return true;
    }
 
    public Pista buscarPistaPorCodigo(String codigo) {
        if (codigo == null) {
            return null;
        }
        for (int i = 0; i < pistas.size(); i++) {
            if (pistas.get(i).getCodigo().equalsIgnoreCase(codigo.trim())) {
                return pistas.get(i);
            }
        }
        return null;
    }
 
    public boolean modificarPista(String codigo, String nuevaDescripcion, String nuevoTipoEvidencia, int nuevaImportancia, int nuevaConfiabilidad) {
        Pista pista = buscarPistaPorCodigo(codigo);
        if (pista == null) {
            return false;
        }
        new Pista(pista.getCodigo(), nuevaDescripcion, nuevoTipoEvidencia, nuevaImportancia, nuevaConfiabilidad);
        pista.setDescripcion(nuevaDescripcion);
        pista.setTipoEvidencia(nuevoTipoEvidencia);
        pista.setNivelImportancia(nuevaImportancia);
        pista.setNivelConfiabilidad(nuevaConfiabilidad);
        return true;
    }

    public boolean eliminarPista(String codigo) {
        Pista pista = buscarPistaPorCodigo(codigo);
        if (pista == null) {
            return false;
        }
        pistas.remove(pista);
        return true;
    }
 
    public ArrayList<Pista> obtenerTodasPistas() {
        return new ArrayList<Pista>(pistas);
    }
 
    public int cantidadUbicacionesRegistradas() {
        int cantidad = 0;
        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null) {
                cantidad++;
            }
        }
        return cantidad;
    }
 
    public int espaciosDisponiblesUbicaciones() {
        return 5 - cantidadUbicacionesRegistradas();
    }
 
    public Ubicacion ubicacionMayorRiesgo() {
        Ubicacion mayor = null;
        for (int i = 0; i < ubicaciones.length; i++) {
            if (ubicaciones[i] != null && (mayor == null || ubicaciones[i].getNivelRiesgo() > mayor.getNivelRiesgo())) {
                mayor = ubicaciones[i];
            }
        }
        return mayor;
    }
 
    public int cantidadPistasRegistradas() {
        return pistas.size();
    }
 
    public Pista pistaMayorImportancia() {
        if (pistas.isEmpty()) {
            return null;
        }
        Pista mayor = pistas.get(0);
        for (int i = 1; i < pistas.size(); i++) {
            if (pistas.get(i).getNivelImportancia() > mayor.getNivelImportancia()) {
                mayor = pistas.get(i);
            }
        }
        return mayor;
    }
 
    public Pista pistaMayorConfiabilidad() {
        if (pistas.isEmpty()) {
            return null;
        }
        Pista mayor = pistas.get(0);
        for (int i = 1; i < pistas.size(); i++) {
            if (pistas.get(i).getNivelConfiabilidad() > mayor.getNivelConfiabilidad()) {
                mayor = pistas.get(i);
            }
        }
        return mayor;
    }
 
    public double promedioImportanciaPistas() {
        if (pistas.isEmpty()) {
            return 0.0;
        }
        int suma = 0;
        for (int i = 0; i < pistas.size(); i++) {
            suma += pistas.get(i).getNivelImportancia();
        }
        return (double) suma / pistas.size();
    }
 
    private boolean existeCodigoPista(String codigo) {
        return buscarPistaPorCodigo(codigo) != null;
    }
 
    private boolean esPosicionValida(int posicion) {
        return posicion >= 0 && posicion < 5;
    }
 
    private boolean esPosicionDisponible(int posicion) {
        return esPosicionValida(posicion) && ubicaciones[posicion] == null;
    }
 
    private static void validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío");
        }
    }
 
    @Override
    public String toString() {
        return "Caso: " + nombreCaso + " Código: " + codigoIdentificacion + " Detective: " + detectiveResponsable + " Ubicaciones registradas: " + cantidadUbicacionesRegistradas() + " Espacios disponibles: " + espaciosDisponiblesUbicaciones() + " Pistas: " + pistas.size();
    }
}
