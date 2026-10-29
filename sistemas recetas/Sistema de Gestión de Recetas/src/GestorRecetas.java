import java.util.Arrays;

public class GestorRecetas {
    private final Receta[] recetas;
    private int total = 0;

    public GestorRecetas(int capacidad) {
        this.recetas = new Receta[capacidad];
    }

    public boolean estaLleno() {
        return total >= recetas.length;
    }

    public void registrar(Receta receta) throws CapacidadExcedidaException {
        if (estaLleno()) {
            throw new CapacidadExcedidaException(recetas.length);
        }
        if (existe(receta.getNombre())) {
            throw new IllegalArgumentException("Ya existe una receta llamada \"" + receta.getNombre() + "\".");
        }
        recetas[total++] = receta;
        
    }

    public Receta buscar(String nombre) throws RecetaNoEncontradaException {
        Receta r = localizar(nombre);
        
        return r;
    }

    /** Comprueba que exista, sin registrar una búsqueda en el log. */
    public void verificarExiste(String nombre) throws RecetaNoEncontradaException {
        localizar(nombre);
    }

    public void actualizarInstrucciones(String nombre, String nuevas) throws RecetaNoEncontradaException {
        Receta r = localizar(nombre);
        r.setInstrucciones(nuevas);
        
    }

    public void actualizarPorciones(String nombre, int nuevas) throws RecetaNoEncontradaException {
        Receta r = localizar(nombre);
        r.setPorciones(nuevas);
        
    }

    public Receta[] listar() {
        return Arrays.copyOf(recetas, total);
    }

    private Receta localizar(String nombre) throws RecetaNoEncontradaException {
        String buscado = (nombre == null) ? "" : nombre.trim();
        for (int i = 0; i < total; i++) {
            if (recetas[i].getNombre().equalsIgnoreCase(buscado)) {
                return recetas[i];
            }
        }
        throw new RecetaNoEncontradaException(buscado);
    }

    private boolean existe(String nombre) {
        try {
            localizar(nombre);
            return true;
        } catch (RecetaNoEncontradaException e) {
            return false;
        }
    }
}
