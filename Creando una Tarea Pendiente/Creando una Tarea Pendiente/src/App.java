public class App {
    public static void main(String[] args) {

        // Tarea con prioridad por defecto (Media)
        Tarea t1 = new Tarea("Estudiar para el parcial");
        t1.mostrarEstado();

        // Tarea con prioridad válida
        Tarea t2 = new Tarea("Entregar el taller de Java", 1);
        t2.mostrarEstado();

        // Tarea con prioridad inválida (usa la de defecto)
        Tarea t3 = new Tarea("Limpiar el escritorio", 7);
        t3.mostrarEstado();

        // Cambiar prioridad con valor válido
        t3.setPrioridad(3);
        System.out.println("Nueva prioridad de t3: " + t3.getPrioridad());

        // Cambiar prioridad con valor inválido
        t3.setPrioridad(0);

        // Marcar como completada
        t2.marcarComoCompletada();
        System.out.println("¿t2 completada? " + t2.isCompletada());
        t2.mostrarEstado();

        // Usar getter de descripción
        System.out.println("Descripción de t1: " + t1.getDescripcion());
    }
}