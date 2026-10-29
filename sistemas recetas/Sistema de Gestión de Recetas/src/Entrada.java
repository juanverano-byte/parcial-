import java.util.Scanner;

/** Lectura de datos por consola. Maneja las entradas no numéricas. */
public class Entrada {
    private static final Scanner scanner = new Scanner(System.in);

    public static String leerLinea(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public static int leerEntero(String mensaje) {
        while (true) {
            String texto = leerLinea(mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida: debe ingresar un número entero.");
                
            }
        }
    }

    public static double leerDouble(String mensaje) {
        while (true) {
            String texto = leerLinea(mensaje);
            try {
                return Double.parseDouble(texto.replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.println("Entrada no válida: debe ingresar un número.");
                
            }
        }
    }
}