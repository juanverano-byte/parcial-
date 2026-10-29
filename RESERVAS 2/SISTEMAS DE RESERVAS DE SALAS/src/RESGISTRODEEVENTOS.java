import java.time.LocalDateTime; 

class RegistroEventos {
    private static final String[] eventos = new String[500];
    private static int total = 0;

    public static void registrar(String tipo, String detalle) {
        String linea = "[" + LocalDateTime.now().withNano(0) + "] " + tipo + ": " + detalle;
        if (total < eventos.length) {
            eventos[total++] = linea;
        }
        System.out.println("  (log) " + linea);
    }

    public static void mostrar() {
        if (total == 0) {
            System.out.println("No hay eventos registrados.");
            return;
        }
        for (int i = 0; i < total; i++) {
            System.out.println(eventos[i]);
        }
    }
}