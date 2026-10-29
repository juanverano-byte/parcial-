class Sala {
    private final int id;
    private final String nombre;
    private final int capacidad;

    public Sala(int id, String nombre, int capacidad) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la sala no puede estar vacío.");
        }
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad debe ser mayor que cero.");
        }
        this.id = id;
        this.nombre = nombre;
        this.capacidad = capacidad;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getCapacidad() { return capacidad; }

    @Override
    public String toString() {
        return "Sala " + id + " - " + nombre + " (capacidad: " + capacidad + ")";
    }
}