import java.time.LocalDate;
class Reserva {
    private final int id;
    private final Sala sala;
    private final String reservante;
    private final int asistentes;
    private final LocalDate fecha;
    private final int hora; // 0 a 23, duración fija de 1 hora

    public Reserva(int id, Sala sala, String reservante, int asistentes, LocalDate fecha, int hora) {
        if (reservante == null || reservante.isBlank()) {
            throw new IllegalArgumentException("El nombre de quien reserva no puede estar vacío.");
        }
        if (asistentes <= 0) {
            throw new IllegalArgumentException("El número de asistentes debe ser positivo.");
        }
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha es obligatoria.");
        }
        if (hora < 0 || hora > 23) {
            throw new IllegalArgumentException("La hora debe estar entre 0 y 23.");
        }
        this.id = id;
        this.sala = sala;
        this.reservante = reservante;
        this.asistentes = asistentes;
        this.fecha = fecha;
        this.hora = hora;
    }

    public int getId() { return id; }
    public Sala getSala() { return sala; }
    public String getReservante() { return reservante; }
    public int getAsistentes() { return asistentes; }
    public LocalDate getFecha() { return fecha; }
    public int getHora() { return hora; }

    @Override
    public String toString() {
        return "Reserva #" + id + " | " + sala.getNombre() + " | " + fecha + " " + hora + ":00-" + (hora + 1)
                + ":00 | " + reservante + " (" + asistentes + " personas)";
    }
}