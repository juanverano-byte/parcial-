public class Ingrediente {
    private final String nombre;
    private final double cantidad;
    private final String unidad;

    public Ingrediente(String nombre, double cantidad, String unidad) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del ingrediente no puede estar vacío.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad del ingrediente debe ser mayor que 0.");
        }
        if (unidad == null || unidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La unidad del ingrediente no puede estar vacía.");
        }
        this.nombre = nombre.trim();
        this.cantidad = cantidad;
        this.unidad = unidad.trim();
    }

    public String getNombre() { return nombre; }
    public double getCantidad() { return cantidad; }
    public String getUnidad() { return unidad; }

    @Override
    public String toString() {
        String cant = (cantidad % 1 == 0) ? String.valueOf((long) cantidad) : String.valueOf(cantidad);
        return cant + " " + unidad + " de " + nombre;
    }
}