public class CapacidadExcedidaException extends Exception {
    public CapacidadExcedidaException(int capacidad) {
        super("Se alcanzó la capacidad máxima del sistema (" + capacidad + " recetas).");
    }
}
