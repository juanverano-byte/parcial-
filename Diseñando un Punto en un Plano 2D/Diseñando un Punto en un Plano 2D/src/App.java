public class App {
    public static void main(String[] args) {

        // Punto en el origen
        Punto p1 = new Punto();
        System.out.print("Punto p1 (origen): ");
        p1.mostrarCoordenadas();

        // Punto con coordenadas
        Punto p2 = new Punto(3, 4);
        System.out.print("Punto p2: ");
        p2.mostrarCoordenadas();
        System.out.println("Distancia de p2 al origen: " + p2.calcularDistanciaDesdeOrigen());

        // Mover el punto
        p2.mover(2, -1);
        System.out.print("p2 después de mover (2, -1): ");
        p2.mostrarCoordenadas();

        // Modificar con setters
        p2.setX(10);
        p2.setY(5);
        System.out.print("p2 después de setX(10) y setY(5): ");
        p2.mostrarCoordenadas();

        // Usar getters
        System.out.println("getX() = " + p2.getX() + ", getY() = " + p2.getY());
        System.out.println("Distancia final de p2 al origen: " + p2.calcularDistanciaDesdeOrigen());
    }
}

