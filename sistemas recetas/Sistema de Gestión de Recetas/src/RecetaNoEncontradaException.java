public class RecetaNoEncontradaException extends Exception {
    public RecetaNoEncontradaException(String nombre) {
        super("No existe ninguna receta llamada \"" + nombre + "\".");
    }
}