import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        Sala[] salas = {
            new Sala(1, "Sala Andes", 6),
            new Sala(2, "Sala Caribe", 10),
            new Sala(3, "Sala Amazonas", 20)
        };
        GestorReservas gestor = new GestorReservas(salas);

        int opcion;
        do {
            System.out.println("\n=== RESERVA DE SALAS ===");
            System.out.println("1. Ver salas");
            System.out.println("2. Crear reserva");
            System.out.println("3. Consultar reservas por sala");
            System.out.println("4. Consultar reservas por fecha");
            System.out.println("5. Cancelar reserva");
            System.out.println("6. Ver registro de eventos");
            System.out.println("0. Salir");
            opcion = Entrada.leerEntero("Opción: ");

            try {
                switch (opcion) {
                    case 1:
                        for (Sala s : gestor.getSalas()) System.out.println(s);
                        break;
                    case 2:
                        int idSala = Entrada.leerEntero("Id de la sala: ");
                        String nombre = Entrada.leerTexto("Nombre de quien reserva: ");
                        int asistentes = Entrada.leerEntero("Número de asistentes: ");
                        LocalDate fecha = Entrada.leerFecha("Fecha (AAAA-MM-DD): ");
                        int hora = Entrada.leerEntero("Hora de inicio (0-23): ");
                        Reserva r = gestor.crearReserva(idSala, nombre, asistentes, fecha, hora);
                        System.out.println("Reserva creada: " + r);
                        break;
                    case 3:
                        System.out.print(gestor.consultarPorSala(Entrada.leerEntero("Id de la sala: ")));
                        break;
                    case 4:
                        System.out.print(gestor.consultarPorFecha(Entrada.leerFecha("Fecha (AAAA-MM-DD): ")));
                        break;
                    case 5:
                        gestor.cancelarReserva(Entrada.leerEntero("Id de la reserva: "));
                        System.out.println("Reserva cancelada.");
                        break;
                    case 6:
                        RegistroEventos.mostrar();
                        break;
                    case 0:
                        System.out.println("¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException | ReservaNoEncontradaException
                     | CapacidadExcedidaException | SalaNoDisponibleException e) {
                RegistroEventos.registrar("ERROR", e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}