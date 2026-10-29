class LibroNoEncontradoException extends Exception {
    public LibroNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}

class SinCopiasDisponiblesException extends Exception {
    public SinCopiasDisponiblesException(String mensaje) {
        super(mensaje);
    }
}

class CapacidadExcedidaException extends Exception {
    public CapacidadExcedidaException(String mensaje) {
        super(mensaje);
    }
}