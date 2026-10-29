class CapacidadExcedidaException extends Exception {
    public CapacidadExcedidaException(String mensaje) {
        super(mensaje);
    }
}

class ReservaNoEncontradaException extends Exception {
    public ReservaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}

class SalaNoDisponibleException extends Exception {
    public SalaNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}