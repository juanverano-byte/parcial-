import java.time.LocalDate;
import java.util.Scanner;
class Entrada {
    private static final Scanner sc = new Scanner(System.in);

    public static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return sc.nextLine().trim();
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje));
            } catch (NumberFormatException e) {
                RegistroEventos.registrar("ERROR", "Entrada no numérica");
                System.out.println("Ingrese un número válido.");
            }
        }
    }

    public static LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                return LocalDate.parse(leerTexto(mensaje));
            } catch (Exception e) {
                RegistroEventos.registrar("ERROR", "Fecha con formato inválido");
                System.out.println("Formato inválido. Use AAAA-MM-DD (ej. 2026-11-05).");
            }
        }
    }
}