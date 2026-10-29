class Biblioteca {
    private static final int MAX_LIBROS = 10;

    private final Libro[] catalogo = new Libro[MAX_LIBROS];
    private int totalLibros = 0;

    private Libro buscarIsbn(String isbn) throws LibroNoEncontradoException {
        for (int i = 0; i < totalLibros; i++) {
            if (catalogo[i].getIsbn().equalsIgnoreCase(isbn)) return catalogo[i];
        }
        throw new LibroNoEncontradoException("No existe un libro con ISBN " + isbn + ".");
    }

    public void registrarLibro(String titulo, String autor, String isbn, int copias)
            throws CapacidadExcedidaException {
        Libro nuevo = new Libro(titulo, autor, isbn, copias);

        for (int i = 0; i < totalLibros; i++) {
            if (catalogo[i].getIsbn().equalsIgnoreCase(isbn)) {
                throw new IllegalArgumentException("Ya existe un libro con el ISBN " + isbn + ".");
            }
        }
        if (totalLibros >= MAX_LIBROS) {
            throw new CapacidadExcedidaException("El catálogo alcanzó el máximo de " + MAX_LIBROS + " libros.");
        }
        catalogo[totalLibros++] = nuevo;
        RegistroEventos.registrar("REGISTRO", nuevo.toString());
    }

    public Libro prestarLibro(String isbn) throws LibroNoEncontradoException, SinCopiasDisponiblesException {
        Libro libro = buscarIsbn(isbn);
        if (libro.getCopiasDisponibles() == 0) {
            throw new SinCopiasDisponiblesException("No hay copias disponibles de '" + libro.getTitulo() + "'.");
        }
        libro.prestar();
        RegistroEventos.registrar("PRESTAMO", libro.toString());
        return libro;
    }

    public Libro devolverLibro(String isbn) throws LibroNoEncontradoException {
        Libro libro = buscarIsbn(isbn);
        if (libro.getCopiasDisponibles() >= libro.getCopiasTotales()) {
            throw new IllegalArgumentException("Todas las copias de '" + libro.getTitulo()
                    + "' ya están en la biblioteca.");
        }
        libro.devolver();
        RegistroEventos.registrar("DEVOLUCION", libro.toString());
        return libro;
    }

    public Libro consultarPorIsbn(String isbn) throws LibroNoEncontradoException {
        Libro libro = buscarIsbn(isbn);
        RegistroEventos.registrar("CONSULTA", "Por ISBN: " + isbn);
        return libro;
    }

    public Libro consultarPorTitulo(String titulo) throws LibroNoEncontradoException {
        for (int i = 0; i < totalLibros; i++) {
            if (catalogo[i].getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                RegistroEventos.registrar("CONSULTA", "Por título: " + titulo);
                return catalogo[i];
            }
        }
        throw new LibroNoEncontradoException("No se encontró ningún libro con el título '" + titulo + "'.");
    }

    public String listarLibros() {
        RegistroEventos.registrar("LISTADO", "Listado completo del catálogo");
        if (totalLibros == 0) return "El catálogo está vacío.\n";
        String resultado = "";
        for (int i = 0; i < totalLibros; i++) {
            resultado += catalogo[i] + "\n";
        }
        return resultado;
    }
}