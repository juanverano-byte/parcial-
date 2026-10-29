import java.util.ArrayList;
import java.util.List;

public class App {
    private static final int CAPACIDAD = 10;
    private static final GestorRecetas gestor = new GestorRecetas(CAPACIDAD);

    public static void main(String[] args) {
        
        int opcion;

        do {
            System.out.println("\n===== GESTIÓN DE RECETAS DE COCINA =====");
            System.out.println("1. Registrar receta");
            System.out.println("2. Consultar receta por nombre");
            System.out.println("3. Actualizar receta");
            System.out.println("4. Listar recetas");
            System.out.println("5. Ver registro de eventos");
            System.out.println("6. Salir");
            opcion = Entrada.leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1: registrarReceta(); break;
                case 2: consultarReceta(); break;
                case 3: actualizarReceta(); break;
                case 4: listarRecetas(); break;
                case 5:
                    System.out.println("Hasta pronto.");
                    
                    break;
                default:
                    fallo("Opción de menú no válida: " + opcion);
            }
        } while (opcion != 6);
    }

    private static void registrarReceta() {
        if (gestor.estaLleno()) {
            fallo("No hay espacio para más recetas (capacidad máxima: " + CAPACIDAD + ").");
            return;
        }
        try {
            String nombre = Entrada.leerLinea("Nombre de la receta: ");
            int porciones = Entrada.leerEntero("Número de porciones: ");
            int cantidad = Entrada.leerEntero("¿Cuántos ingredientes tiene?: ");

            List<Ingrediente> ingredientes = new ArrayList<>();
            for (int i = 1; i <= cantidad; i++) {
                System.out.println("Ingrediente " + i + ":");
                String nom = Entrada.leerLinea("  Nombre: ");
                double cant = Entrada.leerDouble("  Cantidad: ");
                String unidad = Entrada.leerLinea("  Unidad (g, taza, cucharada...): ");
                ingredientes.add(new Ingrediente(nom, cant, unidad));
            }

            String instrucciones = Entrada.leerLinea("Instrucciones de preparación: ");
            gestor.registrar(new Receta(nombre, ingredientes, instrucciones, porciones));
            System.out.println("Receta registrada correctamente.");
        } catch (IllegalArgumentException | CapacidadExcedidaException e) {
            fallo("No se registró la receta. " + e.getMessage());
        }
    }

    private static void consultarReceta() {
        String nombre = Entrada.leerLinea("Nombre de la receta a buscar: ");
        try {
            gestor.buscar(nombre).mostrar();
        } catch (RecetaNoEncontradaException e) {
            fallo(e.getMessage());
        }
    }

    private static void actualizarReceta() {
        String nombre = Entrada.leerLinea("Nombre de la receta a actualizar: ");
        try {
            gestor.verificarExiste(nombre);
            System.out.println("1. Modificar instrucciones");
            System.out.println("2. Modificar número de porciones");
            int campo = Entrada.leerEntero("¿Qué desea modificar?: ");

            if (campo == 1) {
                String nuevas = Entrada.leerLinea("Nuevas instrucciones: ");
                gestor.actualizarInstrucciones(nombre, nuevas);
            } else if (campo == 2) {
                int nuevas = Entrada.leerEntero("Nuevo número de porciones: ");
                gestor.actualizarPorciones(nombre, nuevas);
            } else {
                fallo("Opción de actualización no válida: " + campo);
                return;
            }
            System.out.println("Receta actualizada correctamente.");
        } catch (RecetaNoEncontradaException | IllegalArgumentException e) {
            fallo("No se actualizó la receta. " + e.getMessage());
        }
    }

    private static void listarRecetas() {
        Receta[] recetas = gestor.listar();
        if (recetas.length == 0) {
            System.out.println("No hay recetas registradas.");
        } else {
            System.out.println("\n=========== RECETAS REGISTRADAS ===========");
            for (int i = 0; i < recetas.length; i++) {
                Receta r = recetas[i];
                System.out.printf("%d. %s (%d porciones, %d ingredientes)%n",
                        i + 1, r.getNombre(), r.getPorciones(), r.getIngredientes().size());
            }
            System.out.println("===========================================");
        }
        
    }

    /** Informa del error al usuario y lo deja registrado para auditoría. */
    private static void fallo(String mensaje) {
        System.out.println("Error: " + mensaje);
        
    }
}
