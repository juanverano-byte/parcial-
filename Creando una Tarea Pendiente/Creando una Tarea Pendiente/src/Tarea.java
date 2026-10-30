public class Tarea {

    // Constantes de prioridad
    private static final int PRIORIDAD_MIN = 1;
    private static final int PRIORIDAD_MAX = 3;
    private static final int PRIORIDAD_DEFECTO = 2;

    // Atributos privados
    private String descripcion;
    private int prioridad;
    private boolean completada;

    // Constructor que recibe la descripción y asigna prioridad por defecto (2 - Media)
    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.prioridad = PRIORIDAD_DEFECTO;
        this.completada = false;
    }

    // Constructor que recibe descripción y prioridad, validando el rango (1 a 3)
    public Tarea(String descripcion, int prioridad) {
        this.descripcion = descripcion;
        this.completada = false;
        if (esPrioridadValida(prioridad)) {
            this.prioridad = prioridad;
        } else {
            System.out.println("Prioridad inválida (" + prioridad + "). Se asigna la prioridad por defecto: "
                    + PRIORIDAD_DEFECTO + " - Media.");
            this.prioridad = PRIORIDAD_DEFECTO;
        }
    }

    // Valida que la prioridad esté entre 1 y 3
    private boolean esPrioridadValida(int prioridad) {
        return prioridad >= PRIORIDAD_MIN && prioridad <= PRIORIDAD_MAX;
    }

    // Métodos de acceso
    public String getDescripcion() {
        return descripcion;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public boolean isCompletada() {
        return completada;
    }

    // Cambia la prioridad solo si es válida
    public void setPrioridad(int nuevaPrioridad) {
        if (esPrioridadValida(nuevaPrioridad)) {
            this.prioridad = nuevaPrioridad;
        } else {
            System.out.println("Prioridad inválida (" + nuevaPrioridad + "). Debe estar entre "
                    + PRIORIDAD_MIN + " y " + PRIORIDAD_MAX + ". No se realizó el cambio.");
        }
    }

    // Marca la tarea como completada
    public void marcarComoCompletada() {
        this.completada = true;
    }

    // Imprime descripción, prioridad y estado
    public void mostrarEstado() {
        String textoPrioridad;
        switch (prioridad) {
            case 1:
                textoPrioridad = "Alta";
                break;
            case 2:
                textoPrioridad = "Media";
                break;
            default:
                textoPrioridad = "Baja";
                break;
        }
        System.out.println("Tarea: " + descripcion);
        System.out.println("Prioridad: " + prioridad + " (" + textoPrioridad + ")");
        System.out.println("Completada: " + (completada ? "Sí" : "No"));
        System.out.println("-----------------------------");
    }
}