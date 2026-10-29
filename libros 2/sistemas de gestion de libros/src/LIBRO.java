class Libro {
    private final String titulo;
    private final String autor;
    private final String isbn;
    private final int copiasTotales;
    private int copiasDisponibles;

    public Libro(String titulo, String autor, String isbn, int copiasTotales) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("El título no puede estar vacío.");
        }
        if (autor == null || autor.isBlank()) {
            throw new IllegalArgumentException("El autor no puede estar vacío.");
        }
        if (isbn == null || isbn.isBlank()) {
            throw new IllegalArgumentException("El ISBN no puede estar vacío.");
        }
        if (copiasTotales <= 0) {
            throw new IllegalArgumentException("La cantidad de copias debe ser positiva.");
        }
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.copiasTotales = copiasTotales;
        this.copiasDisponibles = copiasTotales;
    }

    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public int getCopiasTotales() { return copiasTotales; }
    public int getCopiasDisponibles() { return copiasDisponibles; }

    public void prestar() { copiasDisponibles--; }
    public void devolver() { copiasDisponibles++; }

    @Override
    public String toString() {
        return "[" + isbn + "] " + titulo + " - " + autor
                + " | Disponibles: " + copiasDisponibles + "/" + copiasTotales;
    }
}
