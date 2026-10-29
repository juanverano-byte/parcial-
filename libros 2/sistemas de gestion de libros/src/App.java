
public class App {
    public static void main(String[] args) {
        Biblioteca biblioteca = new Biblioteca();

        int opcion;
        do {
            System.out.println("\n=== BIBLIOTECA ===");
            System.out.println("1. Registrar libro");
            System.out.println("2. Prestar libro");
            System.out.println("3. Devolver libro");
            System.out.println("4. Consultar libro (ISBN o título)");
            System.out.println("5. Listar catálogo");
            System.out.println("6. Ver registro de eventos");
            System.out.println("0. Salir");
            opcion = Entrada.leerEntero("Opción: ");

            try {
                switch (opcion) {
                    case 1: {
                        String titulo = Entrada.leerTexto("Título: ");
                        String autor = Entrada.leerTexto("Autor: ");
                        String isbn = Entrada.leerTexto("ISBN: ");
                        int copias = Entrada.leerEntero("Cantidad de copias: ");
                        biblioteca.registrarLibro(titulo, autor, isbn, copias);
                        System.out.println("Libro registrado correctamente.");
                        break;
                    }
                    case 2: {
                        Libro l = biblioteca.prestarLibro(Entrada.leerTexto("ISBN del libro a prestar: "));
                        System.out.println("Préstamo exitoso. " + l);
                        break;
                    }
                    case 3: {
                        Libro l = biblioteca.devolverLibro(Entrada.leerTexto("ISBN del libro a devolver: "));
                        System.out.println("Devolución exitosa. " + l);
                        break;
                    }
                    case 4: {
                        int tipo = Entrada.leerEntero("Buscar por: 1. ISBN  2. Título -> ");
                        if (tipo == 1) {
                            System.out.println(biblioteca.consultarPorIsbn(Entrada.leerTexto("ISBN: ")));
                        } else if (tipo == 2) {
                            System.out.println(biblioteca.consultarPorTitulo(Entrada.leerTexto("Título: ")));
                        } else {
                            System.out.println("Opción de búsqueda inválida.");
                        }
                        break;
                    }
                    case 5:
                        System.out.print(biblioteca.listarLibros());
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
            } catch (IllegalArgumentException | LibroNoEncontradoException
                     | SinCopiasDisponiblesException | CapacidadExcedidaException e) {
                RegistroEventos.registrar("ERROR", e.getMessage());
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }
}