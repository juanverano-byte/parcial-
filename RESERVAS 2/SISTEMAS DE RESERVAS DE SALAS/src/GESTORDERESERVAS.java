import java.time.LocalDate;
import java.time.LocalDateTime;

class GestorReservas {
    private static final int MAX_RESERVAS = 10;

    private final Sala[] salas;
    private final Reserva[] reservas = new Reserva[MAX_RESERVAS];
    private int siguienteId = 1;

    public GestorReservas(Sala[] salas) {
        this.salas = salas;
    }

    public Sala[] getSalas() {
        return salas;
    }

    public Sala buscarSala(int idSala) throws ReservaNoEncontradaException {
        for (Sala s : salas) {
            if (s.getId() == idSala) return s;
        }
        throw new ReservaNoEncontradaException("La sala con id " + idSala + " no existe.");
    }

    public Reserva crearReserva(int idSala, String reservante, int asistentes, LocalDate fecha, int hora)
            throws ReservaNoEncontradaException, CapacidadExcedidaException, SalaNoDisponibleException {

        Sala sala = buscarSala(idSala);
        Reserva nueva = new Reserva(siguienteId, sala, reservante, asistentes, fecha, hora);

        LocalDateTime ahora = LocalDateTime.now();
        if (fecha.isBefore(ahora.toLocalDate())
                || (fecha.isEqual(ahora.toLocalDate()) && hora <= ahora.getHour())) {
            throw new IllegalArgumentException("No se puede reservar en una fecha/hora pasada.");
        }

        if (asistentes > sala.getCapacidad()) {
            throw new CapacidadExcedidaException("La sala '" + sala.getNombre() + "' admite máximo "
                    + sala.getCapacidad() + " personas.");
        }

        for (Reserva r : reservas) {
            if (r != null && r.getSala().getId() == idSala
                    && r.getFecha().isEqual(fecha) && r.getHora() == hora) {
                throw new SalaNoDisponibleException("La sala ya está reservada en ese horario (reserva #"
                        + r.getId() + ").");
            }
        }

        for (int i = 0; i < reservas.length; i++) {
            if (reservas[i] == null) {
                reservas[i] = nueva;
                siguienteId++;
                RegistroEventos.registrar("CREACION", nueva.toString());
                return nueva;
            }
        }
        throw new CapacidadExcedidaException("El sistema alcanzó el máximo de " + MAX_RESERVAS
                + " reservas almacenables.");
    }

    public String consultarPorSala(int idSala) throws ReservaNoEncontradaException {
        Sala sala = buscarSala(idSala);
        String resultado = "";
        for (Reserva r : reservas) {
            if (r != null && r.getSala().getId() == idSala) resultado += r + "\n";
        }
        RegistroEventos.registrar("CONSULTA", "Reservas de la sala " + sala.getNombre());
        return resultado.isEmpty() ? "La sala no tiene reservas.\n" : resultado;
    }

    public String consultarPorFecha(LocalDate fecha) {
        String resultado = "";
        for (Reserva r : reservas) {
            if (r != null && r.getFecha().isEqual(fecha)) resultado += r + "\n";
        }
        RegistroEventos.registrar("CONSULTA", "Reservas de la fecha " + fecha);
        return resultado.isEmpty() ? "No hay reservas para esa fecha.\n" : resultado;
    }

    public void cancelarReserva(int idReserva) throws ReservaNoEncontradaException {
        for (int i = 0; i < reservas.length; i++) {
            if (reservas[i] != null && reservas[i].getId() == idReserva) {
                RegistroEventos.registrar("CANCELACION", reservas[i].toString());
                reservas[i] = null;
                return;
            }
        }
        throw new ReservaNoEncontradaException("La reserva con id " + idReserva + " no existe.");
    }
}