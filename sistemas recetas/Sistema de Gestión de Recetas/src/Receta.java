import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Receta {
    private final String nombre;
    private final List<Ingrediente> ingredientes;
    private String instrucciones;
    private int porciones;

    public Receta(String nombre, List<Ingrediente> ingredientes, String instrucciones, int porciones) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la receta no puede estar vacío.");
        }
        if (ingredientes == null || ingredientes.isEmpty()) {
            throw new IllegalArgumentException("La receta debe tener al menos un ingrediente.");
        }
        validarInstrucciones(instrucciones);
        validarPorciones(porciones);

        this.nombre = nombre.trim();
        this.ingredientes = new ArrayList<>(ingredientes);
        this.instrucciones = instrucciones.trim();
        this.porciones = porciones;
    }

    private static void validarInstrucciones(String instrucciones) {
        if (instrucciones == null || instrucciones.trim().isEmpty()) {
            throw new IllegalArgumentException("Las instrucciones de preparación no pueden estar vacías.");
        }
    }

    private static void validarPorciones(int porciones) {
        if (porciones <= 0) {
            throw new IllegalArgumentException("El número de porciones debe ser mayor que 0.");
        }
    }

    public String getNombre() { return nombre; }
    public String getInstrucciones() { return instrucciones; }
    public int getPorciones() { return porciones; }
    public List<Ingrediente> getIngredientes() { return Collections.unmodifiableList(ingredientes); }

    public void setInstrucciones(String instrucciones) {
        validarInstrucciones(instrucciones);
        this.instrucciones = instrucciones.trim();
    }

    public void setPorciones(int porciones) {
        validarPorciones(porciones);
        this.porciones = porciones;
    }

    public void mostrar() {
        System.out.println("\n=============== " + nombre.toUpperCase() + " ===============");
        System.out.println("Porciones: " + porciones);
        System.out.println("Ingredientes:");
        for (Ingrediente ing : ingredientes) {
            System.out.println("  - " + ing);
        }
        System.out.println("Preparación:");
        System.out.println("  " + instrucciones);
        System.out.println("===================================================");
    }
}
