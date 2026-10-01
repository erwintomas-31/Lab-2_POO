import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private Caso caso;
    public static void main(String[] args) {
        Main programa = new Main();
        programa.iniciarPrograma();
    }
 
    private void iniciarPrograma() {
        try {
            System.out.println("Agencia De Detectives");
            solicitarDatosInicialesCaso();
            ejecutarMenu();
        } catch (NoSuchElementException e) {
            System.out.println("Cerrando el programa");
        } finally {
            scanner.close();
            System.out.println("Programa finalizado");
        }
    }
 
    private void solicitarDatosInicialesCaso() {
        System.out.println("Datos del caso");
        String nombre = leerCadenaNoVacia("Nombre del caso: ");
        String codigo = leerCadenaNoVacia("Código de identificación: ");
        String detective = leerCadenaNoVacia("Detective responsable: ");
        caso = new Caso(nombre, codigo, detective);
    }
 
    private void ejecutarMenu() {
        int opcion = 0;
        do {
            System.out.println();
            System.out.println(caso);
            System.out.println("1.Nuevo caso");
            System.out.println("2.Registrar ubicación");
            System.out.println("3.Consultar ubicaciones");
            System.out.println("4.Consultar una ubicación");
            System.out.println("5.Modificar ubicación");
            System.out.println("6.Descartar ubicación");
            System.out.println("7.Registrar pista");
            System.out.println("8.Consultar pistas");
            System.out.println("9.Buscar pista");
            System.out.println("10.Modificar pista");
            System.out.println("11.Eliminar pista");
            System.out.println("12.Mostrar reporte de investigación");
            System.out.println("13.Salir");
            opcion = leerEnteroValidado("Seleccione una opción: ");
            try {
                switch (opcion) {
                    case 1:
                        solicitarDatosInicialesCaso();
                        System.out.println("Nuevo caso creado, sin ubicaciones ni pistas");
                        break;
                    case 2:
                        procesarRegistroUbicacion();
                        break;
                    case 3:
                        mostrarTodasUbicaciones();
                        break;
                    case 4:
                        mostrarUbicacionEspecifica();
                        break;
                    case 5:
                        procesarModificacionUbicacion();
                        break;
                    case 6:
                        procesarEliminarUbicacion();
                        break;
                    case 7:
                        procesarRegistroPista();
                        break;
                    case 8:
                        mostrarTodasPistas();
                        break;
                    case 9:
                        procesarBusquedaPista();
                        break;
                    case 10:
                        procesarModificacionPista();
                        break;
                    case 11:
                        procesarEliminacionPista();
                        break;
                    case 12:
                        mostrarEstadisticasYReportes();
                        break;
                    case 13:
                        System.out.println("Cerrando el programa");
                        break;
                    default:
                        System.out.println("Opción inválida. Elija un número del 1 al 13");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 13);
    }

    private void procesarRegistroUbicacion() {
        System.out.println("Registrar ubicación");
        int posicion = leerEnteroValidado("Posición donde registrar (0-4): ");
        if (caso.obtenerUbicacion(posicion) != null) {
            System.out.println("La posición " + posicion + " ya está ocupada");
            return;
        }
        String codigo = leerCadenaNoVacia("Código: ");
        String nombre = leerCadenaNoVacia("Nombre: ");
        String direccion = leerCadenaNoVacia("Dirección: ");
        int riesgo = leerEnteroValidado("Nivel de riesgo (1-10): ");
        String estado = leerCadenaNoVacia("Estado (Pendiente, en revisión o procesada): ");
        Ubicacion nueva = new Ubicacion(codigo, nombre, direccion, riesgo, estado);
        if (caso.registrarUbicacion(posicion, nueva)) {
            System.out.println("Ubicación registrada en la posición " + posicion);
        } else {
            System.out.println("No se pudo registrar la ubicación");
        }
    }
 
    private void mostrarTodasUbicaciones() {
        System.out.println("Ubicaciones registradas");
        Ubicacion[] activas = caso.obtenerTodasUbicaciones();
        if (activas.length == 0) {
            System.out.println("No hay ubicaciones registradas");
            return;
        }
        for (int i = 0; i < 5; i++) {
            Ubicacion u = caso.obtenerUbicacion(i);
            if (u != null) {
                System.out.println("Posición: " + i + " " + u);
            }
        }
    }
 
    private void mostrarUbicacionEspecifica() {
        System.out.println("Consultar una ubicación");
        int posicion = leerEnteroValidado("Posición (0-4): ");
        Ubicacion u = caso.obtenerUbicacion(posicion);
        if (u == null) {
            System.out.println("La posición " + posicion + " está vacía");
        } else {
            System.out.println("Posición: " + posicion + " " + u);
        }
    }
 
    private void procesarModificacionUbicacion() {
        System.out.println("Modificar ubicación");
        int posicion = leerEnteroValidado("Posición de la ubicación (0-4): ");
        Ubicacion actual = caso.obtenerUbicacion(posicion);
        if (actual == null) {
            System.out.println("La posición " + posicion + " está vacía");
            return;
        }
        System.out.println("Ubicación actual: " + actual);
        int nuevoRiesgo = leerEnteroValidado("Nuevo nivel de riesgo (1-10): ");
        String nuevoEstado = leerCadenaNoVacia("Nuevo estado: ");
        if (caso.modificarUbicacion(posicion, nuevoRiesgo, nuevoEstado)) {
            System.out.println("Ubicación modificada correctamente");
        } else {
            System.out.println("No se pudo modificar la ubicación");
        }
    }
 
    private void procesarEliminarUbicacion() {
        System.out.println("Eliminar ubicación");
        int posicion = leerEnteroValidado("Posición de la ubicación (0-4): ");
        if (caso.descartarUbicacion(posicion)) {
            System.out.println("Ubicación descartada. La posición " + posicion + " quedó disponible");
        } else {
            System.out.println("La posición " + posicion + " ya estaba vacía");
        }
    }
 
    private void procesarRegistroPista() {
        System.out.println("Registrar pista");
        String codigo = leerCadenaNoVacia("Código de la pista: ");
        if (caso.buscarPistaPorCodigo(codigo) != null) {
            System.out.println("Ya existe una pista con el código " + codigo + " No se permiten códigos repetidos");
            return;
        }
        String descripcion = leerCadenaNoVacia("Descripción: ");
        String tipo = leerCadenaNoVacia("Tipo de evidencia: ");
        int importancia = leerEnteroValidado("Nivel de importancia (1-10): ");
        int confiabilidad = leerEnteroValidado("Nivel de confiabilidad (0-100): ");
        Pista nueva = new Pista(codigo, descripcion, tipo, importancia, confiabilidad);
        if (caso.registrarPista(nueva)) {
            System.out.println("Pista registrada correctamente");
        } else {
            System.out.println("No se pudo registrar la pista");
        }
    }
 
    private void mostrarTodasPistas() {
        System.out.println("Pistas encontradas");
        ArrayList<Pista> lista = caso.obtenerTodasPistas();
        if (lista.isEmpty()) {
            System.out.println("No hay pistas registradas");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println((i + 1) + " " + lista.get(i));
        }
    }
 
    private void procesarBusquedaPista() {
        System.out.println("Buscar pista");
        String codigo = leerCadenaNoVacia("Código de la pista: ");
        Pista p = caso.buscarPistaPorCodigo(codigo);
        if (p == null) {
            System.out.println("No se encontró ninguna pista con el código " + codigo);
        } else {
            System.out.println(p);
        }
    }
 
    private void procesarModificacionPista() {
        System.out.println("Modificar pista");
        String codigo = leerCadenaNoVacia("Código de la pista: ");
        Pista actual = caso.buscarPistaPorCodigo(codigo);
        if (actual == null) {
            System.out.println("No se encontró ninguna pista con el código " + codigo);
            return;
        }
        System.out.println("Pista actual: " + actual);
        String descripcion = leerCadenaNoVacia("Nueva descripción: ");
        String tipo = leerCadenaNoVacia("Nuevo tipo de evidencia: ");
        int importancia = leerEnteroValidado("Nueva importancia (1-10): ");
        int confiabilidad = leerEnteroValidado("Nueva confiabilidad (0-100): ");
        if (caso.modificarPista(codigo, descripcion, tipo, importancia, confiabilidad)) {
            System.out.println("Pista modificada correctamente");
        } else {
            System.out.println("No se pudo modificar la pista");
        }
    }
 
    private void procesarEliminacionPista() {
        System.out.println("Eliminar pista");
        String codigo = leerCadenaNoVacia("Código de la pista: ");
        if (caso.eliminarPista(codigo)) {
            System.out.println("Pista eliminada correctamente");
        } else {
            System.out.println("No se encontró ninguna pista con el código " + codigo);
        }
    }
 
    private void mostrarEstadisticasYReportes() {
        System.out.println("Reporte");
        System.out.println("Caso: " + caso.getNombreCaso() + " Código: " + caso.getCodigoIdentificacion());
        System.out.println("Detective responsable: " + caso.getDetectiveResponsable());
        System.out.println("Ubicaciones registradas: " + caso.cantidadUbicacionesRegistradas());
        System.out.println("Espacios disponibles: " + caso.espaciosDisponiblesUbicaciones());
        Ubicacion mayorRiesgo = caso.ubicacionMayorRiesgo();
        if (mayorRiesgo == null) {
            System.out.println("Ubicación con mayor nivel de riesgo: no hay ubicaciones registradas");
        } else {
            System.out.println("Ubicación con mayor nivel de riesgo: " + mayorRiesgo);
        }
        System.out.println("Pistas encontradas: " + caso.cantidadPistasRegistradas());
        Pista mayorImportancia = caso.pistaMayorImportancia();
        Pista mayorConfiabilidad = caso.pistaMayorConfiabilidad();
        if (mayorImportancia == null || mayorConfiabilidad == null) {
            System.out.println("Pista con mayor importancia: no hay pistas registradas");
            System.out.println("Pista con mayor confiabilidad: no hay pistas registradas");
            System.out.println("No hay promedio, porque no hay pistas");
        } else {
            System.out.println("Pista con mayor importancia: " + mayorImportancia);
            System.out.println("Pista con mayor confiabilidad: " + mayorConfiabilidad);
            System.out.println("Promedio de importancia: " + caso.promedioImportanciaPistas());
        }
    }
 
    private int leerEnteroValidado(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                int valor = scanner.nextInt();
                scanner.nextLine(); 
                return valor;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Debe ingresar un número entero");
                scanner.nextLine(); 
            }
        }
    }
 
    private String leerCadenaNoVacia(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje);
            texto = scanner.nextLine().trim();
            if (texto.isEmpty()) {
                System.out.println("El valor no puede estar vacío");
            }
        } while (texto.isEmpty());
        return texto;
    }
}